package com.slin.week4;

import edu.princeton.cs.algs4.Queue;

public class BST<Key extends Comparable<Key>, Value>
{
    private Node root;

    public void put(Key key, Value val)
    {
        root = put(root, key, val);
    }

    public Value get(Key key)
    {
        Node x = this.root;
        while(x != null)
        {
            int comp = key.compareTo(x.key);
            if      (comp < 0)  x = x.left;
            else if (comp > 0)  x = x.right;
            else                return x.value;
        }
        return null;
    }

    public void delete(Key key)
    {
        root = delete(root, key);
    }

    public void deleteMin()
    {
        root = deleteMin(root);
    }

    public int size()
    {
        return size(root);
    }

    public Iterable<Key> iterator()
    {
        Queue<Key> q = new Queue<>();
        inorder(root, q);
        return q;
    }

    public int rank(Key key)
    {
        return rank(key, root);
    }
    

    private Node delete(Node x, Key key)
    {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else
        {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node min(Node x)
    {
        Node min = x;
        while (min.left != null)
        {
            min = min.left;
        }
        return min;
    }

    private int rank(Key key, Node x)
    {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if     (cmp < 0)    return rank(key, x.left);
        else if(cmp > 0)    return 1 + size(x.left) + rank(key, x.right);
        else                return size(x.left);
    }

    private Node deleteMin(Node x)
    {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private void inorder(Node x, Queue<Key> q)
    {
        if (x == null) return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }

    private Node put(Node x, Key key, Value val)
    {
        if (x == null) return new Node(key, val);
        int comp = key.compareTo(x.key);
        if (comp < 0)
            x.left = put(x.left, key, val);
        else if (comp > 0)
            x.right = put(x.right, key, val);
        else
            x.value = val;
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private int size(Node x)
    {
        if (x == null) return 0;
        return x.count;
    }

    private class Node
    {
        private Key key;
        private Value value;
        private Node left, right;
        private int count;

        private Node(Key k, Value v)
        {
            this.key = k;
            this.value = v;
        }
    }
}