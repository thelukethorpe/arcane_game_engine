package game.scene;

import game.component.Component;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SceneManager<TComponent extends Component> {

  private final Map<String, Scene<TComponent>> scenes;
  private Scene<TComponent> currentScene;

  public SceneManager(Scene<TComponent> boat, Collection<Scene<TComponent>> scenes) {
    this.scenes = scenes.stream().collect(Collectors.toMap(Scene::getName, Function.identity()));
    this.scenes.put(boat.getName(), boat);
    this.loadScene(boat.getName());
  }

  public synchronized void loadScene(String name) {
    Scene<TComponent> scene = scenes.get(name);
    if (scene == null) {
      throw new UndefinedSceneNameException("Scene named \"" + name + "\" has not been defined.");
    }
    currentScene = scene;
  }

  public synchronized void exitCurrentScene() {
    currentScene = null;
  }

  public synchronized Scene<TComponent> getCurrentScene() {
    return currentScene;
  }
}
