package inf112.project.RoboRally.gui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetsManagement {

    public final AssetManager assetManager = new AssetManager();

    public void loadTextures() {
        assetManager.load("assets/conveyorBelt_east.png", Texture.class);
        assetManager.load("assets/crossedWrench.png", Texture.class);
        assetManager.load("assets/floor_metal.jpg", Texture.class);
        assetManager.load("assets/player_one.png", Texture.class);
        assetManager.load("assets/rotationCog_right.png", Texture.class);
        assetManager.load("assets/rotationCog_left.png", Texture.class);
        assetManager.load("assets/singleWrench.png", Texture.class);
        assetManager.load("assets/flag1.png", Texture.class);
        assetManager.load("assets/flag2.png", Texture.class);
        assetManager.load("assets/flag3.png", Texture.class);
        assetManager.load("assets/flag4.png", Texture.class);
        assetManager.load("assets/flag5.png", Texture.class);
        assetManager.load("assets/flag6.png", Texture.class);
        assetManager.load("assets/flag7.png", Texture.class);
        assetManager.load("assets/flag8.png", Texture.class);
        assetManager.load("assets/flag9.png", Texture.class);
        assetManager.load("assets/pit.png", Texture.class);
        assetManager.load("assets/laserHorizontal.png", Texture.class);
        assetManager.load("assets/laserVertical.png", Texture.class);
    
    }

    public void finishLoading() {
        while (!assetManager.isFinished()) {
            assetManager.update();
        }
        assetManager.finishLoading();
    }

    public Texture getAssetFileName(String s) {
        return assetManager.get(s,Texture.class);
    }

    public void dispose() {
        assetManager.dispose();
    }
}
