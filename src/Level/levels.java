package Level;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Big_Bloated;
import entities.Carnivorous;
import entities.Turtle;
import main.Game;
import objects.Cannon;
import objects.GameContainer;
import objects.Potion;
import objects.Spike;
import utilz.HelpMethods;

import static utilz.HelpMethods.*;

public class levels {

	private BufferedImage img;
	private int[][] lvlData;
	private int lvlTilesWide;
	private int maxTilesOffset;
	private int maxLvlOffsetX;
	private Point playerSpawn;

	private ArrayList<Carnivorous> Carnivorous;
	private ArrayList<Turtle> Turtle;
	private ArrayList<Big_Bloated> Big_Bloated;
	private ArrayList<Potion> potions;
	private ArrayList<GameContainer> containers;
	private ArrayList<Spike> spikes;
	private ArrayList<Cannon> cannons;
	
	public levels(BufferedImage img) {
		this.img = img;
		createLevelData();
		createEnemies();
		calcLvlOffsets();
		calcPlayerSpawn();
		createPotions();
		createContainers();
		createSpikes();
		createCannons();
	}
	private void createCannons() {
		cannons = HelpMethods.GetCannons(img);
	}
		
		
	
	private void createSpikes() {
		spikes = HelpMethods.GetSpikes(img);
		
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
		Turtle = GetTurtle(img);
		Big_Bloated = GetBigBloated(img);
	}
	
	public ArrayList<Carnivorous> getCarnivorous() {
		return Carnivorous;
	}
	public ArrayList<Turtle> getTurtle() {
		return Turtle;
	}
	public ArrayList<Big_Bloated> getBigBloated() {
		return Big_Bloated;
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
	public ArrayList<Potion> getPotions() {
		return potions;
	}
	
	private void createPotions() {
		potions = HelpMethods.GetPotions(img);
	}
	
	private void createContainers() {
		containers = HelpMethods.GetContainers(img);
	}
	
	public ArrayList<GameContainer> getContainers() {
		return containers;
	}
	public ArrayList<Spike> getSpikes() {
		return spikes;
	}
	public ArrayList<Cannon> getCannons(){
		return cannons;
	}
	
}
