package game.component.geometry.animation;

import game.component.Component;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class Animator<TComponent extends Component> {

  private final Duration duration;
  private final Animation<TComponent> animation;

  public Animator(Duration duration, Animation<TComponent> animation) {
    this.duration = duration;
    this.animation = animation;
  }

  public void animate(TComponent component) {
    long durationMs = duration.toMillis();
    long startTimeMs = System.currentTimeMillis();
    Supplier<Long> getCurrentTimeMs = () -> System.currentTimeMillis() - startTimeMs;
    for (long currentTimeMs = getCurrentTimeMs.get();
        currentTimeMs <= durationMs;
        currentTimeMs = getCurrentTimeMs.get()) {
      animation.tick(currentTimeMs, component);
    }
  }

  public Future<Void> animateAsync(TComponent component) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    return executor.submit(
        () -> {
          animate(component);
          return null;
        });
  }
}
