package inf112.project.RoboRally.objects;


import java.util.Random;

public enum Rotation {
    NONE, LEFT, RIGHT, HALFWAY;
    
    
    private static Rotation[] getAllRotations() {
        return new Rotation[]{Rotation.NONE, Rotation.LEFT, Rotation.RIGHT, Rotation.HALFWAY};
    }
    
    public static Rotation getRandomRotation() {
        Random random = new Random();
        return Rotation.getAllRotations()[random.nextInt(getAllRotations().length)];
    }
}