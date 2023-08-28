import javax.swing.JButton;

class Button extends JButton {
  private Tournament tournament;
  
  private Player player;
  
  private Team team;
  
  private Match match;
  
  private Editor editor;
  
  private PlayerEditor playerEditor;
  
  private TeamEditor teamEditor;
  
  private MatchEditor matchEditor;
  
  public Button(Match match, Bracket bracket, Editor editor) {
    this.match = match;
    this.matchEditor = new MatchEditor(match, bracket, editor);
  }
  
  public Button(Tournament tournament, Menu menu, int ID) {
    this.tournament = tournament;
    this.editor = new Editor(tournament, menu, ID);
  }
  
  public Button(Team team, Editor editor) {
    this.team = team;
    this.teamEditor = new TeamEditor(team, editor);
  }
  
  public Button(Player player, Team team, Editor editor) {
    this.player = player;
    this.playerEditor = new PlayerEditor(player, team, editor);
  }
  
  public Button(Player player, Team team, Editor editor, TeamEditor teamEditor) {
    this.player = player;
    this.playerEditor = new TeamPlayerEditor(player, team, editor, teamEditor);
  }
  
  public void openPlayerEditor() {
    this.playerEditor.open();
  }
  
  public void openTournamentEditor() {
    this.editor.open();
  }
  
  public void openTeamEditor() {
    this.teamEditor.open();
  }
  
  public void openMatchEditor() {
    this.matchEditor.open();
  }
  
  public Tournament getTournament() {
    return this.tournament;
  }
  
  public Match getMatch() {
    return this.match;
  }
  
  public Player getPlayer() {
    return this.player;
  }
  
  public Team getTeam() {
    return this.team;
  }
}
