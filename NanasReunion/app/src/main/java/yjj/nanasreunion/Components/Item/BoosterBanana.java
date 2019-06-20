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

public class BoosterBanana extends Item {

    private Graphics original_graphics;
    Vector2f OriginalMaxVelocity;

    protected BoosterBanana()
    {
        super("BoosterBanana", 3.f);
    }

    @Override
    public Item Create() {
        return new BoosterBanana();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {
        original_graphics = pawn.graphics;

        pawn.graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.moving_banana),
                20, 6, 6);
        pawn.graphics.SetScale(0.75f, 0.75f);

        OriginalMaxVelocity = pawn.physics.GetMaxVelocity();
        pawn.physics.SetMaxVelocity(new Vector2f(OriginalMaxVelocity.x * 2.5f, OriginalMaxVelocity.y));

    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.graphics=original_graphics;
        pawn.physics.SetMaxVelocity(OriginalMaxVelocity);
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {
        super.Draw(canvas, camera, pawn);
    }
}
