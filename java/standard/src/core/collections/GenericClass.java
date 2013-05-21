package core.collections;

import java.util.LinkedList;

public class GenericClass<T,J,R> {
      T t;
      J j;
      
      public GenericClass(int y, R r) {
    	  
      }
      public static void main(String[] args) {
    	  GenericClass<Integer,Float,LinkedList<Integer>> t=
    			  new GenericClass<Integer,Float,LinkedList<Integer>>(5,new LinkedList<Integer>());
          System.out.println(t.toString());
      }
}
