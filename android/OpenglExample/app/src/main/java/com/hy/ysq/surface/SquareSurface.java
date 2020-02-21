package com.hy.ysq.surface;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.hy.ysq.render.SquareRender;

public class SquareSurface extends GLSurfaceView
{
    public SquareSurface(Context context) {
        super(context);

        setEGLContextClientVersion(3);

        mRender = new SquareRender();
        setRenderer(mRender);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    private SquareRender mRender;
}
