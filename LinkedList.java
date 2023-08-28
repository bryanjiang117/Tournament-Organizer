public class LinkedList {
    private Node head = null;
    
    private int size = 0;
    
    public void add(Button data) {
      if (this.head == null) {
        this.head = new Node(data, null);
        this.size++;
        return;
      } 
      Node cur = this.head;
      for (int i = 0; i < this.size - 1; i++)
        cur = cur.getNext(); 
      cur.setNext(new Node(data, null));
      this.size++;
    }
    
    public void add(Team data) {
      if (this.head == null) {
        this.head = new Node(data, null);
        this.size++;
        return;
      } 
      Node cur = this.head;
      for (int i = 0; i < this.size - 1; i++)
        cur = cur.getNext(); 
      cur.setNext(new Node(data, null));
      this.size++;
    }
    
    public void add(Player data) {
      if (this.head == null) {
        this.head = new Node(data, null);
        this.size++;
        return;
      } 
      Node cur = this.head;
      for (int i = 0; i < this.size - 1; i++)
        cur = cur.getNext(); 
      cur.setNext(new Node(data, null));
      this.size++;
    }
    
    public void add(Match data) {
      if (this.head == null) {
        this.head = new Node(data, null);
        this.size++;
        return;
      } 
      Node cur = this.head;
      for (int i = 0; i < this.size - 1; i++)
        cur = cur.getNext(); 
      cur.setNext(new Node(data, null));
      this.size++;
    }
    
    public void add(String data) {
      if (this.head == null) {
        this.head = new Node(data, null);
        this.size++;
        return;
      } 
      Node cur = this.head;
      for (int i = 0; i < this.size - 1; i++)
        cur = cur.getNext(); 
      cur.setNext(new Node(data, null));
      this.size++;
    }
    
    public void addAtHead(Player data) {
      Node newHead = new Node(data, this.head);
      this.head = newHead;
      this.size++;
    }
    
    public void addAtHead(Team data) {
      Node newHead = new Node(data, this.head);
      this.head = newHead;
      this.size++;
    }
    
    public void remove(Button data) {
      if (this.head == null)
        return; 
      if (this.head.getButtonData() == data) {
        this.head = this.head.getNext();
        this.size--;
        return;
      } 
      Node cur = this.head;
      for (int i = 0; i < this.size - 1; i++) {
        Node next = cur.getNext();
        if (next.getButtonData() == data) {
          next = next.getNext();
          this.size--;
          return;
        } 
        cur = cur.getNext();
      } 
    }
    
    public void remove(Team data) {
      if (this.head == null)
        return; 
      if (this.head.getTeamData() == data) {
        this.head = this.head.getNext();
        this.size--;
        return;
      } 
      Node cur = this.head;
      for (int i = 0; i < this.size - 1; i++) {
        Node next = cur.getNext();
        if (next.getTeamData() == data) {
          next = next.getNext();
          this.size--;
          return;
        } 
        cur = cur.getNext();
      } 
    }
    
    public void remove(Player data) {
      if (this.head == null)
        return; 
      if (this.head.getPlayerData() == data) {
        this.head = this.head.getNext();
        this.size--;
        return;
      } 
      Node cur = this.head;
      for (int i = 0; i < this.size - 1; i++) {
        Node next = cur.getNext();
        if (next.getPlayerData() == data) {
          next = next.getNext();
          this.size--;
          return;
        } 
        cur = cur.getNext();
      } 
    }
    
    public void remove(Match data) {
      if (this.head == null)
        return; 
      if (this.head.getMatchData() == data) {
        this.head = this.head.getNext();
        this.size--;
        return;
      } 
      Node cur = this.head;
      for (int i = 0; i < this.size - 1; i++) {
        Node next = cur.getNext();
        if (next.getMatchData() == data) {
          next = next.getNext();
          this.size--;
          return;
        } 
        cur = cur.getNext();
      } 
    }
    
    public void remove(String data) {
      if (this.head == null)
        return; 
      if (this.head.getStringData() == data) {
        this.head = this.head.getNext();
        this.size--;
        return;
      } 
      Node cur = this.head;
      for (int i = 0; i < this.size - 1; i++) {
        Node next = cur.getNext();
        if (next.getStringData() == data) {
          next = next.getNext();
          this.size--;
          return;
        } 
        cur = cur.getNext();
      } 
    }
    
    public void clear() {
      this.head = null;
      this.size = 0;
    }
    
    public Button getButton(int index) {
      if (this.head == null)
        return null; 
      if (index == 0)
        return this.head.getButtonData(); 
      Node cur = this.head;
      for (int i = 1; i <= index; i++) {
        cur = cur.getNext();
        if (cur == null)
          return null; 
      } 
      return cur.getButtonData();
    }
    
    public Team getTeam(int index) {
      if (this.head == null)
        return null; 
      if (index == 0)
        return this.head.getTeamData(); 
      Node cur = this.head;
      for (int i = 1; i <= index; i++) {
        cur = cur.getNext();
        if (cur == null)
          return null; 
      } 
      return cur.getTeamData();
    }
    
    public Player getPlayer(int index) {
      if (this.head == null)
        return null; 
      if (index == 0)
        return this.head.getPlayerData(); 
      Node cur = this.head;
      for (int i = 1; i <= index; i++) {
        cur = cur.getNext();
        if (cur == null)
          return null; 
      } 
      return cur.getPlayerData();
    }
    
    public Match getMatch(int index) {
      if (this.head == null)
        return null; 
      if (index == 0)
        return this.head.getMatchData(); 
      Node cur = this.head;
      for (int i = 1; i <= index; i++) {
        cur = cur.getNext();
        if (cur == null)
          return null; 
      } 
      return cur.getMatchData();
    }
    
    public String getString(int index) {
      if (this.head == null)
        return null; 
      if (index == 0)
        return this.head.getStringData(); 
      Node cur = this.head;
      for (int i = 1; i <= index; i++) {
        cur = cur.getNext();
        if (cur == null)
          return null; 
      } 
      return cur.getStringData();
    }
    
    public Player getNextPlayer() {
      Player tempPlayer = this.head.getPlayerData();
      remove(tempPlayer);
      return tempPlayer;
    }
    
    public Team getNextTeam() {
      Team tempTeam = this.head.getTeamData();
      remove(tempTeam);
      return tempTeam;
    }
    
    public Match getNextMatch() {
      Match tempMatch = this.head.getMatchData();
      remove(tempMatch);
      return tempMatch;
    }
    
    public String getNextString() {
      String tempString = this.head.getStringData();
      remove(tempString);
      return tempString;
    }
    
    public Player[] getPlayerDataInArray() {
      if (this.head == null)
        return new Player[0]; 
      Player[] returnData = new Player[this.size];
      Node cur = this.head;
      for (int i = 0; i < this.size; i++) {
        returnData[i] = cur.getPlayerData();
        cur = cur.getNext();
      } 
      return returnData;
    }
    
    public Team[] getTeamDataInArray() {
      if (this.head == null)
        return new Team[0]; 
      Team[] returnData = new Team[this.size];
      Node cur = this.head;
      for (int i = 0; i < this.size; i++) {
        returnData[i] = cur.getTeamData();
        cur = cur.getNext();
      } 
      return returnData;
    }
    
    public LinkedList cloneMatches() {
      LinkedList clonedList = new LinkedList();
      Node cur = this.head;
      for (int i = 0; i < this.size; i++) {
        clonedList.add(cur.getMatchData());
        cur = cur.getNext();
      } 
      return clonedList;
    }
    
    public LinkedList cloneStrings() {
      LinkedList clonedList = new LinkedList();
      Node cur = this.head;
      for (int i = 0; i < this.size; i++) {
        clonedList.add(cur.getStringData());
        cur = cur.getNext();
      } 
      return clonedList;
    }
    
    public int getSize() {
      return this.size;
    }
  }
  