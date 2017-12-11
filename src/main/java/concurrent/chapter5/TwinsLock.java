package concurrent.chapter5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by ShannonAJ on 2017/12/10.
 * 1.自定义同步组件
 * 该工具在同一时刻只允许至多两个线程同时访问
 * 超过两个线程的访问将被阻塞
 *
 * 2.同一时刻支持多个线程访问，是共享式访问
 *
 *
 */
public class TwinsLock implements Lock{

    private static final Logger LOG = LoggerFactory.getLogger(TwinsLock.class);
    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {
        //构造方法
        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must lager than zero.");
            }
            //设置同步状态
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (;;) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (;;) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }

            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
