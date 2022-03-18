package view;

import controller.DungeonController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



/**
 * The class represents a mouse click handler whose work is to handle the click event.
 */
class MouseHandle extends MouseAdapter {

  DungeonController listener;
  int length;
  int width;
  int zoom;

  /**
   * Represents constructor to create mouseHandle function to handle mouse clicks.
   * @param listener represents controller.
   * @param a represents  length of dungeon.
   * @param b represents width of dungeon.
   */
  MouseHandle(DungeonController listener, int a, int b) {
    this.listener = listener;
    this.length = a;
    this.width = b;
    this.zoom = 5;

  }

  /**
   * The below method sets the new coordinate for the mouse click based upon the click event and
   * zoom value.
   * @param a  represents the length range.
   * @param b b represents the length range.
   * @param zoom zoom represents the length range.
   */
  public void setNewCoordinates(int a, int b, int zoom) {

    this.length = a;
    this.width = b;
    this.zoom = zoom;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    super.mouseClicked(e);

    int y = 75;
    int m = 0;

    for (int i = 1; i <= width; i++) {

      int x = 0;
      for (int j = 1; j <= length; j++) {

        if (e.getY() > y && e.getY() < y
                + (10 * zoom) && e.getX() > x && e.getX() < x + (10 * zoom)) {
          listener.handleCellClick(m);
        }
        x = x + 10 * zoom;
        m++;
      }
      y = y + 10 * zoom;
    }

  }
}