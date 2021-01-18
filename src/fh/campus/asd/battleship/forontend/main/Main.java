package fh.campus.asd.battleship.forontend.main;


import fh.campus.asd.battleship.backend.models.ImageShip;
import fh.campus.asd.battleship.backend.models.Player;
import fh.campus.asd.battleship.backend.models.Ship;
import fh.campus.asd.battleship.forontend.enums.Direction;
import fh.campus.asd.battleship.helper.GUIConfig;
import fh.campus.asd.battleship.helper.GUILabelsHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.log4j.Logger;

import java.io.File;



public class Main extends Application {

    private static final Logger log;

    static {
        log = Logger.getLogger(Main.class);
    }

    private final Player player1 = new Player();
    private final Player player2 = new Player();

    private double pressedX;
    private double pressedY;
    private int gameround = 1;
    private boolean shipscomplete = false; //zu testzwecken auf true später muss auf false gestellt werden

    private final Button buttonSaveShipsLeft = new Button(GUILabelsHelper.SAVE_SHIPS_LABEL);
    private final Button buttonSaveShipsRight = new Button(GUILabelsHelper.SAVE_SHIPS_LABEL);
    private final Button newGame = new Button(GUILabelsHelper.NEW_GAME_NAME);
    private final Button exit = new Button(GUILabelsHelper.EXIT_GAMES_LABEL);
    private final Button reset = new Button(GUILabelsHelper.RESTART_GAMES_LABEL);
    private final Button seeShips1 = new Button(GUILabelsHelper.SHOW_SHIPS_LABEL);
    private final Button seeShips2 = new Button(GUILabelsHelper.SHOW_SHIPS_LABEL);
    private final Button cont = new Button(GUILabelsHelper.CONTINUE_LABEL);

    private final ImageView startmenu = new ImageView(GUILabelsHelper.FILE_PATH_START);
    private final ImageView wonleft = new ImageView(GUILabelsHelper.FILE_PATH_PLAYER1_WON);
    private final ImageView wonright = new ImageView(GUILabelsHelper.FILE_PATH_PLAYER2_WON);
    private final ImageView maskleftfield = new ImageView(GUILabelsHelper.FILE_PATH_ISLAND_BELOW_LEFT);
    private final ImageView maskrightfield = new ImageView(GUILabelsHelper.FILE_PATH_ISLAND_BELOW_RIGHT);

    private final Rectangle indicate1 = new Rectangle(GUIConfig.indicate1V1, GUIConfig.indicate1V2, GUIConfig.indicate1V3, GUIConfig.indicate1V4);
    private final Rectangle indicate2 = new Rectangle(GUIConfig.indicate2V1, GUIConfig.indicate2V2, GUIConfig.indicate2V3, GUIConfig.indicate2V4);

    private final Media bomb = new Media(new File(GUILabelsHelper.BOMB_WAV).toURI().toString());
    private final MediaPlayer bombplay = new MediaPlayer(bomb);
    private final Media miss = new Media(new File(GUILabelsHelper.MISS_WAV).toURI().toString());
    private final MediaPlayer missplay = new MediaPlayer(miss);
    private final Media music = new Media(new File(GUILabelsHelper.MUSIC_WAV).toURI().toString());
    private final MediaPlayer musicplay = new MediaPlayer(music);
    private final Media winner = new Media(new File(GUILabelsHelper.WINNER_WAV).toURI().toString());
    private final MediaPlayer winnerplay = new MediaPlayer(winner);

    private final Image[] bships = {
            new Image(GUILabelsHelper.RES_ONE_TWO),
            new Image(GUILabelsHelper.RES_ONE_THREE),
            new Image(GUILabelsHelper.RES_ONE_FOUR),
            new Image(GUILabelsHelper.RES_ONE_FIVE)
    };

    //Schiffe SPieler 1
    private final ImageShip[] imageShip1 = new ImageShip[]{
                new ImageShip(1520, 640, 2, bships[0]),
                new ImageShip(1520, 640, 2, bships[0]),
                new ImageShip(1520, 640, 2, bships[0]),
                new ImageShip(1520, 640, 2, bships[0]),
                new ImageShip(1520, 720, 3, bships[1]),
                new ImageShip(1520, 720, 3, bships[1]),
                new ImageShip(1520, 720, 3, bships[1]),
                new ImageShip(1520, 800, 4, bships[2]),
                new ImageShip(1520, 800, 4, bships[2]),
                new ImageShip(1520, 880, 5, bships[3])
        };

    //Schiffe Spieler 2
    private final ImageShip[] imageShip0 = new ImageShip[]{
                new ImageShip(1800 - 1520 - 3 * 40, 640, 2, bships[0]),
                new ImageShip(1800 - 1520 - 3 * 40, 640, 2, bships[0]),
                new ImageShip(1800 - 1520 - 3 * 40, 640, 2, bships[0]),
                new ImageShip(1800 - 1520 - 3 * 40, 640, 2, bships[0]),
                new ImageShip(1800 - 1520 - 3 * 40, 720, 3, bships[1]),
                new ImageShip(1800 - 1520 - 3 * 40, 720, 3, bships[1]),
                new ImageShip(1800 - 1520 - 3 * 40, 720, 3, bships[1]),
                new ImageShip(1800 - 1520 - 3 * 40, 800, 4, bships[2]),
                new ImageShip(1800 - 1520 - 3 * 40, 800, 4, bships[2]),
                new ImageShip(1800 - 1520 - 3 * 40, 880, 5, bships[3])
        };
    
    private Pane battleshipcontainer = new Pane();

    private void drawGUI() {
        initMusic();
        initBattleshipContainer();
        initButtons();
        initSeeShips();
        indicate1.setFill(Color.RED);
        indicate2.setFill(Color.RED);
        initBattleshipContainerPart2();
        setAllVisible();
        changeMask();
    }

    private void setAllVisible() {
        startmenu.setVisible(true);
        reset.setVisible(false);
        maskleftfield.setVisible(false);
        maskrightfield.setVisible(false);
        seeShips1.setVisible(false);
        seeShips2.setVisible(false);
        indicate1.setVisible(false);
        indicate2.setVisible(false);
    }

    private void initBattleshipContainerPart2() {
        battleshipcontainer.getChildren().add(seeShips1);
        battleshipcontainer.getChildren().add(seeShips2);
        battleshipcontainer.getChildren().addAll(buttonSaveShipsLeft, buttonSaveShipsRight, maskleftfield, maskrightfield,
                startmenu, indicate1, indicate2);
    }

    private void initSeeShips() {
        seeShips1.setLayoutX(1520);
        seeShips1.setLayoutY(550);
        seeShips1.setPrefSize(120, 10);
        seeShips1.setOnAction(
                event -> changeMask()
        );

        seeShips2.setLayoutX(160);
        seeShips2.setLayoutY(550);
        seeShips2.setPrefSize(120, 10);
        seeShips2.setOnAction(
                event -> changeMask()
        );
    }

    private void initButtons() {
        buttonSaveShipsLeft.setLayoutX(160);
        buttonSaveShipsLeft.setLayoutY(500);
        buttonSaveShipsLeft.setPrefSize(120, 10);
        buttonSaveShipsLeft.setOnAction(event -> {
            saveShips(imageShip0, player1, 480, 440 + 440);
            shipsComplete();
        }
        );
        buttonSaveShipsRight.setLayoutX(1520);
        buttonSaveShipsRight.setLayoutY(500);
        buttonSaveShipsRight.setPrefSize(120, 10);
        buttonSaveShipsRight.setOnAction(
                event -> {
                    saveShips(imageShip1, player2, 2 * 440 + 40 + 40, 440 + 440 + 40 + 440);
                    shipsComplete();
                }
        );
    }

    private void initBattleshipContainer() {
        for (int i = 0; i < imageShip0.length; i++)
        {
            battleshipcontainer.getChildren().add(imageShip0[i].getImageView());
            battleshipcontainer.getChildren().add(imageShip1[i].getImageView());
        }

        battleshipcontainer.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED)
            {
                pressedX = event.getSceneX();
                pressedY = event.getSceneY();
                attacks((int) Math.round(pressedX), (int) Math.round(pressedY));
            }
        });
    }

    private void initMusic() {
        musicplay.setCycleCount(500);
        musicplay.play();
    }

    private void activateMask()
    {
        maskleftfield.setVisible(true);
        maskrightfield.setVisible(true);
    }

    private void deactivateMask()
    {
        maskleftfield.setVisible(false);
        maskrightfield.setVisible(false);
    }

    private void changeMask()
    {
        if (gameround % 2 == 1)
        {
            maskleftfield.setVisible(false);
            maskrightfield.setVisible(true);
        } else if (gameround % 2 == 0)
        {
            maskleftfield.setVisible(true);
            maskrightfield.setVisible(false);
        }
    }


    @Override
    public void  start(final Stage primaryStage) {
        BackgroundImage background = new BackgroundImage(new Image(GUILabelsHelper.BACKGROUND_PIC, 1800, 1000,
                true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        maskleftfield.setX(439);
        maskleftfield.setY(519);
        maskrightfield.setX(919);
        maskrightfield.setY(519);


        battleshipcontainer.setBackground(new Background(background));
        drawGUI();

        reset.setLayoutX(440);
        reset.setLayoutY(10);
        reset.setPrefHeight(10);

        reset.setOnAction(event -> {
            reset();
            Scene scenel = new Scene(battleshipcontainer, 1800, 1000);
            primaryStage.setScene(scenel);
            primaryStage.show();
        });
        battleshipcontainer.getChildren().add(reset);
        newGame.setLayoutX(700);
        newGame.setLayoutY(300);
        newGame.setMinSize(400, 150);
        Font font = new Font(30);
        newGame.setFont(font);
        newGame.setOnAction(event -> {
            reset();
            Scene scenel = new Scene(battleshipcontainer, 1800, 1000);
            primaryStage.setScene(scenel);
            primaryStage.show();

        }
        );

        battleshipcontainer.getChildren().add(newGame);


        exit.setLayoutX(700);
        exit.setLayoutY(500);
        exit.setMinSize(400, 150);
        exit.setFont(font);
        exit.setOnAction(event -> System.exit(0)
        );


        battleshipcontainer.getChildren().add(exit);
        cont.setOnAction(
                event -> {
                    reset();
                    reset.setVisible(false);
                    battleshipcontainer.getChildren().add(newGame);
                    battleshipcontainer.getChildren().add(exit);
                    startmenu.setVisible(true);
                    newGame.setVisible(true);
                    exit.setVisible(true);
                    Scene scenel = new Scene(battleshipcontainer, 1800, 1000);
                    primaryStage.setScene(scenel);
                    primaryStage.show();
                }
        );

        Scene scene = new Scene(battleshipcontainer, 1800, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /*Wir berechnen x und y relativ zum jeweiligen spielfeld und kriegen eine zahl zwischen 0 und 9 raus.*/
    private int[] calculateXY(int imageshipx, int imageshipy, int p1x, int p1y, int p2x, int p2y)
    {
        int[] result = new int[2];

        //Checkt ob die Koordinaten vom Schiff im richtigen Feld liegen
        if (imageshipx >= p1x && imageshipx <= p2x && imageshipy >= p1y && imageshipy <= p2y)
        {
            int vectory;
            int vectorx = imageshipx - p1x;
            vectory = imageshipy - p1y;
            result[0] = vectorx / 40;
            result[1] = vectory / 40;
            return result;
        }

        return result;
    }


    private void saveShips(ImageShip[] imageShip, Player player, int p1x, int p2x)
    {


        /*Geht alle Schiffe duch und schaut erstmal ob */
        for (ImageShip imageship : imageShip)
        {
            if (!imageship.isDisable())
            {
                int[] a = calculateXY(imageship.getX(), imageship.getY(), p1x, 560, p2x, 960);

                if (a.length > 0)
                {
                    if (player.getArea().setShip(a[0], a[1], imageship.getLength(), imageship.getDirection(), imageship.getDiffvectorx(), imageship.getDiffvectory()))
                    {
                        imageship.lock();

                    } else
                    {
                        imageship.changePosition(0, 0);
                        imageship.rotateTo(Direction.RIGHT);
                    }
                } else
                {
                    imageship.changePosition(0, 0);
                    imageship.rotateTo(Direction.RIGHT);

                }
            }
        }
        if (player.getArea().isFleetComplete())
        {
            gameround++;
            if (player == player1)
            {
                changeMask();
                buttonSaveShipsLeft.setVisible(false);


            } else
            {
                buttonSaveShipsRight.setVisible(false);
                changeMask();
                seeShips1.setVisible(true);
                seeShips2.setVisible(true);
                indicate1.setVisible(true);


            }
            if (player1.getArea().isFleetComplete() && player2.getArea().isFleetComplete())
            {
                activateMask();
            }

        }
    }

    private void attacks(int x, int y)
    {
        int[] a;
        if (!(player1.getArea().gameOver() || player2.getArea().gameOver()))
        {
            if (!shipscomplete) {
                return;
            }
            log.debug(GUILabelsHelper.SCHIEFE_DONE);
            if (gameround % 2 == 1)
            {
                a = calculateXY(x, y, 480, 80, 880, 880);

                if (a.length > 0)
                    if (!player1.attackPossible(a[0], a[1])) {
                    } else {
                        if (player2.getArea().attack(a[0], a[1])) {
                            drawAttack(a[0], a[1], x, y, player2);
                            player1.saveAttack(a[0], a[1]);
                            activateMask();
                            bombplay.stop();
                            bombplay.play();

                        } else {
                            drawMiss(x, y);
                            player1.saveAttack(a[0], a[1]);
                            activateMask();
                            indicate1.setVisible(false);
                            indicate2.setVisible(true);
                            missplay.stop();
                            missplay.play();
                        }
                    }
                if (player2.getArea().gameOver())
                {
                    log.debug(GUILabelsHelper.PLAYER_ONE_WON);
                    deactivateMask();
                    seeShips1.setVisible(false);
                    seeShips2.setVisible(false);
                    reset.setVisible(false);
                    battleshipcontainer.getChildren().add(wonleft);
                    wonleft.setX(50);
                    wonleft.setY(520);
                    winnerplay.stop();
                    winnerplay.play();
                    battleshipcontainer.getChildren().add(cont);
                    cont.setLayoutX(160);
                    cont.setLayoutY(850);
                    cont.setVisible(true);
                }

            } else
            {
                a = calculateXY(x, y, 440 + 40 + 10 * 40 + 2 * 40, 40 + 40, 440 + 440 + 440 + 40, 440 + 40);
                if (a.length > 0 && player2.attackPossible(a[0], a[1])) {
                    if (player1.getArea().attack(a[0], a[1])) {
                        drawAttack(a[0], a[1], x, y, player1);
                        player2.saveAttack(a[0], a[1]);
                        activateMask();
                        bombplay.stop();
                        bombplay.play();

                    } else {
                        drawMiss(x, y);
                        player2.saveAttack(a[0], a[1]);
                        activateMask();
                        indicate1.setVisible(true);
                        indicate2.setVisible(false);
                        missplay.stop();
                        missplay.play();
                    }

                }
                if (player1.getArea().gameOver())
                {
                    log.debug(GUILabelsHelper.PLAYER_TWO_WON);
                    deactivateMask();
                    seeShips1.setVisible(false);
                    seeShips2.setVisible(false);
                    reset.setVisible(false);
                    battleshipcontainer.getChildren().add(wonright);
                    wonright.setX(1450);
                    wonright.setY(520);
                    winnerplay.stop();
                    winnerplay.play();
                    battleshipcontainer.getChildren().add(cont);
                    cont.setLayoutX(1520);
                    cont.setLayoutY(850);
                    cont.setVisible(true);

                }
            }

        }
    }

    /*Wasserzeichen, gerundet auf die richtige Stelle setzen*/
    private void drawMiss(double x, double y)
    {
        int diffx = (int) x % 40;
        x -= diffx;

        int diffy = (int) y % 40;
        y -= diffy;
        ImageView miss1;
        miss1 = new ImageView(GUILabelsHelper.WATER_MAKER);
        miss1.setX(x);
        miss1.setY(y);
        battleshipcontainer.getChildren().add(miss1);
        gameround++;

    }

    /*Feuerzeichen, gerundet auf die richtige Stelle. Wenn Schiff zerstört, richtiges destroyed Schiff setzen*/
    private void drawAttack(int xx, int yy, double xreal, double yreal, Player player)
    {
        ImageShip imageShipl;

        int diffx = (int) xreal % 40;
        xreal -= diffx;

        int diffy = (int) yreal % 40;
        yreal -= diffy;

        ImageView hit = new ImageView(GUILabelsHelper.HIT_PNG);
        hit.setX(xreal);
        hit.setY(yreal);
        battleshipcontainer.getChildren().addAll(hit);


        Image image = new Image(GUILabelsHelper.SHIP_DESTROYED);
        /*Objekt ship wird entweder null oder ein Schiff zugewiesen (Siehe Klasse Ship, Methode isDestroyed). Wenn
        das Schiff zerstört ist, wird im switch case gefragt welche Länge und dementsprechen setzen wir das Schiff*/
        Ship ship = player.getArea().isDestroyed(xx, yy);

        if (ship != null)
        {
            log.debug("zerstört");
            switch (ship.getLength())
            {
                case 0:
                    break;
                case 2:
                    image = new Image(GUILabelsHelper.SHIP_DESTROYED_ONE_TWO_RES);
                    break;
                case 3:
                    image = new Image(GUILabelsHelper.SHIP_DESTROYED_ONE_THREE_RES);
                    break;
                case 4:
                    image = new Image(GUILabelsHelper.SHIP_DESTROYED_ONE_FOUR_RES);
                    break;
                case 5:
                    image = new Image(GUILabelsHelper.SHIP_DESTROYED_ONE_FIVE_RES);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + ship.getLength());
            }

            int x;
            //*40 um auf unsere Spielfeldkoordinaten zu kommen
            x = ship.getX() * 40;
            int y = ship.getY() * 40;
            //Wird immer in das gegenüberliegende Feld gesetzt, deshalb stehen hier die Koordinaten vom Spieler 2
            if (player == player1)
            {
                x += 2 * 440 + 40 + 40;

            } else
            {
                x += (440 + 40);


            }
            y += 2 * 40;

            /*Schiff kreiert und zum Battleshipcontainer dazugehaut und lock==true, um es nicht bewegbar zu machen*/
            imageShipl = new ImageShip(x - ship.getDivx(), y - ship.getDivy(), ship.getLength(), image);
            battleshipcontainer.getChildren().add(imageShipl.getImageView());
            imageShipl.rotateTo(ship.getDirection());
            imageShipl.lock();
        }
    }

    //Alle Schiffe beider Spieler sind gesetzt, dann true
    private void shipsComplete()
    {
        if (player1.getArea().isFleetComplete() && player2.getArea().isFleetComplete())
        {
            this.shipscomplete = true;
        }

    }

    //Für einzelne Methoden, siehe entsprechende Klassen. Canvas wird zurückgesetzt
    private void reset()
    {

        for (int i = 0; i < imageShip0.length; i++)
        {
            imageShip1[i].rotateTo(Direction.RIGHT);
            imageShip0[i].rotateTo(Direction.RIGHT);
            imageShip0[i].reset();
            imageShip1[i].reset();

        }
        player1.getArea().removeAll();
        player2.getArea().removeAll();
        player1.reset();
        player2.reset();
        gameround = 1;
        shipscomplete = false;
        buttonSaveShipsRight.setVisible(true);
        buttonSaveShipsLeft.setVisible(true);
        battleshipcontainer = new Pane();
        BackgroundImage background = new BackgroundImage(new Image(GUILabelsHelper.BACKGROUND_BATTLESHIP_IMAGE, 1800, 1000,
                true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        battleshipcontainer.setBackground(new Background(background));
        drawGUI();
        battleshipcontainer.getChildren().add(reset);
        reset.setVisible(true);
        startmenu.setVisible(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
