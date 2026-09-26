package klondike;

import core.Card;
import core.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class KlondikeGame {
	// for informational purposes
	long seed;

	// piles
	Stack<Card> stock;
	Stack<Card> wastePile;
	List<Stack<Card>> foundations;
	List<Stack<Card>> tableaux;

	public KlondikeGame(long seed) {
		this.seed = seed;
		Random random = new Random(seed);
		Stack<Card> deck = Deck.shuffleCards(Deck.newDeck(), random);

		this.stock = new Stack<>();
		this.wastePile = new Stack<>();

		this.foundations = new ArrayList<>(4);
		foundations.add(new Stack<>());
		foundations.add(new Stack<>());
		foundations.add(new Stack<>());
		foundations.add(new Stack<>());

		tableaux = new ArrayList<>(7);
		for (int i = 1; i <= 7; i++) {
			tableaux.add(new Stack<>());
			Stack<Card> current = tableaux.get(i - 1);

			for (int j = 0; j < i; j++)
				current.add(deck.pop());

			// flip top card face-up
			current.peek().flip();
		}

		while (!deck.isEmpty())
			stock.add(deck.pop());
	}

	public KlondikeGame() {
		long seed = System.currentTimeMillis();
		this(seed);
	}


	public void drawFromStock() {
		if (!stock.empty()) {
			wastePile.add(stock.pop());
			wastePile.peek().flip();
		}
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof KlondikeGame klondike))
			return false;

		return this.seed == klondike.seed
				&& this.stock.equals(klondike.stock)
				&& this.wastePile.equals(klondike.wastePile)
				&& this.foundations.equals(klondike.foundations)
				&& this.tableaux.equals(klondike.tableaux);
	}
}
