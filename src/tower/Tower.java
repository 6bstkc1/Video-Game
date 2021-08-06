package tower;

import enemy.Enemy;

/**
 * Represents an abstract class for an ingame tower
 * 
 * @author Sean
 *
 */
public abstract class Tower {

	private int cost; // How much money does it cost
	private int fireRate; // How fast can it fire
	private int range; // How far can it shoot
	private float xPos;
	private float yPos;
	private Enemy target;
	private String towerString = "NewArt/Tower.png";

	private String projectileString = "NewArt/Arrow.png";
	
	/**
	 * Constructor used with other tower objects
	 * 
	 * @param cost: how much the tower costs
	 * @param fireRate: how fast the tower fires
	 * @param range: how far the tower fires
	 */
	public Tower(int cost, int fireRate, int range, float xPos, float yPos) {
		this.cost = cost;
		this.fireRate = fireRate;
		this.range = range;
		this.xPos = xPos;
		this.yPos = yPos;
		this.target = null;
	}
	
	/**
	 * Returns the cost of the tower
	 * 
	 * @return cost, int
	 */
	public int getCost() {
		return cost;
	}
	
	public int getRange() {
		return range;
	}
	
	public int getFireRate() {
		return fireRate;
	}
	
	public boolean isValidTarget(Enemy enemy) {
		return range >= Math.sqrt(Math.pow(enemy.getXPos() - xPos,2) + Math.pow(enemy.getYPos() - yPos,2));
	}
	
	public Enemy getTarget() {
		return target;
	}
	
	public void setTarget(Enemy enemy) {
		target = enemy;
	}
	
	public float getXPos() {
		return xPos;
	}
	
	public float getYPos() {
		return yPos;
	}
	
	public String getImageString() {
		return towerString;
	}
	
	public void setImageString(String newImageString) {
		towerString = newImageString;
	}
	
	public String getProjectileImageString() {
		return projectileString;
	}
	
	public void setProjectileString(String newProjectileString) {
		projectileString = newProjectileString;
	}
	
}
