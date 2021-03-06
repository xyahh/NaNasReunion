package yjj.nanasreunion.Components.Item;

import android.graphics.Canvas;

import yjj.nanasreunion.Components.Camera;
import yjj.nanasreunion.Objects.Pawn;

public abstract class Item {
    public String name;
    private float duration;

    protected Item(String item_name, float item_duration) {
        name = item_name;
        duration = item_duration;
    }

    public String GetName()
    {
        return name;
    }

    public abstract Item Create();

    public abstract void Use(Pawn pawn, Camera camera);

    public boolean UpdateAndValidate(Pawn pawn, Camera camera, float deltaTime) {
        duration -= deltaTime;
        return duration <= 0.f;
    }

    public abstract void Stop(Pawn pawn, Camera camera);

    public void Draw(Canvas canvas, Camera camera, Pawn pawn)
    {

    }

}
