package com.starcloud.todayinformation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private FullScreenVideoView videoView;
    private CustomCountDownTimer customCountDownTimer;
    private TextView tv_splash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv_splash = (TextView)findViewById(R.id.tv_splash);
        tv_splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });

        videoView = (FullScreenVideoView) findViewById(R.id.vv_play);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        customCountDownTimer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                tv_splash.setText(time + "秒");
            }

            @Override
            public void onFinish() {
                tv_splash.setText("跳过");
            }
        });
        customCountDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        customCountDownTimer.cancel();
    }
}
