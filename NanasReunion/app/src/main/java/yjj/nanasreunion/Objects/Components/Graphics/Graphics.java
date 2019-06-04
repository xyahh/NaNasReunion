package yjj.nanasreunion.Objects.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import yjj.nanasreunion.Objects.Components.Camera;
import yjj.nanasreunion.Objects.Components.Collision.Collision;
import yjj.nanasreunion.Vector2d;

public class Graphics
{
    protected Bitmap            m_Bitmap;
    // checks whether the bitmap will be seen in the screen or not (if not, then skip rendering)
    protected Vector2d          m_BitmapHalfSize;
    protected Collision         m_BitmapBox;

    protected Graphics()
    {
        //only called by Child classes (e.g. NullGraphics)
    }

    public Graphics(Bitmap bitmap)
    {
        SetBitmap(bitmap);
    }

    public void SetBitmap(Bitmap bitmap)
    {
        m_Bitmap = bitmap;
        m_BitmapHalfSize    = new Vector2d(m_Bitmap.getWidth()  * 0.5f,
                                            m_Bitmap.getHeight() * 0.5f);
        m_BitmapBox         = new Collision(new Vector2d(), m_BitmapHalfSize);
    }

    public void Draw(Canvas canvas, Camera camera, Vector2d WorldPosition, float interp)
    {
        Vector2d ScreenPosition = camera.ToScreenSpace(WorldPosition);
        m_BitmapBox.SetPosition(ScreenPosition);

        if(camera.AppearsOnScreen(m_BitmapBox))
        {
            canvas.drawBitmap(m_Bitmap,
                    ScreenPosition.x  - m_BitmapHalfSize.x,
                    ScreenPosition.y  - m_BitmapHalfSize.y,
                    null);
        }
    }
}
