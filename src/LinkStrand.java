
public class LinkStrand implements IDnaStrand {
	
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	
	private Node myFirst, myLast, myCurrent;
	private long mySize;
	private int myAppends;
	private int myIndex, myLocalIndex;

	/**
	 * Constructor that makes a LinkStrand with ""
	 */
	public LinkStrand() {
		this("");
	}
	
	/**
	 * Constructor that makes a LinkStrand with the given String
	 * @param String that will be used to make a new LinkStrand
	 */
	public LinkStrand(String s) {
		initialize(s);
	}
	
	/**
	 * Method that establishes the first Node in the LinkStrand
	 * @param String that will be used to make a new Node
	 */
	@Override 
	public void initialize(String source) {
		myFirst = new Node(source);
		myLast = myFirst;
		myAppends = 0;
		mySize = source.length();
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst;
		
	}
	
	/**
	 * returns the size
	 * @return the number of characters in the LinkStrand
	 */
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return mySize;
	}
	
	/**
	 * returns a new LinkStrand object
	 * @param String 
	 * @return LinkStrand created using String source
	 */
	@Override
	public IDnaStrand getInstance(String source) {
		// TODO Auto-generated method stub
		return new LinkStrand(source);
	}
	
	/**
	 * Appends a strand of DNA to the LinkStrand/LinkedList
	 * @return LinkStrand with the new DNA
	 * @param dna is the String that is used to make a new Node to append
	 */
	@Override
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		Node nodeDNA = new Node(dna);
		myLast.next = nodeDNA;
		myLast = myLast.next;
		mySize = mySize + dna.length();
		myAppends++;
		return this;
	}
	
	/**
	 * Creates a String of the DNA to return
	 * @return String of all the DNA in the LinkStrand/nodes
	 */
	@Override
	public String toString() {
        Node current = myFirst;
		StringBuilder ret = new StringBuilder("");
		
		while(current!= null) {
			ret = ret.append(current.info);
			current = current.next;
		}
		return ret.toString();
	}

	/**
	 * Reverses all the nodes in the LinkedList (LinkStrand)
	 * And also reverses all the characters/letters in the Strings
	 * stored in the nodes without mutating the original LinkedList
	 * @return LinkStrand of the reversed DNA
	 */
	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
	    LinkStrand ret = new LinkStrand();
	    Node original = this.myFirst;
	    StringBuilder first = new StringBuilder(original.info);
		first.reverse();
		Node rev = new Node(first.toString());
		Node head = rev;
		original = original.next;
		
		while(original != null) {
			StringBuilder copy = new StringBuilder(original.info);
			copy.reverse();
			Node add = new Node(copy.toString());
			add.next = head;
			head = add;
			original = original.next;
		}
		
		while(head != null) {
		ret.append(head.info);
		head = head.next;
		}
		
	return ret;

	}
	
	/**
	 * Counts the number of times append has been used
	 * @return int of the number of time appends has been called
	 */
	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}
	
	/**
	 * Method that returns the character of a given index
	 * by looping through the LinkedList
	 * However, reestablishes the start index as the last
	 * index called to establish O(1) if the next call
	 * references the previous index + 1
	 * @param int index you want to retrieve
	 * @return the character at that index
	 */
   @Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		if(index >= mySize || index < 0) 
			 throw new IndexOutOfBoundsException();
		if(myIndex > index){
		myCurrent = myFirst;
		myIndex = 0;
		myLocalIndex = 0;
		}
		while(myIndex!=index) {
			myIndex++;
			myLocalIndex++;
			if(myLocalIndex >= myCurrent.info.length()) {
				myLocalIndex = 0;
				myCurrent = myCurrent.next;
			}
		}
		return myCurrent.info.charAt(myLocalIndex);
	}
	

}
