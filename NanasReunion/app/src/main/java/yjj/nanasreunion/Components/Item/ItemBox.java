package yjj.nanasreunion.Components.Item;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

import yjj.nanasreunion.Command.Command;
import yjj.nanasreunion.Command.GrabItemCommand;
import yjj.nanasreunion.Command.SelfDestructCommand;
import yjj.nanasreunion.Components.Collision.ActorCollision;
import yjj.nanasreunion.Components.Collision.COLLISION_TYPES;
import yjj.nanasreunion.Components.Graphics.SpriteGraphics;
import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;
import yjj.nanasreunion.Vector2f;

public class ItemBox extends Actor
{
    public static final Item ItemList[] =
    {
            new Ad(),
            new BabyBanana(),
            new BananaFamily(),
            new BananaFighter(),
            new BananaTree(),
            new BigBanana(),
            new BoosterBanana(),
            new BrokenClock(),
            new ChocoBanana(),
            new Day(),
            new EditorMode(),
            new FlyingBanana(),
            new FrozenCamera(),
            new Ice(),
            new JumpShoes(),
            new MagicClock(),
            new MirrorWorld(),
            new Night(),
            new Ninja(),
            new Nuclear(),
            new Rockstar(),
            new SlowBanana(),
            new SuperJumpItem(),
            new GravityUp(),
            new GravityDown()
    };

    static SpriteGraphics item_box_graphics = null;
    static Random          randomizer = new Random();
    static float           box_size_x = 0.2f;
    static float           box_size_y = 0.2f;

    public static void LoadAllItemAssets()
    {
        item_box_graphics = new SpriteGraphics(ServiceHub.Inst().GetBitmap(R.drawable.item_box),
                10, 6, 6, new Rect(0, 0, 0, 22));
        item_box_graphics.SetScale(0.15f, 0.15f);

        Ice.LoadAssets();
    }

    public ItemBox()
    {
        super();

        pivot = new Vector2f(0.5f, 0.8f);

        int randIndex = randomizer.nextInt(ItemList.length);
        Item item =  ItemList[randIndex].Create();

        collision   = new ActorCollision(COLLISION_TYPES.ITEM);
        collision.SetDimensions(box_size_x, box_size_y);
        collision.SetCollisionCommands(COLLISION_TYPES.PLAYER, new ArrayList<Command>(){
            {
                add(new GrabItemCommand(item));
                add(new SelfDestructCommand());
            }
        });
        collision.SetCollisionEnabled(true);
        graphics = item_box_graphics;
    }
}
