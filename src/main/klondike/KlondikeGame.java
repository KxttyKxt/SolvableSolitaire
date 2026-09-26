package klondike;

import universal.Card;
import universal.Deck;

import java.util.*;

public class KlondikeGame {
	long seed;
	Random random;
	Stack<Card> deck;

	Stack<Card> stock;
	Stack<Card> wastePile;

	List<Stack<Card>> foundations;
	List<Stack<Card>> tableau;

	public KlondikeGame(long seed) {
		this.seed = seed;
		this.random = new Random(seed);
		this.deck = Deck.shuffleCards(Deck.newDeck(), random);

		this.stock = new Stack<>();
		this.wastePile = new Stack<>();

		this.foundations = new ArrayList<>();
		foundations.add(new Stack<>());
		foundations.add(new Stack<>());
		foundations.add(new Stack<>());
		foundations.add(new Stack<>());

		for (int i = 1; i <= 7; i++) {
			tableau.add(new Stack<>());
			Stack<Card> current = tableau.get(i - 1);

			for (int j = 0; j < i; j++) {
				current.add(deck.pop());
			}

			current.peek().flip();
		}

		while (!deck.isEmpty()) {
			stock.add(deck.pop());
		}


	}

	public KlondikeGame() {
		long seed = System.currentTimeMillis();
		this(seed);
	}
}
