package tower;

/**
 * This is a generic basic tower
 * 
 * @author Sean Doolen
 *
 */
public class ElectricTower extends Tower {

	
	
	public ElectricTower(float xPos, float yPos) {
		super(15, 25, 128,xPos,yPos);
		super.setImageString("NewArt/electric tower.png");

		super.setProjectileString("projectile/ElectricProjectile.png");
	}

}
