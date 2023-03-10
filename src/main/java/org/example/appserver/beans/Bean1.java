package org.example.appserver.beans;

import org.example.appserver.annots.MyBean;
import org.example.appserver.annots.MyInjection;

@MyBean
public class Bean1 {
    @MyInjection
    private Bean bean2;

    public void printBean2() {
        System.out.println(bean2);
    }

    public Bean getBean2() {
        return bean2;
    }

    public void setBean2(Bean bean2) {
        this.bean2 = bean2;
    }
}
