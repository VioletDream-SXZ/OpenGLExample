package com.hy.ysq.render;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class HelloTriangleRenderer implements GLSurfaceView.Renderer {

    public HelloTriangleRenderer()
    {
        mVertices = ByteBuffer.allocateDirect(mVerticesData.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVertices.put(mVerticesData).position(0);
    }

    private int LoadShader(int type, String shaderStr)
    {
        int shader;
        int[] compiled = new int[1];

        shader = GLES30.glCreateShader(type);

        if (shader == 0)
        {
            return 0;
        }

        GLES30.glShaderSource(shader, shaderStr);

        GLES30.glCompileShader(shader);

        GLES30.glGetShaderiv(shader, GLES30.GL_COMPILE_STATUS, compiled, 0);

        if (compiled[0] == 0)
        {
            Log.e ( TAG, "opengles compile error: " + GLES30.glGetShaderInfoLog ( shader ) );
            GLES30.glDeleteShader ( shader );
            return 0;
        }

        return shader;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        int vertexShader;
        int fragmentShader;
        int programObject;
        int[] linked = new int[1];

        vertexShader = LoadShader(GLES30.GL_VERTEX_SHADER, vShaderStr);
        fragmentShader = LoadShader(GLES30.GL_FRAGMENT_SHADER, fShaderStr);

        programObject = GLES30.glCreateProgram();

        if (programObject == 0)
        {
            return;
        }

        GLES30.glAttachShader ( programObject, vertexShader );
        GLES30.glAttachShader ( programObject, fragmentShader );

        // Bind vPosition to attribute 0
        GLES30.glBindAttribLocation ( programObject, 0, "vPosition" );

        // Link the program
        GLES30.glLinkProgram ( programObject );

        // Check the link status
        GLES30.glGetProgramiv ( programObject, GLES30.GL_LINK_STATUS, linked, 0 );

        if ( linked[0] == 0 )
        {
            Log.e ( TAG, "Error linking program:" );
            Log.e ( TAG, GLES30.glGetProgramInfoLog ( programObject ) );
            GLES30.glDeleteProgram ( programObject );
            return;
        }

        // Store the program object
        mProgramObject = programObject;

        GLES30.glClearColor ( 1.0f, 1.0f, 1.0f, 0.0f );
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Set the viewport
        GLES30.glViewport ( 0, 0, mWidth, mHeight );

        // Clear the color buffer
        GLES30.glClear ( GLES30.GL_COLOR_BUFFER_BIT );

        // Use the program object
        GLES30.glUseProgram ( mProgramObject );

        // Load the vertex data
        GLES30.glVertexAttribPointer ( 0, 3, GLES30.GL_FLOAT, false, 0, mVertices );
        GLES30.glEnableVertexAttribArray ( 0 );

        GLES30.glDrawArrays ( GLES30.GL_TRIANGLES, 0, 3 );
    }

    // Member variables
    private int mProgramObject;
    private int mWidth;
    private int mHeight;
    private FloatBuffer mVertices;
    private static String TAG = "HelloTriangleRenderer";

    private final float[] mVerticesData =
            { 0.0f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f };

    private final String vShaderStr =
                    "#version 300 es 			     \n"
                    +   "in vec4 vPosition;          \n"
                    + "void main()                   \n"
                    + "{                             \n"
                    + "   gl_Position = vPosition;   \n"
                    + "}                             \n";

    private  final String fShaderStr =
                    "#version 300 es		 			          	\n"
                    + "precision mediump float;					  	\n"
                    + "out vec4 fragColor;	 			 		  	\n"
                    + "void main()                                  \n"
                    + "{                                            \n"
                    + "  fragColor = vec4 ( 1.0, 0.0, 0.0, 1.0 );	\n"
                    + "}                                            \n";
}
