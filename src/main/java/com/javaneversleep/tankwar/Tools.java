package com.javaneversleep.tankwar;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Tools {

    public static Image getImage(String imageName) {
        return new ImageIcon("assets/images/" + imageName).getImage();
    }
}
