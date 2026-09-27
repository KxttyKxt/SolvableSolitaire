package klondike;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import core.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class KlondikeGameTest {
	private static KlondikeGame klondike;
	private static List<Card> aces;

	@BeforeEach
	void setup() {
		klondike = new KlondikeGame();

		// before each because game actions are destructive
		aces = List.of(
				new Card(Card.Suit.HEARTS, Card.Face.ACE),
				new Card(Card.Suit.DIAMONDS, Card.Face.ACE),
				new Card(Card.Suit.SPADES, Card.Face.ACE),
				new Card(Card.Suit.CLUBS, Card.Face.ACE)
		);
	}


	private static <T> Stack<T> copyStack(Stack<T> stack) {
		Stack<T> copy = new Stack<>();
		copy.addAll(stack);
		return copy;
	}

	private static boolean quickMove(Card card) {
		klondike.stockPile.add(card);
		klondike.drawFromStockPile();
		return klondike.moveWasteCardToAFoundation();
	}

	<T> void assertEmpty(Collection<T> collection) {
		assertTrue(collection.isEmpty());
	}


	@Test
	void testTableauxSize() {
		int expected = 7;
		int actual = klondike.tableaux.size();

		String failureMessage = String.format(
				"Klondike should have %d tableau columns, but has %d.",
				expected,
				actual
		);
		assertEquals(expected, actual, failureMessage);
	}

	@Test
	void testTableauxColumnSizes() {
		// for-loop becomes less readable
		assertTableauColumnSize(klondike, 0);
		assertTableauColumnSize(klondike, 1);
		assertTableauColumnSize(klondike, 2);
		assertTableauColumnSize(klondike, 3);
		assertTableauColumnSize(klondike, 4);
		assertTableauColumnSize(klondike, 5);
		assertTableauColumnSize(klondike, 6);
	}
	private void assertTableauColumnSize(KlondikeGame klondike, int index) {
		int expected = index + 1;
		int actual = klondike.tableaux.get(index).size();

		String failureMessage = String.format(
				"Klondike tableau column %d should have %d cards, but has %d.",
				index,
				expected,
				actual
		);
		assertEquals(expected, actual, failureMessage);
	}

	@Test
	void testTableauxTopCardsFaceUp() {
		for (List<Card> column : klondike.tableaux)
			assertTrue(column.getFirst().facingUp());
	}

	@Test
	void testBuriedTableauxCardsFaceDown() {
		// destructive, hence @BeforeEach
		for (List<Card> column : klondike.tableaux) {
			// remove the top card
			column.removeFirst();
			// the next one should be flipped over, if there is a next one
			boolean notFaceUp = column.isEmpty() || !column.getFirst().facingUp();
			assertTrue(notFaceUp);
		}
	}


	@Test
	void testStockSize() {
		int expected = 24;
		int actual = klondike.stockPile.size();
		String failureMessage = String.format(
				"Expected stock size is %d, but actual stock size is %d.",
				expected,
				actual
		);
		assertEquals(expected, actual, failureMessage);
	}

	@Test
	void testStockFacesDown() {
		// unlike the tableaux, stock should always face down
		assertFalse(klondike.stockPile.peek().facingUp());
	}


	@Test
	void testWastePileStartsEmpty() {
		assertTrue(
				klondike.wastePile.empty(),
				"Klondike waste pile not empty."
		);
	}


	@Test
	void testFoundationsSize() {
		int expected = 4;
		int actual = klondike.foundations.size();
		String failureMessage = String.format(
				"Expected %d foundations, found %d",
				expected,
				actual
		);
		assertEquals(expected, actual, failureMessage);
	}

	@Test
	void testEachFoundationStartsEmpty() {
		for (int i = 0; i < 4; i++) {
			assertTrue(
					klondike.foundations.get(i).empty(),
					String.format("Klondike foundation %d not empty.", i)
			);
		}
	}


	@Test
	void testSameSeedKlondikesAreEqual() {
		KlondikeGame klondike1 = new KlondikeGame(0);
		KlondikeGame klondike2 = new KlondikeGame(0);
		assertEquals(klondike1, klondike2);
	}

	@Test
	void testDiffSeedKlondikesAreNotEqual() {
		KlondikeGame klondike1 = new KlondikeGame(0);
		KlondikeGame klondike2 = new KlondikeGame(1);
		assertNotEquals(klondike1, klondike2);
	}

	@Test
	void klondikeAndObjAreNotEqual() {
		// I am specifically testing the first clause of Klondike#equals()
		//noinspection MisorderedAssertEqualsArguments
		assertNotEquals(klondike, new Object());
	}

	@Test
	void deepTestKlondikesAreNotEqual() {
		KlondikeGame klondikeGood = new KlondikeGame(0);
		KlondikeGame popDeck = new KlondikeGame(1);

		KlondikeGame klondikeBadStock = new KlondikeGame(0);
		klondikeBadStock.stockPile = new Stack<>();
		assertNotEquals(klondikeGood, klondikeBadStock);

		KlondikeGame klondikeBadWaste = new KlondikeGame(0);
		klondikeBadWaste.wastePile.add(popDeck.stockPile.pop());
		assertNotEquals(klondikeGood, klondikeBadWaste);

		KlondikeGame klondikeBadFoundations = new KlondikeGame(0);
		klondikeBadFoundations.foundations.getFirst().add(popDeck.stockPile.pop());
		assertNotEquals(klondikeGood, klondikeBadFoundations);
	}


	@Test
	void testDrawStock() {
		Card toDraw = klondike.stockPile.peek();
		assertEmpty(klondike.wastePile);
		klondike.drawFromStockPile();

		// intentional identity checks
		boolean drawnCardIsInWastePile = klondike.wastePile.peek() == toDraw;
		assertTrue(drawnCardIsInWastePile);
		boolean drawnCardIsNotInStock = klondike.stockPile.peek() != toDraw;
		assertTrue(drawnCardIsNotInStock);
	}

	@Test
	void testDrawStockButStockIsEmpty() {
		klondike.stockPile.clear();
		// no EmptyStackException
		assertDoesNotThrow(() -> klondike.drawFromStockPile());
		// wastePile is the same
		assertEmpty(klondike.wastePile);
	}


	@Test
	void testRecycleDoesNothingWhenStockIsNotEmpty() {
		// arbitrarily add some cards to the waste pile
		klondike.drawFromStockPile();
		klondike.drawFromStockPile();

		// deep-copy stacks for reference
		Stack<Card> stockBefore = copyStack(klondike.stockPile);
		Stack<Card> wasteBefore = copyStack(klondike.wastePile);

		// this should not do anything
		klondike.recycleWasteIntoStock();

		// assert no effect
		assertEquals(stockBefore, klondike.stockPile);
		assertEquals(wasteBefore, klondike.wastePile);
	}

	@Test
	void testRecycleDoesNothingWhenWasteIsEmpty() {
		// deep-copy stock pile for reference
		Stack<Card> stockBefore = copyStack(klondike.stockPile);
		assertEmpty(klondike.wastePile);

		// this should not do anything
		klondike.recycleWasteIntoStock();

		// assert no effect
		assertEquals(stockBefore, klondike.stockPile);
		assertEmpty(klondike.wastePile);
	}

	@Test
	void testRecycleWorksCorrectly() {
		Stack<Card> initialStockPile = copyStack(klondike.stockPile);

		// empty out stock pile into waste pile
		while (!klondike.stockPile.empty()) {
			klondike.drawFromStockPile();
		}
		assertEmpty(klondike.stockPile);

		klondike.recycleWasteIntoStock();
		assertEquals(initialStockPile, klondike.stockPile);
		assertEmpty(klondike.wastePile);
	}


	@Test
	void testMoveWasteAceToFirstFoundation() {
		Card card = new Card(Card.Suit.HEARTS, Card.Face.ACE);
		Stack<Card> firstFoundation = klondike.foundations.getFirst();

		assertEmpty(firstFoundation);

		// there's a helper for this, but I want to test its functionality
		// directly before relying on it
		klondike.stockPile.add(card);
		klondike.drawFromStockPile();
		boolean success = klondike.moveWasteCardToAFoundation();

		assertTrue(success);

		assertEmpty(klondike.wastePile);
		assertSame(card, firstFoundation.peek());
		assertEquals(1, firstFoundation.size());
	}

	@Test
	void testMoveWasteCardToAcedFirstFoundation() {
		quickMove(new Card(Card.Suit.HEARTS, Card.Face.ACE));
		boolean success = quickMove(new Card(Card.Suit.HEARTS, Card.Face.TWO));
		assertTrue(success);
		assertEquals(2, klondike.foundations.getFirst().size());
	}

	@Test
	void testMoveWasteAllAces() {
		for (Card ace : aces)
			assertTrue(quickMove(ace));

		List<Card.Suit> checkedSuits = new ArrayList<>(4);
		for (Stack<Card> foundation : klondike.foundations) {
			assertEquals(1, foundation.size());
			Card card = foundation.peek();

			// make sure all cards are aces
			assertEquals(Card.Face.ACE, card.face());

			// make sure all suits are unique
			assertFalse(checkedSuits.contains(card.suit()));
			checkedSuits.add(card.suit());
		}
	}

	@Test
	void testMoveWasteCardWhenWastePileIsEmpty() {
		assertEmpty(klondike.wastePile);
		boolean success = klondike.moveWasteCardToAFoundation();
		assertFalse(success);
	}

	@Test
	void testMoveWasteCardOfIncorrectSuitButCorrectValue() {
		Card aceOfSpades = new Card(Card.Suit.SPADES, Card.Face.ACE);

		boolean success = quickMove(aceOfSpades);
		assertTrue(success);

		Card twoOfHearts = new Card(Card.Suit.HEARTS, Card.Face.TWO);
		klondike.stockPile.add(twoOfHearts);
		klondike.drawFromStockPile();

		success = klondike.moveWasteCardToAFoundation();
		assertFalse(success);
		assertEquals(1, klondike.wastePile.size());
	}

	@Test
	void testMoveWasteCardOfCorrectSuitButIncorrectValue() {
		boolean success = quickMove(new Card(Card.Suit.SPADES, Card.Face.ACE));
		assertTrue(success);

		Card threeOfSpades = new Card(Card.Suit.SPADES, Card.Face.THREE);
		success = quickMove(threeOfSpades);
		assertFalse(success);
	}


	@Test
	// since Klondike#copy() makes a deep copy,
	// that copy should fulfill the same conventions as `Object.clone()`.
	void testCopyFollowsCloneConventions() {
		KlondikeGame copy = klondike.copy();
		assertEquals(copy.getClass(), klondike.getClass());
		assertNotSame(copy, klondike);
		assertEquals(copy, klondike);
	}
}
