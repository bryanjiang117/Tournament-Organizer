import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Bracket extends JPanel implements ActionListener {
  private Match root = null;
  
  private LinkedList teams;
  
  private LinkedList buttons;
  
  private LinkedList matches;
  
  private Tournament tournament;
  
  private Editor editor;
  
  private JButton startButton;
  
  private int nMatches;
  
  private int height;
  
  private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  
  private final int LENGTH = 750;
  
  private final int HEIGHT = 550;
  
  private final int MATCH_LENGTH = 120;
  
  private final int MATCH_HEIGHT = 30;
  
  private File file;
  
  public Bracket(Tournament tournament, Editor editor) {
    this.file = new File("./database/editor" + editor.getID() + ".txt");
    this.tournament = tournament;
    this.editor = editor;
    this.buttons = new LinkedList();
    setLayout((LayoutManager)null);
    setBounds(0, 50, 750, 550);
    setBackground(Constants.lightYellow);
    this.startButton = new JButton();
    this.startButton.setBounds(650, 500, 100, 50);
    this.startButton.setBackground(Constants.green);
    this.startButton.setForeground(Constants.lightPeach);
    this.startButton.setFont(Constants.smallFont);
    this.startButton.setText("Start");
    this.startButton.addActionListener(this);
    update();
  }
  
  public void update() {
    this.teams = getSortedTeams();
    this.matches = this.tournament.getMatches().cloneMatches();
    this.nMatches = this.tournament.getNTeams() - 1;
    this.height = 0;
    if (this.nMatches < 1)
      return; 
    initializeTree();
    assignTeams(this.root);
    assignButtonsAndIDs();
    displayBracket();
    repaint();
    setVisible(true);
  }
  
  public void initializeTree() {
    int nMatches = this.nMatches;
    LinkedList visit = new LinkedList();
    this.buttons.clear();
    this.root = this.matches.getNextMatch();
    nMatches--;
    visit.add(this.root);
    while (nMatches >= Math.pow(2.0D, (this.height + 1))) {
      this.height++;
      int nMatchesInLevel = visit.getSize();
      LinkedList visitNext = new LinkedList();
      for (int i = 0; i < nMatchesInLevel; i++) {
        if (nMatches > 0) {
          Match newMatch = this.matches.getNextMatch();
          visit.getMatch(i).setLeft(newMatch);
          visitNext.add(newMatch);
          nMatches--;
        } 
        if (nMatches > 0) {
          Match newMatch = this.matches.getNextMatch();
          visit.getMatch(i).setRight(newMatch);
          visitNext.add(newMatch);
          nMatches--;
        } 
      } 
      visit = visitNext.cloneMatches();
    } 
    if (nMatches > 0) {
      this.height++;
      int nMatchesInLevel = visit.getSize();
      int i;
      for (i = 0; i < nMatchesInLevel; i++) {
        if (nMatches > 0) {
          Match newMatch = this.matches.getNextMatch();
          visit.getMatch(i).setLeft(newMatch);
          nMatches--;
        } 
      } 
      for (i = 0; i < nMatchesInLevel; i++) {
        if (nMatches > 0) {
          Match newMatch = this.matches.getNextMatch();
          visit.getMatch(i).setRight(newMatch);
          nMatches--;
        } 
      } 
    } 
  }
  
  public void assignTeams(Match curMatch) {
    if (curMatch == null)
      return; 
    assignTeams(curMatch.getLeft());
    assignTeams(curMatch.getRight());
    if (curMatch.getLeft() == null)
      curMatch.setLeftTeam(this.teams.getNextTeam()); 
    if (curMatch.getRight() == null)
      curMatch.setRightTeam(this.teams.getNextTeam()); 
    curMatch.update();
  }
  
  public void assignButtonsAndIDs() {
    int nMatches = this.nMatches;
    int curHeight = 0;
    LinkedList visit = new LinkedList();
    this.root.setID(String.valueOf(this.alphabet.charAt(curHeight)) + "1");
    nMatches--;
    visit.add(this.root);
    addButton(this.root);
    while (nMatches > 0) {
      int count = 1;
      curHeight++;
      int nMatchesInLevel = visit.getSize();
      LinkedList visitNext = new LinkedList();
      for (int i = 0; i < nMatchesInLevel; i++) {
        Match curMatch = visit.getMatch(i);
        if (curMatch.getLeft() != null && nMatches > 0) {
          Match curLeftMatch = curMatch.getLeft();
          curLeftMatch.setID(this.alphabet.charAt(curHeight) + " " + count);
          visitNext.add(curLeftMatch);
          addButton(curLeftMatch);
          nMatches--;
          count++;
        } 
        if (curMatch.getRight() != null && nMatches > 0) {
          Match curRightMatch = curMatch.getRight();
          curRightMatch.setID(this.alphabet.charAt(curHeight) + " " + count);
          visitNext.add(curRightMatch);
          addButton(curRightMatch);
          nMatches--;
          count++;
        } 
      } 
      visit.clear();
      int nMatchesInNextLevel = visitNext.getSize();
      for (int j = 0; j < nMatchesInNextLevel; j++)
        visit.add(visitNext.getNextMatch()); 
    } 
  }
  
  public void displayBracket() {
    int nMatches = this.nMatches;
    removeAll();
    add(this.startButton);
    if (this.tournament.hasStarted()) {
      this.startButton.setBackground(Constants.red);
      this.startButton.setText("Started");
      this.startButton.setEnabled(false);
    } 
    for (int i = 0; i <= this.height; i++) {
      int nRightChildren, nMatchesInColumn = (int)Math.pow(2.0D, i);
      int heightSpace = (550 - nMatchesInColumn * 30) / (nMatchesInColumn + 1);
      int lengthSpace = 25;
      int nSingleLeftChildren = 0;
      int nDisplayedInColumn = 0;
      if (nMatches < nMatchesInColumn) {
        nRightChildren = nMatches - nMatchesInColumn / 2;
      } else {
        nRightChildren = nMatchesInColumn;
      } 
      for (int j = 0; j < nMatchesInColumn; j++) {
        if (nMatches < 1)
          return; 
        Button curButton = this.buttons.getButton(this.nMatches - nMatches);
        if (curButton.getMatch().getWinner() != null)
          curButton.setBackground(Constants.green); 
        if (nRightChildren > 0) {
          curButton.setBounds((this.height - i) * (lengthSpace + 120) + lengthSpace, 
              j * (heightSpace + 30) + heightSpace, 120, 30);
          curButton.setText(curButton.getMatch().getID());
          if (curButton.getMatch().getWinner() != null)
            curButton.setBackground(Constants.green); 
          add(curButton);
          nMatches--;
          nDisplayedInColumn++;
        } else {
          curButton.setBounds((this.height - i) * (lengthSpace + 120) + lengthSpace, (
              j + nSingleLeftChildren) * (heightSpace + 30) + heightSpace, 120, 30);
          curButton.setText(curButton.getMatch().getID());
          if (curButton.getMatch().getWinner() != null)
            curButton.setBackground(Constants.green); 
          add(curButton);
          nMatches--;
          nSingleLeftChildren++;
        } 
        if (nDisplayedInColumn % 2 == 0)
          nRightChildren--; 
      } 
    } 
    repaint();
    setVisible(true);
  }
  
  public void addButton(Match match) {
    Button newButton = new Button(match, this, this.editor);
    newButton.setBackground(Constants.salmon);
    newButton.setForeground(Constants.lightPeach);
    newButton.setFont(Constants.smallBoldFont);
    newButton.addActionListener(this);
    this.buttons.add(newButton);
  }
  
  public LinkedList getSortedTeams() {
    LinkedList tempList = this.tournament.getTeams().getTeamsInList();
    if (tempList != null) {
      Team[] teamsArr = sortTeamsByRank(tempList.getTeamDataInArray());
      tempList.clear();
      for (int i = 0; i < teamsArr.length; i++)
        tempList.add(teamsArr[i]); 
    } 
    return tempList;
  }
  
  public Team[] sortTeamsByRank(Team[] teamsArr) {
    int nTeams = teamsArr.length;
    for (int i = 1; i < nTeams; i++) {
      for (int k = i; k > 0 && 
        teamsArr[k].getAvgRank() < teamsArr[k - 1].getAvgRank(); k--) {
        Team tempTeam = teamsArr[k];
        teamsArr[k] = teamsArr[k - 1];
        teamsArr[k - 1] = tempTeam;
      } 
    } 
    Team[] alternatingTeamsArr = new Team[nTeams];
    for (int j = 0; j < nTeams / 2; j++) {
      alternatingTeamsArr[2 * j] = teamsArr[j];
      alternatingTeamsArr[2 * j + 1] = teamsArr[nTeams / 2 + j];
    } 
    if (nTeams % 2 != 0)
      alternatingTeamsArr[nTeams - 1] = teamsArr[nTeams - 1]; 
    return alternatingTeamsArr;
  }
  
  public void saveData() {
    try {
      FileWriter fileWriter = new FileWriter(this.file, false);
      BufferedWriter writer = new BufferedWriter(fileWriter);
      writer.write(this.tournament.getName());
      writer.newLine();
      writer.write("" + this.tournament.getNTeams());
      writer.newLine();
      writer.write("started");
      traverseSave(this.root, writer);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void traverseSave(Match curMatch, BufferedWriter writer) {
    if (curMatch == null)
      return; 
    traverseSave(curMatch.getLeft(), writer);
    traverseSave(curMatch.getRight(), writer);
    try {
      writer.newLine();
      Team winner = curMatch.getWinner();
      if (winner != null) {
        if (winner == curMatch.getLeftTeam()) {
          writer.write("left");
        } else if (winner == curMatch.getRightTeam()) {
          writer.write("right");
        } 
      } else {
        writer.write("none");
      } 
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void readData() {
    try {
      FileReader fileReader = new FileReader(this.file);
      BufferedReader reader = new BufferedReader(fileReader);
      reader.readLine();
      reader.readLine();
      reader.readLine();
      traverseRead(this.root, reader);
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void traverseRead(Match curMatch, BufferedReader reader) {
    if (curMatch == null)
      return; 
    traverseRead(curMatch.getLeft(), reader);
    traverseRead(curMatch.getRight(), reader);
    curMatch.update();
    try {
      String readLine = reader.readLine();
      if (readLine.equals("left")) {
        curMatch.setWinner(curMatch.getLeftTeam());
      } else if (readLine.equals("right")) {
        curMatch.setWinner(curMatch.getRightTeam());
      } 
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == this.startButton) {
      this.startButton.setBackground(Constants.red);
      this.startButton.setText("Started");
      this.startButton.setEnabled(false);
      this.tournament.start();
    } 
    for (int i = 0; i < this.nMatches; i++) {
      if (event.getSource() == this.buttons.getButton(i))
        this.buttons.getButton(i).openMatchEditor(); 
    } 
  }
}
