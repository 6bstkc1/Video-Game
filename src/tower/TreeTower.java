package tower;

public class TreeTower extends Tower{

	public TreeTower(float xPos, float yPos) {
		super(100, 30, 600,xPos,yPos);
		super.setImageString("NewArt/TreeTower.png");

		super.setProjectileString("projectile/TreeProjectile.png");
	}
}
