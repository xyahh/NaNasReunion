package yjj.nanasreunion.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.Vector;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class Graphics
{
    private   Matrix            m_DrawMatrix;
    private Vector2f            m_OriginalDrawHalfSize;

    private static float        m_DPI;

    private float               m_ScaleX;
    private float               m_ScaleY;
    private float               m_Angle;

    protected Vector2f m_DrawHalfSize; //scaled draw half size

    protected Graphics()
    {
        m_DrawMatrix = new Matrix();
        m_ScaleX = 1.f;
        m_ScaleY = 1.f;
        m_Angle = 0.f;
        m_DPI = ServiceHub.Inst().GetDPI();
    }

    public void Rotate(float rotation)
    {
        m_Angle += rotation;
    }

    public void SetRotation(float rotation)
    {
        m_Angle = rotation;
    }

    public void SetScale(float scaleX, float scaleY)
    {
        m_ScaleX = scaleX;
        m_ScaleY = scaleY;
        m_DrawHalfSize = Vector2f.Scale(m_OriginalDrawHalfSize, scaleX, scaleY);
    }

    /*
    width and height must be original and NOT scaled values.
     */
    protected void SetDrawSize(int width, int height)
    {
        m_OriginalDrawHalfSize = new Vector2f(width / 2.f, height / 2.f);
        SetScale(1.f, 1.f);
    }

    protected void Draw(Canvas canvas, Bitmap bitmap, Camera camera, Vector2f WorldPosition)
    {
        Vector2i ScreenPosition;
        if(camera == null) ScreenPosition = Vector2f.Scale(WorldPosition, m_DPI).toInt();
        else ScreenPosition = camera.ScreenSpace(WorldPosition);

        m_DrawMatrix.reset();
        m_DrawMatrix.setTranslate(-m_DrawHalfSize.x, -m_DrawHalfSize.y);
        m_DrawMatrix.postRotate(m_Angle);
        m_DrawMatrix.preScale(m_ScaleX, m_ScaleY);
        m_DrawMatrix.postTranslate(ScreenPosition.x, ScreenPosition.y);
        canvas.drawBitmap(bitmap, m_DrawMatrix, null);
    }

    public void Update(float DeltaTime)
    {

    }

    public void Draw(Canvas canvas, Camera camera, Vector2f WorldPosition, float interp)
    {
    }
}
