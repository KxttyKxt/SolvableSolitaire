package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
	private static Card card;


	@BeforeEach
	void setUpCards() {
		card = new Card();
	}


	@Test
	void testMakeImmutableChangesFlag() {
		assertFalse(card.isImmutable());
		Card madeImmutable = card.makeImmutable();
		assertTrue(madeImmutable.isImmutable());
	}

	@Test
	void testFlippingAnImmutableCardThrowsException() {
		Card immutableCard = card.makeImmutable();
		assertThrows(UnsupportedOperationException.class, immutableCard::flip);
	}

	@Test
	void facingUpAffectsFace() {
		// card faces down by default
		assertNull(card.face());
		card.flip();
		assertEquals(Card.Face.ACE, card.face());
	}

	@Test
	void facingUpAffectsSuit() {
		// card faces down by default
		assertNull(card.suit());
		card.flip();
		assertEquals(Card.Suit.HEARTS, card.suit());
	}

	@Test
	void testFlippingCard() {
		boolean initialFacingUpState = card.facingUp();
		card.flip();

		boolean expected = !initialFacingUpState;
		boolean actual = card.facingUp();
		assertEquals(expected, actual);
	}


	@Test
	void testEqualCardsAreEqual() {
		Card card1 = new Card(Card.Suit.HEARTS, Card.Face.ACE);
		Card card2 = new Card(Card.Suit.HEARTS, Card.Face.ACE);
		assertEquals(card1, card2);
	}

	@Test
	void testNotEqualCardsHaveDifferentFaces() {
		Card card1 = new Card(Card.Suit.HEARTS, Card.Face.ACE);
		Card card2 = new Card(Card.Suit.HEARTS, Card.Face.TWO);
		assertNotEquals(card1, card2);
	}

	@Test
	void testNotEqualCardsHaveDifferentSuits() {
		Card card1 = new Card(Card.Suit.HEARTS, Card.Face.ACE);
		Card card2 = new Card(Card.Suit.DIAMONDS, Card.Face.ACE);
		assertNotEquals(card1, card2);
	}

	@Test
	void testNotEqualCardsFaceDifferentWays() {
		Card card1 = new Card(Card.Suit.HEARTS, Card.Face.ACE);
		Card card2 = new Card(Card.Suit.HEARTS, Card.Face.ACE, true);
		assertNotEquals(card1, card2);
	}

	@Test
	void testNotEqualCompletelyDifferentObject() {
		Object obj = 1;
		// I'm specifically testing the `instanceof` condition in Card#equals()
		//noinspection MisorderedAssertEqualsArguments
		assertNotEquals(card, obj);
	}


	@Test
	void testCardToStringWhetherFlipped() {
		String expected = "[..]";
		String actual = card.toString();
		assertEquals(expected, actual);

		card.flip();

		expected = "[♥A]";
		actual = card.toString();
		assertEquals(expected, actual);
	}
}