package gl;

import game.GameEngine;
import game.canvas.ScreenCanvas;
import game.scene.DuplicateSceneNameException;
import game.scene.Scene;
import game.scene.SceneManager;
import gl.canvas.GLScreenCanvas;
import gl.component.GLDrawableComponent;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GLGameEngine extends GameEngine<GLDrawableComponent> {

  private GLGameEngine(
      SceneManager<GLDrawableComponent> sceneManager,
      ScreenCanvas<GLDrawableComponent> screenCanvas) {
    super(sceneManager, screenCanvas);
  }

  public static Builder withScenes(
      Scene<GLDrawableComponent> boat, Collection<Scene<GLDrawableComponent>> otherScenes) {
    return new Builder(boat, otherScenes);
  }

  public static Builder withScenes(
      Scene<GLDrawableComponent> boat, Scene<GLDrawableComponent>... otherScenes) {
    return withScenes(boat, List.of(otherScenes));
  }

  public static class Builder {

    private final Scene<GLDrawableComponent> boat;
    private final Collection<Scene<GLDrawableComponent>> otherScenes;
    private GLScreenCanvas.Builder screenCanvasBuilder = GLScreenCanvas.builder();

    private Builder(
        Scene<GLDrawableComponent> boat, Collection<Scene<GLDrawableComponent>> otherScenes) {
      this.boat = boat;
      this.otherScenes = otherScenes;
      Set<String> names = new HashSet<>();
      names.add(boat.getName());
      for (Scene<GLDrawableComponent> scene : otherScenes) {
        String name = scene.getName();
        if (names.contains(name)) {
          throw new DuplicateSceneNameException(
              "The scene with name \"" + name + "\" has already been defined.");
        }
        names.add(name);
      }
    }

    public GLGameEngine build() {
      return new GLGameEngine(new SceneManager<>(boat, otherScenes), screenCanvasBuilder.build());
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
