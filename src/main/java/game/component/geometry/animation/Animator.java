package game.component.geometry.animation;

import game.component.Component;
import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

public class Animator<TComponent extends Component> {

  private final Duration duration;
  private final Animation<TComponent> animation;

  public Animator(Duration duration, Animation<TComponent> animation) {
    this.duration = duration;
    this.animation = animation;
  }

  public void animate(List<TComponent> components) {
    int nextComponent = 0;
    long durationMs = duration.toMillis();
    long startTimeMs = System.currentTimeMillis();
    Supplier<Long> getCurrentTimeMs = () -> System.currentTimeMillis() - startTimeMs;
    for (long currentTimeMs = getCurrentTimeMs.get();
        currentTimeMs <= durationMs;
        currentTimeMs = getCurrentTimeMs.get()) {
      animation.tick(currentTimeMs, components.get(nextComponent));
      nextComponent = (nextComponent + 1) % components.size();
    }
    components.forEach(component -> animation.tick(durationMs, component));
  }
}
