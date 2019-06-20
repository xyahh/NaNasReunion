package yjj.nanasreunion.OtherClasses.Scrolling;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class ScrollingObject
{
    Bitmap m_Bitmap;
    Bitmap m_ReversedBitmap;

    int m_Width;
    int m_Height;
    boolean m_ReversedFirst;

    Vector2f m_AbsoluteSpeed;

    float m_DeltaX;
    float m_RelativeSpeed;

    float m_StartY;
    float m_EndY;

    ScrollingObject(Bitmap bitmap, float WorldY, Vector2f AbsoluteSpeed, float TargetRelativeSpeed, Vector2i ScreenSize)
    {
        m_AbsoluteSpeed = AbsoluteSpeed;
        m_RelativeSpeed = TargetRelativeSpeed;
        m_ReversedFirst = false;
        m_DeltaX = 0.f;

        // Save the m_Width and m_Height for later use
        m_Width = bitmap.getWidth();
        m_Height = bitmap.getHeight();

        float ScaleRatio = (float)(ScreenSize.x) / (float)(m_Width);
        int Height =  Math.round(m_Height * ScaleRatio);
        int Width = ScreenSize.x;

        //Position the background vertically
        m_StartY = WorldY;
        m_EndY = m_StartY + Height;

        // Create the bitmap
        m_Bitmap = Bitmap.createScaledBitmap(bitmap, Width, Height, true);

        m_Width = m_Bitmap.getWidth();
        m_Height = m_Bitmap.getHeight();

        //Create a mirror image of the background (horizontal flip)
        Matrix m = new Matrix();
        m.setScale(-1, 1);
        m_ReversedBitmap = Bitmap.createBitmap(m_Bitmap, 0, 0, m_Width, m_Height, m, true);
    }

    public void UpdateBackground(Camera camera, float deltaTime)
    {

        float delta = (m_AbsoluteSpeed.x * deltaTime) - (camera.GetCamDeltaDistance().x * m_RelativeSpeed);
        if(!ServiceHub.RightwardGameplay)
            delta*=-1.f;
        m_DeltaX += delta;

        if(m_DeltaX > camera.ViewportRight)
        {
            m_DeltaX = camera.ViewportLeft;
            m_ReversedFirst = !m_ReversedFirst;
        } else if(m_DeltaX < camera.ViewportLeft)
        {
            m_DeltaX = camera.ViewportRight;
            m_ReversedFirst = !m_ReversedFirst;
        }

    }

    public void Draw(Canvas canvas, Paint paint, Camera camera)
    {
        // define what portion of images to capture and
        // what coordinates of screen to draw them at
        Vector2i v = camera.GetScreenSpace(new Vector2f(0.f, m_StartY));
        int deltaX = (int) camera.DeltaXToPixels(m_DeltaX);
        int startY =  v.y;
        int endY = startY + m_Height;

        // For the regular bitmap
        Rect fromRect1 = new Rect(0, 0, m_Width - deltaX, m_Height);
        Rect toRect1 = new Rect(deltaX, startY, m_Width, endY);

        // For the reversed background
        Rect fromRect2 = new Rect(m_Width - deltaX, 0, m_Width, m_Height);
        Rect toRect2 = new Rect(0, startY, deltaX, endY);

        //draw the two background bitmaps
        if (!m_ReversedFirst) {
            canvas.drawBitmap(m_Bitmap, fromRect1, toRect1, paint);
            canvas.drawBitmap(m_ReversedBitmap, fromRect2, toRect2, paint);
        } else {
            canvas.drawBitmap(m_Bitmap, fromRect2, toRect2, paint);
            canvas.drawBitmap(m_ReversedBitmap, fromRect1, toRect1, paint);
        }
    }
}
