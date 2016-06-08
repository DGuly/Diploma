package ua.kpi.worker;

import javafx.scene.chart.XYChart;

import java.util.List;

/**
 * Created by dmytryguly on 5/19/16.
 */
public class ChartBuilder {

    public void buildChart(XYChart.Series series, List<Double> xValue, List<Double> yValue) {
        for (int i = 0; i < xValue.size(); i++) {
            double x = xValue.get(i);
            double y = yValue.get(i);
            series.getData().add(new XYChart.Data(x, y));
        }
    }
}
