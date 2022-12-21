package game;

import game.component.Component;
import game.component.ComponentManager;
import game.scene.SceneManager;

public class GameManager<TComponent extends Component> {
  private final SceneManager<TComponent> sceneManager;

  public GameManager(SceneManager<TComponent> sceneManager) {
    this.sceneManager = sceneManager;
  }

  public void loadScene(String name) {
    sceneManager.loadScene(name);
  }

  public void exitGame() {
    sceneManager.exitCurrentScene();
  }

  public ComponentManager<TComponent> getCurrentComponentManager() {
    return sceneManager.getCurrentScene().getComponentManager();
  }
}
