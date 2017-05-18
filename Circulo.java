import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Circulo extends Application
{
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
        circulo.setCenterX(250);
        circulo.setCenterY(250);
        
        contenedor.getChildren().add(circulo);
        
        escenario.setScene(escena);
        
        escenario.show();
    }
}
