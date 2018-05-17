package com.rrtoyewx.opengldemo.esl10;

import android.Manifest;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.rrtoyewx.opengldemo.R;

public class MainActivity extends AppCompatActivity {
    GLSurfaceView glSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

        glSurfaceView = (GLSurfaceView) findViewById(R.id.gl_surface_view);
        glSurfaceView.setRenderer(new PointSurfaceRenderer());
    }

    @Override
    protected void onResume() {
        glSurfaceView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        glSurfaceView.onPause();
        super.onPause();
    }
}
