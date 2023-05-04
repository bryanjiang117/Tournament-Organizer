public class TreeNode {
    private TreeNode left;
    
    private TreeNode right;
    
    private Team teamData;
    
    private Player playerData;
    
    public TreeNode(Team data) {
      this.left = null;
      this.right = null;
      this.teamData = data;
    }
    
    public TreeNode(Player data) {
      this.left = null;
      this.right = null;
      this.playerData = data;
    }
    
    public void setData(Team data) {
      this.teamData = data;
    }
    
    public void setData(Player data) {
      this.playerData = data;
    }
    
    public void setRight(TreeNode right) {
      this.right = right;
    }
    
    public void setLeft(TreeNode left) {
      this.left = left;
    }
    
    public Team getTeamData() {
      return this.teamData;
    }
    
    public Player getPlayerData() {
      return this.playerData;
    }
    
    public TreeNode getLeft() {
      return this.left;
    }
    
    public TreeNode getRight() {
      return this.right;
    }
  }
  