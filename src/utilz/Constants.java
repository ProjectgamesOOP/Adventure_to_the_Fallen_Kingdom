 package utilz;

import main.Game;

public class Constants {
	
	public static class EnemyConstants {
		public  static final int CARNIVOROUS = 0;
		public  static final int BATTLE_TURTLE = 1;
		public  static final int BIG_BLOATED = 2;
		
		// public static final int ATTACK = 0;
		// public static final int DEAD = 1;
		// public static final int RUNNING = 2;
		// public static final int IDLE = 3;

		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;
		
		public static final int CARNIVOROUS_WIDTH_DEFAULT = 96;
		public static final int CARNIVOROUS_HEIGHT_DEFAULT = 98;

		//Enemy 2
		public static final int BATTLE_TURTLE_WIDTH_DEFAULT = 72;
		public static final int BATTLE_TURTLE_HEIGHT_DEFAULT = 74;

		//Enemy 3
		public static final int BIG_BLOATED_WIDTH_DEFAULT = 72;
		public static final int BIG_BLOATED_HEIGHT_DEFAULT = 74;
		
		public static final int CARNIVOROUS_WIDTH = (int)(CARNIVOROUS_WIDTH_DEFAULT * Game.SCALE);
		public static final int CARNIVOROUS_HEIGHT = (int)(CARNIVOROUS_HEIGHT_DEFAULT * Game.SCALE);

		public static final int BATTLE_TURTLE_WIDTH = (int)(BATTLE_TURTLE_WIDTH_DEFAULT * Game.SCALE);
		public static final int BATTLE_TURTLE_HEIGHT = (int)(BATTLE_TURTLE_HEIGHT_DEFAULT * Game.SCALE);

		public static final int BIG_BLOATED_WIDTH = (int)(BIG_BLOATED_WIDTH_DEFAULT * Game.SCALE);
		public static final int BIG_BLOATED_HEIGHT = (int)(BIG_BLOATED_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int CARNIVOROUS_DRAWOFFSET_X = (int)(68 * Game.SCALE);
		public static final int CARNIVOROUS_DRAWOFFSET_Y = (int)(65 * Game.SCALE);

		public static final int BATTLE_TURTLE_DRAWOFFSET_X = (int)(68 * Game.SCALE);
		public static final int BATTLE_TURTLE_DRAWOFFSET_Y = (int)(65 * Game.SCALE);

		public static final int BIG_BLOATED_DRAWOFFSET_X = (int)(68 * Game.SCALE);
		public static final int BIG_BLOATED_DRAWOFFSET_Y = (int)(65 * Game.SCALE);
		
		
		public static int GetSpriteAmount(int enemy_type, int enemy_state) {
			switch (enemy_state) {

			case IDLE: {
				if (enemy_type == CARNIVOROUS)
					return 6;
				else if (enemy_type == BATTLE_TURTLE || enemy_type == BIG_BLOATED)
					return 4;
			}
			case RUNNING: {
				if (enemy_type == BATTLE_TURTLE)
					return 4;
				else if (enemy_type == CARNIVOROUS || enemy_type == BIG_BLOATED)
					return 6;
			}
			case ATTACK: {
				if (enemy_type == BATTLE_TURTLE)
					return 4;
				else if (enemy_type == CARNIVOROUS || enemy_type == BIG_BLOATED)
					return 6;
			}
			case HIT: {
				if (enemy_type == CARNIVOROUS)
					return 4;
				else if (enemy_type == BATTLE_TURTLE || enemy_type == BIG_BLOATED)
					return 2;
			}
			case DEAD: {
				if (enemy_type == CARNIVOROUS)
					return 6;
				else if (enemy_type == BATTLE_TURTLE || enemy_type == BIG_BLOATED)
					return 4;
			}
			}

			return 0;

		}
		public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
			case CARNIVOROUS:
				return 10;
			case BATTLE_TURTLE:
				return 20;
			default:
				return 1;
			}
		}
		
		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
			case CARNIVOROUS:
				return 15;
			case BATTLE_TURTLE:
				return 20;
			default:
				return 0;
			}
		}
		
	}

	public static class Environment {
		public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
		public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;
		public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
		public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;
		
		public static final int BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
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
