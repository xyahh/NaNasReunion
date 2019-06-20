package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;
import android.graphics.Rect;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Components.Physics.Physics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Services.Timer;
import yjj.nanasreunion.Vector2f;

public class MagicClock extends Item {

    private Graphics original_graphics;
    private Graphics graphics;
    private Vector2f pivot;

    protected MagicClock()
    {
        super("MagicClock", 5.f);
    }

    @Override
    public Item Create() {
        return new MagicClock();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {
        original_graphics = pawn.graphics;
        graphics     = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.magicclock),
                10,4,4);
        graphics.SetScale(0.1f, 0.1f);
        pivot = new Vector2f(0.5f, 0.5f);

        Timer.SetTimeDilation(2.f);

    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.graphics = original_graphics;
        Timer.SetTimeDilation(1.f);
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {
        Vector2f position     = new Vector2f(pawn.position.x+0.3f, 0.8f);

        graphics.Draw(canvas, camera, position, pivot, 0.f);
    }
}
