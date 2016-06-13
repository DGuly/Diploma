package ua.kpi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import ua.kpi.ui.SpringFXMLLoader;
import ua.kpi.worker.ChartBuilder;
import ua.kpi.worker.DataStorage;
import ua.kpi.worker.MathWorker;

import java.io.IOException;

import static ua.kpi.worker.MathConstants.*;


/**
 * Created by dmytryguly on 5/18/16.
 */
@Component
public class MainController extends AbstractController {

    private ChartBuilder chartBuilder = new ChartBuilder();
    private final DataStorage dataStorage = DataStorage.getInstance();
    private MathWorker worker = new MathWorker(dataStorage);

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

        lineChart.setTitle("График зависимости Ua от m(числа адресов) при Uвх = " + dataStorage.getUvh());
        lineChart.setPrefSize(900, 560);
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Напряжение Ua от количества адресов");
        //populating the series with data

        chartBuilder.buildChart(series, worker.getMs(), worker.getUas(dataStorage.getUvh(), t, dataStorage.getD_LOWER()));
        chartBuilder.buildChart(series, worker.getMs(), worker.getUbs(dataStorage.getUvh(), t, dataStorage.getD_LOWER()));


        Button getBackToMenu = new Button("Вернуться в меню");
        getBackToMenu.setPrefSize(150, 30);
        getBackToMenu.setOnAction(event1 -> {
            MainController controller = (MainController) SpringFXMLLoader.load("main.fxml");
            Scene scene = new Scene((Parent) controller.getView(), 600, 400);
            previousStage.setScene(scene);
        });

        Slider slider = new Slider();
        slider.setMin(dataStorage.getUvh_LOWER());
        slider.setMax(dataStorage.getUvh_UPPER());
        slider.setValue(dataStorage.getUvh());
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            lineChart.getData().removeAll();
            chartBuilder.buildChart(series, worker.getMs(), worker.getUas(newValue.doubleValue(), t, dataStorage.getD_LOWER()));
            chartBuilder.buildChart(series, worker.getMs(), worker.getUbs(newValue.doubleValue(), t, dataStorage.getD_LOWER()));
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
        series1.getData().add(new XYChart.Data(KQ_NAME, dataStorage.getKQ()));
        series1.getData().add(new XYChart.Data(BETA_NAME, BETA));
        series1.getData().add(new XYChart.Data(e_NAME, e));
        series1.getData().add(new XYChart.Data(Uvh_NAME, dataStorage.getUvh()));


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
    public void setIncomingData(ActionEvent event) {
        Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 50, 50, 50));
        vbox.setSpacing(10);

        TextField KQ = new TextField(Double.toString(dataStorage.getKQ()));
        TextField m_LOWER = new TextField(Integer.toString(dataStorage.getM_LOWER()));
        TextField m_UPPER = new TextField(Integer.toString(dataStorage.getM_UPPER()));
        TextField d_LOWER = new TextField(Integer.toString(dataStorage.getD_LOWER()));
        TextField d_MIDDLE = new TextField(Integer.toString(dataStorage.getD_MIDDLE()));
        TextField Uvh_LOWER = new TextField(Double.toString(dataStorage.getUvh_LOWER()));
        TextField Uvh_UPPER = new TextField(Double.toString(dataStorage.getUvh_UPPER()));
        TextField Uvh = new TextField(Double.toString(dataStorage.getUvh()));
        HBox hBox1 = new HBox();
        hBox1.setPadding(new Insets(10, 10, 10, 10));
        hBox1.setSpacing(50);
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        Label KQ_label = new Label("Заряд Kq");
        hBox1.getChildren().addAll(KQ_label, KQ);

        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(10, 10, 10, 10));
        hBox2.setSpacing(50);
        hBox2.setAlignment(Pos.CENTER_RIGHT);
        Label m_LOWER_label = new Label("Нижняя граница числа адресов");
        hBox2.getChildren().addAll(m_LOWER_label, m_LOWER);

        HBox hBox3 = new HBox();
        hBox3.setPadding(new Insets(10, 10, 10, 10));
        hBox3.setSpacing(50);
        hBox3.setAlignment(Pos.CENTER_RIGHT);
        Label m_UPPER_label = new Label("Верхняя граница числа адресов");
        hBox3.getChildren().addAll(m_UPPER_label, m_UPPER);

        HBox hBox4 = new HBox();
        hBox4.setPadding(new Insets(10, 10, 10, 10));
        hBox4.setSpacing(50);
        hBox4.setAlignment(Pos.CENTER_RIGHT);
        Label d_LOWER_label = new Label("Нижняя граница размера элемента");
        hBox4.getChildren().addAll(d_LOWER_label, d_LOWER);

        HBox hBox5 = new HBox();
        hBox5.setPadding(new Insets(10, 10, 10, 10));
        hBox5.setSpacing(50);
        hBox5.setAlignment(Pos.CENTER_RIGHT);
        Label d_UPPER_label = new Label("Верхняя граница размера элемента");
        hBox5.getChildren().addAll(d_UPPER_label, d_MIDDLE);

        HBox hBox6 = new HBox();
        hBox6.setPadding(new Insets(10, 10, 10, 10));
        hBox6.setSpacing(50);
        hBox6.setAlignment(Pos.CENTER_RIGHT);
        Label Uvh_LOWER_label = new Label("Нижняя граница входного напряжения");
        hBox6.getChildren().addAll(Uvh_LOWER_label, Uvh_LOWER);

        HBox hBox7 = new HBox();
        hBox7.setPadding(new Insets(10, 10, 10, 10));
        hBox7.setSpacing(50);
        hBox7.setAlignment(Pos.CENTER_RIGHT);
        Label Uvh_UPPER_label = new Label("Верхняя граница входного напряжения");
        hBox7.getChildren().addAll(Uvh_UPPER_label, Uvh_UPPER);

        HBox hBox8 = new HBox();
        hBox8.setPadding(new Insets(10, 10, 10, 10));
        hBox8.setSpacing(50);
        hBox8.setAlignment(Pos.CENTER_RIGHT);
        Label Uvh_label = new Label("Входное напряжение");
        hBox8.getChildren().addAll(Uvh_label, Uvh);

        Button saveData = new Button("Сохранить данные");
        saveData.setPrefSize(250, 30);
        saveData.setOnAction(event1 -> {
            dataStorage.setKQ(Double.parseDouble(KQ.getText()));
            dataStorage.setM_LOWER(Integer.parseInt(m_LOWER.getText()));
            dataStorage.setM_UPPER(Integer.parseInt(m_UPPER.getText()));
            dataStorage.setD_LOWER(Integer.parseInt(d_LOWER.getText()));
            dataStorage.setD_MIDDLE(Integer.parseInt(d_MIDDLE.getText()));
            dataStorage.setUvh_LOWER(Double.parseDouble(Uvh_LOWER.getText()));
            dataStorage.setUvh_UPPER(Double.parseDouble(Uvh_UPPER.getText()));
            dataStorage.setUvh(Double.parseDouble(Uvh.getText()));
            MainController controller = (MainController) SpringFXMLLoader.load("main.fxml");
            Scene scene = new Scene((Parent) controller.getView(), 600, 400);
            previousStage.setScene(scene);
        });

        Button getBackToMenu = new Button("Вернуться в меню без сохранения");
        getBackToMenu.setPrefSize(250, 30);
        getBackToMenu.setOnAction(event1 -> {
            MainController controller = (MainController) SpringFXMLLoader.load("main.fxml");
            Scene scene = new Scene((Parent) controller.getView(), 600, 400);
            previousStage.setScene(scene);
        });

        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7, hBox8, saveData, getBackToMenu);
        Scene scene1 = new Scene(vbox, 600, 600);
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
