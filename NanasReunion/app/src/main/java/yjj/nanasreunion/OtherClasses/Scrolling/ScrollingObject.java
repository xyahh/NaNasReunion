package yjj.nanasreunion.OtherClasses.Scrolling;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Objects.Actor;
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


        float dpi =  ServiceHub.Inst().GetDPI();

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
        m_DeltaX -= camera.toPixelsF(camera.GetCameraDeltaVelocity().x * m_RelativeSpeed);
        m_DeltaX += m_AbsoluteSpeed.x * deltaTime;
        if(m_DeltaX >= m_Width)
        {
            m_DeltaX = 0;
            m_ReversedFirst = !m_ReversedFirst;
        } else if(m_DeltaX <= 0)
        {
            m_DeltaX = m_Width;
            m_ReversedFirst = !m_ReversedFirst;
        }

    }
}
