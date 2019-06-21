package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Command.KillCommand;
import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Collision.COLLISION_TYPES;
import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Components.State.MultiJumpState;
import yjj.nanasreunion.Components.State.State;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;

public class SuperBanana extends Item {


    Graphics original_graphics;
    ArrayList<Command> OriginalCommandList;
    float PrevGravity;
    State prevJumpState;

    protected SuperBanana() {
        super("Super Banana", 10.f);
    }

    @Override
    public Item Create() {
        return new SuperBanana();
    }

    @Override
    public void Use(Pawn pawn, Camera camera)
    {


        original_graphics = pawn.graphics;
        Rect padding = new Rect(0, 0, 0, 25);
        SpriteGraphics sprite = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.super_banana), 12, 4, 4,padding);
        pawn.graphics = sprite;
        OriginalCommandList = pawn.collision.GetCollisionCommands(COLLISION_TYPES.ENEMY);
        pawn.collision.SetCollisionType(COLLISION_TYPES.DEFAULT);
        pawn.collision.SetCollisionCommands(COLLISION_TYPES.ENEMY, new ArrayList<Command>()
        {
            {
                add(new KillCommand());
            }
        });
        PrevGravity = pawn.physics.GetGravity();
        pawn.physics.SetGravity(PrevGravity / 3.f);

        prevJumpState = pawn.JumpingState;
        pawn.JumpingState = new MultiJumpState(0);
    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        if(pawn.position.y > 0.75f)
        {
            pawn.position.y = 0.75f;
        }
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.collision.SetCollisionType(COLLISION_TYPES.PLAYER);
        pawn.physics.SetGravity(PrevGravity);
        pawn.collision.SetCollisionCommands(COLLISION_TYPES.ENEMY,OriginalCommandList);
        pawn.graphics = original_graphics;
        pawn.JumpingState = prevJumpState;
    }

}
