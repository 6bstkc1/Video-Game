package tower;

public class DogTower extends Tower{

	
	public DogTower(float xPos, float yPos) {
		super(40, 25, 256,xPos,yPos);
		super.setImageString("NewArt/dog tower.png");
		super.setProjectileString("projectile/DogProjectile.png");
	}
	
	
}
