package com.onightperson.hearken.javacode;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liubaozhu on 17/8/28.
 */

public class OperatorTest {
    private static final String TAG = "OperatorTest";

    public static void main(String[] args) {

////        int a = 0x1000004f;
////        int b = 0xffffffff;
////
////        long key = (long) a << 32;
////
////        System.out.println("key: " + Long.toHexString(key) + ", b: " + Long.toHexString(b));
//
//        int a = 0xffffffff;
//        int b = 0x10000000;
//        int c = 0x00000001;
//        int d = 1;
//        int e = -1;
//
//        System.out.println("a -> hex: " + String.format("% 8x", a)
//                + ", b: " + String.format("%08x", b)
//                + ", c -> hex: " + String.format("%08x", c)
//                + ", d -> hex: " + String.format("%08x", d)
//                + ", e -> hex: " + String.format("%08x", e));
        execShell(new String[] {"-c", " adb shell 'rm -rf /storage/emulated/0/DCIM/Camera/internal.mp4'"});
    }

    public static void execShell(String[] cmd) {
        Process process = null;
        try {
            // 权限限制
            process = Runtime.getRuntime().exec("sh");
            System.out.println("process: " + process);
            // 获取输出流
            PrintWriter e = new PrintWriter(process.getOutputStream());
            // 将命令写入
            e.println(cmd);
            // 提交命令
            e.flush();
            e.close();
            int result = process.waitFor();
            System.out.println("result: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }
}
