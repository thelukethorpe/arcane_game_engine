package game.scene;

import game.canvas.ScreenCanvas;
import game.component.Component;
import game.component.ComponentManager;
import util.time.Tickable;

public class Scene<TComponent extends Component> implements Tickable {

  private final String name;
  private final ComponentManager<TComponent> componentManager;

  public Scene(String name) {
    this.name = name;
    this.componentManager = new ComponentManager<>();
  }

  @Override
  public void tick(double delta) {
    componentManager.tick(delta);
  }

  public String getName() {
    return name;
  }

  public ComponentManager<TComponent> getComponentManager() {
    return componentManager;
  }

  public void renderTo(ScreenCanvas<TComponent> screenCanvas) {
    screenCanvas.drawToScreen(componentManager.getComponentsInReverseOrderOfAppearance());
  }
}
