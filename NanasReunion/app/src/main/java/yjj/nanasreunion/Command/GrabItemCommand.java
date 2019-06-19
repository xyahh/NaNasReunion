package yjj.nanasreunion.Command;

import yjj.nanasreunion.Components.Item.Item;
import yjj.nanasreunion.Components.Item.ItemBox;
import yjj.nanasreunion.Objects.Actor;
import yjj.nanasreunion.Objects.Pawn;

public class GrabItemCommand implements Command
{
    Item m_Item;

    public GrabItemCommand(Item item)
    {
        m_Item = item;
    }

    @Override
    public void Execute(Actor instigator, Actor target)
    {
        if(target instanceof Pawn)
        {
            Pawn p = (Pawn) target;
            p.AddItem(m_Item);
        }
    }
}
