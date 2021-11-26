import game.GameEngine;
import game.GameException;
import game.GameScript;
import game.canvas.Colour;
import game.canvas.Shader;
import game.component.Component;
import game.component.ComponentManager;
import game.component.PolygonComponent;
import game.component.geometry.RealVertex;
import game.component.geometry.ScreenCanvasAdapter;
import game.component.geometry.shape.Rectangle;
import game.component.geometry.shape.Triangle;
import game.scene.Scene;
import java.util.concurrent.TimeUnit;

public class GameSimplePolygonExample {
  public static void main(String[] args) {
    GameScript gameScript =
        gameManager -> {
          int virtualWidth = 100;
          int virtualHeight = 50;
          ScreenCanvasAdapter screenCanvasAdapter =
              gameManager.getScreenCanvasAdapter(virtualWidth, virtualHeight);
          Component redTriangle =
              new PolygonComponent(
                  new Triangle(
                      new RealVertex(0.0, 0.0),
                      new RealVertex(7.5, 0.2),
                      new RealVertex(50.0, 25.0)),
                  Shader.of(Colour.RED),
                  0.0);
          Component greenSquare =
              new PolygonComponent(
                  new Rectangle(new RealVertex(80.0, 30.0), 20.0, 20.0),
                  Shader.of(Colour.GREEN),
                  0.0);
          Component blueRectangle =
              new PolygonComponent(
                  new Rectangle(new RealVertex(50.0, 25.0), 15.0, 6.0),
                  Shader.of(Colour.BLUE),
                  0.0);
          ComponentManager componentManager = gameManager.getCurrentComponentManager();
          componentManager.setScreenCanvasAdapter(screenCanvasAdapter);
          componentManager.addComponent(redTriangle);
          componentManager.addComponent(greenSquare);
          componentManager.addComponent(blueRectangle);
          try {
            TimeUnit.SECONDS.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          gameManager.exitGame();
        };
    GameEngine gameEngine =
        GameEngine.withScenes(new Scene("Boat", Shader.of(Colour.BLACK)))
            .fullscreen()
            .borderless()
            .build();
    try {
      gameEngine.launch(gameScript);
    } catch (GameException e) {
      e.printStackTrace();
    }
  }
}
