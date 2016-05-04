package commandobserver;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


public class Images {
	//
	public static final String BATTLE_CRUISER = "battleCruiser.jpg";
	public static final String BATTLE_SHOOTER = "battleShooter.jpg";
	public static final String BATTLE_STAR = "battleStar.jpg";
	public static final String MASTER_SHIP = "masterShip.jpg";
	public static final String setBackgroundImage = "Space01.png";

	private Images() {

	}


	//using hashmap to compute the image  into an array 
	private static final Map<String, Image> imageMap = new HashMap<>();


	//method to get image by the object name and display them
	public static Image getImage(String name) {
		Image image = imageMap.get(name);
		if (image == null) {
			InputStream input = ImageIO.class.getResourceAsStream("/images/"
					+ name);
			try {
				image = javax.imageio.ImageIO.read(input);
				imageMap.put(name, image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return image;
	}
}
