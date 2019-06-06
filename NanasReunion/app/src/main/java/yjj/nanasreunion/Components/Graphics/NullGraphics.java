package yjj.nanasreunion.Components.Graphics;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Vector2f;

public class NullGraphics extends Graphics
{
    public NullGraphics()
    {
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Vector2f WorldPosition, float interp) {
        super.Draw(canvas, camera, WorldPosition, interp);
    }
}
