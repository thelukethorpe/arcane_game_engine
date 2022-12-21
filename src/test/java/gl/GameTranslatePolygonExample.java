package gl;

import game.GameEngine;
import game.GameException;
import game.GameScript;
import game.canvas.Colour;
import game.canvas.Shader;
import game.component.Component;
import game.component.ComponentManager;
import geometry.vertex.RealVertex;
import game.scene.Scene;

import java.util.concurrent.TimeUnit;

public class GameTranslatePolygonExample {
  public static void main(String[] args) {
    GameScript gameScript =
        gameManager -> {
          int virtualWidth = 100;
          int virtualHeight = 50;
          ScreenCanvasAdapter screenCanvasAdapter =
              gameManager.getScreenCanvasAdapter(virtualWidth, virtualHeight);
          Component whiteSquare =
              new PolygonComponent(
                  new Rectangle(new RealVertex(0.0, 0.0), 10.0, 10.0),
                      0.0);
          ComponentManager<TComponent> componentManager = gameManager.getCurrentComponentManager();
          componentManager.setScreenCanvasAdapter(screenCanvasAdapter);
          componentManager.addComponent(whiteSquare);
          long numberOfFrames = TimeUnit.SECONDS.toMillis(10);
          double delta = 1 / (double) numberOfFrames;
          RealVertex vertexDelta = new RealVertex(delta * virtualWidth, delta * virtualHeight);
          try {
            for (int i = 0; i < numberOfFrames; i++) {
              whiteSquare.translate(vertexDelta);
              TimeUnit.MILLISECONDS.sleep(1);
            }

          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          gameManager.exitGame();
        };
    GameEngine gameEngine =
        GameEngine.withScenes(new Scene<TComponent>("Boat", Shader.of(Colour.BLACK)))
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
