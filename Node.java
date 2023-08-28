public class Node {
    private Node next;
    
    private Button buttonData;
    
    private Team teamData;
    
    private Player playerData;
    
    private Match matchData;
    
    private String stringData;
    
    public Node(Button data, Node next) {
      this.buttonData = data;
      this.next = next;
    }
    
    public Node(Team data, Node next) {
      this.teamData = data;
      this.next = next;
    }
    
    public Node(Player data, Node next) {
      this.playerData = data;
      this.next = next;
    }
    
    public Node(Match data, Node next) {
      this.matchData = data;
      this.next = next;
    }
    
    public Node(String data, Node next) {
      this.stringData = data;
      this.next = next;
    }
    
    public void setData(Button data) {
      this.buttonData = data;
    }
    
    public void setData(Team data) {
      this.teamData = data;
    }
    
    public void setData(Player data) {
      this.playerData = data;
    }
    
    public void setData(Match data) {
      this.matchData = data;
    }
    
    public void setData(String data) {
      this.stringData = data;
    }
    
    public Button getButtonData() {
      return this.buttonData;
    }
    
    public Team getTeamData() {
      return this.teamData;
    }
    
    public Player getPlayerData() {
      return this.playerData;
    }
    
    public Match getMatchData() {
      return this.matchData;
    }
    
    public String getStringData() {
      return this.stringData;
    }
    
    public void setNext(Node next) {
      this.next = next;
    }
    
    public Node getNext() {
      return this.next;
    }
  }
  