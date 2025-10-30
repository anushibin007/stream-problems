package org.projects.assignments.flatmaps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CookingFlatMaps {

	// 1️⃣ Flatten list of lists
	// Input: [[1,2,3], [4,5], [6]]
	// Output: [1,2,3,4,5,6]
	public static List<Integer> flattenListOfLists(List<List<Integer>> input) {
		List<Integer> result = input //
				.stream() //
				.flatMap(aNestedList -> aNestedList.stream()) //
				.toList();

		return result;
	}

	// 2️⃣ Split list of sentences into list of words
	// Input: ["Hello World", "Java Streams"]
	// Output: ["Hello", "World", "Java", "Streams"]
	public static List<String> splitSentencesIntoWords(List<String> sentences) {
		// Naive solution with multiple stream passes
//		List<List<String>> wordsAsNestedList = sentences
//				.stream()
//				.map(sentence -> List.of(sentence.split(" ")))
//				.toList();
//		List<String> result = wordsAsNestedList //
//		.stream() //
//		.flatMap(aNestedList -> aNestedList.stream()) //
//		.toList();

		// Optimized solution using single stream pass
		List<String> result = sentences //
				.stream() //
				.flatMap(sentence -> List.of(sentence.split(" ")).stream()) //
				.toList();

		return result;
	}

	// 3️⃣ Extract all unique characters from list of words
	// Input: ["java", "stream"]
	// Output: [j, a, v, s, t, r, e, m]
	public static List<Character> extractCharacters(List<String> words) {

		List<Character> wordsToCharList = words //
				.stream() //
				.flatMap(word -> List.of(word.split("")).stream()) //
				.distinct() //
				.map(str -> Character.valueOf(str.charAt(0))) //
				.toList();

		return wordsToCharList;
	}

	// 4️⃣ Flatten a list of optional values
	// Input: [Optional.of(1), Optional.empty(), Optional.of(3)]
	// Output: [1,3]
	public static List<Integer> flattenOptionals(List<Optional<Integer>> optionals) {

		List<Integer> list = optionals.stream().flatMap(Optional::stream).toList();

		return list;
	}

	// 5️⃣ Combine lists of skills from employees
	// Input: [{name:"A", skills:[Java, SQL]}, {name:"B", skills:[Python]}]
	// Output: [Java, SQL, Python]
	public static List<String> extractEmployeeSkills(List<Employee> employees) {
		return employees.stream().flatMap(emp -> emp.skills.stream()).toList();
	}

	// 6️⃣ Convert list of comma-separated strings into integers
	// Input: ["1,2,3", "4,5"]
	// Output: [1,2,3,4,5]
	public static List<Integer> splitCommaSeparatedNumbers(List<String> numbers) {
		List<Integer> result = numbers.stream() //
				.flatMap(str -> Arrays.stream(str.split(","))) //
				.map(str -> Integer.valueOf(str)) //
				.toList();

		return result;
	}

	// 7️⃣ Get all words from a map of <String, List<String>>
	// Input: {"A": ["one","two"], "B": ["three"]}
	// Output: ["one", "two", "three"]
	public static List<String> flattenMapValues(Map<String, List<String>> map) {

		return map.entrySet().stream().flatMap(entry -> entry.getValue().stream()).toList();

	}

	// 8️⃣ Extract domain names from emails
	// Input: ["a@gmail.com", "b@yahoo.com"]
	// Output: ["gmail.com", "yahoo.com"]
	public static List<String> extractDomains(List<String> emails) {
		return emails.stream().flatMap(email -> Arrays.stream(email.split("@")).skip(1)).toList();

	}

	// 9️⃣ Expand product variations
	// Input: [{product:"Tshirt", sizes:["S","M"]}, {product:"Shoes",
	// sizes:["8","9"]}]
	// Output: ["Tshirt-S", "Tshirt-M", "Shoes-8", "Shoes-9"]
	public static List<String> expandProductVariations(List<Product> products) {
		List<String> result = products //
				.stream() //
				.flatMap(product -> product.sizes.stream().map(size -> String.join("-", product.product, size, "hi"))) //
				.toList();
		return result;
	}

	// 🔟 Generate all pair combinations
	// Input: [1,2], [3,4]
	// Output: [(1,3), (1,4), (2,3), (2,4)]
	public static List<String> generatePairs(List<Integer> list1, List<Integer> list2) {
		return list1
		.stream()
		.flatMap(
				l1item -> list2
				.stream()
				.map(l2item -> "(" + 
				String.join(",", String.valueOf(l1item), String.valueOf(l2item)) + ")"
				)
				)
		.toList();
	}

	// Helper classes for testing
	public static class Employee {
		public String name;
		public List<String> skills;

		public Employee(String name, List<String> skills) {
			this.name = name;
			this.skills = skills;
		}
	}

	public static class Product {
		public String product;
		public List<String> sizes;

		public Product(String product, List<String> sizes) {
			this.product = product;
			this.sizes = sizes;
		}
	}
}
