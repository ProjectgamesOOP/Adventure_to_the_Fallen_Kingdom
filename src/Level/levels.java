package Level;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Battle_Turtle;
import entities.Carnivorous;
import entities.Big_Bloated;
import main.Game;
// import static utilz.HelpMethods.GetLevelData;
// import static utilz.HelpMethods.GetCarnivorous;
// import static utilz.HelpMethods.GetBattleTurtle;
// import static utilz.HelpMethods.GetBigBloated;
// import static utilz.HelpMethods.GetPlayerSpawn;

import static utilz.Constants.EnemyConstants.*;

public class levels {

	private BufferedImage img;
	private ArrayList<Carnivorous> carnivorous = new ArrayList<>();
	private ArrayList<Battle_Turtle> battleTurtles = new ArrayList<>();
	private ArrayList<Big_Bloated> bigBloateds = new ArrayList<>();
	private int[][] lvlData;
	private int lvlTilesWide;
	private int maxTilesOffset;
	private int maxLvlOffsetX;
	private Point playerSpawn;

	public levels(BufferedImage img) {
		this.img = img;
		lvlData = new int[img.getHeight()][img.getWidth()];
		loadLevel();
		calcLvlOffsets();
		// createLevelData();
		// createEnemies();
		// calcPlayerSpawn();
	}
	//old
	// private void calcPlayerSpawn() {
	// 	playerSpawn = GetPlayerSpawn(img);
	// }

	//new
	private void loadLevel() {

		for (int y = 0; y < img.getHeight(); y++)
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
				int red = c.getRed();
				int green = c.getGreen();
				// int blue = c.getBlue();

				loadLevelData(red, x, y);
				loadEntities(green, x, y);
				// loadObjects(blue, x, y);
			}
	}

	private void loadLevelData(int redValue, int x, int y) {
		if (redValue >= 48)
			lvlData[y][x] = 0;
		else
			lvlData[y][x] = redValue;
	}

	//new
	private void loadEntities(int greenValue, int x, int y) {
		switch (greenValue) {
		case CARNIVOROUS: 
			carnivorous.add(new Carnivorous(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
			break;
		case BATTLE_TURTLE: 
			battleTurtles.add(new Battle_Turtle(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
			break;
		case BIG_BLOATED:
			bigBloateds.add(new Big_Bloated(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
			break;
		case 100: 
			playerSpawn = new Point(x * Game.TILES_SIZE, y * Game.TILES_SIZE);
			break;
		}
	}

	private void calcLvlOffsets() {
		lvlTilesWide = img.getWidth();
		maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
		maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
	}

	// private void createEnemies() {
	// 	Carnivorous = GetCarnivorous(img);
	// }

	public levels(int[][] lvlData) {
		this.lvlData = lvlData;
	}

	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}
	
	public int[][] getLevelData() {
		return lvlData;
	}

	public Point getPlayerSpawn() {
		return playerSpawn;
	}

	public int getLvlOffset() {
		return maxLvlOffsetX;
	}

	// private void createLevelData() {
	// 	lvlData = GetLevelData(img);
	// }

	public ArrayList<Carnivorous> getCarnivorous() {
		return carnivorous;
	}

	//new
	public ArrayList<Battle_Turtle> getBattleTurtle() {
		return battleTurtles;
	}
	
	//new
	public ArrayList<Big_Bloated> getBigBloateds() {
		return bigBloateds;
	}
}
