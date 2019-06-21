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

    Vector2f OriginalMaxVelocity;

    protected BoosterBanana()
    {
        super("Booster Banana", 3.f);
    }

    @Override
    public Item Create() {
        return new BoosterBanana();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {

        OriginalMaxVelocity = pawn.physics.GetMaxVelocity();
        pawn.physics.SetMaxVelocity(new Vector2f(OriginalMaxVelocity.x * 2.5f, OriginalMaxVelocity.y));
    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.physics.SetMaxVelocity(OriginalMaxVelocity);
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {
        super.Draw(canvas, camera, pawn);
    }
}
