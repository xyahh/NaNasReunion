package yjj.nanasreunion.Objects;

import android.graphics.Rect;

import java.util.ArrayList;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Command.GameOverCommand;
import yjj.nanasreunion.Components.Collision.ActorCollision;
import yjj.nanasreunion.Components.Collision.COLLISION_TYPES;
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
        Rect r = new Rect(0, 0, 0, 14);
        BirdGraphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.bird_moving),
                10,9,9);
        BirdGraphics.SetScale(0.2f, 0.2f);
    }

    public Bird(Pawn pawn)
    {
        super();
        position     = new Vector2f(pawn.position.x + 3.f, 0.8f);
        graphics     =  BirdGraphics;
        physics      = new Physics();
        physics.SetMaxVelocity(new Vector2f(0.35f, 0.f));
        physics.SetMass(1.f);
        physics.SetGravity(0.f);

        pivot = new Vector2f(0.5f, 0.75f);

        collision = new ActorCollision(COLLISION_TYPES.ENEMY);
        collision.SetDimensions(0.15f, 0.15f);
        collision.SetCollisionCommands(COLLISION_TYPES.PLAYER, new ArrayList<Command>(){
            {
                add(new GameOverCommand());
            }
        });
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
