package com.onightperson.hearken.thread.unsynch;

import java.util.List;

/**
 * Created by liubaozhu on 17/4/3.
 */

public class UnsynchBankManager {
    private static final String TAG = "UnsynchBankManager";

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
        /*for (int i = 0; i < ACCOUNTS; i++) {
            Runnable task = new TradeTask(mBank, i, INITIAL_BALANCE);
            Thread t = new Thread(task);
            t.start();
        }*/

        Object origin = null;

        String testStr = (String) origin;
        List<String> testList = (List<String>) origin;

        System.out.println("testStr: " + testStr + ", testList: " + testList);
    }
}
