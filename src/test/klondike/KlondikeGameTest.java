package klondike;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universal.Card;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class KlondikeGameTest {
	private KlondikeGame klondike;


	@BeforeEach
	void setup() {
		klondike = new KlondikeGame();
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
		for (Stack<Card> column : klondike.tableaux) {
			assertTrue(column.peek().facingUp());
		}
	}

	@Test
	void testBuriedTableauxCardsFaceDown() {
		// destructive, hence @BeforeEach
		for (Stack<Card> column : klondike.tableaux) {
			// remove the top card
			column.pop();
			// the next one should be flipped over, if there is a next one
			boolean emptyOrFacingDown = column.empty() || !column.peek().facingUp();
			assertTrue(emptyOrFacingDown);
		}
	}


	@Test
	void testStockSize() {
		int expected = 24;
		int actual = klondike.stock.size();
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
		assertFalse(klondike.stock.peek().facingUp());
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
	void testEachFoundationIsEmpty() {
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
		KlondikeGame popMeForBadCards = new KlondikeGame(1);

		KlondikeGame klondikeBadStock = new KlondikeGame(0);
		klondikeBadStock.stock = new Stack<>();
		assertNotEquals(klondikeGood, klondikeBadStock);

		KlondikeGame klondikeBadWaste = new KlondikeGame(0);
		klondikeBadWaste.wastePile.add(popMeForBadCards.stock.pop());
		assertNotEquals(klondikeGood, klondikeBadWaste);

		KlondikeGame klondikeBadFoundations = new KlondikeGame(0);
		klondikeBadFoundations.foundations.getFirst().add(popMeForBadCards.stock.pop());
		assertNotEquals(klondikeGood, klondikeBadFoundations);
	}
}
