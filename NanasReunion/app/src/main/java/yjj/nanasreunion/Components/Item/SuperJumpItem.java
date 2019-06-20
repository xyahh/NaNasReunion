package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class SuperJumpItem extends Item {

    float   OriginalJumpForce;
    private SpriteGraphics graphics;
    private Vector2f position;
    private Vector2f pivot;

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
        graphics     = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.supershoes),
                10,1,1);
        graphics.SetScale(0.12f, 0.12f);
        pivot = new Vector2f(0.5f, 0.5f);


    }


    @Override
    public void Stop(Pawn pawn, Camera camera)
    {
        pawn.JumpForce = OriginalJumpForce;
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {
        Vector2f position     = new Vector2f(pawn.position.x+0.3f, 0.8f);

        graphics.Draw(canvas, camera, position, pivot, 0.f);

    }
}
