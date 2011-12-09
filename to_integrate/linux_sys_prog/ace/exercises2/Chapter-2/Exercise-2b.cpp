#define ACE_NTRACE 1
#include <ace/OS_NS_stdio.h>
#include <ace/Malloc_Base.h>    // To get ACE_Allocator
#include <ace/Message_Queue.h>
#include <ace/Read_Buffer.h>
#include <ace/Thread_Manager.h>
#include <ace/Service_Config.h>
#include <ace/Truncate.h>
#include "ace/Timer_Queue.h"
#include "ace/Reactor.h"

#define Message_Offset 6
// Global thread manager.
static ACE_Thread_Manager thr_mgr;
// Make the queue be capable of being *very* large.
static const long max_queue = LONG_MAX;

ACE_Message_Queue<ACE_MT_SYNCH> msg_queue1 (max_queue);

ACE_Message_Queue<ACE_MT_SYNCH> msg_queue2 (max_queue);
static void * consumer (ACE_Message_Queue<ACE_MT_SYNCH> *msg_queue);
ACE_Reactor     reactor;     // Reactor

static int done = 0;    // Termination state of both consumers

class MyTime_Handler : public ACE_Event_Handler
{
public:
    //Method which is called back by the Reactor when timeout occurs.
    virtual int handle_timeout (const ACE_Time_Value &tv, const void *arg)
		 { long argument = long (arg);
//     ACE_DEBUG ((LM_DEBUG, "Timer timed out at %d! arg=%d\n", tv.sec(), argument));
//     Activate the consumer
       if ( argument == 1 )
					{ if (thr_mgr.spawn (ACE_THR_FUNC (consumer), (void *) &msg_queue1,
                         THR_NEW_LWP | THR_DETACHED) == -1)
                ACE_ERROR_RETURN ((LM_ERROR, "%p\n", "spawn consumer1"), 1);
								return 1;
					}
			 else
					{ if (thr_mgr.spawn (ACE_THR_FUNC (consumer), (void *) &msg_queue2,
                         THR_NEW_LWP | THR_DETACHED) == -1)
                ACE_ERROR_RETURN ((LM_ERROR, "%p\n", "spawn consumer2"), 1);
								return 1;
					}
			    
//       Keep yourself registered with the Reactor.
         return 0;
      }
};

// The consumer dequeues a message from the ACE_Message_Queue, writes
// the message to the stderr stream, and deletes the message.  The
// producer sends a 0-sized message to inform the consumer to stop
// reading and exit.

static void * consumer (ACE_Message_Queue<ACE_MT_SYNCH> *msg_queue)
{ // Keep looping, reading a message out of the queue, until we
  // timeout or get a message with a length == 0, which signals us to quit.

//ACE_DEBUG ((LM_DEBUG , ACE_TEXT ("consumer : thread=%t Line:%l\n")));
//ACE_OS::sleep (1);
	// Read ONLY one message and return !!!
  ACE_Message_Block *mb;

  if (msg_queue->dequeue_head (mb) == -1)
      return 0;
  int length = ACE_Utils::truncate_cast<int> (mb->length ());

  mb->rd_ptr (mb->rd_ptr() + Message_Offset);  // Skip the type and delay
  if (length > 0)
      ACE_OS::puts ((mb->rd_ptr ()));

  // Free up the buffer memory and the Message_Block.
  mb->rd_ptr (mb->rd_ptr() - Message_Offset);         // Return to the actual buffer address
  ACE_Allocator::instance ()->free (mb->rd_ptr ());   // Free the buffer
  mb->release ();

	if ( ! length )   // End of data !!!
	    done++;
  return 0;
}

// The producer reads data from the stdin stream, creates a message,
// and then queues the message in the message list, where it is
// removed by the consumer thread.  A 0-sized message is enqueued when
// there is no more data to read.  The consumer uses this as a flag to
// know when to exit.

static void * producer ()
{ MyTime_Handler *th=new MyTime_Handler;

  ACE_DEBUG ((LM_DEBUG , ACE_TEXT ("producer : thread=%t Line:%l\n")));
  ACE_Read_Buffer rb (ACE_STDIN);

  // Keep reading stdin, until we reach EOF.

  for (;;)
    { // Allocate a new buffer.
      char *buffer = rb.read ('\n');
      ACE_Message_Block *mb;
      int timer_id;
			int type;
			float delay;
			static float AbsoluteDelay=0.0;

      if (buffer == 0)
        { // Send a 0-sized shutdown message to the other thread and exit.

          ACE_NEW_RETURN (mb, ACE_Message_Block ((size_t) 0), 0);

          // Send Zero size message to both queues !!!
          if (msg_queue1.enqueue_tail (mb) == -1)
            ACE_ERROR ((LM_ERROR, "(%t) %p\n", "put_next"));
          ACE_NEW_RETURN (mb, ACE_Message_Block ((size_t) 0), 0);
          if (msg_queue2.enqueue_tail (mb) == -1)
            ACE_ERROR ((LM_ERROR, "(%t) %p\n", "put_next"));
          reactor.schedule_timer (th,
                    (const void *) 1, // Specify queue1 in handle_timeout()
                    ACE_Time_Value (AbsoluteDelay+0.1));
          reactor.schedule_timer (th,
                    (const void *) 2, // Specify queue2 in handle_timeout()
                    ACE_Time_Value (AbsoluteDelay+0.2));
          break;
        }

      // Enqueue the message in priority order.
      else
        { sscanf (buffer, "%d %f", &type, &delay);
			    AbsoluteDelay += delay;
          // Allocate a new message, but have it "borrow" its memory from the buffer.
          ACE_NEW_RETURN (mb, ACE_Message_Block (rb.size (), ACE_Message_Block::MB_DATA,
                          0, buffer), 0);
          // get message size
          int size = ACE_Utils::truncate_cast<unsigned long> (rb.size ());
          mb->wr_ptr (rb.size ());
//			mb->rd_ptr(mb->rd_ptr() + Message_Offset);   // Skip thhe type and time in the message

//        ACE_DEBUG ((LM_DEBUG, "enqueueing message of size %d\n", size));
				switch (type)
              {  case 1:
//                        Enqueue in tail queue1
                          if (msg_queue1.enqueue_tail (mb) == -1)
                               ACE_ERROR ((LM_ERROR, "(%t) %p\n", "put_next"));
                          timer_id = 
                          reactor.schedule_timer (th,
                                    (const void *) 1, // Specify queue1 in handle_timeout()
                                    ACE_Time_Value (AbsoluteDelay));
// 		                    ACE_DEBUG ((LM_DEBUG , "case1: timer id=%d\n", timer_id));
                          break;
                 case 2:
//                        Enqueue in head queue1
                          if (msg_queue1.enqueue_head (mb) == -1)
                               ACE_ERROR ((LM_ERROR, "(%t) %p\n", "put_next"));
                          timer_id = 
                          reactor.schedule_timer (th,
                                    (const void *) 1, // Specify queue1 in handle_timeout()
                                    ACE_Time_Value (AbsoluteDelay));
//		                    ACE_DEBUG ((LM_DEBUG , "case2: timer id=%d\n", timer_id));
                          break;
                 case 3:
//                        Enqueue in tail queue2
                          if (msg_queue2.enqueue_tail (mb) == -1)
                               ACE_ERROR ((LM_ERROR, "(%t) %p\n", "put_next"));
                          timer_id = 
                          reactor.schedule_timer (th,
                                    (const void *) 2, // Specify queue2 in handle_timeout()
                                    ACE_Time_Value (AbsoluteDelay));
//				                ACE_DEBUG ((LM_DEBUG , "case3: timer id=%d\n", timer_id));
                          break;
                 case 4:
//                        Enqueue in head queue2
                          if (msg_queue2.enqueue_head (mb) == -1)
                               ACE_ERROR ((LM_ERROR, "(%t) %p\n", "put_next"));
                          timer_id = 
                          reactor.schedule_timer (th,
                                    (const void *) 2, // Specify queue2 in handle_timeout()
                                    ACE_Time_Value (AbsoluteDelay));
//				                ACE_DEBUG ((LM_DEBUG , "case4: timer id=%d\n", timer_id));
                          break;
                 default:
				                  ACE_DEBUG ((LM_DEBUG , "default: <%d> doing nothing!!!\n", type));
                          break;
               }

        }
    }  // End of for loop

  // finished ? then return

  ACE_DEBUG ((LM_DEBUG , ACE_TEXT ("end of producer : thread=%t Line:%l\n")));
  return 0;
}

// Spawn off one thread that copies stdin to stdout in order of the size of each line.

int ACE_TMAIN (int, ACE_TCHAR *[])
{ ACE_DEBUG ((LM_DEBUG , ACE_TEXT ("main : thread=%t Line:%l\n")));
 
  if (thr_mgr.spawn (ACE_THR_FUNC (producer), (void *) NULL,
                     THR_NEW_LWP | THR_DETACHED) == -1)
        ACE_ERROR_RETURN ((LM_ERROR, "%p\n", "spawn producer"), 1);
  // Wait for producer thread to exit. Comsumer thread is handled by the done variable
  thr_mgr.wait ();
	// wait for all events to be completed
  while ( done != 2 )
      reactor.handle_events ();
  return 0;
}
