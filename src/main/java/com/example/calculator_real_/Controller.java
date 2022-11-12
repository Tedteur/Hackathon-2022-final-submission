package com.example.calculator_real_;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML
    private Button power;
    @FXML
    private TextField newConfig;
    @FXML
    private Button tick1;
    @FXML
    private ChoiceBox updateConfig;
    @FXML
    private Button tick2;
    @FXML
    private ChoiceBox existConfig;
    @FXML
    private Button reset;
    @FXML
    private Slider higher;
    @FXML
    private Slider lower;

    public void setHigherLimit(ActionEvent event){
        try{
            HelloApplication.controller.setUpperLimit((int)higher.getValue());
        } catch (Exception e){

        }

    }
    public void setLowerLimit(ActionEvent event){
        try{
            HelloApplication.controller.setLowerLimit((int)lower.getValue());
        } catch (Exception e){

        }
    }

}