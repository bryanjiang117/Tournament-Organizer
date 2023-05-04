import java.awt.event.ActionEvent;

public class TeamPlayerEditor extends PlayerEditor {
  private TeamEditor teamEditor;
  
  public TeamPlayerEditor(Player player, Team team, Editor editor, TeamEditor teamEditor) {
    super(player, team, editor);
    this.teamEditor = teamEditor;
  }
  
  public void open() {
    this.teamEditor.disableFrame();
    update();
    this.frame.setVisible(true);
  }
  
  public void close() {
    this.teamEditor.enableFrame();
    this.frame.dispose();
  }
  
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == this.saveButton) {
      saveData();
      this.teamEditor.update();
      close();
    } else if (event.getSource() == this.cancelButton) {
      close();
    } 
  }
}
