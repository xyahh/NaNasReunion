package yjj.nanasreunion.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;
import yjj.nanasreunion.Vector2i;

public class Graphics
{
    private static float        m_DPI;

    private float               m_ScaleX;
    private float               m_ScaleY;
    private float               m_Angle;

    protected Vector2f m_DrawSize;
    private Vector2f m_OriginalDrawSize;

    protected Graphics()
    {
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
        m_DrawSize = Vector2f.Scale(m_OriginalDrawSize, scaleX, scaleY);
    }

    /*
    width and height must be original and NOT scaled values.
     */
    protected void SetDrawSize(float width, float height)
    {
        m_OriginalDrawSize = new Vector2f(width, height);
        m_DrawSize = Vector2f.Scale(m_OriginalDrawSize, m_ScaleX, m_ScaleY);
    }

    protected Matrix GetTransformMatrix(Camera camera, Vector2f WorldPosition, Vector2f pivot)
    {
        Vector2i ScreenPosition = camera.GetScreenSpace(WorldPosition);
        Vector2f PivotOffset = Vector2f.Scale(m_DrawSize, pivot.x, pivot.y);

        Matrix transform = new Matrix(); //init with Identity
        transform.setTranslate(-PivotOffset.x, -PivotOffset.y);

        //SRT Transformation (Scale -> Rotate -> Translate)
        transform.preScale(m_ScaleX, m_ScaleY);
        transform.postRotate(m_Angle);
        transform.postTranslate(ScreenPosition.x, ScreenPosition.y);

        return transform;
    }

    protected void Draw(Canvas canvas, Bitmap bitmap, Camera camera, Vector2f WorldPosition, Vector2f pivot)
    {
        Matrix transform = GetTransformMatrix(camera, WorldPosition, pivot);
        canvas.drawBitmap(bitmap, transform, null);
    }

    public void Update(float DeltaTime)
    {

    }

    public void Draw(Canvas canvas, Camera camera, Vector2f WorldPosition, Vector2f pivot, float interp)
    {
    }
}
