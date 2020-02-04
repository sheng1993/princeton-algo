package com.slin.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> 
{
    private Node head;
    private Node tail;
    private int length;

    public class DequeIterator implements Iterator<Item>
    {
        Node current;

        public DequeIterator()
        {
            this.current = Deque.this.head;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public Item next() {
            if (!this.hasNext()) throw new NoSuchElementException();
            
            Node result = this.current;
            this.current = this.current.next;
            return result.value;
        }
        
    }

    private class Node
    {
        Item value;
        Node next;
        Node prev;

        public Node(Item value)
        {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    // construct an empty deque
    public Deque()
    {
        this.length = 0;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return this.length;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null)   throw new IllegalArgumentException();

        Node node = new Node(item);
        node.next = this.head;

        if (this.head == null)
        {
            this.tail = node;
        }
        else
        {
            this.head.prev = node;
        }

        this.head = node;
        this.length++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null) throw new IllegalArgumentException();

        Node node = new Node(item);
        node.prev = this.tail;

        if (this.tail == null)
        {
            this.head = node;
        }
        else
        {
            this.tail.next = node;
        }

        this.tail = node;
        this.length++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (this.isEmpty()) throw new NoSuchElementException();

        Node result = this.head;
        this.head = this.head.next;
        if (this.head != null)
        {
            this.head.prev = null;
        }
        else
        {
            this.tail = null;
        }
        this.length--;
        return result.value;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (this.isEmpty()) throw new NoSuchElementException();

        Node result = this.tail;
        this.tail = this.tail.prev;
        if (this.tail != null)
        {
            this.tail.next = null;
        }
        else
        {
            this.head = null;
        }
        this.length--;
        return result.value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(0);
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addFirst(-1);
        deque.addLast(-1);
        deque.removeFirst();
        deque.removeLast();

        for (Integer i : deque) {
            System.out.print(i);
            System.out.print(" ");
        }

        while (!deque.isEmpty())
        {
            deque.removeFirst();
        }

        for (Integer i : deque) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
}