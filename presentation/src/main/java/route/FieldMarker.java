package route;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import route.model.Coords;
import route.model.Field;

public class FieldMarker {

	public int[][] createMarkedArray(final Field[][] board, Coords start, Coords destination) {

		if (!start.equals(destination)) {

			Field startField = board[start.getX()][start.getY()];
			Field destinationField = board[destination.getX()][destination.getY()];

			initialize(board);

			addStartFieldToTheQueue(startField);

			if (markArrayForShortestPath(searchingQueue, destinationField)) {
				printMarkedArray(markerArray, start, destination);
				return markerArray;
			}
		}

		printMarkedArray(markerArray, start, destination);
		return null;
	}

	/* Private */
	private Queue<Field> searchingQueue;
	private int[][] markerArray;
	private Field[][] board;

	private void addStartFieldToTheQueue(Field start) {

		// Startujemy dodaj�c pierwsze pole do kolejki
		Coords coords = start.getCoords();
		markerArray[coords.getX()][coords.getY()] = 0;

		searchingQueue.add(start);

	}

	private boolean markArrayForShortestPath(Queue<Field> searchingQueue, Field endField) {

		Field field = searchingQueue.poll();

		if (field == null)
			return false; // Nie uda�o nam si� znale�� trasy

		Coords coords = field.getCoords();
		int y = coords.getY();
		int x = coords.getX();

		int actualIndex = markerArray[x][y];

		if (markSurroundingFieldsAndAddToQueue(searchingQueue, x, y, ++actualIndex, endField.getCoords()))
			return true;

		return markArrayForShortestPath(searchingQueue, endField);

	}

	private boolean markSurroundingFieldsAndAddToQueue(Collection<Field> collection, int x, int y, int actualIndex,
			Coords destinationCoords) {

		return tryToAddField(x + 1, y - 1, collection, actualIndex, destinationCoords)
				|| tryToAddField(x + 1, y + 0, collection, actualIndex, destinationCoords)
				|| tryToAddField(x + 1, y + 1, collection, actualIndex, destinationCoords)
				|| tryToAddField(x + 0, y - 1, collection, actualIndex, destinationCoords)
				|| tryToAddField(x + 0, y + 1, collection, actualIndex, destinationCoords)
				|| tryToAddField(x - 1, y - 1, collection, actualIndex, destinationCoords)
				|| tryToAddField(x - 1, y + 0, collection, actualIndex, destinationCoords)
				|| tryToAddField(x - 1, y + 1, collection, actualIndex, destinationCoords);

	}

	private boolean tryToAddField(int x, int y, Collection<Field> collection, int actualIndex, Coords destinationCoords) {

		if (x < 0 || x >= board.length || y < 0 || y >= board[x].length)
			return false;

		Field field = board[x][y];

		if (field != null && markerArray[x][y] == 0) { // Pole nie zosta�o jeszcze odwiedzone

			markerArray[x][y] = actualIndex;

			if (destinationCoords.getX() == x && destinationCoords.getY() == y)
				return true;

			collection.add(field);
		}

		return false;

	}

	private void initialize(Field[][] givenBoard) {

		searchingQueue = new LinkedList<Field>();

		this.board = givenBoard;

		int width = givenBoard.length;

		markerArray = new int[width][];

		for (int i = 0; i < width; ++i) {

			int height = givenBoard[i].length;

			markerArray[i] = new int[height];
			for (int j = 0; j < height; ++j)
				markerArray[i][j] = 0;

		}

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
		System.out.println();
	}

}
