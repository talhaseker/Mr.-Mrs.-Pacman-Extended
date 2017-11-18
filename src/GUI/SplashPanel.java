package GUI;

import GUI.UIBase.PacLabel;
import GameLogic.AnimationManager.Animation;
import GameLogic.AnimationManager.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by talhaseker on 6.10.2017.
 */

public class SplashPanel extends JPanel {

    BufferedImage[] fadeInTitleImages = new BufferedImage[17]; //
    Animation fadeInTitleAnimation;
    private boolean isAnimating = true;

    public SplashPanel(){

        for (int i = 1; i <= 17; i++){
            fadeInTitleImages[i-1] = Sprite.loadSprite("pacmanTitle/" + i);
        }

        fadeInTitleAnimation = new Animation(fadeInTitleImages, 0);
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        PacLabel mrLabel = new PacLabel("MR. 8 MRS.", 72f);
        add(mrLabel);
        Dimension sizeMr = mrLabel.getPreferredSize();
        mrLabel.setBounds(220, 100, sizeMr.width, sizeMr.height);

        PacLabel extendedLabel = new PacLabel("EXTENDED", 72f);
        add(extendedLabel);
        Dimension sizeExtended = extendedLabel.getPreferredSize();
        extendedLabel.setBounds(240, 300, sizeExtended.width, sizeExtended.height);
    }

    public void startAnimation() {
        fadeInTitleAnimation.start();
        isAnimating = true;
        int counter = 0;
        while (true){
            counter++;
            try { Thread.sleep(50); }
            catch (InterruptedException e)
            {
                return;
            }
            if (counter == 60)
                break;
            if (!isAnimating)
                break;
            if (counter <= 16)
                this.repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (800 - fadeInTitleAnimation.getSprite().getWidth())/2;
        int y = (500 - fadeInTitleAnimation.getSprite().getHeight())/2;
        g.drawImage(fadeInTitleAnimation.getSprite(), x - 30, y, null);
        fadeInTitleAnimation.update();
    }
}