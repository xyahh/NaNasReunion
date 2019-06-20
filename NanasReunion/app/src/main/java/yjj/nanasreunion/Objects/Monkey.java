package yjj.nanasreunion.Objects;

import java.util.ArrayList;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Command.GameOverCommand;
import yjj.nanasreunion.Components.Collision.ActorCollision;
import yjj.nanasreunion.Components.Collision.COLLISION_TYPES;
import yjj.nanasreunion.Components.Collision.Collision;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Components.Graphics.StaticGraphics;
import yjj.nanasreunion.Components.Physics.Physics;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class Monkey extends Enemy
{
    private static SpriteGraphics MonkeyGraphics;

    public Monkey()
    {

    }

    public static void LoadAssets()
    {
        MonkeyGraphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.monkey_moving), 10, 11, 11);
        MonkeyGraphics.SetScale(0.3f, 0.3f);
    }


    public Monkey(Pawn pawn)
    {
        super();
        position   = new Vector2f(pawn.position.x + 4.f, 0.f);

        pivot = new Vector2f(0.5f, 1.f);

        graphics   = MonkeyGraphics;

        physics    = new Physics();
        physics.SetMass(1.f);

        collision = new ActorCollision(COLLISION_TYPES.ENEMY);
        collision.SetDimensions(0.1f, 0.35f);

        collision.SetCollisionCommands(COLLISION_TYPES.PLAYER, new ArrayList<Command>(){
            {
                add(new GameOverCommand());
            }
        });
    }

    @Override
    public void Update(float DeltaTime)
    {
        super.Update(DeltaTime);
        physics.SetMaxVelocity(new Vector2f(0.25f * ServiceHub.EnemySpeedMultiplier, 0.f));
        physics.ApplyForce(new Vector2f(-5.f, 0.f));
    }

    @Override
    public Enemy Spawn(Pawn pawn)
    {
        return new Monkey(pawn);
    }
}
