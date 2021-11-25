package game;

import game.canvas.BufferedScreenCanvas;
import game.canvas.ScreenCanvas;
import game.scene.DuplicateSceneNameException;
import game.scene.Scene;
import game.scene.SceneManager;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameEngine {

  private final SceneManager sceneManager;
  private final ScreenCanvas screenCanvas;

  private GameEngine(SceneManager sceneManager, ScreenCanvas screenCanvas) {
    this.sceneManager = sceneManager;
    this.screenCanvas = screenCanvas;
  }

  public static Builder withScenes(Scene boat, Collection<Scene> otherScenes) {
    return new Builder(boat, otherScenes);
  }

  public static Builder withScenes(Scene boat, Scene... otherScenes) {
    return withScenes(boat, List.of(otherScenes));
  }

  public void launch(GameScript gameScript) throws GameException {
    Thread gameScriptThread =
        new Thread(
            () ->
                gameScript.run(
                    new GameManager(
                        sceneManager, screenCanvas.getWidth(), screenCanvas.getHeight())));
    gameScriptThread.start();
    screenCanvas.show();
    for (Scene currentScene = sceneManager.getCurrentScene();
        currentScene != null;
        currentScene = sceneManager.getCurrentScene()) {
      currentScene.render(screenCanvas);
    }
    screenCanvas.hide();
    screenCanvas.close();
    try {
      gameScriptThread.join();
    } catch (InterruptedException e) {
      throw new GameException(e);
    }
  }

  public static class Builder {

    private final Scene boat;
    private final Collection<Scene> otherScenes;
    private BufferedScreenCanvas.Builder screenCanvasBuilder = BufferedScreenCanvas.builder();

    private Builder(Scene boat, Collection<Scene> otherScenes) {
      this.boat = boat;
      this.otherScenes = otherScenes;
      Set<String> names = new HashSet<>();
      names.add(boat.getName());
      for (Scene scene : otherScenes) {
        String name = scene.getName();
        if (names.contains(name)) {
          throw new DuplicateSceneNameException(
              "The scene with name \"" + name + "\" has already been defined.");
        }
        names.add(name);
      }
    }

    public GameEngine build() {
      return new GameEngine(new SceneManager(boat, otherScenes), screenCanvasBuilder.build());
    }

    public Builder fullscreen() {
      screenCanvasBuilder = screenCanvasBuilder.fullscreen();
      return this;
    }

    public Builder borderless() {
      screenCanvasBuilder = screenCanvasBuilder.borderless();
      return this;
    }
  }
}
