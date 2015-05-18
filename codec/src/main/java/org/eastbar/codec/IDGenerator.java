package org.eastbar.codec;

/**
 * Created by andysjtu on 2015/5/9.
 */
public class IDGenerator {
    public static short value=0;

    public static synchronized short nextID(){
        value++;
        if(value<0){
            value=1;
        }
        short newValue= value;
        return newValue;
    }

    public static synchronized void reset(short newValue){
        value= newValue;
    }

    public static void main(String[] args) {
        System.out.println(IDGenerator.nextID());
        System.out.println(IDGenerator.nextID());
        IDGenerator.reset(Short.MAX_VALUE);
        System.out.println(IDGenerator.nextID());
    }
}
