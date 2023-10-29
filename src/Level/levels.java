package Level;

public class levels {

	private int[][] lvlData;

	public levels(int[][] lvlData) {
		this.lvlData = lvlData;
	}

	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}

}
