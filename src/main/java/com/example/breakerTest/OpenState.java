package com.example.breakerTest;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: qiudong
 * @description: 熔断器断开状态
 * 断开状态内部维护一个计数器，如果断开达到一定的时间，
 * 则自动切换到半断开状态，并且，在断开状态下，如果需要执行操作，则直接抛出异常。
 * @date: Created in 14:15 2018-11-05
 */
public class OpenState extends AbstractBreakerState {

    public OpenState(BreakerManager manager) {
        super(manager);

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeoutHasBeenReached();
                timer.cancel();
            }
        }, manager.timeout);
    }

    @Override
    public void protectedCodeIsAboutToBeCalled() {
        super.protectedCodeIsAboutToBeCalled();
        throw new RuntimeException("服务已熔断，请稍等重试！");
    }

    /**
     * 断开超过设定的阈值，自动切换到半断开状态
     */
    private void timeoutHasBeenReached() {
        manager.moveToHalfOpenState();
    }
}
