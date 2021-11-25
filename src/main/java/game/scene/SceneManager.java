package game.scene;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SceneManager {

  private final Map<String, Scene> scenes;
  private Scene currentScene;

  public SceneManager(Scene boat, Collection<Scene> scenes) {
    this.scenes = scenes.stream().collect(Collectors.toMap(Scene::getName, Function.identity()));
    this.scenes.put(boat.getName(), boat);
    this.loadScene(boat.getName());
  }

  public synchronized void loadScene(String name) {
    Scene scene = scenes.get(name);
    if (scene == null) {
      throw new UndefinedSceneNameException("Scene named \"" + name + "\" has not been defined.");
    }
    currentScene = scene;
  }

  public synchronized void exitCurrentScene() {
    currentScene = null;
  }

  public synchronized Scene getCurrentScene() {
    return currentScene;
  }
}
