public class Tournament {
    private String name;
    
    private LinkedList matches;
    
    private BST teams;
    
    private BST players;
    
    private boolean started;
    
    public Tournament() {
      this.name = "";
      this.teams = new BST();
      this.players = new BST();
      this.matches = new LinkedList();
    }
    
    public Tournament(String name) {
      if (name == null)
        name = ""; 
      this.name = name;
      this.teams = new BST();
      this.players = new BST();
      this.matches = new LinkedList();
    }
    
    public void start() {
      this.started = true;
    }
    
    public boolean hasStarted() {
      return this.started;
    }
    
    public void add(Player player) {
      this.players.add(player);
    }
    
    public void remove(Player player) {
      this.players.remove(player);
    }
    
    public void add(Team team) {
      this.teams.add(team);
    }
    
    public void remove(Team team) {
      this.teams.remove(team);
    }
    
    public void add(Match match) {
      this.matches.add(match);
    }
    
    public void remove(Match match) {
      this.matches.remove(match);
    }
    
    public boolean has(Player player) {
      return this.players.has(player);
    }
    
    public boolean has(Team team) {
      return this.teams.has(team);
    }
    
    public boolean hasPlayerName(String name) {
      return this.players.hasPlayerName(name);
    }
    
    public boolean hasTeamName(String name) {
      return this.teams.hasTeamName(name);
    }
    
    public LinkedList searchTeams(String keyword) {
      LinkedList containingMatches = this.teams.searchTeamsContainingKeyword(keyword);
      Team exactMatch = this.teams.searchTeams(keyword);
      containingMatches.addAtHead(exactMatch);
      return containingMatches;
    }
    
    public LinkedList searchPlayers(String keyword) {
      LinkedList containingMatches = this.players.searchPlayersContainingKeyword(keyword);
      Player exactMatch = this.players.searchPlayers(keyword);
      containingMatches.addAtHead(exactMatch);
      return containingMatches;
    }
    
    public String getName() {
      return this.name;
    }
    
    public int getNPlayers() {
      return this.players.getSize();
    }
    
    public int getNTeams() {
      return this.teams.getSize();
    }
    
    public BST getPlayers() {
      return this.players;
    }
    
    public BST getTeams() {
      return this.teams;
    }
    
    public LinkedList getMatches() {
      return this.matches;
    }
    
    public void setName(String name) {
      this.name = name;
    }
  }
  