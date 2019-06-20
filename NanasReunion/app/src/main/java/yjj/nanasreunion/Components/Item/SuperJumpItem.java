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
        OriginalJumpForce =  pawn.JumpForce;
        pawn.JumpForce = pawn.JumpForce +100;
    }


    @Override
    public void Stop(Pawn pawn, Camera camera)
    {
        pawn.JumpForce = OriginalJumpForce;
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {

    }
}
