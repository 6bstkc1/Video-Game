package enemy;

public class Wizard extends Enemy{
	
	public Wizard(float xPos, float yPos) {
		super(xPos,yPos,500,(float).4, 100);
		super.setImageString("NewArt/Wizard.png");
	}
}
