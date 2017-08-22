package concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ShannonAJ on 2017/8/7.
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(), "waitThread");
        waitThread.start();
        Thread notifyThread = new Thread(new Notify(), "notifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                while(flag) {
                    System.out.println(Thread.currentThread() + " flag is true, wait @ "
                            + new SimpleDateFormat("HH:mm:ss").format(new Date()));


                }

            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {

        }
    }
}
