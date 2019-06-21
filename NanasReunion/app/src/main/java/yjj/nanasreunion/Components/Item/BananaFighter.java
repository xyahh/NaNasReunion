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
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;

public class BananaFighter extends Item {


    ArrayList<Command> OriginalCommandList;
    Graphics original_graphics;

    protected BananaFighter() {
        super("Banana Fighter", 8.f);
    }


    @Override
    public Item Create() {
        return new BananaFighter();
    }

    @Override
    public void Use(Pawn pawn, Camera camera) {

        original_graphics = pawn.graphics;
        Rect padding = new Rect(0, 0, 0, 33);
        SpriteGraphics sprite = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.fighter_banana), 12, 12, 12,padding);
        pawn.graphics = sprite;
        OriginalCommandList = pawn.collision.GetCollisionCommands(COLLISION_TYPES.ENEMY);
        pawn.collision.SetCollisionCommands(COLLISION_TYPES.ENEMY, new ArrayList<Command>()
        {
            {
                add(new KillCommand());
            }
        });
        pawn.collision.SetCollisionType(COLLISION_TYPES.DEFAULT);
    }


    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.collision.SetCollisionType(COLLISION_TYPES.PLAYER);
        pawn.collision.SetCollisionCommands(COLLISION_TYPES.ENEMY,OriginalCommandList);
        pawn.graphics = original_graphics;
    }

}
