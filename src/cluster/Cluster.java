package cluster;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/*
 * The MIT License
 *
 * Copyright 2015 Sean Vogel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 *
 * @author      Sean Vogel
 *  zid         z1729629
 *  course      CSCI-490-D
 *  assignment  1
 */
public class Cluster {

    /**
     * kMeans
     * 
     * Assign data points in data to 1 of k cluster groups
     * 
     * @param k         number of clusters
     * @param d         dimensionality of data points
     * @param centers   the initial center points
     * @param data      2D array of data points
     * @return          the selected RetValue which includes final 
     *                  centers and cluster assignments
     * @throws          java.lang.Exception
     */
    public static RetValue kMeans(int k, Distance d, Point[] centers, double[][] data) throws Exception {
        if(centers.length != k) {
            throw new Exception("Number of centers must match k value");
        }
                
        int[] clusters = new int[data.length];
        
        while(true) {            
            int[] c2 = new int[data.length];//array to check if there is a change in assignments
            
            for(int i = 0; i < data.length; ++i) {
                double min = d.get(centers[0], new Point(data[i]));
                int c = 0;//assigned cluster

                for(int j = 0; j < centers.length; ++j) {
                    double m = d.get(centers[j], new Point(data[i]));
                    if(m < min) { min = m; c = j; }
                }
                c2[i] = c;//assign data[i] to cluster c
            }
            centers = calcNewCenters(k, centers[0].d, c2, data);
            if(Arrays.equals(c2, clusters)) { break; }
            clusters = Arrays.copyOf(c2, c2.length);
        }        
        
        return new RetValue(centers, clusters);
    }
    
    /**
     * kMeans
     * 
     * Assign data points in data to 1 of k cluster groups
     * 
     * @param k     number of clusters
     * @param d     dimensionality of data points
     * @param runs  number of runs to perform
     * @param data  2D array of data points
     * @return      the selected RetValue which includes final 
     *              centers and cluster assignments
     * @throws      java.lang.Exception
     */
    public static RetValue kMeans(int k, Distance d, int runs, double[][] data) throws Exception {
        Random r = new Random();        
        ArrayList<RetValue> retValues = new ArrayList<>();
        
        while(runs-- > 0) {
            
            HashSet<Integer> centerIndices = new HashSet<>();
            while(centerIndices.size() <= k) {
                centerIndices.add(r.nextInt(data.length));
            }
            
            Iterator<Integer> it = centerIndices.iterator();
            Point[] centers = new Point[k];
            for(int i = 0; i < k; ++i) { 
                centers[i] = new Point(data[it.next()]);
            }
            
            retValues.add(kMeans(k, d, centers, data));
        }
        
        return leastSumOfSquares(retValues, data);
    }

    /**
     * calcNewCenters
     * 
     * recalculates cluster centers
     * 
     * @param k         the number of clusters
     * @param d         the dimensionality of data points and centers
     * @param clusters  array of data point cluster assignments
     * @param data      2D array of data points
     * @return          the newly calculated centers
     */
    private static Point[] calcNewCenters(int k, int d, int[] clusters, double[][] data) {        
        Point[] newcenters = new Point[k];
        for(int i = 0; i < k; ++i) { newcenters[i] = new Point(d); }//initialize array
               
        int[] sums = new int[k];//sum of points in each cluster
        for(int i = 0; i < clusters.length; ++i) {
            newcenters[clusters[i]].add(new Point(data[i]));
            ++sums[clusters[i]];
        }
        
        //divide centers by sum of points in cluster to get mean
        for(int i = 0; i < k; ++i) { newcenters[i].div(sums[i]); }
        
        return newcenters;
    }
    
    /**
     * leastSumOfSquares
     * 
     * Calculate the sum of squares for the clusters
     * in each RetValue in ArrayList
     * 
     * @param a ArrayList of RetValue
     * @param data 2D array of data points
     * @return the RetValue with the lowest SoS
     */
    private static RetValue leastSumOfSquares(ArrayList<RetValue> a, double[][] data) {
        double[] sums = new double[a.size()];//a sum for each RetValue
        
        for(int i = 0; i < a.size(); ++i) {//foreach RetValue
            RetValue r = a.get(i);
            for(int j = 0; j < data.length; ++j) {//foreach sample in data
                for(int k = 0; k < data[j].length; ++k) {//foreach feature in sample
                    //sum the square of the differences from mean (centers)
                    sums[i] += Math.pow(data[j][k] - r.centers[r.clusters[j]].points[k], 2);
                }                 
            }
        }
        //find min sum
        double min = sums[0];
        int min_i = 0;
        for(int i = 0; i < sums.length; ++i) {
            if(sums[i] < min){ min = sums[i]; min_i = i; }
        }
        
        return a.get(min_i);
    }    
}
