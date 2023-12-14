package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;

import Level.levels;
import gamestates.Playing;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
	
	private Playing playing;
	private BufferedImage[][] carnivorousArr, battleTurtleArr, bigBloatedArr;
	private levels currentLevel;
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}
	
	public void loadEnemies(levels Levels) {
		this.currentLevel = Levels;	
	}

	public void update(int[][] lvlData, Player player) {
		boolean isAnyActive = false;
		for (Carnivorous c : currentLevel.getCarnivorous())
			if (c.isActive()) {
				c.update(lvlData, player);
				isAnyActive = true;
			}
		
		for (Battle_Turtle t : currentLevel.getBattleTurtle())
			if (t.isActive()) {
				t.update(lvlData, player);
				isAnyActive = true;
			}
		
		for (Big_Bloated b: currentLevel.getBigBloateds())
			if (b.isActive()) {
				b.update(lvlData, player);
				isAnyActive = true;
			}

		if(!isAnyActive)
			playing.setLevelCompleted(true);
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawCarnivorous(g, xLvlOffset);
		drawBattleTurtle(g, xLvlOffset);
		drawBigBloated(g, xLvlOffset);
	}
	
	private void drawCarnivorous(Graphics g, int xLvlOffset) {
		for(Carnivorous c : currentLevel.getCarnivorous()) 
			if (c.isActive()) {
				g.drawImage(carnivorousArr[c.getState()][c.getAniIndex()], 
					(int)c.getHitbox().x - xLvlOffset - CARNIVOROUS_DRAWOFFSET_X + c.flipX() + 20 , 
					(int)c.getHitbox().y - CARNIVOROUS_DRAWOFFSET_Y,
					CARNIVOROUS_WIDTH * c.flipW() , CARNIVOROUS_HEIGHT, null);
//		        c.drawHitbox(g,xLvlOffset);
//				c.drawAttackBox(g, xLvlOffset);
			}
		
	}

	private void drawBattleTurtle(Graphics g, int xLvlOffset) {
		for(Battle_Turtle t : currentLevel.getBattleTurtle()) 
			if (t.isActive()) {
				g.drawImage(battleTurtleArr[t.getState()][t.getAniIndex()], 
					(int)t.getHitbox().x - xLvlOffset - BATTLE_TURTLE_DRAWOFFSET_X + t.flipX() + 20 , 
					(int)t.getHitbox().y - BATTLE_TURTLE_DRAWOFFSET_Y,
					BATTLE_TURTLE_WIDTH * t.flipW() , BATTLE_TURTLE_HEIGHT, null);
//		        t.drawHitbox(g,xLvlOffset);
//				t.drawAttackBox(g, xLvlOffset);
			}
		
	}

	private void drawBigBloated(Graphics g, int xLvlOffset) {
		for(Big_Bloated b : currentLevel.getBigBloateds()) 
			if (b.isActive()) {
				g.drawImage(bigBloatedArr[b.getState()][b.getAniIndex()], 
					(int)b.getHitbox().x - xLvlOffset - BATTLE_TURTLE_DRAWOFFSET_X + b.flipX() + 20 , 
					(int)b.getHitbox().y - BATTLE_TURTLE_DRAWOFFSET_Y,
					BATTLE_TURTLE_WIDTH * b.flipW() , BATTLE_TURTLE_HEIGHT, null);
//		        b.drawHitbox(g,xLvlOffset);
//				b.drawAttackBox(g, xLvlOffset);
			}
		
	}
	
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Carnivorous c : currentLevel.getCarnivorous())
			if (c.isActive()) {
				if (c.getState() != DEAD && c.getState() != HIT)
					if (attackBox.intersects(c.getHitbox())) {
						c.hurt(20);
						return;
					}
			}
		
		for (Battle_Turtle t : currentLevel.getBattleTurtle())
			if (t.isActive()) {
				if (t.getState() != DEAD && t.getState() != HIT)
					if (attackBox.intersects(t.getHitbox())) {
						t.hurt(20);
						return;
					}
			}
		
		for (Big_Bloated b : currentLevel.getBigBloateds())
			if (b.isActive()) {
				if (b.getState() != DEAD && b.getState() != HIT)
					if (attackBox.intersects(b.getHitbox())) {
						b.hurt(20);
						return;
					}
			}
	}
	    
	private void loadEnemyImgs() {
		carnivorousArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.CARNIVOROUS_SPRITE), 6, 5, CARNIVOROUS_WIDTH_DEFAULT, CARNIVOROUS_HEIGHT_DEFAULT);
		battleTurtleArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.BATTLE_TURTLE_SPRITE), 4, 5, BATTLE_TURTLE_WIDTH_DEFAULT, BATTLE_TURTLE_HEIGHT_DEFAULT);
		bigBloatedArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.BIG_BLOATED_SPRITE),6, 5, BIG_BLOATED_WIDTH_DEFAULT, BIG_BLOATED_HEIGHT_DEFAULT);
	}

	private BufferedImage[][] getImgArr(BufferedImage atlas, int xSize, int ySize, int spriteW, int spriteH) {
		BufferedImage[][] tempArr = new BufferedImage[ySize][xSize];
		for (int j = 0; j < tempArr.length; j++)
			for (int i = 0; i < tempArr[j].length; i++)
				tempArr[j][i] = atlas.getSubimage(i * spriteW, j * spriteH, spriteW, spriteH);
		return tempArr;
	}

	public void resetAllEnemies() {
		for (Carnivorous c : currentLevel.getCarnivorous())
			c.resetEnemy();
		for (Battle_Turtle t : currentLevel.getBattleTurtle())
			t.resetEnemy();
		for (Big_Bloated b: currentLevel.getBigBloateds())
			b.resetEnemy();
	}

}
