package com.marquedo.marquedo.auth_3;

import android.os.CountDownTimer;

abstract class CustomCountDownTimer {
    private final long mCountDownInterval;
    private CountDownTimer mCountDownTimer;

    CustomCountDownTimer(long millisInFuture, long countDownInterval) {
        mCountDownInterval = countDownInterval;
        mCountDownTimer = create(millisInFuture, countDownInterval);
    }

    void update(long millisInFuture) {
        mCountDownTimer.cancel();
        mCountDownTimer = create(millisInFuture, mCountDownInterval);
        mCountDownTimer.start();
    }

    private CountDownTimer create(long millisInFuture, long countDownInterval) {
        return new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                CustomCountDownTimer.this.onTick(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                CustomCountDownTimer.this.onFinish();
            }
        };
    }

    void cancel() {
        mCountDownTimer.cancel();
    }

    public void start() {
        mCountDownTimer.start();
    }

    protected abstract void onFinish();

    protected abstract void onTick(long millisUntilFinished);
}
