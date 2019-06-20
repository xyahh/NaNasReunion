package yjj.nanasreunion.Objects;

import yjj.nanasreunion.Components.Collision.COLLISION_TYPES;

public abstract class Enemy extends Actor
{
    protected Enemy()
    {
        collision.SetCollisionType(COLLISION_TYPES.ENEMY);
    }

    public abstract Enemy Spawn(Pawn pawn);
}
