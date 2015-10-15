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
 * CityBlock
 * 
 * Calculate the cityblock distance between two Points
 * 
 * @author      Sean Vogel
 *  zid         z1729629
 *  course      CSCI-490-D
 *  assignment  1
 */
public class CityBlock implements Distance {

    /**
     * Get the cityblock distance between a and b
     * 
     * @param a Point
     * @param b Point
     * @return  the calculated distance
     * @throws  Exception 
     */
    @Override
    public double get(Point a, Point b) throws Exception {
        if(a.d != b.d) { throw new Exception("Point dimensions must match!"); }

        double sum = 0;
        for(int i = 0; i < a.d; ++i) {
            sum += Math.abs(a.points[i] - b.points[i]);
        }
        return sum;
    }    
}
