// DLList by Sebastien Godbout 251011518, creates the class DLList with certain methods to be used in ShortestPath
public class DLList<T> implements DLListADT<T> {
	
// Initializes DLList's variables
	private DLNode<T> front;
	private DLNode<T> rear;
	private int count;

// Constructor
	public DLList() {
		front = null;
		rear = null;
		count = 0;
	}

// Method to insert a DLNode with the defined dataItem and value into the DLList
	public void insert(T dataItem, int value) {
		
		DLNode<T> node = new DLNode<T>(dataItem, value); // Creates the DLNode
		
		if (front == null) { // If empty makes the node the only one within the list
			front = rear = node;
			count++;
		} else {
			rear.setNext(node); // Otherwise puts it in with other nodes
			node.setNext(null);
			node.setPrevious(rear);
			rear = node;
			count++; // Increases count no matter what due to a node being added
		}
	}

// Method to get a value based on the dataItem representing it within the DLList
	public int getDataValue(T dataItem) throws InvalidDataItemException {
		
		DLNode<T> temp = front; // Creates node at beginning of list to traverse the list as a temporary node
		
		while (temp != null) { 
			
			if (temp.getData().equals(dataItem)) { // For when the dataItem matching the specified dataItem is found
				return temp.getValue(); 		   // Returns the corresponding value
			
			} else {
				temp = temp.getNext(); // Otherwise moves down the list
			}
		
		}
		
		throw new InvalidDataItemException("Item was not found within list"); // If no matching dataItem is found, exception is thrown
	}
	
// Method to change the value of a specified dataItem within a DLNode
	public void changeValue(T dataItem, int newValue) throws InvalidDataItemException {
		
		DLNode<T> temp = front; // Creates node at beginning of list to traverse the list as a temporary node
		
		while (temp != null) {
			
			if (temp.getData().equals(dataItem)) { // For when the dataItem matching the specified dataItem is found
				temp.setValue(newValue); 		   // Changes the value of the corresponding DLNode
				break; 							   // Exits the loop instead of returning anything
			
			} else {
				temp = temp.getNext(); // Otherwise moves down the list
			}
		}
		
		if (temp == null) {
			throw new InvalidDataItemException("Item was not found within list"); // If temp reaches the end of list an exception is thrown
		}
		
	}

// Method to find and remove the DLNode with the smallest value	
	public T getSmallest() throws EmptyListException {
		
		if (isEmpty()) {
			throw new EmptyListException("List is empty"); // If empty and exception is thrown
		}
		
			DLNode<T> temp = front; 	  // Creates node at beginning of list to traverse the list as a temporary node
			DLNode<T> smallObject = temp; // Lets the first DLNode within the list equal the smallest for the time being
			
			while (temp != null) {
			
				if (temp.getValue() < smallObject.getValue()) { // Checks to see if temp value is smaller than the previously stored node's value
					smallObject = temp;							// If so makes the smallest equal to that node and finds next node
					temp = temp.getNext();
				} else {
					temp = temp.getNext(); // Finds next node in list
				}
			}

// Part of method to remove the node with the smallest value
			if (count == 1) { 							 // If one node exists within the list it sets everything to null
				front = rear = null;
			} else if (smallObject.equals(front)) {
				smallObject.getNext().setPrevious(null); // If node is at the front
				front = smallObject.getNext();
			} else if (smallObject.equals(rear)) {
				smallObject.getPrevious().setNext(null); // If node is at the rear
				rear = smallObject.getPrevious();
			} else {
				smallObject.getPrevious().setNext(smallObject.getNext());
				smallObject.getNext().setPrevious(smallObject.getPrevious()); // If node is in the middle of a group of nodes
			}
			count--; // Removes one from count due to deleting a node
		
			return smallObject.getData(); // Returns the smallest node's dataItem	
	}
	
// Method to check if the list is empty and returns true or false
	public boolean isEmpty() {
		return front == null;
	}

// Method to return the size of the list using its count
	public int size() {
		return count;
	}
	
// The print statement for a DLList
	public String toString() {
		
// Initializes variables
		DLNode<T> node = front;
		String word = "List: ";
		T tempData = null;
		int tempVal = 0;
		
		if (count == 0) { 
			System.out.println("List is empty"); // If list is empty
			System.exit(0);
		}
		
		else {
			while (node.getNext() != null) { // Otherwise goes down the list saving the node's data and value within the string word
				
				tempData = node.getData();
				tempVal = node.getValue();
				
				word = word + tempData + "," + tempVal + ";";
				
				node = node.getNext();
			}
		}
		return word;
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
