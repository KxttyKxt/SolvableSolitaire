package core;

import java.util.*;

public class Deck {
	public static Deque<Card> newDeck() {
		return new ArrayDeque<>(Arrays.asList(arrayDeck()));
	}

	public static Deque<Card> shuffledDeck(Random random) {
		List<Card> cardsList = Arrays.asList(arrayDeck());
		Collections.shuffle(cardsList, random);
		return new ArrayDeque<>(cardsList);
	}

	public static boolean equals(Deque<Card> deck1, Deque<Card> deck2) {
		if (deck1.size() != deck2.size())
			return false;

		Iterator<Card> it1 = deck1.iterator();
		Iterator<Card> it2 = deck2.iterator();

		while (it1.hasNext())
			if (!it1.next().equals(it2.next()))
				return false;

		return true;
	}

	private static Card[] arrayDeck() {
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
}
