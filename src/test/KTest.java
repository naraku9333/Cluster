
import cluster.Cluster;
import cluster.Euclidean;
import cluster.Point;
import cluster.RetValue;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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
 * KTest
 * 
 * Driver program to test kMeans 
 * 
 * @author      Sean Vogel
 *  zid         z1729629
 *  course      CSCI-490-D
 *  assignment  1
 * 
 */
public class KTest {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * Part A
         */
//        double[][] testData1 = {{1.0}, {2.0}, {6.0}, {7.0}};
//        double[][] testData2 = {{0, 0}, {1,0}, {2, 0}, {2, 1}, {3, 1}, {4,1}};
//        
//        try {
//            Point[] c1 = { new Point(new double[]{6.0}), new Point(new double[]{7.0})};
//            Point[] c2 = { new Point(new double[]{3,1}), new Point(new double[]{4,1})};                                                
//            
//            File file = new File("outputA.txt");
//            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
//                bw.write("\nTest 1: kMeans(int k, Distance d, Point[] centers, double[][] data)");
//                
//                RetValue ret1 = Cluster.kMeans(2, new Euclidean(), c1, testData1);
//                doOutput(bw, ret1);
//                
//                bw.write("\nTest 2: kMeans(int k, Distance d, int runs, double[][] data)");
//                bw.write("with runs == 5");
//                
//                RetValue ret2 = Cluster.kMeans(2, new Euclidean(), 5, testData2);
//                doOutput(bw, ret2);
//            }
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
        
                
        /**
        * Parts B and C
        */
        try {                        
            double[][] imgData = new double[250*250 + 1][1];
            Scanner sc = new Scanner(new File("gerber.dat"));
            int n = 0;
            while(sc.hasNextDouble()) {
                imgData[n][0] = sc.nextDouble();
                ++n;
            }
            
            Point[] c3 = { new Point(new double[]{1.0}), new Point(new double[]{50.0})};            
                   
            File file = new File("outputB.txt");
            RetValue ret;
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("\nTest 1: kMeans(int k, Distance d, Point[] centers, double[][] data)");
                ret = Cluster.kMeans(2, new Euclidean(), c3, imgData);
                doOutput(bw, ret);
            }            
                        
            //part C
            BufferedImage bi = new BufferedImage(250, 250, BufferedImage.TYPE_BYTE_BINARY);
            bi.getRaster().setPixels(0, 0, 250, 250, ret.clusters);
            
            File output = new File("gerber.png");//"outputImage.png");
            ImageIO.write(bi, "png", output);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Helper function to output results
     * 
     * @param bw
     * @param ret
     * @throws IOException 
     */
    private static void doOutput(BufferedWriter bw, RetValue ret) throws IOException {
        StringBuilder sb = new StringBuilder();
        //output center points of each cluster
        sb.append("\nCluster centers: ");
        for(Point p : ret.centers) {
            sb.append("(");
            for(double dd : p.points) {
                sb.append(dd).append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append(") ");
        }
        sb.append("\n\n");

        //output cluster assignments
        for(int i = 0; i < ret.clusters.length; ++i) {
           sb.append("observation: ").append(i+1).append("\t\tcluster: ")
                   .append(ret.clusters[i]).append("\n"); 
        }
        bw.write(sb.toString());
    }
}
