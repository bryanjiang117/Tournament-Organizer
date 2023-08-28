import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MatchEditor implements ActionListener {
  private JFrame frame;
  
  private Container container;
  
  private JButton saveButton;
  
  private JButton cancelButton;
  
  private JButton leftTeamButton;
  
  private JButton rightTeamButton;
  
  private JLabel title;
  
  private Match match;
  
  private Bracket bracket;
  
  private Editor editor;
  
  private Tournament tournament;
  
  private Team winner;
  
  public MatchEditor(Match match, Bracket bracket, Editor editor) {
    this.match = match;
    this.bracket = bracket;
    this.editor = editor;
    this.tournament = editor.getTournament();
    this.frame = new JFrame();
    this.frame.setAlwaysOnTop(true);
    this.frame.setBounds(300, 225, 350, 150);
    this.frame.setDefaultCloseOperation(3);
    this.frame.setUndecorated(true);
    this.frame.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Constants.salmon));
    this.frame.setBackground(Constants.yellow);
    this.container = this.frame.getContentPane();
    this.container.setLayout((LayoutManager)null);
    this.container.setBackground(Constants.yellow);
    this.title = new JLabel();
    this.title.setBounds(25, 10, 300, 40);
    this.title.setForeground(Constants.salmon);
    this.title.setHorizontalAlignment(0);
    this.title.setFont(Constants.largeFont);
    this.leftTeamButton = new JButton();
    this.leftTeamButton.setBounds(25, 55, 125, 50);
    this.leftTeamButton.setBackground(Constants.lightYellow);
    this.leftTeamButton.setForeground(Constants.lightPeach);
    this.leftTeamButton.setFont(Constants.verySmallFont);
    this.leftTeamButton.addActionListener(this);
    this.rightTeamButton = new JButton();
    this.rightTeamButton.setBounds(200, 55, 125, 50);
    this.rightTeamButton.setBackground(Constants.lightYellow);
    this.rightTeamButton.setForeground(Constants.lightPeach);
    this.rightTeamButton.setFont(Constants.verySmallFont);
    this.rightTeamButton.addActionListener(this);
    this.saveButton = new JButton("Save");
    this.saveButton.setBounds(50, 120, 100, 20);
    this.saveButton.setBackground(Constants.lightPeach);
    this.saveButton.setForeground(Constants.salmon);
    this.saveButton.setFont(Constants.smallFont);
    this.saveButton.addActionListener(this);
    this.cancelButton = new JButton("Cancel");
    this.cancelButton.setBounds(200, 120, 100, 20);
    this.cancelButton.setBackground(Constants.lightPeach);
    this.cancelButton.setForeground(Constants.salmon);
    this.cancelButton.setFont(Constants.smallFont);
    this.cancelButton.addActionListener(this);
    this.container.add(this.title);
    this.container.add(this.leftTeamButton);
    this.container.add(this.rightTeamButton);
    this.container.add(this.saveButton);
    this.container.add(this.cancelButton);
  }
  
  public void open() {
    this.editor.disableFrame();
    update();
    this.frame.setVisible(true);
  }
  
  public void close() {
    this.editor.enableFrame();
    this.frame.dispose();
  }
  
  public void update() {
    this.title.setText(this.match.getID());
    this.winner = this.match.getWinner();
    this.frame.repaint();
    if (this.match.getLeftTeam() == null) {
      this.leftTeamButton.setText("Winner of " + this.match.getLeft().getID());
    } else {
      this.leftTeamButton.setText(this.match.getLeftTeam().getName());
    } 
    if (this.match.getRightTeam() == null) {
      this.rightTeamButton.setText("Winner of " + this.match.getRight().getID());
    } else {
      this.rightTeamButton.setText(this.match.getRightTeam().getName());
    } 
    Team winner = this.match.getWinner();
    if (winner != null) {
      this.leftTeamButton.setEnabled(false);
      this.rightTeamButton.setEnabled(false);
      if (this.match.getLeftTeam() == winner) {
        this.leftTeamButton.setBackground(Constants.green);
        this.rightTeamButton.setBackground(Constants.red);
      } else if (this.match.getRightTeam() == winner) {
        this.rightTeamButton.setBackground(Constants.green);
        this.leftTeamButton.setBackground(Constants.red);
      } 
    } 
  }
  
  public void saveData() {
    this.match.setWinner(this.winner);
    this.bracket.saveData();
  }
  
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == this.saveButton) {
      if (this.tournament.hasStarted()) {
        saveData();
        this.bracket.update();
      } 
      close();
    } else if (event.getSource() == this.cancelButton) {
      close();
    } else if (event.getSource() == this.leftTeamButton) {
      if (this.tournament.hasStarted()) {
        if (this.match.getLeftTeam() != null && this.match.getRightTeam() != null) {
          this.winner = this.match.getLeftTeam();
          this.leftTeamButton.setBackground(Constants.green);
          this.rightTeamButton.setBackground(Constants.red);
        } else {
          JOptionPane.showMessageDialog(this.container, "Please indicate the results of the previous matches before this one");
        } 
      } else {
        JOptionPane.showMessageDialog(this.container, "The tournament has not started yet");
      } 
    } else if (event.getSource() == this.rightTeamButton) {
      if (this.tournament.hasStarted()) {
        if (this.match.getLeftTeam() != null && this.match.getRightTeam() != null) {
          this.winner = this.match.getRightTeam();
          this.rightTeamButton.setBackground(Constants.green);
          this.leftTeamButton.setBackground(Constants.red);
        } else {
          JOptionPane.showMessageDialog(this.container, "Please indicate the results of the previous matches before this one");
        } 
      } else {
        JOptionPane.showMessageDialog(this.container, "The tournament has not started yet");
      } 
    } 
  }
}
