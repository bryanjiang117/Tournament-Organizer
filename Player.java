public class Player {
    private String name;
    
    private String gameUser;
    
    private String discordUser;
    
    private int rank;
    
    private boolean hasPaid;
    
    private Team team;
    
    private static int ID;
    
    public Player(Team team) {
      ID++;
      this.name = "Player " + ID;
      this.gameUser = "";
      this.discordUser = "";
      this.rank = 0;
      this.hasPaid = false;
      this.team = team;
    }
    
    public int compareTo(Player other) {
      return this.name.compareTo(other.getName());
    }
    
    public String getName() {
      return this.name;
    }
    
    public String getGameUser() {
      return this.gameUser;
    }
    
    public String getDiscordUser() {
      return this.discordUser;
    }
    
    public int getRank() {
      return this.rank;
    }
    
    public boolean getHasPaid() {
      return this.hasPaid;
    }
    
    public void setName(String name) {
      Tournament tempTournament = this.team.getEditor().getTournament();
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
      Tournament tempTournament = this.team.getEditor().getTournament();
      if (tempTournament.hasPlayerName(name)) {
        int count = 1;
        String newName = String.valueOf(name) + "(" + count + ")";
        while (tempTournament.hasPlayerName(newName)) {
          count++;
          newName = String.valueOf(name) + "(" + count + ")";
        } 
        return newName;
      } 
      return name;
    }
    
    public void setGameUser(String gameUser) {
      this.gameUser = gameUser;
    }
    
    public void setDiscordUser(String discordUser) {
      this.discordUser = discordUser;
    }
    
    public void setRank(int rank) {
      this.rank = rank;
      this.team.updatePlayers();
    }
    
    public void setHasPaid(boolean hasPaid) {
      this.hasPaid = hasPaid;
      this.team.updatePlayers();
    }
  }
  