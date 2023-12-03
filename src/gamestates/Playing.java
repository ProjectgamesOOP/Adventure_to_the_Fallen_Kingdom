package gamestates;

import Level.LevelManager;
import UI.GameOverOverlay;
import UI.LevelCompleted;
import UI.PauseOverlay;
import entities.EnemyManager;
import entities.Player;
import main.Game;
import utilz.LoadSave;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Playing extends State implements Statemethods {
    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private PauseOverlay pauseOverlay;
    private GameOverOverlay gameOverOverlay;
    private LevelCompleted levelCompleted;
    private boolean paused = false ;
    
    private int xLvlOffset;
	private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
	private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
	private int maxLvlOffsetX;
	
	private BufferedImage backgroundImg;
	
	private boolean gameOver;
	private boolean lvlCompleted;
	

    public Playing(Game game) {
        super(game);
        initClasses();
        
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG_MAP1);
        calcLvlOffset();
		loadStartLevel();
    }
    
    private void loadStartLevel() {
		enemyManager.loadEnemies(levelManager.getCurrentLevel());
	}

	private void calcLvlOffset() {
		maxLvlOffsetX = levelManager.getCurrentLevel().getLvlOffset();
	}
	
	public void loadNextLevel() {
		resetAll();
		levelManager.loadNextLevel();
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
	}
	
    private void initClasses() {
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        
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
	@Override
    public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		
        levelManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);
        enemyManager.draw(g,xLvlOffset);

        if (paused) {
        	g.setColor(new Color(0, 0, 0, 170));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
        }else if(gameOver)
        	gameOverOverlay.draw(g);    
        else if (lvlCompleted)
			levelCompleted.draw(g);
    }
	
	public void resetAll() {
		gameOver = false;
		paused = false;
		lvlCompleted = false;
		player.resetAll();
		enemyManager.resetAllEnemies();
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		enemyManager.checkEnemyHit(attackBox);
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
}
