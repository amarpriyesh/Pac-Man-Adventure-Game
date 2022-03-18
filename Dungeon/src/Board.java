

import controller.DungeonController;
import controller.DungeonControllerImpl;
import dungeon.dungeon.Dungeon;
import dungeon.dungeon.DungeonImpl;
import view.DungeonView;
import view.DungeonViewImpl;

import java.io.InputStreamReader;


/**
 * The class represents the driver where the command line instructions would be sent to the player
 * to move.
 */
public class Board {

  /**
   * Main method declaration.
   *
   * @param args It does not expect any argument.
   */
  public static void main(String[] args) {

    if (args != null) {
      Readable input = new InputStreamReader(System.in);
      Appendable output = System.out;
      if (args.length == 7) {
        Dungeon obj = new DungeonImpl(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]), Integer.parseInt(args[2]), Boolean.valueOf(args[3]),
                Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));
        new DungeonControllerImpl(input, output).playGame(obj);

      } else if (args.length == 6) {
        Dungeon obj = new DungeonImpl(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]), Integer.parseInt(args[2]), Boolean.valueOf(args[3]),
                Integer.parseInt(args[4]), Integer.parseInt(args[5]));

        new DungeonControllerImpl(input, output).playGame(obj);


      } else if (args.length == 0) {
        //Dungeon obj = new DungeonImpl(6, 6, 5, true, 20, 1);
        Dungeon model = new DungeonImpl(6, 6,
                55, false, 40, 1, 11);

        model.gameSetup();
        model.distributePitsThieves();

        DungeonView view = new DungeonViewImpl(model);

        DungeonController cont = new DungeonControllerImpl(view, model);


      } else {
        throw new IllegalArgumentException("!!Wrong number of parameters are provided");
      }
    }

  }
}
