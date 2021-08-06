package tile;

import tower.Tower;

/**
 * Class representing a tile used on the board
 * 
 * @author Sean Doolen
 *
 */
public class Tile {
	
	private boolean buildable;
	private Tower tower;
	
	/**
	 * Constructor
	 * 
	 * @param buildable: if the ground can hold a tower or not
	 */
	public Tile(boolean buildable) {
		this.buildable = buildable;
		tower = null;
	}
	
	/**
	 * Returns buildable
	 * 
	 * @return buildable, boolean
	 */
	public boolean getBuildAble() {
		return buildable;
	}
	
	/**
	 * Returns the tower the tile holds
	 * 
	 * @return tower, Tower
	 */
	public Tower getTower() {
		return tower;
	}
	
	/**
	 * Assigns the tower to the tile
	 * 
	 * @param tower: the tower to be placed
	 */
	public void setTower(Tower tower) {
		this.tower = tower;
	}
}
