package view;

import controller.DungeonController;
import dungeon.dungeon.ReadOnlyDungeon;

/**
 * The below interface  represent the graphical view of dungeon and has several methods
 * for its operation and showing error message.
 */
public interface DungeonView {
  /**
   * Set up the controller to handle click events in this view.
   *
   * @param listener the controller
   */
  void addClickListener(DungeonController listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * The below method sets the view focus back to the panel.
   */
  void resetFocus();

  /**
   * The below method helps the view setting up the read only dungeon model for the gameplay.
   * @param model represents read only model.
   */
  void setDungeonImage(ReadOnlyDungeon model);

  /**
   * The below method sets up description of the gameplay on  the message screen.
   * @param str message string.
   */
  void setDescription(String str);

  /**
   * The below method sets the scrool position to where the player is situated.
   * @param x takes in the x location of the player.
   * @param y takes in the y location of the player.
   */
  void setScrollPos(int x, int y);

  /**
   * The below method shows the error message on the view in case of any error.
   * @param s shows string error message.
   */
  void showMessage(String s);

}
