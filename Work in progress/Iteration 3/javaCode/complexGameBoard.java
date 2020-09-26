import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;


public class complexGameBoard extends JFrame implements ActionListener {

    //public static final int dim = 16;
    //private static final Color COL_BACKGROUND = new Color(190, 190, 190);
    //private static final Color COL_CELL1 = new Color(210, 210, 210);
    //private static final Color COL_CELL2 = new Color(245, 245, 245);
    //private static final Color COL_WALL = new Color(85, 85, 85);
// gui components that are contained in this frame:
    private JPanel sidePanel, bottomPanel, topLeftPanel, bottomLeftPanel;    // top and bottom panels in the main window
    private JLabel sideLabel, topLabel, topLabel2, topLabel3, topLabel4, player1Label, player2Label, player3Label, player4Label, playersTitle, movesTitle, bidOrderTitle;                // a text label to appear in the top panel
    private JButton topButton, instructionsButton, startBiddingButton, verifyButton, player1LockBid, lockAllBidsButton, saveGameButton;                // a 'reset' button to appear in the top panel
    private GridSquare[][] gridSquaresGameBoard;    // squares to appear in grid formation in the bottom panel
    private int x, y;                        // the size of the grid
    private JComboBox<Integer> movesList1, movesList2, movesList3, movesList4;
    private ImageIcon blueCircleIcon, blueHexIcon, blueSquareIcon, blueTriangleIcon, greenCircleIcon, greenHexIcon, greenSquareIcon, greenTriangleIcon;
    private ImageIcon redCircleIcon, redHexIcon, redSquareIcon, redTriangleIcon, yellowCircleIcon, yellowHexIcon, yellowSquareIcon, yellowTriangleIcon;
    private ImageIcon vortexIcon;
    private TargetTile blueCircleTargetTile, blueHexTargetTile, blueSquareTargetTile, blueTriangleTargetTile;
    private TargetTile greenCircleTargetTile, greenHexTargetTile, greenSquareTargetTile, greenTriangleTargetTile;
    private TargetTile redCircleTargetTile, redHexTargetTile, redSquareTargetTile, redTriangleTargetTile;
    private TargetTile yellowCircleTargetTile, yellowHexTargetTile, yellowSquareTargetTile, yellowTriangleTargetTile;
    private TargetTile vortexTargetTile;
    private TargetChip blueCircleTargetChip, blueHexTargetChip, blueSquareTargetChip, blueTriangleTargetChip;
    private TargetChip greenCircleTargetChip, greenHexTargetChip, greenSquareTargetChip, greenTriangleTargetChip;
    private TargetChip redCircleTargetChip, redHexTargetChip, redSquareTargetChip, redTriangleTargetChip;
    private TargetChip yellowCircleTargetChip, yellowHexTargetChip, yellowSquareTargetChip, yellowTriangleTargetChip;
    private TargetChip vortexTargetChip;
    private TargetChip currentChipToDisplay;
    private TargetChip [] randomTargetChipArray;
    private ImageIcon redRobotIcon, greenRobotIcon, yellowRobotIcon, blueRobotIcon;
    private GridSquare currentTargetSquare;
    private RobotPieces redRobot, greenRobot, yellowRobot, blueRobot;
    private Player player1, player2, player3, player4, playerThatGoesNow;
    private JComboBox<Integer> bidOrderList1, bidOrderList2, bidOrderList3, bidOrderList4;

    private ClickRecorder clickRecorder;
    private boolean  readyToVerify;
    private boolean winnerOfRoundFound;
    private int playerCount;
    // private ATimer timer;

    private LocalDateTime startTime;
    private javax.swing.Timer timer;
    private Duration duration = Duration.ofMinutes(1);


    public complexGameBoard(int x, int y) {
        this.x = x;
        this.y = y;
        this.setSize(1200, 800);
        this.setTitle("Ricochet Robots");
        //Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setLocation(dimension.width/2 - this.getSize().width/2, dimension.height/2 - this.getSize().height/2);       
        this.setLocationRelativeTo(null);
        this.readyToVerify = false;
        this.clickRecorder = new ClickRecorder();

        this.winnerOfRoundFound = false;
        this.playerCount = 0;

        this.currentChipToDisplay = new TargetChip("", "", null, -1, -1);
        this.randomTargetChipArray = new TargetChip[17];
        createIconObjects();
        createPanels();
        createButtonsWithIconsAndTargetTilesAndAddToGrid();
        setupVisualAndLogicalBorders();
        addRobotIconsAndRobotsToStartingPositions();
        //addStartBiddingButton();
        addPanelsToGetContentPane();

        enableAllGridSquaresClickable();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem1 = new JMenuItem(" New Game  ");
        menuItem1.addActionListener((ActionEvent e ) -> {newGame();});
        JMenuItem menuItem2 = new JMenuItem(" Save  ");
        menuItem2.addActionListener((ActionEvent e ) -> {saveGame();});
        //JMenuItem menuItem2 = new JMenuItem(" Load...   ");
        //menuItem2.addActionListener((ActionEvent e ) -> {loadGame();});
        // use getMenuShortcutKeyMaskEx() in Java 10 (getMenuShortcutKeyMask() deprecated)
        //menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
        //Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(menuItem1);
        menu.add(menuItem2);
        //menu.add(menuItem2);

        this.setJMenuBar(menuBar);


        // housekeeping : behaviour
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


        createPlayersAndConnectRobots();
        makeTargetChipsAndPutLatestOnBoard();

    }

    public void actionPerformed(ActionEvent actionEvent) {
        // get the object that was selected in the gui
        Object selected = actionEvent.getSource();

    }

    private void createIconObjects() {
        //blue icons
        blueCircleIcon = new ImageIcon("bluecircle.jpg");
        blueHexIcon = new ImageIcon("bluehex.jpg");
        blueSquareIcon = new ImageIcon("bluesquare.jpg");
        blueTriangleIcon = new ImageIcon("bluetriangle.jpg");
        //green icons
        greenCircleIcon = new ImageIcon("greencircle.jpg");
        greenHexIcon = new ImageIcon("greenhex.jpg");
        greenSquareIcon = new ImageIcon("greensquare.jpg");
        greenTriangleIcon = new ImageIcon("greentriangle.jpg");
        //red icons
        redCircleIcon = new ImageIcon("redcircle.jpg");
        redHexIcon = new ImageIcon("redhex.jpg");
        redSquareIcon = new ImageIcon("redsquare.jpg");
        redTriangleIcon = new ImageIcon("redtriangle.jpg");
        //yellow icons
        yellowCircleIcon = new ImageIcon("yellowcircle.jpg");
        yellowHexIcon = new ImageIcon("yellowhex.jpg");
        yellowSquareIcon = new ImageIcon("yellowsquare.jpg");
        yellowTriangleIcon = new ImageIcon("yellowtriangle.jpg");

        vortexIcon = new ImageIcon("vortex.jpeg");
    }

    private void createPanels() {
        // first create the panels
        topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new GridLayout(5, 1));

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(x, y));
        bottomPanel.setSize(800, 800);

        // then create the components for each panel and add them to it

        // for the top panel:
        topLabel = new JLabel("Make a bid!");
        topLabel2 = new JLabel("Timer starts when a bid is made.");
        topLabel3 = new JLabel("Timer");
        topLabel4 = new JLabel("1m 00s", SwingConstants.CENTER);
        //topButton = new JButton("");
        //topButton.addActionListener( this);			// IMPORTANT! Without this, clicking the button does nothing.

        instructionsButton = new JButton("Instructions");
        instructionsButton.addActionListener((ActionEvent e ) -> {showInstructions();});

        JButton startBiddingButton = new JButton("Start Bid Timer!");
        startBiddingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
//
                if(timer.isRunning()) {
                    //TODO Or do I add start bidding round here? It also works here.
                    timer.stop();
                    startTime= null;
                    startBiddingButton.setText("Start Bid Timer!");
                    String message = "TIME IS UP! Please lock in all of the bids using the button!";
                    JOptionPane messageThatPlayerWon = new JOptionPane();
                    messageThatPlayerWon.showMessageDialog(new JFrame(), message);
                } else {
                    startBiddingRound();//TODO Check if in right spot
                    startTime = LocalDateTime.now();
                    timer.start();
                    startBiddingButton.setText("Stop Bid Timer!");
                }
            }
        });
        verifyButton = new JButton("Verify Bid!"); //Action Listener is added later.
        lockAllBidsButton = new JButton("Lock All Bids"); //Action Listener is added later.
        verifyButton.addActionListener((ActionEvent e ) -> {this.readyToVerify = true;});

        saveGameButton = new JButton("Save Game");
        saveGameButton.addActionListener((ActionEvent e ) -> {saveGame();});
        //clickYourPathButton = new JButton("Click Your Path");
        //clickYourPathButton.addActionListener((ActionEvent e) -> {});
        topLeftPanel.add(instructionsButton);
        topLeftPanel.add(startBiddingButton);
        topLeftPanel.add(topLabel4);
        topLeftPanel.add(lockAllBidsButton);
        topLeftPanel.add(verifyButton);
        //topLeftPanel.add(saveGameButton);
        timer = new javax.swing.Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                LocalDateTime now = LocalDateTime.now();
                Duration runningTime = Duration.between(startTime, now);
                Duration timeLeft = duration.minus(runningTime);
                if(timeLeft.isZero() || timeLeft.isNegative()){
                    timeLeft = Duration.ZERO;
                    startBiddingButton.doClick();
                }
                topLabel4.setText(format(timeLeft));
            }
        });


        //topPanel.add(startBiddingButton);
        //topPanel.add(topLabel2);
        //topPanel.add(topLabel3);
       // topPanel.add(topLabel4);
        //topPanel.add( topLabel);


        bottomLeftPanel = new JPanel(new GridLayout(5, 3));
        bottomLeftPanel.setSize(100, 800);

        movesTitle = new JLabel("BID:", SwingConstants.CENTER);
        movesList1 = new JComboBox<Integer>();
        movesList2 = new JComboBox<Integer>();
        movesList3 = new JComboBox<Integer>();
        movesList4 = new JComboBox<Integer>();


        for (int i = 0; i < 31; i++) {
            movesList1.addItem(i);
            movesList2.addItem(i);
            movesList3.addItem(i);
            movesList4.addItem(i);
        }

        bidOrderTitle = new JLabel("BID ORDER:", SwingConstants.CENTER);
        bidOrderList1 = new JComboBox<Integer>();
        bidOrderList2 = new JComboBox<Integer>();
        bidOrderList3 = new JComboBox<Integer>();
        bidOrderList4 = new JComboBox<Integer>();

        for (int i = 1; i <= 4; i++){
            bidOrderList1.addItem(i);
            bidOrderList2.addItem(i);
            bidOrderList3.addItem(i);
            bidOrderList4.addItem(i);
        }



        //Labels for the Number of Players
        playersTitle = new JLabel("PLAYERS:", SwingConstants.CENTER);
        player1Label = new JLabel("Player 1:", SwingConstants.CENTER);
        player2Label = new JLabel("Player 2:", SwingConstants.CENTER);
        player3Label = new JLabel("Player 3:", SwingConstants.CENTER);
        player4Label = new JLabel("Player 4:", SwingConstants.CENTER);


        //bottomLeftPanel.add(topLabel2);
        //bottomLeftPanel.add(topLabel3);
        //bottomLeftPanel.add(topLabel4);
        //playerPanel.add( topLabel3);

        //Adding all the components
        bottomLeftPanel.add(playersTitle);
        bottomLeftPanel.add(movesTitle);
        bottomLeftPanel.add(bidOrderTitle);

        bottomLeftPanel.add(player1Label);
        bottomLeftPanel.add(movesList1);
        bottomLeftPanel.add(bidOrderList1);

        bottomLeftPanel.add(player2Label);
        bottomLeftPanel.add(movesList2);
        bottomLeftPanel.add(bidOrderList2);

        bottomLeftPanel.add(player3Label);
        bottomLeftPanel.add(movesList3);
        bottomLeftPanel.add(bidOrderList3);

        bottomLeftPanel.add(player4Label);
        bottomLeftPanel.add(movesList4);
        bottomLeftPanel.add(bidOrderList4);


        //topPanel.add(player1Label);
        //topPanel.add(player2Label);
        //topPanel.add(player3Label);
        //topPanel.add(player4Label);
        //topPanel.add ( topButton);


    }

    private void createButtonsWithIconsAndTargetTilesAndAddToGrid() {
        // for the bottom panel:
        // create the buttons and add them to the grid
        gridSquaresGameBoard = new GridSquare[y][x];

        for (int row = 0; row < y; row++) {
            for (int column = 0; column < x; column++) {
                gridSquaresGameBoard[row][column] = new GridSquare(row, column);//THIS IS AN ERROR< X AND Y SHOULD BE COLUMN and ROW.
                gridSquaresGameBoard[row][column].setSize(20, 20);
                gridSquaresGameBoard[row][column].setColor(column + row);
                gridSquaresGameBoard[row][column].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));


                //gridSquares [column][row].addMouseListener( this);		// AGAIN, don't forget this line!

                bottomPanel.add(gridSquaresGameBoard[row][column]); //Adding to GridLayout happens to each row after row is filled.
            }
        }

        /*
        for (int column = 0; column < x; column++) {
            for (int row = 0; row < y; row++) {
                gridSquares[column][row] = new GridSquare(column, row);//THIS IS AN ERROR< X AND Y SHOULD BE COLUMN and ROW.
                gridSquares[column][row].setSize(20, 20);
                gridSquares[column][row].setColor(column + row);
                gridSquares[column][row].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));


                //gridSquares [column][row].addMouseListener( this);		// AGAIN, don't forget this line!

                bottomPanel.add(gridSquares[column][row]);
            }
        }
        */
        //TODO I'm leaving this section alone, but the row and columns need to be replaced here.
        for (int row = 1; row < y; row++) {
            gridSquaresGameBoard[0][row].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.black));
        }
        for (int column = 1; column < y; column++) {
            gridSquaresGameBoard[column][0].setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, Color.black));
        }
        for (int row = 1; row < y; row++) {
            gridSquaresGameBoard[15][row].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 1, Color.black));
        }
        for (int column = 1; column < y; column++) {
            gridSquaresGameBoard[column][15].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 4, Color.black));
        }
        gridSquaresGameBoard[0][0].setBorder(BorderFactory.createMatteBorder(4, 4, 1, 1, Color.black));
        gridSquaresGameBoard[0][15].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 4, Color.black));
        gridSquaresGameBoard[15][0].setBorder(BorderFactory.createMatteBorder(1, 4, 4, 1, Color.black));
        gridSquaresGameBoard[15][15].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.black));

        //make all the TargetTiles
        redCircleTargetTile = new TargetTile("RED", "CIRCLE");
        redSquareTargetTile = new TargetTile("RED", "SQUARE");
        redHexTargetTile = new TargetTile("RED", "HEX");
        redTriangleTargetTile = new TargetTile("RED", "TRIANGLE");

        greenCircleTargetTile = new TargetTile("GREEN", "CIRCLE");
        greenSquareTargetTile = new TargetTile("GREEN", "SQUARE");
        greenHexTargetTile = new TargetTile("GREEN", "HEX");
        greenTriangleTargetTile = new TargetTile("GREEN", "TRIANGLE");

        yellowCircleTargetTile = new TargetTile("YELLOW", "CIRCLE");
        yellowSquareTargetTile = new TargetTile("YELLOW", "SQUARE");
        yellowHexTargetTile = new TargetTile("YELLOW", "HEX");
        yellowTriangleTargetTile = new TargetTile("YELLOW", "TRIANGLE");

        blueCircleTargetTile = new TargetTile("BLUE", "CIRCLE");
        blueSquareTargetTile = new TargetTile("BLUE", "SQUARE");
        blueHexTargetTile = new TargetTile("BLUE", "HEX");
        blueTriangleTargetTile = new TargetTile("BLUE", "TRIANGLE");

        vortexTargetTile = new TargetTile("MULTICOLOR", "VORTEX");


        //set Target Tiles and their icons
        gridSquaresGameBoard[3][1].setIcon(new ImageIcon(redCircleIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[3][1].addTargetTileToSquare(redCircleTargetTile);
        gridSquaresGameBoard[1][10].setIcon(new ImageIcon(redSquareIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[1][10].addTargetTileToSquare(redSquareTargetTile);
        gridSquaresGameBoard[4][6].setIcon(new ImageIcon(yellowHexIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[4][6].addTargetTileToSquare(yellowHexTargetTile);
        gridSquaresGameBoard[4][8].setIcon(new ImageIcon(blueTriangleIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[4][8].addTargetTileToSquare(blueTriangleTargetTile);
        gridSquaresGameBoard[5][13].setIcon(new ImageIcon(greenHexIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[5][13].addTargetTileToSquare(greenHexTargetTile);
        gridSquaresGameBoard[6][13].setIcon(new ImageIcon(yellowCircleIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[6][13].addTargetTileToSquare(yellowCircleTargetTile);
        gridSquaresGameBoard[6][2].setIcon(new ImageIcon(yellowTriangleIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[6][2].addTargetTileToSquare(yellowTriangleTargetTile);
        gridSquaresGameBoard[6][3].setIcon(new ImageIcon(blueSquareIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[6][3].addTargetTileToSquare(blueSquareTargetTile);


        gridSquaresGameBoard[9][2].setIcon(new ImageIcon(yellowTriangleIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[9][2].addTargetTileToSquare(yellowTriangleTargetTile);
        gridSquaresGameBoard[9][10].setIcon(new ImageIcon(yellowSquareIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[9][10].addTargetTileToSquare(yellowSquareTargetTile);
        gridSquaresGameBoard[10][7].setIcon(new ImageIcon(vortexIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[10][7].addTargetTileToSquare(vortexTargetTile);
        gridSquaresGameBoard[11][12].setIcon(new ImageIcon(redTriangleIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[11][12].addTargetTileToSquare(redTriangleTargetTile);
        gridSquaresGameBoard[11][13].setIcon(new ImageIcon(greenCircleIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[11][13].addTargetTileToSquare(greenCircleTargetTile);
        gridSquaresGameBoard[12][3].setIcon(new ImageIcon(greenSquareIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[12][3].addTargetTileToSquare(greenSquareTargetTile);
        gridSquaresGameBoard[13][3].setIcon(new ImageIcon(blueCircleIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[13][3].addTargetTileToSquare(blueCircleTargetTile);
        gridSquaresGameBoard[13][9].setIcon(new ImageIcon(blueHexIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[13][9].addTargetTileToSquare(blueHexTargetTile);
        gridSquaresGameBoard[14][5].setIcon(new ImageIcon(redHexIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[14][5].addTargetTileToSquare(redHexTargetTile);
        


    }

    private void setupVisualAndLogicalBorders() {
        //set the exterior barriers visually
        gridSquaresGameBoard[0][5].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[0][6].setBorder(BorderFactory.createMatteBorder(4, 6, 1, 1, Color.BLACK));

        gridSquaresGameBoard[0][8].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[0][9].setBorder(BorderFactory.createMatteBorder(4, 6, 1, 1, Color.BLACK));

        gridSquaresGameBoard[2][15].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 4, Color.BLACK));
        gridSquaresGameBoard[3][15].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 4, Color.BLACK));

        gridSquaresGameBoard[12][15].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 4, Color.BLACK));
        gridSquaresGameBoard[13][15].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 4, Color.BLACK));

        gridSquaresGameBoard[15][10].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 6, Color.BLACK));
        gridSquaresGameBoard[15][11].setBorder(BorderFactory.createMatteBorder(1, 6, 4, 1, Color.BLACK));

        gridSquaresGameBoard[15][6].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 6, Color.BLACK));
        gridSquaresGameBoard[15][7].setBorder(BorderFactory.createMatteBorder(1, 6, 4, 1, Color.BLACK));

        gridSquaresGameBoard[5][0].setBorder(BorderFactory.createMatteBorder(1, 4, 6, 1, Color.BLACK));
        gridSquaresGameBoard[6][0].setBorder(BorderFactory.createMatteBorder(6, 4, 1, 1, Color.BLACK));

        gridSquaresGameBoard[10][0].setBorder(BorderFactory.createMatteBorder(1, 4, 6, 1, Color.BLACK));
        gridSquaresGameBoard[11][0].setBorder(BorderFactory.createMatteBorder(6, 4, 1, 1, Color.BLACK));


        //set the interior barriers and the logic

        //First do outer perimeter logic barriers

        //First do north edge of whole board can overwrite corners
        gridSquaresGameBoard[0][0].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][1].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][2].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][3].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][4].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][5].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][6].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][7].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][8].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][9].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][10].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][11].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][12].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][13].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][14].addNorthEdgeBarrier();
        gridSquaresGameBoard[0][15].addNorthEdgeBarrier();

        //Then east edge of whole board can overwrite corners

        gridSquaresGameBoard[0][15].addEastEdgeBarrier();
        gridSquaresGameBoard[1][15].addEastEdgeBarrier();
        gridSquaresGameBoard[2][15].addEastEdgeBarrier();
        gridSquaresGameBoard[3][15].addEastEdgeBarrier();
        gridSquaresGameBoard[4][15].addEastEdgeBarrier();
        gridSquaresGameBoard[5][15].addEastEdgeBarrier();
        gridSquaresGameBoard[6][15].addEastEdgeBarrier();
        gridSquaresGameBoard[7][15].addEastEdgeBarrier();
        gridSquaresGameBoard[8][15].addEastEdgeBarrier();
        gridSquaresGameBoard[9][15].addEastEdgeBarrier();
        gridSquaresGameBoard[10][15].addEastEdgeBarrier();
        gridSquaresGameBoard[11][15].addEastEdgeBarrier();
        gridSquaresGameBoard[12][15].addEastEdgeBarrier();
        gridSquaresGameBoard[13][15].addEastEdgeBarrier();
        gridSquaresGameBoard[14][15].addEastEdgeBarrier();
        gridSquaresGameBoard[15][15].addEastEdgeBarrier();

        //Then south edge of whole board can overwrite corners

        gridSquaresGameBoard[15][15].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][14].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][13].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][12].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][11].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][10].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][9].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][8].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][7].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][6].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][5].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][4].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][3].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][2].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][1].addSouthEdgeBarrier();
        gridSquaresGameBoard[15][0].addSouthEdgeBarrier();

        //Then west edge of whole board can overwrite corners

        gridSquaresGameBoard[15][0].addWestEdgeBarrier();
        gridSquaresGameBoard[14][0].addWestEdgeBarrier();
        gridSquaresGameBoard[13][0].addWestEdgeBarrier();
        gridSquaresGameBoard[12][0].addWestEdgeBarrier();
        gridSquaresGameBoard[11][0].addWestEdgeBarrier();
        gridSquaresGameBoard[10][0].addWestEdgeBarrier();
        gridSquaresGameBoard[9][0].addWestEdgeBarrier();
        gridSquaresGameBoard[8][0].addWestEdgeBarrier();
        gridSquaresGameBoard[7][0].addWestEdgeBarrier();
        gridSquaresGameBoard[6][0].addWestEdgeBarrier();
        gridSquaresGameBoard[5][0].addWestEdgeBarrier();
        gridSquaresGameBoard[4][0].addWestEdgeBarrier();
        gridSquaresGameBoard[3][0].addWestEdgeBarrier();
        gridSquaresGameBoard[2][0].addWestEdgeBarrier();
        gridSquaresGameBoard[1][0].addWestEdgeBarrier();
        gridSquaresGameBoard[0][0].addWestEdgeBarrier();

        //Now do rest


        //First corner barrier

        gridSquaresGameBoard[3][0].setBorder(BorderFactory.createMatteBorder(1, 4, 1, 6, Color.BLACK));
        gridSquaresGameBoard[0][4].addSouthEdgeBarrier();
        gridSquaresGameBoard[1][3].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[1][3].addEastEdgeBarrier();
        gridSquaresGameBoard[1][4].setBorder(BorderFactory.createMatteBorder(6, 6, 1, 1, Color.BLACK));
        gridSquaresGameBoard[1][4].addNorthEdgeBarrier();
        //Border not filled in.
        gridSquaresGameBoard[1][4].addWestEdgeBarrier();

        //Second corner barrier

        gridSquaresGameBoard[0][13].setBorder(BorderFactory.createMatteBorder(4, 1, 6, 1, Color.BLACK));
        gridSquaresGameBoard[0][13].addSouthEdgeBarrier();
        gridSquaresGameBoard[1][12].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[1][12].addEastEdgeBarrier();
        gridSquaresGameBoard[1][13].setBorder(BorderFactory.createMatteBorder(6, 6, 1, 1, Color.BLACK));
        gridSquaresGameBoard[1][13].addNorthEdgeBarrier();
        gridSquaresGameBoard[1][13].addWestEdgeBarrier();

        //Third corner barrier

        gridSquaresGameBoard[13][3].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 1, Color.BLACK));
        gridSquaresGameBoard[13][3].addSouthEdgeBarrier();
        gridSquaresGameBoard[14][2].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[14][2].addEastEdgeBarrier();
        gridSquaresGameBoard[14][3].setBorder(BorderFactory.createMatteBorder(6, 6, 1, 1, Color.BLACK));
        gridSquaresGameBoard[14][3].addNorthEdgeBarrier();
        gridSquaresGameBoard[14][3].addWestEdgeBarrier();

        //Fourth corner barrier

        gridSquaresGameBoard[12][10].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 1, Color.BLACK));
        gridSquaresGameBoard[12][10].addSouthEdgeBarrier();
        gridSquaresGameBoard[13][9].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[13][9].addEastEdgeBarrier();
        gridSquaresGameBoard[13][10].setBorder(BorderFactory.createMatteBorder(6, 6, 1, 1, Color.BLACK));
        gridSquaresGameBoard[13][10].addNorthEdgeBarrier();
        gridSquaresGameBoard[13][10].addWestEdgeBarrier();

        //Fifth corner barrier

        gridSquaresGameBoard[2][9].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 6, Color.BLACK));
        gridSquaresGameBoard[2][9].addSouthEdgeBarrier();
        gridSquaresGameBoard[2][9].addEastEdgeBarrier();
        gridSquaresGameBoard[2][10].setBorder(BorderFactory.createMatteBorder(1, 6, 1, 1, Color.BLACK));
        gridSquaresGameBoard[2][10].addWestEdgeBarrier();
        gridSquaresGameBoard[3][9].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 1, Color.BLACK));
        gridSquaresGameBoard[3][9].addNorthEdgeBarrier();

        //Sixth corner barrier

        gridSquaresGameBoard[3][6].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 6, Color.BLACK));
        gridSquaresGameBoard[3][6].addSouthEdgeBarrier();
        gridSquaresGameBoard[3][6].addEastEdgeBarrier();
        gridSquaresGameBoard[3][7].setBorder(BorderFactory.createMatteBorder(1, 6, 1, 1, Color.BLACK));
        gridSquaresGameBoard[3][7].addWestEdgeBarrier();
        gridSquaresGameBoard[4][6].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 1, Color.BLACK));
        gridSquaresGameBoard[4][6].addNorthEdgeBarrier();

        //Seventh corner barrier

        gridSquaresGameBoard[9][1].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 6, Color.BLACK));
        gridSquaresGameBoard[9][1].addSouthEdgeBarrier();
        gridSquaresGameBoard[9][1].addEastEdgeBarrier();
        gridSquaresGameBoard[9][2].setBorder(BorderFactory.createMatteBorder(1, 6, 1, 1, Color.BLACK));
        gridSquaresGameBoard[9][2].addWestEdgeBarrier();
        gridSquaresGameBoard[10][1].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 1, Color.BLACK));
        gridSquaresGameBoard[10][1].addNorthEdgeBarrier();


        //Eighth corner barrier

        gridSquaresGameBoard[9][14].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 6, Color.BLACK));
        gridSquaresGameBoard[9][14].addSouthEdgeBarrier();
        gridSquaresGameBoard[9][14].addEastEdgeBarrier();
        gridSquaresGameBoard[9][15].setBorder(BorderFactory.createMatteBorder(1, 6, 1, 4, Color.BLACK));
        gridSquaresGameBoard[9][15].addWestEdgeBarrier();
        gridSquaresGameBoard[10][14].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 1, Color.BLACK));
        gridSquaresGameBoard[10][14].addNorthEdgeBarrier();

        //Ninth corner barrier

        gridSquaresGameBoard[1][1].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 1, Color.BLACK));
        gridSquaresGameBoard[1][1].addSouthEdgeBarrier();
        gridSquaresGameBoard[2][1].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[2][1].addNorthEdgeBarrier();
        gridSquaresGameBoard[2][1].addWestEdgeBarrier();
        gridSquaresGameBoard[2][2].setBorder(BorderFactory.createMatteBorder(1, 6, 1, 1, Color.BLACK));
        gridSquaresGameBoard[2][2].addEastEdgeBarrier();

        //Tenth corner barrier

        gridSquaresGameBoard[7][5].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 1, Color.BLACK));
        gridSquaresGameBoard[7][5].addSouthEdgeBarrier();
        gridSquaresGameBoard[8][5].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[8][5].addNorthEdgeBarrier();
        gridSquaresGameBoard[8][5].addEastEdgeBarrier();
        gridSquaresGameBoard[8][6].setBorder(BorderFactory.createMatteBorder(1, 6, 1, 1, Color.BLACK));
        gridSquaresGameBoard[8][6].addWestEdgeBarrier();

        //Eleventh corner barrier

        gridSquaresGameBoard[5][11].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 1, Color.BLACK));
        gridSquaresGameBoard[5][11].addSouthEdgeBarrier();
        gridSquaresGameBoard[6][11].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[6][11].addNorthEdgeBarrier();
        gridSquaresGameBoard[6][11].addEastEdgeBarrier();
        gridSquaresGameBoard[6][12].setBorder(BorderFactory.createMatteBorder(1, 6, 1, 1, Color.BLACK));
        gridSquaresGameBoard[6][12].addWestEdgeBarrier();

        //Tweleth corner barrier

        gridSquaresGameBoard[9][8].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 1, Color.BLACK));
        gridSquaresGameBoard[9][8].addSouthEdgeBarrier();
        gridSquaresGameBoard[10][8].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[10][8].addNorthEdgeBarrier();
        gridSquaresGameBoard[10][8].addEastEdgeBarrier();
        gridSquaresGameBoard[10][9].setBorder(BorderFactory.createMatteBorder(1, 6, 1, 1, Color.BLACK));
        gridSquaresGameBoard[10][9].addWestEdgeBarrier();

        //Thirteenth corner barrier

        gridSquaresGameBoard[12][5].setBorder(BorderFactory.createMatteBorder(1, 1, 6, 1, Color.BLACK));
        gridSquaresGameBoard[12][5].addSouthEdgeBarrier();
        gridSquaresGameBoard[13][5].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[13][5].addNorthEdgeBarrier();
        gridSquaresGameBoard[13][5].addEastEdgeBarrier();
        gridSquaresGameBoard[13][6].setBorder(BorderFactory.createMatteBorder(1, 6, 1, 1, Color.BLACK));
        gridSquaresGameBoard[13][6].addWestEdgeBarrier();

        //Fourteenth corner barrier

        gridSquaresGameBoard[6][2].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[6][2].addEastEdgeBarrier();
        gridSquaresGameBoard[6][3].setBorder(BorderFactory.createMatteBorder(1, 6, 6, 1, Color.BLACK));
        gridSquaresGameBoard[6][3].addWestEdgeBarrier();
        gridSquaresGameBoard[6][3].addSouthEdgeBarrier();
        gridSquaresGameBoard[7][3].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 1, Color.BLACK));
        gridSquaresGameBoard[7][3].addNorthEdgeBarrier();

        //Fifteenth corner barrier

        gridSquaresGameBoard[5][13].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[5][13].addEastEdgeBarrier();
        gridSquaresGameBoard[5][14].setBorder(BorderFactory.createMatteBorder(1, 6, 6, 1, Color.BLACK));
        gridSquaresGameBoard[5][14].addWestEdgeBarrier();
        gridSquaresGameBoard[5][14].addSouthEdgeBarrier();
        gridSquaresGameBoard[6][14].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 1, Color.BLACK));
        gridSquaresGameBoard[6][14].addNorthEdgeBarrier();

        //Sixteenth corner barrier

        gridSquaresGameBoard[10][3].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[10][3].addEastEdgeBarrier();
        gridSquaresGameBoard[10][4].setBorder(BorderFactory.createMatteBorder(1, 6, 6, 1, Color.BLACK));
        gridSquaresGameBoard[10][4].addWestEdgeBarrier();
        gridSquaresGameBoard[10][4].addSouthEdgeBarrier();
        gridSquaresGameBoard[11][4].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 1, Color.BLACK));
        gridSquaresGameBoard[11][4].addNorthEdgeBarrier();

        //Barrier

        gridSquaresGameBoard[11][12].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 6, Color.BLACK));
        gridSquaresGameBoard[11][12].addEastEdgeBarrier();
        gridSquaresGameBoard[11][13].setBorder(BorderFactory.createMatteBorder(1, 6, 6, 1, Color.BLACK));
        gridSquaresGameBoard[11][13].addWestEdgeBarrier();
        gridSquaresGameBoard[11][13].addSouthEdgeBarrier();
        gridSquaresGameBoard[12][13].setBorder(BorderFactory.createMatteBorder(6, 1, 1, 1, Color.BLACK));
        gridSquaresGameBoard[12][13].addNorthEdgeBarrier();

        //Barrier

        gridSquaresGameBoard[7][7].setBorder(BorderFactory.createMatteBorder(8, 8, 0, 0, Color.BLACK));
        gridSquaresGameBoard[7][7].addNorthEdgeBarrier();
        gridSquaresGameBoard[7][7].addWestEdgeBarrier();
        gridSquaresGameBoard[7][8].setBorder(BorderFactory.createMatteBorder(8, 0, 0, 8, Color.BLACK));
        gridSquaresGameBoard[7][8].addNorthEdgeBarrier();
        gridSquaresGameBoard[7][8].addEastEdgeBarrier();
        gridSquaresGameBoard[8][7].setBorder(BorderFactory.createMatteBorder(0, 8, 8, 0, Color.BLACK));
        gridSquaresGameBoard[8][7].addWestEdgeBarrier();
        gridSquaresGameBoard[8][7].addSouthEdgeBarrier();
        gridSquaresGameBoard[8][8].setBorder(BorderFactory.createMatteBorder(0, 0, 8, 8, Color.BLACK));
        gridSquaresGameBoard[8][8].addSouthEdgeBarrier();
        gridSquaresGameBoard[8][8].addEastEdgeBarrier();
    }

    private void addRobotIconsAndRobotsToStartingPositions() {
        redRobotIcon = new ImageIcon("redRobot.jpg");
        greenRobotIcon = new ImageIcon("greenRobot.jpg");
        yellowRobotIcon = new ImageIcon("yellowRobot.jpg");
        blueRobotIcon = new ImageIcon("blueRobot.jpg");
        //TODO need to finish adding icons here


        redRobot = new RobotPieces("RED", "STAR");
        redRobot.setRobotRowCoordinate(11);
        redRobot.setRobotColumnCoordinate(3);
        gridSquaresGameBoard[11][3].addRobotToSquare(redRobot);
        gridSquaresGameBoard[11][3].setIcon(new ImageIcon(redRobotIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

        greenRobot = new RobotPieces("GREEN", "");
        greenRobot.setRobotRowCoordinate(5);
        greenRobot.setRobotColumnCoordinate(13);
        gridSquaresGameBoard[5][13].addRobotToSquare(greenRobot);
        gridSquaresGameBoard[5][13].setIcon(new ImageIcon(greenRobotIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

        yellowRobot = new RobotPieces("YELLOW", "");
        yellowRobot.setRobotRowCoordinate(3);
        yellowRobot.setRobotColumnCoordinate(10);
        gridSquaresGameBoard[3][10].addRobotToSquare(yellowRobot);
        gridSquaresGameBoard[3][10].setIcon(new ImageIcon(yellowRobotIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

        blueRobot = new RobotPieces("BLUE", "");
        blueRobot.setRobotRowCoordinate(0);
        blueRobot.setRobotColumnCoordinate(0);
        gridSquaresGameBoard[0][0].addRobotToSquare(blueRobot);
        gridSquaresGameBoard[0][0].setIcon(new ImageIcon(blueRobotIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

    }


    private void addPanelsToGetContentPane() {
        // now add the top and bottom panels to the main frame
        getContentPane().setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        leftPanel.add(topLeftPanel);
        leftPanel.add(bottomLeftPanel);
        getContentPane().add(leftPanel, BorderLayout.EAST);
        //getContentPane().add(topLeftPanel, BorderLayout.NORTH);
        getContentPane().add(bottomPanel, BorderLayout.CENTER);        // needs to be center or will draw too small
        //getContentPane().add(bottomLeftPanel, BorderLayout.EAST);
    }

    private void createPlayersAndConnectRobots() {
        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();

        //TODO Set all to human now, THIS WILL NEED TO BE CHANGED to connect to game settings.

        player1.setPlayerTypeToHuman();
        player2.setPlayerTypeToHuman();
        player3.setPlayerTypeToHuman();
        player4.setPlayerTypeToHuman();

        player1.setPlayersRobot(redRobot);
        player2.setPlayersRobot(greenRobot);
        player3.setPlayersRobot(yellowRobot);
        player4.setPlayersRobot(blueRobot);

        player1.setPlayerName("Player 1");
        player2.setPlayerName("Player 2");
        player3.setPlayerName("Player 3");
        player4.setPlayerName("Player 4");
    }

    private void startBiddingRound() {
        //ATimer biddingTimer = new ATimer();

        int currentPlayer1Bid = 0;
        int currentPlayer2Bid = 0;
        int currentPlayer3Bid = 0;
        int currentPlayer4Bid = 0;

        //biddingTimer.startTimer();

        //DUMMYCODE TO REMOVE
        //this.player1.setBidNumber(5);
        //this.player2.setBidNumber(100);
        //this.player3.setBidNumber(100);
        //this.player4.setBidNumber(100);
        //DUMMYCODE TO REMOVE ABOVE

        lockAllBidsButton.addActionListener( (ActionEvent e) -> biddingPhase2(currentPlayer1Bid, currentPlayer2Bid, currentPlayer3Bid, currentPlayer4Bid));

    }

    protected String format(Duration duration){
        long hours = duration.toHours();
        long mins = duration.minusHours(hours).toMinutes();
        long seconds = duration.minusMinutes(mins).toMillis() / 1000;
        return String.format("%02d seconds", seconds);
    }

    private void biddingPhase2(int currentPlayer1Bid, int currentPlayer2Bid, int currentPlayer3Bid, int currentPlayer4Bid) {
        //while (biddingTimer.hasBiddingTimeStopped() == false) {
            /*if (currentPlayer1Bid != player1.getBidNumber()) {
                currentPlayer1Bid = player1.getBidNumber();
            }
            if (currentPlayer2Bid != player2.getBidNumber()) {
                currentPlayer2Bid = player1.getBidNumber();
            }
            if (currentPlayer3Bid != player3.getBidNumber()) {
                currentPlayer3Bid = player3.getBidNumber();
            }
            if (currentPlayer4Bid != player4.getBidNumber()) {
                currentPlayer4Bid = player4.getBidNumber();
            }*/
        //}

        //Lock in the bids and the bid order.

            player1.setBidNumber(movesList1.getSelectedIndex());
            player1.setBidOrder(bidOrderList1.getSelectedIndex() + 1); //Indexing muat start here at 1, not 0.

            player2.setBidNumber(movesList2.getSelectedIndex());
            player2.setBidOrder(bidOrderList2.getSelectedIndex() + 1); //Indexing muat start here at 1, not 0.

            player3.setBidNumber(movesList3.getSelectedIndex());
            player3.setBidOrder(bidOrderList2.getSelectedIndex() + 1); //Indexing muat start here at 1, not 0.

            player4.setBidNumber(movesList4.getSelectedIndex());
            player4.setBidOrder(bidOrderList4.getSelectedIndex() + 1); //Indexing muat start here at 1, not 0.

        //First check that no player claimed the same number of bids
        if (didMoreThanOnePlayerClaimSameBidOrder() == true){
            String message = "Sorry, someone claimed to bid at the same time as another. Fix your order, and lock in bids again using the button.";
            JOptionPane messageThatPlayerWon = new JOptionPane();
            messageThatPlayerWon.showMessageDialog(new JFrame(), message);

        }
        else{
            //Determine the player with the lowest bids AND who bid first if there are duplicate bids.
            playerThatGoesNow = determinePlayerThatShowsMovesNow();


            //Now check if there are others with the same bid

            // if (biddingTimer.hasBiddingTimeStopped()){
            String message = "Ok, so " + playerThatGoesNow.getPlayerName() + ", click your proposed path and then click Verify.";
            JOptionPane messageThatPlayerWon = new JOptionPane();
            messageThatPlayerWon.showMessageDialog(new JFrame(), message);
            //}
            //  }



//test
            //int playerCount = 0;

            //boolean winnerOfRoundFound = false;

            //while ((winnerOfRoundFound == false) && (playerCount != 4)) {


            //TODO Now get that person to click through their squares
            //letChosenPlayerClickTheirPath();

            //First, enable all the gridsquares to be clickable for the user.
            //enableAllGridSquaresClickable();

            this.clickRecorder = new ClickRecorder();

            addActionListenersToAllGridSquares();

            verifyButton.addActionListener((ActionEvent e) -> {
                finishBiddingProcess();
            });



        }


    }


    private void finishBiddingProcess(){
            /*while (this.readyToVerify == false) {
            /*try {
                Thread.sleep(10000000);
            } catch (InterruptedException e){
            }*/
            /*}*/

            //this.readyToVerify = false; //Reset
            //verifyButton.removeActionListener((ActionEvent e ) -> {finishBiddingProcess();}; //Reset

            removeAddedActionListenersToAllGridSquares();

            GridSquare[] arrayOfSquaresPlayerClickedInOrder = clickRecorder.getArrayOfClickedSquares();


            //DUMMY CODE Will need to be removed.
            //GridSquare[] squaresPlayerClicked = {new GridSquare(-1, -1), new GridSquare(-1, -1), new GridSquare(-1, -1)};

            //DummyCode
            GridSquare squareWithCurrentTargetTile = gridSquaresGameBoard[13][5]; //It will be this one for now the red hex star one.


            VerifyBidProcessor verifyBidProcessor = new VerifyBidProcessor(playerThatGoesNow.getBidNumber(), arrayOfSquaresPlayerClickedInOrder, playerThatGoesNow.getPlayersRobot(), squareWithCurrentTargetTile, gridSquaresGameBoard);

            if (verifyBidProcessor.wereMovesLegalAndAccurate() == true) {
                this.winnerOfRoundFound = true;
                String message = "Correct bid! " + playerThatGoesNow.getPlayerName() + " won this round!";
                JOptionPane messageThatPlayerWon = new JOptionPane();
                messageThatPlayerWon.showMessageDialog(new JFrame(), message);
                //playerWithLowestBid.addTargetChipToPlayersCollection(squareWithCurrentTargetTile.getTargetTileOnSquare());
                //squareWithCurrentTargetTile.removeTargetTileFromGridSquare();
                //Show on grid to remove
                moveWinningBiddersRobot(arrayOfSquaresPlayerClickedInOrder);
                giveTargetChipPointToPlayerAndRemoveTargetTileFromBoard(playerThatGoesNow, arrayOfSquaresPlayerClickedInOrder);
                updateNewTargetChipOnBoardAndSquareCoordinates();//Maybe add later.



            } else {
                this.winnerOfRoundFound = false;
                String message = playerThatGoesNow.getPlayerName() + ", sorry, that's not correct. Next player.";
                JOptionPane messageThatPlayerWon = new JOptionPane();
                messageThatPlayerWon.showMessageDialog(new JFrame(), message);

                playerThatGoesNow.setBidNumber(1000000); //Leave loser player out of next finding out next bid.
                playerThatGoesNow.setBidOrder(10000000); //Leave loser player out of next finding out next bid.
                this.playerCount += 1;

                playerThatGoesNow = determinePlayerThatShowsMovesNow();
            }


        if (this.playerCount == 4){
            String message = "Nobody made accurate bids. Begin bidding round again.";
            JOptionPane messageThatPlayerWon = new JOptionPane();
            messageThatPlayerWon.showMessageDialog(new JFrame(), message);
        }


        //How to disable players bids?

    }

    private boolean didMoreThanOnePlayerClaimSameBidOrder(){
        int counter = 0;
        for (int i = 1; i <= 4; i++){
            if (player1.getBidOrder() == i){
                counter += 1;
            }
            if (player2.getBidOrder() == i){
                counter += 1;
            }
            if (player3.getBidOrder() == i){
                counter += 1;
            }
            if (player4.getBidOrder() == i){
                counter += 1;
            }
            if (counter > 2){
                return true;
            }
            else{
                counter = 0; //Reset
            }

        }
        return false;
    }

    /**
     * This method assumes beforehand that it has been shown that nobody claimed the same bid order.
     * @return
     */
    private Player determinePlayerThatShowsMovesNow() {
        int currentLowestBid = player1.getBidNumber();
        Player currentPlayerWithLowestBid = player1;

        if (currentLowestBid > player2.getBidNumber()) {
            currentLowestBid = player2.getBidNumber();
            currentPlayerWithLowestBid = player2;
        }

        if (currentLowestBid > player3.getBidNumber()) {
            currentLowestBid = player3.getBidNumber();
            currentPlayerWithLowestBid = player3;
        }

        if (currentLowestBid > player4.getBidNumber()) {
            currentLowestBid = player4.getBidNumber();
            currentPlayerWithLowestBid = player4;
        }

        //Now check if there is anyone that has the same lowest bid number.
        //If there is, get their bid order. The one who bid first goes first.

        Player currentPlayerToReturn = currentPlayerWithLowestBid;
        int currentBidOrder = currentPlayerToReturn.getBidOrder();



        if ( (currentPlayerToReturn.getBidNumber() == player1.getBidNumber()) && (currentPlayerWithLowestBid.getBidOrder() > player1.getBidOrder()) ){
            currentPlayerToReturn = player1;
        }

        if ((currentPlayerToReturn.getBidNumber() == player2.getBidNumber()) && (currentPlayerWithLowestBid.getBidOrder() > player2.getBidOrder()) ){
            currentPlayerToReturn = player2;
        }

        if ((currentPlayerToReturn.getBidNumber() == player3.getBidNumber()) && (currentPlayerWithLowestBid.getBidOrder() > player3.getBidOrder()) ){
            currentPlayerToReturn = player3;
        }

        if ((currentPlayerToReturn.getBidNumber() == player4.getBidNumber()) && (currentPlayerWithLowestBid.getBidOrder() > player4.getBidOrder()) ){
            currentPlayerToReturn = player4;
        }

        return currentPlayerToReturn;
    }

    private void enableAllGridSquaresClickable() {
        for (int row = 0; row < this.gridSquaresGameBoard.length; row++) {
            for (int column = 0; column < this.gridSquaresGameBoard[row].length; column++) {
                this.gridSquaresGameBoard[row][column].setEnabled(true);
            }
        }
    }

    private void disableAllGridSquaresToNotClickable() {
        for (int row = 0; row < this.gridSquaresGameBoard.length; row++) {
            for (int column = 0; column < this.gridSquaresGameBoard[row].length; column++) {
                this.gridSquaresGameBoard[row][column].setEnabled(false);
            }
        }
    }


    private void letChosenPlayerClickTheirPath() {


    }


    private void addActionListenersToAllGridSquares() {
        for (int row = 0; row < this.gridSquaresGameBoard.length; row++) {
            for (int column = 0; column < this.gridSquaresGameBoard[row].length; column++) {
                int finalRow = row;
                int finalColumn = column;
                this.gridSquaresGameBoard[finalRow][finalColumn].addActionListener((ActionEvent e) -> {
                    clickRecorder.recordClickedGridSquare(this.gridSquaresGameBoard[finalRow][finalColumn]);
                });
            }
        }
    }


    private void removeAddedActionListenersToAllGridSquares(){
        for (int row = 0; row < this.gridSquaresGameBoard.length; row++) {
            for (int column = 0; column < this.gridSquaresGameBoard[row].length; column++) {
                int finalRow = row;
                int finalColumn = column;

                this.gridSquaresGameBoard[finalRow][finalColumn].removeActionListener((ActionEvent e) -> {
                    clickRecorder.recordClickedGridSquare(this.gridSquaresGameBoard[finalRow][finalColumn]);
                });
            }
        }

    }

private void moveWinningBiddersRobot(GridSquare [] playersClickedSquares){ //JUNAID LOOK AT THIS!
        RobotPieces playersRobot = playerThatGoesNow.getPlayersRobot();

        //Get the gridsquare the robot is on.
    //Get robot's icon on the gridsquare
    int squareRowCoord = playersClickedSquares[0].getSquaresRowCoordinate();
    int squareColumnCoord = playersClickedSquares[0].getSquaresColumnCoordinate();

    Icon robotIcon = gridSquaresGameBoard[squareRowCoord][squareColumnCoord].getIcon();

    for (int i = 1; i < playersClickedSquares.length; i++) {

        //Remove the icon from the gridsquare
        gridSquaresGameBoard[squareRowCoord][squareColumnCoord].setIcon(null);
        gridSquaresGameBoard[squareColumnCoord][squareColumnCoord].setVisible(true); //Force it to show?

        //Wait a few seconds
        try {
            Thread.sleep(200);
        } catch (Exception e) {

        }

        //Get new coordinates
        squareRowCoord = playersClickedSquares[i].getSquaresRowCoordinate();
        squareColumnCoord = playersClickedSquares[i].getSquaresColumnCoordinate();

        gridSquaresGameBoard[squareRowCoord][squareColumnCoord].setIcon(robotIcon);//This may need to be changed.
        gridSquaresGameBoard[squareColumnCoord][squareColumnCoord].setVisible(true); //Force it to show?
        //Wait a few seconds
        try {
            Thread.sleep(200);
        } catch (Exception e) {

        }

        //update now the robot's own coordinates
        playersRobot.setRobotRowCoordinate(squareRowCoord);//This changes the players robot right?
        playersRobot.setRobotColumnCoordinate(squareColumnCoord);//This changes the players robot right?


        //.setIcon(new ImageIcon(redCircleIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
    }


}

private void makeTargetChipsAndPutLatestOnBoard(){
    redCircleTargetChip = new TargetChip("RED", "CIRCLE", new ImageIcon("redcircle.jpg"), 1, 4);
    redSquareTargetChip = new TargetChip("RED", "SQUARE", new ImageIcon("redsquare.jpg"), 1, 13);
    redTriangleTargetChip = new TargetChip("RED", "TRIANGLE", new ImageIcon("redtriangle.jpg"), 10, 8 );
    redHexTargetChip = new TargetChip("RED", "HEX", new ImageIcon("redhex.jpg"), 13, 5);

    blueCircleTargetChip = new TargetChip("BLUE", "CIRCLE", new ImageIcon("bluecircle.jpg"), 9, 1);
    blueSquareTargetChip = new TargetChip("BLUE", "SQUARE", new ImageIcon("bluesquare.jpg"), 6, 3);
    blueTriangleTargetChip = new TargetChip("BLUE", "TRIANGLE", new ImageIcon("bluetriangle.jpg"), 2, 9);
    blueHexTargetChip = new TargetChip("BLUE", "HEX", new ImageIcon("bluehex.jpg"), 13, 10);

    yellowCircleTargetChip = new TargetChip("YELLOW", "CIRCLE", new ImageIcon("yellowcircle.jpg"), 6, 11);
    yellowSquareTargetChip = new TargetChip("YELLOW", "SQUARE", new ImageIcon("yellowsquare.jpg"), 6, 11);
    yellowTriangleTargetChip = new TargetChip("YELLOW", "TRIANGLE", new ImageIcon("yellowtriangle.jpg"), 14, 3);
    yellowHexTargetChip = new TargetChip("YELLOW", "HEX", new ImageIcon("yellowhex.jpg"), 3, 6);

    greenCircleTargetChip = new TargetChip("GREEN", "CIRCLE", new ImageIcon("greencircle.jpg"), 11, 13);
    greenSquareTargetChip = new TargetChip("GREEN", "SQUARE", new ImageIcon("greensquare.jpg"), 10, 4);
    greenTriangleTargetChip = new TargetChip("GREEN", "TRIANGLE", new ImageIcon("greentriangle.jpg"), 2, 1);
    greenHexTargetChip = new TargetChip("GREEN", "HEX", new ImageIcon("greenhex.jpg"), 5, 14);

    vortexTargetChip = new TargetChip("MULTICOLOR", "VORTEX", new ImageIcon("vortex.jpeg"), 8, 5);

    //Now, put all into an array.

    TargetChip [] firstArray = {redHexTargetChip, redCircleTargetChip, redSquareTargetChip, redTriangleTargetChip,
                                blueCircleTargetChip, blueHexTargetChip, blueSquareTargetChip, blueTriangleTargetChip,
                                yellowCircleTargetChip, yellowHexTargetChip, yellowSquareTargetChip, yellowTriangleTargetChip,
                                vortexTargetChip};

    /*//Now, randomize the array later. //TODO FOR NOW HEX IS FIRST FOR DEMO.

    randomTargetChipArray = new TargetChip[17];
    randomTargetChipArray[0] = redHexTargetChip;

    for (int i = 1; i < firstArray.length; i++){
        int [] numbersUsed = {1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}; //TODO Will need to change the first 1.
        int randomNumber = getRandomNumber();
        if ()

        */

    this.randomTargetChipArray = firstArray; //TODO WILL NEED TO BE REMOVED LATER

    updateNewTargetChipOnBoardAndSquareCoordinates();
    }




    private void updateNewTargetChipOnBoardAndSquareCoordinates(){

        boolean nextChipFound = false;
        int index = 0;
        while (nextChipFound == false){

            if (this.randomTargetChipArray[index] != null){
                this.currentChipToDisplay = this.randomTargetChipArray[index];
                this.randomTargetChipArray[index] = null;
                nextChipFound = true;
            }
            else{
                index += 1;
            }

        }

        ImageIcon iconToUpdate = this.currentChipToDisplay.getImageIcon();

        //Update the icon on the four squares on the board.
        //Square 1
        gridSquaresGameBoard[7][7].setIcon(new ImageIcon(iconToUpdate.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
//.setIcon(new ImageIcon(yellowSquareIcon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[7][8].setIcon(new ImageIcon(iconToUpdate.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[8][7].setIcon(new ImageIcon(iconToUpdate.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        gridSquaresGameBoard[8][8].setIcon(new ImageIcon(iconToUpdate.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));

        //Now find the square that matches

        this.currentTargetSquare = gridSquaresGameBoard[this.currentChipToDisplay.getRelatedGridSquaresRowCoord()][this.currentChipToDisplay.getRelatedGridSquaresColumnCoord()];


    }

    private void showInstructions(){
        String message = "***Instructions To Be Added Later.***";
        JOptionPane messageThatPlayerWon = new JOptionPane();
        messageThatPlayerWon.showMessageDialog(new JFrame(), message);
    }

    private void saveGame(){
        String message = "***Save Game Option to be added later.***";
        JOptionPane messageThatPlayerWon = new JOptionPane();
        messageThatPlayerWon.showMessageDialog(new JFrame(), message);
    }

    private void newGame(){
        String message = "***New Game Option to be added later.***";
        JOptionPane messageThatPlayerWon = new JOptionPane();
        messageThatPlayerWon.showMessageDialog(new JFrame(), message);
    }

    private void giveTargetChipPointToPlayerAndRemoveTargetTileFromBoard(Player playerThatWonBid, GridSquare [] playersClickedSquares){
        playerThatWonBid.addTargetChipToPlayersCollection(currentChipToDisplay);
        GridSquare squareWithTile = playersClickedSquares[playersClickedSquares.length - 1];
        //To ensure, remove it from the source, the actual Gameboard [][]
        this.gridSquaresGameBoard[squareWithTile.getSquaresRowCoordinate()][squareWithTile.getSquaresColumnCoordinate()].removeTargetTileFromGridSquare();
    }


}
