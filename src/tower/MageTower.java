package tower;

public class MageTower extends Tower{
	public MageTower(float xPos, float yPos) {
		super(50, 10, 128,xPos,yPos);
		super.setImageString("NewArt/MageTower.png");

		super.setProjectileString("projectile/MageProjectile.png");
	}
}
