package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Carnivorous;
import static utilz.Constants.EnemyConstants.CARNIVOROUS;

import main.Game;

public class LoadSave {

	public static final String PLAYER_ATLAS = "player_sprites_sheet.png";
	public static final String LEVEL_ATLAS = "outside_sprites.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "urm_buttons.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";

	//constants variables for the background 
	public static final String PLAYING_BG_IMG_MAP1 = "playing_bg_img_map1.png";
	public static final String PLAYING_BG_IMG_MAP2 = "playing_bg_img_map2.png";
	public static final String PLAYING_BG_IMG_MAP3 = "playing_bg_img_map3.png";

	//constants variables for the environment
	public static final String WHITE_BIG_CLOUDS = "white_big_clouds.png";
	public static final String BLACK_BIG_CLOUDS = "black_big_clouds.png";
	public static final String GREY_BIG_CLOUDS = "grey_big_clouds.png";
	public static final String WHITE_SMALL_CLOUDS = "white_small_clouds.png";
	public static final String BLACK_SMALL_CLOUDS = "black_small_clouds.png";
	public static final String GREY_SMALL_CLOUDS = "grey_small_clouds.png";
	
	public static final String CARNIVOROUS_SPRITE  = "carnivorous.png";
	public static final String TURTLE_SPRITE = "Battle_Turtle.png";
	public static final String BIG_BLOATED_SPRITE = "Big_Bloated.png";
	
	public static final String HEALTH_POWER_BAR  = "health_power_bar.png";
	public static final String Level_Completed  = "completed_sprite.png";
	
	public static final String Potion  = "potions_sprites.png";
	public static final String Container  = "objects_sprites.png";
	public static final String Trap  = "trap_atlas.png";
	
	public static BufferedImage GetSpriteAtlas(String fileName) {
	    BufferedImage img = null;
	    try {
	        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
	        if (is != null) {
	            img = ImageIO.read(is);
	        } else {
	            System.out.println("Failed to find file: " + fileName);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return img;
	}
	
	
	public static BufferedImage[] GetAllLevels() {
		URL url = LoadSave.class.getResource("/Lvls");
		File file = null;

		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		File[] files = file.listFiles();
		File[] filesSorted = new File[files.length];

		for (int i = 0; i < filesSorted.length; i++)
			for (int j = 0; j < files.length; j++) {
				if (files[j].getName().equals((i + 1) + ".png"))
					filesSorted[i] = files[j];

			}

		BufferedImage[] imgs = new BufferedImage[filesSorted.length];

		for (int i = 0; i < imgs.length; i++)
			try {
				imgs[i] = ImageIO.read(filesSorted[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}

		return imgs;
	}
	
	
	


}