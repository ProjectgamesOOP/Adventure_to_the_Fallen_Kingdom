package entities;

import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.EnemyConstants.*;

import java.awt.geom.Rectangle2D;

import main.Game;
public class Turtle extends Enemy {

	private int attackBoxOffsetX;

	public Turtle(float x, float y) {
		super(x, y, TURTLE_WIDTH, TURTLE_HEIGHT, TURTLE);
		initHitbox(22,30);
		initAttackBox();
	}

	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x,y,(int)(82 * Game.SCALE),(int)(30 * Game.SCALE));
		attackBoxOffsetX = (int)(Game.SCALE * 30);		
	}


	
	public void update(int[][] lvlData,Player player) {
		updateBehavior(lvlData,player);
		updateAnimationTick();
		updateAttackBox();
		
	}
	
	private void updateAttackBox() {
		attackBox.x = hitbox.x - attackBoxOffsetX;
		attackBox.y = hitbox.y;
		
	}

	private void updateBehavior(int[][] lvlData,Player player) {
		if(firstUpdate) 
			firstUpdateCheck(lvlData);
		
		if(inAir) 
			updateInAir(lvlData);
		else {
			switch(state) {
			case IDLE:
				newState(RUNNING);
				break;
			case RUNNING:
				if(canSeePlayer(lvlData,player)) {
					turnTowardsPlayer(player);
				if(isPlayerCloseForAttack(player))
					newState(ATTACK);
				}
				
				move(lvlData);
				break;
			case ATTACK:
				if(aniIndex == 0)
					attackChecked = false;
				
				if(aniIndex == 2 && !attackChecked)
					checkPlayerHit(attackBox,player);
				break;
			}
		}
		
	}
	
	public int flipX() {
		if(walkDir == RIGHT)
			return width;
		else
			return 0;
	}
	
	public int flipW() {
		if(walkDir == RIGHT)
			return -1;
		else 
			return 1;
	}

}
