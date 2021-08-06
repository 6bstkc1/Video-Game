
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

import enemy.BasicEnemy;
import enemy.Enemy;
import enemy.FastEnemy;
import enemy.GoblinEnemy;
import enemy.Wizard;
import projectile.BasicProjectile;
import projectile.Projectile;
import tile.Tile;
import tower.Tower;

/**
 * 
 * Model Class for tower defense game
 * 
 * @author Sean Doolen, Patricia Lemmen Meyer
 *
 */
public class TowerDefenseModel extends Observable {
	private final int ROWS = 8;
	private final int COLS = 8;
	private int currency;
	private int health;
	private Tile board[][];
	private ArrayList<Enemy> enemies;
	private Stack<Integer>[] waves;
	private int WAVECOUNT = 5;
	private int waveNum;
	private int ticksBetweenWaves;
	private ArrayList<Tower> towers;
	private ArrayList<Projectile> projectiles;
	private int ticks;
	private ArrayList<Object> garbage;
	private boolean hasWon;
	private boolean hasLost;
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	private ArrayList<Point> path;

	/**
	 * constructor for the model. Initiates the board.
	 */
	public TowerDefenseModel() {
		currency = 2000000;
		health = 50;
		board = new Tile[ROWS][COLS];
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLS; j++)
				board[i][j] = new Tile(true);

		for (int i = 0; i < 8; i++)
			board[4][i] = new Tile(false);
	}

	/**
	 * Constructor for model that takes in an observer and a level number.
	 * 
	 * @param gui: the Observer
	 * @param level: level to play
	 */
	public TowerDefenseModel(TowerDefenseGUI gui, int level) {
		this.addObserver(gui);
		currency = 80;
		health = 30;
		board = new Tile[ROWS][COLS];

		enemies = new ArrayList<Enemy>();
		waves = new Stack[WAVECOUNT];
		ticksBetweenWaves = 200;
		waveNum = 0;

		if (level == 1) {
			xStart = 64;
			yStart = 0;

			xEnd = 320;
			yEnd = 512;

			path = new ArrayList<Point>();

			
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < COLS; j++)
					board[i][j] = new Tile(true);

			for (int i = 0; i < 4; i++) {
				board[1][i] = new Tile(false);
				path.add(new Point(1, i));
			}
			
			board[2][3] = new Tile(false);
			path.add(new Point(2, 3));
			board[3][3] = new Tile(false);
			path.add(new Point(3, 3));
			board[3][4] = new Tile(false);
			path.add(new Point(3, 4));
			board[3][5] = new Tile(false);
			path.add(new Point(3, 5));
			
			board[4][5] = new Tile(false);
			path.add(new Point(4, 5));
			board[5][5] = new Tile(false);
			path.add(new Point(5, 5));
			board[6][5] = new Tile(false);
			path.add(new Point(6, 5));
			board[6][6] = new Tile(false);
			path.add(new Point(6, 6));
			board[6][7] = new Tile(false);
			path.add(new Point(6, 7));
			path.add(new Point(6, 8)); // moves enemies off the screen
			

			for (int i = 0; i < WAVECOUNT; i++)
				waves[i] = new Stack<Integer>();

			// Wave 1
			for (int i = 0; i < 5; i++)
				waves[0].push(0);
			// Wave 2
			for (int i = 0; i < 10; i++)
				waves[1].push(0);
			// Wave 3
			for (int i = 0; i < 5; i++) {
				waves[3].push(1);
				waves[3].push(0);
			}
			// Wave 4
			for (int i = 0; i < 10; i++) {
				waves[3].push(1);
			}
			// Wave 5
			for (int i = 0; i < 5; i++) {
				waves[4].push(2);
				waves[4].push(0);
			}
			waves[4].push(3);

		} else if (level == 2) {
//			xEnd = 448;
//			yEnd = 256;
			
			xStart = 0;
			yStart = 128;

			xEnd = 512;
			yEnd = 320;

			path = new ArrayList<Point>();

			
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < COLS; j++)
					board[i][j] = new Tile(true);

			//Horizontal first stretch of path
			for (int i = 0; i < 5; i++) {
				board[i][2] = new Tile(false);
				path.add(new Point(i, 2));
			}

			//lower horizontal path to end on right bottom
			for (int i = 3; i < 6; i++) {
				board[4][i] = new Tile(false);
				path.add(new Point(4, i));
			}
			
			for (int i = 4; i < 8; i++) {
				board[i][5] = new Tile(false);
				path.add(new Point(i, 5));
			}
			
			path.add(new Point(8, 5));
			
			for (int i = 0; i < WAVECOUNT; i++)
				waves[i] = new Stack<Integer>();

			// Wave 1
			for (int i = 0; i < 10; i++)
				waves[0].push(0);
			// Wave 2
			for (int i = 0; i < 10; i++)
				waves[1].push(1);
			// Wave 3
			for (int i = 0; i < 5; i++)
				waves[2].push(1);
				waves[2].push(0);
			// Wave 4
			for (int i = 0; i < 5; i++) {
				waves[3].push(1);
				waves[3].push(2);
			}
			// Wave 5
			for (int i = 0; i < 5; i++) {
				waves[4].push(2);
				waves[4].push(1);
			}
			waves[4].push(3);

		} else if (level == 3) {
			// level 3 has a different track and is also composed of waves of only the toughest enemies
			xStart = 0;
			yStart = 64;

			xEnd = 512;
			yEnd = 320;

			path = new ArrayList<Point>();

			// sets up the grass tiles
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLS; j++) {
					board[i][j] = new Tile(true);
				}
			}
			

			for (int i = 0; i < 4; i++) {
				board[i][1] = new Tile(false);
				path.add(new Point(i, 1));
			}
			
			for (int i = 1; i < 6; i++) {
				board[3][i] = new Tile(false);
				path.add(new Point(3, i));
			}
			
			for (int i = 3; i < 8; i++) {
				board[i][6] = new Tile(false);
				path.add(new Point(i, 6));
			}

			path.add(new Point(8, 6)); // moves enemies off the screen
			

			for (int i = 0; i < WAVECOUNT; i++)
				waves[i] = new Stack<Integer>();

			// Wave 1
			for (int i = 0; i < 10; i++)
				waves[0].push(0);
			// Wave 2
			for (int i = 0; i < 10; i++) {
				waves[1].push(1);
			}
			// Wave 3
			for (int i = 0; i < 10; i++)
				waves[2].push(2);
			// Wave 4
			for (int i = 0; i < 10; i++) {
				waves[3].push(1);
				waves[3].push(2);
			}
			// Wave 5
			for (int i = 0; i < 10; i++) {
				waves[4].push(0);
				waves[4].push(1);
				waves[4].push(2);
			}
			waves[4].push(3);
			waves[4].push(3);
		}		
		towers = new ArrayList<Tower>();
		projectiles = new ArrayList<Projectile>();
		garbage = new ArrayList();

		hasWon = false;
		hasLost = false;
	}

	/**
	 * Returns the currency
	 * 
	 * @return: currency, integer
	 */
	public int getCurrency() {
		return currency;
	}

	/**
	 * Returns the health
	 * 
	 * @return health, integer
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Returns a specific tile object based on postion
	 * 
	 * @param row: the row of the object
	 * @param col: the column of the object
	 * @return the tile object
	 */
	public Tile getTile(int row, int col) {
		if (row < 0 || row > ROWS - 1 || col < 0 || col > COLS - 1)
			return null;
		return board[row][col];
	}

	/**
	 * Allows towers to be place on the board If a tower is placed then currency is
	 * subtracted by the tower's cost
	 * 
	 * @param tower: the specific tower to be placed
	 * @param row: the row where the tower's placed
	 * @param col: the column where the tower is placed
	 * @return true if the tower was placed, false otherwise
	 */
	public boolean placeTower(Tower tower, int row, int col) {

		if (row < 0 || row > ROWS - 1 || col < 0 || col > COLS - 1 || board[row][col].getTower() != null)
			return false;

		if (currency < tower.getCost())
			return false;

		currency -= tower.getCost();
		board[row][col].setTower(tower);
		towers.add(tower);
		return true;
	}

	/**
	 * Allows towers to be sold for half their cost
	 * 
	 * @param row: the row of the tower sold
	 * @param col: the column of the tower sold
	 * @return true if a tower was sold, false otherwise
	 */
	public boolean sellTower(int row, int col) {

		if (row < 0 || row > ROWS - 1 || col < 0 || col > COLS - 1 || board[row][col].getTower() == null)
			return false;
		currency += board[row][col].getTower().getCost() / 2;
		Tower tower = board[row][col].getTower();
		towers.remove(tower);
		board[row][col].setTower(null);
		return true;
	}

	/**
	 * Gets the array list of tower objects
	 * 
	 * @return: towers, the array list
	 */
	public ArrayList<Tower> getTowers() {
		return towers;
	}

	/**
	 * Gets the array list of projectile objects
	 * 
	 * @return: projectiles, the array list
	 */
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	/**
	 * Gets the array list of enemy objects onscreen
	 * 
	 * @return: enemies, the array list
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * Handles the enemy objects
	 */
	private void handleEnemies() {
		for (Enemy e : enemies) {

			float eX = e.getXPos();
			float eY = e.getYPos();

			int tileX = (int) (eX / 64 );
			int tileY = (int) (eY / 64 );

			for (int i = 0; i < path.size(); i++) {
				if (tileX == path.get(i).getX() && tileY == path.get(i).getY()) {
					Point next = path.get(i + 1);
					if (next.getY() > tileY) {
						e.move(0, 1);
					}
					else if(next.getX() > tileX) {
						e.move(1,0);
					}
					else if (next.getY() < tileY) {
						e.move(0, -1);
					}
					else if(next.getX() > tileX) {
						e.move(1,0);
					}
					else {
						
					}
				}				
			}

			if (e.getYPos() >= yEnd && e.getXPos() >= xEnd) {
				health -= 1;
				garbage.add(e);
			}
			if (e.getHealth() <= 0) {
				garbage.add(e);
				currency += e.getBounty();
			}
		}
	enemies.removeAll(garbage);

	}

	/**
	 * Handles the projectile objects
	 */
	private void handleProjectiles() {
		for (Projectile p : projectiles) {
			p.move();
			p.decay();
			if (p.getLifeTime() == 0) {
				garbage.add(p);
			}
			for (Enemy e : enemies) {
				if (Math.sqrt(Math.pow(p.getXPos() - e.getXPos(), 2) + Math.pow(p.getYPos() - e.getYPos(), 2)) <= p
						.getHitCircle()) {
					e.setHealth(e.getHealth() - p.getDamage());
					garbage.add(p);
				}

			}
		}
		projectiles.removeAll(garbage);

	}

	/**
	 * Handles the tower objects
	 */
	private void handleTowers() {
		for (Tower t : towers) {
			if (garbage.contains(t.getTarget()))
				t.setTarget(null);

			for (Enemy e : enemies) {
				// If t has a target and the target is in range, move to next tower
				if (t.getTarget() != null && t.isValidTarget(e))
					break;
				// If t has a target but the target has moved out of range set target to null
				// and move on to next enemy
				else if (t.getTarget() != null && !t.isValidTarget(e)) {
					t.setTarget(null);
					continue;
				}
				// If t has no target and the enemy is a valid target set the target and move on
				else if (t.getTarget() == null && t.isValidTarget(e)) {
					t.setTarget(e);
					break;
				}
				// If t has no target and enemy is not a valid target, check another enemy
				else
					continue;
			}
			// Firing the tower
			if (t.getTarget() != null && ticks % t.getFireRate() == 0) {

				float enemyX = t.getTarget().getXPos();
				float enemyY = t.getTarget().getYPos();

				float towerX = t.getXPos();
				float towerY = t.getYPos();

				float xDist = enemyX - towerX;
				float yDist = enemyY - towerY;

				double theta = Math.atan2(yDist, xDist);

				BasicProjectile projectile = new BasicProjectile(towerX, towerY, Math.cos(theta), Math.sin(theta));
				projectile.setImageString(t.getProjectileImageString());
				projectiles.add(projectile);
			}
		}
	}

	/**
	 * Represents a cycle of the game
	 */
	public void tick() {
		if (!hasWon && !hasLost) {
			setWin();
			setLoss();
			spawnEnemies();
			handleEnemies();
			handleTowers();
			handleProjectiles();
			garbage.clear();
			ticks++;
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Handles the enemy wave spawning
	 */
	private void spawnEnemies() {
		if (waveNum >= WAVECOUNT) {
			;
		} else if (ticks >= ticksBetweenWaves && !waves[waveNum].isEmpty() && ticks % 150 == 0) {
			int enemyId = waves[waveNum].pop();
			switch (enemyId) {
			case 0:
				enemies.add(new BasicEnemy(xStart, yStart));
				break;
			case 1:
				enemies.add(new FastEnemy(xStart, yStart));
				break;
			case 2:
				enemies.add(new GoblinEnemy(xStart, yStart));
				break;
			case 3:
				enemies.add(new Wizard(xStart, yStart));
				break;
			}
		} else if (waves[waveNum].isEmpty()) {
			ticksBetweenWaves += 1500;
			waveNum++;
		}
	}

	/**
	 * Checks for an in game win
	 */
	private void setWin() {
		if (waveNum >= WAVECOUNT && enemies.isEmpty())
			hasWon = true;
	}

	/**
	 * Checks for an ingame loss
	 */
	private void setLoss() {
		if (health <= 0)
			hasLost = true;
	}
	
	/**
	 * Checks if the user has won
	 * 
	 * @return, true if won, false otherwise
	 */
	public boolean checkWin() {
		return hasWon;
	}
	/**
	 * Checks if the user has won
	 * 
	 * @return, true if won, false otherwise
	 */
	public boolean checkLoss() {
		return hasLost;
	}
}
