package entities;

import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.CanMoveHere;
import static utilz.HelpMethods.GetEntityYPosUnderRoofOrAboveFloor;
import static utilz.HelpMethods.IsEntityOnFloor;
import static utilz.HelpMethods.IsFloor;

import main.Game;

public class Carnivorous extends Enemy {

	public Carnivorous(float x, float y) {
		super(x, y, CARNIVOROUS_WIDTH, CARNIVOROUS_HEIGHT, CARNIVOROUS);
		initHitbox(x,y,(int)(22 * Game.SCALE),(int)(30 * Game.SCALE));
	}
	
	public void update(int[][] lvlData,Player player) {
		updateMove(lvlData,player);
		updateAnimationTick();
		
	}
	
	private void updateMove(int[][] lvlData,Player player) {
		if(firstUpdate) 
			firstUpdateCheck(lvlData);
		
		if(inAir) 
			updateInAir(lvlData);
		else {
			switch(enemyState) {
			case IDLE:
				newState(RUNNING);
				break;
			case RUNNING:
				
				if(canSeePlayer(lvlData,player))
					turnTowardsPlayer(player);
				if(isPlayerCloseForAttack(player))
					newState(ATTACK);
				
				move(lvlData);
				break;
			}
		}
		
	}
	

}
