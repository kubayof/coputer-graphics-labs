package com.naofi.controller;

import com.naofi.model.ModelFacade;
import com.naofi.view.ViewFacade;

import javax.swing.*;

public class Lab3Controller {
    private final ModelFacade model;
    private final ViewFacade view;

    public Lab3Controller(ModelFacade model, ViewFacade view) {
        this.model = model;
        this.view = view;
        initController();
        initListeners();
    }

    protected void initController() {
        model.subscribe(view);
        view.update(model.getAlgorithmNumber());
    }

    protected void initListeners() {
        view.getSelectAlgorithmPanel().getSlider().addChangeListener(changeEvent -> {
            JSlider source = (JSlider) changeEvent.getSource();
            if (!source.getValueIsAdjusting()) {
                model.setAlgorithmNumber(source.getValue());
            }
        });
    }
}
