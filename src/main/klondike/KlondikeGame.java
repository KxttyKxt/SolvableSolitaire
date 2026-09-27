package klondike;

import core.Card;
import core.Deck;

import java.util.*;

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
	Deque<Card> stockPile;
	Deque<Card> wastePile;
	List<Deque<Card>> foundations;
	List<List<Card>> tableaux;

	public KlondikeGame(long seed) {
		this.seed = seed;
		Random random = new Random(seed);
		Deque<Card> deck = Deck.shuffledDeck(random);

		this.stockPile = new ArrayDeque<>();
		this.wastePile = new ArrayDeque<>();

		this.foundations = new ArrayList<>(4);
		foundations.add(new ArrayDeque<>());
		foundations.add(new ArrayDeque<>());
		foundations.add(new ArrayDeque<>());
		foundations.add(new ArrayDeque<>());

		tableaux = new ArrayList<>(7);
		for (int i = 1; i <= 7; i++) {
			tableaux.add(new ArrayList<>());
			List<Card> current = tableaux.get(i - 1);

			for (int j = 0; j < i; j++)
				current.addFirst(deck.removeFirst());

			// flip top card face-up
			current.getFirst().flip();
		}

		while (!deck.isEmpty())
			stockPile.addFirst(deck.removeFirst());
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

		copy.stockPile = new ArrayDeque<>();
		copy.stockPile.addAll(this.stockPile);
		copy.wastePile = new ArrayDeque<>();
		copy.wastePile.addAll(this.wastePile);
		copy.foundations = new ArrayList<>(4);
		for (int i = 0; i < 4; i++) {
			copy.foundations.add(new ArrayDeque<>());
			copy.foundations.get(i).addAll(this.foundations.get(i));
		}
		copy.tableaux = new ArrayList<>(7);
		for (int i = 0; i < 7; i++) {
			copy.tableaux.add(new ArrayList<>());
			copy.tableaux.get(i).addAll(this.tableaux.get(i));
		}

		return copy;
	}


	// *** actionable methods ***
	public void drawFromStockPile() {
		if (!stockPile.isEmpty()) {
			wastePile.addFirst(stockPile.removeFirst());
			wastePile.getFirst().flip();
		}
	}

	public void recycleWasteIntoStock() {
		if (stockPile.isEmpty())
			while (!wastePile.isEmpty())
				stockPile.addFirst(wastePile.removeFirst());
	}

	/// @return true if the stock pile card was moved to a foundation,
	/// 	or false if no foundation can accept the stock pile card.
	public boolean moveWasteCardToAFoundation() {
		if (wastePile.isEmpty())
			return false;

		int index = findAcceptableFoundationForCard(wastePile.getFirst());
		if (index == -1)
			return false;

		foundations.get(index).addFirst(wastePile.removeFirst());
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
		if (foundations.get(foundationIndex).isEmpty())
			return cardToAccept.face() == Card.Face.ACE;

		Card topCard = foundations.get(foundationIndex).getFirst();

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

		// foundations is a difficult check
		boolean allButFoundations = this.seed == klondike.seed
				&& Deck.equals(this.stockPile, klondike.stockPile)
				&& Deck.equals(this.wastePile, klondike.wastePile)
				&& this.tableaux.equals(klondike.tableaux);

		if (!allButFoundations)
			return false;

		for (int i = 0; i < 4; i++) {
			if (!Deck.equals(
					this.foundations.get(i),
					klondike.foundations.get(i)
			))
				return false;
		}

		return true;
	}
}
