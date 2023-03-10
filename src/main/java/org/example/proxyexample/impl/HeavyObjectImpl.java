package org.example.proxyexample.impl;

import org.example.proxyexample.intrf.HeavyObject;

public class HeavyObjectImpl implements HeavyObject {
    private String workChunk;

    public String getWorkChunk() {
        return workChunk;
    }

    public void setWorkChunk(String workChunk) {
        this.workChunk = workChunk;
    }

    @Override
    public void operation(String workChunk) {
        setWorkChunk(workChunk);
        System.out.println(workChunk);
    }
}
