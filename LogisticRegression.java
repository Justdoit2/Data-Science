// Copyright 2017 smanna@scu.edu
// Student: You are allowed to make changes here.
// Do NOT alter the public method header.
// Feel free to add your own private variables and methods

import util.ChartUtil;
import util.MathUtil;
import util.MatrixUtil;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LogisticRegression {



    /**
     * Gradient Descent
     * @param X                 nxm Matrix
     * @param y                 1xm Vector
     * @param theta             1xn Vector
     * @param alpha             step factor
     * @param maxInteration     Maximum iteration for convergence
     * @return
     */
    public double[] gradientDescent(double[][] X, double[] y, double[] theta,
                                   double alpha, int maxInteration) {
        int n = X.length;   // number of features (plus an intercept term), 3 features
        int m = y.length;   // number of observations (training samples) 200 like hypothesis
        double[] costHistory = new double[maxInteration];
       double [] temp=theta.clone(); //equals number of features in temp array
   
 
        for (int it = 0; it < maxInteration; ++it) {
               //System.out.println(alpha);//this will literaly give you alpha, which you put 0.1
               // costHistory[it]=computeCost(X,y,theta);
               double track;
            for ( int i =0; i<theta.length;++i)
               {
                   
		temp[i]=theta[i] + ((alpha)*computeCost(X,y,theta));
                  
        
		}
                  
	  
              costHistory[it]=computeCost(X,y,theta); //from the gradient descent algorithm

            System.out.println("Done iteration " + it + ". Current cost: " + costHistory[it]);
        }
   

        return costHistory;
    }
    
    public double sigmoidFuction(double x) {
        return 1 / (1 + Math.exp(-x));
    }
    
    /**
     * Cost function.
     * @param X             nxm Matrix
     * @param y             1xm Vector
     * @param theta         1xn Vector
     * @return
     */
    private double computeCost(double[][] X, double[] y, double[] theta) {
        int m = y.length;   // number of observations (training samples)
        
        // theta * X    (1xn * nxm = 1xm)
        double[] hypothesis = MatrixUtil.vectorTimesMatrix(theta, X);
        
        // h = sigmoid(theta * X)
        for (int idx = 0; idx < hypothesis.length; ++idx)
            hypothesis[idx] = sigmoidFuction(hypothesis[idx]);
        
        // log(h) * y' + log(1 - h) * (1 - y)'
        double sum = 0;
        for (int idx = 0; idx < hypothesis.length; ++idx)
            sum += y[idx] * MathUtil.getLog(hypothesis[idx])
            + (1 - y[idx]) * MathUtil.getLog(1 - hypothesis[idx]);
        
        // -(log(h) * y' + log(1 - h) * (1 - y)') / m
        return   -sum/m; //already given negative sum 
    }
}
