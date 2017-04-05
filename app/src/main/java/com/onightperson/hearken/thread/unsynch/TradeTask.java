package com.onightperson.hearken.thread.unsynch;

/**
 * Created by liubaozhu on 17/4/4.
 */

public class TradeTask implements Runnable {

    private UnsynchBank mBank;
    private int mFromAccount;
    private double mMaxAmount;
    private int DELAY = 10;

    public TradeTask(UnsynchBank bank, int from, double max) {
        mBank = bank;
        mFromAccount = from;
        mMaxAmount = max;
    }

    @Override
    public void run() {

//        try {
//            while (true) {
//
//                int toAccount = (int) (mBank.size() * Math.random());
//                double amount = mMaxAmount * Math.random();
//                mBank.trade(mFromAccount, toAccount, amount);
//                Thread.sleep((int) (DELAY * Math.random()));
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        
        int toAccount = (int) (mBank.size() * Math.random());
        double amount = mMaxAmount * Math.random();
        try {
            mBank.trade(mFromAccount, toAccount, amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
