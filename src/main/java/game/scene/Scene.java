package game.scene;

import game.canvas.ScreenCanvas;
import game.canvas.Shader;
import game.component.Component;
import game.component.ComponentManager;
import java.util.Collections;
import java.util.List;

public class Scene {

  private final String name;
  private final Shader backgroundShader;
  private final ComponentManager componentManager;

  public Scene(String name, Shader backgroundShader) {
    this.name = name;
    this.backgroundShader = backgroundShader;
    this.componentManager = new ComponentManager();
  }

  public String getName() {
    return name;
  }

  public ComponentManager getComponentManager() {
    return componentManager;
  }

  private void printBackground(ScreenCanvas screenCanvas) {
    screenCanvas.shade(backgroundShader);
  }

  private void printComponents(ScreenCanvas screenCanvas) {
    List<Component> components = componentManager.getScreenAdaptedComponentsInOrderOfAppearance();
    Collections.reverse(components);
    for (Component component : components) {
      component.print(screenCanvas);
    }
  }

  public void render(ScreenCanvas screenCanvas) {
    printBackground(screenCanvas);
    printComponents(screenCanvas);
    screenCanvas.paintToScreen();
  }
}
