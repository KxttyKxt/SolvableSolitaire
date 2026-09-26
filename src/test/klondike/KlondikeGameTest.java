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
}