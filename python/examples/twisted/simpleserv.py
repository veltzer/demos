#!/usr/bin/python

from twisted.internet import reactor, protocol

class Echo(protocol.Protocol):
    """This is just about the simplest possible protocol"""
    
    def dataReceived(self, data):
        "As soon as any data is received, write it back."
        self.transport.write(data)

"""This runs the protocol on port 8000"""
factory = protocol.ServerFactory()
factory.protocol = Echo
reactor.listenTCP(8000,factory)
reactor.listenTCP(8002,factory)
reactor.run()