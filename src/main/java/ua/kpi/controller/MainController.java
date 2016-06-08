package ua.kpi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import ua.kpi.ui.SpringFXMLLoader;
import ua.kpi.worker.ChartBuilder;
import ua.kpi.worker.MathWorker;

import java.io.IOException;

import static ua.kpi.worker.MathConstants.*;


/**
 * Created by dmytryguly on 5/18/16.
 */
@Component
public class MainController extends AbstractController {

    private MathWorker worker = new MathWorker();
    private ChartBuilder chartBuilder = new ChartBuilder();
    final static int d;

    static {
        d = d_LOWER;
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws InterruptedException, IOException {
        Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    }


    @FXML
    public void buildUaFromM(ActionEvent event) {
        Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Число адресов");
        yAxis.setLabel("Напряжение А");
        //creating the chart
        final LineChart<Number, Number> lineChart =
                new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("График зависимости Ua от m(числа адресов) при Uвх = " + Uvh);
        lineChart.setPrefSize(900, 560);
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Напряжение Ua от количества адресов");
        //populating the series with data

        chartBuilder.buildChart(series, worker.getMs(), worker.getUas(Uvh, t, d));
        chartBuilder.buildChart(series, worker.getMs(), worker.getUbs(Uvh, t, d));


        Button getBackToMenu = new Button("Вернуться в меню");
        getBackToMenu.setPrefSize(150, 30);
        getBackToMenu.setOnAction(event1 -> {
            MainController controller = (MainController) SpringFXMLLoader.load("main.fxml");
            Scene scene = new Scene((Parent) controller.getView(), 600, 400);
            previousStage.setScene(scene);
        });

        Slider slider = new Slider();
        slider.setMin(Uvh_LOWER);
        slider.setMax(Uvh_UPPER);
        slider.setValue(Uvh);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            lineChart.getData().removeAll();
            chartBuilder.buildChart(series, worker.getMs(), worker.getUas(newValue.doubleValue(), t, d));
            chartBuilder.buildChart(series, worker.getMs(), worker.getUbs(newValue.doubleValue(), t, d));
            lineChart.getData().add(series);
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(lineChart, getBackToMenu, slider);
        vBox.setCenterShape(true);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 900, 600);
        lineChart.getData().add(series);

        previousStage.setScene(scene);
    }

    @FXML
    public void buildAppConstants(ActionEvent event) {
        Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final CategoryAxis xAxis1 = new CategoryAxis();
        final NumberAxis yAxis1 = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<>(xAxis1, yAxis1);
        bc.setPrefSize(500, 900);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("константы");
        series1.getData().add(new XYChart.Data(KQ_NAME, KQ));
        series1.getData().add(new XYChart.Data(BETA_NAME, BETA));
        series1.getData().add(new XYChart.Data(e_NAME, e));
        series1.getData().add(new XYChart.Data(Uvh_NAME, Uvh));


        Button getBackToMenu = new Button("Вернуться в меню");
        getBackToMenu.setPrefSize(150, 30);
        getBackToMenu.setOnAction(event1 -> {
            MainController controller = (MainController) SpringFXMLLoader.load("main.fxml");
            Scene scene = new Scene((Parent) controller.getView(), 600, 400);
            previousStage.setScene(scene);
        });

        bc.getData().addAll(series1);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(bc, getBackToMenu);
        vbox.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(vbox, 900, 600);
        previousStage.setScene(scene1);
    }

    @FXML
    public void buildFread(ActionEvent event) {
        Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Длина элемента, нм");
        yAxis.setLabel("Быстродействие (частота реагирования), ГГц");
        //creating the chart
        final LineChart<Number, Number> lineChart =
                new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("График зависимости быстродействия от длины элементов");
        lineChart.setPrefSize(900, 560);
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Быстродействие от длины элемента");
        //populating the series with data

        chartBuilder.buildChart(series, worker.getDs(), worker.getFreads());
        //chartBuilder.buildChart(series, worker.getDs(), worker.getUbs(Uvh, t, d));


        Button getBackToMenu = new Button("Вернуться в меню");
        getBackToMenu.setPrefSize(150, 30);
        getBackToMenu.setOnAction(event1 -> {
            MainController controller = (MainController) SpringFXMLLoader.load("main.fxml");
            Scene scene = new Scene((Parent) controller.getView(), 600, 400);
            previousStage.setScene(scene);
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(lineChart, getBackToMenu);
        vBox.setCenterShape(true);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 900, 600);
        lineChart.getData().add(series);

        previousStage.setScene(scene);
    }

}
