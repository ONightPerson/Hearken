package com.onightperson.hearken.singleton;

/**
 * Created by liubaozhu on 17/3/16.
 */

/**
 * 依赖JVM在类加载时完成唯一对象的实例化，基于类加载的机制，它们天生就是线程安全的。
 */
public class EagerlyLoadingOne {
    private EagerlyLoadingOne() {}

    public static final EagerlyLoadingOne sInstance = new EagerlyLoadingOne();
}
