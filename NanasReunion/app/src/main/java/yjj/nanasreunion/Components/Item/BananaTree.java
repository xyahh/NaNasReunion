package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;
import android.graphics.Rect;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Components.Graphics.Graphics;
import yjj.nanasreunion.Components.Graphics.StaticGraphics;
import yjj.nanasreunion.Components.State.FrozenState;
import yjj.nanasreunion.Objects.Pawn;
import yjj.nanasreunion.R;
import yjj.nanasreunion.Services.ServiceHub;

public class BananaTree extends Item
{
    private Graphics original_graphics;

    protected BananaTree()
    {
        super("Banana Tree", 2.f);
    }

    @Override
    public Item Create() {
        return  new BananaTree();
    }

    @Override
    public void Use(Pawn pawn, Camera camera)
    {
        original_graphics = pawn.graphics;
        Rect padding = new Rect(0, 0, 0, 7);
        StaticGraphics tree_graphics = new StaticGraphics(ServiceHub.Inst().GetBitmap(R.drawable.banana_tree), padding);
        //tree_graphics.SetScale(0.75f, 0.75f);
        pawn.graphics     = tree_graphics;
        pawn.PushState(new FrozenState());
    }

    @Override
    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        return super.UpdateAndValidate(pawn, camera, deltaTime);
    }

    @Override
    public void Stop(Pawn pawn, Camera camera) {
        pawn.graphics = original_graphics;
        pawn.PopState();
    }

    @Override
    public void Draw(Canvas canvas, Camera camera, Pawn pawn) {
        super.Draw(canvas, camera, pawn);
    }
}
