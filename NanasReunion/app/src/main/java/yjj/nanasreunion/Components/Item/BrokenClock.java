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
import yjj.nanasreunion.Vector2f;

public class BrokenClock extends Item {

    private Graphics original_graphics;
    private Graphics graphics;
    Vector2f OriginalMaxVelocity;
    float   OriginalMass;
    private Vector2f pivot;
    private Vector2f position;

    protected BrokenClock()
    {
        super("BrokenClock", 5.f);
    }

    @Override
    public Item Create() {
        return new BrokenClock();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {
        original_graphics = pawn.graphics;

        pawn.graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.moving_banana),
                5, 6, 6);
        pawn.graphics.SetScale(0.75f, 0.75f);
        graphics     = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.brokenclock),
                10,4,4);
        graphics.SetScale(0.3f, 0.3f);
        pivot = new Vector2f(0.5f, 0.5f);

        OriginalMass = pawn.physics.GetMass();
        pawn.physics.SetMass(OriginalMass * 1.25f);

        OriginalMaxVelocity = pawn.physics.GetMaxVelocity();
        pawn.physics.SetMaxVelocity(new Vector2f(OriginalMaxVelocity.x * 0.5f, OriginalMaxVelocity.y));

    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.graphics=original_graphics;
        pawn.physics.SetMass(OriginalMass);
        pawn.physics.SetMaxVelocity(OriginalMaxVelocity);
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {
        Vector2f position     = new Vector2f(pawn.position.x+0.3f, 0.8f);

        graphics.Draw(canvas, camera, position, pivot, 0.f);
    }
}
