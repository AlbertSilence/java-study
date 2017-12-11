package concurrent.chapter5;

import concurrent.SleepUtils;

import java.util.concurrent.locks.Lock;

/**
 * Created by ShannonAJ on 2017/12/11.
 */
public class TwinsLockTest {

    public static void main(String[] args) {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();

                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        //启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        //每隔1秒换行
        for (int j = 0; j < 10; j++) {
            SleepUtils.second(1);
            System.out.println();
        }

    }
}
