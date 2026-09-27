package core;

import java.util.*;

public class CardStack {
	private final Deque<Card> cards;

	public static CardStack withNewDeck() {
		CardStack cardStack = new CardStack();
		cardStack.cards.addAll(newDeck());
		return cardStack;
	}

	public static CardStack withShuffledDeck(Random random) {
		CardStack cardStack = new CardStack();

		List<Card> freshCards = newDeck();
		Collections.shuffle(freshCards, random);
		cardStack.cards.addAll(freshCards);

		return cardStack;
	}

	public CardStack() {
		cards = new ArrayDeque<>();
	}

	private CardStack(Collection<Card> cards) {
		this.cards = new ArrayDeque<>(cards);
	}

	public CardStack copy() {
		return new CardStack(this.cards);
	}


	public void push(Card card) {
		cards.addFirst(card);
	}

	public Card pop() {
		return cards.removeFirst();
	}

	public Card peek() {
		return cards.getFirst();
	}


	public void addAll(CardStack stack) {
		this.cards.addAll(stack.cards);
	}

	public void clear() {
		cards.clear();
	}


	public int size() {
		return cards.size();
	}

	public boolean empty() {
		return cards.isEmpty();
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CardStack cardStack))
			return false;

		if (this.cards.size() != cardStack.cards.size())
			return false;

		Iterator<Card> it1 = this.cards.iterator();
		Iterator<Card> it2 = cardStack.cards.iterator();

		while (it1.hasNext())
			if (!it1.next().equals(it2.next()))
				return false;

		return true;
	}


	private static List<Card> newDeck() {
		return Arrays.asList(
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
				new Card(Card.Suit.CLUBS, Card.Face.KING)
		);
	}
}
