import game.GameEngine;
import game.GameException;
import game.GameScript;
import game.canvas.Colour;
import game.canvas.Shader;
import game.component.ComponentManager;
import game.component.PolygonComponent;
import game.component.geometry.RealVertex;
import game.component.geometry.ScreenCanvasAdapter;
import game.component.geometry.animation.Animation;
import game.component.geometry.animation.Animator;
import game.component.geometry.shape.Circle;
import game.scene.Scene;
import game.util.time.TimeKeeper;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class GameAnimatePolygonExample {
  public static void main(String[] args) {
    GameScript gameScript =
        gameManager -> {
          int virtualWidth = 100;
          int virtualHeight = 50;
          ScreenCanvasAdapter screenCanvasAdapter =
              gameManager.getScreenCanvasAdapter(virtualWidth, virtualHeight);
          PolygonComponent redCircle =
              new PolygonComponent(
                  new Circle(new RealVertex(45.0, 35.0), 5.0), Shader.of(Colour.RED), 0.0);
          PolygonComponent yellowCircle =
              new PolygonComponent(
                  new Circle(new RealVertex(50.0, 35.0), 5.0), Shader.of(Colour.YELLOW), 1.0);
          PolygonComponent greenCircle =
              new PolygonComponent(
                  new Circle(new RealVertex(55.0, 35.0), 5.0), Shader.of(Colour.GREEN), 2.0);
          ComponentManager componentManager = gameManager.getCurrentComponentManager();
          componentManager.setScreenCanvasAdapter(screenCanvasAdapter);
          componentManager.addComponent(redCircle);
          componentManager.addComponent(yellowCircle);
          componentManager.addComponent(greenCircle);
          double g = 9.81;
          double l = 20.0;
          double initialTheta = 0.75;
          Function<Double, RealVertex> pendulumPosition =
              (time) -> {
                double theta = initialTheta * Math.cos(Math.sqrt(g / l) * time);
                double x = l * Math.sin(theta);
                double y = l * Math.cos(theta);
                return new RealVertex(x, y);
              };
          TimeKeeper<RealVertex> pendulumTimeKeeper = new TimeKeeper<>(pendulumPosition.apply(0.0));
          Animation<PolygonComponent> pendulumSwingAnimation =
              (timeInMs, component) -> {
                double time = timeInMs / 1000.0;
                RealVertex previousPosition = pendulumTimeKeeper.getPreviousValue();
                RealVertex nextPosition = pendulumPosition.apply(time);
                RealVertex positionDelta = nextPosition.minus(previousPosition);
                pendulumTimeKeeper.update(time, nextPosition);
                component.translate(positionDelta);
              };
          Animator<PolygonComponent> pendulumSwingAnimator =
              new Animator<>(Duration.ofSeconds(10), pendulumSwingAnimation);
          pendulumSwingAnimator.animate(List.of(redCircle, yellowCircle, greenCircle));
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
