
/**
 * Represents the GUI for the game
 * 
 * @author Sean, Patricia Lemmen Meyer
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import enemy.Enemy;
import enemy.FastEnemy;
import enemy.GoblinEnemy;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import projectile.Projectile;
import tile.Tile;
import tower.BasicTower;
import tower.DogTower;
import tower.ElectricTower;
import tower.GhostTower;
import tower.KingTower;
import tower.MageTower;
import tower.Tower;
import tower.TreeTower;
import tower.VolcanoTower;

public class TowerDefenseGUI extends Application implements Observer {

	private BorderPane window;
	private Scene scene;
	private Label currency;
	private Label health;
	private GridPane gridPane;
	private Canvas c;
	private GraphicsContext gc;
	private TowerDefenseController controller;
	private int currLevel;
	private Timeline tl;
	private AnimationTimer at;

	private int tickRate;

	/**
	 * starts
	 */
	public void start(Stage stage) {
		stage.setTitle("Tower Defense");
		window = new BorderPane();

		Label welcome = new Label("Welcome to fantasy tower defence!");
		Button one = new Button("Level 1");
		Button two = new Button("Level 2");
		Button three = new Button("Level 3");

		one.setOnAction(event1 -> {
			currLevel = 1;
			stage.setScene(startGame());
			Button start = new Button("start");
			Button stop = new Button("stop");
			HBox hb = new HBox();
			hb.getChildren().addAll(start, stop);

		});

		KeyFrame k2 = new KeyFrame(Duration.millis(10), e2 -> {
		});

		tl = new Timeline(k2);
		tl.setCycleCount(Timeline.INDEFINITE);
		tl.play();

		window = new BorderPane();
		scene = new Scene(window, 600, 512);
		gridPane = new GridPane();
		window.setCenter(gridPane);

		stage.setScene(scene);

		two.setOnAction(event -> {
			currLevel = 2;
			stage.setScene(startGame());
			Button start = new Button("start");
			Button stop = new Button("stop");
			HBox hb = new HBox();
			hb.getChildren().addAll(start, stop);
		});

		three.setOnAction(event -> {
			// level 3
			currLevel = 3;
			stage.setScene(startGame());
		});

		HBox hb = new HBox();
		hb.getChildren().addAll(one, two, three);
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(10);
		window.setCenter(hb);

		// adding main menu backround
		BackgroundImage bg = new BackgroundImage(new Image("NewArt/main menu 2.png", 700, 512, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		// the set to node
		window.setBackground(new Background(bg));

		// scene = new Scene(window, 600, 512);
		stage.setTitle("Tower Defense");

		stage.show();

	}

	public Scene startGame() {

		tickRate = 2;

		controller = new TowerDefenseController(new TowerDefenseModel(this, currLevel));

		AnimationTimer at = new AnimationTimer() {
			@Override
			public void handle(long now) {
				for (int i = 0; i < tickRate; i++) {
					controller.tick();
				}
			}
		};

		window = new BorderPane();

		gridPane = new GridPane();
		window.setCenter(gridPane);

		ToggleGroup tg2 = new ToggleGroup();
		ToggleButton pause = new ToggleButton("Pause");
		ToggleButton normal = new ToggleButton("Normal");
		normal.setSelected(true);
		ToggleButton fastForward = new ToggleButton("Fast Forward");

		pause.setToggleGroup(tg2);

		pause.setOnAction(event -> {
			tickRate = 0;
		});

		normal.setToggleGroup(tg2);

		normal.setOnAction(event -> {
			tickRate = 2;
		});

		fastForward.setToggleGroup(tg2);
		fastForward.setOnAction(event -> {
			tickRate = 4;
		});

		ToggleGroup tg = new ToggleGroup();
		Label l = new Label("Current level: " + currLevel);
		ToggleButton sell = new ToggleButton("Sell");
		Label buy = new Label("Towers available:");

		ToggleButton basicTower = new ToggleButton("Basic Tower \n Cost: $10");
		Image image = new Image(getClass().getResourceAsStream("NewArt/Tower.png")); // change to name of new // tower
																						// image
		basicTower.setGraphic(new ImageView(image));

		ToggleButton electricTower = new ToggleButton("Electric Tower \n Cost: $15");
		Image electricTowerImage = new Image(getClass().getResourceAsStream("NewArt/electric tower.png")); // change to
																											// name of
																											// new //
																											// tower
																											// image
		electricTower.setGraphic(new ImageView(electricTowerImage));

		ToggleButton dogTower = new ToggleButton("Dog Tower \n Cost: $40");
		Image dogTowerImage = new Image(getClass().getResourceAsStream("NewArt/dog tower.png")); // change to name of
																									// new // tower
																									// image
		dogTower.setGraphic(new ImageView(dogTowerImage));

		ToggleButton treeTower = new ToggleButton("Tree Tower \n Cost: $100");
		Image treeTowerImage = new Image(getClass().getResourceAsStream("NewArt/TreeTower.png")); // change to name of
																									// new // tower
																									// image
		treeTower.setGraphic(new ImageView(treeTowerImage));

		ToggleButton volcanoTower = new ToggleButton("Volcano Tower \n Cost: $500");
		Image volcanoTowerImage = new Image(getClass().getResourceAsStream("NewArt/Volcano.png")); // change to name of
																									// new // tower
																									// image
		volcanoTower.setGraphic(new ImageView(volcanoTowerImage));

		ToggleButton mageTower = new ToggleButton("Mage Tower \n Cost: $50");
		Image mageTowerImage = new Image(getClass().getResourceAsStream("NewArt/MageTower.png")); // change to name of
																									// new // tower
																									// image
		mageTower.setGraphic(new ImageView(mageTowerImage));

		ToggleButton ghostTower = new ToggleButton("Ghost Tower \n Cost: $30");
		Image ghostTowerImage = new Image(getClass().getResourceAsStream("NewArt/GhostTower.png")); // change to name of
																									// new // tower
																									// image
		ghostTower.setGraphic(new ImageView(ghostTowerImage));

		ToggleButton kingTower = new ToggleButton("King Tower \n Cost: $400");
		Image kingTowerImage = new Image(getClass().getResourceAsStream("NewArt/KingTower.png")); // change to name of
																									// new // tower
																									// image
		kingTower.setGraphic(new ImageView(kingTowerImage));

		sell.setToggleGroup(tg);
		basicTower.setToggleGroup(tg);
		electricTower.setToggleGroup(tg);
		dogTower.setToggleGroup(tg);
		treeTower.setToggleGroup(tg);
		volcanoTower.setToggleGroup(tg);
		mageTower.setToggleGroup(tg);
		ghostTower.setToggleGroup(tg);
		kingTower.setToggleGroup(tg);

		Scene game = new Scene(window, 850, 512);

		game.setOnMouseClicked(event -> {

			int xPos = (int) event.getX() / 64;
			int yPos = (int) event.getY() / 64;

			Tile t = controller.getTile(xPos, yPos);

			if (basicTower.isSelected() && t != null && t.getBuildAble()) {

				controller.placeTower(new BasicTower(64 * xPos, 64 * yPos), xPos, yPos);

			}
			if (electricTower.isSelected() && t != null && t.getBuildAble()) {

				controller.placeTower(new ElectricTower(64 * xPos, 64 * yPos), xPos, yPos);

			}
			if (dogTower.isSelected() && t != null && t.getBuildAble()) {

				controller.placeTower(new DogTower(64 * xPos, 64 * yPos), xPos, yPos);

			}
			if (treeTower.isSelected() && t != null && t.getBuildAble()) {

				controller.placeTower(new TreeTower(64 * xPos, 64 * yPos), xPos, yPos);

			}
			if (volcanoTower.isSelected() && t != null && t.getBuildAble()) {

				controller.placeTower(new VolcanoTower(64 * xPos, 64 * yPos), xPos, yPos);

			}
			if (ghostTower.isSelected() && t != null && t.getBuildAble()) {

				controller.placeTower(new GhostTower(64 * xPos, 64 * yPos), xPos, yPos);

			}
			if (mageTower.isSelected() && t != null && t.getBuildAble()) {

				controller.placeTower(new MageTower(64 * xPos, 64 * yPos), xPos, yPos);

			}
			if (kingTower.isSelected() && t != null && t.getBuildAble()) {

				controller.placeTower(new KingTower(64 * xPos, 64 * yPos), xPos, yPos);

			}
			if (sell.isSelected() && t != null && t.getTower() != null) {

				controller.sell(xPos, yPos);
			}
		});

		sell.setToggleGroup(tg);
		basicTower.setToggleGroup(tg);
		electricTower.setToggleGroup(tg);
		dogTower.setToggleGroup(tg);
		treeTower.setToggleGroup(tg);
		volcanoTower.setToggleGroup(tg);
		mageTower.setToggleGroup(tg);
		ghostTower.setToggleGroup(tg);
		kingTower.setToggleGroup(tg);

		currency = new Label("Money: " + controller.getCurrency());
		health = new Label("Lives: " + controller.getGetHealth());

		VBox v = new VBox();
		v.getChildren().add(l);
		v.getChildren().add(currency);
		v.getChildren().add(health);
		v.getChildren().add(sell);
		v.getChildren().add(buy);
		// add towers to hboxes
		HBox rowOne = new HBox();
		rowOne.getChildren().add(basicTower);
		rowOne.getChildren().add(electricTower);
		v.getChildren().add(rowOne);
		// add second row of tower buttons
		HBox rowTwo = new HBox();
		rowTwo.getChildren().add(dogTower);
		rowTwo.getChildren().add(treeTower);
		v.getChildren().add(rowTwo);
		// add third row of tower buttons
		HBox rowThree = new HBox();
		rowThree.getChildren().add(volcanoTower);
		rowThree.getChildren().add(ghostTower);
		v.getChildren().add(rowThree);

		HBox rowFour = new HBox();
		rowFour.getChildren().add(kingTower);
		rowFour.getChildren().add(mageTower);
		v.getChildren().add(rowFour);

		v.getChildren().add(pause);
		v.getChildren().add(normal);
		v.getChildren().add(fastForward);

		gridPane.add(v, 1, 0);

		c = new Canvas(512, 512);
		gc = c.getGraphicsContext2D();
		gridPane.add(c, 0, 0);

		refreshGame();
		at.start();
		
		MusicPlayer mc = new MusicPlayer();
		mc.start();
		
		return game;
	}

	/**
	 * Assembles certain aspects of the game Used initially in start() and called
	 * when the model is updated
	 */
	public void refreshGame() {

		currency.setText("Money: " + controller.getCurrency());
		health.setText("Health: " + controller.getGetHealth());

		Image image;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (controller.getTile(i, j).getBuildAble())
					image = new Image("NewArt/Grass.png", false);
				else
					image = new Image("NewArt/NewDirt.png", false);

				gc.drawImage(image, i * 64, j * 64, 64, 64);
			}
		}

		ArrayList<Tower> towers = controller.getTowers();
		for (Tower t : towers) {

			image = new Image(t.getImageString(), false);

			int towerRange = t.getRange();
			int towerX = (int) t.getXPos();
			int towerY = (int) t.getYPos();

			int circleX = 64 + (towerX) - (towerRange * 2 + 64) / 2;
			int circleY = 64 + (towerY) - (towerRange * 2 + 64) / 2;

			// Draw an oval for the tower range
			// gc.setStroke(Color.BLACK);
			// gc.strokeOval(circleX, circleY, towerRange * 2, towerRange * 2);
			gc.drawImage(image, towerX, towerY, 64, 64);

			// draw a line to the tower target
			/*
			 * if (t.getTarget() != null) { gc.setStroke(Color.RED);
			 * gc.strokeLine(t.getXPos() + 32, t.getYPos() + 32, t.getTarget().getXPos() +
			 * 32, t.getTarget().getYPos() + 32);
			 * 
			 * }
			 */
		}

		ArrayList<Projectile> projectiles = controller.getProjectiles();
		for (Projectile p : projectiles) {
			image = new Image(p.getImageString(), false);
			gc.drawImage(image, p.getXPos() + 16, p.getYPos() + 16, 16, 16);
		}

		@SuppressWarnings("unchecked")
		ArrayList<Enemy> enemies = controller.getEnemies();
		for (Enemy e : enemies) {

			float enemyX = e.getXPos();
			float enemyY = e.getYPos();

			gc.drawImage(new Image(e.getImageString(), false), enemyX, enemyY);

		}
	}

	/**
	 * Creates the menu bar
	 * 
	 * @return VBox item to add to the scene
	 * 
	 *         NOTE: This is unused in the current build - Sean
	 */
	public VBox makeMenu() {
		Menu controls = new Menu("Controls");
		MenuItem play = new MenuItem("Play");
		play.setOnAction(event -> {
			tickRate = 0;
		});

		MenuItem pause = new MenuItem("Pause");
		MenuBar mb = new MenuBar();

		controls.getItems().add(play);
		controls.getItems().add(pause);
		mb.getMenus().add(controls);

		pause.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t1) {
				tl.pause();
				at.stop();

			}
		});

		play.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t1) {
				tl.play();
				at.start();

			}
		});

		VBox vb = new VBox(mb);

		return vb;
	}

	/**
	 * Called whenever the model is updated
	 * 
	 * @param arg0: the observable object.
	 * @param arg1: an argument passed to the notifyObserversmethod.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if(controller.checkLoss()) {
			tickRate = 0;
			Alert loser = new Alert(AlertType.WARNING);
			loser.setHeaderText("LOSER!");
			loser.setContentText("YOU LOST!");
			loser.show();
		}
		else if(controller.checkWin()) {
			tickRate = 0;
			Alert winner = new Alert(AlertType.INFORMATION);
			winner.setHeaderText("WINNER!");
			winner.setContentText("YOU WON!");
			winner.show();
		}
			refreshGame();
	}

	/**
	 * Plays music in the background
	 * 
	 * @author Sean Doolen
	 *
	 */
	private class MusicPlayer extends Thread {

		public void start() {
			File music = new File("music/music.wav");
			AudioInputStream radio = null;
			try {
				radio = AudioSystem.getAudioInputStream(music);
			} catch (UnsupportedAudioFileException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Clip dc = null;
			try {
				dc = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				dc.open(radio);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dc.start();
			dc.loop(Clip.LOOP_CONTINUOUSLY);

		}
	}

}
