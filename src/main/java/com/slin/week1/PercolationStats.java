package com.slin.week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats 
{
    private int n;
    private int trials;
    private double[] thresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <= 0)
        {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.trials = trials;
        this.thresholds = new double[this.trials];
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(this.thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(this.thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return (mean() - 1.96 * Math.sqrt(stddev() / this.trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return (mean() + 1.96 * Math.sqrt(stddev() / this.trials));
    }

    public void runSimulations()
    {
        for (int i=0; i<this.trials; i++)
        {
            //System.out.println("Running simulation " + String.valueOf(i + 1) + " of " + String.valueOf(this.trials));
            simulate(i);
        }
    }

    private void simulate(int t)
    {
        Percolation p = new Percolation(this.n);
        while (!p.percolates())
        {
            int row = StdRandom.uniform(1, this.n + 1);
            int col = StdRandom.uniform(1, this.n + 1);
            p.open(row, col);
        }
        thresholds[t] = ((double)p.numberOfOpenSites()) / (this.n * this.n);
    }

   // test client (see below)
   public static void main(String[] args)
   {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);
        stats.runSimulations();
        System.out.println("mean                    = " + String.valueOf(stats.mean()));
        System.out.println("stddev                  = " + String.valueOf(stats.stddev()));
        System.out.println("95% confidence interval = [" + String.valueOf(stats.confidenceLo()) + ", " + String.valueOf(stats.confidenceHi()) + "]");
   }
}