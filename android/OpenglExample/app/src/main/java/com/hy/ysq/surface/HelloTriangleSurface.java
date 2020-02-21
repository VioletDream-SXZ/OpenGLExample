package com.hy.ysq.surface;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.hy.ysq.render.HelloTriangleRenderer;

public class HelloTriangleSurface extends GLSurfaceView
{

    public HelloTriangleSurface(Context context) {
        super(context);

        setEGLContextClientVersion(3);

        mRender = new HelloTriangleRenderer();
        setRenderer(mRender);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    private HelloTriangleRenderer mRender;
}
