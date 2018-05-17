package com.rrtoyewx.mediarecoderdemo;

import android.Manifest;
import android.content.Intent;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button recordAudio;
    private Button recordVideo;
    private Button nextButton;
    private SurfaceView surfaceView;
    private MediaRecorder audioRecorder;
    private MediaRecorder videoRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        recordAudio = (Button) findViewById(R.id.record_audio);
        recordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordAudio();
            }
        });
        recordVideo = (Button) findViewById(R.id.record_video);
        recordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordVideo(1);
            }
        });
        nextButton = (Button) findViewById(R.id.next_activity);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecordVideoViewActivity.class));
            }
        });
    }

    private void recordAudio() {
        audioRecorder = new MediaRecorder();
        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        audioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        audioRecorder.setOutputFile(getExternalCacheDir().getAbsolutePath() + "/audioRecord");
        try {
            //3s 自动结束
            audioRecorder.setMaxDuration(3000);
            audioRecorder.prepare();
            audioRecorder.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void recordVideo() {
        videoRecorder = new MediaRecorder();
        videoRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        videoRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        videoRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
        videoRecorder.setOutputFile(getExternalCacheDir().getAbsolutePath() + "/videoRecord01");
        videoRecorder.setVideoSize(640, 480);
        videoRecorder.setVideoFrameRate(1);
        videoRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());

        try {
            videoRecorder.setMaxDuration(8000);
            videoRecorder.prepare();
            videoRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void recordVideo(int type) {
        videoRecorder = new MediaRecorder();
        videoRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        videoRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        CamcorderProfile camcorderProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_1080P);
        videoRecorder.setProfile(camcorderProfile);
        videoRecorder.setOutputFile(getExternalCacheDir().getAbsolutePath() + "/videoRecord_type");

        videoRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
        videoRecorder.setMaxDuration(10000);
        try {
            videoRecorder.prepare();
            videoRecorder.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
