package com.huuc.context;
//使用线程local来存储使用同一线程的数据
public class BaseContext {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(String id) {
        threadLocal.set(id);
    }

    public static String getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
