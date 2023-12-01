package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;

public class Carnivorous extends Enemy {

	public Carnivorous(float x, float y) {
		super(x, y, CARNIVOROUS_WIDTH, CARNIVOROUS_HEIGHT, CARNIVOROUS);
		initHitbox(x,y,(int)(22 * Game.SCALE),(int)(30 * Game.SCALE));
	}
	

}
