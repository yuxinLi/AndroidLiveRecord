package com.rrtoyewx.mediacodecdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.rrtoyewx.mediacodecdemo.openGLEncode.EncodeOpenGLCameraActivity;

public class MainActivity extends AppCompatActivity {
    Button encodeCameraBtn;
    Button encodeSurfaceBtn;
    Button decodeSurfaceBtn;
    Button encodeOpenGLCameraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

        encodeCameraBtn = (Button) findViewById(R.id.encode_camera_video);
        encodeSurfaceBtn = (Button) findViewById(R.id.encode_surface_video);
        decodeSurfaceBtn = (Button) findViewById(R.id.decode_surface_video);
        encodeOpenGLCameraBtn = (Button) findViewById(R.id.encode_camera_video_not_show);

        encodeCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EncodeCameraActivity.class));
            }
        });
        encodeSurfaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EncoderSurfaceActivity.class));
            }
        });

        decodeSurfaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DecodeMp4Activity.class));
            }
        });

        encodeOpenGLCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EncodeOpenGLCameraActivity.class));
            }
        });

    }
}
