package cluster;


import java.util.Arrays;

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
 * Point
 * 
 * class to represent a data point with d dimensionality
 * 
 * @author      Sean Vogel
 * @zid         z1729629
 * @course      CSCI-490-D
 * @assignment  1
 * 
 */
public class Point {

    /**
     *
     */
    public int d;//dimensionality 

    /**
     *
     */
    public double[] points;
    
    /**
     * Constructor that initializes the dimensionality
     * 
     * @param d
     */
    public Point(int d) { 
        this.d = d; 
        points = new double[d];
    }
    
    /**
     * Constructor that initializes
     * the points array
     * 
     * @param points
     */
    public Point(double[] points) {
        this(points.length);
        this.points = Arrays.copyOf(points, d);
    }
    
    /**
     *  Add a point to this point
     * 
     * @param p
     */
    public void add(Point p) {
        for(int i = 0; i < points.length; ++i) {
            points[i] += p.points[i];
        }
    }
    
    /**
     * Divide this point by n
     * 
     * @param n
     */
    public void div(int n) {
        for(int i = 0; i < points.length; ++i) {
            points[i] /= n;
        }
    }
    
    @Override 
    public boolean equals(Object o) {
        if(o instanceof Point) {
            return Arrays.equals(points, ((Point)o).points);
        }
        else { 
            return false; 
        }
    }
    
    /**
     * from http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
     * @return 
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(points) * d;
    }
}
