package gamestates;

import Level.LevelManager;
import UI.GameOverOverlay;
import UI.LevelCompleted;
import UI.PauseOverlay;
import entities.EnemyManager;
import entities.Player;
import main.Game;
import utilz.LoadSave;
import objects.ObjectManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import static utilz.Constants.Environment.*;


public class Playing extends State implements Statemethods {
    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private PauseOverlay pauseOverlay;
    private GameOverOverlay gameOverOverlay;
    private LevelCompleted levelCompleted;
    private ObjectManager objectManager;
    private boolean paused = false ;
    
    private int xLvlOffset;
	private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
	private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
	private int maxLvlOffsetX;
	
	//new modify for the multiple background
	public int currentLevelIndex = 0; 
	private BufferedImage backgroundImg, bigcloudImg, smallcloudImg;
	private int[] smallCloudsPos;
	private Random rnd = new Random();
	
	private boolean gameOver;
	private boolean lvlCompleted;
	

    public Playing(Game game) {
        super(game);
        initClasses();
        
		// Setting the background playing and all the environment in the background
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG_MAP1); // default background will be background1
		bigcloudImg = LoadSave.GetSpriteAtlas(LoadSave.WHITE_BIG_CLOUDS); // default big clouds
        smallcloudImg = LoadSave.GetSpriteAtlas(LoadSave.WHITE_SMALL_CLOUDS); // default small clouds
        smallCloudsPos = new int[8];
		for (int i = 0; i < smallCloudsPos.length; i++)
			smallCloudsPos[i] = (int) (90 * Game.SCALE) + rnd.nextInt((int) (100 * Game.SCALE));
        calcLvlOffset();
		loadStartLevel();
    }
    
    private void loadStartLevel() {
		enemyManager.loadEnemies(levelManager.getCurrentLevel());
		objectManager.loadObjects(levelManager.getCurrentLevel());
	}

	private void calcLvlOffset() {
		maxLvlOffsetX = levelManager.getCurrentLevel().getLvlOffset();
	}
	
	public void loadNextLevel() {
		// resetAll();
		levelManager.loadNextLevel();
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
		resetAll();

		// Set background based on the current level index
        switch (currentLevelIndex) {
            case 0:
                setBackgroundImage(LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG_MAP2));
                setBigCloudImage(LoadSave.GetSpriteAtlas(LoadSave.BLACK_BIG_CLOUDS));
                setSmallCloudImage(LoadSave.GetSpriteAtlas(LoadSave.BLACK_SMALL_CLOUDS));
                break;
            case 1:
                setBackgroundImage(LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG_MAP3));
                setBigCloudImage(LoadSave.GetSpriteAtlas(LoadSave.GREY_BIG_CLOUDS));
                setSmallCloudImage(LoadSave.GetSpriteAtlas(LoadSave.GREY_SMALL_CLOUDS));
                break;
            
        }
        
        currentLevelIndex++; // Increment the current level index for the next level
	}
	
    private void initClasses() {
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        objectManager = new ObjectManager(this);
        
        player = new Player(200, 200, (int) (140 * Game.SCALE), (int) (102 * Game.SCALE),this);
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
        
        pauseOverlay = new PauseOverlay(this);
        gameOverOverlay = new GameOverOverlay(this);
        levelCompleted = new LevelCompleted(this);
    }

    @Override
    public void update() {
    	if (paused) {
			pauseOverlay.update();
		} else if (lvlCompleted) {
			levelCompleted.update();
		} else if (!gameOver) {
			levelManager.update();
			objectManager.update();
			player.update();
			enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
			checkCloseToBorder();
		}
        
    }

    private void checkCloseToBorder() {
    	int playerX = (int) player.getHitbox().x;
		int diff = playerX - xLvlOffset;

		if (diff > rightBorder)
			xLvlOffset += diff - rightBorder;
		else if (diff < leftBorder)
			xLvlOffset += diff - leftBorder;

		if (xLvlOffset > maxLvlOffsetX)
			xLvlOffset = maxLvlOffsetX;
		else if (xLvlOffset < 0)
			xLvlOffset = 0;
		
	}

	//setBackgroundImage() method
    public void setBackgroundImage(BufferedImage backgroundImage) {
        // Set the background image for the playing state
        this.backgroundImg = backgroundImage;
    }
    
    //setBigCloudImage() method
    public void setBigCloudImage(BufferedImage bigcloudImage) {
        // Set the cloud image for the playing state
        this.bigcloudImg = bigcloudImage;
    }
    
    //setSmallCloudImage() method
    public void setSmallCloudImage(BufferedImage smallcloudImage) {
        // Set the cloud image for the playing state
        this.smallcloudImg = smallcloudImage;
    }

	@Override
    public void draw(Graphics g) {
		//new modify for drawing the background
		if (backgroundImg != null) {
			g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		}
		
		//drawing big clouds
		drawBigClouds(g);

        levelManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);
        enemyManager.draw(g,xLvlOffset);
        objectManager.draw(g, xLvlOffset);

        if (paused) {
        	g.setColor(new Color(0, 0, 0, 170));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
        }else if(gameOver)
        	gameOverOverlay.draw(g);    
        else if (lvlCompleted)
			levelCompleted.draw(g);
    }
	
	// drawClouds method()
	private void drawBigClouds(Graphics g) {
		for (int i = 0; i < 3; i++) // for loop to make the big cloud throught all the map
			g.drawImage(bigcloudImg, i * BIG_CLOUD_WIDTH - (int) (xLvlOffset * 0.3), (int) (204 * Game.SCALE), BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);
		for (int i = 0; i < smallCloudsPos.length; i++) // for loop to make the small cloud randomly throught all the map
			g.drawImage(smallcloudImg, SMALL_CLOUD_WIDTH * 4 * i - (int) (xLvlOffset * 0.7), smallCloudsPos[i], SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);
	}
	
	public void resetAll() {
		gameOver = false;
		paused = false;
		lvlCompleted = false;
		player.resetAll();
		enemyManager.resetAllEnemies();
		objectManager.resetAllObjects();
	}
	
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public void checkObjectHit(Rectangle2D.Float attackBox) {
		objectManager.checkObjectHit(attackBox);
	}
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		enemyManager.checkEnemyHit(attackBox);
	}

	public void checkPotionTouched(Rectangle2D.Float hitbox) {
		objectManager.checkObjectTouched(hitbox);
	}
	public void checkSpikesTouched(Player p) {
		objectManager.checkSpikesTouched(p);
	}
	
    @Override
    public void mouseClicked(MouseEvent e) {
    	if(!gameOver)
    		if (e.getButton() == MouseEvent.BUTTON1)
    			player.setAttacking(true);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    	if (gameOver)
    		gameOverOverlay.keyPressed(e);
    	else
    		switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	if(!gameOver)
    		switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        
        }

    }

    public void mouseDragged(MouseEvent e) {
    	if(!gameOver)
    		if (paused)
                pauseOverlay.mouseDragged(e);   
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	if (!gameOver) {
			if (paused)
				pauseOverlay.mousePressed(e);
			else if (lvlCompleted)
				levelCompleted.mousePressed(e);
    	}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    	if (!gameOver) {
			if (paused)
				pauseOverlay.mouseReleased(e);
			else if (lvlCompleted)
				levelCompleted.mouseReleased(e);
		}
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    	if (!gameOver) {
			if (paused)
				pauseOverlay.mouseMoved(e);
			else if (lvlCompleted)
				levelCompleted.mouseMoved(e);
		}
    }


    public void unpauseGame() {
        paused = false;
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }
    
    public EnemyManager getEnemyManager() {
		return enemyManager;
	}
    
    public void setLevelCompleted(boolean levelCompleted) {
		this.lvlCompleted = levelCompleted;
	}

	public void setMaxLvlOffset(int lvlOffset) {
		this.maxLvlOffsetX = lvlOffset;
	}
	public ObjectManager getObjectManager() {
		return objectManager;
	}
	public LevelManager getLevelManager() {
		return levelManager;
	}
}
