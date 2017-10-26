package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by talhaseker on 5.10.2017.
 */
public class SplashScreen extends JWindow {

    // create opening window
    private final int splashSizeWidth = 800;
    private final int splashSizeHeight = 500;
    SplashPanel splashPanel;

    public SplashScreen(){
        super();
        this.getContentPane().setLayout(new BorderLayout());
        splashPanel = new SplashPanel();
        this.getContentPane().add(splashPanel, BorderLayout.CENTER);

        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenDimension.width - splashSizeWidth)/2;
        int y = (screenDimension.height - splashSizeHeight)/2;

        this.setBounds(x, y, splashSizeWidth, splashSizeHeight);
        this.setVisible(true);
        splashPanel.startAnimation();
    }
}