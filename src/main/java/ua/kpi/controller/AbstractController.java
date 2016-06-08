package ua.kpi.controller;

import javafx.scene.Node;

/**
 * Created by dmytryguly on 5/18/16.
 */
public abstract class AbstractController implements Controller {
    private Node view;

    public Node getView() {
        return view;
    }

    public void setView(Node view) {
        this.view = view;
    }
}
