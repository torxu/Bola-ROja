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
import java.util.Random;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;

public class Circulo extends Application
{

    private int dx = 5;
    private int dy = 5;
    private int rx = 5;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage escenario)
    {
        Group contenedor = new Group();

        Scene escena = new Scene(contenedor, 500, 500);

        Circle circulo = new Circle(10, Color.web("red"));
        Rectangle rectangulo = new Rectangle(100,10, Color.web("blue"));

        Label perder = new Label("Has perdido el juego");
        perder.setTranslateX(200);
        perder.setTranslateY(250);
        perder.setScaleX(1.5);
        perder.setScaleY(1.5);

        Random rnd = new Random();
        Random r = new Random();
        int valorRandom = rnd.nextInt(480)+10;
        int valorRandom2 = r.nextInt(480)+10;

        circulo.relocate(250, 20);
        rectangulo.relocate(200,450);

        contenedor.getChildren().add(circulo);
        contenedor.getChildren().add(rectangulo);

        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(.0200),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        double xMin = circulo.getBoundsInParent().getMinX();
                        double yMin = circulo.getBoundsInParent().getMinY();
                        double xMax = circulo.getBoundsInParent().getMaxX();
                        double yMax = circulo.getBoundsInParent().getMaxY();

                        if (xMin < 0 || xMax > escena.getWidth()) {
                            dx = dx * -1;
                        }
                        if (yMin < 0  || yMax > escena.getWidth()+20) {
                            dy = dy * -1;
                        }

                        if(rectangulo.getBoundsInParent().intersects(circulo.getBoundsInParent())){
                            dy = dy * -1;
                        }

                        circulo.setTranslateX(circulo.getTranslateX() + dx);
                        circulo.setTranslateY(circulo.getTranslateY() + dy);

                        rectangulo.setTranslateX(rectangulo.getTranslateX() + rx);

                        double xMinr = rectangulo.getBoundsInParent().getMinX();
                        double xMaxr = rectangulo.getBoundsInParent().getMaxX();

                       if (xMinr == 0 || xMaxr == escena.getWidth()) {
                           rx = 0;
                        }

                        escena.setOnKeyPressed(t ->{                                                                                      
                                if (t.getCode() == KeyCode.LEFT && !(xMinr < -10)){                  
                                    if (xMinr == 0) {  
                                        rx = 0;
                                    }
                                    else{
                                        rx = -5;
                                    }
                                }
                            

                                if (t.getCode() == KeyCode.RIGHT) {
                                    if (xMaxr == escena.getWidth()) {  
                                        rx = 0;
                                    }
                                    else{
                                        rx = 5;
                                    }
                                }

                            });

                        if(yMin > escena.getWidth()){                           
                            contenedor.getChildren().add(perder);  
                            tl.stop();
                        }

                    }
                });        

        tl.getKeyFrames().add(moveBall);
        tl.play();

        escenario.setScene(escena);

        escenario.show();
    }
}
