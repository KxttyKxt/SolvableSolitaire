package universal;

public class Card {
	private final Suit suit;
	private final Face face;
	private boolean facingUp;

	Card(Suit suit, Face face, boolean facingUp) {
		this.suit = suit;
		this.face = face;
		this.facingUp = facingUp;
	}

	Card(Suit suit, Face face) {
		this(suit, face, false);
	}
	

	public Face face() {
		return facingUp ? face : null;
	}

	public Suit suit() {
		return facingUp ? suit : null;
	}

	public boolean facingUp() {
		return facingUp;
	}
	

	public void flip() {
		facingUp = !facingUp;
	}
	

	public enum Face {
		ACE(1),
		TWO(2),
		THREE(3),
		FOUR(4),
		FIVE(5),
		SIX(6),
		SEVEN(7),
		EIGHT(8),
		NINE(9),
		TEN(10),
		JACK(11),
		QUEEN(12),
		KING(13),;

		public final int value;

		Face(int value) {
			this.value = value;
		}
	}

	public enum Suit {
		HEARTS,
		DIAMONDS,
		SPADES,
		CLUBS,
	}
}


