package yjj.nanasreunion.Components.Item;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Command.GrabItemCommand;
import yjj.nanasreunion.Command.SelfDestructCommand;
import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Collision.ActorCollision;
import yjj.nanasreunion.Components.Collision.COLLISION_TYPES;
import yjj.nanasreunion.Components.Collision.Collision;
import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.RectGraphics;
import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Vector2f;

public class ItemBox extends Actor
{
    public static final Item ItemList[] =
    {
            new FrozenCamera(),
            new SuperJumpItem(),
            new JumpShoes()
    };

    public ItemBox()
    {
        super();

        Random r = new Random();
        int randIndex = r.nextInt(ItemList.length);
        Item item =  ItemList[randIndex].Create();

        Vector2f dimensions = new Vector2f(0.2f, 0.2f);

        collision   = new ActorCollision(COLLISION_TYPES.ITEM);
        collision.SetDimensions(dimensions.x, dimensions.y);
        collision.AddCollisionCommand(COLLISION_TYPES.PLAYER, new ArrayList<Command>(){
            {
                add(new GrabItemCommand(item));
                add(new SelfDestructCommand());
            }
        });
        collision.SetCollisionEnabled(true);

        Paint p = new Paint();
        p.setColor(Color.BLUE);
        RectGraphics rect = new RectGraphics(p);
        rect.SetDimensions(dimensions.x, dimensions.y);
        graphics = rect;
    }
}
