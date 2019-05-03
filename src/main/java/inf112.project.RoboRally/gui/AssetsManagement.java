package inf112.project.RoboRally.gui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

class AssetsManagement {

    private final AssetManager assetManager = new AssetManager();

    void loadTextures() {
        //ConveyorBelts
        assetManager.load("assets/conveyorbelts/conveyorBelt_east1.png", Texture.class);
        assetManager.load("assets/conveyorbelts/conveyorBelt_west1.png", Texture.class);
        assetManager.load("assets/conveyorbelts/conveyorBelt_north1.png", Texture.class);
        assetManager.load("assets/conveyorbelts/conveyorBelt_south1.png", Texture.class);
        assetManager.load("assets/conveyorbelts/conveyorBelt_east2.png", Texture.class);
        assetManager.load("assets/conveyorbelts/conveyorBelt_west2.png", Texture.class);
        assetManager.load("assets/conveyorbelts/conveyorBelt_north2.png", Texture.class);
        assetManager.load("assets/conveyorbelts/conveyorBelt_south2.png", Texture.class);
        
        //Wrenches, floor, pit and player
        assetManager.load("assets/crossedWrench.png", Texture.class);
        assetManager.load("assets/singleWrench.png", Texture.class);
        assetManager.load("assets/floor_metal.jpg", Texture.class);
        assetManager.load("assets/pit.png", Texture.class);
        assetManager.load("assets/player_one.png", Texture.class);
        assetManager.load("assets/player_color.png", Texture.class);
        assetManager.load("assets/player_color_north.png", Texture.class);
        assetManager.load("assets/player_color_south.png", Texture.class);
        assetManager.load("assets/player_color_east.png", Texture.class);
        assetManager.load("assets/player_color_west.png", Texture.class);
    
        //RotationCogs
        assetManager.load("assets/rotationCog_right.png", Texture.class);
        assetManager.load("assets/rotationCog_left.png", Texture.class);
    
        //Flags
        assetManager.load("assets/flags/flag1.png", Texture.class);
        assetManager.load("assets/flags/flag2.png", Texture.class);
        assetManager.load("assets/flags/flag3.png", Texture.class);
        assetManager.load("assets/flags/flag4.png", Texture.class);
        assetManager.load("assets/flags/flag5.png", Texture.class);
        assetManager.load("assets/flags/flag6.png", Texture.class);
        assetManager.load("assets/flags/flag7.png", Texture.class);
        assetManager.load("assets/flags/flag8.png", Texture.class);
        assetManager.load("assets/flags/flag9.png", Texture.class);
        assetManager.load("assets/flags/flag.png", Texture.class);
        
        //Lasers
        assetManager.load("assets/lasertowers/laserHorizontal.png", Texture.class);
        assetManager.load("assets/lasertowers/laserVertical.png", Texture.class);
        assetManager.load("assets/lasertowers/laserTower_northwards.png", Texture.class);
        assetManager.load("assets/lasertowers/laserTower_southwards.png", Texture.class);
        assetManager.load("assets/lasertowers/laserTower_eastwards.png", Texture.class);
        assetManager.load("assets/lasertowers/laserTower_westwards.png", Texture.class);
        
        //Walls
        assetManager.load("assets/walls/wall_North.png", Texture.class);
        assetManager.load("assets/walls/wall_South.png", Texture.class);
        assetManager.load("assets/walls/wall_East.png", Texture.class);
        assetManager.load("assets/walls/wall_West.png", Texture.class);
        assetManager.load("assets/walls/wall_NorthSouth.png", Texture.class);
        assetManager.load("assets/walls/wall_NorthEast.png", Texture.class);
        assetManager.load("assets/walls/wall_NorthWest.png", Texture.class);
        assetManager.load("assets/walls/wall_SouthEast.png", Texture.class);
        assetManager.load("assets/walls/wall_SouthWest.png", Texture.class);
        assetManager.load("assets/walls/wall_EastWest.png", Texture.class);
        assetManager.load("assets/walls/wall_NorthSouthEast.png", Texture.class);
        assetManager.load("assets/walls/wall_NorthSouthWest.png", Texture.class);
        assetManager.load("assets/walls/wall_NorthEastWest.png", Texture.class);
        assetManager.load("assets/walls/wall_SouthEastWest.png", Texture.class);
        assetManager.load("assets/walls/wall_NorthSouthEastWest.png", Texture.class);

        //GameOver
        assetManager.load("assets/GameOver.png", Texture.class);
        assetManager.load("assets/GameWon.png", Texture.class);
    }

    void finishLoading() {
        while (!assetManager.isFinished()) {
            assetManager.update();
        }
        assetManager.finishLoading();
    }

    Texture getAssetFileName(String s) {
        return assetManager.get(s,Texture.class);
    }

    void dispose() {
        assetManager.dispose();
    }
}
