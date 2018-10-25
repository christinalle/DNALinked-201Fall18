
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

	public LinkStrand() {
		this("");
	}
	public LinkStrand(String s) {
		initialize(s);
	}
	
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
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return mySize;
	}
	@Override
	public IDnaStrand getInstance(String source) {
		// TODO Auto-generated method stub
		return new LinkStrand(source);
	}
	@Override
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stu
		Node nodeDNA = new Node(dna);
		myLast.next = nodeDNA;
		myLast = myLast.next;
		mySize = mySize + dna.length();
		myAppends++;
		return this;
	}
	
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
	
	
	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}
	
/**	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		if(index >= mySize) 
			 throw new IndexOutOfBoundsException();
		
		while(myIndex!=index) {
			myIndex++;
			myLocalIndex++;
			if(myIndex >= mySize-1) {
				myIndex = 0;
				myLocalIndex = 0;
				myCurrent = myFirst;
			}
			if(myLocalIndex >= myCurrent.info.length()) {
			myLocalIndex = 0;
			myCurrent = myCurrent.next;
			}
		}
		return myCurrent.info.charAt(myLocalIndex);
	
	}
	**/
	
	
	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		if(index >= mySize || index < 0) 
			 throw new IndexOutOfBoundsException();
			
		Node current = myCurrent;
		int dex = myLocalIndex;
		int count = myIndex;
		while(count!=index) {
			count++;
			dex++;
			if(count >= mySize-1) {
				myIndex = 0;
				myLocalIndex = 0;
				myCurrent = myFirst;
			}
			if(dex >= current.info.length()) {
			dex = 0;
			current = current.next;
			}
			myCurrent = current;
			myLocalIndex = dex;
		}
		myIndex = index;
		return current.info.charAt(myLocalIndex);
	
	}

}
