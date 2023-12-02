package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Level.levels;
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
	}
	
	public void loadEnemies(levels Levels) {
		carnivorous = Levels.getCarnivorous();
		
	}

	public void update(int[][] lvlData,Player player) {
		boolean isAnyActive = false;
		for(Carnivorous c : carnivorous)
			if(c.isActive()) {
				c.update(lvlData,player);
				isAnyActive = true;
			}
		if(!isAnyActive)
			playing.setLevelCompleted(true);
	}
	
	public void draw(Graphics g, int xLvlOffset) {
		drawCarnivorous(g, xLvlOffset);
	}
	
	private void drawCarnivorous(Graphics g, int xLvlOffset) {
		for(Carnivorous c : carnivorous) 
			if (c.isActive()) {
				g.drawImage(carnivorousArr[c.getEneymyState()][c.getAniIndex()], 
					(int)c.getHitbox().x - xLvlOffset - CARNIVOROUS_DRAWOFFSET_X + c.flipX() + 20 , 
					(int)c.getHitbox().y - CARNIVOROUS_DRAWOFFSET_Y,
					CARNIVOROUS_WIDTH * c.flipW() , CARNIVOROUS_HEIGHT, null);
//		        c.drawHitbox(g,xLvlOffset);
//				c.drawAttackBox(g, xLvlOffset);
			}
		
	}
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for(Carnivorous c : carnivorous) 
			if(c.isActive())
				if(attackBox.intersects(c.getHitbox())) {
					c.hurt(10);
					return;
			}
		
	}

	private void loadEnemyImgs() {
		carnivorousArr = new BufferedImage[4][6];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CARNIVOROUS_SPRITE);
		for(int j = 0; j < carnivorousArr.length; j++ )
			for(int i = 0; i < carnivorousArr[j].length; i++)
				carnivorousArr[j][i] = temp.getSubimage(i * CARNIVOROUS_WIDTH_DEFAULT, j * CARNIVOROUS_HEIGHT_DEFAULT, CARNIVOROUS_WIDTH_DEFAULT, CARNIVOROUS_HEIGHT_DEFAULT);
		
	}
	
	public void resetAllEnemies() {
		for(Carnivorous c : carnivorous) 
			c.resetEnemy();
		
	}

}
