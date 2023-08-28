import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Team {
  private String name;
  
  private double avgRank;
  
  private boolean hasAllPaid;
  
  private Player[] players;
  
  private File file;
  
  private BufferedWriter writer;
  
  private BufferedReader reader;
  
  private String fileName;
  
  private Editor editor;
  
  public Team(Editor editor, int ID) {
    this.editor = editor;
    this.name = "team " + ID;
    this.avgRank = 0.0D;
    this.hasAllPaid = false;
    this.players = new Player[5];
    for (int i = 0; i < 5; i++)
      this.players[i] = new Player(this); 
    this.fileName = "team" + editor.getID() + "." + ID + ".txt";
    this.file = new File("./database/" + this.fileName);
    if (this.file.exists()) {
      try {
        FileReader fileReader = new FileReader(this.file);
        this.reader = new BufferedReader(fileReader);
        setName(this.reader.readLine());
        setHasAllPaid(this.reader.readLine().equals("has all paid"));
        setAvgRank(Double.parseDouble(this.reader.readLine()));
        for (int j = 0; j < 5; j++) {
          this.players[j].setName(this.reader.readLine());
          this.players[j].setGameUser(this.reader.readLine());
          this.players[j].setDiscordUser(this.reader.readLine());
          this.players[j].setRank(Integer.parseInt(this.reader.readLine()));
          this.players[j].setHasPaid(this.reader.readLine().equals("has paid"));
          editor.getTournament().add(this.players[j]);
        } 
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } else {
      try {
        this.file.createNewFile();
        saveData();
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public void saveData() {
    try {
      FileWriter fileWriter = new FileWriter(this.file, false);
      this.writer = new BufferedWriter(fileWriter);
      this.writer.write(this.name);
      this.writer.newLine();
      if (this.hasAllPaid) {
        this.writer.write("has all paid");
      } else {
        this.writer.write("have not all paid");
      } 
      this.writer.newLine();
      this.writer.write("" + (int)this.avgRank);
      this.writer.newLine();
      for (int i = 0; i < 5; i++) {
        this.writer.write(this.players[i].getName());
        this.writer.newLine();
        this.writer.write(this.players[i].getGameUser());
        this.writer.newLine();
        this.writer.write(this.players[i].getDiscordUser());
        this.writer.newLine();
        this.writer.write("" + this.players[i].getRank());
        this.writer.newLine();
        if (this.players[i].getHasPaid()) {
          this.writer.write("has paid");
        } else {
          this.writer.write("has not paid");
        } 
        this.writer.newLine();
        this.writer.flush();
      } 
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public int compareTo(Team other) {
    return this.name.compareTo(other.getName());
  }
  
  public String getName() {
    return this.name;
  }
  
  public boolean getHasAllPaid() {
    return this.hasAllPaid;
  }
  
  public double getAvgRank() {
    return this.avgRank;
  }
  
  public Editor getEditor() {
    return this.editor;
  }
  
  public Player[] getPlayers() {
    return this.players;
  }
  
  public void setName(String name) {
    Tournament tempTournament = this.editor.getTournament();
    if (!this.name.equals(name))
      if (tempTournament.has(this)) {
        tempTournament.remove(this);
        this.name = checkDuplicates(name);
        tempTournament.add(this);
      } else {
        this.name = checkDuplicates(name);
      }  
  }
  
  public String checkDuplicates(String name) {
    Tournament tempTournament = this.editor.getTournament();
    if (tempTournament.hasTeamName(name)) {
      int count = 1;
      String newName = String.valueOf(name) + "(" + count + ")";
      while (tempTournament.hasTeamName(newName)) {
        count++;
        newName = String.valueOf(name) + "(" + count + ")";
      } 
      return newName;
    } 
    return name;
  }
  
  public void setHasAllPaid(boolean hasAllPaid) {
    this.hasAllPaid = hasAllPaid;
  }
  
  public void setAvgRank(double avgRank) {
    this.avgRank = avgRank;
  }
  
  public void updatePlayers() {
    int sum = 0, nHaveRank = 0;
    this.hasAllPaid = true;
    for (int i = 0; i < 5; i++) {
      sum += this.players[i].getRank();
      if (this.players[i].getRank() != 0)
        nHaveRank++; 
      if (!this.players[i].getHasPaid())
        this.hasAllPaid = false; 
    } 
    if (nHaveRank > 0)
      this.avgRank = sum / nHaveRank; 
  }
}
