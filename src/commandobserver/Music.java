package commandobserver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Music implements ActionListener {


	@Override
	public final void actionPerformed(ActionEvent arg0) {
		sound();
	}
	
	// Creating a Method Require to play the music
	private static void sound() {

		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;

		try{
			BGM = new AudioStream(new FileInputStream("newhorizon.wav"));
			MD = BGM.getData();loop = new ContinuousAudioDataStream(MD);
		}
		catch(IOException Error){
			System.out.println("cant find the audio file");
		}

		MGP.start(loop);

	}

}


