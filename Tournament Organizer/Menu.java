import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Menu implements ActionListener {
  private JFrame frame;
  
  private Container container;
  
  private JLabel title;
  
  private JScrollPane tournamentScroller;
  
  private JButton addTournamentButton;
  
  private JButton closeButton;
  
  private JPanel scrollPanel;
  
  private LinkedList tournamentButtons;
  
  private int nTournamentsDisplayed;
  
  private File file;
  
  private int editorID;
  
  public static void main(String[] args) {
    Menu menu = new Menu();
    menu.frame.setVisible(true);
  }
  
  public Menu() {
    initialize();
  }
  
  private void initialize() {
    this.tournamentButtons = new LinkedList();
    this.frame = new JFrame();
    this.frame.setBounds(0, 0, 1000, 600);
    this.frame.setUndecorated(true);
    this.frame.setDefaultCloseOperation(3);
    this.container = this.frame.getContentPane();
    this.container.setLayout((LayoutManager)null);
    this.container.setBackground(new Color(255, 245, 220));
    this.closeButton = new JButton("X");
    this.closeButton.setBounds(940, 15, 50, 20);
    this.closeButton.setBackground(Constants.lightPeach);
    this.closeButton.setForeground(Constants.salmon);
    this.closeButton.setFont(Constants.closeButtonFont);
    this.closeButton.setBorderPainted(false);
    this.closeButton.addActionListener(this);
    this.title = new JLabel("Tournament Editor");
    this.title.setBounds(0, 0, 1000, 130);
    this.title.setHorizontalAlignment(0);
    this.title.setBackground(Constants.peach2);
    this.title.setForeground(Constants.lightPeach);
    this.title.setOpaque(true);
    this.title.setFont(Constants.titleFont);
    this.addTournamentButton = new JButton("Add New Tournament ");
    this.addTournamentButton.setBounds(325, 515, 350, 50);
    this.addTournamentButton.setBackground(Constants.peach);
    this.addTournamentButton.setForeground(Constants.lightPeach);
    this.addTournamentButton.setFont(Constants.regularFont);
    this.addTournamentButton.setBorderPainted(false);
    this.addTournamentButton.addActionListener(this);
    this.scrollPanel = new JPanel();
    this.scrollPanel.setLayout(new BoxLayout(this.scrollPanel, 3));
    this.scrollPanel.setBackground(new Color(255, 255, 240));
    this.tournamentScroller = new JScrollPane(this.scrollPanel);
    this.tournamentScroller.setHorizontalScrollBarPolicy(31);
    this.tournamentScroller.setBounds(150, 150, 700, 350);
    this.tournamentScroller.setBorder((Border)null);
    this.tournamentScroller.getVerticalScrollBar().setBackground(Constants.lightYellow);
    this.tournamentScroller.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
          protected void configureScrollBarColors() {
            this.thumbColor = Constants.peach2;
          }
        });
    this.container.add(this.closeButton);
    this.container.add(this.title);
    this.container.add(this.addTournamentButton);
    this.container.add(this.tournamentScroller);
    this.frame.setVisible(true);
    this.frame.setResizable(false);
    this.file = new File("./database/menu.txt");
    if (this.file.exists()) {
      try {
        FileReader fileReader = new FileReader(this.file);
        BufferedReader reader = new BufferedReader(fileReader);
        this.editorID = Integer.parseInt(reader.readLine());
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
    for (int i = 1; i <= this.editorID; i++) {
      File tempFile = new File("./database/", "editor" + i + ".txt");
      if (tempFile.exists()) {
        Button tournamentButton = new Button(new Tournament(), this, i);
        this.tournamentButtons.add(tournamentButton);
        displayTournamentButton(tournamentButton);
      } 
    } 
  }
  
  public void open() {
    update();
    this.frame.setVisible(true);
  }
  
  public void update() {
    for (int i = 0; i < this.tournamentButtons.getSize(); i++) {
      Button tempButton = this.tournamentButtons.getButton(i);
      if (tempButton.getTournament().getName().equals("")) {
        tempButton.setText("tournament");
        tempButton.setFont(Constants.largeItalicFont);
      } else {
        tempButton.setText(tempButton.getTournament().getName());
        tempButton.setFont(Constants.largeFont);
      } 
    } 
    this.frame.repaint();
    this.frame.setVisible(true);
  }
  
  public void displayTournamentButton(Button tournamentButton) {
    this.nTournamentsDisplayed++;
    if (this.nTournamentsDisplayed % 2 == 0) {
      tournamentButton.setBackground(Constants.lightYellow);
    } else {
      tournamentButton.setBackground(Constants.yellow);
    } 
    if (tournamentButton.getTournament().getName().equals("")) {
      tournamentButton.setText("tournament");
      tournamentButton.setFont(Constants.largeItalicFont);
    } else {
      tournamentButton.setText(tournamentButton.getTournament().getName());
      tournamentButton.setFont(Constants.largeFont);
    } 
    tournamentButton.setForeground(Constants.salmon);
    tournamentButton.setBorderPainted(false);
    tournamentButton.setMinimumSize(new Dimension(700, 100));
    tournamentButton.setMaximumSize(new Dimension(700, 100));
    tournamentButton.setPreferredSize(new Dimension(700, 100));
    tournamentButton.addActionListener(this);
    this.scrollPanel.add(tournamentButton);
    this.frame.repaint();
    this.frame.setVisible(true);
  }
  
  public void saveData() {
    try {
      FileWriter fileWriter = new FileWriter(this.file, false);
      BufferedWriter writer = new BufferedWriter(fileWriter);
      writer.write(this.editorID);
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == this.addTournamentButton) {
      this.editorID++;
      String name = JOptionPane.showInputDialog(this.container, "What is the name of this tournament?", "");
      Button tournamentButton = new Button(new Tournament(name), this, this.editorID);
      this.tournamentButtons.add(tournamentButton);
      saveData();
      displayTournamentButton(tournamentButton);
    } else if (event.getSource() == this.closeButton) {
      System.exit(0);
    } else {
      for (int i = 0; i < this.tournamentButtons.getSize(); i++) {
        Button cur = this.tournamentButtons.getButton(i);
        if (event.getSource() == cur) {
          this.frame.setVisible(false);
          cur.openTournamentEditor();
        } 
      } 
    } 
  }
}
