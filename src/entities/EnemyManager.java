package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
	
	private Playing playing;
	private BufferedImage[][] carnivorousArr;
	private ArrayList<Carnivorous> carnivorous = new ArrayList<>();
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}
	
	private void addEnemies() {
		carnivorous = LoadSave.GetCarnivorous();
		
	}

	public void update(int[][] lvlData,Player player) {
		for(Carnivorous c : carnivorous)
			c.update(lvlData,player);
	}
	
	public void draw(Graphics g, int xLvlOffset) {
		drawCarnivorous(g, xLvlOffset);
	}
	
	private void drawCarnivorous(Graphics g, int xLvlOffset) {
		for(Carnivorous c : carnivorous)
			g.drawImage(carnivorousArr[c.getEneymyState()][c.getAniIndex()], (int)(c.getHitbox().x - CARNIVOROUS_DRAWOFFSET_X) - xLvlOffset , (int)c.getHitbox().y - CARNIVOROUS_DRAWOFFSET_Y, CARNIVOROUS_WIDTH, CARNIVOROUS_HEIGHT, null);
//		    c.drawHitbox(g,xLvlOffset);
		
	}

	private void loadEnemyImgs() {
		carnivorousArr = new BufferedImage[4][6];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CARNIVOROUS_SPRITE);
		for(int j = 0; j < carnivorousArr.length; j++ )
			for(int i = 0; i < carnivorousArr[j].length; i++)
				carnivorousArr[j][i] = temp.getSubimage(i * CARNIVOROUS_WIDTH_DEFAULT, j * CARNIVOROUS_HEIGHT_DEFAULT, CARNIVOROUS_WIDTH_DEFAULT, CARNIVOROUS_HEIGHT_DEFAULT);
		
	}

}
