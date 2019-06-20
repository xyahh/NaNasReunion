package yjj.nanasreunion.Objects;

import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Components.Physics.Physics;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class Monkey extends Enemy
{
    public Monkey()
    {

    }

    public Monkey(Pawn pawn)
    {
        super();
        position   = new Vector2f(pawn.position.x + 4.f, 0.f);

        graphics   = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.monkey_moving), 10, 11, 11);
        graphics.SetScale(0.3f, 0.3f);

        physics    = new Physics();
        physics.SetMass(1.f);
    }

    @Override
    public Enemy Spawn(Pawn pawn)
    {
        return new Monkey(pawn);
    }
}
