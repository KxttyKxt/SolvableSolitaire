package core;

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
	

	// record-like naming conventions
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


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Card card))
			return false;

		return this.face == card.face
				&& this.suit == card.suit
				&& this.facingUp == card.facingUp;
	}

	@Override
	public String toString() {
		return facingUp
				? String.format("[%s%s]", suit.label, face.label)
				: "[..]";
	}


	public enum Face {
		ACE(1, 'A'),
		TWO(2, '2'),
		THREE(3, '3'),
		FOUR(4, '4'),
		FIVE(5, '5'),
		SIX(6, '6'),
		SEVEN(7, '7'),
		EIGHT(8, '8'),
		NINE(9, '9'),
		TEN(10, 'X'),
		JACK(11, 'J'),
		QUEEN(12, 'Q'),
		KING(13, 'K'),;

		public final int value;
		public final char label;

		Face(int value, char label) {
			this.value = value;
			this.label = label;
		}
	}

	public enum Suit {
		HEARTS('♥'),
		DIAMONDS('♦'),
		SPADES('♠'),
		CLUBS('♣');

		public final char label;

		Suit(char label) {
			this.label = label;
		}
	}
}


