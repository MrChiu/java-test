package com.example.lockTest.AQS;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 15:47 2019-04-10
 */
public class Mutex implements Lock, Serializable {

    // sync对象完成所有的艰苦工作
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    // Our internal helper class
    private static class Sync extends AbstractQueuedSynchronizer {
        // 报告是否处于锁定状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 如果状态为零，则获取锁
        @Override
        public boolean tryAcquire(int acquires) {
            assert acquires == 1; // Otherwise unused
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 通过将状态设置为零来释放锁
        @Override
        protected boolean tryRelease(int releases) {
            assert releases == 1; // Otherwise unused
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 提供了条件
        Condition newCondition() {
            return new ConditionObject();
        }

        // 正确反序列化
        private void readObject(ObjectInputStream s)throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            // 重置为解锁状态
            setState(0);
        }
    }
}
