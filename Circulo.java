import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.Animation;
import javafx.util.Duration;
import javafx.scene.control.Button;

public class Circulo extends Application
{
            
        static int dx = 1;
        static int dy = 1;
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage escenario)
    {
        Group contenedor = new Group();
        
        Scene escena = new Scene(contenedor, 500, 500);
        
        Circle circulo = new Circle(20, Color.web("red"));
        circulo.relocate(250, 250);
        
        
        
        contenedor.getChildren().add(circulo);
        

        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(.0200),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        circulo.setTranslateX(circulo.getTranslateX() + dx);
                        circulo.setTranslateY(circulo.getTranslateY() + dy);
                    }
                });
        tl.getKeyFrames().add(moveBall);
        tl.play();
        
        Button boton = new Button("Stop/Start");
        boton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                if(!tl.getStatus().equals(Animation.Status.PAUSED)){
                    tl.pause();
                }
                else{
                    tl.play();
                }
            }
        });
        
        contenedor.getChildren().add(boton);
        
        
        escenario.setScene(escena);
        
        escenario.show();
    }
}
