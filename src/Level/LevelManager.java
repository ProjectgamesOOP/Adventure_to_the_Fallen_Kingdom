package Level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Gamestate;
import main.Game;
import utilz.LoadSave;

public class LevelManager {

	private Game game;
	private BufferedImage[] levelSprite;
	private ArrayList<levels> Levels;
	private int lvlIndex = 0;

	public LevelManager(Game game) {
		this.game = game;
		importOutsideSprites();
		Levels = new ArrayList<>();
		buildAllLevels();
	}

	private void buildAllLevels() {
		BufferedImage[] allLevels = LoadSave.GetAllLevels();
		for (BufferedImage img : allLevels)
			Levels.add(new levels(img));
		
	}

	private void importOutsideSprites() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48];
		for (int j = 0; j < 4; j++)
			for (int i = 0; i < 12; i++) {
				int index = j * 12 + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
	}

	public void draw(Graphics g, int xLvlOffset) {
		for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
			for (int i = 0; i < Levels.get(lvlIndex).getLevelData()[0].length; i++) {
				int index = Levels.get(lvlIndex).getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], Game.TILES_SIZE * i - xLvlOffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
	}
	public void loadNextLevel() {
		lvlIndex++;
		if (lvlIndex >= Levels.size()) {
			lvlIndex = 0;
			System.out.println("No more levels! Game Completed!");
			Gamestate.state = Gamestate.MENU;
		}

		levels newLevel = Levels.get(lvlIndex);
		game.getPlaying().getEnemyManager().loadEnemies(newLevel);
		game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
		game.getPlaying().setMaxLvlOffset(newLevel.getLvlOffset());
	}
	
	public void update() {

	}
	
	public levels getCurrentLevel() {
		return Levels.get(lvlIndex);
	}
	
	public int getAmountOfLevels() {
		return Levels.size();
	}

}