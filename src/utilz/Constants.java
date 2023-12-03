 package utilz;

import main.Game;

public class Constants {
	
	public static class EnemyConstants {
		public  static final int CARNIVOROUS = 0;
		
		public static final int ATTACK = 0;
		public static final int DEAD = 1;
		public static final int RUNNING = 2;
		public static final int IDLE = 3;
		
		public static final int CARNIVOROUS_WIDTH_DEFAULT = 96;
		public static final int CARNIVOROUS_HEIGHT_DEFAULT = 98;
		
		public static final int CARNIVOROUS_WIDTH = (int)(CARNIVOROUS_WIDTH_DEFAULT * Game.SCALE);
		public static final int CARNIVOROUS_HEIGHT = (int)(CARNIVOROUS_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int CARNIVOROUS_DRAWOFFSET_X = (int)(68 * Game.SCALE);
		public static final int CARNIVOROUS_DRAWOFFSET_Y = (int)(65 * Game.SCALE);
		
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
				case DEAD:
					return 6;
				}
			}
			
			return 0;
		}
		public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
			case CARNIVOROUS:
				return 10;
			default:
				return 1;
			}
		}
		
		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
			case CARNIVOROUS:
				return 15;
			default:
				return 0;
			}
		}
		
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
