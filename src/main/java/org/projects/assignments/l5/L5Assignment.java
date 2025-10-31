package org.projects.assignments.l5;

import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Advanced Stream API Concepts - L5 Advanced Assignment This class covers
 * advanced stream operations including parallel streams, Optional handling,
 * custom collectors, primitive streams, and more.
 */
public class L5Assignment {

	/**
	 * 1. Parallel Stream Processing - Find Sum of Squares
	 * 
	 * Example: Given a large list of integers, calculate sum of squares using
	 * parallel stream.
	 * 
	 * Example: Numbers: [1, 2, 3, 4, 5]
	 * 
	 * Output: 55
	 * 
	 * Explanation: 1² + 2² + 3² + 4² + 5² = 1 + 4 + 9 + 16 + 25 = 55
	 */
	public int sumOfSquaresParallel(List<Integer> numbers) {

		int sum = numbers //
				.parallelStream() //
				.mapToInt(val -> val * val) //
				.sum();

		return sum;

// 		To understand the caveats of using parallelStream,
//		I have demonstrated this use case below where
//		I want to find the square of 10000 integer values
//		and store them into a list. If we use a normal
//		ArrayList<>, the result would be inconsistent.
//		But if we use a synchronizedList, then concurrency
//		is achieved and we will get the right solution.
//		
//		List<Integer> largeInputSet = new ArrayList<>();
//		IntStream.rangeClosed(1, 10000).forEach(num-> largeInputSet.add(num));
//		
//		List<Integer> squareResult = Collections.synchronizedList(new ArrayList<>());
//		
//		largeInputSet //
//		.parallelStream() //
//		.forEach(val -> squareResult.add(val * val));
//		
//		System.out.println(squareResult.size());
	}

	/**
	 * 2. Optional Handling
	 * 
	 * Find First Even Number or Default
	 * 
	 * Example: Given a list of integers, find the first even number, return -1 if
	 * none found.
	 * 
	 * Example: Numbers: [1, 3, 5, 8, 9]
	 * 
	 * Output: 8
	 * 
	 * Explanation: 8 is the first even number. If no even number exists, return -1.
	 */
	public int findFirstEvenOrDefault(List<Integer> numbers) {

		return numbers //
				.stream() //
				.filter(num -> num % 2 == 0) //
				.findFirst() //
				.orElse(-1);

	}

	/**
	 * 3. Optional with orElseThrow
	 * 
	 * Find Maximum or Throw Exception
	 * 
	 * Example: Given a list of integers, find maximum value or throw exception if
	 * list is empty.
	 * 
	 * Example:
	 * 
	 * Numbers: [10, 5, 20, 15]
	 * 
	 * Output: 20
	 * 
	 * Explanation: Returns max value, throws NoSuchElementException if list is
	 * empty.
	 */
	public int findMaxOrThrow(List<Integer> numbers) {

		return numbers //
				.stream() //
				.mapToInt(Integer::intValue) //
				.max() //
				.orElseThrow();

	}

	/**
	 * 4. IntStream Range
	 * 
	 * Generate Sum of Numbers from 1 to N
	 * 
	 * Example: Given N = 10, calculate sum of all numbers from 1 to 10.
	 * 
	 * N: 10
	 * 
	 * Output: 55
	 * 
	 * Explanation: 1 + 2 + 3 + ... + 10 = 55
	 */
	public int sumFromOneToN(int n) {

		return IntStream //
				.rangeClosed(1, n) //
				.sum();

	}

	/**
	 * 5. Generate Infinite Stream
	 * 
	 * First N Even Numbers
	 * 
	 * Example: Generate first N even numbers starting from 0.
	 * 
	 * Example: N: 5 Output: [0, 2, 4, 6, 8]
	 * 
	 * Explanation: Generate infinite stream, then limit to N elements.
	 */
	public List<Integer> generateFirstNEvenNumbers(int n) {

		// Solution 1
		// Not very good looking soln but gets the job done
		IntStream //
				.rangeClosed(0, Integer.MAX_VALUE) //
				.filter(num -> num % 2 == 0) //
				.limit(n) //
				.boxed() //
				.toList();

		// Solution 2
		// Uses an external counter to maintain the states
		AtomicInteger counter = new AtomicInteger(0);

		IntStream //
					// Generate calls the IntSupplier function
					// every time a new number is needed. And the
					// new number's state is maintained in the counter variable
				.generate(() -> counter.getAndAdd(2)) //
				.limit(n) //
				.boxed() //
				.toList();

		// Solution 3
		// Most efficient solution completely
		// using streams only
		return IntStream //
				// generates a new
				// number whenever iterate
				// is called, based on the given
				// operation
				.iterate(0, x -> x + 2) //
				.limit(n) //
				.boxed() //
				.toList();
	}

	/**
	 * 6. Primitive Stream Conversion
	 * 
	 * Sum of String Lengths
	 * 
	 * Example: Given a list of strings, calculate total sum of all string lengths.
	 * Example: Strings: ["hello", "world", "java"] Output: 14 Explanation: 5 + 5 +
	 * 4 = 14
	 */
	public int sumOfStringLengths(List<String> strings) {

		// Using reduce() operation.
		// Too much verbose!
//		return strings //
//				.stream() //
//				.map(String::length) //
//				.reduce(0, (a, b) -> a + b); //

		// Using the sum() function that we get OOTB
		return strings //
				.stream() //
				.mapToInt(String::length) //
				.sum();

	}

	/**
	 * 7. Boxed Stream
	 * 
	 * Convert IntStream to List
	 * 
	 * Example: Given a range from 1 to 5, convert IntStream to List of Integers.
	 * 
	 * Example: Range: 1 to 5
	 * 
	 * Output: [1, 2, 3, 4, 5]
	 * 
	 * Explanation: Convert primitive IntStream to List<Integer>.
	 */
	public List<Integer> intStreamToList(int start, int end) {

		return IntStream //
				.rangeClosed(start, end) //
				.boxed() //
				.toList();

	}

	/**
	 * 8. Peek for Debugging
	 * 
	 * Print and Transform Example: Given a list of numbers, print each number and
	 * then square them.
	 * 
	 * Example: Numbers: [1, 2, 3]
	 * 
	 * Output: [1, 4, 9] (also prints: Processing: 1, Processing: 2, Processing: 3)
	 * 
	 * Explanation: Print each element before transformation.
	 */
	public List<Integer> peekAndSquare(List<Integer> numbers) {

		return numbers.stream() //
				.peek(val -> System.out.println("Processing: " + val)) //
				.map(val -> val * val) //
				.toList();

	}

	/**
	 * 9. NoneMatch
	 * 
	 * Check if No Negative Numbers exist in the list
	 * 
	 * Example: Given a list of integers, check if there are no negative numbers.
	 * 
	 * Example: Numbers: [1, 2, 3, 4, 5]
	 * 
	 * Output: true
	 * 
	 * Explanation: Returns true if no element is negative, false otherwise.
	 */
	public boolean hasNoNegatives(List<Integer> numbers) {

		return numbers //
				.stream() //
				.noneMatch(num -> num <= 0);

	}

	/**
	 * 10. FindAny with Parallel Stream
	 * 
	 * Example: Given a list of strings, find any string that starts with 'J' using
	 * parallel stream.
	 * 
	 * Example:
	 * 
	 * Strings: ["Alice", "Bob", "John", "Jane", "Charlie"]
	 * 
	 * Output: "John" or "Jane" (any match)
	 * 
	 * Explanation: Returns any matching element.
	 */
	public String findAnyStartingWithJ(List<String> strings) {

		return strings //
				.parallelStream() //
				.filter(name -> name.startsWith("J")) //
				.findAny() //
				.orElse("");

	}

	/**
	 * 11. ToArray - Convert Stream to Array
	 * 
	 * Example: Given a list of strings, convert to array using streams.
	 * 
	 * Example: Strings: ["apple", "banana", "cherry"]
	 * 
	 * Output: ["apple", "banana", "cherry"] as String[] Explanation: Convert stream
	 * to array.
	 */
	public String[] streamToArray(List<String> strings) {

		return strings //
				.stream() //
				.toArray(String[]::new);

	}

	/**
	 * 12. Reducing Collector -
	 * 
	 * Concatenate Strings with Reduce
	 * 
	 * Example: Given a list of strings, concatenate them using reduce.
	 * 
	 * Example: Strings: ["Java", "is", "fun"]
	 * 
	 * Output: "Java is fun"
	 * 
	 * Explanation: Concatenate strings with space separator.
	 */
	public String concatenateWithReduce(List<String> strings) {

		return strings //
				.stream() //
				.reduce((a, b) -> String.join(" ", a, b)) //
//		.reduce((a, b) -> a + " " + b) // this also works
				.orElse("");

	}

	/**
	 * 13. Teeing Collector - Calculate Both Sum and Count
	 * 
	 * Example: Given a list of integers, calculate both sum and count
	 * simultaneously.
	 * 
	 * Example: Numbers: [10, 20, 30, 40]
	 * 
	 * Output: {sum=100, count=4} Explanation: Perform two operations in one pass
	 * (Java 12+).
	 */
	public Map<String, Integer> sumAndCount(List<Integer> numbers) {

		return numbers //
				.stream() //
				.collect( //
						Collectors.teeing( //
								Collectors.summingInt(Integer::intValue), // C1
								Collectors.counting(), // C2
								(sum, count) -> { // Merger
									return Map.of("sum", sum, "count", count.intValue()); //
								}));

	}

	/**
	 * 14. Filtering Downstream Collector -
	 * 
	 * Group and Filter
	 * 
	 * Example: Given a list of numbers, group by even/odd and keep only numbers >
	 * 5.
	 * 
	 * Example: Numbers: [1, 2, 6, 7, 8, 10, 3]
	 * 
	 * Output: {false=[7], true=[6, 8, 10]}
	 * 
	 * Explanation: Group by even/odd, then filter to keep only numbers > 5.
	 */
	public Map<Boolean, List<Integer>> groupAndFilter(List<Integer> numbers) {

		return numbers.stream()
//		.filter(num -> num > 5) // naive solution
				.collect(Collectors.groupingBy( //
						num -> num % 2 == 0, //
						Collectors.collectingAndThen( //
								Collectors.toList(), //
								list -> list //
										.stream() //
										.filter(num -> num > 5) //
										.toList() //
						)));

	}

	/**
	 * 15. Mapping Downstream Collector
	 * 
	 * Group Strings and Get Lengths
	 * 
	 * Example: Given list of strings, group by first character and collect their
	 * lengths.
	 * 
	 * 
	 * Example: Strings: ["apple", "apricot", "banana", "cherry"] Output: {a=[5, 7],
	 * b=[6], c=[6]}
	 * 
	 * Explanation: Group by first character, map to lengths.
	 */
	public Map<Character, List<Integer>> groupByFirstCharAndMapToLength(List<String> strings) {

		return strings.stream().collect(Collectors.groupingBy(str -> str.charAt(0), //
				Collectors.collectingAndThen( //
						Collectors.toList(), //
						list -> list.stream().map(String::length).toList() //
				) //
		) //
		);

	}

	/**
	 * 16. SummarizingInt -
	 * 
	 * Get Statistics for Numbers
	 * 
	 * Example: Given a list of integers, get count, sum, min, max, and average.
	 * Example: Numbers: [10, 20, 30, 40, 50]
	 * 
	 * Output: IntSummaryStatistics{count=5, sum=150, min=10, average=30.0, max=50}
	 * 
	 * Explanation: Get all statistics at once.
	 */
	public IntSummaryStatistics getStatistics(List<Integer> numbers) {

		return numbers //
				.stream() //
				.collect(Collectors.summarizingInt(Integer::intValue));
	}

	/**
	 * 17. CollectingAndThen -
	 * 
	 * Group and Get Unmodifiable
	 * 
	 * Map Example: Given strings, group by length and return unmodifiable map.
	 * 
	 * Example: Strings: ["cat", "dog", "apple", "kiwi"]
	 * 
	 * Output: {3=[cat, dog], 4=[kiwi], 5=[apple]} (unmodifiable)
	 * 
	 * Explanation: Post-process the collected result.
	 */
	public Map<Integer, List<String>> groupByLengthUnmodifiable(List<String> strings) {

		return strings //
				.stream() //
				.collect(Collectors.collectingAndThen(Collectors.groupingBy(String::length),
						Collections::unmodifiableMap));

	}

	/**
	 * 18. FlatMapToInt - Flatten and Convert to IntStream
	 * 
	 * Example: Given a list of lists of integers, flatten and sum all numbers.
	 * 
	 * Example:
	 * 
	 * Nested: [[1, 2], [3, 4], [5]]
	 * 
	 * Output: 15 Explanation: Flatten and convert to IntStream, then sum.
	 */
	public int flattenAndSum(List<List<Integer>> nestedLists) {

		return nestedLists //
				.stream() //
				.flatMap(list -> list.stream()) //
				.mapToInt(Integer::valueOf)
				// naive implementation without flatmaps
//				.mapToInt(list -> //
//				list //
//						.stream() //
//						.mapToInt(Integer::intValue) //
//						.sum() //
//				) //
				.sum();

	}

	/**
	 * 19. Multi-level Grouping with Counting -
	 * 
	 * Group by Two Criteria
	 * 
	 * Example: Given strings, group by length, then by first character, and count.
	 * 
	 * Example: Strings: ["apple", "apricot", "cat", "dog", "kiwi"]
	 * 
	 * Output: {3={c=1, d=1}, 4={k=1}, 5={a=1}, 7={a=1}}
	 * 
	 * Explanation: First group by length, then by first character, then count
	 * occurrences.
	 */
	public Map<Integer, Map<Character, Long>> multiLevelGrouping(List<String> strings) {

		return strings //
				.stream() //
				.collect( //
						Collectors.groupingBy( //
								String::length, //
								Collectors.groupingBy(s -> s.charAt(0), //
										Collectors.counting() //
								) //
						) //
				);

	}

	/**
	 * 20. Custom Collector - Collect to StringBuilder
	 * 
	 * Example: Given a list of strings, collect them into StringBuilder with custom
	 * separator.
	 * 
	 * Example: Strings: ["Hello", "World", "Java"]
	 * 
	 * Output: "Hello | World | Java"
	 * 
	 * Explanation: Create custom collector that uses StringBuilder.
	 */
	public String customCollectorWithStringBuilder(List<String> strings, String separator) {

		Collector<String, StringBuilder, String> customCollector = Collector.of( //
				StringBuilder::new // supplier
				, (sb, s) -> { // accumulator
					if (sb.length() > 0) {
						sb.append(separator);
					}
					sb.append(s);
				}, (sb1, sb2) -> { // combiner
					if (sb1.length() > 0 && sb2.length() > 0) {
						sb1.append(separator);
					}
					sb1.append(sb2);
					return sb1;
				}, StringBuilder::toString // finisher
		);

		return strings.stream().collect(customCollector);
	}
}
