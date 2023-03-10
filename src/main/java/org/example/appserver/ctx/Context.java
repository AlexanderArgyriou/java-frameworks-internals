package org.example.appserver.ctx;

import java.util.ArrayList;
import java.util.List;

public class Context {
    private static final Context ctx = new Context();
    List<Object> beans = new ArrayList<>();

    public static Context getCtx() {
        return ctx;
    }

    public void addToContext(Object bean) {
        beans.add(bean);
    }

    public List<Object> getBeans() {
        return beans;
    }

    public Object getFromContext(Class<?> c) {
        return beans.stream().filter(b -> c.equals(b.getClass())).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Context{" +
                "beans=" + beans +
                '}';
    }
}
