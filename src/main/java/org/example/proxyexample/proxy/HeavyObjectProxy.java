package org.example.proxyexample.proxy;

import org.example.proxyexample.intrf.HeavyObject;

public class HeavyObjectProxy implements HeavyObject {
    private final HeavyObject heavyObject;

    public HeavyObjectProxy(HeavyObject heavyObject) {
        this.heavyObject = heavyObject;
    }

    @Override
    public void operation(String workChunk) {
        System.out.println("from proxy extra operation");
        heavyObject.operation(workChunk);
    }
}
