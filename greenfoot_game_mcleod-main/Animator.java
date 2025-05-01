import java.util.*;
import greenfoot.*;

public class Animator {
    public static List<Animation> animations = new ArrayList<Animation>();
    
    public static void playAnimation(Animation animation, boolean loop) {
        animation.setCurrentImage(0);
        animation.setLooping(loop);
        animation.setPlaying(true);
    }
    
    public static void pauseAnimation(Animation animation) {
        animation.setPlaying(false);
    }
    
    public static void stopAnimation(Animation animation) {
        animation.setCurrentImage(0);
        animation.setCurrentFrame(animation.getFramerate());
        animation.setPlaying(true);
    }
    
    public static void onTick() {
        for (int i = 0; i < animations.size(); i++) {
            Animation a = Animator.animations.get(i);
            if (a.isPlaying()) {
                a.progressAnimation();
                if (a.getCurrentFrame() == 0) {
                    a.nextImage();
                    
                    String[] frames = a.getFrames();
                    int currentImage = a.getCurrentImage();
                    
                    Actor actor = a.getActor();
                    actor.setImage(frames[currentImage]);
                    
                    if (a.hasCustomDimensions()) {
                        actor.getImage().scale(a.getWidth(), a.getHeight());
                    }
                    
                    if (a.hasCustomRotation()) {
                        actor.getImage().rotate(a.getRotation());
                    }
                    
                    a.progressAnimation();
                    
                    if (currentImage + 1 == frames.length && !a.isLooping()) {
                        a.setPlaying(false);
                    }
                }
            }
        }
    }
}