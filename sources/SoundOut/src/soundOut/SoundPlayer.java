package soundOut;
// play sounds on the speaker given an amplitude vector[]

import sun.audio.*;

public class SoundPlayer {

  private java.io.InputStream soundStream;
  private boolean mute=true;
  private byte sound[]=null;
  private double volume=1.0;
  private double[] yVec;
  //AudioDataStream ap = new AudioDataStream();


  public SoundPlayer() {
      this(100,false);
  }

  public SoundPlayer(int num, boolean oneShot) {
      sound=new byte[num];
      if(oneShot) soundStream = new AudioDataStream(new AudioData(sound));
      else soundStream = new ContinuousAudioDataStream(new AudioData(sound));
  }

  public void setYVec(double[] v){
      yVec=v;
      calcSound();
  }

  public void closeStream(){
      try{soundStream.close();}catch (Exception e){}
  }

  public synchronized void calcSound() {
    double y=0;
    if (!mute) AudioPlayer.player.stop(soundStream);
    int numPts=yVec.length;
    if ((sound==null) ||(numPts!=sound.length)){
        sound = new  byte[numPts];
        soundStream = new ContinuousAudioDataStream(new AudioData(sound));
    }
    for (int i = 0; i < numPts; i++)
    {
        y=volume*8160*yVec[i];
        sound[i] = int2ulaw((int)(y));
    }
    if (!mute) AudioPlayer.player.start(soundStream);
  }

  public void setVolume(double v){
    volume=v;
    calcSound();
  }
  public double getVolume(){
    return volume;
  }

  public void setMute(boolean m){
    mute=m;
    if (mute ){
      AudioPlayer.player.stop(soundStream);
    }
    else{
      AudioPlayer.player.start(soundStream);
    }
  }

  public static byte int2ulaw(int ch) {
    int mask;

    if (ch < 0) {
      ch = -ch;
      mask = 0x7f;
    }
    else {
      mask = 0xff;
    }
    //
    if (ch < 32) {
      ch = 0xF0 | 15 - (ch/2);
    }
    else
      if (ch < 96) {
	ch = 0xE0 | 15 - (ch-32)/4;
      }
      else
	if (ch < 224) {
	  ch = 0xD0 | 15 - (ch-96)/8;
	}
	else
	  if (ch < 480) {
	    ch = 0xC0 | 15 - (ch-224)/16;
	  }
	  else
	    if (ch < 992 ) {
	      ch = 0xB0 | 15 - (ch-480)/32;
	    }
	    else
	      if (ch < 2016) {
		ch = 0xA0 | 15 - (ch-992)/64;
	      }
	      else
		if (ch < 4064) {
		  ch = 0x90 | 15 - (ch-2016)/128;
		}
		else
		  if (ch < 8160) {
		    ch = 0x80 | 15 - (ch-4064)/256;
		  }
		  else {
		    ch = 0x80;
		  }
    return (byte)(mask & ch);
  }
}