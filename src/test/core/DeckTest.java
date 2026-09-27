package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
	Deque<Card> deck1;
	Deque<Card> deck2;

	@BeforeEach
	void setup() {
		deck1 = Deck.newDeck();
		deck2 = Deck.shuffledDeck(new Random());
	}


	@Test
	void testEquals() {
		Deque<Card> deck3 = Deck.newDeck();
		assertArrayEquals(deck1.toArray(), deck3.toArray());
		assertTrue(Deck.equals(deck1, deck3));
	}

	@Test
	void testNotEqualsDifferentDecks() {
		assertFalse(Arrays.equals(deck1.toArray(), deck2.toArray()));
		assertFalse(Deck.equals(deck1, deck2));
	}

	@Test
	void notEqualsDifferentSizes() {
		deck1.removeFirst();
		assertFalse(Arrays.equals(deck1.toArray(), deck2.toArray()));
		assertFalse(Deck.equals(deck1, deck2));
	}
}