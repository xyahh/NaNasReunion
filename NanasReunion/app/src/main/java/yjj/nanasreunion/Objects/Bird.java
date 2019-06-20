package yjj.nanasreunion.Objects;

import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Components.Physics.Physics;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class Bird extends Enemy
{
    public Bird()
    {

    }

    public Bird(Pawn pawn)
    {
        super();
        position     = new Vector2f(pawn.position.x + 3.f, 1.f);
        graphics     = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.bird_moving),
           10,9,9);
        graphics.SetScale(0.2f, 0.2f);
        physics      = new Physics();
        physics.SetMass(1.f);
    }

    @Override
    public Enemy Spawn(Pawn pawn)
    {
        return new Bird(pawn);
    }
}
