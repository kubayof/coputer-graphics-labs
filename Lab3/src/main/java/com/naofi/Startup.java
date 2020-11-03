package com.naofi;

import com.naofi.controller.Lab3Controller;
import com.naofi.model.ModelFacade;
import com.naofi.view.ViewFacade;

import java.awt.*;

public class Startup {
    private static void createAndShowGUI()
    {
        ModelFacade model = new ModelFacade();
        ViewFacade view = new ViewFacade();
        new Lab3Controller(model, view);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> createAndShowGUI());
    }
}
