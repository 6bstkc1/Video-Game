import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import enemy.BasicEnemy;
import enemy.Enemy;
import enemy.FastEnemy;
import enemy.GoblinEnemy;
import enemy.Wizard;
import javafx.application.Application;
import projectile.BasicProjectile;
import tile.Tile;
import tower.BasicTower;
import tower.DogTower;
import tower.ElectricTower;
import tower.GhostTower;
import tower.KingTower;
import tower.LegendaryTower;
import tower.MageTower;
import tower.Tower;
import tower.TreeTower;
import tower.VolcanoTower;

/**
 * 
 * Test cases for the game
 * 
 * @author Cullen Bates
 *
 */
public class TowerDefenseTests {
	
	private TowerDefenseModel model= new TowerDefenseModel();
	
	private TowerDefenseController control = new TowerDefenseController(new TowerDefenseModel());
	
	private TowerDefenseGUI gui = new TowerDefenseGUI();
	
	@Test
	public void ModelHealthTest(){
		assertEquals(50, model.getHealth());
	}
	
	@Test
	public void ModelCurrencyTest(){
		model.getTile(1, 1);
		assertEquals(2000000, model.getCurrency());
	}
	
	@Test
	public void ModelTileTest(){
		assertEquals(null, model.getTile(1, -1));
	}
	

	
	@Test
	public void ModelTowerFailTest(){
		assertEquals(false, model.placeTower(new BasicTower(1,1), -1, 1));
	}
	
	@Test
	public void ControllerTest(){
		control.getTowers();
		control.checkWin();
		control.checkLoss();
		control.getCurrency();
		control.getProjectiles();
		control.sell(0, 0);
	}
	
	@Test
	public void ControllerCurrTest(){
	
	}
	
	@Test
	public void ControllerHealthTest(){
		assertEquals(50, control.getGetHealth());
	}
	
	@Test
	public void ControllerTileTest(){
		control.getTile(1, 1);
	}
	
	
	@Test
	public void ControllerEnemyTest(){
		control.getEnemies();
	}
	
	
	@Test
	public void ControllerTickTest(){
		
	}
	
	@Test
	public void GUITest(){
		model = new TowerDefenseModel(gui, 1);
	}
	
	@Test
	public void EnemyTest(){
		Enemy enemy = new BasicEnemy(10, 5);
		enemy.getXPos();
	}
	
	@Test
	public void FastEnemyTest(){
		Enemy enemy = new FastEnemy(10, 5);
		enemy.getYPos();
	}
	
	@Test
	public void TowerRangeTest(){
		Tower tower = new BasicTower(1,1);
		assertEquals(128, tower.getRange());
	}
	
	@Test
	public void TileBuildTest(){
		Tile tile = new Tile(true);
		assertEquals(true, tile.getBuildAble());
	}
	
	@Test
	public void GUITests(){
		//Application.launch(TowerDefenseGUI.class);
	}
	
	@Test
	public void EnemyHealth(){
		GoblinEnemy goblin = new GoblinEnemy(1, 1);
		assertEquals(250, goblin.getHealth());
	}
	@Test
	public void EnemyHealthSet(){
		Wizard wiz = new Wizard(2, 1);
		wiz.setHealth(300);
		assertEquals(300, wiz.getHealth());
	}
	@Test
	public void EnemyImageString(){
		GoblinEnemy goblin = new GoblinEnemy(1, 1);
		goblin.setImageString("gobby image");
		assertEquals("gobby image", goblin.getImageString());
	}
	
	@Test
	public void EnemyBounty(){
		GoblinEnemy goblin = new GoblinEnemy(1, 1);
		goblin.move(2, 1);
		assertEquals(10, goblin.getBounty());
	}
	
	@Test
	public void ProjectileTest1(){
		BasicProjectile projectile = new BasicProjectile(1, 1, 1, 1);
		assertEquals(1, projectile.getXPos(), 0);
	}
	
	@Test
	public void ProjectileTest2(){
		BasicProjectile projectile = new BasicProjectile(1, 0, 1, 1);
		projectile.move();
		assertEquals(4, projectile.getYPos(), 0);
	}
	
	@Test
	public void ProjectileTest3(){
		BasicProjectile projectile = new BasicProjectile(1, 0, 1, 1);
		projectile.getHitCircle();
		projectile.getDamage();
		projectile.getLifeTime();
		projectile.decay();
	}
	
	@Test
	public void MakeAllTowersTest() {
		DogTower dog = new DogTower(0, 0);
		ElectricTower electric = new ElectricTower(0, 0);
		GhostTower ghost = new GhostTower(0, 0);
		KingTower king = new KingTower(0, 0);
		LegendaryTower legend = new LegendaryTower(0, 0);
		MageTower mage = new MageTower(0, 0);
		TreeTower tree = new TreeTower(0, 0);
		VolcanoTower volc = new VolcanoTower(0, 0);
		BasicTower basic = new BasicTower(0, 0);
	}
	
	@Test
	public void TowerGettersTest() {

		BasicTower basic = new BasicTower(0, 0);
		assertEquals(0, basic.getXPos(), 0);

		assertEquals(0, basic.getYPos(), 0);
		

		assertEquals(basic.getImageString(), "NewArt/Tower.png");

		assertEquals(40, basic.getFireRate(), 0);
		assertEquals(10, basic.getCost(), 0);
		assertEquals(null, basic.getTarget());
	}
	@Test
	public void TowerEnemyTest() {

		BasicTower basic = new BasicTower(0, 0);
		
		basic.setTarget(null);

		assertEquals(true, basic.isValidTarget(new GoblinEnemy(0, 0)));
		assertEquals(false, basic.isValidTarget(new GoblinEnemy(500, 500)));
	}
	
	@Test
	public void PlaceOnTile() {
		Tile newTile = new Tile(true);
		BasicTower basic = new BasicTower(0, 0);
		newTile.setTower(basic);
		assertEquals(newTile.getTower(), basic);
		
	}

	
}
