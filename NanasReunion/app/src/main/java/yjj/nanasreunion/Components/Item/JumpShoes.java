package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.State.InAirState;
import yjj.nanasreunion.Components.State.STATE_ID;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.Vector2f;

public class JumpShoes extends Item {
    float   OriginalJumpForce;

    protected JumpShoes()
    {
        super("JumpShoes", 5.f);
    }

    @Override
    public Item Create() {
        return new JumpShoes();
    }

    @Override
    public void Use(Pawn pawn, Camera camera)
    {
        OriginalJumpForce =  pawn.JumpForce;
        pawn.JumpForce = 0.f;
    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime)
    {
        if(pawn.states.top().GetID() == STATE_ID.RUNNING)
        {
            pawn.physics.ApplyForce(new Vector2f(0.f, OriginalJumpForce));
        }
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
            pawn.JumpForce = OriginalJumpForce;
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {

    }
}
