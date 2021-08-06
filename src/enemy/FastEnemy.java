package enemy;

/**
 * This enemy is faster then the basic one, but has less health
 * 
 * @author Sean Doolen
 *
 */
public class FastEnemy extends Enemy {

	public FastEnemy(float xPos, float yPos) {
		super(xPos,yPos,50,(float).8,4);
		super.setImageString("NewArt/FlyingEye.png");
	}
}
