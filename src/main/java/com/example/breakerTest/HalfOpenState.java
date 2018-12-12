package com.example.breakerTest;

/**
 * @author: qiudong
 * @description: 熔断器半断开状态
 * 切换到半断开状态时，将连续成功调用计数重置为0，当执行成功的时候，自增该字段，
 * 当达到连读调用成功次数的阈值时，切换到闭合状态。
 * 如果调用失败，立即切换到断开模式。
 * @date: Created in 14:16 2018-11-05
 */
public class HalfOpenState extends AbstractBreakerState {

    public HalfOpenState(BreakerManager manager) {
        super(manager);

        //重置连续成功计数
        manager.resetConsecutiveSuccessCount();
    }

    @Override
    public void ActUponException() {
        super.ActUponException();

        //只要有失败，立即切换到断开模式
        manager.moveToOpenState();
    }

    @Override
    public void protectedCodeHasBeenCalled() {
        super.protectedCodeHasBeenCalled();

        //如果连续成功次数达到阈值，切换到闭合状态
        if (manager.consecutiveSuccessThresholdReached()) {
            manager.moveToClosedState();
        }
    }
}
