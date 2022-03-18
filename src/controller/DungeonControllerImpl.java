package controller;

import static dungeon.position.Artifact.ARROW;
import static dungeon.position.Artifact.DIAMOND;
import static dungeon.position.Artifact.RUBY;
import static dungeon.position.Artifact.SAPPHIRE;

import dungeon.dungeon.Dungeon;
import dungeon.dungeon.DungeonImpl;
import dungeon.dungeon.GameState;
import dungeon.position.Artifact;
import dungeon.position.Move;
import view.DungeonView;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;






/**
 * The below class represents dungeon controller call that mediates between view and model to
 * execute the gameplay.
 */
public class DungeonControllerImpl implements DungeonController {

  private final Appendable out;
  private final boolean quit;
  private Scanner scan;
  private boolean boolTreasureCheat;
  private boolean boolOtyughCheat;
  private boolean boolDugeonCheat;

  private DungeonView view;
  private Dungeon dun;
  private String description;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public DungeonControllerImpl(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
    quit = false;

    boolTreasureCheat = false;
    boolOtyughCheat = false;
    boolDugeonCheat = false;
  }

  /**
   * The below constructor takes in the model and the view and syncs the working between
   * view and the model.
   * @param view Dungeon View.
   * @param model Dungeon Model.
   */
  public DungeonControllerImpl(DungeonView view, Dungeon model) {

    if (view == null || model == null) {
      throw new IllegalArgumentException("Model or view can't be null");
    }
    dun = model;
    view.addClickListener(this);
    view.makeVisible();
    this.view = view;
    quit = false;

    boolTreasureCheat = false;
    boolOtyughCheat = false;
    boolDugeonCheat = false;
    out = new StringBuilder();
    scan = new Scanner("hello");


  }


  @Override
  public void createModel(int[] arr) {
    //System.out.println("mod");
    boolean bool = arr[3] == 1;

    try {
      this.dun = new DungeonImpl(arr[0], arr[1], arr[2], bool, arr[4], arr[5]);
      dun.gameSetup();
    } catch (IllegalArgumentException e) {
      view.showMessage(e.getMessage());
    } catch (NullPointerException e) {
      view.showMessage(e.getMessage());
    }

    dun.showDungeon(true);
    view.setDungeonImage(dun);
    view.resetFocus();
    view.refresh();
    // throw new IllegalArgumentException("!!Wrong number of parameters are provided");


    //playGame();
    //view.resetFocus();
  }

  @Override
  public void playGame(Dungeon dun) {
    this.dun = dun;

    if (dun == null) {
      throw new IllegalArgumentException("Illegal  Model");
    }
    try {
      dun.gameSetup();
      out.append("Game Starts, you could view Dungeon, Otyughs, and Gems by pressing D, "
              +
              "O, G respectively.");
      while (!quit) {

        out.append("\n");
        out.append(dun.printDungeonNew());
        out.append(dun.toString());
        out.append("Move, Pickup, or Shoot (M-P-S)?");
        String strMove = "";
        try {


          strMove = scan.next();

        } catch (NoSuchElementException e) {
          break;
        } catch (NullPointerException e) {
          out.append("\nInvalid Input!!!");
        }
        if (strMove.equalsIgnoreCase("M")) {
          move();
        } else if (strMove.equalsIgnoreCase("P")) {
          pickUp();
        } else if (strMove.equalsIgnoreCase("S")) {
          shoot();
        } else if (strMove.equalsIgnoreCase("G")) {
          boolTreasureCheat = !boolTreasureCheat;
          dun.showTreasureOnMap(boolTreasureCheat);
        } else if (strMove.equalsIgnoreCase("o")) {
          boolOtyughCheat = !boolOtyughCheat;
          dun.showOtyughOnMap(boolOtyughCheat);
        } else if (strMove.equalsIgnoreCase("d")) {
          boolDugeonCheat = !boolDugeonCheat;
          dun.showDungeon(boolDugeonCheat);
        } else {

          out.append("\nInvalid Input!!!");
        }

        if (dun.getGameState() == GameState.END) {
          out.append("\n");
          out.append(dun.printDungeonNew());

          out.append(dun.toString());

          out.append("\nGame Ends");
          break;
        }

        //TODO
        //throw new IndexOutOfBoundsException();
        //
        // view.refresh();
      }

    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }


  }


  @Override
  public void move(Readable str) throws IOException {
    if (dun.getGameState() == GameState.END) {
      view.showMessage(dun.gameResult());
    }

    Move strMove;
    out.append("\nWhere to? ");
    this.scan = new Scanner(str);


    try {
      strMove = Move.valueOf(scan.next().toUpperCase());


      description = (dun.movePlayer(strMove));


    } catch (IllegalArgumentException e) {
      if (e.getMessage().contains("No enum constant")) {
        description = ("\n" + "This is not a valid value, please enter E, W, N, S" + "\n");
      } else {
        description = ("\n" + e.getMessage() + "\n");
      }
    } catch (NoSuchElementException e) {
      out.append("\nNo input available!!!");
    }
    description = description + "\n" + dun.toString();
    description = description.replaceAll("\\r|\\n", "<br>");
    description = "<HTML>" + description + "</HTML>";
    view.setDescription(description);

    view.refresh();

  }


  private void move() throws IOException {

    Move strMove;
    out.append("\nWhere to? ");


    try {
      strMove = Move.valueOf(scan.next().toUpperCase());
      //todo

      out.append(dun.movePlayer(strMove));


    } catch (IllegalArgumentException e) {
      if (e.getMessage().contains("No enum constant")) {
        out.append("\n" + "This is not a valid value, please enter E, W, N, S" + "\n");
      } else {
        out.append("\n" + e.getMessage() + "\n");
      }
    } catch (NoSuchElementException e) {
      out.append("\nNo input available!!!");
    }
    // view.refresh();
  }

  @Override
  public void shoot(Readable str) throws IOException {
    if (dun.getGameState() == GameState.END) {
      view.showMessage(dun.gameResult());
    }

    this.scan = new Scanner(str);
    try {
      out.append("\nNo. of caves (1-5)? ");
      int dist = Integer.parseInt(scan.next());
      out.append("\nWhere to? ");
      Move mov = Move.valueOf(scan.next().toUpperCase());
      description = (dun.shoot(mov, dist));
    } catch (NumberFormatException e) {
      out.append("\nEnter distance in number");
    } catch (IllegalArgumentException e) {
      if (e.getMessage().contains("No enum constant")) {
        description = ("\n" + "This is not a valid value, please enter E, W, N, S" + "\n");
      } else {
        description = ("\n" + e.getMessage() + "\n");
      }

    } catch (NoSuchElementException e) {
      out.append("\nNo input available!!!");
    }
    description = description + "\n" + dun.toString();
    description = description.replaceAll("\\r|\\n", "<br>");
    description = "<HTML>" + description + "</HTML>";
    view.setDescription(description);
    view.refresh();
  }

  private void shoot() throws IOException {


    try {
      out.append("\nNo. of caves (1-5)? ");
      int dist = Integer.parseInt(scan.next());
      out.append("\nWhere to? ");
      Move mov = Move.valueOf(scan.next().toUpperCase());
      out.append(dun.shoot(mov, dist));
    } catch (NumberFormatException e) {
      out.append("\nEnter distance in number");
    } catch (IllegalArgumentException e) {
      if (e.getMessage().contains("No enum constant")) {
        out.append("\n" + "This is not a valid value, please enter E, W, N, S" + "\n");
      } else {
        out.append("\n" + e.getMessage() + "\n");
      }

    } catch (NoSuchElementException e) {
      out.append("\nNo input available!!!");
    }
  }

  @Override
  public void pickUp(Readable str) throws IOException {

    if (dun.getGameState() == GameState.END) {
      view.showMessage(dun.gameResult());
    }
    this.scan = new Scanner(str);
    Artifact strMove;
    out.append("\nWhat? ");


    try {
      strMove = Artifact.valueOf(scan.next().toUpperCase());
      switch (strMove) {
        case RUBY:
          description = (dun.pickUp(RUBY));
          break;
        case SAPPHIRE:
          description = (dun.pickUp(SAPPHIRE));
          break;
        case DIAMOND:
          description = (dun.pickUp(DIAMOND));
          break;
        case ARROW:
          description = (dun.pickUp(ARROW));
          break;
        //default does not do anything here.
        default:
          break;

      }
    } catch (IllegalArgumentException e) {
      if (e.getMessage().contains("No enum constant")) {
        description = ("\n" + "This is not a valid value, please enter "
                +
                "ARROW, DIAMOND, SAPPHIRE, RUBY" + "\n");
      } else {
        description = ("\n" + e.getMessage() + "\n");
      }
    } catch (NoSuchElementException e) {
      out.append("\nNo input available!!!");
    }
    description = description + "\n" + dun.toString();
    description = description.replaceAll("\\r|\\n", "<br>");
    description = "<HTML>" + description + "</HTML>";
    view.setDescription(description);
    view.refresh();
  }

  private void pickUp() throws IOException {


    Artifact strMove;
    out.append("\nWhat? ");


    try {
      strMove = Artifact.valueOf(scan.next().toUpperCase());
      switch (strMove) {
        case RUBY:
          out.append(dun.pickUp(RUBY));
          break;
        case SAPPHIRE:
          out.append(dun.pickUp(SAPPHIRE));
          break;
        case DIAMOND:
          out.append(dun.pickUp(DIAMOND));
          break;
        case ARROW:
          out.append(dun.pickUp(ARROW));
          break;
        //default does not do anything here.
        default:
          break;

      }
    } catch (IllegalArgumentException e) {
      if (e.getMessage().contains("No enum constant")) {
        out.append("\n" + "This is not a valid value, please enter "
                +
                "ARROW, DIAMOND, SAPPHIRE, RUBY" + "\n");
      } else {
        out.append("\n" + e.getMessage() + "\n");
      }
    } catch (NoSuchElementException e) {
      out.append("\nNo input available!!!");
    }
  }

  @Override
  public void handleCellClick(int m) {
    try {
      description = dun.isValidPosition(m);
    } catch (IllegalArgumentException e) {
      description = description + e.getMessage();
    }
    // dun.setPlayerPos(m);
    description = description + "\n" + dun.toString();
    description = description.replaceAll("\\r|\\n", "<br>");
    description = "<HTML>" + description + "</HTML>";
    view.setDescription(description);
    view.refresh();
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void reLive() {
    dun.reLive();
    view.refresh();
  }

  @Override
  public void setCheatMode(boolean bool) {
    dun.setCheatMode(bool);
  }

  @Override
  public void setMonsters() {
    dun.setMonsters();
  }

  @Override
  public void toggleRunMonster(boolean bool) {
    dun.toggleRunMonster(bool);
  }

  @Override
  public void distributePitTheve() {
    dun.distributePitsThieves();
  }
}