/**
 * The Controller for the game
 * 
 * @author Sean
 */
import java.util.ArrayList;


import enemy.Enemy;
import projectile.Projectile;
import tile.Tile;
import tower.Tower;

public class TowerDefenseController {

	
	
	
	private TowerDefenseModel model; // The model for the game
	
	/**
	 * Constructor
	 * 
	 * @param model: the model the controller talks to 
	 */
	public TowerDefenseController(TowerDefenseModel model) {
		this.model = model;
	}
	
	/**
	 * Returns the currency from the model
	 * 
	 * @return the currency 
	 */
	public int getCurrency() {
		return model.getCurrency();
	}
	
	/**
	 * Returns the health left from the model
	 * 
	 * @return the health amount
	 */
	public int getGetHealth() {
		return model.getHealth();
	}
	
	/**
	 * Returns a specific tile from the board
	 * 
	 * @param row: the row of the tile
	 * @param col: the column of the tile
	 * @return the Tile
	 */
	public Tile getTile(int row, int col) {
		return model.getTile(row, col);
	}
	
	/**
	 * Attempts to place a tower onto the board
	 * 
	 * @param tower: the type of tower to be placed
	 * @param row: the row of the tile
	 * @param col: the column of the tile
	 * @return true if the tower was placed, false otherwise
	 */
	public boolean placeTower(Tower tower, int row, int col) {
		return model.placeTower(tower, row, col);
	}
	
	
	/**
	 * Attempts to sell a tower
	 * 
	 * @param row: the row of the tile
	 * @param col: the column of the tile
	 * @return true if the tower was sold, false otherwise
	 */
	public boolean sell(int x, int y) {
		return model.sellTower(x,y);
	}
	
	/**
	 * Returns the list of enemies from the model
	 * 
	 * @return enemies, arraylist
	 */
	public ArrayList<Enemy> getEnemies() {
		return model.getEnemies();
	}
	
	/**
	 * Returns the list of enemies from the model
	 * 
	 * @return enemies, arraylist
	 */
	public ArrayList<Projectile> getProjectiles() {
		return model.getProjectiles();
	}
	
	/**
	 * Returns the list of towers from the model
	 * 
	 * @return enemies, arraylist
	 */
	public ArrayList<Tower> getTowers() {
		return model.getTowers();
	}
	
	/**
	 * Orders the model to tick
	 */
	public void tick() {
		model.tick();
	}
	
	/**
	 * Checks if the game was won
	 * 
	 * @return true if won false otherwise
	 */
	public boolean checkWin() {
		return  model.checkWin();
	}
	
	/**
	 * Checks if the game was lost
	 * 
	 * @return true if lost false otherwise
	 */
	public boolean checkLoss() {
		return model.checkLoss();
	}

}
