import greenfoot.*;

public class Animation {
    private Actor actor;
    private String[] frames;
    private int framerate;
    private boolean playing = false;
    private boolean loop = false;
    
    private int currentFrame;
    private int currentImage = 0;
    
    private int width;
    private int height;
    private int rotation;
    
    private boolean customDimensions = false;
    private boolean customRotation = false;
    
    public Animation(Actor actor, String[] frames, int framerate) {
        this.actor = actor;
        this.frames = frames;
        this.framerate = framerate;
        this.currentFrame = this.framerate;
        
        Animator.animations.add(this);
    }
    
    public Animation(Actor actor, String[] frames, int framerate, int width, int height) {
        this.actor = actor;
        this.frames = frames;
        this.framerate = framerate;
        this.currentFrame = this.framerate;
        this.width = width;
        this.height = height;
        this.customDimensions = true;
        
        Animator.animations.add(this);
    }
    
    public Animation(Actor actor, String[] frames, int framerate, int width, int height, int rotation) {
        this.actor = actor;
        this.frames = frames;
        this.framerate = framerate;
        this.currentFrame = this.framerate;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.customDimensions = true;
        this.customRotation = true;
        
        Animator.animations.add(this);
    }
    
    public void setIndividualFramerate(int framerate) {
        this.framerate = framerate;
    }
    
    public boolean isPlaying() {
        return this.playing;
    }
    
    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
    
    public void progressAnimation() {
        if (this.currentFrame != 0) {
            this.currentFrame--;
        } else {
            this.currentFrame = this.framerate;
        }
    }
    
    public void nextImage() {
        if (this.currentImage + 1 != this.frames.length) {
            this.currentImage++;
        } else {
            this.currentImage = 0;
        }
    }
    
    public int getCurrentFrame() {
        return this.currentFrame;
    }
    
    public void setCurrentFrame(int frame) {
        this.currentFrame = frame;
    }
    
    public int getCurrentImage() {
        return this.currentImage;
    }
    
    public void setCurrentImage(int image) {
        this.currentImage = image;
    }
    
    public Actor getActor() {
        return this.actor;
    }
    
    public String[] getFrames() {
        return this.frames;
    }
    
    public boolean hasCustomDimensions() {
        return this.customDimensions;
    }
    
    public boolean hasCustomRotation() {
        return this.customRotation;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getRotation() {
        return this.rotation;
    }
    
    public boolean isLooping() {
        return this.loop;
    }
    
    public void setLooping(boolean loop) {
        this.loop = loop;
    }
    
    public int getFramerate() {
        return this.framerate;
    }
}