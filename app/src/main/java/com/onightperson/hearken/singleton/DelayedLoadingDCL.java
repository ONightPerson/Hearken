package com.onightperson.hearken.singleton;

/**
 * Created by liubaozhu on 17/3/16.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 加载时间：延迟加载 Double Check Lock 线程安全/效率较高
 * DCL 在JDK 1.5失效的原因
 * 在高并发环境，JDK 1.4及更早版本下，DCL偶尔会失败。其根本原因在于，Java中new一个对象并不是原子操作，编译
 * singleton = new Singleton()时，语句被转成多条汇编语句，它们大致做了3件事情：
 * 1 给Singleton实例分配内存空间
 * 2 调用私有构造函数singleton(),初始化成员变量
 * 3 将singleton对象指向分配的内存 (执行完该操作，singleton就不是null了)
 * 由于Java编译器允许处理器乱序执行，以及JDK 1.5之前旧的JAVA内存模型中Cache、寄存器到主存回写顺序的规定，
 * 2和3的执行顺序是无法确定的，可能是1->2->3，也可能是1->3->2.如果后面的情况，在线程A执行完步骤3但还没有执行2之前，
 * 被切换到线程B上，此时线程B执行第一个判断时，发现singleton不为null，则直接使用singleton，但构造函数却还没有完成所有的
 * 初始化操作，就会出错，也就是DCL失效问题。而volatile能够保证每
 */
public class DelayedLoadingDCL implements Serializable, Cloneable {
    private static final String TAG = "DelayedLoadingDCL";

    private static final long serialVersionUID = 1L;

    private volatile static DelayedLoadingDCL sInstance;

    public static DelayedLoadingDCL getInstance() {
        if (sInstance == null) {
            synchronized (DelayedLoadingDCL.class) {
                if (sInstance == null) {
                    sInstance = new DelayedLoadingDCL();
                }
            }
        }

        return sInstance;
    }

    private DelayedLoadingDCL() {
        if (null != sInstance) {
            throw  new RuntimeException("Cannot construct a singleton more than once");
        }
    }

    /**
     * 用于从流中替换对象，主要用于强制单例。当对象被读入，通过该方法将流对象替换为单例对象，这样
     * 可以确保通过序列和反序列化单例时不会生成单例类的其他对象。
     * @return
     */
    private Object readResolve() {
        return sInstance;
    }


    public static void main(String[] args) {
        DelayedLoadingDCL dclInstance1 = DelayedLoadingDCL.getInstance();
        DelayedLoadingDCL dclInstance2 = DelayedLoadingDCL.getInstance();

        System.out.println("main: is singleton pattern normally valid: "
                + (dclInstance1 == dclInstance2));

        File file = new File("../tempcache.txt");
        try {
            //将dclInstance1序列化到磁盘中
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(dclInstance1);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            DelayedLoadingDCL dclInstance3 = (DelayedLoadingDCL) ois.readObject();
            System.out.println("main: after deserialize, is singleton pattern still valid: "
                    + (dclInstance1 == dclInstance3));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

        //clone
        try {
            DelayedLoadingDCL dclInstance4 = (DelayedLoadingDCL) dclInstance1.clone();
            System.out.println("after clone, is singleton pattern still valid: "
                    + (dclInstance1 == dclInstance4)
                    + ", 两个单例是否相等: " + dclInstance1.equals(dclInstance4));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

  /*  @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }*/
}
