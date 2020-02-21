package com.hy.ysq.openglexample;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Surface;
import android.widget.LinearLayout;

import com.hy.ysq.surface.HelloTriangleSurface;
import com.hy.ysq.surface.SquareSurface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = new SquareSurface(this);
//        setContentView(view);
        LinearLayout layout = (LinearLayout)findViewById(R.id.line1);
        layout.addView(view);
    }

    private GLSurfaceView view;
}
