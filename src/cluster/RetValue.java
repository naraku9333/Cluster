package cluster;



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
 * RetValue
 * 
 * class to hold the return values from kMeans
 * 
 * holds an array of center points and
 * an array of cluster assignments.
 * 
 * Every index in clusters corresponds to same index in data points array
 * Ex: clusters[3] is the cluster assignment for data[3]
 * 
 *  author      Sean Vogel
 *  zid         z1729629
 *  course      CSCI-490-D
 *  assignment  1
 */
public class RetValue {

    /**
     *
     */
    public Point[] centers;    

    /**
     *
     */
    public int[] clusters;

    /**
     *
     * @param p
     * @param a
     */
    public RetValue(Point[] p, int[] a) {
        centers = p;
        clusters = a;
    }
}
