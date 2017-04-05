package com.onightperson.hearken.thread.unsynch;

/**
 * Created by liubaozhu on 17/4/3.
 */

public class UnsynchBankManager {
    public static final int ACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    private static UnsynchBank mBank;

    private static volatile UnsynchBankManager sInstance;

    public static UnsynchBankManager getInstance() {
        if (sInstance == null) {
            synchronized (UnsynchBankManager.class) {
                if (sInstance == null) {
                    sInstance = new UnsynchBankManager();
                }
            }
        }

        return sInstance;
    }

    private UnsynchBankManager() {
        mBank = new UnsynchBank(ACCOUNTS, INITIAL_BALANCE);
    }

    public static void doTask() {
        for (int i = 0; i < ACCOUNTS; i++) {
            Runnable task = new TradeTask(mBank, i, INITIAL_BALANCE);
            Thread t = new Thread(task);
            t.start();
        }
    }
}
