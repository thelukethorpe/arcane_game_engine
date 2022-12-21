package game;

import game.canvas.ScreenCanvas;
import game.component.Component;
import game.scene.Scene;
import game.scene.SceneManager;
import util.time.TimeKeeper;

public class GameEngine<TComponent extends Component> {

  private final SceneManager<TComponent> sceneManager;
  private final ScreenCanvas<TComponent> screenCanvas;

  protected GameEngine(
      SceneManager<TComponent> sceneManager, ScreenCanvas<TComponent> screenCanvas) {
    this.sceneManager = sceneManager;
    this.screenCanvas = screenCanvas;
  }

  public void launch(GameScript gameScript) throws GameException {
    Thread gameScriptThread = new Thread(() -> gameScript.run(new GameManager<>(sceneManager)));
    gameScriptThread.start();
    screenCanvas.show();
    TimeKeeper timeKeeper = new TimeKeeper();
    for (Scene<TComponent> currentScene = sceneManager.getCurrentScene();
        currentScene != null;
        currentScene = sceneManager.getCurrentScene()) {
      currentScene.tick(timeKeeper.TickAndGetDelta());
      currentScene.renderTo(screenCanvas);
    }
    screenCanvas.hide();
    screenCanvas.close();
    try {
      gameScriptThread.join();
    } catch (InterruptedException e) {
      throw new GameException(e);
    }
  }
}
