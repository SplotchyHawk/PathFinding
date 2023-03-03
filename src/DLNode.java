// DLNode by Sebastien Godbout 251011518, creates the class of a doubly linked node for a doubly linked list
public class DLNode<T> {

// Initializes DLNode's variables
	private T dataItem;
	private DLNode<T> next;
	private DLNode<T> previous;
	private int value;
	
// Constructor	
	public DLNode(T data, int value) {
		this.dataItem = data;
		this.value = value;
	}

// Returns value of a DLNode
	public int getValue() {
		return value;
	}

// Returns DataItem of a DLNode
	public T getData() {
		return dataItem;
	}

// Returns the next DLNode in respect to the current one
	public DLNode<T> getNext() {
		return next;
	}
	
// Returns the previous DLNode in respect to the current one
	public DLNode<T> getPrevious() {
		return previous;
	}

// Changes the dataItem within the DLNode
	public void setData(T data) {
		this.dataItem = data;
	}

// Sets the next DLNode in respect to the current one
	public void setNext(DLNode<T> next) {
		this.next = next;
	}

// Sets the previous DLNode in respect to the current one
	public void setPrevious(DLNode<T> previous) {
		this.previous = previous;
	}

// Changes the value within the DLNode
	public void setValue(int val) {
		this.value = val;
	}
	
	
	
	
	
	
	
	

}
