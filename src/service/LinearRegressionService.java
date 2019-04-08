package service;

import bean.DataNode;
import forecast.LinearRegression;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinearRegressionService {
    public double[] getABR(List<DataNode> dataNodeList)throws Exception{
        LinearRegression linearRegression = new LinearRegression();
        linearRegression.init(dataNodeList);
        linearRegression.getAB();
        linearRegression.getR();
        return new double[]{linearRegression.getAlpha(),linearRegression.getBeta(),linearRegression.getR()};
    }

}
