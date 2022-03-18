package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.DungeonController;
import controller.DungeonControllerImpl;
import dungeon.dungeon.Dungeon;
import dungeon.dungeon.DungeonImpl;
import dungeon.position.Artifact;
import dungeon.position.Move;
import dungeon.position.Type;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






/**
 * The class helps to tests the methods and functionality of the Dungeon interface.
 */
public class DungeonTest {
  Dungeon obj;

  /**
   * Sets up objects for different function.
   */
  @Before
  public void setUp() {

    Dungeon obj = new DungeonImpl(5, 5, 55, false, 100,
            1, 12);
    obj.gameSetup();
  }


  /**
   * The below tests if an otyugh gets set at the end point.
   */
  @Test
  public void testOtyughEnd() {


    Dungeon obj1 = new DungeonImpl(5, 5, 55, false, 100,
            1, 12);
    obj1.gameSetup();
    assertEquals(21, obj1.getEndPoint());
    assertTrue(obj1.getPositionMap().get(21).getOtyugh());


  }

  /**
   * The below test tests the invalid move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    Dungeon obj = new DungeonImpl(5, 5, 55, false, 100,
            1, 12);
    obj.gameSetup();
    obj.movePlayer(Move.E);
  }

  /**
   * The below test tests the invalid shoot.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPick() {
    Dungeon obj = new DungeonImpl(5, 5, 55, false, 100,
            1, 12);
    obj.gameSetup();
    obj.pickUp(Artifact.RUBY);
    obj.pickUp(Artifact.ARROW);
    obj.pickUp(Artifact.ARROW);
    obj.pickUp(Artifact.ARROW);
  }

  /**
   * The below test tests shooting an otyugh without an arrow.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShoot() {
    Dungeon obj = new DungeonImpl(5, 5, 55, false, 100,
            1, 12);
    obj.gameSetup();
    obj.shoot(Move.E, 1);
    obj.shoot(Move.E, 1);
    obj.shoot(Move.E, 1);
    obj.shoot(Move.E, 1);
  }

  /**
   * The below test tests shooting an otyugh with invalid distance..
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShootDistance() {
    Dungeon obj = new DungeonImpl(5, 5, 55, false, 100,
            1, 12);
    obj.gameSetup();
    obj.shoot(Move.E, -1);

  }

  /**
   * The below test tests shooting an otyugh with 0 distance wasting arrow.
   */
  @Test
  public void testWastingArrows() {
    Dungeon obj = new DungeonImpl(5, 5, 55, false, 100,
            1, 12);
    obj.gameSetup();
    obj.shoot(Move.E, 0);
    assertEquals("\n"
                    +
                    "Player P, has 2 Arrow\n"
                    +
                    "You are in a CAVE, Player Position:14\n"
                    +
                    "You find 1 Diamond, 1 Ruby, 1 Arrow here\n"
                    +
                    "Doors lead to  W N S\n", obj.toString());


  }

  /**
   * The below test tests shooting an otyugh with greater than 5 distance.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShoot5Distance() {
    Dungeon obj = new DungeonImpl(5, 5, 55, false, 100,
            1, 12);
    obj.gameSetup();
    obj.shoot(Move.E, 8);

  }

  /**
   * The below tests if an otyugh never gets stored at the start point.
   */
  @Test
  public void testOtyughStart() {
    Dungeon obj = new DungeonImpl(5, 5, 55, false, 100,
            1, 12);
    obj.gameSetup();
    assertEquals(14, obj.getStartPoint());
    assertFalse(obj.getPositionMap().get(14).getOtyugh());
  }

  /**
   * The below tests number of Otyughs.
   */
  @Test
  public void testNumberOfOtyughs() {
    Dungeon obj1 = new DungeonImpl(5, 5, 5, false, 100,
            6, 12);
    obj1.gameSetup();
    int countOtyugh = 0;
    for (int i = 0; i < obj1.getPositionMap().size(); i++) {
      if (obj1.getPositionMap().get(i).getOtyugh()) {
        countOtyugh++;
      }
    }
    assertEquals(6, countOtyugh);

  }

  /**
   * The below tests that Otyughs stay only in cave.
   */
  @Test
  public void testOtyughFoundInCaves() {
    Dungeon obj1 = new DungeonImpl(5, 5, 5, false, 100,
            6);
    obj1.gameSetup();
    int countOtyugh = 0;
    for (int i = 0; i < obj1.getPositionMap().size(); i++) {
      if (obj1.getPositionMap().get(i).getOtyugh() && obj1.getPositionMap()
              .get(i).getType().equals(Type.CAVE)) {
        countOtyugh++;

      }
    }
    assertEquals(6, countOtyugh);

  }

  /**
   * Test Otyughs with other treasures.
   */
  @Test
  public void testOtyughsWithTreasures() {
    Dungeon obj1 = new DungeonImpl(5, 5, 5, false, 100,
            6, 12);
    obj1.gameSetup();
    int countOtyugh = 0;
    for (int i = 0; i < obj1.getPositionMap().size(); i++) {
      if (obj1.getPositionMap().get(i).getOtyugh() && obj1.getPositionMap().get(i).hasTreasure()) {
        countOtyugh++;
      }
    }
    assertEquals(6, countOtyugh);

  }


  /**
   * The below test player getting killed by the otyugh.
   */
  @Test
  public void testOtyughSlayPlayer() {
    Dungeon obj1 = new DungeonImpl(5, 5, 5, false, 100,
            6, 12);
    obj1.gameSetup();

    int countOtyugh = 0;
    int playerPosition = obj1.getStartPoint();
    assertEquals("You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:20\n"
            +
            "You find 1 Ruby, 1 Arrow here\n"
            +
            "Doors lead to  N\n", obj1.toString());

    assertEquals("\n"
            +
            "Player moved to N\n"
            +
            "Chomp, chomp, chomp, you are eaten by an Otyugh!\n"
            +
            "Better luck next time\n", obj1.movePlayer(Move.N));


  }

  /**
   * The test tests different levels of Otyughs smell.
   */
  @Test
  public void testOtyughMoreLessPungentSmell() {
    Dungeon obj1 = new DungeonImpl(8, 6, 5, false, 100,
            6, 12);
    obj1.gameSetup();
    obj1.movePlayer(Move.W);

    assertEquals("\nPlayer P, has 3 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:45\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  E W\n", obj1.toString());

    obj1.movePlayer(Move.W);
    assertEquals("You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:44\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  E N\n", obj1.toString());

    obj1.movePlayer(Move.N);
    assertEquals("You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:36\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  W S\n", obj1.toString());


    assertEquals("\n"
            +
            "Player moved to W\n"
            +
            "Chomp, chomp, chomp, you are eaten by an Otyugh!\n"
            +
            "Better luck next time\n", obj1.movePlayer(Move.W));


  }

  /**
   * The below method tests kill of Otyugh through arrow.
   */
  @Test
  public void testKillOtyughThroughArrow() {
    Dungeon obj1 = new DungeonImpl(8, 6, 5, false, 100,
            1, 12);
    obj1.gameSetup();
    assertTrue(obj1.getPositionMap().get(obj1.getEndPoint()).isAliveOtyugh());
    int distance = 0;
    for (List<Integer> lis : obj1.dfs(obj1.getStartPoint())
    ) {
      distance++;
      if (lis.contains(obj1.getEndPoint())) {
        break;
      }
    }
    assertEquals(11, distance);
    obj1.movePlayer(Move.W);

    assertEquals("\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:45\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  E W\n", obj1.toString());
    distance = 0;
    for (List<Integer> lis : obj1.dfs(45)
    ) {
      distance++;
      if (lis.contains(obj1.getEndPoint())) {
        break;
      }
    }

    obj1.movePlayer(Move.W);
    obj1.movePlayer(Move.N);
    obj1.movePlayer(Move.W);

    assertEquals(10, distance);
    assertEquals("\n"
            +
            "Nice shot!! You hear a great howl in the distance\n", obj1.shoot(Move.N, 4));
    assertEquals(
            "\n"
                    +
                    "Congratulations!! you killed the Otyugh\n", obj1.shoot(Move.N, 4));

    assertFalse(obj1.getPositionMap().get(obj1.getEndPoint()).isAliveOtyugh());

  }

  /**
   * Getting more pungent smell from two distance from multipe otyugh.
   */
  @Test
  public void test() {
    Dungeon obj1 = new DungeonImpl(8, 6, 5, false, 20,
            12, 12);

    obj1.gameSetup();
    assertTrue(obj1.getPositionMap().get(obj1.getEndPoint()).isAliveOtyugh());

    Map<Integer, Integer> otyughDistanceMap = new HashMap<Integer, Integer>();
    for (Integer i : obj1.getOtyughPosition()
    ) {
      int distance = 0;
      for (List<Integer> lis : obj1.dfs(obj1.getStartPoint())
      ) {
        distance++;
        if (lis.contains(i)) {
          otyughDistanceMap.put(i, distance);
          break;
        }
      }
    }

    //checks if otyuhus are there at locations.
    assertTrue(obj1.getPositionMap().get(27).isAliveOtyugh());
    assertTrue(obj1.getPositionMap().get(43).isAliveOtyugh());
    assertTrue(obj1.getPositionMap().get(34).isAliveOtyugh());

    //checks the distance of otyughs from the player.
    assertEquals((Integer) (5), otyughDistanceMap.get(27));
    assertEquals((Integer) (5), otyughDistanceMap.get(43));
    assertEquals((Integer) (5), otyughDistanceMap.get(34));

    assertEquals("\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:46\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  W\n", obj1.toString());
    obj1.movePlayer(Move.W);
    obj1.movePlayer(Move.W);
    obj1.movePlayer(Move.N);
    //assertTrue();
    // obj1.movePlayer(Move.N);
    assertEquals("You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:36\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  W S\n", obj1.toString());


  }

  /**
   * Test that player starts with 3 crooked arrows and pick extra arrows.
   */
  @Test
  public void playerWith3Arrows() {
    Dungeon obj1 = new DungeonImpl(8, 6, 5, false, 20,
            12, 12);

    obj1.gameSetup();
    assertEquals("\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:46\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  W\n", obj1.toString());

  }

  /**
   * Tests if player picks up the arrows.
   */
  @Test
  public void playerPicksArrow() {
    Dungeon obj1 = new DungeonImpl(8, 6, 5, false, 20,
            12, 12);

    obj1.gameSetup();
    assertEquals("\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:46\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  W\n", obj1.toString());

    assertEquals("You pick up a ARROW", obj1.pickUp(Artifact.ARROW));

    assertEquals("\n"
            +
            "Player P, has 4 Arrow\n"
            +
            "You are in a CAVE, Player Position:46\n"
            +
            "Doors lead to  W\n", obj1.toString());

  }

  /**
   * Tests if player picks up the treasures.
   */
  @Test
  public void playerPicksTreasure() {
    Dungeon obj1 = new DungeonImpl(8, 6, 5, false, 50,
            1, 12);

    obj1.gameSetup();
    assertEquals("\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:46\n"

            +
            "Doors lead to  W\n", obj1.toString());


    assertEquals("\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:46\n"
            +
            "Doors lead to  W\n", obj1.toString());
    obj1.movePlayer(Move.W);
    obj1.movePlayer(Move.W);
    obj1.movePlayer(Move.N);
    obj1.movePlayer(Move.W);

    obj1.movePlayer(Move.W);
    obj1.movePlayer(Move.N);
    obj1.movePlayer(Move.W);

    assertEquals("\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:25\n"
            +
            "You find 2 Diamond, 1 Sapphire, 1 Arrow here\n"
            +
            "Doors lead to  E W N\n", obj1.toString());

    assertEquals("You pick up a DIAMOND", obj1.pickUp(Artifact.DIAMOND));

    assertEquals("\n"
            +
            "Player P, has 1 Diamond, 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:25\n"
            +
            "You find 1 Diamond, 1 Sapphire, 1 Arrow here\n"
            +
            "Doors lead to  E W N\n", obj1.toString());
  }

  /**
   * Tests frequency of arrows if they are same as other treasure frequency and whether the arrows
   * can be found in both tunnel and caves.
   */
  @Test
  public void frequencyArrowAndPosition() {
    Dungeon obj1 = new DungeonImpl(8, 6, 5, false, 20,
            12, 12);

    obj1.gameSetup();
    int arrowNumber = 0;
    int numPosition = 0;
    for (Integer pos : obj1.getPositionMap().keySet()
    ) {

      numPosition++;

      if (obj1.getPositionMap().get(pos).getTreasure(Artifact.ARROW)) {
        assertTrue(obj1.getPositionMap().get(pos).getType().equals(Type.CAVE)
                ||
                obj1.getPositionMap().get(pos).getType().equals(Type.TUNNEL));

        arrowNumber++;
      }
    }
    assertEquals(20, (arrowNumber * 100 / numPosition));
  }

  /**
   * The below test tests the presenec of arrow with
   * other artifacts and when they are found independently.
   */
  @Test
  public void independentArrowWithTreasure() {
    Dungeon obj1 = new DungeonImpl(8, 6, 5, false, 100,
            1, 12);

    obj1.gameSetup();
    obj1.movePlayer(Move.W);
    obj1.movePlayer(Move.W);
    obj1.movePlayer(Move.N);

    assertEquals(""
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:36\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  W S\n", obj1.toString());
    obj1.movePlayer(Move.W);
    obj1.movePlayer(Move.W);


    assertEquals("\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:34\n"
            +
            "You find 2 Diamond, 1 Sapphire, 1 Ruby, 1 Arrow here\n"
            +
            "Doors lead to  E W N S\n", obj1.toString());


  }

  /**
   * The below test tests killing of otyugh verifying players win.
   */
  @Test
  public void testKillOtyughThroughArrowVerifyingWin() {
    Dungeon obj1 = new DungeonImpl(8, 6, 16, false, 100,
            1, 12);
    obj1.gameSetup();
    assertTrue(obj1.getPositionMap().get(obj1.getEndPoint()).isAliveOtyugh());
    int distance = 0;
    for (List<Integer> lis : obj1.dfs(obj1.getStartPoint())
    ) {
      distance++;
      if (lis.contains(obj1.getEndPoint())) {
        break;
      }
    }


    assertEquals(5, distance);
    obj1.movePlayer(Move.N);
    obj1.movePlayer(Move.N);
    obj1.movePlayer(Move.W);


    assertEquals("You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:6\n"
            +
            "You find 1 Diamond, 1 Arrow here\n"
            +
            "Doors lead to  E W S\n", obj1.toString());
    distance = 0;

    for (List<Integer> lis : obj1.dfs(6)
    ) {
      distance++;
      if (lis.contains(obj1.getEndPoint())) {
        break;
      }
    }
    assertEquals(2, distance);
    assertEquals(100, obj1.getPositionMap().get(obj1.getEndPoint()).otyughLife());
    assertEquals("\n"
            +
            "Nice shot!! You hear a great howl in the distance\n", obj1.shoot(Move.S, 1));
    assertEquals(50, obj1.getPositionMap().get(obj1.getEndPoint()).otyughLife());
    assertEquals(
            "\n"
                    +
                    "Congratulations!! you killed the Otyugh\n", obj1.shoot(Move.S, 1));
    assertEquals(0, obj1.getPositionMap().get(obj1.getEndPoint()).otyughLife());
    //Verifying the distance, caves and tunnel by making player travel in dungeon.
    obj1.movePlayer(Move.S);
    assertEquals("\n"
            +
            "Player moved to W\n"
            +
            "You are in a CAVE Player Position: 13\n"
            +
            "You find a dead Otyugh\n"
            +
            "Congratulations!! Player Wins", obj1.movePlayer(Move.W));
    assertEquals(13, obj1.getEndPoint());


    assertFalse(obj1.getPositionMap().get(obj1.getEndPoint()).isAliveOtyugh());

  }

  /**
   * The test expects an illegal argument exception when a player tries to kill an
   * otyugh with out having an arrow.
   */
  @Test(expected = IllegalArgumentException.class)
  public void killOtyughWithoutArrow() {
    Dungeon obj1 = new DungeonImpl(8, 8, 5, false, 0,
            10, 12);
    obj1.gameSetup();
    assertEquals("You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:31\n"
            +
            "Doors lead to  W\n", obj1.toString());
    obj1.shoot(Move.W, 5);
    obj1.shoot(Move.W, 5);
    obj1.shoot(Move.W, 5);
    obj1.shoot(Move.W, 5);

  }

  /**
   * The below method tests the differences between the shot distances.
   */
  @Test
  public void testExactDistanceDifferentDistanceShoot() {
    Dungeon obj1 = new DungeonImpl(8, 8, 5, false, 0,
            10, 12);
    obj1.gameSetup();
    assertEquals("You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:31\n"
            +
            "Doors lead to  W\n", obj1.toString());
    assertEquals("\n"
            +
            "Nice shot!! You hear a great howl in the distance\n", obj1.shoot(Move.W, 2));
    assertEquals("\n"
                    +
                    "Bad luck! you missed Otyugh, arrow went till position:13\n",
            obj1.shoot(Move.W, 3));

  }

  /**
   * Test Survival chance of player.
   */
  @Test
  public void testPlayerSurvival() {
    Dungeon obj1 = new DungeonImpl(8, 8, 5, false, 0,
            10, 12);
    obj1.gameSetup();
    assertTrue(obj1.getPositionMap().get(22).isAliveOtyugh());

    int i = 1000;
    int j = 0;
    while (i > 0) {
      if (obj1.getPositionMap().get(22).otyughKillTest()) {
        j++;
      }
      i--;
    }

    assertTrue((100 == (j * 100 / 1000)));


    assertEquals("\n"
            +
            "Nice shot!! You hear a great howl in the distance\n", obj1.shoot(Move.W, 1));
    i = 1000;
    j = 0;
    while (i > 0) {
      if (obj1.getPositionMap().get(22).otyughKillTest()) {
        j++;
      }
      i--;
    }

    assertTrue((49 < (j * 100 / 1000)) && (53 > (j / 1000) * 100));


  }

  /**
   * The below test tests if the player is not eaten by the injured Otyugh.
   */
  @Test
  public void testNotEatenByInjuredOtyugh() {
    Dungeon obj1 = new DungeonImpl(8, 8, 5, false, 0,
            10, 12);

    obj1.gameSetup();
    assertEquals("\n"
            +
            "Nice shot!! You hear a great howl in the distance\n", obj1.shoot(Move.W, 1));
    obj1.movePlayer(Move.W);
    assertEquals("\n"
            +
            "Player moved to N\n"
            +
            "You found an injured Otyugh!\n"
            +
            "Relax!! You got saved this time\n", obj1.movePlayer(Move.N));
    obj1.movePlayer(Move.N);

  }

  /**
   * The below test tests if the player is eaten by the injured Otyugh.
   */
  @Test
  public void testEatenByInjuredOtyugh() {
    Dungeon obj1 = new DungeonImpl(8, 8, 5, false, 0,
            20, 12);

    obj1.gameSetup();
    assertEquals("\n"
            +
            "Nice shot!! You hear a great howl in the distance\n", obj1.shoot(Move.W, 1));
    obj1.movePlayer(Move.W);
    assertEquals("\n"
            +
            "Player moved to N\n"
            +
            "Chomp, chomp, chomp, you are eaten by an Otyugh!\n"
            +
            "Better luck next time\n", obj1.movePlayer(Move.N));
    assertTrue(obj1.getPositionMap().get(obj1.retPlayerPos()).otyughLife() == 50);


  }

  /**
   * Tests player can't move after getting eaten.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllgalmoveByPlayerAfterGettingEaten() {
    Dungeon obj1 = new DungeonImpl(8, 8, 5, false, 0,
            20, 12);

    obj1.gameSetup();
    assertEquals("\n"
            +
            "Nice shot!! You hear a great howl in the distance\n", obj1.shoot(Move.W, 1));
    obj1.movePlayer(Move.W);
    assertEquals("\n"
            +
            "Player moved to N\n"
            +
            "Chomp, chomp, chomp, you are eaten by an Otyugh!\n"
            +
            "Better luck next time\n", obj1.movePlayer(Move.N));
    assertTrue(obj1.getPositionMap().get(obj1.retPlayerPos()).otyughLife() == 50);
    obj1.movePlayer(Move.W);
  }

  /**
   * The method checks if the arrow moves in the wrapping dungeon or not.
   */
  @Test
  public void arrowMovingWrappingDungeon() {
    Dungeon obj1 = new DungeonImpl(8, 8, 140, true, 0,
            15, 12);

    obj1.gameSetup();
    assertEquals("You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:52\n"
            +
            "Doors lead to  E W N S\n", obj1.toString());
    obj1.movePlayer(Move.W);
    obj1.movePlayer(Move.W);
    obj1.movePlayer(Move.W);
    assertEquals(100, obj1.getPositionMap().get(53).otyughLife());
    assertEquals("\n"
            +
            "Nice shot!! You hear a great howl in the distance\n", obj1.shoot(Move.E, 4));
    assertEquals(50, obj1.getPositionMap().get(53).otyughLife());
    assertEquals("\n"
            +
            "Congratulations!! you killed the Otyugh\n", obj1.shoot(Move.W, 4));
    assertEquals(0, obj1.getPositionMap().get(53).otyughLife());

  }

  /**
   * The below method confirms that the arrow can only travel in a straight line in cave.
   */
  @Test
  public void arrowNotTravellingInCave() {
    Dungeon obj1 = new DungeonImpl(8, 8, 5, true, 0,
            10, 12);

    obj1.gameSetup();
    assertEquals("You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:40\n"
            +
            "Doors lead to  W N S\n", obj1.toString());
    assertEquals("\n"
                    +
                    "Bad luck! you missed Otyugh, arrow went till position:40\n",
            obj1.shoot(Move.E, 3));

  }

  /**
   * The method tests if the arrow moves in the tunnel.
   */
  @Test
  public void testArrowMovingInTunnel() {
    Dungeon obj1 = new DungeonImpl(8, 8, 5, true, 0,
            20, 12);

    obj1.gameSetup();
    assertEquals("\n"
            +
            "Nice shot!! You hear a great howl in the distance\n", obj1.shoot(Move.S, 1));
    assertEquals("\n"
            +
            "Congratulations!! you killed the Otyugh\n", obj1.shoot(Move.S, 1));
    obj1.movePlayer(Move.S);
    assertEquals("You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 1 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:48\n"
            +
            "Doors lead to  E N\n", obj1.toString());

    obj1.movePlayer(Move.E);
    assertEquals("\n"
            +
            "Player P, has 1 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:49\n"
            +
            "Doors lead to  W S\n", obj1.toString());
    assertEquals("\n"
            +
            "Player moved to S\n"
            +
            "You found a Dead Otyugh here.\n", obj1.movePlayer(Move.S));


  }

  /**
   * The method simulates a snenario where the player wins the game using controller.
   */
  @Test
  public void testPlayerWinNew() {
    Dungeon dun = new DungeonImpl(8, 8, 15, false,
            30, 15, 10);
    Reader input = new StringReader("s 1 w m w m n m e m e s 1 e s 1 e m e");
    Appendable gameLog = new StringBuilder();
    DungeonController c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertEquals("Game Starts, you could view Dungeon, Otyughs, and"
            +
            " Gems by pressing D, O, G respectively.\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:9\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  W\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "No. of caves (1-5)? \n"
            +
            "Where to? \n"
            +
            "Nice shot!! You hear a great howl in the distance\n"
            +
            "\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 2 Arrow\n"
            +
            "You are in a CAVE, Player Position:9\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  W\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to W\n"
            +
            "You found an injured Otyugh!\n"
            +
            "Relax!! You got saved this time\n"
            +
            "\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 2 Arrow\n"
            +
            "You are in a CAVE, Player Position:8\n"
            +
            "Doors lead to  E N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to N\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 2 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:0\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  E S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to E\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 2 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:1\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  E W\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to E\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 2 Arrow\n"
            +
            "You are in a CAVE, Player Position:2\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  E W S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "No. of caves (1-5)? \n"
            +
            "Where to? \n"
            +
            "Nice shot!! You hear a great howl in the distance\n"
            +
            "\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 1 Arrow\n"
            +
            "You are in a CAVE, Player Position:2\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  E W S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "No. of caves (1-5)? \n"
            +
            "Where to? \n"
            +
            "Congratulations!! you killed the Otyugh\n"
            +
            "\n"
            +
            "You smell something terrible nearby\n"
            +
            "\n"
            +
            "You are in a CAVE, Player Position:2\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  E W S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to E\n"
            +
            "You are in a CAVE Player Position: 3\n"
            +
            "You find a dead Otyugh\n"
            +
            "Congratulations!! Player Wins\n"
            +
            "\n"
            +
            "Game Ends", gameLog.toString());

  }


  /**
   * The test tests if the controller moves the player around.
   */
  @Test
  public void testMove() {
    Dungeon dun = new DungeonImpl(10, 5, 15, false,
            30, 15, 10);
    Reader input = new StringReader("m e m s m w m e m e m w m s m n");
    Appendable gameLog = new StringBuilder();
    DungeonController c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertEquals("Game Starts, you could view Dungeon, "
            +
            "Otyughs, and Gems by pressing D, O, G respectively.\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:21\n"
            +
            "Doors lead to  E W S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to E\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:22\n"
            +
            "Doors lead to  E W S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to S\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:32\n"
            +
            "You find 2 Diamond, 1 Sapphire, 2 Ruby, 1 Arrow here\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to W\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:31\n"
            +
            "Doors lead to  E N\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to E\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:32\n"
            +
            "You find 2 Diamond, 1 Sapphire, 2 Ruby, 1 Arrow here\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to E\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:33\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  E W S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to W\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:32\n"
            +
            "You find 2 Diamond, 1 Sapphire, 2 Ruby, 1 Arrow here\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to S\n"
            +
            "Chomp, chomp, chomp, you are eaten by an Otyugh!\n"
            +
            "Better luck next time\n"
            +
            "\n"
            +
            "\n"
            +
            "Game Ends", gameLog.toString());
  }

  /**
   * The test tests if the controller moves the player around.
   */


  @Test
  public void dungeonWrappingMovePickPlayerDie() {
    Dungeon dun = new DungeonImpl(10, 5, 15,
            true, 30, 15, 10);
    Reader input = new StringReader("p ruby p sapphire m e m s m s m s");
    Appendable gameLog = new StringBuilder();
    DungeonController c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);

    assertEquals("Game Starts, you could view Dungeon, Otyughs, and Gems by pressing D,"
            +
            " O, G respectively.\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:35\n"
            +
            "You find 1 Sapphire, 1 Ruby,  here\n"
            +
            "Doors lead to  E\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "What? You pick up a RUBY\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 1 Ruby, 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:35\n"
            +
            "You find 1 Sapphire,  here\n"
            +
            "Doors lead to  E\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "What? You pick up a SAPPHIRE\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 1 Sapphire, 1 Ruby, 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:35\n"
            +
            "Doors lead to  E\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to E\n"
            +
            "Chomp, chomp, chomp, you are eaten by an Otyugh!\n"
            +
            "Better luck next time\n"
            +
            "\n"
            +
            "\n"
            +
            "Game Ends", gameLog.toString());
  }

  @Test
  public void testDfs() {
    Dungeon dun = new DungeonImpl(6, 6, 15, false,
            30, 15, 10);
    dun.gameSetup();
    dun.showDungeon(true);
    System.out.println(dun.printDungeonNew());
    System.out.println( dun.dfs(2,2));

  }

}

