package com.onightperson.hearken.singleton;

/**
 * Created by liubaozhu on 17/3/16.
 */

/**
 * 实现方式 加载时间:延迟加载 静态内部类(利用ClassLoader的机制来保证初始化instance时只会有一个) 线程安全/效率较高
 */
public class DelayedLoadingStaticInnerClass {

    private DelayedLoadingStaticInnerClass() {}

    //只有在显式调用getInstance方法时，才会装载DelayedLoadingStaticInnerClassHolder类，从而实例化对象
    public static final DelayedLoadingStaticInnerClass getInstance() {
        return DelayedLoadingStaticInnerClassHolder.INSTANCE;
    }

    private static class DelayedLoadingStaticInnerClassHolder {
        private static final DelayedLoadingStaticInnerClass INSTANCE =
                new DelayedLoadingStaticInnerClass();
    }
}