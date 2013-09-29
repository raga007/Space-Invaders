package com.spaceinvaders;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * A sound to be used on the game. Note that a sound
 * contains no state information, i.e. its just the sound and 
 * not the location. This allows us to use a single sound in
 * lots of different places without having to store multiple 
 * copies of the sound.
 * 
 */
public class SpriteSound {
	/** The sound to be used */
	private AudioInputStream audioInputStream;
	/** Soundline object */
	private SourceDataLine soundLine;
	/** 64 KB buffere is used for playing sound files */
	private final int BUFFER_SIZE = 64*1024;  
	private URL url ;
	
	public SpriteSound(String soundFilePath) {
		// The ClassLoader.getResource() ensures we get the sprite
		// from the appropriate place, this helps with deploying the game
		// with things like webstart. You could equally do a file look
		// up here.
		url = this.getClass().getClassLoader().getResource(soundFilePath);
		
		if (url == null) {
			Util.fail("Can't find ref: "+ "gameresources/shoot.wav");
		}
	}
	/**
	 * Create a new sprite based on an image
	 * 
	 * @param image The image that is this sprite
	 */
	public SpriteSound(URL url) {
		this.url = url;
	}
	public void play(){
	      // Set up an audio input stream piped from the sound file.
	      try {
	    	  audioInputStream = AudioSystem.getAudioInputStream(url);
	         AudioFormat audioFormat = audioInputStream.getFormat();
	         DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	         soundLine = (SourceDataLine) AudioSystem.getLine(info);
	         soundLine.open(audioFormat);
	         soundLine.start();
	         int nBytesRead = 0;
	         byte[] sampledData = new byte[BUFFER_SIZE];
	         while (nBytesRead != -1) {
	            nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
	            if (nBytesRead >= 0) {
	               // Writes audio data to the mixer via this source data line.
	               soundLine.write(sampledData, 0, nBytesRead);
	            }
	         }
	      } catch (UnsupportedAudioFileException ex) {
	         ex.printStackTrace();
	      } catch (IOException ex) {
	    	  Util.fail("Failed to load: "+ "gameresources/shoot.wav");
	      } catch (LineUnavailableException ex) {
	         ex.printStackTrace();
	      } finally {
	         soundLine.drain();
	         soundLine.close();
	      }
	}
	
   public static void main(String[] args) {
      new SpriteSound("gameresources/shoot.wav").play();
   }
}