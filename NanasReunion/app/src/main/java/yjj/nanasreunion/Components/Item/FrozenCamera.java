package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.Vector2f;

public class FrozenCamera extends Item
{
    private boolean MovingFactorX;
    private boolean MovingFactorY;

    public FrozenCamera()
    {
        super("Frozen Camera", 1.f);
    }

    @Override
    public Item Create()
    {
        return new FrozenCamera();
    }

    @Override
    public void Use(Pawn pawn, Camera camera)
    {
        MovingFactorX = camera.GetMovingFactorX();
        MovingFactorY = camera.GetMovingFactorY();

        camera.SetMovingFactor(false, false);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera)
    {
        camera.SetMovingFactor(MovingFactorX, MovingFactorY);
    }
}
