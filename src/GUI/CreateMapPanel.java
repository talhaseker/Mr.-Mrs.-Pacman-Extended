package GUI;

import DataLayer.GameDatabase.GameDataManager;
import GUI.UIBase.PacButton;
import GameLogic.AnimationManager.Sprite;
import GameLogic.Enums.GhostType;
import GameLogic.Enums.PacmanType;
import GameLogic.GameMap;
import GameLogic.ScreenItems.Ghost;
import GameLogic.ScreenItems.Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by Aziz Osman on 30.10.2017.
 */
public class CreateMapPanel extends JPanel implements ActionListener {

    private JPanel bottomPanel;
    private JPanel centerPanel;

    private PacButton playButton;
    private PacButton saveButton;

    private JComboBox ghostCombo;
    private JComboBox pacmanCombo;

    private JRadioButton wallBtn;
    private JRadioButton yellowFoodBtn;
    private JRadioButton greenFoodBtn;
    private JRadioButton bigFoodBtn;

    private JToolBar toolBar;

    private BufferedImage wallImage, bigFoodImage, yellowFoodImage, greenFoodImage, bluePrint;
    private int[][] gameMap;
    private Point[] gmForbiddenPoints;
    private GameMap gm;
    private enum ItemToAdd {
        WALL(1), EMPTY(-1), BIG(3), YELLOW(4), GREEN(5);
        private final int id;
        ItemToAdd(int id) { this.id = id; }
        public int getValue() { return id; }
    };

    private ItemToAdd currentItemToAdd = ItemToAdd.WALL;

    private int numbOfGhosts = 0;
    private int numbOfPacmans = 1;
    private final String single = "Single Player";
    private final String multi = "Multiplayer";

    public CreateMapPanel(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.gm = new GameMap();
        this.gmForbiddenPoints = gm.getForbiddenPoints();
        this.gameMap = gm.mapCreateBase;

        ClassLoader loader = this.getClass().getClassLoader();
        ButtonGroup buttonGroup = new ButtonGroup();

        wallBtn = new JRadioButton(new ImageIcon(loader.getResource("ImageIcons/wall.png")));
        wallBtn.setSelectedIcon(new ImageIcon(loader.getResource("ImageIcons/wall.png")));
        wallBtn.addActionListener(this);
        wallBtn.setToolTipText("Add Wall To Map");
        wallBtn.setSelected(true);

        yellowFoodBtn = new JRadioButton(new ImageIcon(loader.getResource("ImageIcons/yellow.png")));
        yellowFoodBtn.setSelectedIcon(new ImageIcon(loader.getResource("ImageIcons/yellow.png")));
        yellowFoodBtn.addActionListener(this);
        yellowFoodBtn.setToolTipText("Add Yellow Food To Map");

        greenFoodBtn = new JRadioButton(new ImageIcon(loader.getResource("ImageIcons/green.png")));
        greenFoodBtn.setSelectedIcon(new ImageIcon(loader.getResource("ImageIcons/green.png")));
        greenFoodBtn.addActionListener(this);
        greenFoodBtn.setToolTipText("Add Green Food To Map");

        bigFoodBtn = new JRadioButton(new ImageIcon(loader.getResource("ImageIcons/big.png")));
        bigFoodBtn.setSelectedIcon(new ImageIcon(loader.getResource("ImageIcons/big.png")));
        bigFoodBtn.addActionListener(this);
        bigFoodBtn.setToolTipText("Add Big Food To Map");

        //add buttons to group
        buttonGroup.add(wallBtn);
        buttonGroup.add(yellowFoodBtn);
        buttonGroup.add(greenFoodBtn);
        buttonGroup.add(bigFoodBtn);

        toolBar = new JToolBar();
        toolBar.setBackground(Color.BLACK);
        toolBar.add(wallBtn);
        toolBar.add(new JToolBar.Separator());
        toolBar.add(yellowFoodBtn);
        toolBar.add(greenFoodBtn);
        toolBar.add(bigFoodBtn);

        String[] ghostNumb = {"0","1","2","3","4"};
        ghostCombo = new JComboBox(ghostNumb);
        ghostCombo.addActionListener(this);

        String[] pmStat = {single, multi};
        pacmanCombo = new JComboBox(pmStat);
        pacmanCombo.addActionListener(this);

        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.BLACK);
        playButton = new PacButton("PLAY MAP", 24f);
        playButton.addActionListener(this);
        saveButton = new PacButton("SAVE MAP", 24f);
        saveButton.addActionListener(this);
        bottomPanel.add(playButton);
        bottomPanel.add(saveButton);

        bluePrint = Sprite.loadSprite("ImageIcons/blueprint");
        wallImage = Sprite.loadSprite("ImageIcons/wall");
        yellowFoodImage = Sprite.loadSprite("ImageIcons/yellow");
        greenFoodImage = Sprite.loadSprite("ImageIcons/green");
        bigFoodImage = Sprite.loadSprite("ImageIcons/big");

        centerPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(bluePrint, 0, 0, null);
                for (int i=0; i<20; i++){
                    for (int j=0; j<11; j++){
                        switch (gameMap[j][i]){
                            case -1:case 0:
                                break;
                            case 1:
                                g.drawImage(wallImage, i*28, j*28, null);
                                break;
                            case 3:
                                g.drawImage(bigFoodImage, i*28, j*28, null);
                                break;
                            case 4:
                                g.drawImage(yellowFoodImage, i*28, j*28, null);
                                break;
                            case 5:
                                g.drawImage(greenFoodImage, i*28, j*28, null);
                                break;
                        }
                    }
                }
            }
        };

//        centerPanel.setBackground(Color.BLACK);
        centerPanel.addMouseListener(new CenterPanelAction());

        this.add(toolBar, BorderLayout.PAGE_START);
//        this.add(centerPanel, BorderLayout.CENTER);
        JPanel jp = new JPanel(null);
        this.add(jp, BorderLayout.CENTER);
        Rectangle rec = jp.getBounds();
        jp.add(centerPanel);
        jp.setBackground(Color.BLACK);
        centerPanel.setLocation(20, 80);
        centerPanel.setSize(new Dimension(560, 308));
        this.add(ghostCombo, BorderLayout.WEST);
        this.add(pacmanCombo, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.PAGE_END);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if(source == wallBtn){
//            if (wallBtn.isSelected())
                currentItemToAdd = ItemToAdd.WALL;
        }
        if(source == yellowFoodBtn){
//            if (wallBtn.isSelected())
            currentItemToAdd = ItemToAdd.YELLOW;
        }
        if(source == greenFoodBtn){
//            if (wallBtn.isSelected())
            currentItemToAdd = ItemToAdd.GREEN;
        }
        if(source == bigFoodBtn){
//            if (wallBtn.isSelected())
            currentItemToAdd = ItemToAdd.BIG;
        }

        if(source == ghostCombo){
            numbOfGhosts = ghostCombo.getSelectedIndex();
        }

        if(source == pacmanCombo){
            numbOfGhosts = pacmanCombo.getSelectedIndex() +1;
        }

        if(source == playButton){
            Pacman[] pacmans = new Pacman[numbOfPacmans];
            Ghost[] ghosts = new Ghost[numbOfGhosts];
            pacmans[0] = new Pacman(PacmanType.MRPACMAN);
            if (numbOfPacmans == 2)
                pacmans[1] = new Pacman(PacmanType.MRSPACMAN);

            for (int i = 0; i<numbOfGhosts; i++)
                ghosts[i] = new Ghost(GhostType.values()[i]);

            GameDataManager gameDataManager = new GameDataManager();
            gameDataManager.saveGameData("test.txt", 0, 1, numbOfPacmans,3,pacmans, ghosts, gameMap);
            GameFrame.uiManager.viewGame(0, "test.txt");
        }
    }

    public class CenterPanelAction implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            Point mp = new Point((int)(e.getX())/28, (int)(e.getY())/28);
            boolean isValidPoint = true;
            for (Point p: gmForbiddenPoints)
                if (p.getX() == mp.getX() && p.getY() == mp.getY())
                    isValidPoint = false;

            if (isValidPoint){
                if (gameMap[(int)mp.getY()][(int)mp.getX()] == currentItemToAdd.getValue()) {
                    gameMap[(int)mp.getY()][(int)mp.getX()] = -1;
                }else{
                    gameMap[(int)mp.getY()][(int)mp.getX()] = currentItemToAdd.getValue();
                }
            }
            centerPanel.repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

}
