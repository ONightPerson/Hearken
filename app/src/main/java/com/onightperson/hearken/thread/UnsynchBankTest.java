package com.onightperson.hearken.thread;

import com.onightperson.hearken.thread.unsynch.UnsynchBankManager;

/**
 * Created by liubaozhu on 17/4/3.
 */

public class UnsynchBankTest {


    public static void main(String[] args) {
        UnsynchBankManager manager = UnsynchBankManager.getInstance();
        manager.doTask();
    }
}
