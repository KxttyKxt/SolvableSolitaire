package core;

public class Card {
	private final Suit suit;
	private final Face face;
	private boolean facingUp;

	/// making this true is an irreversible action
	private boolean flagImmutable;

	public Card(Suit suit, Face face, boolean facingUp) {
		this.suit = suit;
		this.face = face;
		this.facingUp = facingUp;
		flagImmutable = false;
	}

	public Card(Suit suit, Face face) {
		this(suit, face, false);
	}

	public Card() {
		this(Suit.HEARTS, Face.ACE);
	}


	/// returns an immutable copy of this card.
	/// making a card immutable is an irreversible action.
	///
	/// once a card is made immutable, certain methods will throw an
	/// [`UnsupportedOperationException`.][UnsupportedOperationException]
	/// [Flipping a card][Card#flip()] is an example of this.
	///
	/// immutable cards are used to share information about a Game's
	/// state while making sure that shared cards aren't mutated improperly.
	/// more specifically, games like [Klondike][klondike.KlondikeGame]
	/// share card information for display to the user so that they can plan
	/// their next move.
	Card makeImmutable() {
		Card immutable = new Card(this.suit, this.face, this.facingUp);
		immutable.flagImmutable = true;
		return immutable;
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
	public boolean isImmutable() {
		return flagImmutable;
	}
	

	/// flip a card over.
	/// if the card was face-up, it will now be face-down, and vice versa.
	///
	/// @throws UnsupportedOperationException if the card is
	/// [immutable][Card#makeImmutable()]
	public void flip() {
		if (flagImmutable) {
			throw new UnsupportedOperationException("An immutable card cannot be flipped.");
		}
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


