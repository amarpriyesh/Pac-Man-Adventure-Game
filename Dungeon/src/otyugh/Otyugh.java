package otyugh;

/**
 * The below interface represents the monsters in the cave who have different functions like kill,
 * getting damaged etc.
 */
public interface Otyugh {

  /**
   * The method enables Otyugh to kill the user.
   *
   * @return boolean whether the player got killed or not;
   */
  boolean kill();

  /**
   * The method checks if the Otyugh is alive or not.
   *
   * @return boolean true/false.
   */
  boolean isAlive();

  /**
   * The method reduces Otyugh's life when hit by an arrow.
   *
   * @return String of otyugh's reaction.
   */
  String takeDamage();

  /**
   * The below method returns Otyugh's life.
   *
   * @return number int, 1 means 50 percent, 2 100 percent.
   */
  int otyughLife();

  /**
   * The below method returns the current position of the otyugh.
   * @return position of in integer.
   */
  int getPosition();

  /**
   * The below method sets the position of the otyugh.
   * @param position position to be assigned.
   */
  void setPosition(int position);

  /**
   * The below method returns the type of otyugh as enum.
   * @return type of otyugh.
   */
  OtyughType getType();
}
