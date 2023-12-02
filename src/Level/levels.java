package Level;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Carnivorous;
import main.Game;
import static utilz.HelpMethods.GetLevelData;
import static utilz.HelpMethods.GetCarnivorous;
import static utilz.HelpMethods.GetPlayerSpawn;

public class levels {

	private BufferedImage img;
	private ArrayList<Carnivorous> Carnivorous;
	private int[][] lvlData;
	private int lvlTilesWide;
	private int maxTilesOffset;
	private int maxLvlOffsetX;
	private Point playerSpawn;

	public levels(BufferedImage img) {
		this.img = img;
		createLevelData();
		createEnemies();
		calcLvlOffsets();
		calcPlayerSpawn();
	}
	private void calcPlayerSpawn() {
		playerSpawn = GetPlayerSpawn(img);
	}

	private void calcLvlOffsets() {
		lvlTilesWide = img.getWidth();
		maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
		maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
	}

	private void createEnemies() {
		Carnivorous = GetCarnivorous(img);
	}
	
	public ArrayList<Carnivorous> getCarnivorous() {
		return Carnivorous;
	}
	
	public int getLvlOffset() {
		return maxLvlOffsetX;
	}

	private void createLevelData() {
		lvlData = GetLevelData(img);
	}

	public Point getPlayerSpawn() {
		return playerSpawn;
	}

	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}
	
	public int[][] getLevelData() {
		return lvlData;
	}

}
