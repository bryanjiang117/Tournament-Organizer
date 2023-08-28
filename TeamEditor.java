import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TeamEditor implements ActionListener {
  private JFrame frame;
  
  private Container container;
  
  private Editor editor;
  
  private Tournament tournament;
  
  private Team team;
  
  private JTextField nameInputField;
  
  private Button[] playerButtons;
  
  private Player[] players;
  
  private JButton saveButton;
  
  private JButton cancelButton;
  
  private JLabel title;
  
  private JLabel nameLabel;
  
  public TeamEditor(Team team, Editor editor) {
    this.team = team;
    this.editor = editor;
    this.tournament = editor.getTournament();
    this.playerButtons = new Button[5];
    this.players = new Player[5];
    this.frame = new JFrame();
    this.frame.setAlwaysOnTop(true);
    this.frame.setBounds(250, 150, 500, 300);
    this.frame.setDefaultCloseOperation(3);
    this.frame.setUndecorated(true);
    this.frame.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Constants.salmon));
    this.frame.setBackground(Constants.yellow);
    this.container = this.frame.getContentPane();
    this.container.setLayout((LayoutManager)null);
    this.container.setBackground(Constants.yellow);
    this.players = team.getPlayers();
    for (int i = 0; i < 5; i++) {
      Button tempButton = new Button(this.players[i], team, editor, this);
      if (this.players[i].getName().equals("")) {
        tempButton.setText("Player " + (i + 1));
      } else {
        tempButton.setText(this.players[i].getName());
      } 
      tempButton.setBounds(50, 100 + i * 25, 150, 15);
      tempButton.setBackground(Constants.lightPeach);
      tempButton.setForeground(Constants.salmon);
      tempButton.setFont(Constants.verySmallFont);
      tempButton.addActionListener(this);
      this.container.add(tempButton);
      this.playerButtons[i] = tempButton;
    } 
    this.title = new JLabel("Team editor");
    this.title.setBounds(50, 10, 400, 30);
    this.title.setForeground(Constants.salmon);
    this.title.setHorizontalAlignment(0);
    this.title.setFont(Constants.largeFont);
    this.nameLabel = new JLabel("Name: (no spaces)");
    this.nameLabel.setBounds(50, 45, 400, 20);
    this.nameLabel.setForeground(Constants.salmon);
    this.nameLabel.setFont(Constants.smallFont);
    this.nameInputField = new JTextField();
    this.nameInputField.setText(team.getName());
    this.nameInputField.setBounds(50, 70, 400, 20);
    this.nameInputField.setBackground(Constants.lightPeach);
    this.nameInputField.setForeground(Constants.salmon);
    this.nameInputField.addActionListener(this);
    this.saveButton = new JButton("Save");
    this.saveButton.setBounds(110, 250, 120, 20);
    this.saveButton.setBackground(Constants.lightPeach);
    this.saveButton.setForeground(Constants.salmon);
    this.saveButton.setFont(Constants.smallFont);
    this.saveButton.addActionListener(this);
    this.cancelButton = new JButton("Cancel");
    this.cancelButton.setBounds(270, 250, 120, 20);
    this.cancelButton.setBackground(Constants.lightPeach);
    this.cancelButton.setForeground(Constants.salmon);
    this.cancelButton.setFont(Constants.smallFont);
    this.cancelButton.addActionListener(this);
    this.container.add(this.title);
    this.container.add(this.nameLabel);
    this.container.add(this.nameInputField);
    this.container.add(this.saveButton);
    this.container.add(this.cancelButton);
  }
  
  public void open() {
    this.editor.disableFrame();
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
    if (this.tournament.hasStarted())
      this.nameInputField.setEnabled(false); 
    for (int i = 0; i < 5; i++) {
      if (this.players[i].getName().equals("")) {
        this.playerButtons[i].setText("Player " + (i + 1));
      } else {
        this.playerButtons[i].setText(this.players[i].getName());
      } 
    } 
    this.frame.repaint();
  }
  
  public void saveData() {
    this.team.setName(this.nameInputField.getText().replaceAll("\\s+", ""));
    this.team.saveData();
  }
  
  public void close() {
    this.editor.enableFrame();
    this.editor.update();
    this.frame.dispose();
  }
  
  public Team getTeam() {
    return this.team;
  }
  
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == this.saveButton) {
      saveData();
      close();
    } else if (event.getSource() == this.cancelButton) {
      close();
    } else {
      for (int i = 0; i < 5; i++) {
        if (event.getSource() == this.playerButtons[i])
          this.playerButtons[i].openPlayerEditor(); 
      } 
    } 
  }
}
