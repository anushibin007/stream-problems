package org.projects.assignments.l5;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class L5AssignmentTest {

	L5Assignment l5Assignment = new L5Assignment();

	// Test for Question 1: Sum of Squares Parallel
	@Test
	public void testSumOfSquaresParallel() {
		List<Integer> input = List.of(1, 2, 3, 4, 5);
		int expected = 55;
		int result = l5Assignment.sumOfSquaresParallel(input);
		assertEquals(expected, result);
	}

	// Test for Question 2: Find First Even Or Default
	@Test
	public void testFindFirstEvenOrDefault() {
		List<Integer> input = List.of(1, 3, 5, 8, 9);
		int expected = 8;
		int result = l5Assignment.findFirstEvenOrDefault(input);
		assertEquals(expected, result);
	}

	@Test
	public void testFindFirstEvenOrDefaultNoMatch() {
		List<Integer> input = List.of(1, 3, 5, 7, 9);
		int expected = -1;
		int result = l5Assignment.findFirstEvenOrDefault(input);
		assertEquals(expected, result);
	}

	// Test for Question 3: Find Max Or Throw
	@Test
	public void testFindMaxOrThrow() {
		List<Integer> input = List.of(10, 5, 20, 15);
		int expected = 20;
		int result = l5Assignment.findMaxOrThrow(input);
		assertEquals(expected, result);
	}

	@Test
	public void testFindMaxOrThrowEmptyList() {
		List<Integer> input = List.of();
		assertThrows(NoSuchElementException.class, () -> l5Assignment.findMaxOrThrow(input));
	}

	// Test for Question 4: Sum From One To N
	@Test
	public void testSumFromOneToN() {
		int input = 10;
		int expected = 55;
		int result = l5Assignment.sumFromOneToN(input);
		assertEquals(expected, result);
	}

	@Test
	public void testSumFromOneToNSmall() {
		int input = 5;
		int expected = 15;
		int result = l5Assignment.sumFromOneToN(input);
		assertEquals(expected, result);
	}

	// Test for Question 5: Generate First N Even Numbers
	@Test
	public void testGenerateFirstNEvenNumbers() {
		int input = 5;
		List<Integer> expected = List.of(0, 2, 4, 6, 8);
		List<Integer> result = l5Assignment.generateFirstNEvenNumbers(input);
		assertEquals(expected, result);
	}

	// Test for Question 6: Sum of String Lengths
	@Test
	public void testSumOfStringLengths() {
		List<String> input = List.of("hello", "world", "java");
		int expected = 14;
		int result = l5Assignment.sumOfStringLengths(input);
		assertEquals(expected, result);
	}

	// Test for Question 7: IntStream To List
	@Test
	public void testIntStreamToList() {
		int start = 1;
		int end = 5;
		List<Integer> expected = List.of(1, 2, 3, 4, 5);
		List<Integer> result = l5Assignment.intStreamToList(start, end);
		assertEquals(expected, result);
	}

	// Test for Question 8: Peek And Square
	@Test
	public void testPeekAndSquare() {
		List<Integer> input = List.of(1, 2, 3);
		List<Integer> expected = List.of(1, 4, 9);
		List<Integer> result = l5Assignment.peekAndSquare(input);
		assertEquals(expected, result);
	}

	// Test for Question 9: Has No Negatives
	@Test
	public void testHasNoNegatives() {
		List<Integer> input = List.of(1, 2, 3, 4, 5);
		boolean expected = true;
		boolean result = l5Assignment.hasNoNegatives(input);
		assertEquals(expected, result);
	}

	@Test
	public void testHasNoNegativesWithNegative() {
		List<Integer> input = List.of(1, -2, 3, 4, 5);
		boolean expected = false;
		boolean result = l5Assignment.hasNoNegatives(input);
		assertEquals(expected, result);
	}

	// Test for Question 10: Find Any Starting With J
	@Test
	public void testFindAnyStartingWithJ() {
		List<String> input = List.of("Alice", "Bob", "John", "Jane", "Charlie");
		String result = l5Assignment.findAnyStartingWithJ(input);
		assertTrue(result.equals("John") || result.equals("Jane"));
	}

	// Test for Question 11: Stream To Array
	@Test
	public void testStreamToArray() {
		List<String> input = List.of("apple", "banana", "cherry");
		String[] expected = { "apple", "banana", "cherry" };
		String[] result = l5Assignment.streamToArray(input);
		assertArrayEquals(expected, result);
	}

	// Test for Question 12: Concatenate With Reduce
	@Test
	public void testConcatenateWithReduce() {
		List<String> input = List.of("Java", "is", "fun");
		String expected = "Java is fun";
		String result = l5Assignment.concatenateWithReduce(input);
		assertEquals(expected, result);
	}

	// Test for Question 13: Sum And Count
	@Test
	public void testSumAndCount() {
		List<Integer> input = List.of(10, 20, 30, 40);
		Map<String, Integer> expected = Map.of("sum", 100, "count", 4);
		Map<String, Integer> result = l5Assignment.sumAndCount(input);
		assertEquals(result.get("sum"), expected.get("sum"));
		assertEquals(result.get("count"), expected.get("count"));
	}

	// Test for Question 14: Group And Filter
	@Test
	public void testGroupAndFilter() {
		List<Integer> input = List.of(1, 2, 6, 7, 8, 10, 3);
		Map<Boolean, List<Integer>> expected = Map.of(false, List.of(7), true, List.of(6, 8, 10));
		Map<Boolean, List<Integer>> result = l5Assignment.groupAndFilter(input);
		assertEquals(expected.get(false), result.get(false));
		assertEquals(expected.get(true), result.get(true));
	}

	// Test for Question 15: Group By First Char And Map To Length
	@Test
	public void testGroupByFirstCharAndMapToLength() {
		List<String> input = List.of("apple", "apricot", "banana", "cherry");
		Map<Character, List<Integer>> expected = Map.of('a', List.of(5, 7), 'b', List.of(6), 'c', List.of(6));
		Map<Character, List<Integer>> result = l5Assignment.groupByFirstCharAndMapToLength(input);
		assertEquals(expected, result);
	}

	// Test for Question 16: Get Statistics
	@Test
	public void testGetStatistics() {
		List<Integer> input = List.of(10, 20, 30, 40, 50);
		IntSummaryStatistics result = l5Assignment.getStatistics(input);
		assertEquals(result.getCount(), 5);
		assertEquals(result.getSum(), 150);
		assertEquals(result.getMin(), 10);
		assertEquals(result.getMax(), 50);
		assertEquals(result.getAverage(), 30.0, 0.01);
	}

	// Test for Question 17: Group By Length Unmodifiable
	@Test
	public void testGroupByLengthUnmodifiable() {
		List<String> input = List.of("cat", "dog", "apple", "kiwi");
		Map<Integer, List<String>> result = l5Assignment.groupByLengthUnmodifiable(input);
		assertEquals(result.get(3), List.of("cat", "dog"));
		assertEquals(result.get(4), List.of("kiwi"));
		assertEquals(result.get(5), List.of("apple"));
	}

	@Test
	public void testGroupByLengthUnmodifiableThrowsException() {
		List<String> input = List.of("cat", "dog");
		Map<Integer, List<String>> result = l5Assignment.groupByLengthUnmodifiable(input);
		assertThrows(UnsupportedOperationException.class, () -> result.put(10, List.of("test")));
	}

	// Test for Question 18: Flatten And Sum
	@Test
	public void testFlattenAndSum() {
		List<List<Integer>> input = List.of(List.of(1, 2), List.of(3, 4), List.of(5));
		int expected = 15;
		int result = l5Assignment.flattenAndSum(input);
		assertEquals(expected, result);
	}

	// Test for Question 19: Multi Level Grouping
	@Test
	public void testMultiLevelGrouping() {
		List<String> input = List.of("apple", "apricot", "cat", "dog", "kiwi");
		Map<Integer, Map<Character, Long>> result = l5Assignment.multiLevelGrouping(input);
		assertEquals(result.get(3).get('c'), Long.valueOf(1));
		assertEquals(result.get(3).get('d'), Long.valueOf(1));
		assertEquals(result.get(4).get('k'), Long.valueOf(1));
		assertEquals(result.get(5).get('a'), Long.valueOf(1));
		assertEquals(result.get(7).get('a'), Long.valueOf(1));
	}

	// Test for Question 20: Custom Collector With StringBuilder
	@Test
	public void testCustomCollectorWithStringBuilder() {
		List<String> input = List.of("Hello", "World", "Java");
		String expected = "Hello | World | Java";
		String result = l5Assignment.customCollectorWithStringBuilder(input, " | ");
		assertEquals(expected, result);
	}

	@Test
	public void testCustomCollectorWithStringBuilderDifferentSeparator() {
		List<String> input = List.of("One", "Two", "Three");
		String expected = "One - Two - Three";
		String result = l5Assignment.customCollectorWithStringBuilder(input, " - ");
		assertEquals(expected, result);
	}
}
