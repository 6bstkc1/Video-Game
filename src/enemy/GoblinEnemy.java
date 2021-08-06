package enemy;

public class GoblinEnemy extends Enemy {

	
	public GoblinEnemy(float xPos, float yPos) {
		super(xPos,yPos,250,(float).2, 10);
		super.setImageString("img/gobby.png");
	}
}
