package universal;

import java.util.*;

public class Deck {
	public static Card[] newDeck(boolean facing) {
		return new Card[]{
				new Card(Card.Suit.HEARTS, Card.Face.ACE, facing),
				new Card(Card.Suit.HEARTS, Card.Face.TWO, facing),
				new Card(Card.Suit.HEARTS, Card.Face.THREE, facing),
				new Card(Card.Suit.HEARTS, Card.Face.FOUR, facing),
				new Card(Card.Suit.HEARTS, Card.Face.FIVE, facing),
				new Card(Card.Suit.HEARTS, Card.Face.SIX, facing),
				new Card(Card.Suit.HEARTS, Card.Face.SEVEN, facing),
				new Card(Card.Suit.HEARTS, Card.Face.EIGHT, facing),
				new Card(Card.Suit.HEARTS, Card.Face.NINE, facing),
				new Card(Card.Suit.HEARTS, Card.Face.TEN, facing),
				new Card(Card.Suit.HEARTS, Card.Face.JACK, facing),
				new Card(Card.Suit.HEARTS, Card.Face.QUEEN, facing),
				new Card(Card.Suit.HEARTS, Card.Face.KING, facing),

				new Card(Card.Suit.DIAMONDS, Card.Face.ACE, facing),
				new Card(Card.Suit.DIAMONDS, Card.Face.TWO, facing),
				new Card(Card.Suit.DIAMONDS, Card.Face.THREE, facing),
				new Card(Card.Suit.DIAMONDS, Card.Face.FOUR, facing),
				new Card(Card.Suit.DIAMONDS, Card.Face.FIVE, facing),
				new Card(Card.Suit.DIAMONDS, Card.Face.SIX, facing),
				new Card(Card.Suit.DIAMONDS, Card.Face.SEVEN, facing),
				new Card(Card.Suit.DIAMONDS, Card.Face.EIGHT, facing),
				new Card(Card.Suit.DIAMONDS, Card.Face.NINE, facing),
				new Card(Card.Suit.DIAMONDS, Card.Face.TEN, facing),
				new Card(Card.Suit.DIAMONDS, Card.Face.JACK, facing),
				new Card(Card.Suit.DIAMONDS, Card.Face.QUEEN, facing),
				new Card(Card.Suit.DIAMONDS, Card.Face.KING, facing),

				new Card(Card.Suit.SPADES, Card.Face.ACE, facing),
				new Card(Card.Suit.SPADES, Card.Face.TWO, facing),
				new Card(Card.Suit.SPADES, Card.Face.THREE, facing),
				new Card(Card.Suit.SPADES, Card.Face.FOUR, facing),
				new Card(Card.Suit.SPADES, Card.Face.FIVE, facing),
				new Card(Card.Suit.SPADES, Card.Face.SIX, facing),
				new Card(Card.Suit.SPADES, Card.Face.SEVEN, facing),
				new Card(Card.Suit.SPADES, Card.Face.EIGHT, facing),
				new Card(Card.Suit.SPADES, Card.Face.NINE, facing),
				new Card(Card.Suit.SPADES, Card.Face.TEN, facing),
				new Card(Card.Suit.SPADES, Card.Face.JACK, facing),
				new Card(Card.Suit.SPADES, Card.Face.QUEEN, facing),
				new Card(Card.Suit.SPADES, Card.Face.KING, facing),

				new Card(Card.Suit.CLUBS, Card.Face.ACE, facing),
				new Card(Card.Suit.CLUBS, Card.Face.TWO, facing),
				new Card(Card.Suit.CLUBS, Card.Face.THREE, facing),
				new Card(Card.Suit.CLUBS, Card.Face.FOUR, facing),
				new Card(Card.Suit.CLUBS, Card.Face.FIVE, facing),
				new Card(Card.Suit.CLUBS, Card.Face.SIX, facing),
				new Card(Card.Suit.CLUBS, Card.Face.SEVEN, facing),
				new Card(Card.Suit.CLUBS, Card.Face.EIGHT, facing),
				new Card(Card.Suit.CLUBS, Card.Face.NINE, facing),
				new Card(Card.Suit.CLUBS, Card.Face.TEN, facing),
				new Card(Card.Suit.CLUBS, Card.Face.JACK, facing),
				new Card(Card.Suit.CLUBS, Card.Face.QUEEN, facing),
				new Card(Card.Suit.CLUBS, Card.Face.KING, facing),
		};
	}

	public static Card[] newDeck() {
		return newDeck(false);
	}

	public static Stack<Card> shuffleCards(Card[] cards, Random random) {
		List<Card> cardsList = Arrays.asList(cards);
		Collections.shuffle(cardsList, random);

		Stack<Card> toReturn = new Stack<>();
		toReturn.addAll(cardsList);
		return toReturn;
	}
}
