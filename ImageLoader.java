import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageLoader {
  static public ImageIcon loadImageTransformed(String fileName, int width, int height) {
    return new ImageIcon(new ImageIcon(fileName).getImage()
      .getScaledInstance(width, height, Image.SCALE_SMOOTH));
  }
}
