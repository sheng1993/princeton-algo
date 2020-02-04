package com.slin.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    private int n;
    private int[][] matrix;
    private WeightedQuickUnionUF uf;
    private int openCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n <= 0)
        {
            throw new IllegalArgumentException("N should be > 0");
        }

        this.n = n;
        this.matrix = new int[this.n][this.n];
        this.openCount = 0;
        this.uf = new WeightedQuickUnionUF(this.n*this.n);
        
        for (int i=0; i<this.n; i++)
        {
            for (int j=0; j<this.n; j++)
            {
                this.matrix[i][j] = 0;

                if ((i == 0 || i == this.n-1) && j > 0)
                {
                    this.uf.union(this.to_index(i+1, j), this.to_index(i+1, j+1));
                }
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        this.validate_index(row, col);
        if (this.matrix[row-1][col-1] == 0)
        {
            this.matrix[row-1][col-1] = 1;
            this.openCount++;

            //Union with others
            this.union_step(row-1, col-1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        this.validate_index(row, col);
        return this.matrix[row-1][col-1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        this.validate_index(row, col);
        return this.matrix[row-1][col-1] == 2;
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {   
        return this.openCount;
    }

    // does the system percolate?
    public boolean percolates()
    {
        return this.uf.find(this.to_index(1, 1)) == this.uf.find(to_index(this.n, 1));
    }

    private void validate_index(int row, int col)
    {
        if (row <= 0 || row > this.n || col <= 0 || col > this.n){
            throw new IllegalArgumentException("Indixes should be base 1");
        }
    }

    private int to_index(int row, int col){
        return ((row-1) * this.n) + col-1;
    }

    private void union_step(int row, int col)
    {
        if (row > 0 && this.matrix[row-1][col] == 1)
        {
            this.uf.union(to_index(row+1, col+1), to_index(row, col+1));
        }
        
        if (col > 0 && this.matrix[row][col-1] == 1)
        {
            this.uf.union(to_index(row+1, col+1), to_index(row+1, col));
        }

        if (row < this.n - 1 && this.matrix[row+1][col] == 1)
        {
            this.uf.union(to_index(row+1, col+1), to_index(row+2, col+1));
        }

        if (col < this.n - 1 && this.matrix[row][col+1] == 1)
        {
            this.uf.union(to_index(row+1, col+1), to_index(row+1, col+2));
        }
    }

    // test client (optional)
    public static void main(String[] args)
    {
        Percolation p = new Percolation(10);
        p.percolates();
    }
}