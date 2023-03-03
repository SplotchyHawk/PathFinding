// ShortestPath by Sebastien Godbout 251011518, this program finds the best and shortest possible path to the customer
// Uses DLList and DLNode through DLList
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShortestPath {
	
	public static Map cityMap;
	
	
// Takes the filename inputed and allows the program to run based on the map given	
	public ShortestPath (String filename) {
		
		try {
			cityMap = new Map(filename);
		}catch (InvalidMapException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
// Main class which uses all aspects of DLList and nextCell to find the shortest possible path to the customer 
	public static void main (String[] args) {

// Asks the user for the file name that contains the map
		if (args.length < 1) {
			System.out.println("Please give the name of the File: ");
			System.exit(0);
		}		
		String mapfile = args[2];
		
// This creates an object of the class ShortestPath to implement the map file's name
		ShortestPath path = new ShortestPath(mapfile);
		
		
// Initialize an object of type doubly linked list
		DLList<MapCell> list = new DLList<MapCell>();

// Creates a variable of type map cell holding the value of the start of the map given
		MapCell first = cityMap.getStart(); 
		
// Throws the object first into the linked list with a value of 0 and marks it as within the list
		list.insert(first, 0);
		first.markInList();

// Initialize the distance used to eventually display how many cells it took to reach the customer
		int smallDistance = 0;

// The first while loop which only works as long as the list is not empty
		while (!list.isEmpty()) {
			
			MapCell smallest = list.getSmallest(); // Initializes the value used to display the smallest value within the list
			smallest.markOutList();
			
			if (smallest.isCustomer()) { // Makes sure customer is not reached
				break;
			} else { 
				
				for (int i = 0; i < 4; i++) { // Runs through all possible cells around a certain cell before moving on
					
					MapCell nextCell = nextCell(smallest);	// Using nextCell() finds the value of any of the cells after the current smallest cell
				
					while (nextCell != null && !nextCell.isMarked()) {
					
						smallDistance = 1 + smallest.getDistanceToStart(); // Increments the value of the smallest distance
					
						if (nextCell.getDistanceToStart() > smallDistance) {
						
							nextCell.setDistanceToStart(smallDistance);
						
							nextCell.setPredecessor(smallest); // Keeps track of the path taken
						}	
						
						int nextDistance = nextCell.getDistanceToStart(); // New distance value to be used once the while loop restarts
					
		
						list.insert(nextCell, nextDistance); // Inserts the nextCell and the above value to later be used as "smallest"
						
						nextCell.markInList();		
					}
				}	
			}		
		}
	
// Print statements to show the result of the test on the map
		if (list.isEmpty())	{
			System.out.println("No possible path to the cutomer");
		} else {
			System.out.println("Customer reached");
			System.out.println("The shortest distance to customer is " + smallDistance + " cells");
			}
	}
		
	
	
	private static MapCell nextCell(MapCell cell) {

// Finds the next cell to continue with
		if ((cell.isOmniSwitch()) || (cell.isPowerStation())) {

			for (int i = 0; i <= 3; i++) {
				MapCell cellOption = cell.getNeighbour(i); // Looped in order to get all the surrounding cells

				if (cellOption != null) {

					if ((!cellOption.isMarkedInList()) && (!cellOption.isMarkedOutList())) { // Checking if any of the cells are marked

						if ((i==0) || (i==2)) { // Top and bottom cells

							if ((!cellOption.isHorizontalSwitch()) && (!cellOption.isBlock())) {
								return cellOption;
							}
						}

						if ((i==1) || (i==3)) { // Left and right cells

							if ((!cellOption.isVerticalSwitch()) && (!cellOption.isBlock())) {
								return cellOption;
							}
						}
					}
				}
			}
		}

// Finds next cell from a vertical switch while respecting its limitations
			if (cell.isVerticalSwitch()) {

				for (int i = 0; i <= 2; i = i + 2) { // Checking top and bottom cells
					MapCell cellOption = cell.getNeighbour(i);

					if (cellOption != null) {

						if ((!cellOption.isMarkedInList()) && (!cellOption.isMarkedOutList())) { // Check if marked

							if ((!cellOption.isHorizontalSwitch()) && (!cellOption.isBlock())) { // If possible returns next cell
								return cellOption;
							}
						}
					}
				}
			}

// Finds next cell from a horizontal switch while respecting its limitations
			if (cell.isHorizontalSwitch()) {

				for (int i = 1; i <= 3 ; i = i + 2) { // Checking right and left cells
					MapCell cellOption = cell.getNeighbour(i);

					if (cellOption != null) {

						if ((!cellOption.isMarkedInList()) && (!cellOption.isMarkedOutList())) { // Check if marked

							if ((!cellOption.isVerticalSwitch()) && (!cellOption.isBlock())) { // If possible returns next cell
								return cellOption;
							}
						}
					}
				}
			}
// Returns null if no cell options are available to move forward
		return null;
		}
}
	
