package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import java.util.ArrayList;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Command.SpeedCommandDown;
import yjj.nanasreunion.Command.SpeedCommandUp;
import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Collision.COLLISION_TYPES;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class Day extends Item {


    Vector2f OriginalMaxVelocity;
    private ArrayList<Command> OriginalCommandList;

    protected Day()
    {
        super("Day", 10.f);
    }

    @Override
    public Item Create() {
        return new Day();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {


        pawn.graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.moving_banana),
                20, 6, 6);
        pawn.graphics.SetScale(0.75f, 0.75f);

        OriginalMaxVelocity = pawn.physics.GetMaxVelocity();
        pawn.physics.SetMaxVelocity(new Vector2f(OriginalMaxVelocity.x * 2.5f, OriginalMaxVelocity.y));

        pawn.collision.SetCollisionCommands(COLLISION_TYPES.ITEM, new ArrayList<Command>()
        {
            {
                add(new SpeedCommandUp());
            }
        });
    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.collision.SetCollisionCommands(COLLISION_TYPES.ENEMY, OriginalCommandList);
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn)
    {
        super.Draw(canvas, camera, pawn);
    }
}


