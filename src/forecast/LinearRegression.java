package forecast;

import bean.DataNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LinearRegression {

    private List<DataNode> list;
    private double alpha;
    private double beta;
    private double r;
    public LinearRegression() throws IOException {
    }
    public void init(List<DataNode> list){
        this.list = list;
    }
    public double getAlpha() {
        return alpha;
    }
    public double getBeta() {
        return beta;
    }
    public double getR(){
        return this.r;
    }
    public void getAB(){
        int n = list.size();
        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;
        for (DataNode dataNode : list){
            sumX += dataNode.getX();
            sumY += dataNode.getY();
            sumXY += dataNode.getXY();
            sumX2 += Math.pow(dataNode.getX(), 2);
        }
        this.alpha = (((sumY * sumX) / n) - sumXY) / (((sumX * sumX) / n) - sumX2);
        this.beta = (sumY - this.alpha * sumX) / n;
    }
    public void getR2(){
        double num = 0;
        double den = 0;
        double sumY = 0;
        for (DataNode dataNode : list){
            sumY += dataNode.getY();
        }
        double avgY = sumY / list.size();
        for (DataNode dataNode : list){
            num += Math.pow((dataNode.getY() - (dataNode.getX() * this.alpha + beta)), 2);
            den += Math.pow((dataNode.getY() - avgY), 2);
        }
        this.r = 1 - (num / den);
    }
}
