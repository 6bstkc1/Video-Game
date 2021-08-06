package tower;

public class KingTower extends Tower{
	public KingTower(float xPos, float yPos) {
		super(400, 2, 128,xPos,yPos);
		super.setImageString("NewArt/KingTower.png");

		super.setProjectileString("projectile/KingProjectile.png");
	}
}
