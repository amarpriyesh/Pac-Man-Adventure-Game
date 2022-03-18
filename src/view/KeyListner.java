package view;

import controller.DungeonController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.StringReader;


/**
 * The below class represents key listner which helps recognizing the key events and perform actions
 * accordingly.
 */
class KeyListner implements KeyListener {
  private final DungeonController listener;
  private boolean suppKeyShoot = false;
  private boolean suppKeyPick = false;
  private boolean suppKeyDistance = false;
  private String command;


  /**
   * The below constructor helps creating the key listener and initiaise the required variables.
   * @param control represents  dungeonController to control the game play.
   */
  KeyListner(DungeonController control) {
    this.listener = control;
    suppKeyShoot = false;

    suppKeyDistance = false;
    suppKeyPick = false;
    command = "";
  }


  /**
   * Invoked when a key has been typed.
   * See the class description for {@link KeyEvent} for a definition of
   * a key typed event.
   *
   * @param e the event to be processed
   */
  @Override
  public void keyTyped(KeyEvent e) {
    //not required here.
  }

  @Override
  public void keyPressed(KeyEvent e) {


    if (e.getKeyCode() == 38) {
      try {
        if (!suppKeyShoot && !suppKeyDistance && !suppKeyPick) {
          listener.move(new StringReader("n"));
        } else if (suppKeyShoot && suppKeyDistance) {
          listener.shoot(new StringReader(command + " n"));
          suppKeyShoot = false;
          suppKeyDistance = false;
          command = "";
        }

      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } else if (e.getKeyChar() == 's') {

      suppKeyShoot = true;


    } else if (e.getKeyChar() == 'c') {

      suppKeyShoot = false;
      suppKeyPick = false;
      suppKeyDistance = false;
      command = "";
    } else if (e.getKeyCode() == 39) {
      try {
        if (!suppKeyShoot && !suppKeyDistance && !suppKeyPick) {
          listener.move(new StringReader("e"));
        } else if (suppKeyShoot && suppKeyDistance) {
          listener.shoot(new StringReader(command + " e"));
          suppKeyShoot = false;

          suppKeyDistance = false;
          command = "";
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } else if (e.getKeyCode() == 40) {
      try {
        if (!suppKeyShoot && !suppKeyDistance && !suppKeyPick) {
          listener.move(new StringReader("s"));
        } else if (suppKeyShoot && suppKeyDistance) {
          listener.shoot(new StringReader(command + " s"));
          suppKeyShoot = false;

          suppKeyDistance = false;
          command = "";
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } else if (e.getKeyCode() == 37) {
      try {
        if (!suppKeyShoot && !suppKeyDistance && !suppKeyPick) {
          listener.move(new StringReader("w"));
        } else if (suppKeyShoot && suppKeyDistance) {
          listener.shoot(new StringReader(command + " w"));
          suppKeyShoot = false;

          suppKeyDistance = false;
          command = "";
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } else if (e.getKeyCode() == 1) {
      try {
        listener.move(new StringReader("w"));
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } else if (e.getKeyCode() == 49) {
      command = command + "1";
      suppKeyDistance = true;
    } else if (e.getKeyCode() == 50) {
      command = command + "2";
      suppKeyDistance = true;
    } else if (e.getKeyCode() == 51) {
      command = command + "3";
      suppKeyDistance = true;
    } else if (e.getKeyCode() == 52) {
      command = command + "4";
      suppKeyDistance = true;
    } else if (e.getKeyCode() == 53) {
      command = command + "5";
      suppKeyDistance = true;
    } else if (e.getKeyChar() == 'a' && suppKeyPick) {
      try {
        listener.pickUp(new StringReader("arrow"));
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      suppKeyPick = false;
      suppKeyShoot = false;
    } else if (e.getKeyChar() == 'd' && suppKeyPick) {
      try {
        listener.pickUp(new StringReader("diamond"));
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      suppKeyPick = false;
      suppKeyShoot = false;
    } else if (e.getKeyChar() == 'e' && suppKeyPick) {
      try {
        listener.pickUp(new StringReader("sapphire"));
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      suppKeyPick = false;
      suppKeyShoot = false;
    } else if (e.getKeyChar() == 'r' && suppKeyPick) {
      try {
        listener.pickUp(new StringReader("ruby"));
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      suppKeyPick = false;
      suppKeyShoot = false;
    } else if (e.getKeyChar() == 'p') {
      suppKeyPick = true;
    }
  }

  /**
   * Invoked when a key has been released.
   * See the class description for {@link KeyEvent} for a definition of
   * a key released event.
   *
   * @param e the event to be processed
   */
  @Override
  public void keyReleased(KeyEvent e) {
    //not required here.
  }


}
