import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Editor implements ActionListener {
  private Tournament tournament;
  
  private Menu menu;
  
  private JFrame frame;
  
  private Container container;
  
  private JPanel topPanel;
  
  private JPanel catalogPanel;
  
  private JPanel catalogScrollPanel;
  
  private Bracket bracket;
  
  private JTextField searchBar;
  
  private JTextField title;
  
  private JButton addButton;
  
  private JButton closeButton;
  
  private JButton backButton;
  
  private JComboBox<String> catalogDropdownMenu;
  
  private JScrollPane catalogScroller;
  
  private final String[] catalogDropdownOptions = new String[] { "Teams", "Players" };
  
  private LinkedList teamButtons;
  
  private LinkedList playerButtons;
  
  private boolean showPlayers;
  
  private int nPlayersDisplayed;
  
  private int nTeamsDisplayed;
  
  private int teamID;
  
  private int ID;
  
  private File file;
  
  public Editor(Tournament tournament, Menu menu, int ID) {
    this.tournament = tournament;
    this.menu = menu;
    this.ID = ID;
    this.teamButtons = new LinkedList();
    this.playerButtons = new LinkedList();
    this.frame = new JFrame();
    this.frame.setBounds(0, 0, 1000, 600);
    this.frame.setUndecorated(true);
    this.frame.setDefaultCloseOperation(3);
    this.container = this.frame.getContentPane();
    this.container.setLayout((LayoutManager)null);
    this.topPanel = new JPanel();
    this.topPanel.setBounds(0, 0, 1000, 50);
    this.topPanel.setBackground(Constants.peach);
    this.topPanel.setLayout((LayoutManager)null);
    this.topPanel.setVisible(true);
    this.title = new JTextField(tournament.getName());
    this.title.setBounds(75, 7, 855, 40);
    this.title.setBackground(Constants.peach);
    this.title.setForeground(Constants.salmon);
    this.title.setFont(Constants.largeFont);
    this.title.setBorder((Border)null);
    this.title.addActionListener(this);
    this.closeButton = new JButton("X");
    this.closeButton.setBounds(940, 15, 50, 20);
    this.closeButton.setBackground(Constants.lightPeach);
    this.closeButton.setForeground(Constants.salmon);
    this.closeButton.setFont(Constants.closeButtonFont);
    this.closeButton.setBorderPainted(false);
    this.closeButton.addActionListener(this);
    this.backButton = new JButton("<");
    this.backButton.setBounds(10, 15, 50, 20);
    this.backButton.setBackground(Constants.lightPeach);
    this.backButton.setForeground(Constants.salmon);
    this.backButton.setFont(Constants.closeButtonFont);
    this.backButton.setBorderPainted(false);
    this.backButton.addActionListener(this);
    this.topPanel.add(this.closeButton);
    this.topPanel.add(this.backButton);
    this.topPanel.add(this.title);
    this.catalogPanel = new JPanel();
    this.catalogPanel.setBounds(750, 50, 250, 550);
    this.catalogPanel.setBackground(Constants.lightPeach);
    this.catalogPanel.setLayout((LayoutManager)null);
    this.catalogPanel.setVisible(true);
    this.searchBar = new JTextField();
    this.searchBar.setBounds(10, 10, 230, 35);
    this.searchBar.setFont(Constants.regularFont);
    this.searchBar.addActionListener(this);
    this.addButton = new JButton("+");
    this.addButton.setBounds(200, 55, 40, 35);
    this.addButton.setBackground(Constants.peach);
    this.addButton.setForeground(Constants.lightYellow);
    this.addButton.setFont(Constants.largeFont);
    this.addButton.setBorder((Border)null);
    this.addButton.addActionListener(this);
    this.catalogDropdownMenu = new JComboBox<>(this.catalogDropdownOptions);
    this.catalogDropdownMenu.setBounds(10, 55, 190, 35);
    this.catalogDropdownMenu.setBackground(Constants.peach);
    this.catalogDropdownMenu.setForeground(Constants.lightPeach);
    this.catalogDropdownMenu.setFont(Constants.smallFont);
    this.catalogDropdownMenu.addActionListener(this);
    this.catalogScrollPanel = new JPanel();
    this.catalogScrollPanel.setLayout(new BoxLayout(this.catalogScrollPanel, 3));
    this.catalogScrollPanel.setBackground(Constants.yellow);
    this.catalogScroller = new JScrollPane(this.catalogScrollPanel);
    this.catalogScroller.setBounds(0, 100, 250, 450);
    this.catalogScroller.setBorder((Border)null);
    this.catalogScroller.setHorizontalScrollBarPolicy(31);
    this.catalogScroller.getVerticalScrollBar().setBackground(Constants.lightYellow);
    this.catalogScroller.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
          protected void configureScrollBarColors() {
            this.thumbColor = Constants.peach2;
          }
        });
    this.catalogPanel.add(this.searchBar);
    this.catalogPanel.add(this.addButton);
    this.catalogPanel.add(this.catalogDropdownMenu);
    this.catalogPanel.add(this.catalogScroller);
    this.container.add(this.topPanel);
    this.container.add(this.catalogPanel);
    this.file = new File("./database/editor" + ID + ".txt");
    if (this.file.exists()) {
      try {
        FileReader fileReader = new FileReader(this.file);
        BufferedReader reader = new BufferedReader(fileReader);
        tournament.setName(reader.readLine());
        this.title.setText(tournament.getName());
        this.teamID = Integer.parseInt(reader.readLine());
        if (reader.readLine().equals("started"))
          tournament.start(); 
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } else {
      try {
        this.file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
    for (int i = 1; i <= this.teamID; i++) {
      File tempFile = new File("./database/team" + ID + "." + i + ".txt");
      if (tempFile.exists()) {
        Team newTeam = new Team(this, i);
        Button teamButton = new Button(newTeam, this);
        tournament.add(teamButton.getTeam());
        if (tournament.getNTeams() > 1)
          tournament.add(new Match()); 
        this.teamButtons.add(teamButton);
        Player[] players = newTeam.getPlayers();
        for (int j = 0; j < 5; j++) {
          Button playerButton = new Button(players[j], newTeam, this);
          this.playerButtons.add(playerButton);
        } 
      } 
    } 
    this.bracket = new Bracket(tournament, this);
    if (tournament.hasStarted())
      this.bracket.readData(); 
    this.container.add(this.bracket);
    saveData();
  }
  
  public void open() {
    update();
    this.frame.setVisible(true);
  }
  
  public void enableFrame() {
    this.frame.setEnabled(true);
  }
  
  public void disableFrame() {
    this.frame.setEnabled(false);
  }
  
  public void update() {
    this.nPlayersDisplayed = 0;
    this.nTeamsDisplayed = 0;
    this.bracket.update();
    this.title.setText(this.tournament.getName());
    if (this.showPlayers) {
      this.addButton.setEnabled(false);
      this.catalogScrollPanel.removeAll();
      LinkedList players = this.tournament.searchPlayers(this.searchBar.getText());
      if (players != null)
        for (int i = 0; i < players.getSize(); i++) {
          Player curPlayer = players.getPlayer(i);
          for (int j = 0; j < this.playerButtons.getSize(); j++) {
            Button curButton = this.playerButtons.getButton(j);
            if (curPlayer == curButton.getPlayer())
              displayPlayerButton(curButton); 
          } 
        }  
    } else {
      this.addButton.setEnabled(true);
      this.catalogScrollPanel.removeAll();
      LinkedList teams = this.tournament.searchTeams(this.searchBar.getText());
      if (teams != null)
        for (int i = 0; i < teams.getSize(); i++) {
          Team curTeam = teams.getTeam(i);
          for (int j = 0; j < this.teamButtons.getSize(); j++) {
            Button curButton = this.teamButtons.getButton(j);
            if (curTeam == curButton.getTeam())
              displayTeamButton(curButton); 
          } 
        }  
    } 
    this.frame.repaint();
  }
  
  public void saveData() {
    this.tournament.setName(this.title.getText());
    if (!this.tournament.hasStarted()) {
      try {
        FileWriter fileWriter = new FileWriter(this.file, false);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        this.tournament.setName(this.title.getText());
        writer.write(this.tournament.getName());
        writer.newLine();
        writer.write(this.teamID);
        writer.newLine();
        writer.write("not started");
        writer.flush();
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } else {
      this.bracket.saveData();
    } 
  }
  
  public void displayPlayerButton(Button playerButton) {
    if (!this.showPlayers)
      return; 
    if (this.nPlayersDisplayed % 2 == 1) {
      playerButton.setBackground(Constants.lightYellow);
    } else {
      playerButton.setBackground(Constants.peach);
    } 
    playerButton.setText(playerButton.getPlayer().getName());
    playerButton.setFont(Constants.smallFont);
    playerButton.setForeground(Constants.salmon);
    playerButton.setBorderPainted(false);
    playerButton.setMinimumSize(new Dimension(250, 30));
    playerButton.setMaximumSize(new Dimension(250, 30));
    playerButton.setPreferredSize(new Dimension(250, 30));
    playerButton.addActionListener(this);
    this.catalogScrollPanel.add(playerButton);
    this.nPlayersDisplayed++;
    this.frame.repaint();
    this.frame.setVisible(true);
  }
  
  public void displayTeamButton(Button teamButton) {
    if (this.showPlayers)
      return; 
    if (this.nTeamsDisplayed % 2 == 1) {
      teamButton.setBackground(Constants.lightYellow);
    } else {
      teamButton.setBackground(Constants.peach);
    } 
    teamButton.setText(teamButton.getTeam().getName());
    teamButton.setFont(Constants.regularFont);
    teamButton.setForeground(Constants.salmon);
    teamButton.setMinimumSize(new Dimension(250, 50));
    teamButton.setMaximumSize(new Dimension(250, 50));
    teamButton.setPreferredSize(new Dimension(250, 50));
    teamButton.setBorderPainted(false);
    teamButton.addActionListener(this);
    this.catalogScrollPanel.add(teamButton);
    this.nTeamsDisplayed++;
    this.frame.repaint();
    this.frame.setVisible(true);
  }
  
  public int getID() {
    return this.ID;
  }
  
  public Tournament getTournament() {
    return this.tournament;
  }
  
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == this.closeButton) {
      saveData();
      System.exit(0);
    } else if (event.getSource() == this.backButton) {
      saveData();
      this.frame.dispose();
      this.menu.open();
    } else if (event.getSource() == this.catalogDropdownMenu) {
      if (this.catalogDropdownMenu.getSelectedItem().toString() == "Players") {
        this.showPlayers = true;
      } else {
        this.showPlayers = false;
      } 
      update();
    } else if (event.getSource() == this.addButton && this.tournament.getNTeams() < 32 && !this.tournament.hasStarted()) {
      this.teamID++;
      Team newTeam = new Team(this, this.teamID);
      Button teamButton = new Button(newTeam, this);
      this.tournament.add(newTeam);
      if (this.tournament.getNTeams() > 1)
        this.tournament.add(new Match()); 
      this.teamButtons.add(teamButton);
      Player[] newPlayers = teamButton.getTeam().getPlayers();
      for (int i = 0; i < 5; i++) {
        this.tournament.add(newPlayers[i]);
        this.playerButtons.add(new Button(newPlayers[i], teamButton.getTeam(), this));
      } 
      saveData();
      this.frame.setEnabled(false);
      teamButton.openTeamEditor();
    } else if (event.getSource() == this.addButton && this.tournament.hasStarted()) {
      JOptionPane.showMessageDialog(this.container, "This tournament has started, no more changes can be made to the teams");
    } else if (event.getSource() == this.addButton && this.tournament.getNTeams() == 32) {
      JOptionPane.showMessageDialog(this.container, "The tournament editor is full (32/32)");
    } else if (event.getSource() == this.searchBar) {
      update();
    } else if (this.showPlayers) {
      for (int i = 0; i < this.tournament.getNPlayers(); i++) {
        if (event.getSource() == this.playerButtons.getButton(i))
          this.playerButtons.getButton(i).openPlayerEditor(); 
      } 
    } else {
      for (int i = 0; i < this.tournament.getNTeams(); i++) {
        if (event.getSource() == this.teamButtons.getButton(i))
          this.teamButtons.getButton(i).openTeamEditor(); 
      } 
    } 
  }
}
