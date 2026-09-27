package klondike;

import core.Card;
import core.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/// Klondike is the most typical form of Solitaire, especially in America.
/// Solitaire is actually a tabletop blanket genre, I have learned,
/// but when most people say Solitaire, they mean Klondike.
///
/// Klondike involves:
/// - a stock (draw pile)
/// - a waste pile (where drawn cards go)
/// - four "foundation" piles, and
/// - seven "tableau" columns
///
/// The goal is to clear all tableaux by placing the cards into the foundation piles.
/// Foundations are empty piles that must follow a suit sequentially from ace to king.
/// Cards placed into foundations can come from the tableaux or from the waste pile.
/// Cards can also be moved between tableaux,
/// but when moving a card or cards onto a tableau, the bottom card must be:
/// 1. a different color than the card it is placed onto, and
/// 2. one number/value above or below the card it is placed onto
///
/// Guide used for reference -
/// [_What Is Klondike Solitaire: The Definitive Guide_](https://thesolitaire.com/blog/what-is-klondike-solitaire/)
public class KlondikeGame {
	/// stored for informational purposes.
	/// having a seed for shuffling the deck makes the game deterministic and
	/// replicable.
	public final long seed;

	// piles
	Stack<Card> stockPile;
	Stack<Card> wastePile;
	List<Stack<Card>> foundations;
	List<Stack<Card>> tableaux;

	public KlondikeGame(long seed) {
		this.seed = seed;
		Random random = new Random(seed);
		Stack<Card> deck = Deck.shuffleCards(Deck.newDeck(), random);

		this.stockPile = new Stack<>();
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
			stockPile.add(deck.pop());
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

		copy.stockPile = new Stack<>();
		copy.stockPile.addAll(this.stockPile);
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


	// *** actionable methods ***
	public void drawFromStockPile() {
		if (!stockPile.empty()) {
			wastePile.add(stockPile.pop());
			wastePile.peek().flip();
		}
	}

	public void recycleWasteIntoStock() {
		if (stockPile.empty())
			while (!wastePile.empty())
				stockPile.add(wastePile.pop());
	}

	/// @return true if the stock pile card was moved to a foundation,
	/// 	or false if no foundation can accept the stock pile card.
	public boolean moveWasteCardToAFoundation() {
		if (wastePile.empty())
			return false;

		int index = findAcceptableFoundationForCard(wastePile.peek());
		if (index == -1)
			return false;

		foundations.get(index).add(wastePile.pop());
		return true;
	}


	// *** helper methods ***
	private int findAcceptableFoundationForCard(Card cardToAccept) {
		for (int i = 0; i < 4; i++)
			if (foundationCanAcceptCard(cardToAccept, i))
				return i;

		return -1;
	}

	private boolean foundationCanAcceptCard(Card cardToAccept, int foundationIndex) {
		if (foundations.get(foundationIndex).empty())
			return cardToAccept.face() == Card.Face.ACE;

		Card topCard = foundations.get(foundationIndex).peek();

		if (cardToAccept.suit() != topCard.suit())
			return false;
		else
			return cardToAccept.face().value == topCard.face().value + 1;
	}


	// *** overrides ***
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof KlondikeGame klondike))
			return false;

		return this.seed == klondike.seed
				&& this.stockPile.equals(klondike.stockPile)
				&& this.wastePile.equals(klondike.wastePile)
				&& this.foundations.equals(klondike.foundations)
				&& this.tableaux.equals(klondike.tableaux);
	}
}
