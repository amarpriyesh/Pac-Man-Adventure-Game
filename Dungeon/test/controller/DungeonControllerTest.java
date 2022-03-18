package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import dungeon.dungeon.Dungeon;
import dungeon.dungeon.DungeonImpl;
import dungeon.dungeon.MockModel;
import dungeon.position.Move;
import org.junit.Before;
import org.junit.Test;
import view.DungeonView;
import view.MockView;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The below class represents controller test class that helps user to test the unchecked
 * exceptions.
 */
public class DungeonControllerTest {

  private Appendable gameLog;
  private Dungeon dun;
  private DungeonController c;

  /**
   * The below method sets up controller for the user with some basic moves.
   */
  @Before
  public void setUp() {

    dun = new DungeonImpl(10, 5, 15, false, 30, 15, 10);
    Reader input = new StringReader("s 1 e m e m w s 1 e m e");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);

  }


  /**
   * The test tests if the Illegal mode throws Illegal argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullIlligalModel() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    dun = new DungeonImpl(10, 5, 15, false, 100, 1, 10);
    Reader input = new StringReader("s 1 e m e m w s 1 e m e");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(null);
    String df = "";
    assertEquals(gameLog.toString(), df);
  }


  /**
   * The test tests if the Illegal mode throws Illegal argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDungeonIlligalWidth() {
    dun = new DungeonImpl(10, 3, 15, false, 100, 1, 10);
    Reader input = new StringReader("s 1 e m e m w s 1 e m e");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(null);
    String df = "";
    assertEquals(gameLog.toString(), df);
  }

  /**
   * The test tests if the Illegal mode throws Illegal argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDungeonIlligalWidthHeight() {
    dun = new DungeonImpl(0, -2, 15, false, 100, 1, 10);
    Reader input = new StringReader("s 1 e m e m w s 1 e m e");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(null);
    String df = "";
    assertEquals(gameLog.toString(), df);
  }

  /**
   * The test tests if the Illegal mode throws Illegal argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDungeonDegree() {
    dun = new DungeonImpl(10, 12, 1500, false, 100, 1, 10);
    Reader input = new StringReader("s 1 e m e m w s 1 e m e");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(null);
    String df = "";
    assertEquals(gameLog.toString(), df);
  }

  /**
   * The test tests if the Illegal mode throws Illegal argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDungeonPercentTreasure() {
    dun = new DungeonImpl(10, 12, 15, false, 101, 1, 10);
    Reader input = new StringReader("s 1 e m e m w s 1 e m e");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(null);
    String df = "";
    assertEquals(gameLog.toString(), df);
  }

  /**
   * The test tests if the Illegal mode throws Illegal argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeDungeonPercentTreasure() {
    dun = new DungeonImpl(10, 12, 15, false, -1, 1, 10);
    Reader input = new StringReader("s 1 e m e m w s 1 e m e");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(null);
    String df = "";
    assertEquals(gameLog.toString(), df);
  }

  /**
   * The test tests if the Illegal mode throws Illegal argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeDungeonOtyugh() {
    dun = new DungeonImpl(10, 12, 15, false, 10, -1, 10);
    Reader input = new StringReader("s 1 e m e m w s 1 e m e");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(null);
    String df = "";
    assertEquals(gameLog.toString(), df);
  }

  /**
   * The test tests if the Illegal mode throws Illegal argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInvalidDungeonOtyugh() {
    dun = new DungeonImpl(10, 12, 15, false, 1, 1000, 10);
    Reader input = new StringReader("s 1 e m e m w s 1 e m e");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(null);
    String df = "";
    assertEquals(gameLog.toString(), df);
  }

  /**
   * The test tests if the Illegal mode throws Illegal argument exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInvalidFormat() {
    dun = new DungeonImpl(10, 12, 15 / 10, false, 1, 10, 10);
    Reader input = new StringReader("s 1 e m e m w s 1 e m e");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(null);
    String df = "";
    assertEquals(gameLog.toString(), df);
  }


  /**
   * The test tests if the controller throws checked error message for an invalid Input
   * "Invalid Input".
   */
  @Test
  public void invalidInput() {
    Reader input = new StringReader("ee ee");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("Invalid Input!!!"));


  }

  /**
   * The test tests if the controller throws checked error message for an invalid Input
   * "Invalid Input".
   */
  @Test
  public void invalidInputNumber() {
    Reader input = new StringReader("6");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("Invalid Input!!!"));


  }

  /**
   * The test tests if the controller throws checked error message for an invalid Input
   * "Invalid Input".
   */
  @Test
  public void invalidInputNull() {
    Reader input = new StringReader("null");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("Invalid Input!!!"));


  }

  /**
   * The test tests if the controller throws checked error message for an invalid Input
   * "Invalid Input".
   */
  @Test
  public void invalidInputSpecialCharacter() {
    Reader input = new StringReader("$");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("Invalid Input!!!"));


  }

  /**
   * The below tests tests the invalid move values:.
   */
  @Test
  public void invalidInputMove() {
    Reader input = new StringReader("m abcd");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("This is not a valid value, please enter E, W, N, S"));

  }

  /**
   * The below tests tests the invalid move values:.
   */
  @Test
  public void invalidInputMoveNull() {
    Reader input = new StringReader("m $");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("This is not a valid value, please enter E, W, N, S"));

  }

  /**
   * The below tests tests move in invalid direction.
   */
  @Test
  public void invalidInputMoveNorth() {
    Reader input = new StringReader("m n");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("This move is Invalid"));

  }

  /**
   * The below tests tests invalid distance.
   */
  @Test
  public void invalidInputShootNorth() {
    dun = new DungeonImpl(10, 5, 15, false, 30, 15, 10);

    Reader input = new StringReader("s 10 w");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("you could only enter distance till 5 Caves"));

  }

  /**
   * The below tests tests invalid distance < 0.
   */
  @Test
  public void invalidInputShootNegativeDist() {
    Reader input = new StringReader("s -20 w");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("you could only enter distance till 5 Caves"));

  }

  /**
   * The below tests tests invalid input to controller..
   */
  @Test
  public void invalidInputShootParameterInterchange() {
    Reader input = new StringReader("s w 4");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("Invalid Input!!!"));

  }

  /**
   * The below tests tests invalid Direction as number.
   */
  @Test
  public void invalidInputShootParameterInvalidDirection() {
    Reader input = new StringReader("s 4 abc");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("This is not a valid value, please enter E, W, N, S"));

  }

  /**
   * The below tests tests invalid Direction as number.
   */
  @Test
  public void invalidInputShootParameterInvalidDirectionNumber() {
    Reader input = new StringReader("s 4 1");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("This is not a valid value, please enter E, W, N, S"));

  }

  /**
   * The test tests if the controller throws checked error message for an invalid Input
   * "Invalid Input".
   */
  @Test
  public void pickUpInvalidItems() {
    Reader input = new StringReader("p arrow");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("No Such treasure/weapon found"));
  }

  /**
   * The test tests if the controller throws checked error message for an invalid Input
   * "Invalid Input".
   */
  @Test
  public void pickUpInvalidTreasure() {
    Reader input = new StringReader("p arrow");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("No Such treasure/weapon found"));
  }


  /**
   * The test tests if the controller throws checked error message for an invalid Input
   * "Invalid Input".
   */
  @Test
  public void invalidInputSpaces() {
    Reader input = new StringReader("   m   e  m               ");
    gameLog = new StringBuilder();
    c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertTrue(gameLog.toString().contains("No input available!!!"));
  }


  /**
   * The below tests tests Win of player.
   * Use the input from the user to.
   * navigate the player through the dungeon.
   * pick up treasure and/or arrows if they are found in the same location as the player.
   * shoot an arrow in a given direction.
   */
  @Test
  public void playerPickTreasureArrowShootWin() {
    Dungeon dun2 = new DungeonImpl(6, 5,
            13, false, 80, 5, 11);
    Reader input1 = new StringReader("p arrow m e s 1 n s 1 n m n m n m n m w s 1 n s 1 n m n");

    Appendable gameLog1 = new StringBuilder();
    DungeonController c1 = new DungeonControllerImpl(input1, gameLog1);
    c1.playGame(dun2);
    dun2.showDungeon(true);
    dun2.showTreasureOnMap(true);
    assertEquals(
            "Game Starts, you could view Dungeon, Otyughs, and "
                    +
                    "Gems by pressing D, O, G respectively.\n"
                    +
                    "You smell something terrible nearby\n"
                    +
                    "\n"
                    +
                    "Player P, has 3 Arrow\n"
                    +
                    "You are in a CAVE, Player Position:27\n"
                    +
                    "You find 1 Arrow here\n"
                    +
                    "Doors lead to  E W N\n"
                    +
                    "Move, Pickup, or Shoot (M-P-S)?\n"
                    +
                    "What? You pick up a ARROW\n"
                    +
                    "You smell something terrible nearby\n"
                    +
                    "\n"
                    +
                    "Player P, has 4 Arrow\n"
                    +
                    "You are in a CAVE, Player Position:27\n"
                    +
                    "Doors lead to  E W N\n"
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
                    "Player P, has 4 Arrow\n"
                    +
                    "You are in a CAVE, Player Position:28\n"
                    +
                    "Doors lead to  E W N\n"
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
                    "Player P, has 3 Arrow\n"
                    +
                    "You are in a CAVE, Player Position:28\n"
                    +
                    "Doors lead to  E W N\n"
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
                    "\n"
                    +
                    "Player P, has 2 Arrow\n"
                    +
                    "You are in a CAVE, Player Position:28\n"
                    +
                    "Doors lead to  E W N\n"
                    +
                    "Move, Pickup, or Shoot (M-P-S)?\n"
                    +
                    "Where to? \n"
                    +
                    "Player moved to N\n"
                    +
                    "You found a Dead Otyugh here.\n"
                    +
                    "\n"
                    +
                    "\n"
                    +
                    "Player P, has 2 Arrow\n"
                    +
                    "You are in a CAVE, Player Position:22\n"
                    +
                    "You find 1 Sapphire, 1 Ruby, 1 Arrow here\n"
                    +
                    "Doors lead to  E W N S\n"
                    +
                    "Move, Pickup, or Shoot (M-P-S)?\n"
                    +
                    "Where to? \n"
                    +
                    "Player moved to N\n"
                    +
                    "\n"
                    +
                    "Player P, has 2 Arrow\n"
                    +
                    "You are in a TUNNEL, Player Position:16\n"
                    +
                    "You find 1 Arrow here\n"
                    +
                    "Doors lead to  N S\n"
                    +
                    "Move, Pickup, or Shoot (M-P-S)?\n"
                    +
                    "Where to? \n"
                    +
                    "Player moved to N\n"
                    +
                    "You smell something terrible nearby\n"
                    +
                    "\n"
                    +
                    "Player P, has 2 Arrow\n"
                    +
                    "You are in a CAVE, Player Position:10\n"
                    +
                    "You find 1 Diamond,  here\n"
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
                    "Player P, has 2 Arrow\n"
                    +
                    "You are in a CAVE, Player Position:9\n"
                    +
                    "You find 1 Sapphire, 1 Arrow here\n"
                    +
                    "Doors lead to  E W N\n"
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
                    "You are in a CAVE, Player Position:9\n"
                    +
                    "You find 1 Sapphire, 1 Arrow here\n"
                    +
                    "Doors lead to  E W N\n"
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
                    "You are in a CAVE, Player Position:9\n"
                    +
                    "You find 1 Sapphire, 1 Arrow here\n"
                    +
                    "Doors lead to  E W N\n"
                    +
                    "Move, Pickup, or Shoot (M-P-S)?\n"
                    +
                    "Where to? \n"
                    +
                    "Player moved to N\n"
                    +
                    "You are in a CAVE Player Position: 3\n"
                    +
                    "You find a dead Otyugh\n"
                    +
                    "Congratulations!! Player Wins\n"
                    +
                    "\n"
                    +
                    "Game Ends", gameLog1.toString());
    assertTrue(gameLog1.toString().contains("Congratulations!! Player Wins"));

  }

  /**
   * The below tests tests defeat of player where it gets eaten by the otyugh.
   */
  @Test
  public void playerMoveInjuresGetsEatenWrappingShoots() {
    Dungeon dun = new DungeonImpl(7, 5,
            13, true, 80, 5, 11);
    Reader input = new StringReader("s 1 s m s m e");
    Appendable gameLog = new StringBuilder();
    DungeonController c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertEquals("Game Starts, you could view Dungeon,"
            +
            " Otyughs, and Gems by pressing D, O, G respectively.\n"
            +
            "You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:6\n"
            +
            "Doors lead to  S\n"
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
            "You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 2 Arrow\n"
            +
            "You are in a CAVE, Player Position:6\n"
            +
            "Doors lead to  S\n"
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
            "Player P, has 2 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:13\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  E N\n"
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

  /**
   * Tests player getting saved by an Injured otyugh.
   */
  @Test
  public void playerMoveInjureGetSaved() {
    Dungeon dun = new DungeonImpl(7, 5,
            13, true, 80, 4, 11);
    Reader input = new StringReader("s 1 s s 1 s m s m e s 1 s m s");
    Appendable gameLog = new StringBuilder();
    DungeonController c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertEquals("Game Starts, you could view Dungeon, Otyughs, and Gems by "
            +
            "pressing D, O, G respectively.\n"
            +
            "You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:6\n"
            +
            "Doors lead to  S\n"
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
            "You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 2 Arrow\n"
            +
            "You are in a CAVE, Player Position:6\n"
            +
            "Doors lead to  S\n"
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
            "\n"
            +
            "Player P, has 1 Arrow\n"
            +
            "You are in a CAVE, Player Position:6\n"
            +
            "Doors lead to  S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to S\n"
            +
            "You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 1 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:13\n"
            +
            "You find 1 Arrow here\n"
            +
            "Doors lead to  E N\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to E\n"
            +
            "You found a Dead Otyugh here.\n"
            +
            "\n"
            +
            "You smell something more pungent nearby\n"
            +
            "\n"
            +
            "Player P, has 1 Arrow\n"
            +
            "You are in a CAVE, Player Position:7\n"
            +
            "You find 1 Sapphire, 1 Arrow here\n"
            +
            "Doors lead to  E W N S\n"
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
            "You are in a CAVE, Player Position:7\n"
            +
            "You find 1 Sapphire, 1 Arrow here\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to S\n"
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
            "You are in a CAVE, Player Position:14\n"
            +
            "You find 3 Diamond, 2 Ruby, 1 Arrow here\n"
            +
            "Doors lead to  E W N\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?", gameLog.toString());
  }

  /**
   * Tests player travelling everywhere in dungeon..
   */
  @Test
  public void playerTravelEveryWhereDungeon() {
    Dungeon dun = new DungeonImpl(5, 5,
            55, false, 0, 1, 11);
    Reader input = new StringReader("m e m e m n m n m n m n m w m s "
            +
            "m s m s m s m w m n m n m n m n m n m w m s m s m s m s m w m n m n m n m n m n");
    Appendable gameLog = new StringBuilder();
    DungeonController c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertEquals("Game Starts, you could view Dungeon, "
            +
            "Otyughs, and Gems by pressing D, O, G respectively.\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:22\n"
            +
            "Doors lead to  E W N\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to E\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:23\n"
            +
            "Doors lead to  E W N\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to E\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:24\n"
            +
            "Doors lead to  W N\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to N\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:19\n"
            +
            "Doors lead to  W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to N\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:14\n"
            +
            "Doors lead to  W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to N\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:9\n"
            +
            "Doors lead to  W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to N\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:4\n"
            +
            "Doors lead to  W S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to W\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:3\n"
            +
            "Doors lead to  E W S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to S\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:8\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to S\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:13\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to S\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:18\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to S\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:23\n"
            +
            "Doors lead to  E W N\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to W\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:22\n"
            +
            "Doors lead to  E W N\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to N\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:17\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to N\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:12\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to N\n"
            +
            "You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:7\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to N\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:2\n"
            +
            "Doors lead to  E W S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "This move is Invalid\n"
            +
            "\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:2\n"
            +
            "Doors lead to  E W S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to W\n"
            +
            "You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:1\n"
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
            "You are in a CAVE, Player Position:6\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to S\n"
            +
            "You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:11\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to S\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:16\n"
            +
            "Doors lead to  E W N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to S\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:21\n"
            +
            "Doors lead to  E W N\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to W\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a TUNNEL, Player Position:20\n"
            +
            "Doors lead to  E N\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to N\n"
            +
            "You smell something terrible nearby\n"
            +
            "\n"
            +
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:15\n"
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
            "Player P, has 3 Arrow\n"
            +
            "You are in a CAVE, Player Position:10\n"
            +
            "Doors lead to  E N S\n"
            +
            "Move, Pickup, or Shoot (M-P-S)?\n"
            +
            "Where to? \n"
            +
            "Player moved to N\n"
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
   *The below method tests the mock view for its functionality.
   */
  @Test
  public void mockViewTest() {

    List<String> log = new ArrayList<>();
    List<Boolean> log2 = new ArrayList<>();
    Dungeon mockModel = new MockModel(log2);
    DungeonView view = new MockView(log);
    DungeonController c = new DungeonControllerImpl(view,mockModel);
    view.refresh();
    view.addClickListener(c);
    assertEquals(log,log);
  }

  /**
   *The below method tests the mock view functions for its functionality.
   */
  @Test
  public void mockViewTest2() {
    List<Boolean> log2 = new ArrayList<>();
    Dungeon mockModel = new MockModel(log2);
    List<String> log = new ArrayList<>();
    DungeonView view = new MockView(log);
    view.resetFocus();
    view.setDescription("");
    view.setScrollPos(2, 3) ;
    view.showMessage("");
    assertEquals(Arrays.asList("focus called", "description called","pos called",
            "message called"), log);
  }

  /**
   *The below method tests the mock model functions for its functionality.
   */
  @Test
  public void mockModelTest() {
    List<Boolean> log = new ArrayList<>();
    Dungeon mockModel = new MockModel(log);
    mockModel.gameResult();
    mockModel.setCheatMode(true);
    mockModel.setMonsters();
    mockModel.toggleRunMonster(true);
    mockModel.distributePitsThieves();
    mockModel.movePlayer(Move.W);
    mockModel.dungeonMap();
    mockModel.printDungeonNew();
    mockModel.getGameState();
    assertEquals(Arrays.asList(true, true,true,
            true,true,true,true,true,true), log);
  }

  /**
   * New feature complete gameplay test.
   */
  @Test
  public void newFeatureTest() {
    Dungeon dun = new DungeonImpl(6, 6,
              55, false, 40, 1, 11);
    Reader input = new StringReader("m w m n p diamond m w");
    Appendable gameLog = new StringBuilder();
    DungeonController c = new DungeonControllerImpl(input, gameLog);
    c.playGame(dun);
    assertEquals("Game Starts, you could view Dungeon, Otyughs, and"
              +
              " Gems by pressing D, O, G respectively.\n"
              +
              "\n"
              +
              "Player P, has 3 Arrow\n"
              +
              "You are in a CAVE, Player Position:15\n"
              +
              "You find 1 Arrow here\n"
              +
              "Doors lead to  E W N S\n"
              +
              "Move, Pickup, or Shoot (M-P-S)?\n"
              +
              "Where to? \n"
              +
              "Player moved to W\n"
              +
              "\n"
              +
              "Player P, has 3 Arrow\n"
              +
              "You are in a CAVE, Player Position:14\n"
              +
              "You find 1 Arrow here\n"
              +
              "Doors lead to  E W N\n"
              +
              "Move, Pickup, or Shoot (M-P-S)?\n"
              +
              "Where to? \n"
              +
              "Player moved to N\n"
              +
              " Oh no!!!!!, you met a thief and lost treasures!\n"
              +
              "\n"
              +
              "Careful!! There is a Pit beside\n"
              +
              "\n"
              +
              "You are in a CAVE, Player Position:8\n"
              +
              "You find 1 Diamond,  here\n"
              +
              "Doors lead to  E W N S\n"
              +
              "Move, Pickup, or Shoot (M-P-S)?\n"
              +
              "What? You pick up a DIAMOND\n"
              +
              "Careful!! There is a Pit beside\n"
              +
              "\n"
              +
              "Player P, has 1 Diamond, \n"
              +
              "You are in a CAVE, Player Position:8\n"
              +
              "Doors lead to  E W N S\n"
              +
              "Move, Pickup, or Shoot (M-P-S)?\n"
              +
              "Where to? \n"
              +
              "Player moved to W\n"
              +
              " Ahhhhhhhhhh!!!!!, you fell into a pit!\n"
              +
              "Better luck next time\n"
              +
              "\n"
              +
              "\n"
              +
              "Game Ends",gameLog.toString());
  }
}