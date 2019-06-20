package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;
import android.graphics.Rect;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Components.Graphics.StaticGraphics;
import yjj.nanasreunion.Components.State.FrozenState;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;

public class Rockstar extends Item
{
    private Graphics original_graphics;

    protected Rockstar() {
        super("Rockstar", 7.f);
    }

    @Override
    public Item Create() {
        return new Rockstar();
    }

    @Override
    public void Use(Pawn pawn, Camera camera)
    {
        original_graphics = pawn.graphics;
        Rect padding = new Rect(0, 0, 0, 33);
        SpriteGraphics rockstar_graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.rockstar), 10,
                4, 4, padding);
        //rockstar_graphics.SetScale(0.75f, 0.75f);
        pawn.graphics     = rockstar_graphics;
        pawn.collision.SetCollisionEnabled(false);
    }
    @Override
    public void Stop(Pawn pawn, Camera camera)
    {
        pawn.graphics = original_graphics;
        pawn.collision.SetCollisionEnabled(true);
    }

}
