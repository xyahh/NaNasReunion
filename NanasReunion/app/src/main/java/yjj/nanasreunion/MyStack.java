package yjj.nanasreunion;

import java.util.Deque;
import java.util.ArrayDeque;

public class MyStack<T>
{
    private final Deque<T> stack = new ArrayDeque<T>();

    public void push(T object)
    {
        stack.addFirst(object);
    }

    public T pop()
    {
        return stack.removeFirst();
    }

    public T top()
    {
        return stack.getFirst();
    }

    public int size()
    {
        return stack.size();
    }
}
