package org.example.appserver.beans;

import org.example.appserver.annots.MyBean;
import org.example.appserver.annots.MyTransaction;

@MyBean
public class Bean2 implements Bean {
    @Override
    public String toString() {
        String msg = "i am bean 2";
        return "Bean2{" +
                "msg='" + msg + '\'' +
                '}';
    }

    @MyTransaction
    public void doSomething() {
        System.out.println("prits");
    }
}
