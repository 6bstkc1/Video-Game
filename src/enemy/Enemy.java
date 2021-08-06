package enemy;


/**
 * 
 * Represents the superclass for an enemy
 * 
 * @author Sean
 *
 */
public abstract class Enemy {

	private float xPos;
	private float yPos;
	private int health; // how many hits can it take?
	private float speed; // how fast can it go?
	private int bounty;
	private String imageString = "NewArt/Knight.png";
	
	/**
	 * Enemy constructor
	 * 
	 * @param health: health of enemy
	 * @param speed: how fast enemy moves
	 */
	public Enemy(float xPos, float yPos, int health, float speed, int bounty) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.health = health;
		this.speed = speed;
		this.bounty = bounty;
	}
	
	/**
	 * Returns the enemeie's x position
	 * 
	 * @return xPos, float
	 */
	public float getXPos() {
		return xPos;
	}
	
	/**
	 * Returns the enemeie's y position
	 * 
	 * @return yPos, float
	 */
	public float getYPos() {
		return yPos;
	}
	
	/**
	 * Moves the enemy upwards
	 * (Appears to move down on the Gui)
	 */
	public void move(int x, int y) {
		xPos+= speed * x;
		yPos+= speed * y;
	}
	
	
	/**
	 * Returns the bounty for the enemy
	 * 
	 * @return bounty, int
	 */
	public int getBounty() {
		return bounty;
	}

	/**
	 * Gets the amount of health this enemy has
	 * 
	 * @return health, integer
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the amount of health the enemy has 
	 * 
	 * @param health, the new health to be set
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	public String getImageString() {
		return imageString;
	}
	
	public void setImageString(String imageStringNew) {
		imageString = imageStringNew;
	}
	
}
