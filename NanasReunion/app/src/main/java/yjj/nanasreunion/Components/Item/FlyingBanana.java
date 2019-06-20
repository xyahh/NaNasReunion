package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;
import android.graphics.Rect;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Components.Graphics.StaticGraphics;
import yjj.nanasreunion.Components.State.FrozenState;
import yjj.nanasreunion.Components.State.MultiJumpState;
import yjj.nanasreunion.Components.State.State;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class FlyingBanana extends Item
{
    private Graphics original_graphics;
    private State OriginalJumpingState;

    protected FlyingBanana()
    {
        super("Flying Banana", 7.f);
    }

    @Override
    public Item Create() {
        return new FlyingBanana();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {
        original_graphics = pawn.graphics;

        SpriteGraphics fly_graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.fly_banana),
                10, 6, 6);
        fly_graphics.SetScale(0.75f, 0.75f);

        pawn.graphics     = fly_graphics;
        OriginalJumpingState = pawn.JumpingState;
        pawn.JumpingState = new MultiJumpState(0);

    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {

        if(pawn.position.y > 0.75f)
        {
            pawn.position.y = 0.75f;
        }
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.JumpingState = OriginalJumpingState;
        pawn.graphics = original_graphics;

    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {
        super.Draw(canvas, camera, pawn);
    }
}
