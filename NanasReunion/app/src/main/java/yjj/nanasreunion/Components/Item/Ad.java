package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class Ad extends Item
{
    private Graphics graphics;
    private Vector2f pivot;

    protected Ad()
    {
        super("Ad", 3.f);
    }

    @Override
    public Item Create() {
        return new Ad();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {
        graphics     = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.ad),
                10,1,1);
        graphics.SetScale(0.7f, 0.7f);
        pivot = new Vector2f(0.5f, 0.5f);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {

    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn)
    {
        Vector2f position     = new Vector2f(pawn.position.x+1.0f, 0.4f);
        graphics.Draw(canvas, camera, position, pivot, 0.f);
    }
}
