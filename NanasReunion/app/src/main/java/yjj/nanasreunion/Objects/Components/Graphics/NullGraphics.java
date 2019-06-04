package yjj.nanasreunion.Objects.Components.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import yjj.nanasreunion.Objects.Components.Camera;
import yjj.nanasreunion.Vector2d;

public class NullGraphics extends Graphics
{
    public NullGraphics()
    {
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Vector2d WorldPosition, float interp) {
        super.Draw(canvas, camera, WorldPosition, interp);
    }
}
