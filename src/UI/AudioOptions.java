package UI;

import gamestates.Gamestate;
import main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

import static utilz.Constants.UI.VolumeButtons.SLIDER_WIDTH;
import static utilz.Constants.UI.VolumeButtons.VOLUME_HEIGHT;

public class AudioOptions {
    private VolumeButton volumeButton;
    private SoundButton musicButton, sfxButton;
    private Game game;
    
    public AudioOptions(Game game) {
    	this.game = game;
        createSoundButtons();
        createVolumeButton();

    }

    private void createVolumeButton() {
        int vX = (int) (309 * Game.SCALE);
        int vY = (int) (278 * Game.SCALE);
        volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void createSoundButtons() {
        int soundX = (int) (440 * Game.SCALE);
        int musicY = (int) (120 * Game.SCALE);
        int sfxY = (int) (160 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, 60, 60);
        sfxButton = new SoundButton(soundX, sfxY, 60, 60);
    }

    public void update() {
        musicButton.update();
        sfxButton.update();
        volumeButton.update();
    }

    public void draw(Graphics g) {
        //Sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);

        // Volume Button
        volumeButton.draw(g);
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton))
            musicButton.setMousePressed(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMousePressed(true);
        else if (isIn(e, volumeButton))
            volumeButton.setMousePressed(true);
    }

    public void mouseDragged(MouseEvent e) {
    	if (volumeButton.isMousePressed()) {
			float valueBefore = volumeButton.getFloatValue();
			volumeButton.changeX(e.getX());
			float valueAfter = volumeButton.getFloatValue();
			if(valueBefore != valueAfter)
				game.getAudioPlayer().setVolume(valueAfter);
		}

    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
            if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());
                game.getAudioPlayer().toggleSongMute();
            }

        } else if (isIn(e, sfxButton)) {
            if (sfxButton.isMousePressed()) {
                sfxButton.setMuted(!sfxButton.isMuted());
                game.getAudioPlayer().toggleEffectMute();
            }
        }

        musicButton.resetBools();
        sfxButton.resetBools();
        volumeButton.resetBools();

    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        volumeButton.setMouseOver(false);

        if (isIn(e, musicButton))
            musicButton.setMouseOver(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMouseOver(true);
        else if (isIn(e, volumeButton))
            volumeButton.setMouseOver(true);
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}
