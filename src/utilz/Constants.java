package utilz;

public class Constants {
    
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class PlayerConstants{
		public static final int ATTACK = 0;
		public static final int DEAD = 1;
		public static final int DEFEND = 2;
		public static final int HURT = 3;
		public static final int IDLE = 4;
		public static final int RUNNING = 5;
		public static final int JUMP = 6;
		
		public static int GetSpriteAmount(int player_action) {
			
			switch(player_action) {
			
			case ATTACK:
				return 5;
			case DEAD:
				return 6;
			case DEFEND:
				return 5;
			case HURT:
				return 2;
			case IDLE:
				return 4;
			case RUNNING:
				return 6;
			case JUMP:
			default:
				return 7;
			}
		}
	}
}
