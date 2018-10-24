
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
		Node current = myFirst;
		Node previous = null;
		Node next = null;
		LinkStrand ret = new LinkStrand();
		
		while(current != null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		current = previous;
		
		while(current != null) {
	    StringBuilder copy = new StringBuilder(current.info);
		copy.reverse();
		current.info = copy.toString();
		ret.append(current.info);
		current = current.next;
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
		if(index >= mySize) 
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
		return current.info.charAt(myLocalIndex);
	}

}
