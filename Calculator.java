package javacalculator;

import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.*;

public class JavaCalculator extends Application 
{
    private long num1 = 0;
    private TextField tf = new TextField();
    private String op="";
    private boolean start = true;
    
    public void start(Stage stage){
        tf.setFont(Font.font(20));
        tf.setPrefHeight(50);
        tf.setAlignment(Pos.CENTER_RIGHT);
        tf.setEditable(true);
        
        StackPane sp = new StackPane();
        sp.setPadding(new Insets(10));
        sp.getChildren().add(tf);
        
        TilePane tp = new TilePane();
        tp.setHgap(10);
        tp.setVgap(10);
        tp.setPadding(new Insets(10));
        tp.setAlignment(Pos.TOP_CENTER);
        
        tp.getChildren().addAll(
                createNumber("7"),
                createNumber("8"),
                createNumber("9"),
                createOps("/"),
                
                createNumber("4"),
                createNumber("5"),
                createNumber("6"),
                createOps("X"),
                
                createNumber("1"),
                createNumber("2"),
                createNumber("3"),
                createOps("-"),
              
                createNumber("0"),
                createClear("C"),
                createOps("="),
                createOps("+")
                
        );
        
        
        
        BorderPane bp = new BorderPane();
        bp.setTop(sp);
        bp.setCenter(tp);
        
        
        Scene sc = new Scene(bp,250,330);
        stage.setScene(sc);
        stage.setTitle("Calculator for Two Operations");
        stage.show();
    }
    
    private Button createNumber(String ch){
        Button b = new Button(ch);
        b.setPrefSize(50, 50);
        b.setFont(Font.font(18));
        b.setOnAction(this::processNumber);
        return b;   
    }
    
    private Button createOps(String ch){
        Button b =  new Button(ch);
        b.setPrefSize(50, 50);
        b.setFont(Font.font(18));
        b.setOnAction(this::processOperator);
        return b;
    }
    
    private Button createClear(String ch){
        Button b = new Button(ch);
        b.setPrefSize(50, 50);
        b.setFont(Font.font(18));
        b.setOnAction(e->{
            tf.setText("");
            op = "";
            start = true;
        });
        return b;
        
    }
    
    private void processNumber(ActionEvent ae){
        String value = ((Button)ae.getSource()).getText();
        tf.setText(tf.getText() + value);
        
    }
    
    private void processOperator(ActionEvent ae){
        String value =  ((Button)ae.getSource()).getText();
        
        if(!value.equals("=")){
            if(!op.isEmpty())
                return;
            
           num1 = Long.parseLong(tf.getText());
           op = value;
           tf.setText("");
    }
        else{
            if(op.isEmpty())
                return;
            
            Long num2 = Long.parseLong(tf.getText());
            float a = operations(num1,num2,op);
            tf.setText(String.valueOf(a));
            op = "";
            start = true;
       
        }
    }
    private float operations(Long num1 ,Long num2 ,String op){
     switch(op){   
         case "+" : return num1 + num2;
         case "-" : return num1 - num2;
         case "X" : return num1 * num2;
         case "/" : return num1 / num2;
         default : return 0;
     }
    }
      public static void main(String[] args) {
          launch(args);
    }
    
}
