package enginex;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import spriteAnimation.Frame;

public class Animation {
	private int					frameCount;												// Counts ticks for change
	private int					frameDelay;												// frame delay 1-12 (You will have to play around with this)
	private int					currentFrame;											// animations current frame
	private int					animationDirection;								// animation direction (i.e counting forward or backward)
	private int					totalFrames;											// total amount of frames for your animation

	private boolean			running;													// animation is running...

	private List<Frame>	frames	= new ArrayList<Frame>();	// Arraylist of frames
	
	public String name = "";

	public Animation(BufferedImage[] frames, int frameDelay) {
		this.running = true;

		for(int i = 0; i < frames.length; i++)
			addFrame(frames[i], frameDelay);

		this.frameCount = 0;
		this.frameDelay = frameDelay;
		this.currentFrame = 0;
		this.animationDirection = 1;
		this.totalFrames = this.frames.size();
	}
	
	public Animation(BufferedImage[] frames) {
		this.running = true;

		for(int i = 0; i < frames.length; i++)
			addFrame(frames[i], frameDelay);

		this.frameCount = 0;
		this.frameDelay = 1;
		this.currentFrame = 0;
		this.animationDirection = 1;
		this.totalFrames = this.frames.size();
	}
	
	public Animation(BufferedImage frame) {
		this.running = true;
		
		addFrame(frame, 1);
		
		this.frameCount = 0;
		this.frameDelay = 1;
		this.currentFrame = 0;
		this.animationDirection = 1;
		this.totalFrames = this.frames.size();
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void printName() {
		System.out.println(name);
	}
	public void start() {
		if(frames.size() == 0)
			return;

		running = true;
	}

	public void stop() {
		if(frames.size() == 0)
			return;

		running = false;
	}

	public void restart() {
		if(frames.size() == 0)
			return;

		running = true;
		currentFrame = 0;
	}

	public void reset() {
		this.running = false;
		this.frameCount = 0;
		this.currentFrame = 0;
	}

	private void addFrame(BufferedImage frame, int duration) {
		if(duration <= 0) {
			System.err.println("Invalid duration: " + duration);
			throw new RuntimeException("Invalid duration: " + duration);
		}

		frames.add(new Frame(frame, duration));
		currentFrame = 0;
	}

	public BufferedImage getSprite() {
		return frames.get(currentFrame).getFrame();
	}

	public void update() {
		if(running) {
			frameCount++;
			if(frameCount > frameDelay) {
				frameCount = 0;
				currentFrame += animationDirection;

				if(currentFrame > totalFrames - 1)
					currentFrame = 0;
				else if(currentFrame < 0)
					currentFrame = totalFrames - 1;
			}
		}
	}

}