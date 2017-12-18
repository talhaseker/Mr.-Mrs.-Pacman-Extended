import GUI.GameFrame;
import GUI.SplashScreen;

/**
 * Created by aziz osman on 6.10.2017.
 */
public class ApplicationMain {

    public static void main(String[] args) {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.dispose();
        GameFrame mainGameFrame = new GameFrame();
    }
}
