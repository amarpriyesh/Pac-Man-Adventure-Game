package controller;


import dungeon.dungeon.Dungeon;

import java.io.IOException;



/**
 * The below class represents dungeon controller which helps player to control the gameplay and
 * throw checked exceptions.
 */
public interface DungeonController {

  /**
   * Execute a single game of Dungeon When the game is over,
   * the playGame method ends.
   *
   * @param d a non-null Dungeon model.
   */
  void playGame(Dungeon d);

  /**
   * The below method helps controller to take the dungeon paramaters and create a model.
   *
   * @param arr DUngeon params.
   */
  void createModel(int[] arr);

  /**
   * The below method takes input from view and moves the palyer.
   *
   * @param str move.
   * @throws IOException Throws IO exception.
   */
  void move(Readable str) throws IOException;

  /**
   * The below method takes input from view and helps player to shoot.
   *
   * @param str shoot command.
   * @throws IOException Throws IO exception.
   */
  void shoot(Readable str) throws IOException;

  /**
   * The below method takes input from view and helps player to pick artifacts.
   *
   * @param str shoot command.
   * @throws IOException Throws IO exception.
   */
  void pickUp(Readable str) throws IOException;

  /**
   * Helps player to move sending in the position value.
   *
   * @param m cell value.
   */
  void handleCellClick(int m);

  /**
   * Used to exit running of game.
   */
  void exitProgram();

  /**
   * The below method  is used to give an extra life to the player.
   */
  void reLive();

  /**
   * Start game in cheat mode showing all the dungeon constituents.
   */
  void setCheatMode(boolean bool);

  /**
   * It sets in the moving monster in the game.
   */
  void setMonsters();

  /**
   * The below method is used to  stop the movement of the monster.
   *
   * @param bool true false.
   */
  void toggleRunMonster(boolean bool);

  /**
   * The method sets the pits and thieves in the game and makes the gameplay more difficult.
   */
  void distributePitTheve();

}


