package route.model;

public class Field {

	public Field(Coords coords) {
		this.coords = coords;
	}

	public Coords getCoords() {
		return coords;
	}

	/* ========== Private ========== */
	private final Coords coords;

}
