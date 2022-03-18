package dungeon.dungeon;

import static dungeon.position.Artifact.ARROW;
import static dungeon.position.Artifact.DIAMOND;
import static dungeon.position.Artifact.RUBY;
import static dungeon.position.Artifact.SAPPHIRE;

import dungeon.position.Artifact;
import dungeon.position.Move;
import dungeon.position.Position;
import dungeon.position.PositionImpl;
import dungeon.position.Type;
import otyugh.Monster;
import otyugh.Otyugh;
import otyugh.OtyughImpl;
import otyugh.OtyughType;
import otyugh.Pit;
import otyugh.Thief;
import player.Player;
import player.PlayerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;






/**
 * The class represents the Dungeon and it hosts teh method to create the dungeon, move the player
 * and hosts methods for calculation of Kruskals algo. for the calculation of spanning tree.
 */
public class DungeonImpl implements Dungeon {
  public static RandomGen RAND;
  private final int length;
  private final int width;
  private final boolean wrap;
  private final float percentTreasure;
  private final Map<Integer, Position> positionMap;
  private final int otyugh;
  private final Map<Integer, Otyugh> otyughMap;
  private final List<Integer[]> directionDir;
  private final List<Integer[]> directionList;
  private final List<Integer[]> hash_Set_Sep;
  private int countPoint;
  private StringBuilder desc;
  private int startPoint;
  private int endPoint;
  private Player player;
  private GameState gameState;
  private boolean showTreasureOnMap;
  private boolean showDungeon;
  private boolean showOtyughOnMap;
  private int degree;
  private boolean cheatMode;
  private final List<Otyugh> monster;
  private boolean runMonstor;
  private boolean setupFlag;
  private String monsterMessage;
  Thread thread = new Thread() {
    public void run() {
      try {
        runMonster();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  };


  /**
   * The constructor is used to initialize the the instance fields with following values.
   *
   * @param length          represents length of the dungeon.
   * @param width           represents width of the dungeon.
   * @param degree          represents interconnectivity of the dungeon.
   * @param wrap            represents whether a dungeon is wrapping or not.
   * @param percentTreasure represents what percet of dungeon should be initialised with treasures.
   */
  public DungeonImpl(int length, int width, int degree, boolean wrap, int percentTreasure,
                     int otyugh) {
    //this( length,  width,  degree,  wrap,  percentTreasure, otyugh, RAND.nextInt(0,10));
    if (wrap && ((width * length < 32 || width * length > 10000) || (width < 3 || length < 3))) {
      throw new IllegalArgumentException("Width x length can't be less than"
              +
              " 32 or less than 3 individually "
              +
              " or greater than 10000 in wrapping scenario");
    }
    if (!wrap && ((width * length < 25 || width * length > 10000)) || (width < 2 || length < 2)) {
      throw new IllegalArgumentException("Width x length can't be less than 25 "
              +
              "or less than 2 individually."
              +
              " or greater than 10000 in non wrapping scenario");
    }
    if (percentTreasure < 0 || percentTreasure > 100) {
      throw new IllegalArgumentException("Percent treasure can't be "
              +
              "greater than 100 or less than 0");
    }
    if (otyugh < 1) {
      throw new IllegalArgumentException("There has to be at least one Otyugh");
    }

    RAND = new RandomGen();

    this.degree = degree;
    this.length = length;
    this.width = width;
    this.wrap = wrap;
    positionMap = new HashMap<>();
    gameState = GameState.PLAYING;
    showTreasureOnMap = false;
    showOtyughOnMap = false;
    showDungeon = false;
    this.percentTreasure = percentTreasure / 100f;
    directionDir = new ArrayList<>();
    directionList = new ArrayList<>();
    hash_Set_Sep = new ArrayList<>();
    countPoint = 0;
    this.otyugh = otyugh;
    desc = new StringBuilder();
    otyughMap = new HashMap<>();
    cheatMode = false;
    monster = new ArrayList<>();
    runMonstor = true;
    setupFlag = false;
    monsterMessage = "";

  }


  /**
   * The constructor is used to initialize the the instance fields with following values.
   *
   * @param length          represents length of the dungeon.
   * @param width           represents width of the dungeon.
   * @param degree          represents interconnectivity of the dungeon.
   * @param wrap            represents whether a dungeon is wrapping or not.
   * @param percentTreasure represents what percet of dungeon should be initialised with treasures.
   */
  public DungeonImpl(int length, int width, int degree, boolean wrap,
                     int percentTreasure, int otyugh, int seed) {

    //this(length, width, degree, wrap, percentTreasure, otyugh);
    RAND = new RandomGen(seed);
    if (wrap && ((width * length < 32 || width * length > 10000) || (width < 3 || length < 3))) {
      throw new IllegalArgumentException("Width x length can't be less than"
              +
              " 32 or less than 3 individually "
              +
              " or greater than 10000 in wrapping scenario");
    }
    if (!wrap && ((width * length < 25 || width * length > 10000)) || (width < 2 || length < 2)) {
      throw new IllegalArgumentException("Width x length can't be less than 25 "
              +
              "or less than 2 individually."
              +
              " or greater than 10000 in non wrapping scenario");
    }
    if (percentTreasure < 0 || percentTreasure > 100) {
      throw new IllegalArgumentException("Percent treasure can't be "
              +
              "greater than 100 or less than 0");
    }
    if (otyugh < 1) {
      throw new IllegalArgumentException("There has to be at least one Otyugh");
    }

    //RAND = new RandomGen();

    this.degree = degree;
    this.length = length;
    this.width = width;
    this.wrap = wrap;
    positionMap = new HashMap<>();
    gameState = GameState.PLAYING;
    showTreasureOnMap = false;
    showOtyughOnMap = false;
    showDungeon = false;
    this.percentTreasure = percentTreasure / 100f;
    directionDir = new ArrayList<>();
    directionList = new ArrayList<>();
    hash_Set_Sep = new ArrayList<>();
    countPoint = 0;
    this.otyugh = otyugh;
    desc = new StringBuilder();
    otyughMap = new HashMap<>();
    cheatMode = false;
    monster = new ArrayList<>();
    runMonstor = true;
    setupFlag = false;
    monsterMessage = "";

  }

  @Override
  public void gameSetup() {

    createDungeons();
    setStart();
    setEnd();
    createPlayer("P");
    setTreasure();
    checkOtyugh();
    setupFlag = true;


  }

  @Override
  public void showTreasureOnMap(boolean show) {
    this.showTreasureOnMap = show;
  }

  @Override
  public void showOtyughOnMap(boolean show) {
    this.showOtyughOnMap = show;
  }

  @Override
  public void showDungeon(boolean b) {
    showDungeon = b;
  }

  private void setStart() {
    this.startPoint = getCave().get(RAND.nextInt(0, getCave().size() - 1));
  }

  private void setEnd() {
    List<Integer> tempList = new ArrayList<>();
    for (int i = 4; i < dfs(startPoint).size(); i++) {
      for (Integer loc : dfs(startPoint).get(i)
      ) {
        if (positionMap.get(loc).getType().equals(Type.CAVE)) {
          tempList.add(loc);
        }
      }
    }
    if (tempList.size() > 0) {
      endPoint = tempList.get(RAND.nextInt(0, tempList.size() - 1));
    } else {
      setStart();
      setEnd();
      // gameSetup();
      //throw new IllegalArgumentException("\nConfig doesn't match\n");
    }
  }

  private List<Integer> getCave() {
    List<Integer> mapCave = new ArrayList<>();
    for (int i = 0; i < positionMap.size(); i++) {
      if (positionMap.get(i).getType().equals(Type.CAVE)) {
        mapCave.add(positionMap.get(i).getPosition());
      }
    }
    return mapCave;
  }

  private void setTreasure() {
    List<Integer> mapCave = getCave();
    Artifact[] artifact;
    artifact = new Artifact[]{SAPPHIRE, RUBY, DIAMOND};

    int caveTreasure = (int) Math.ceil(mapCave.size() * this.percentTreasure);
    if (caveTreasure > mapCave.size()) {
      caveTreasure = mapCave.size();
    }

    List<Integer> tempInt = new ArrayList<>();
    while (caveTreasure > 0) {
      int caveNo = RAND.nextInt(0, mapCave.size() - 1);


      positionMap.get(mapCave.get(caveNo)).setTreasure(artifact[RAND.nextInt(0, 2)]);
      while (RAND.nextInt(1, 6) > 3) {
        positionMap.get(mapCave.get(caveNo)).setTreasure(artifact[RAND.nextInt(0, 2)]);
      }
      caveTreasure--;
      mapCave.remove(caveNo);
    }

    int caveArrows = (int) Math.ceil(positionMap.size() * this.percentTreasure);
    if (caveArrows > positionMap.size()) {
      caveArrows = positionMap.size();
    }

    tempInt = new ArrayList<>();

    for (int i = 0; i < positionMap.size(); i++) {
      tempInt.add(i);
    }
    while (caveArrows > 0) {

      int mapNo = RAND.nextInt(0, tempInt.size() - 1);


      positionMap.get(tempInt.get(mapNo)).setTreasure(ARROW);
      tempInt.remove(mapNo);
      caveArrows--;


    }
    mapCave = getCave();
    tempInt.clear();
    tempInt.add(startPoint);
    tempInt.add(endPoint);
    int caveOtyugh = this.otyugh - 1;
    if (caveOtyugh > mapCave.size() - 2) {
      throw new IllegalArgumentException("\nOtyughs can't be greater than total number of caves "
              +
              "which is:" + (mapCave.size() - 1) + "\n");
    }
    while (caveOtyugh > 0) {


      int posNo = RAND.nextInt(0, mapCave.size() - 1);

      if (mapCave.get(posNo) == startPoint || mapCave.get(posNo) == endPoint) {
        mapCave.remove(posNo);
        continue;
      }

      Otyugh o = new OtyughImpl();
      otyughMap.put(positionMap.get(mapCave.get(posNo)).getPosition(), o);
      positionMap.get(mapCave.get(posNo)).setOtyugh(o);
      mapCave.remove(posNo);
      caveOtyugh--;


    }
    otyughMap.put(endPoint, new OtyughImpl());
    positionMap.get(endPoint).setOtyugh(otyughMap.get(endPoint));
  }

  private void createPlayer(String name) {
    player = new PlayerImpl(name, positionMap.get(this.startPoint));
    positionMap.get(this.startPoint).setHasPlayer(true);
    positionMap.get(player.getPositionId()).setPlayerVisited(true);
  }

  private void checkOtyugh() {
    int oneDistance = 0;
    int twoDistance = 0;
    int oneDistancePit = 0;
    if (positionMap.get(player.getPositionId()).isAliveOtyugh()) {
      oneDistance++;
    }
    for (Integer dis : dfs(player.getPositionId()).get(0)
    ) {

      if (positionMap.get(dis).isAliveOtyugh()) {
        oneDistance++;
      }
      if (positionMap.get(dis).getOtyugh() && positionMap.get(dis).getOtyughType()
              .equals(OtyughType.PIT)) {
        oneDistancePit++;
      }
    }
    for (Integer dis : dfs(player.getPositionId()).get(1)
    ) {
      if (positionMap.get(dis).isAliveOtyugh()) {
        twoDistance++;
      }
    }

    if (oneDistancePit == 1) {
      desc.append("Careful!! There is a Pit beside\n");
    }
    if (oneDistance >= 1 || twoDistance > 1) {
      desc.append("You smell something more pungent nearby\n");
    } else if (twoDistance == 1) {
      desc.append("You smell something terrible nearby\n");
    }

  }

  private void createDungeons() {
    getKruskleSet();

    for (int i = 0; i < length * width; i++) {
      List<Integer> temp = new ArrayList<>();
      Map<Move, Integer> validMove = new HashMap<>();


      for (Integer[] item : directionDir
      ) {
        if (item[0] == i) {
          //temp.add(item[2]);
          if (item[2] == 0) {
            validMove.put(Move.E, item[1]);
          } else if (item[2] == 1) {
            validMove.put(Move.W, item[1]);
          } else if (item[2] == 2) {
            validMove.put(Move.N, item[1]);
          } else if (item[2] == 3) {
            validMove.put(Move.S, item[1]);
          }
        }
      }

      if (validMove.size() > 0) {


        positionMap.put(i, new PositionImpl(i, validMove));
      }

    }

  }

  @Override
  public String pickUp(Artifact t) {

    if (positionMap.get(player.getPositionId()).getTreasure(t)) {
      player.setTreasure(t);
      positionMap.get(player.getPositionId()).removeTreasure(t);
      return "You pick up a " + t;

    } else {
      throw new IllegalArgumentException("\nNo Such treasure/weapon found\n");
    }


  }

  @Override
  public String movePlayer(Move move) {
    desc = new StringBuilder();
    try {
      if (positionMap.get(player.getPositionId()).getValidMove(move) == endPoint) {

        positionMap.get(player.getPositionId()).setHasPlayer(false);
        player.move(positionMap.get(positionMap.get(player.getPositionId()).getValidMove(move)));
        positionMap.get(player.getPositionId()).setHasPlayer(true);
        positionMap.get(player.getPositionId()).setPlayerVisited(true);
        if (positionMap.get(player.getPositionId()).isAliveOtyugh()) {
          checkOtyugh();
          return (otyughSlay(move));
        } else {
          gameState = GameState.END;

          return ("\nPlayer moved to " + move + "\nYou are in a "
                  +
                  positionMap.get(player.getPositionId()).getType() + " Player Position: "
                  + player.getPositionId()
                  + "\nYou find a dead Otyugh" + "\nCongratulations!! Player Wins");
        }

      }
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("This move is Invalid");
    }

    if (positionMap.get(player.getPositionId()).validMove(move) && gameState == GameState.PLAYING) {
      positionMap.get(player.getPositionId()).setHasPlayer(false);
      player.move(positionMap.get(positionMap.get(player.getPositionId()).getValidMove(move)));
      positionMap.get(player.getPositionId()).setHasPlayer(true);
      positionMap.get(player.getPositionId()).setPlayerVisited(true);
      checkOtyugh();
      if (positionMap.get(player.getPositionId()).getOtyugh() || positionMap
              .get(player.getPositionId()).hasMonster()) {
        if (positionMap.get(player.getPositionId()).hasMonster()) {
          gameState = GameState.END;
          player.playerSetStatus(false);
          return "\nPlayer moved to " + move + "\n Ahhhh!!! you got killed by a monster!!\n";
        }
        return (otyughSlay(move));
      }
      return ("\nPlayer moved to " + move);

    } else {
      throw new IllegalArgumentException("This move is Invalid");
    }

  }

  private String otyughSlay(Move move) {

    if (positionMap.get(player.getPositionId()).getOtyughType().equals(OtyughType.PIT)
            || positionMap.get(player.getPositionId()).getOtyughType().equals(OtyughType.THIEF)) {
      if (positionMap.get(player.getPositionId()).getOtyughType().equals(OtyughType.PIT)) {
        player.playerSetStatus(false);
        gameState = GameState.END;
        return "\nPlayer moved to " + move + "\n Ahhhhhhhhhh!!!!!, you fell into a pit!\n"
                +
                "Better luck next time\n";
      } else if (positionMap.get(player.getPositionId()).getOtyughType().equals(OtyughType.THIEF)) {
        player.playerRemoveTreasure();
        return "\nPlayer moved to " + move + "\n Oh no!!!!!, you met a thief and lost treasures!\n";
      }

    }
    if (otyughMap.get(player.getPositionId()).isAlive()) {
      if (otyughMap.get(player.getPositionId()).kill()
              &&
              otyughMap.get(player.getPositionId()).getType().equals(OtyughType.OTYUGH)) {
        gameState = GameState.END;
        player.playerSetStatus(false);
        return "\nPlayer moved to " + move + "\nChomp, chomp, chomp, you are eaten by an Otyugh!\n"
                +
                "Better luck next time\n";

      } else {
        return "\nPlayer moved to " + move + "\nYou found an injured Otyugh!\n"
                +
                "Relax!! You got saved this time\n";
      }
    } else {
      return "\nPlayer moved to " + move + "\nYou found a Dead Otyugh here.\n";
    }
  }

  private List<List<Integer>> getKruskleSet() {
    List<List<Integer>> hash_Set_k = new ArrayList<List<Integer>>();
    List<Integer[]> hash_List_Sep = new ArrayList<>();
    List<List<Integer>> hash_Set_Final = new ArrayList<>();
    int[][] dungeon = new int[length][width];

    boolean wrapping = this.wrap;
    for (int j = 0; j < this.width; j++) {
      for (int i = 0; i < this.length; i++) {
        dungeon[i][j] = countPoint;
        countPoint++;
      }
    }

    int down;
    int up;
    int right;
    int left;
    for (int j = 0; j < this.width; j++) {
      int currentRow = j;
      if (currentRow - 1 == -1 && wrapping) {
        up = this.width - 1;
      } else if (currentRow - 1 == -1 && !wrapping) {
        up = -1;
      } else {
        up = currentRow - 1;
      }

      if (currentRow + 1 == this.width && wrapping) {
        down = 0;
      } else if (currentRow + 1 == this.width && !wrapping) {
        down = -1;
      } else {
        down = currentRow + 1;
      }


      for (int i = 0; i < this.length; i++) {
        int currentCol = i;
        if (currentCol - 1 == -1 && wrapping) {
          left = this.length - 1;
        } else if (currentCol - 1 == -1 && !wrapping) {
          left = -1;
        } else {
          left = currentCol - 1;
        }
        if (currentCol + 1 == this.length && wrapping) {
          right = 0;
        } else if (currentCol + 1 == this.length && !wrapping) {
          right = -1;
        } else {
          right = currentCol + 1;
        }

        if (left != -1) {

          directionList.add(new Integer[]{dungeon[i][j], dungeon[left][currentRow], 1});
        }

        if (right != -1) {

          directionList.add(new Integer[]{dungeon[i][j], dungeon[right][currentRow], 0});
        }
        if (up != -1) {

          directionList.add(new Integer[]{dungeon[i][j], dungeon[currentCol][up], 2});
        }
        if (down != -1) {

          directionList.add(new Integer[]{dungeon[i][j], dungeon[currentCol][down], 3});
        }

      }
    }


    for (Integer[] item : directionList) {

      Integer[] a = {item[1], item[0]};
      if (hash_Set_Final.contains(Arrays.asList(a))) {

        continue;

      } else {

        hash_Set_Final.add(Arrays.asList(item));
      }
    }


    for (int j = 0; j < dungeon[0].length; j++) {
      for (int i = 0; i < dungeon.length; i++) {

        hash_Set_k.add((Arrays.asList(dungeon[i][j])));
      }
    }
    List<Integer[]> abcList = new ArrayList<>();

    for (List<Integer> item : hash_Set_Final
    ) {
      abcList.add(item.toArray(new Integer[1]));
    }


    while (abcList.size() > 0) {

      List<Integer> seta = new ArrayList<>();

      List<List<Integer>> toRemoveA = new ArrayList();

      int rand = RAND.nextInt(0, abcList.size() - 1);

      Integer[] item1 = abcList.get(rand);
      int a1 = 0;
      int b1 = 0;
      int continue1 = 0;
      for (List<Integer> item2 : hash_Set_k
      ) {

        if (item2.contains(item1[0]) && item2.contains(item1[1])) {
          hash_Set_Sep.add(item1);

          continue1++;
          abcList.remove(rand);

          break;
        } else if (item2.contains(item1[0]) && !item2.contains(item1[1])) {

          a1 = hash_Set_k.indexOf(item2);


          toRemoveA.add(item2);
        } else if (item2.contains(item1[1]) && !item2.contains(item1[0])) {
          b1 = hash_Set_k.indexOf(item2);

          toRemoveA.add(item2);

        }
      }
      if (continue1 > 0) {
        continue1 = 0;
        continue;
      }

      seta.addAll(hash_Set_k.get(a1));
      seta.addAll(hash_Set_k.get(b1));

      hash_Set_k.removeAll(toRemoveA);
      hash_Set_k.add(seta);

      hash_List_Sep.add(abcList.get(rand));
      directionDir.add(new Integer[]{abcList.get(rand)[0], abcList.get(rand)[1],
              direction(abcList.get(rand)[0], abcList.get(rand)[1])});
      directionDir.add(new Integer[]{abcList.get(rand)[1], abcList.get(rand)[0],
              direction(abcList.get(rand)[1], abcList.get(rand)[0])});
      abcList.remove(rand);

    }
    if (this.degree >= hash_Set_Sep.size()) {
      this.degree = hash_Set_Sep.size() - 1;

    }

    while (this.degree > 0 && hash_Set_Sep.size() > 0) {

      int rand = RAND.nextInt(0, hash_Set_Sep.size() - 1);

      directionDir.add(new Integer[]{hash_Set_Sep.get(rand)[0], hash_Set_Sep.get(rand)[1],
              direction(hash_Set_Sep.get(rand)[0], hash_Set_Sep.get(rand)[1])});
      directionDir.add(new Integer[]{hash_Set_Sep.get(rand)[1], hash_Set_Sep.get(rand)[0],
              direction(hash_Set_Sep.get(rand)[1], hash_Set_Sep.get(rand)[0])});

      this.degree--;
      hash_Set_Sep.remove(rand);

    }
    return hash_Set_k;
  }

  @Override
  public int[] dimensions() {
    return new int[]{length, width};
  }

  @Override
  public String printDungeonNew() {
    Map<String, String> list = new HashMap<>();
    int playerPos = player.getPositionId();
    list.put(" E W N S", "╬");
    list.put(" E N S", "╠");
    list.put(" W N S", "╣");
    list.put(" E W S", "╦");
    list.put(" E W N", "╩");
    list.put(" E N", "╚");
    list.put(" E S", "╔");
    list.put(" W S", "╗");
    list.put(" W N", "╝");
    list.put(" N S", "║");
    list.put(" E W", "═");
    list.put(" E", "╘");
    list.put(" W", "╕");
    list.put(" N", "╙");
    list.put(" S", "╖");


    StringBuilder sb = new StringBuilder();
    int k = 0;
    for (int j = 0; j < width; j++) {
      for (int i = 0; i < length; i++) {
        if (playerPos == k) {
          sb.append(player.getName());
          k++;
          continue;
        }
        if (endPoint == k) {
          if (positionMap.get(endPoint).isAliveOtyugh() && showOtyughOnMap) {
            sb.append("O");
          } else {
            sb.append("E");
          }
          k++;
          continue;
        }
        if (positionMap.get(k).getTreasure() && showTreasureOnMap) {
          sb.append("G");
          k++;
          continue;
        }
        if (positionMap.get(k).isAliveOtyugh() && showOtyughOnMap) {
          sb.append("O");
          k++;
          continue;
        }
        sb.append(list.get(positionMap.get(k).validMoveStr()));
        k++;
      }
      sb.append("\n");
    }
    if (showDungeon) {
      return sb + "\n";
    } else {
      return "";
    }
  }

  private int direction(int x, int y) {


    for (Integer[] abc : directionList
    ) {

      if (abc[0] == x && abc[1] == y) {
        return abc[2];
      }
    }

    return -1;
  }

  @Override
  public Map<Integer, Position> getPositionMap() {
    Map<Integer, Position> tmp = new HashMap<>();
    for (int i = 0; i < positionMap.size(); i++) {
      tmp.put(i, (new PositionImpl((PositionImpl) positionMap.get(i))));
    }
    return positionMap;
  }

  @Override
  public int getStartPoint() {
    return startPoint;
  }

  @Override
  public int getEndPoint() {
    return endPoint;
  }

  @Override
  public GameState getGameState() {
    return gameState;
  }

  @Override
  public String toString() {
    if (gameState != GameState.END) {
      return monsterMessage + desc + player.toString();
    } else {
      return monsterMessage + "";
    }
  }

  @Override
  public String isValidPosition(int pos) {
    for (Move val : positionMap.get(player.getPositionId()).getValidMove().keySet()
    ) {
      if (positionMap.get(player.getPositionId()).getValidMove().get(val) == pos) {
        return movePlayer(val);
      }
    }
    return "Invalid Move";
  }

  private Move oppositeMove(Move m) {
    Move oppositeMov = null;
    switch (m) {
      case N:
        oppositeMov = Move.S;
        break;
      case E:
        oppositeMov = Move.W;
        break;
      case S:
        oppositeMov = Move.N;
        break;
      case W:
        oppositeMov = Move.E;
        break;
      //default does not do anything here.
      default:
        break;
    }
    return oppositeMov;
  }

  @Override
  public String shoot(Move m, int distance) {
    desc = new StringBuilder();
    if (distance > 5 || distance < 0) {
      throw new IllegalArgumentException("you could only enter distance till 5 Caves");
    }

    int pos = player.getPositionId();
    Move mov = m;
    List<Move> moveL = new ArrayList<>();
    moveL.add(Move.E);
    moveL.add(Move.W);
    moveL.add(Move.N);
    moveL.add(Move.S);

    if (player.hasArrow()) {
      player.shootArrow();
      while (distance > 0) {

        if (positionMap.get(pos).validMove(mov) && distance > 0) {
          if (positionMap.get(positionMap.get(pos).getValidMove(mov)).getType().equals(Type.CAVE)) {
            pos = positionMap.get(pos).getValidMove(mov);
            distance--;
          } else {
            pos = positionMap.get(pos).getValidMove(mov);
            for (Move mm : moveL
            ) {
              if (mm.equals(oppositeMove(mov))) {
                continue;
              }
              if (positionMap.get(pos).validMove(mm)) {
                //pos = positionMap.get(pos).getValidMove(mm);
                mov = mm;
                break;

              }
            }

          }
        } else {
          break;
        }


      }

    } else {
      throw new IllegalArgumentException("\nPlayer does not have Arrow\n");
    }
    if (positionMap.get(pos).isAliveOtyugh() && distance == 0
            && positionMap.get(pos).getOtyughType().equals(OtyughType.OTYUGH)) {

      otyughMap.get(pos).takeDamage();
      if (otyughMap.get(pos).isAlive()) {
        checkOtyugh();
        return ("\nNice shot!! You hear a great howl in the distance\n");
      } else {
        checkOtyugh();
        return ("\nCongratulations!! you killed the Otyugh\n");
      }
    } else {
      checkOtyugh();
      return ("\nBad luck! you missed Otyugh, arrow went till position:"
              + positionMap.get(pos).getPosition()
              +
              "\n");
    }

  }

  @Override
  public List<List<Integer>> dfs(int start) {
    Set<Integer> tempInt = new HashSet<>();
    List<List<Integer>> depthList = new ArrayList<>();
    for (int i = 0; i < positionMap.size(); i++) {
      tempInt.add(i);
    }
    List<Integer> curr = new ArrayList<>();
    tempInt.remove(start);
    curr.add(start);
    while (tempInt.size() > 0) {
      List<Integer> temp = new ArrayList<>();
      for (Integer a : curr
      ) {
        for (Integer i : positionMap.get(a).getValidMove().values()
        ) {
          if (tempInt.contains(i)) {
            temp.add(i);
            tempInt.remove(i);
          }
        }
      }
      curr.clear();
      curr.addAll(temp);
      depthList.add(temp);

    }

    return depthList;
  }

  public int dfs(int start, int end) {
    Set<Integer> tempInt = new HashSet<>();
    List<List<Integer>> depthList = new ArrayList<>();
    for (int i = 0; i < positionMap.size(); i++) {
      tempInt.add(i);
    }
    List<Integer> curr = new ArrayList<>();
    tempInt.remove(start);
    curr.add(start);
    int dist = 0;
    while (tempInt.size() > 0) {
      List<Integer> temp = new ArrayList<>();
      for (Integer a : curr
      ) {
        for (Integer i : positionMap.get(a).getValidMove().values()
        ) {
          if (tempInt.contains(i)) {
            temp.add(i);
            tempInt.remove(i);
            if (i==end){
              return dist+1;
            }
          }
        }

      }
      curr.clear();
      curr.addAll(temp);
      depthList.add(temp);
      dist++;

    }

    return 0;
  }

  @Override
  public int hashCode() {
    return length * width;
  }

  /**
   * Overrides equals method from the object class.
   *
   * @return if two objects are equal.
   */
  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }

    if (!(o instanceof DungeonImpl)) {
      return false;
    }

    DungeonImpl that = (DungeonImpl) o;
    return this.toString().equals(that.toString());


  }

  @Override
  public Set<Integer> getOtyughPosition() {
    return otyughMap.keySet();
  }

  @Override
  public int retPlayerPos() {
    return player.getPositionId();
  }

  private boolean hasMonster(int a) {
    for (Otyugh oty : monster
    ) {
      if (oty.getPosition() == a) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Map<Integer, String[]> dungeonMap() {
    Map<Integer, String[]> list = new HashMap<>();

    int m = 0;
    for (int j = 0; j < width; j++) {
      for (int i = 0; i < length; i++) {

        if (positionMap.get(m).hasPlayerVisited() || cheatMode) {
          String hasThief = "false";
          String hasPit = "false";
          String isCave = "false";
          if (positionMap.get(m).getOtyughType() != null) {
            hasThief = String.valueOf(positionMap.get(m).getOtyughType().equals(OtyughType.THIEF));

            hasPit = String.valueOf(positionMap.get(m).getOtyughType().equals(OtyughType.PIT));

          }
          if (positionMap.get(m).getType() == Type.CAVE) {
            isCave = "true";
          }

          list.put(m, new String[]{positionMap.get(m).validMoveStr(),
                  String.valueOf(positionMap.get(m).hasPlayer()),
                  String.valueOf(positionMap.get(m).isAliveOtyugh()),
                  String.valueOf(positionMap.get(m).hasRuby()),
                  String.valueOf(positionMap.get(m).hasSapphire()),
                  String.valueOf(positionMap.get(m).hasDiamond()),
                  String.valueOf(positionMap.get(m).hasArrow()),
                  String.valueOf(positionMap.get(m).hasMonster()), hasThief, hasPit, isCave});

        }
        m++;
      }
    }
    return list;

  }

  @Override
  public void reLive() {
    gameState = GameState.PLAYING;
    player.playerSetStatus(true);
  }

  @Override
  public String getPlayerDetails() {

    return player.playerDetails();

  }

  @Override
  public String gameResult() {
    if (gameState == GameState.END && player.playerStatus()) {
      return "Player Wins!!";
    } else if (gameState == GameState.END && !player.playerStatus()) {
      return "Player Loses!!";
    } else {
      return "Match is On!!";
    }

  }

  @Override
  public void setCheatMode(boolean bool) {
    cheatMode = bool;
  }

  @Override
  public void setMonsters() {


    for (int i = 0; i < length / 5; i++) {
      Otyugh monster = new Monster();
      monster.setPosition(endPoint);
      this.monster.add(monster);
      positionMap.get(endPoint).addMonster(monster);

    }
    if (thread.isAlive()) {
    //Starting thread.
    } else {
      try {
        thread.start();
      } catch (IllegalStateException e) {
        //Do nothing.
      }

    }

  }

  @Override
  public void toggleRunMonster(boolean bool) {
    runMonstor = bool;

  }


  private void runMonster() throws InterruptedException {
    while (gameState == GameState.PLAYING && runMonstor) {

      Move[] move = {Move.E, Move.W, Move.N, Move.S};
      for (Otyugh oty : monster
      ) {
        Thread.sleep(RAND.nextInt(500, 1600));
        int headStart = RAND.nextInt(2,5);
        while (headStart > 0) {

          int position = 0;

          Thread.sleep(RAND.nextInt(200, 800));

          if (dfs(player.getPositionId(),endPoint) <4) {
            position=strategyKill(oty.getPosition());
          } else {
            position = strategyRandom(oty.getPosition());
          }

          moveMonster(position, oty);

          headStart--;
        }
      }
    }
  }

  private int  strategyKill(int otyughPosition) {
    int  position =otyughPosition;
    int tmp = 1000000;
    for (int pos : positionMap.get(otyughPosition).getValidMove().values()
    ) {


      if (dfs(pos, player.getPositionId()) <= tmp) {
        tmp = dfs(pos, player.getPositionId());
        position = pos;
      }

    }
    return position;
  }

  private int  strategyRandom(int otyughPosition) {
    int  position =otyughPosition;
   int abc = (positionMap.get(otyughPosition).getValidMove().values()).toArray().length;

        position = (int) positionMap.get(otyughPosition).getValidMove().values().toArray()[RAND.nextInt(0,abc-1)];

    return position;
  }
  private void moveMonster(int move, Otyugh otyugh) {
    monsterMessage = "";


    if ( gameState == GameState.PLAYING) {
      positionMap.get(otyugh.getPosition()).removeMonster();
      otyugh.setPosition(move);
      positionMap.get(move).addMonster(otyugh);
      if (positionMap.get(player.getPositionId()).hasMonster()) {
        if (otyugh.kill()) {
          monsterMessage = ("Oh no!! you met monster and got killed\n");
          player.playerSetStatus(false);
          gameState = GameState.END;

        } else {
          monsterMessage = ("Hurray!! you met monster but got saved!!\n");
        }
      }


    }

  }

  @Override
  public void distributePitsThieves() {
    if (setupFlag) {
      int thieve = length / 5;



      List<Integer> tempInt = new ArrayList<>();

      for (int i = 0; i < positionMap.size(); i++) {
        tempInt.add(i);
      }
      while (thieve > 0) {

        int mapNo = RAND.nextInt(0, tempInt.size() - 1);

        if (positionMap.get(mapNo).getOtyugh() || mapNo == startPoint) {
        //Setting Otyugh.
        } else {
          positionMap.get(mapNo).setOtyugh(new Thief());
          thieve--;
        }

        tempInt.remove(mapNo);

      }
      int pit = length / 5;
      tempInt = new ArrayList<>();
      for (int i = 0; i < positionMap.size(); i++) {
        tempInt.add(i);
      }

      while (pit > 0) {

        int mapNo = RAND.nextInt(0, tempInt.size() - 1);

        if (positionMap.get(mapNo).getOtyugh() || mapNo == startPoint
                ||
                positionMap.get(mapNo).getType().equals(Type.TUNNEL)) {
        //Do nothing.
        } else {
          positionMap.get(mapNo).setOtyugh(new Pit());
          pit--;
        }

        tempInt.remove(mapNo);

      }


    }
  }
}
