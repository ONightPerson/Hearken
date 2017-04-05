package com.onightperson.hearken.thread.unsynch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liubaozhu on 17/4/3.
 */

public class UnsynchBank {
    private final double[] mAccounts;// n个账户，账户初始金额由构造函数给出
//    private Lock mBankLock;
//    private Condition mSufficientAmount;


    public UnsynchBank(int n, double initialBalance) {
        mAccounts = new double[n];

        for (int i = 0; i < n; i++) {
            mAccounts[i] = initialBalance;
        }
//        mBankLock  = new ReentrantLock();
//        mSufficientAmount = mBankLock.newCondition();

    }

    public synchronized void trade(int from, int to , double amount) throws InterruptedException {
//        mBankLock.lock();
//
//        //金额不够
//        try {
//            while (mAccounts[from] < amount) {
//                mSufficientAmount.await();
//            }
//            //打印当前线程
//            System.out.println(Thread.currentThread());
//            mAccounts[from] -= amount;
//            System.out.printf(" %10.2f from %d to %d", amount, from, to);
//            mAccounts[to] += amount;
//            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
//            mSufficientAmount.signalAll();
//        } finally {
//            mBankLock.unlock();
//        }
        while (mAccounts[from] < amount) {
            wait();
        }
        //打印当前线程
        System.out.println(Thread.currentThread());
        mAccounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        mAccounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        notifyAll();
    }

    public double getTotalBalance() {
        double sum = 0;

        for (double a : mAccounts) {
            sum += a;
        }

        return sum;
    }

    public int size() {
        return mAccounts.length;
    }
}
