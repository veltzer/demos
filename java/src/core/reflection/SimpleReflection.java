package core.reflection;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class SimpleReflection {
    public static void main(String[] args) {
        List<Integer> mylist=new ArrayList<Integer>();
        Class<? extends List> c=mylist.getClass();
        TypeVariable<?> types[]=c.getTypeParameters();
        System.out.println(types[0].getName());
    }
}
