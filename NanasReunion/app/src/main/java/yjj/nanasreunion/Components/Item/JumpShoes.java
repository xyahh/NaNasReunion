package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;


import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Components.State.STATE_ID;
import yjj.nanasreunion.Components.State.State;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;


public class JumpShoes extends Item {
    float OriginalJumpImpulse;
    private SpriteGraphics graphics;
    private Vector2f position;
    private Vector2f pivot;

    protected JumpShoes()
    {
        super("JumpShoes", 2.f);
    }

    @Override
    public Item Create() {
        return new JumpShoes();
    }

    @Override
    public void Use(Pawn pawn, Camera camera)
    {
        OriginalJumpImpulse =  pawn.JumpImpulse;
        pawn.JumpImpulse = 0.f;
        graphics     = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.jumpshoes),
                10,1,1);
        graphics.SetScale(0.12f, 0.12f);
        pivot = new Vector2f(0.5f, 0.5f);
    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime)
    {
        State state = pawn.GetTopState();

        if(state != null && state.GetID() == STATE_ID.RUNNING)
        {
            pawn.physics.ApplyImpulse(new Vector2f(0.f, OriginalJumpImpulse));
        }
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
            pawn.JumpImpulse = OriginalJumpImpulse;
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {
        Vector2f position     = new Vector2f(pawn.position.x+0.3f, 0.8f);

        graphics.Draw(canvas, camera, position, pivot, 0.f);

    }
}
