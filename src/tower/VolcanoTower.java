package tower;

public class VolcanoTower extends Tower{
	public VolcanoTower(float xPos, float yPos) {
		super(500, 5, 128,xPos,yPos);
		super.setImageString("NewArt/Volcano.png");

		super.setProjectileString("projectile/VolcanoProjectile.png");
	}
	
}
