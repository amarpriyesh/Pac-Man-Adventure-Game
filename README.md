# OVERVIEW
This program creates a Dungeon game where the player could move to the end point collecting gems and arrows, the Dungeon now has Otyugh and it provides option to add thieves, pits, and moving monster  as well which could kill the user if they do not  play visely.
The game ends when the user reaches the end point killing the  otyugh at the end cave.


# FEATURES
1. The program features creation of players and assigning of treasures to the location so  that the player could go to each location and collect them.
2. It imititates the game pacman.
3. The player could also kill the otyugh with arrow and  get killed by otyugh if they come in contact with  them.
4. Player could collect the gems and arrows kept in the cave.
5. Player should be aware of thieves and pits and monsters.
6. The file menu provides following options

1.
Create a new Dungeon:
File > new Dungeon fill the following text options.
(Length, Width, Degree, Wrap(true/false), percentArtifact, numOyutugh)

Click Set.

2.
Reset a dungeon
Click file > reset

3
make player alive
Click file > re live

4
get pits and thieves
click file > add thieve and pit

4
get moving monster
click file > setMoving monster




# RUN THE PROGRAM
Please note the jar file is kept in src folder as it functions from there.

1. Through System.

1. Install the 11.0.2 version of  java on your system.
2. Copy the contents of /src/Dungeon1.jar to your system lets say c directory.
3.  Run the Jar file using below command.
	C:\java\java.exe -jar C:\Dungeon.jar

	One of the examples could be:
	C:\Program Files\Java\jdk-11.0.12\bin>java.exe -jar d:Dungeon1.jar 10 6 5 false 30 5

Please provide input in following format:

(Length, Width, Degree, Wrap(true/false), percentArtifact, numOyutugh)

java.exe -jar d:Dungeon.jar 10 6 5 false 30 5

One could aslo add  a seed at last or just try executing the game without parameters for a predefined dungeon gameplay.

2. Through Intelli J

1. Download the .zip file, extract it.
2. Open IntelliJ IDEA click file>NEW>PROJECT
3. Locate the folder and open it.
4. In the res folder open Dungeon_jar.
5. Right click and run the JAR file


# HOW TO USE THE PROGRAM
1. The program can run by executing the jar to play the gamme in GUI mode or With the help of keybord and mouse user could provide input to run the game.
2. The user has to follow the steps provided on the screen for the gameplay.
3. The game usually starts with a preloaded game, the user could move around the dungeon using the arrow keys, pick treasure or shoot an arrow.
4. Player could play and move around the dungeon using the following controls.
5. To get info regarding starting the game and extra features read the features section.


# controls description

1. User could move with the arrow keys on the keyboard to move around the Dungeon.
2. User could click on the valid cells to move to that particular cell.
3. To pick up a treasure Player could press the following keys in succession.
   "p a" to pick an arrow.
   "p d" to pick diamond.
   "p e" to pick sapphire.
   "p r" to pick ruby.

4. To shoot an arrow player can press the following keys. user is supposed to press "s" for shoot then prode the distance and then the arrow keys to provide teh direction.

"s 1 ->" to shoot an arrow towards east for 1 distance.
"s 4 ^" to shoot an arrow towards North for 4 distance.

5. To abort the above command in the middle user could press "c".
6. All the commands are in lower cases.

# DESCRIPTION OF EXAMPLE
In Res folder we have following files:


Graphical with dungeon.
Run 1 --  C:\Users\amarp\CS5010\Dungeon\res\payerMovingInTheDungeonKillingWinningTheGame.txt
This is a wrapping dungeon with only one Otyugh, this file has graphical representation as well for clarity.

1. Player starts at the starting cave.
2. Player could use cheats like:
   D: To display the dungeon.
   O: To display the otyugh.
   G: To display the treasures.

3. P is selected by default for initial configuration.
4. This example of dungeon is shown without  Gems for the clarity of the structure.
5. The player could be moved using the following arguments M-P-S.

M for move
P for pickup
S for shoot

Player P, has 3 Arrow
You are in a CAVE, Player Position:31
Doors lead to  W
Move, Pickup, or Shoot (M-P-S)?m

Where to? w

Player moved to W

6. The player description and the location descrition is printed simultaneously showing the player treasures and weapons.
7. player has to press "p" to pick up a treasure or an arrow and  press shoot "s" to shoot an arrow towards the otyugh.
8. The  document also shows player moving in all the corners of the dungeon.
10. The player shoots the otyugh twice to kill the same and then reaches the end point to ensure his victory.
9. As the player reaches end point E the game ends.


Run 2 -- ..\res\playerMovingInTheDungeonShootingGettingKilled.txt


1.  dungeon is non wrapping  the player could not move beyond the edges.
2. It then displays the dungeon with O as Otyugh P as player  and E as the position to end.
3. P is selected by default for initial configuration.
4. The example of dungeon is shown without  Gems for the clarity of the structure.
5. Next the player could be seen moving to each and every corner of the dungeon and collecting arrows and treasure as follows:

Player P, has 3 Arrow
You are in a CAVE, Player Position:13
You find 1 Arrow here
Doors lead to  W
Move, Pickup, or Shoot (M-P-S)?p

What? arrow
You pick up a ARROW.

6. The player description and the location descrition is printed simultaneously.

7. Since the player does not kill the otyugh and reaches its place the player gets killed by the Otyugh and teh game ends.

Text based output runs:
1. ..\res\playerInjuringOtyughGettingEatenTextBasedWrapping.txt
2. ..\res\PlayerMovingShootingPickingWinningTextBased

Run 3. The gui description has been given at below path
..\res\GUI RUN.pdf



# DESIGN/ MODEL CHANGES

Please refer to the olddesign, newdesign and test plan in the following folder.
..\res\Design Directory
Following changes were done:
1. Following extra methods were added to the new model for ease of testing and for added functionality.


2. Other than Otyugh three extra classes monster, thief and pit were added and the existing code was reused  to make the new features compatible with teh existing model.

4. DungeonView class and an interface was added to add extra flexibility to the gameplay.

5. Otyugh type was added as enum.

Following extra method was introduced and the the unused methods were removed.
DungeonImpl:

Player  changes:

String playerDetails();
public boolean playerStatus();
public void playerSetStatus(boolean status);
public void playerRemoveTreasure();

Otyugh changes:

public int getPosition();
public void setPosition(int position);
public OtyughType getType();


Position changes:

public  void setHasPlayer(boolean val);
public  boolean hasPlayer();
public  boolean hasPlayerVisited();
public  void setPlayerVisited(boolean val);
public boolean hasDiamond();
public boolean hasRuby();
public boolean hasSapphire();
public boolean hasArrow();
public boolean isValidPosition(int pos);
public boolean hasDeadOtyugh();
public void addMonster(Otyugh monster);
public OtyughType getOtyughType();
public void removeMonster();



Dungeon Changes:
public void setPlayerPos(int m);
public String isValidPosition(int m);
void reLive();
public String gameResult();
public void setCheatMode( boolean bool);
public void setMonsters();
public void toggleRunMonster(boolean bool);
public void distributePitsThieves();
public boolean hasMonster();



Dungeon controller:

void playGame(Dungeon d);
public void createModel(int[] arr);
public void move(Readable str) throws IOException;
public  void shoot(Readable str) throws IOException;
public  void pickUp(Readable str) throws IOException;
public void handleCellClick(int m);
public void exitProgram();
public void reLive();
public void setCheatMode( boolean bool);
public void setMonsters();
public void toggleRunMonster(boolean bool);
public void distributePitTheve();






# ASSUMPTIONS
1. It is assumed that the min dungeon size for the non wrapping dungeon is of type 5x5 and for the wrapping dungeon it is 8x4 with no edges lesser  than 3;
2. Also, the user can't make a dungeon greater than 100x100 size.
3. Percentage of artifact to be set is limited to 100%.
4. The number of otyughs and the degree of dungeon is decides by the algorithm and exceptions are thrown if the limit is exceeded.
5. The number of moving monster/pits/thieves would be length/5.
6. Player has 30 percent chance of surviving the moving monster.
8. Thief steals player's gems.




# LIMITATIONS
1. Minimum dungeon size for the non wrapping dungeon is of type 6X6 and for the wrapping dungeon it is 6x6;



# CITATION
[1]. https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
