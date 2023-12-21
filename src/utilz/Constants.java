 package utilz;

import main.Game;

public class Constants {
	
	public static final float GRAVITY = 0.04f * Game.SCALE;
	public static final int ANI_SPEED = 25;
	
	public static class Projectiles{
		public static final int CANNON_BALL_DEFAULT_WIDTH = 15;
		public static final int CANNON_BALL_DEFAULT_HEIGHT = 15;
		
		public static final int CANNON_BALL_WIDTH = (int)(Game.SCALE * CANNON_BALL_DEFAULT_WIDTH);
		public static final int CANNON_BALL_HEIGHT = (int)(Game.SCALE * CANNON_BALL_DEFAULT_HEIGHT);
		public static final float SPEED = 0.5f * Game.SCALE;
	}
	
	public static class ObjectConstants {

		public static final int RED_POTION = 0;
		public static final int BLUE_POTION = 1;
		public static final int BARREL = 2;
		public static final int BOX = 3;
		public static final int SPIKE = 4;
		public static final int CANNON_LEFT = 5;
		public static final int CANNON_RIGHT = 6;		
		
		
		public static final int RED_POTION_VALUE = 15;
		public static final int BLUE_POTION_VALUE = 10;

		public static final int CONTAINER_WIDTH_DEFAULT = 40;
		public static final int CONTAINER_HEIGHT_DEFAULT = 30;
		public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
		public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);

		public static final int POTION_WIDTH_DEFAULT = 12;
		public static final int POTION_HEIGHT_DEFAULT = 16;
		public static final int POTION_WIDTH = (int) (Game.SCALE * POTION_WIDTH_DEFAULT);
		public static final int POTION_HEIGHT = (int) (Game.SCALE * POTION_HEIGHT_DEFAULT);

		public static final int SPIKE_WIDTH_DEFAULT = 32;
		public static final int SPIKE_HEIGHT_DEFAULT = 32;
		public static final int SPIKE_WIDTH = (int) (Game.SCALE * SPIKE_WIDTH_DEFAULT);
		public static final int SPIKE_HEIGHT = (int) (Game.SCALE * SPIKE_HEIGHT_DEFAULT);
		
		public static final int CANNON_WIDTH_DEFAULT = 40;
		public static final int CANNON_HEIGHT_DEFAULT = 26;
		public static final int CANNON_WIDTH = (int) (CANNON_WIDTH_DEFAULT * Game.SCALE);
		public static final int CANNON_HEIGHT = (int) (CANNON_HEIGHT_DEFAULT * Game.SCALE);
		
		public static int GetSpriteAmount(int object_type) {
			switch (object_type) {
			case RED_POTION, BLUE_POTION:
				return 7;
			case BARREL, BOX:
				return 8;
			case CANNON_LEFT, CANNON_RIGHT:
				return 7;
			}
			return 1;
		}
	}

	
	
	
	public static class EnemyConstants {
		public  static final int CARNIVOROUS = 0;
		public  static final int TURTLE = 1;
		public  static final int BIG_BLOATED = 2;

		
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;

		
		public static final int CARNIVOROUS_WIDTH_DEFAULT = 96;
		public static final int CARNIVOROUS_HEIGHT_DEFAULT = 98;
		public static final int CARNIVOROUS_WIDTH = (int)(CARNIVOROUS_WIDTH_DEFAULT * Game.SCALE);
		public static final int CARNIVOROUS_HEIGHT = (int)(CARNIVOROUS_HEIGHT_DEFAULT * Game.SCALE);
		public static final int CARNIVOROUS_DRAWOFFSET_X = (int)(68 * Game.SCALE);
		public static final int CARNIVOROUS_DRAWOFFSET_Y = (int)(65 * Game.SCALE);
		
		public static final int TURTLE_WIDTH_DEFAULT = 72;
		public static final int TURTLE_HEIGHT_DEFAULT = 74;
		public static final int TURTLE_WIDTH = (int)(TURTLE_WIDTH_DEFAULT * Game.SCALE);
		public static final int TURTLE_HEIGHT = (int)(TURTLE_HEIGHT_DEFAULT * Game.SCALE);
		public static final int TURTLE_DRAWOFFSET_X = (int)(68 * Game.SCALE);
		public static final int TURTLE_DRAWOFFSET_Y = (int)(15 * Game.SCALE);
		
		
		public static final int BIG_BLOATED_WIDTH_DEFAULT = 72;
		public static final int BIG_BLOATED_HEIGHT_DEFAULT = 74;
		public static final int BIG_BLOATED_WIDTH = (int)(BIG_BLOATED_WIDTH_DEFAULT * Game.SCALE);
		public static final int BIG_BLOATED_HEIGHT = (int)(BIG_BLOATED_HEIGHT_DEFAULT * Game.SCALE);
		public static final int BIG_BLOATED_DRAWOFFSET_X = (int)(68 * Game.SCALE);
		public static final int BIG_BLOATED_DRAWOFFSET_Y = (int)(15 * Game.SCALE);
		

		
		public static int GetSpriteAmount(int enemy_type, int enemy_state) {
			
			switch(enemy_type) {
			case CARNIVOROUS:
				switch(enemy_state) {
				case IDLE:
					return 6;
				case RUNNING:
					return 6;
				case ATTACK:
					return 6;
				case HIT:
					return 4;
				case DEAD:
					return 6;
				}
			case TURTLE:
				switch(enemy_state) {
				case IDLE:
					return 4;
				case RUNNING:
					return 4;
				case ATTACK:
					return 4;
				case HIT:
					return 2;
				case DEAD:
					return 4;
				}
			case BIG_BLOATED:
				switch(enemy_state) {
				case IDLE:
					return 4;
				case RUNNING:
					return 6;
				case ATTACK:
					return 6;
				case HIT:
					return 2;
				case DEAD:
					return 4;
				}
			}
			
			
			return 0;
		}
		public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
			case CARNIVOROUS:
				return 20;
			case TURTLE:
				return 15;
			case BIG_BLOATED:
				return 30;
			default:
				return 1;
			}
		}
		
		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
			case CARNIVOROUS:
				return 10;
			case TURTLE:
				return 5;
			case BIG_BLOATED:
				return 15;
			default:
				return 0;
			}
		}
		
	}

	//Environment Class for the clouds constants
	public static class Environment {
		public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
		public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;
		
		public static final int SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
	}

	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}

		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}

		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

		}

		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
		}
	}
    
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class PlayerConstants{
		public static final int RUSH = 0;
		public static final int ATTACK = 1;
		public static final int GROUND = 2;
		public static final int DEAD = 3;
		public static final int FALLING = 4;
		public static final int HIT = 5;
		public static final int IDLE = 6;
		public static final int JUMP = 7;
		public static final int RUNNING = 8;
		

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
				
				case RUNNING:
					return 10;
				case IDLE:
					return 10;
				case HIT:
					return 1;
				case JUMP:
					return 3;
				case GROUND:
					return 1;
				case FALLING:
					return 3;
				case ATTACK:
					return 10;
				case DEAD:
					return 10;
				case RUSH:
					return 2;
				default:
					return 1;
			}
		}
	}
}
