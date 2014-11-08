package route;

import org.testng.annotations.Test;

import route.model.Coords;
import route.model.Field;

public class FieldMarkerTest {

	private FieldMarker fieldMarker = new FieldMarker();

	@Test
	public void testFieldMarker() {
		
	}

	@Test
	public void testFieldMarkerStartEqualsDestination() {

	}

	@Test
	public void testFieldMarkerBoundsExample() {

	}

	@Test
	public void testFieldMarkerUnreachableDestination() {

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
