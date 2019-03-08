package inf112.project.RoboRally;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.project.RoboRally.gui.GraphicalUserInterface;

public class RoboRallyMain {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "RoboRally";
        cfg.width = 1200;
        cfg.height = 800;

        new LwjglApplication(new GraphicalUserInterface(), cfg);
    }
}