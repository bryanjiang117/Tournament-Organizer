import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PlayerEditor implements ActionListener {
  protected JFrame frame;
  
  protected Container container;
  
  protected Player player;
  
  protected Team team;
  
  protected Editor editor;
  
  protected Tournament tournament;
  
  protected JTextField nameInputField;
  
  protected JTextField gameUserInputField;
  
  protected JTextField discordUserInputField;
  
  protected JButton saveButton;
  
  protected JButton cancelButton;
  
  protected JLabel title;
  
  protected JLabel nameLabel;
  
  protected JLabel gameUserLabel;
  
  protected JLabel discordUserLabel;
  
  protected JLabel rankLabel;
  
  protected JLabel ifPaidLabel;
  
  protected JComboBox<String> rankDropdown;
  
  protected JComboBox<String> hasPaidDropdown;
  
  protected final String[] rankDropdownOptions = new String[] { "Iron", "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Masters/Immortal" };
  
  protected final String[] hasPaidDropdownOptions = new String[] { "No", "Yes" };
  
  public PlayerEditor(Player player, Team team, Editor editor) {
    this.player = player;
    this.team = team;
    this.editor = editor;
    this.tournament = editor.getTournament();
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
    this.title = new JLabel("Player editor");
    this.title.setBounds(50, 10, 400, 30);
    this.title.setForeground(Constants.salmon);
    this.title.setHorizontalAlignment(0);
    this.title.setFont(Constants.largeFont);
    this.nameLabel = new JLabel("Name:");
    this.nameLabel.setBounds(50, 45, 400, 20);
    this.nameLabel.setForeground(Constants.salmon);
    this.nameInputField = new JTextField();
    this.nameInputField.setText(player.getName());
    this.nameInputField.setBounds(50, 70, 400, 20);
    this.nameInputField.setBackground(Constants.lightPeach);
    this.nameInputField.setForeground(Constants.salmon);
    this.nameInputField.addActionListener(this);
    this.gameUserLabel = new JLabel("In-Game Username");
    this.gameUserLabel.setBounds(50, 95, 400, 20);
    this.gameUserLabel.setForeground(Constants.salmon);
    this.gameUserInputField = new JTextField();
    this.gameUserInputField.setText(player.getGameUser());
    this.gameUserInputField.setBounds(50, 120, 400, 20);
    this.gameUserInputField.setBackground(Constants.lightPeach);
    this.gameUserInputField.setForeground(Constants.salmon);
    this.gameUserInputField.addActionListener(this);
    this.discordUserLabel = new JLabel("Discord Username (Ex: chadMinecrafter#0102):");
    this.discordUserLabel.setBounds(50, 145, 400, 20);
    this.discordUserLabel.setForeground(Constants.salmon);
    this.discordUserInputField = new JTextField();
    this.discordUserInputField.setText(player.getDiscordUser());
    this.discordUserInputField.setBounds(50, 170, 400, 20);
    this.discordUserInputField.setBackground(Constants.lightPeach);
    this.discordUserInputField.setForeground(Constants.salmon);
    this.discordUserInputField.addActionListener(this);
    this.rankLabel = new JLabel("In-Game Rank:");
    this.rankLabel.setBounds(120, 195, 100, 20);
    this.rankLabel.setForeground(Constants.salmon);
    this.rankDropdown = new JComboBox<>(this.rankDropdownOptions);
    this.rankDropdown.setSelectedIndex(player.getRank() - 1);
    this.rankDropdown.setBounds(120, 220, 100, 20);
    this.rankDropdown.setBackground(Constants.lightPeach);
    this.rankDropdown.setForeground(Constants.salmon);
    this.rankDropdown.addActionListener(this);
    this.ifPaidLabel = new JLabel("Paid:");
    this.ifPaidLabel.setBounds(280, 195, 100, 20);
    this.ifPaidLabel.setForeground(Constants.salmon);
    this.hasPaidDropdown = new JComboBox<>(this.hasPaidDropdownOptions);
    if (player.getHasPaid()) {
      this.hasPaidDropdown.setSelectedItem("Yes");
    } else {
      this.hasPaidDropdown.setSelectedItem("No");
    } 
    this.hasPaidDropdown.setBounds(280, 220, 100, 20);
    this.hasPaidDropdown.setBackground(Constants.lightPeach);
    this.hasPaidDropdown.setForeground(Constants.salmon);
    this.hasPaidDropdown.addActionListener(this);
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
    this.container.add(this.gameUserLabel);
    this.container.add(this.gameUserInputField);
    this.container.add(this.discordUserLabel);
    this.container.add(this.discordUserInputField);
    this.container.add(this.rankLabel);
    this.container.add(this.rankDropdown);
    this.container.add(this.ifPaidLabel);
    this.container.add(this.hasPaidDropdown);
    this.container.add(this.saveButton);
    this.container.add(this.cancelButton);
  }
  
  public void open() {
    this.editor.disableFrame();
    update();
    this.frame.setVisible(true);
  }
  
  public void close() {
    this.frame.dispose();
    this.editor.update();
    this.editor.enableFrame();
  }
  
  public void update() {
    if (this.tournament.hasStarted()) {
      this.nameInputField.setEnabled(false);
      this.gameUserInputField.setEnabled(false);
      this.discordUserInputField.setEnabled(false);
      this.rankDropdown.setEnabled(false);
      this.hasPaidDropdown.setEnabled(false);
    } 
    this.nameInputField.setText(this.player.getName());
    this.gameUserInputField.setText(this.player.getGameUser());
    this.discordUserInputField.setText(this.player.getDiscordUser());
    this.rankDropdown.setSelectedIndex(this.player.getRank() - 1);
    if (this.player.getHasPaid()) {
      this.hasPaidDropdown.setSelectedItem("Yes");
    } else {
      this.hasPaidDropdown.setSelectedItem("No");
    } 
    this.frame.repaint();
  }
  
  public void saveData() {
    this.player.setName(this.nameInputField.getText());
    this.player.setGameUser(this.gameUserInputField.getText());
    this.player.setDiscordUser(this.discordUserInputField.getText());
    this.player.setHasPaid(this.hasPaidDropdown.getSelectedItem().equals("Yes"));
    this.player.setRank(this.rankDropdown.getSelectedIndex() + 1);
    this.team.saveData();
  }
  
  public Player getPlayer() {
    return this.player;
  }
  
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == this.saveButton) {
      saveData();
      close();
    } else if (event.getSource() == this.cancelButton) {
      close();
    } 
  }
}
