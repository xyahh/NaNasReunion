package yjj.nanasreunion.Objects.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import yjj.nanasreunion.Objects.Components.Camera;
import yjj.nanasreunion.Vector2d;

public class Graphics
{
    protected Matrix            m_DrawMatrix;
    private   Vector2d          m_OriginalDrawHalfSize;
    protected Vector2d          m_DrawHalfSize; //scaled draw half size
    protected float             m_Scale;

    protected Graphics()
    {
        m_DrawMatrix = new Matrix();
        m_Scale = 1.f;
    }

    /*
    width and height must be original and NOT scaled values.
     */
    protected void SetDrawSize(int width, int height)
    {
        m_OriginalDrawHalfSize = new Vector2d(width / 2.f, height / 2.f);
        SetScale(1.f);
    }

    public void SetScale(float scale)
    {
        m_Scale = scale;
        m_DrawHalfSize = Vector2d.Scale(m_OriginalDrawHalfSize, m_Scale);
    }

    protected void Draw(Canvas canvas, Bitmap bitmap, Matrix matrix, Camera camera, Vector2d WorldPosition)
    {
        Vector2d ScreenPosition = camera.ScreenSpace(WorldPosition);
        m_DrawMatrix.preScale(m_Scale, m_Scale);
        m_DrawMatrix.postTranslate(ScreenPosition.x - m_OriginalDrawHalfSize.x, ScreenPosition.y - m_OriginalDrawHalfSize.y);
        canvas.drawBitmap(bitmap, matrix, null);
        m_DrawMatrix.reset();
    }

    public void Update(float DeltaTime)
    {

    }

    public void Draw(Canvas canvas, Camera camera, Vector2d WorldPosition, float interp)
    {
    }
}
