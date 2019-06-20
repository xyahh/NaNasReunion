package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Objects.Pawn;

public class SuperJumpItem extends Item {

    float   OriginalJumpForce;

    protected SuperJumpItem()
    {
        super("Jump x2", 5.f);
    }

    @Override
    public Item Create()
    {
        return new SuperJumpItem();
    }

    @Override
    public void Use(Pawn pawn, Camera camera)
    {
        OriginalJumpForce =  pawn.JumpImpulse;
        pawn.JumpImpulse = pawn.JumpImpulse + 1.f;
    }


    @Override
    public void Stop(Pawn pawn, Camera camera)
    {
        pawn.JumpImpulse = OriginalJumpForce;
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {

    }
}
