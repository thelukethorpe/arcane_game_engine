package game.component;

import game.component.geometry.ScreenCanvasAdapter;
import game.util.concurrent.Atomic;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ComponentManager {
  private final ConcurrentLinkedQueue<Component> components;
  private final Atomic<ScreenCanvasAdapter> screenCanvasAdapter;

  public ComponentManager() {
    this.components = new ConcurrentLinkedQueue<>();
    this.screenCanvasAdapter = new Atomic<>();
  }

  public void setScreenCanvasAdapter(ScreenCanvasAdapter screenCanvasAdapter) {
    this.screenCanvasAdapter.set(screenCanvasAdapter);
  }

  public void addComponent(Component component) {
    components.add(component);
  }

  public List<Component> getScreenAdaptedComponents() {
    ScreenCanvasAdapter screenCanvasAdapter = this.screenCanvasAdapter.get();
    List<Component> screenAdaptedComponents = new LinkedList<>();
    for (Component component : components) {
      if (screenCanvasAdapter == null) {
        throw new MissingScreenCanvasAdapterException("Screen canvas adapter has not been set");
      }
      screenAdaptedComponents.add(component.apply(screenCanvasAdapter));
    }
    return screenAdaptedComponents;
  }

  public List<Component> getScreenAdaptedComponentsInOrderOfAppearance() {
    List<Component> screenAdaptedComponents = new ArrayList<>(getScreenAdaptedComponents());
    screenAdaptedComponents.sort(Comparator.comparingDouble(Component::getPrecedence));
    return screenAdaptedComponents;
  }
}
