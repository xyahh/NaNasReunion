package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;
import android.graphics.Rect;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.StaticGraphics;
import yjj.nanasreunion.Components.Physics.Physics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class BananaFamily extends Item {
    private Graphics graphics;
    private Physics physics;
    private Vector2f pivot;
    private Vector2f position;
    protected BananaFamily() {
            super("Banana Family", 2.f);
    }

    @Override
    public Item Create() {
        return new BananaFamily();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {
        Rect padding = new Rect(0, 0, 0, 1);
        graphics     = new StaticGraphics(ServiceHub.Inst().GetBitmap(R.drawable.banana_family), padding);
        graphics.SetScale(0.75f, 0.75f);
        pivot = new Vector2f(0.5f, 1.0f);
        position = new Vector2f(pawn.position.x + 2.5f, 0.f);
    }



    @Override
    public void Stop(Pawn pawn, Camera camera)
    {
        ServiceHub.LosingCondition = true;
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn)
    {
        graphics.Draw(canvas, camera, position, pivot, 0.f);
    }
}
