package universal;

import java.util.*;

public class Deck {
	public static Card[] newDeck() {
		return new Card[]{
				new Card(Card.Suit.HEARTS, Card.Face.ACE),
				new Card(Card.Suit.HEARTS, Card.Face.TWO),
				new Card(Card.Suit.HEARTS, Card.Face.THREE),
				new Card(Card.Suit.HEARTS, Card.Face.FOUR),
				new Card(Card.Suit.HEARTS, Card.Face.FIVE),
				new Card(Card.Suit.HEARTS, Card.Face.SIX),
				new Card(Card.Suit.HEARTS, Card.Face.SEVEN),
				new Card(Card.Suit.HEARTS, Card.Face.EIGHT),
				new Card(Card.Suit.HEARTS, Card.Face.NINE),
				new Card(Card.Suit.HEARTS, Card.Face.TEN),
				new Card(Card.Suit.HEARTS, Card.Face.JACK),
				new Card(Card.Suit.HEARTS, Card.Face.QUEEN),
				new Card(Card.Suit.HEARTS, Card.Face.KING),

				new Card(Card.Suit.DIAMONDS, Card.Face.ACE),
				new Card(Card.Suit.DIAMONDS, Card.Face.TWO),
				new Card(Card.Suit.DIAMONDS, Card.Face.THREE),
				new Card(Card.Suit.DIAMONDS, Card.Face.FOUR),
				new Card(Card.Suit.DIAMONDS, Card.Face.FIVE),
				new Card(Card.Suit.DIAMONDS, Card.Face.SIX),
				new Card(Card.Suit.DIAMONDS, Card.Face.SEVEN),
				new Card(Card.Suit.DIAMONDS, Card.Face.EIGHT),
				new Card(Card.Suit.DIAMONDS, Card.Face.NINE),
				new Card(Card.Suit.DIAMONDS, Card.Face.TEN),
				new Card(Card.Suit.DIAMONDS, Card.Face.JACK),
				new Card(Card.Suit.DIAMONDS, Card.Face.QUEEN),
				new Card(Card.Suit.DIAMONDS, Card.Face.KING),

				new Card(Card.Suit.SPADES, Card.Face.ACE),
				new Card(Card.Suit.SPADES, Card.Face.TWO),
				new Card(Card.Suit.SPADES, Card.Face.THREE),
				new Card(Card.Suit.SPADES, Card.Face.FOUR),
				new Card(Card.Suit.SPADES, Card.Face.FIVE),
				new Card(Card.Suit.SPADES, Card.Face.SIX),
				new Card(Card.Suit.SPADES, Card.Face.SEVEN),
				new Card(Card.Suit.SPADES, Card.Face.EIGHT),
				new Card(Card.Suit.SPADES, Card.Face.NINE),
				new Card(Card.Suit.SPADES, Card.Face.TEN),
				new Card(Card.Suit.SPADES, Card.Face.JACK),
				new Card(Card.Suit.SPADES, Card.Face.QUEEN),
				new Card(Card.Suit.SPADES, Card.Face.KING),

				new Card(Card.Suit.CLUBS, Card.Face.ACE),
				new Card(Card.Suit.CLUBS, Card.Face.TWO),
				new Card(Card.Suit.CLUBS, Card.Face.THREE),
				new Card(Card.Suit.CLUBS, Card.Face.FOUR),
				new Card(Card.Suit.CLUBS, Card.Face.FIVE),
				new Card(Card.Suit.CLUBS, Card.Face.SIX),
				new Card(Card.Suit.CLUBS, Card.Face.SEVEN),
				new Card(Card.Suit.CLUBS, Card.Face.EIGHT),
				new Card(Card.Suit.CLUBS, Card.Face.NINE),
				new Card(Card.Suit.CLUBS, Card.Face.TEN),
				new Card(Card.Suit.CLUBS, Card.Face.JACK),
				new Card(Card.Suit.CLUBS, Card.Face.QUEEN),
				new Card(Card.Suit.CLUBS, Card.Face.KING),
		};
	}

	public static Stack<Card> shuffleCards(Card[] cards, Random random) {
		List<Card> cardsList = Arrays.asList(cards);
		Collections.shuffle(cardsList, random);

		Stack<Card> toReturn = new Stack<>();
		toReturn.addAll(cardsList);

		return toReturn;
	}
}
