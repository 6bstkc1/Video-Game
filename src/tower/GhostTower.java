package tower;

public class GhostTower extends Tower{
	public GhostTower(float xPos, float yPos) {
		super(30, 40, 500,xPos,yPos);
		super.setImageString("NewArt/GhostTower.png");
		super.setProjectileString("projectile/GhostProjectile.png");
	}
}
