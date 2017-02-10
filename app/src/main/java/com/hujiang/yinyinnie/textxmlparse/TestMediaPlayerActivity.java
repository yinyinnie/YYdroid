package com.hujiang.yinyinnie.textxmlparse;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by nieyinyin on 09/01/2017.
 */

public class TestMediaPlayerActivity extends Activity{

    private MediaPlayer mPlayer;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private VideoView mVideoView;
    private SeekBar mSeekBar;
    private Button mBtnController;
    private TextView mTvCurrentTime;
    private TextView mTvDuration;

    private final String m3u8StrUri = "http://172.16.12.65:8000/prog_index.m3u8";
    private final String appleUri = "http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8";

    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mediaplayer);

        mContext = this;


        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mBtnController = (Button) findViewById(R.id.control_play);
        mTvCurrentTime = (TextView) findViewById(R.id.tv_current_time);
        mTvDuration = (TextView) findViewById(R.id.tv_duration);


//        mVideoView = (VideoView) findViewById(R.id.videoView);
//        mVideoView.setVideoURI(Uri.parse(m3u8StrUri));
//        mVideoView.setMediaController(new android.widget.MediaController(mContext));
//        mVideoView.start();


        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                {
                    mPlayer.start();


                    int duration = mPlayer.getDuration();
                    int currentPosition = mPlayer.getCurrentPosition();

                    mSeekBar.setMax(duration);
                    mSeekBar.setSecondaryProgress(duration);
                    mSeekBar.setProgress(currentPosition);
                    mTvCurrentTime.setText(String.valueOf(currentPosition / 1000 / 60  + ":" + (currentPosition / 1000) % 60));
                    mTvDuration.setText(String.valueOf(String.valueOf(duration / 1000 / 60  + ":" + (duration / 1000) % 60)));

                    startTimer();
                }
            }
        });


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPlayer.seekTo(seekBar.getProgress());
            }
        });

        mBtnController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPlayer.isPlaying()) {
                    mPlayer.pause();
//                    mBtnController.setBackgroundResource(R.drawable.hjplayer_btn_play_selector);
                }else {
                    mPlayer.start();
//                    mBtnController.setBackgroundResource(R.drawable.hjplayer_btn_pause_selector);
                }
            }
        });


        //------------
//        String uri = "/sdcard/index.hjmp3";
        FileInputStream fileInputStream = null;
        if (mPlayer != null) {
            try {
//                File f = new File(uri);
//                fileInputStream = new FileInputStream(f);
//                mPlayer.setDataSource(fileInputStream.getFD(), 2012L, f.length());
//                mPlayer.setDataSource(m3u8StrUri);
                mPlayer.setDataSource("/sdcard/test1.mp4");
                mPlayer.prepareAsync();
            } catch (Exception e) {
                Log.e("yinyin", e.toString(), e);
            } finally {
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                        fileInputStream = null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(mPlayer != null) mPlayer.setDisplay(mSurfaceHolder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if(mPlayer != null) mPlayer.setDisplay(null);
            }
        });

    }

    Handler mTimeHandler = new Handler();
    Runnable mTicker = null;
    private void startTimer() {
        if (mTicker == null) {
            mTicker = new Runnable() {
                @Override
                public void run() {

                    int currentPosition = mPlayer.getCurrentPosition();

                    mSeekBar.setProgress(mPlayer.getCurrentPosition());
                    mTvCurrentTime.setText(String.valueOf(currentPosition / 1000 / 60  + ":" + (currentPosition / 1000) % 60));

                    long now = SystemClock.uptimeMillis();
                    long next = now + (1000 - now % 1000);
                    mTimeHandler.postAtTime(mTicker, next);

                }
            };
            new Thread(mTicker).start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify activity_a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if(mPlayer != null) mPlayer.release();
        super.onDestroy();
    }

}
