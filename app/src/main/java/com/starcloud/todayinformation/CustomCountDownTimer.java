package com.starcloud.todayinformation;


import android.os.Handler;

/**
 * Created by asus on 2019/8/4 21:26.
 */
public class CustomCountDownTimer implements Runnable{

    private Handler hander;
    private int time;
    private int countDownTime;
    private ICountDownHandler countDownHandler;
    private boolean isRun;

    public CustomCountDownTimer(int time, ICountDownHandler countDownHandler) {
        this.time = time;
        this.countDownTime = time;
        this.countDownHandler = countDownHandler;
        hander = new Handler();
    }

    @Override
    public void run() {
        if(isRun) {
            if (countDownHandler != null) {
                countDownHandler.onTicker(countDownTime);
            }
            if (countDownTime == 0) {
                if (countDownHandler != null) {
                    countDownHandler.onFinish();
                }
            } else {
                countDownTime = time--;
                hander.postDelayed(this, 1000);
            }
        }
    }

    public void start() {
        isRun = true;
        hander.post(this);
    }

    public void cancel() {
        isRun = false;
        hander.removeCallbacks(this);
    }

    //观察者回调模式
    public interface ICountDownHandler {
        void onTicker(int time);
        void onFinish();
    }
}
