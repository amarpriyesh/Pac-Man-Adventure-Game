package view;

import controller.DungeonController;
import dungeon.dungeon.ReadOnlyDungeon;

import java.util.List;



/**
 * The below class represents a mock view to test if controller operates correctly.
 */
public class MockView implements DungeonView {
  private List<String> log;

  /**
   * Represents constructor to imitate the view class. Takes in a dummy log to see if it
   * gets updated once the view is called.
   * @param log gog to represent dummy value.
   */
  public MockView(List<String> log) {
    this.log = log;
  }
  /**
   * Set up the controller to handle click events in this view.
   *
   * @param listener the controller
   */

  @Override
  public void addClickListener(DungeonController listener) {
    log.add("listener called");
  }

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  @Override
  public void refresh() {
    log.add("refreh called");
  }

  /**
   * Make the view visible to start the game session.
   */
  @Override
  public void makeVisible() {
    log.add("visible called");
  }

  /**
   * The below method sets the view focus back to the panel.
   */
  @Override
  public void resetFocus() {
    log.add("focus called");
  }

  /**
   * The below method helps the view setting up the read only dungeon model for the gameplay.
   *
   * @param model represents read only model.
   */
  @Override
  public void setDungeonImage(ReadOnlyDungeon model) {
    log.add("image called");
  }

  /**
   * The below method sets up description of the gameplay on  the message screen.
   *
   * @param str message string.
   */
  @Override
  public void setDescription(String str) {
    log.add("description called");
  }

  /**
   * The below method sets the scrool position to where the player is situated.
   *
   * @param x takes in the x location of the player.
   * @param y takes in the y location of the player.
   */
  @Override
  public void setScrollPos(int x, int y) {
    log.add("pos called");
  }

  /**
   * The below method shows the error message on the view in case of any error.
   *
   * @param s shows string error message.
   */
  @Override
  public void showMessage(String s) {
    log.add("message called");
  }
}
