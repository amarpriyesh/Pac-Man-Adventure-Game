package view;

import controller.DungeonController;
import dungeon.dungeon.GameState;
import dungeon.dungeon.ReadOnlyDungeon;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


/**
 * The below class represents the DungeonView with which the dungeon
 * controller would be interacting to make the gameplay more interactive.
 */
public class DungeonViewImpl extends JFrame implements DungeonView {
  private final BoardPanel board;
  private final JScrollPane scroolPne;
  private final JLabel description;
  private final ScrollPane labelScroll;
  private JSpinner lengthTxt;
  private JSpinner widthTxt;
  private JSpinner interconnectivityTxt;
  private JSpinner artifactsTxt;
  private JSpinner otyughTxt;
  private DungeonController controller;
  private ReadOnlyDungeon model;
  private MouseHandle mouseHandeler;
  private boolean scroll;
  private int zoom;
  private int lengthDun;
  private int widthDun;
  private int degreeDun;
  private boolean wrapDun;
  private int treasureDun;
  private int otyughDun;
  private boolean runMonster;
  Thread thread = new Thread() {
    public void run() {
      runMonster();
    }
  };


  /**
   * The below constructor takes in the read only model and helps creating a view with
   * different J componenets.
   *
   * @param model represents readonly Model.
   */
  public DungeonViewImpl(ReadOnlyDungeon model) {
    super("Dungeon");
    lengthDun = 10;
    widthDun = 9;
    degreeDun = 30;
    wrapDun = true;
    treasureDun = 40;
    otyughDun = 3;
    scroll = false;
    setLocation(30, 30);
    this.setJMenuBar(createMenuBar());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.model = model;
    description = new JLabel("<HTML>Welcome to the <br/> Dungeon</HTML>");
    description.setVerticalAlignment(JLabel.TOP);
    zoom = 5;
    JButton plus;
    plus = new JButton("<HTML>Zoom<br>In</HTML>");
    plus.setBounds(300, 0, 68, 75);
    plus.addActionListener(l -> {
      setZoomPlus();
    });
    plus.setVisible(true);
    this.add(plus);
    JButton minus;
    minus = new JButton("<HTML>Zoom<br>Out</HTML>");
    minus.addActionListener(l -> {
      setZoomMinus();
    });
    minus.setBounds(368, 0, 68, 75);
    minus.setVisible(true);
    this.add(minus);
    JButton playerDetail;
    playerDetail = new JButton("<HTML>Player<br>Detail</HTML>");
    playerDetail.setBounds(436, 0, 67, 75);
    playerDetail.addActionListener(l -> {
      getPlayerDetails();
    });
    playerDetail.setVisible(true);
    this.add(playerDetail);
    description.setLocation(0, 0);
    description.setSize(new Dimension(500, 300));
    description.setPreferredSize(new Dimension(250, 200));
    labelScroll = new ScrollPane();
    labelScroll.setSize(300, 75);
    labelScroll.add(description);
    runMonster = false;
    this.add(labelScroll);
    board = new BoardPanel(model, this);
    board.setLayout(new BorderLayout());
    scroolPne = new JScrollPane(board);
    scroolPne.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroolPne.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    this.add(scroolPne);
    resize();
    this.setVisible(true);
    this.setFocusable(true);
    this.requestFocus();
    this.refresh();
  }

  private void resize() {
    if (board.getPreferredSize().getWidth() > 800 && board.getPreferredSize().getHeight() < 875) {
      scroolPne.setSize(board.getPreferredSize());
      setSize(800, (int) board.getPreferredSize().getHeight() + 50);
    } else if (board.getPreferredSize().getWidth() < 800
            && board.getPreferredSize().getHeight() > 875) {
      scroolPne.setSize(board.getPreferredSize());
      setSize((int) board.getPreferredSize().getWidth(), 875 + 50);
    } else if (board.getPreferredSize().getWidth() > 800
            && board.getPreferredSize().getHeight() > 875) {
      scroolPne.setSize(board.getPreferredSize());
      setSize((int) board.getPreferredSize().getWidth(),
              (int) board.getPreferredSize().getHeight() + 50);
    } else {
      scroolPne.setSize(board.getPreferredSize());
      this.setSize(new Dimension((int) board.getPreferredSize().getWidth(),
              (int) board.getPreferredSize().getHeight() + 50));

    }
  }

  @Override
  public void setDungeonImage(ReadOnlyDungeon dun) {
    // rModel = new TicTacToeModel();
    this.model = dun;
    board.setModel(dun);
    board.repaint();
    mouseHandeler.setNewCoordinates(model.dimensions()[0], model.dimensions()[1], zoom);
  }

  /**
   * Set up the controller to handle click events in this view.
   *
   * @param listener the controller
   */
  @Override
  public void addClickListener(DungeonController listener) {

    this.controller = listener;
    this.addKeyListener(new KeyListner(listener));
    mouseHandeler = new MouseHandle(controller, model.dimensions()[0], model.dimensions()[1]);
    board.addMouseListener(mouseHandeler);


  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  @Override
  public void refresh() {
    this.repaint();
    board.repaint();
  }

  /**
   * Make the view visible to start the game session.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  private int[] getDungeonParams() throws ParseException {
    lengthTxt.commitEdit();
    lengthDun = (int) lengthTxt.getValue();
    widthTxt.commitEdit();
    widthDun = (int) widthTxt.getValue();
    interconnectivityTxt.commitEdit();
    artifactsTxt.commitEdit();
    otyughTxt.commitEdit();


    degreeDun = (int) interconnectivityTxt.getValue();
    treasureDun = (int) artifactsTxt.getValue();
    otyughDun = (int) otyughTxt.getValue();


    int wrap1 = 0;
    if (wrapDun) {
      wrap1 = 1;
    } else {
      wrap1 = 0;
    }
    return new int[]{lengthDun, widthDun, degreeDun, wrap1, treasureDun, otyughDun};
  }

  private JMenuBar createMenuBar() {

    JMenuBar menuBar;
    JMenu menu;


    menuBar = new JMenuBar();


    menu = new JMenu("File");
    menu.getAccessibleContext().setAccessibleDescription("Click to create new game");
    menuBar.add(menu);
    JMenuItem itemsMen;

    itemsMen = new JMenuItem("New Dungeon");
    itemsMen.addActionListener(l -> openPop());
    menu.add(itemsMen);

    itemsMen = new JMenuItem("Reset");
    itemsMen.addActionListener(l -> resetGame());
    menu.add(itemsMen);


    itemsMen = new JMenuItem("Re-live");
    itemsMen.addActionListener(l -> controller.reLive());
    menu.add(itemsMen);


    itemsMen = new JMenuItem("Exit");
    itemsMen.addActionListener(l -> exitGame());
    menu.add(itemsMen);


    menu.addSeparator();
    JCheckBoxMenuItem checkBox;
    checkBox = new JCheckBoxMenuItem("Fix Scroll");
    checkBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        scroll = (e.getStateChange() == 1);
        resetFocus();
        refresh();
      }
    });
    menu.add(checkBox);

    checkBox = new JCheckBoxMenuItem("Cheat Mode");
    checkBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        boolean val = (e.getStateChange() == 1);
        controller.setCheatMode(val);
        resetFocus();
        refresh();
      }
    });
    menu.add(checkBox);

    JCheckBoxMenuItem cbmi1 = new JCheckBoxMenuItem("Move Monster");
    JCheckBoxMenuItem cbmi4 = new JCheckBoxMenuItem("Animation new thread");
    cbmi1.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        boolean val = (e.getStateChange() == 1);
        if (val) {
          controller.setMonsters();
          thread.start();
          runMonster = true;
          controller.toggleRunMonster(true);
          cbmi4.setVisible(false);
        } else {
          runMonster = false;
          controller.toggleRunMonster(false);
          cbmi1.setVisible(false);
        }
        resetFocus();
      }
    });

    menu.add(cbmi1);

    JCheckBoxMenuItem cbmi2 = new JCheckBoxMenuItem("Add Thieve & Pit");
    cbmi2.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        boolean val = (e.getStateChange() == 1);
        if (val) {
          controller.distributePitTheve();
          cbmi2.setVisible(false);
        }
        resetFocus();
        refresh();
      }
    });
    menu.add(cbmi2);

    JCheckBoxMenuItem cbmi3 = new JCheckBoxMenuItem("Animate");
    cbmi3.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        boolean val = (e.getStateChange() == 1);
        board.setPanelAnime(val);
        resetFocus();
      }
    });
    menu.add(cbmi3);


    cbmi4.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        boolean val = (e.getStateChange() == 1);
        if (val) {
          runMonster = true;
          thread.start();
          cbmi1.setVisible(false);
        } else {
          runMonster = false;
          cbmi4.setVisible(false);
        }
        resetFocus();
      }
    });
    menu.add(cbmi4);
    return menuBar;

  }

  private void openPop() {


    JFrame frame = new JFrame("Create Dungeon");


    frame.setSize(300, 300);
    frame.setLocation(200, 200);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


    frame.setLayout(new GridLayout(7, 2));

    JLabel length;

    length = new JLabel("Length");
    frame.add(length);

    lengthTxt = new JSpinner(new SpinnerNumberModel(10, 6, 100, 1));
    frame.add(lengthTxt);

    JLabel width;

    width = new JLabel("Width");
    frame.add(width);

    widthTxt = new JSpinner(new SpinnerNumberModel(10, 6, 100, 1));
    frame.add(widthTxt);

    JLabel interconnectivity;

    interconnectivity = new JLabel("Interconnectivity");
    frame.add(interconnectivity);

    interconnectivityTxt = new JSpinner(new SpinnerNumberModel(10,
            0, 1000, 1));
    frame.add(interconnectivityTxt);
    JLabel wrap;

    wrap = new JLabel("Wrap");
    frame.add(wrap);
    JCheckBox wrapTxt;
    wrapTxt = new JCheckBox("Wrap");
    wrapTxt.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        wrapDun = (e.getStateChange() == 1);
      }
    });
    frame.add(wrapTxt);
    JLabel artifacts;

    artifacts = new JLabel("Artifacts");
    frame.add(artifacts);

    artifactsTxt = new JSpinner(new SpinnerNumberModel(40, 0,
            100, 1));
    frame.add(artifactsTxt);
    JLabel otyugh;
    otyugh = new JLabel("Otyughs");
    frame.add(otyugh);

    otyughTxt = new JSpinner(new SpinnerNumberModel(4, 1, 100, 1));
    frame.add(otyughTxt);
    JButton set;
    set = new JButton("Set");
    set.setActionCommand("Set");
    set.addActionListener(l -> {
      try {
        controller.createModel(getDungeonParams());
      } catch (ParseException e) {
        e.printStackTrace();
      }

      frame.setVisible(false);
      resize();
      resetFocus();
    });

    frame.add(set);
    frame.setVisible(true);
    frame.setFocusable(true);
    frame.requestFocus();
    pack();


  }

  @Override
  public void setDescription(String str) {

    description.setText(str);
    description.setVisible(true);
    labelScroll.setScrollPosition(0, 0);
  }

  @Override
  public void setScrollPos(int x, int y) {
    if (scroll) {
      scroolPne.getHorizontalScrollBar().setValue(x);


      scroolPne.getVerticalScrollBar().setValue(y);
    }
    //refresh();
  }

  private void setZoomPlus() {
    if (zoom < 15) {
      zoom++;
      board.setZoom(zoom);
      mouseHandeler.setNewCoordinates(model.dimensions()[0], model.dimensions()[1], zoom);

      refresh();
      resetFocus();
    }
  }

  private void setZoomMinus() {
    if (zoom > 2) {
      zoom--;
      board.setZoom(zoom);
      mouseHandeler.setNewCoordinates(model.dimensions()[0], model.dimensions()[1], zoom);

      refresh();
      resetFocus();
    }
  }

  private void resetGame() {
    int wrap = 0;
    if (wrapDun) {
      wrap = 1;
    } else {
      wrap = 0;
    }
    controller.createModel(new int[]{lengthDun, widthDun, degreeDun, wrap, treasureDun, otyughDun});

  }

  private void exitGame() {
    controller.exitProgram();
  }

  private void getPlayerDetails() {
    JFrame frame = new JFrame("Player Description");
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    frame.setLayout(new GridLayout(1, 1));
    JLabel lab = new JLabel();
    lab.setVerticalAlignment(JLabel.TOP);
    lab.setText("<HTML>" + model.getPlayerDetails().replaceAll("\\r|\\n", "<br>") + "</HTML>");
    frame.add(lab);
    frame.setSize(300, 200);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    resetFocus();

  }

  @Override
  public void showMessage(String s) {
    JFrame frame = new JFrame("Game State");
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    frame.setLayout(new GridLayout(1, 1));
    JLabel lab = new JLabel();
    lab.setVerticalAlignment(JLabel.TOP);
    lab.setText("<HTML>" + s.replaceAll("\\r|\\n", "<br>") + "</HTML>");
    frame.add(lab);
    frame.setSize(300, 200);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private void runMonster() {
    while (runMonster) {
      try {
        Thread.sleep(500);
        this.refresh();
        if(model.getGameState()== GameState.END){
          showMessage(model.gameResult());
          break;
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
