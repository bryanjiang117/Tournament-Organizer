public class Match {
    private Match left;
    
    private Match right;
    
    private Team leftTeam;
    
    private Team rightTeam;
    
    private Team winner;
    
    private String ID;
    
    public void update() {
      if (this.left != null)
        this.leftTeam = this.left.getWinner(); 
      if (this.right != null)
        this.rightTeam = this.right.getWinner(); 
    }
    
    public Match getLeft() {
      return this.left;
    }
    
    public Match getRight() {
      return this.right;
    }
    
    public Team getLeftTeam() {
      return this.leftTeam;
    }
    
    public Team getRightTeam() {
      return this.rightTeam;
    }
    
    public String getID() {
      return this.ID;
    }
    
    public Team getWinner() {
      return this.winner;
    }
    
    public void setLeft(Match match) {
      this.left = match;
    }
    
    public void setRight(Match match) {
      this.right = match;
    }
    
    public void setLeftTeam(Team team) {
      this.leftTeam = team;
    }
    
    public void setRightTeam(Team team) {
      this.rightTeam = team;
    }
    
    public void setID(String ID) {
      this.ID = ID;
    }
    
    public void setWinner(Team team) {
      this.winner = team;
    }
  }
  