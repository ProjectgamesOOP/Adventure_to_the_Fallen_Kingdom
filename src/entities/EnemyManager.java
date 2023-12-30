package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Level.levels;
import gamestates.Playing;
import objects.Projectile;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
	
	private Playing playing;
	private BufferedImage[][] carnivorousArr, turtleArr, bigbloatedArr;
	private ArrayList<Carnivorous> carnivorous = new ArrayList<>();
	private ArrayList<Turtle> turtle = new ArrayList<>();
	private ArrayList<Big_Bloated> big_bloated = new ArrayList<>();
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}

	public void loadEnemies(levels Levels) {
		carnivorous = Levels.getCarnivorous();
		turtle = Levels.getTurtle();
		big_bloated = Levels.getBigBloated();
		
	}

	public void update(int[][] lvlData,Player player) {
		boolean isAnyActive = false;
		for(Carnivorous c : carnivorous)
			if(c.isActive()) {
				c.update(lvlData,player);
				isAnyActive = true;
			}
		for(Turtle c : turtle)
			if(c.isActive()) {
				c.update(lvlData,player);
				isAnyActive = true;
			}
		for(Big_Bloated b : big_bloated)
			if(b.isActive()) {
				b.update(lvlData,player);
				isAnyActive = true;
			}
		if(!isAnyActive)
			playing.setLevelCompleted(true);
	}
	
	public void draw(Graphics g, int xLvlOffset) {
		drawCarnivorous(g, xLvlOffset);
		drawTurtle(g, xLvlOffset);
		drawBigBloated(g, xLvlOffset);
	}
	
	private void drawCarnivorous(Graphics g, int xLvlOffset) {
		for(Carnivorous c : carnivorous) 
			if (c.isActive()) {
				g.drawImage(carnivorousArr[c.getEnemyState()][c.getAniIndex()], 
					(int)c.getHitbox().x - xLvlOffset - CARNIVOROUS_DRAWOFFSET_X + c.flipX() + 20 , 
					(int)c.getHitbox().y - CARNIVOROUS_DRAWOFFSET_Y,
					CARNIVOROUS_WIDTH * c.flipW() , CARNIVOROUS_HEIGHT, null);
//		        c.drawHitbox(g,xLvlOffset);
//				c.drawAttackBox(g, xLvlOffset);
			}
		
	}
	private void drawTurtle(Graphics g, int xLvlOffset) {
		for(Turtle t : turtle) 
			if (t.isActive()) {
				g.drawImage(turtleArr[t.getEnemyState()][t.getAniIndex()], 
					(int)t.getHitbox().x - xLvlOffset - TURTLE_DRAWOFFSET_X + t.flipX() + 40, 
					(int)t.getHitbox().y - TURTLE_DRAWOFFSET_Y - 25 ,
					TURTLE_WIDTH * t.flipW(), TURTLE_HEIGHT, null);
//		        t.drawHitbox(g,xLvlOffset);
//				t.drawAttackBox(g, xLvlOffset);
			}
		
	}
	private void drawBigBloated(Graphics g, int xLvlOffset) {
		for(Big_Bloated b : big_bloated) 
			if (b.isActive()) {
				g.drawImage(bigbloatedArr[b.getEnemyState()][b.getAniIndex()], 
					(int)b.getHitbox().x - xLvlOffset - BIG_BLOATED_DRAWOFFSET_X + b.flipX() + 40, 
					(int)b.getHitbox().y - BIG_BLOATED_DRAWOFFSET_Y - 25,
					BIG_BLOATED_WIDTH * b.flipW(), BIG_BLOATED_HEIGHT, null);
//		        b.drawHitbox(g,xLvlOffset);
//				b.drawAttackBox(g, xLvlOffset);
			}
		
	}
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for(Carnivorous c : carnivorous) 
			if(c.isActive())
				if(attackBox.intersects(c.getHitbox())) {
					c.hurt(10);
					return;
			}
		for(Turtle t : turtle) 
			if(t.isActive())
				if(attackBox.intersects(t.getHitbox())) {
					t.hurt(10);
					return;
			}
		for(Big_Bloated b : big_bloated) 
			if(b.isActive())
				if(attackBox.intersects(b.getHitbox())) {
					b.hurt(10);
					return;
			}
		
	}

	private void loadEnemyImgs() {
		carnivorousArr = new BufferedImage[5][6];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CARNIVOROUS_SPRITE);
		for(int j = 0; j < carnivorousArr.length; j++ )
			for(int i = 0; i < carnivorousArr[j].length; i++)
				carnivorousArr[j][i] = temp.getSubimage(i * CARNIVOROUS_WIDTH_DEFAULT, j * CARNIVOROUS_HEIGHT_DEFAULT, CARNIVOROUS_WIDTH_DEFAULT, CARNIVOROUS_HEIGHT_DEFAULT);
		
		turtleArr = new BufferedImage[5][4];
		BufferedImage temp1 = LoadSave.GetSpriteAtlas(LoadSave.TURTLE_SPRITE);
		for(int j = 0; j < turtleArr.length; j++ )
			for(int i = 0; i < turtleArr[j].length; i++)
				turtleArr[j][i] = temp1.getSubimage(i * TURTLE_WIDTH_DEFAULT, j * TURTLE_HEIGHT_DEFAULT, TURTLE_WIDTH_DEFAULT, TURTLE_HEIGHT_DEFAULT);
		
		bigbloatedArr = new BufferedImage[5][6];
		BufferedImage temp2 = LoadSave.GetSpriteAtlas(LoadSave.BIG_BLOATED_SPRITE);
		for(int j = 0; j < bigbloatedArr.length; j++ )
			for(int i = 0; i < bigbloatedArr[j].length; i++)
				bigbloatedArr[j][i] = temp2.getSubimage(i * BIG_BLOATED_WIDTH_DEFAULT, j *BIG_BLOATED_HEIGHT_DEFAULT, BIG_BLOATED_WIDTH_DEFAULT, BIG_BLOATED_HEIGHT_DEFAULT);
	}
	
	public void resetAllEnemies() {
		for(Carnivorous c : carnivorous) 
			c.resetEnemy();
		for(Turtle t : turtle) 
			t.resetEnemy();
		for(Big_Bloated b : big_bloated) 
			b.resetEnemy();
		
	}

}
