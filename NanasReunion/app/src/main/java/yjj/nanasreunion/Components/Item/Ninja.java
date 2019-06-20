package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;
import android.graphics.Rect;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class Ninja extends Item
{

    private Graphics original_graphics;

    protected Ninja()
    {
        super("Ninja", 5.f);
    }

    @Override
    public Item Create() {
        return new Ninja();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {

        original_graphics = pawn.graphics;
        Rect padding = new Rect(0, 0, 0, 32);
        SpriteGraphics ninja_graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.ninja),
                10,8,8, padding);
        ninja_graphics.SetScale(0.75f, 0.75f);
        pawn.graphics     = ninja_graphics;
    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.graphics     = original_graphics;
    }
}
