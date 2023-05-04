public class BST {
  private TreeNode root = null;
  
  private int size = 0;
  
  public void add(Player data) {
    if (this.size == 0) {
      this.root = new TreeNode(data);
      this.size++;
      return;
    } 
    TreeNode cur = this.root;
    while (true) {
      while (data.compareTo(cur.getPlayerData()) > 0) {
        if (cur.getRight() == null) {
          cur.setRight(new TreeNode(data));
          this.size++;
          return;
        } 
        cur = cur.getRight();
      } 
      if (data.compareTo(cur.getPlayerData()) < 0) {
        if (cur.getLeft() == null) {
          cur.setLeft(new TreeNode(data));
          this.size++;
          return;
        } 
        cur = cur.getLeft();
      } 
    } 
  }
  
  public void add(Team data) {
    if (this.size == 0) {
      this.root = new TreeNode(data);
      this.size++;
      return;
    } 
    TreeNode cur = this.root;
    while (true) {
      while (data.compareTo(cur.getTeamData()) > 0) {
        if (cur.getRight() == null) {
          cur.setRight(new TreeNode(data));
          this.size++;
          return;
        } 
        cur = cur.getRight();
      } 
      if (data.compareTo(cur.getTeamData()) < 0) {
        if (cur.getLeft() == null) {
          cur.setLeft(new TreeNode(data));
          this.size++;
          return;
        } 
        cur = cur.getLeft();
      } 
    } 
  }
  
  public void remove(Player data) {
    if (this.root == null)
      return; 
    this.size--;
    TreeNode cur = this.root, prev = null;
    while (cur != null) {
      if (data.compareTo(cur.getPlayerData()) > 0) {
        prev = cur;
        cur = cur.getRight();
        continue;
      } 
      if (data.compareTo(cur.getPlayerData()) < 0) {
        prev = cur;
        cur = cur.getLeft();
        continue;
      } 
      if (prev == null) {
        if (cur.getLeft() != null && cur.getRight() != null) {
          this.root = cur.getRight();
          TreeNode leftBranch = cur.getLeft();
          prev = this.root;
          cur = prev.getLeft();
          while (cur != null) {
            prev = cur;
            cur = cur.getLeft();
          } 
          prev.setLeft(leftBranch);
        } else if (cur.getLeft() != null) {
          this.root = cur.getLeft();
        } else if (cur.getRight() != null) {
          this.root = cur.getRight();
        } else {
          this.root = null;
        } 
      } else if (cur.getLeft() != null && cur.getRight() != null) {
        if (cur.getPlayerData().compareTo(prev.getPlayerData()) > 0) {
          prev.setRight(cur.getLeft());
        } else {
          prev.setLeft(cur.getLeft());
        } 
        TreeNode rightBranch = cur.getRight();
        prev = cur.getLeft();
        cur = prev.getRight();
        while (cur != null) {
          prev = cur;
          cur = cur.getRight();
        } 
        prev.setRight(rightBranch);
      } else if (cur.getLeft() != null) {
        if (cur.getPlayerData().compareTo(prev.getPlayerData()) > 0) {
          prev.setRight(cur.getLeft());
        } else {
          prev.setLeft(cur.getLeft());
        } 
      } else if (cur.getRight() != null) {
        if (cur.getPlayerData().compareTo(prev.getPlayerData()) > 0) {
          prev.setRight(cur.getRight());
        } else {
          prev.setLeft(cur.getRight());
        } 
      } else if (cur.getPlayerData().compareTo(prev.getPlayerData()) > 0) {
        prev.setRight(null);
      } else {
        prev.setLeft(null);
      } 
      return;
    } 
  }
  
  public void remove(Team data) {
    if (this.root == null)
      return; 
    this.size--;
    TreeNode cur = this.root, prev = null;
    while (cur != null) {
      if (data.compareTo(cur.getTeamData()) > 0) {
        prev = cur;
        cur = cur.getRight();
        continue;
      } 
      if (data.compareTo(cur.getTeamData()) < 0) {
        prev = cur;
        cur = cur.getLeft();
        continue;
      } 
      if (prev == null) {
        if (cur.getLeft() != null && cur.getRight() != null) {
          this.root = cur.getRight();
          TreeNode leftBranch = cur.getLeft();
          prev = this.root;
          cur = prev.getLeft();
          while (cur != null) {
            prev = cur;
            cur = cur.getLeft();
          } 
          prev.setLeft(leftBranch);
        } else if (cur.getLeft() != null) {
          this.root = cur.getLeft();
        } else if (cur.getRight() != null) {
          this.root = cur.getRight();
        } else {
          this.root = null;
        } 
      } else if (cur.getLeft() != null && cur.getRight() != null) {
        if (cur.getTeamData().compareTo(prev.getTeamData()) > 0) {
          prev.setRight(cur.getLeft());
        } else {
          prev.setLeft(cur.getLeft());
        } 
        TreeNode rightBranch = cur.getRight();
        prev = cur.getLeft();
        cur = prev.getRight();
        while (cur != null) {
          prev = cur;
          cur = cur.getRight();
        } 
        prev.setRight(rightBranch);
      } else if (cur.getLeft() != null) {
        if (cur.getTeamData().compareTo(prev.getTeamData()) > 0) {
          prev.setRight(cur.getLeft());
        } else {
          prev.setLeft(cur.getLeft());
        } 
      } else if (cur.getRight() != null) {
        if (cur.getTeamData().compareTo(prev.getTeamData()) > 0) {
          prev.setRight(cur.getRight());
        } else {
          prev.setLeft(cur.getRight());
        } 
      } else if (cur.getTeamData().compareTo(prev.getTeamData()) > 0) {
        prev.setRight(null);
      } else {
        prev.setLeft(null);
      } 
      return;
    } 
  }
  
  public boolean has(Player data) {
    if (this.root == null)
      return false; 
    TreeNode cur = this.root;
    while (cur != null) {
      if (data.compareTo(cur.getPlayerData()) > 0) {
        cur = cur.getRight();
        continue;
      } 
      if (data.compareTo(cur.getPlayerData()) < 0) {
        cur = cur.getLeft();
        continue;
      } 
      return true;
    } 
    return false;
  }
  
  public boolean has(Team data) {
    if (this.root == null)
      return false; 
    TreeNode cur = this.root;
    while (cur != null) {
      if (data.compareTo(cur.getTeamData()) > 0) {
        cur = cur.getRight();
        continue;
      } 
      if (data.compareTo(cur.getTeamData()) < 0) {
        cur = cur.getLeft();
        continue;
      } 
      return true;
    } 
    return false;
  }
  
  public boolean hasPlayerName(String name) {
    return (searchPlayers(name) != null);
  }
  
  public boolean hasTeamName(String name) {
    return (searchTeams(name) != null);
  }
  
  public Player searchPlayers(String keyword) {
    if (this.root == null)
      return null; 
    TreeNode cur = this.root;
    while (cur != null) {
      String curName = cur.getPlayerData().getName();
      if (keyword.compareTo(curName) > 0) {
        cur = cur.getRight();
        continue;
      } 
      if (keyword.compareTo(curName) < 0) {
        cur = cur.getLeft();
        continue;
      } 
      return cur.getPlayerData();
    } 
    return null;
  }
  
  public Team searchTeams(String keyword) {
    if (this.root == null)
      return null; 
    TreeNode cur = this.root;
    while (cur != null) {
      String curName = cur.getTeamData().getName();
      if (keyword.compareTo(curName) > 0) {
        cur = cur.getRight();
        continue;
      } 
      if (keyword.compareTo(curName) < 0) {
        cur = cur.getLeft();
        continue;
      } 
      return cur.getTeamData();
    } 
    return null;
  }
  
  public LinkedList searchPlayersContainingKeyword(String keyword) {
    if (this.root == null)
      return new LinkedList(); 
    LinkedList returnData = new LinkedList();
    traverseSearchPlayers(this.root, returnData, keyword);
    return returnData;
  }
  
  public void traverseSearchPlayers(TreeNode node, LinkedList returnData, String keyword) {
    if (node == null)
      return; 
    traverseSearchPlayers(node.getLeft(), returnData, keyword);
    String curName = node.getPlayerData().getName();
    if (curName.indexOf(keyword) != -1 && !curName.equals(keyword))
      returnData.add(node.getPlayerData()); 
    traverseSearchPlayers(node.getRight(), returnData, keyword);
  }
  
  public LinkedList searchTeamsContainingKeyword(String keyword) {
    if (this.root == null);
    LinkedList returnData = new LinkedList();
    traverseSearchTeams(this.root, returnData, keyword);
    return returnData;
  }
  
  public void traverseSearchTeams(TreeNode node, LinkedList returnData, String keyword) {
    if (node == null)
      return; 
    traverseSearchTeams(node.getLeft(), returnData, keyword);
    String curName = node.getTeamData().getName();
    if (curName.indexOf(keyword) != -1 && !curName.equals(keyword))
      returnData.add(node.getTeamData()); 
    traverseSearchTeams(node.getRight(), returnData, keyword);
  }
  
  public LinkedList getTeamsInList() {
    if (this.root == null)
      return null; 
    LinkedList returnData = new LinkedList();
    traverseGetTeamsInList(this.root, returnData);
    return returnData;
  }
  
  public void traverseGetTeamsInList(TreeNode node, LinkedList returnData) {
    if (node == null)
      return; 
    traverseGetTeamsInList(node.getLeft(), returnData);
    returnData.add(node.getTeamData());
    traverseGetTeamsInList(node.getRight(), returnData);
  }
  
  public TreeNode getRoot() {
    return this.root;
  }
  
  public void setRoot(TreeNode r) {
    this.root = r;
  }
  
  public int getSize() {
    return this.size;
  }
}
