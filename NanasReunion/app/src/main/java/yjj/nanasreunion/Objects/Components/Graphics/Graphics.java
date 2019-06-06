package yjj.nanasreunion.Objects.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

import java.util.concurrent.locks.ReentrantLock;

import yjj.nanasreunion.Objects.Components.Camera;
import yjj.nanasreunion.Objects.Components.Collision.Collision;
import yjj.nanasreunion.Vector2d;

public class Graphics
{
    protected Bitmap                m_Bitmap;
    protected Rect                  m_SourceRect;
    // checks whether the bitmap will be seen in the screen or not (if not, then skip rendering)
    protected Vector2d m_OutHalfSize;
    protected Collision m_Boundaries;

    protected Graphics()
    {
        //only called by Child classes (e.g. NullGraphics)
    }

    /* internal use*/
    protected Graphics(Bitmap bitmap)
    {
        m_Bitmap = bitmap;
        m_Bitmap.setDensity(Bitmap.DENSITY_NONE);
    }

    public Graphics(Bitmap bitmap, float scale)
    {
        this(bitmap);
        Scale(scale);
    }

    /*for external use */
    public void Scale(float scale)
    {
        ScaleBitmap(scale);
        UpdateOutRects();
    }

    public Bitmap GetResizedBitmap(Bitmap bm, int newWidth, int newHeight)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();

        if(width == newWidth && height == newHeight)
            return bm;
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    protected void ScaleBitmap(float scale)
    {
        if(m_Bitmap == null) return;
        int Width =  (int)((float)(m_Bitmap.getWidth()) * scale);
        int Height = (int)((float)(m_Bitmap.getHeight()) * scale);
        m_Bitmap = GetResizedBitmap(m_Bitmap, Width, Height);
    }

    public void UpdateOutRects()
    {
        m_OutHalfSize = new Vector2d(m_Bitmap.getWidth()  * 0.5f, m_Bitmap.getHeight() * 0.5f);
        m_Boundaries = new Collision(new Vector2d(), m_OutHalfSize);
    }

    public void Update(float DeltaTime)
    {

    }

    public void Draw(Canvas canvas, Camera camera, Vector2d WorldPosition, float interp)
    {
        Vector2d ScreenPosition = camera.ScreenSpace(WorldPosition);
        m_Boundaries.SetPosition(ScreenPosition);
        if(camera.AppearsOnScreen(m_Boundaries))
        {
            int X1 = (int)(ScreenPosition.x - m_OutHalfSize.x);
            int Y1 = (int)(ScreenPosition.y - m_OutHalfSize.y);

            int X2 = (int)(ScreenPosition.x + m_OutHalfSize.x);
            int Y2 = (int)(ScreenPosition.y + m_OutHalfSize.y);

            Rect dest = new Rect(X1, Y1, X2, Y2);
            canvas.drawBitmap(m_Bitmap, m_SourceRect, dest, null);
        }
    }
}
