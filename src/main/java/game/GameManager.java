package game;

import game.component.ComponentManager;
import game.component.geometry.ScreenCanvasAdapter;
import game.component.geometry.Vertex;
import game.scene.SceneManager;

public class GameManager {
  private final SceneManager sceneManager;
  private final int screenWidth;
  private final int screenHeight;

  public GameManager(SceneManager sceneManager, int screenWidth, int screenHeight) {
    this.sceneManager = sceneManager;
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
  }

  public void loadScene(String name) {
    sceneManager.loadScene(name);
  }

  public void exitGame() {
    sceneManager.exitCurrentScene();
  }

  public ComponentManager getCurrentComponentManager() {
    return sceneManager.getCurrentScene().getComponentManager();
  }

  public ScreenCanvasAdapter getScreenCanvasAdapter(int virtualWidth, int virtualHeight) {
    return new ScreenCanvasAdapter(
        new Vertex<>(virtualWidth, virtualHeight), new Vertex<>(screenWidth, screenHeight));
  }
}
