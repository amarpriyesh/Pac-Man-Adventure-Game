package view;

import dungeon.dungeon.ReadOnlyDungeon;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
//import java.io.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * The below class represents a board panel where the dungeon board would be drawn, it prints the
 * Dungeon and makes the gameplay more interactive.
 */

class BoardPanel extends JPanel {
  private List<Integer[]> anime;
  private ReadOnlyDungeon model;
  private int zoom;
  private final DungeonViewImpl view;
  private boolean change;
  private boolean pChange;
  private boolean mChange;
  private boolean panelAnime;

  BoardPanel(ReadOnlyDungeon model, DungeonViewImpl view) {
    this.model = model;
    this.view = view;
    zoom = 5;
    change = true;
    anime = new ArrayList<>();
    anime.add(new Integer[]{5, 5});
    anime.add(new Integer[]{2, 5});
    anime.add(new Integer[]{2, 2});
    anime.add(new Integer[]{5, 2});
    change = false;
    pChange = false;
    mChange = false;
    panelAnime = false;
    this.setPreferredSize(new Dimension(50 + 50 * model.dimensions()[0],
            120 + 50 * model.dimensions()[1]));

  }

  @Override
  protected void paintComponent(Graphics g) {
    BufferedImage img;
    BufferedImage player;
    BufferedImage otyugh;
    BufferedImage treasure;
    Class cls = null;


    //System.out.println(model.printDungeonNew());
    Map<String, BufferedImage> list = new HashMap<>();
    try {
      //System.out.println(ImageIO.read(new File(("src/image/NSEW.png"))).toString());
      list.put(" E W N S", ImageIO.read(new File(("src/image/NSEW.png"))));
      list.put(" E N S", ImageIO.read(new File(("src/image/NSE.png"))));
      list.put(" W N S", ImageIO.read(new File(("src/image/NSW.png"))));
      list.put(" E W S", ImageIO.read(new File(("src/image/SEW.png"))));
      list.put(" E W N", ImageIO.read(new File(("src/image/NEW.png"))));
      list.put(" E N", ImageIO.read(new File(("src/image/NE.png"))));
      list.put(" E S", ImageIO.read(new File(("src/image/SE.png"))));
      list.put(" W S", ImageIO.read(new File(("src/image/SW.png"))));
      list.put(" W N", ImageIO.read(new File(("src/image/WN.png"))));
      list.put(" N S", ImageIO.read(new File(("src/image/NS.png"))));
      list.put(" E W", ImageIO.read(new File(("src/image/EW.png"))));
      list.put(" E", ImageIO.read(new File(("src/image/E.png"))));
      list.put(" W", ImageIO.read(new File(("src/image/W.png"))));
      list.put(" N", ImageIO.read(new File(("src/image/N.png"))));
      list.put(" S", ImageIO.read(new File(("src/image/S.png"))));
      list.put("player", ImageIO.read(new File(("src/image/ghost3.png"))));
      list.put("player1", ImageIO.read(new File(("src/image/ghost4.png"))));
      list.put("otyugh", ImageIO.read(new File(("src/image/otyugh.png"))));
      list.put("diamond", ImageIO.read(new File(("src/image/diamond.png"))));
      list.put("ruby", ImageIO.read(new File(("src/image/ruby.png"))));
      list.put("sapphire", ImageIO.read(new File(("src/image/emerald.png"))));
      list.put("arrow", ImageIO.read(new File(("src/image/arrow-white.png"))));
      list.put("black", ImageIO.read(new File(("src/image/black.png"))));
      list.put("monster1", ImageIO.read(new File(("src/image/monster1.png"))));
      list.put("monster2", ImageIO.read(new File(("src/image/monster2.png"))));
      list.put("thief", ImageIO.read(new File(("src/image/thief1.png"))));
      list.put("pit", ImageIO.read(new File(("src/image/stench02.png"))));
      list.put("otyugh1", ImageIO.read(new File(("src/image/otyugh1.png"))));
      list.put("otyugh2", ImageIO.read(new File(("src/image/otyugh2.png"))));
    } catch (IOException e) {

    }


    //System.out.println(model.printDungeonNew());

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    int factor = 10 * zoom;

    int y = 75;
    int m = 0;
    for (int i = 0; i < model.dimensions()[1]; i++) {

      int x = 0;
      for (int j = 0; j < model.dimensions()[0]; j++) {

        // tln(x+" "+y);
        if (model.dungeonMap().get(m) != null) {

          g2d.drawImage(list.get(model.dungeonMap().get(m)[0]), x, y, factor, factor, this);

        } else {
          g2d.drawImage(list.get("black"), x, y, factor, factor, this);
        }
        if (model.dungeonMap().get(m) != null) {
          if (Boolean.valueOf(model.dungeonMap().get(m)[9])) {

            g2d.drawImage(list.get("pit"), x + factor / 4, y + factor / 4,
                    factor / 2, factor / 2, this);

          }
          if (Boolean.valueOf(model.dungeonMap().get(m)[8])) {

            g2d.drawImage(list.get("thief"), x + factor / 4, y + factor / 4,
                    factor / 2, factor / 2, this);

          }
          if (Boolean.valueOf(model.dungeonMap().get(m)[7])) {
            String str;
            if (mChange) {
              str = "monster1";
              mChange = false;
            } else {
              str = "monster2";
              mChange = true;
            }

            g2d.drawImage(list.get(str), x + factor / 4, y + factor / 4, factor / 2,
                    factor / 2, this);
          }
          if (Boolean.valueOf(model.dungeonMap().get(m)[6])) {
            if (Boolean.valueOf(model.dungeonMap().get(m)[10])) {
              g2d.drawImage(list.get("arrow"), x + factor / anime.get(0)[0],
                      y + factor / anime.get(0)[1], factor / 5,
                      factor / 5, this);
            } else {
              g2d.drawImage(list.get("arrow"), x + factor / 2, y + factor / 2,
                      factor / 5, factor / 5, this);

            }
          }

          if (Boolean.valueOf(model.dungeonMap().get(m)[5])) {
            g2d.drawImage(list.get("diamond"), x + factor / anime.get(1)[0],
                    y + factor / anime.get(1)[1],
                    factor / 5, factor / 5, this);
          }

          if (Boolean.valueOf(model.dungeonMap().get(m)[4])) {
            g2d.drawImage(list.get("sapphire"), x + factor / anime.get(2)[0],
                    y + factor / anime.get(2)[1],
                    factor / 5, factor / 5, this);
          }


          if (Boolean.valueOf(model.dungeonMap().get(m)[3])) {
            g2d.drawImage(list.get("ruby"), x + factor / anime.get(3)[0],
                    y + factor / anime.get(3)[1],
                    factor / 5, factor / 5, this);
          }

          if (Boolean.valueOf(model.dungeonMap().get(m)[2])) {
            String str;
            if (pChange) {
              str = "otyugh1";
              pChange = false;
            } else {
              str = "otyugh2";
              pChange = true;
            }
            g2d.drawImage(list.get(str), x + factor / 6, y + factor / 5,
                    (int) (factor / 1.2), (int) (factor / 1.2), this);
          }
          if (Boolean.valueOf(model.dungeonMap().get(m)[1])) {
            String str;
            if (change) {
              str = "player";
              change = false;
            } else {
              str = "player1";
              change = true;
            }

            g2d.drawImage(list.get(str), x + factor / 4,
                    y + factor / 4, factor / 2, factor / 2, this);
            view.setScrollPos(x - view.getWidth() / 2,
                    y - view.getHeight() / 2);

          }
        }

        m++;
        x = x + factor;
      }
      y = y + factor;
    }

    this.setPreferredSize(new Dimension(factor * model.dimensions()[0],
            75 + factor * model.dimensions()[1]));
    //tln( 30 * model.dimensions()[0]);
    if (panelAnime) {
      Integer[] a = anime.get(0);
      anime.remove(0);
      anime.add(a);
    }
  }

  public void setModel(ReadOnlyDungeon model) {
    this.model = model;
  }

  //iterate over board draw x and o accordingly

  public void setZoom(int zoom) {
    this.zoom = zoom;
  }

  public void setPanelAnime(boolean bool) {
    panelAnime = bool;
  }
}
