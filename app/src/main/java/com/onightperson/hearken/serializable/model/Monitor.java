package com.onightperson.hearken.serializable.model;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by liubaozhu on 17/9/2.
 * 一个类若继承了 Serializable 接口，就具有了序列化的能力。该序列化接口
 * 没有方法和域，仅标识能够序列化的语义。
 *
 * 为了使非序列化类的子类能够实现序列化，子类必须承担起存储和恢复其父类 public
 * protected 及 package（可访问） 访问类型的字段。只有在其父类有一个可以访问的
 * 无参构造函数来初始化状态时，子类才有必要实现序列化。如果不是上述情况，将子类声明
 * 为 Serializable 是错误的。
 *
 * 为什么是这样呢？
 * 因为在反序列化时，子类会使用父类的无参构造函数来初始化其字段。
 */

public class Monitor extends Manager implements Serializable {
    private static final long serialVersionUID = 42L;

    public int mClass;

    public Monitor(int id, String name, int position) {
        super(id, name, position);
    }


    @Override
    public String toString() {
        return "id: " + mId + ", name: " + mName + ", position: " + mPosition
                + ", class: " + mClass;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Monitor) {
            Monitor monitor = (Monitor) obj;
            return (mId == monitor.mId) && isEqual(mName, monitor.mName)
                    && (mPosition == monitor.mPosition) && (mClass == monitor.mClass);

        }
        return false;
    }

    private boolean isEqual(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
}
