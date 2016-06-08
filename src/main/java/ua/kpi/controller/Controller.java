package ua.kpi.controller;

/**
 * Created by dmytryguly on 5/18/16.
 */

import javafx.scene.Node;

public interface Controller {
    Node getView();

    void setView(Node view);
}
