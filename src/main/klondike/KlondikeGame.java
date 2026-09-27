package klondike;

import core.Card;
import core.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class KlondikeGame {
	// stored for informational purposes
	public final long seed;

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

	/// similarly to [clone()][Object#clone()],
	/// this creates and returns a deep copy of the game in its current state.
	///
	/// particularly useful for algorithmically solving a game
	/// when there are multiple possible moves.
	public KlondikeGame copy() {
		KlondikeGame copy = new KlondikeGame(this.seed);

		copy.stock = new Stack<>();
		copy.stock.addAll(this.stock);
		copy.wastePile = new Stack<>();
		copy.wastePile.addAll(this.wastePile);
		copy.foundations = new ArrayList<>(4);
		for (int i = 0; i < 4; i++) {
			copy.foundations.add(new Stack<>());
			copy.foundations.get(i).addAll(this.foundations.get(i));
		}
		copy.tableaux = new ArrayList<>(7);
		for (int i = 0; i < 7; i++) {
			copy.tableaux.add(new Stack<>());
			copy.tableaux.get(i).addAll(this.tableaux.get(i));
		}

		return copy;
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
