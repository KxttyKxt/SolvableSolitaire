package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CardStackTest {
	CardStack stack1;
	CardStack stack2;

	@BeforeEach
	void setup() {
		stack1 = CardStack.withNewDeck();
		stack2 = CardStack.withShuffledDeck(new Random());
	}


	@Test
	void testEqualsNewDecks() {
		CardStack stack3 = CardStack.withNewDeck();
		assertEquals(stack1, stack3);
	}

	@Test
	void testEqualsShuffledDecks() {
		CardStack random1 = CardStack.withShuffledDeck(new Random(0));
		CardStack random2 = CardStack.withShuffledDeck(new Random(0));
		assertEquals(random1, random2);
	}

	@Test
	void testEqualsCopy() {
		CardStack stack3 = stack1.copy();
		assertEquals(stack1, stack3);
	}

	@Test
	void testNotEqualsDifferentCardStacks() {
		assertNotEquals(stack1, stack2);
	}

	@Test
	void testNotEqualsDifferentRandoms() {
		CardStack random1 = CardStack.withShuffledDeck(new Random(0));
		CardStack random2 = CardStack.withShuffledDeck(new Random(1));
		assertNotEquals(random1, random2);
	}

	@Test
	void notEqualsDifferentSizes() {
		stack1.pop();
		assertNotEquals(stack1, stack2);
	}

	@Test
	void testNotEqualsObject() {
		// order needed to test `CardStack#equals()`
		//noinspection MisorderedAssertEqualsArguments
		assertNotEquals(stack1, new Object());
	}
}