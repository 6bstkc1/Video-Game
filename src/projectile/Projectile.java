package projectile;

/**
 * Superclass for all projectiles
 * 
 * @author Sean Doolen
 *
 */
public abstract class Projectile {

	private float xPos;
	private float yPos;
	private int lifeTime;
	private double xVel;
	private double yVel;
	private int hitCircle;
	private int damage;
	private String imageString;
	
	/**
	 * The constructor
	 * 
	 * @param xPos: x position of the projectile
	 * @param yPos: y position of the projectile
	 * @param speed: how fast the projectile goes
	 * @param lifeTime: how long in ticks does the projectile exist for
	 * @param xDist: how far away is the projectile from it's target (lateral)
	 * @param yDist: how far away is the projectile from it's target (longitudinal)
	 * @param hitCircle: how the projectile detects a hit on an enemy
	 * @param damage: how much damage does the projectile do to an enemy
	 */
	public Projectile(float xPos, float yPos, float speed, int lifeTime, double xDist, double yDist, int hitCircle, int damage) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.lifeTime = lifeTime;
		this.xVel = speed * xDist;   
		this.yVel = speed * yDist;
		this.hitCircle = hitCircle;
		this.damage = damage;
	}

	/**
	 * Return the projectile's xPos
	 * 
	 * @return xPos, float
	 */
	public float getXPos() {
		return xPos;
	}
	
	/**
	 * Return the projectile's xPos
	 * 
	 * @return yPos, float
	 */
	public float getYPos() {
		return yPos;
	}
	
	/**
	 * Return the projectile's lifetime
	 * 
	 * @return lifeTime, int
	 */
	public int getLifeTime() {
		return lifeTime;
	}
	
	/**
	 * Subtracts life from the projectile
	 */
	public void decay() {
		lifeTime--;
	}
	
	/**
	 * Changes the xPos and yPos based on the velocities
	 */
	public void move() {
		xPos += xVel;
		yPos += yVel;
	}

	/**
	 * Gets the radius of the projectile
	 * 
	 * @return, hitCircle, double
	 */
	public double getHitCircle() {
		return hitCircle;
	}

	/**
	 * Gets the damage of the projectile
	 * 
	 * @return damage, int
	 */
	public int getDamage() {
		return damage;
	}
	
	public String getImageString() {
		return imageString;
	}
	
	public void setImageString(String newImageString) {
		imageString = newImageString;
	}
}
