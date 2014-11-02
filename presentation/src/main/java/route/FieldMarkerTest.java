package route;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import route.model.Coords;
import route.model.Field;

public class FieldMarkerTest {

	FieldMarker fieldMarker = new FieldMarker();

	@Test
	public void testFieldMarker() {

		Field[][] board = initializeBoard(new Coords(10, 10));

		Coords start = new Coords(4, 3);
		Coords destination = new Coords(5, 6);

		int[][] markerArray = fieldMarker.createMarkedArray(board, start, destination);

		assertNotNull(markerArray);
		assertEquals(markerArray[5][6], 4); // Znalezione w 4 kroku

		printMarkedArray(markerArray, start, destination);

	}

	@Test
	public void testFieldMarkerStartEqualsDestination() {

		Field[][] board = initializeBoard(new Coords(10, 10));

		Coords start = new Coords(9, 1);
		Coords destination = new Coords(9, 1);

		int[][] markerArray = fieldMarker.createMarkedArray(board, start, destination);

		assertNull(markerArray);

	}

	@Test
	public void testFieldMarkerOutOfBounds() {

		Field[][] board = initializeBoard(new Coords(10, 10));

		Coords start = new Coords(0, 0);
		Coords destination = new Coords(0, 9);

		int[][] markerArray = fieldMarker.createMarkedArray(board, start, destination);

		assertNotNull(markerArray);
		assertEquals(markerArray[5][6], 7); // Znalezione w 6 kroku

	}
	
	@Test
	public void testFieldMarkerOutOfBoundsExampleTwo() {

		Field[][] board = initializeBoard(new Coords(10, 10));

		Coords start = new Coords(0, 0);
		Coords destination = new Coords(9, 0);

		int[][] markerArray = fieldMarker.createMarkedArray(board, start, destination);

		assertNotNull(markerArray);
		assertEquals(markerArray[5][6], 7); // Znalezione w 6 kroku

	}
	
	@Test
	public void testFieldMarkerUnreachableDestination() {

		Field[][] board = initializeBoard(new Coords(5, 2));

		Coords start = new Coords(1, 1);
		Coords destination = new Coords(4, 1);
		
		board[2][0] = null;
		board[2][1] = null;

		int[][] markerArray = fieldMarker.createMarkedArray(board, start, destination);

		assertNull(markerArray);

	}
	
	private void printMarkedArray(int[][] markerArray, Coords start, Coords destination) {
		for (int i = 0; i < markerArray.length; ++i) {
			for (int j = 0; j < markerArray[i].length; ++j) {

				if (start.getX() == i && start.getY() == j)
					System.out.print(" @");
				else if (destination.getX() == i && destination.getY() == j)
					System.out.print(" #");
				else
					System.out.print(" " + markerArray[i][j]);
			}
			System.out.println();
		}
	}

	private Field[][] initializeBoard(Coords coords) {

		int width = coords.getX();
		int height = coords.getY();

		Field[][] board = new Field[width][height];

		for (int i = 0; i < width; ++i) {
			board[i] = new Field[height];

			for (int j = 0; j < height; ++j)
				board[i][j] = new Field(new Coords(i, j));
		}

		return board;

	}

}
