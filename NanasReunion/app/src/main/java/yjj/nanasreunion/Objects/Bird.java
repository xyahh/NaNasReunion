package yjj.nanasreunion.Objects;

import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Components.Physics.Physics;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class Bird extends Enemy
{
    static SpriteGraphics BirdGraphics;

    public Bird()
    {

    }

    public static void LoadAssets()
    {
        BirdGraphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.bird_moving),
                10,9,9);
        BirdGraphics.SetScale(0.2f, 0.2f);
    }

    public Bird(Pawn pawn)
    {
        super();
        position     = new Vector2f(pawn.position.x + 3.f, 1.f);
        graphics     =  BirdGraphics;
        physics      = new Physics();
        physics.SetMaxVelocity(new Vector2f(0.35f, 0.f));
        physics.SetMass(1.f);
        physics.SetGravity(0.f);
    }

    @Override
    public void Update(float DeltaTime) {
        super.Update(DeltaTime);
        physics.SetMaxVelocity(new Vector2f(0.35f * ServiceHub.EnemySpeedMultiplier, 0.f));
        physics.ApplyForce(new Vector2f(-20.f, 0.f));
    }

    @Override
    public Enemy Spawn(Pawn pawn)
    {
        return new Bird(pawn);
    }
}
