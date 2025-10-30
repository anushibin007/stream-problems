package org.projects.assignments.l3;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.projects.assignments.models.Employee;

public class L3Assignment {

	/*
	 * PROBLEM -- 301
	 * 
	 * Given a list of Employee objects, where each Employee has a name, department,
	 * and salary, write a method that returns a map where the keys are department
	 * names and the values are the average salary of employees in that department.
	 * Use Java Streams API to perform this operation.
	 * 
	 * Example:
	 * 
	 * Input: List of Employees [ Employee("Alice", "HR", 60000), Employee("Bob",
	 * "IT", 80000), Employee("Charlie", "HR", 70000), Employee("David", "IT",
	 * 90000) ]
	 * 
	 * Output: { "HR": 65000.0, "IT": 85000.0 }
	 */

	public Map<String, Double> averageSalaryByDepartment(List<Employee> employees) {
		// write code here using Java Streams API

		Map<String, Double> result = employees.stream().collect( //
				Collectors.groupingBy(Employee::department, // key
						Collectors.collectingAndThen(Collectors.toList(),
								list -> list.stream().mapToDouble(Employee::salary).average().orElse(0.0))));

		return result;
	}

	/*
	 * PROBLEM -- 302
	 * 
	 * From a list of employee salaries, find the second highest unique salary.
	 * 
	 * Example:
	 * 
	 * Input: [50000, 60000, 70000, 60000, 80000]
	 * 
	 * Output: 70000 Note: If there is no second highest unique salary, return -1.
	 */
	public int secondHighestUniqueSalary(List<Integer> salaries) {
		// write code here using Java Streams API
		return salaries.stream() //
				.sorted(Comparator.reverseOrder()) // descending
				.distinct() //
				.skip(1) // skip - 1
				.findFirst() //
				.orElse(-1); //
	}

	/*
	 * PROBLEM -- 303
	 * 
	 * Transform a list of strings into a map where the key is the string and the
	 * value is its length.
	 * 
	 * Example:
	 * 
	 * Input: ["apple", "banana", "cherry"]
	 * 
	 * Output: {"apple": 5, "banana": 6, "cherry": 6}
	 */
	public Map<String, Integer> stringLengthMap(List<String> strings) {
		// write code here using Java Streams API
		return strings //
				.stream() //
				.collect( //
						Collectors.toMap(//
								Function.identity(), // key str -> str
								String::length // value
						));
	}

	/*
	 * PROBLEM -- 304
	 * 
	 * Given two lists, find elements that appear in both lists.
	 * 
	 * Example:
	 * 
	 * Input: List1 = [1, 2, 3, 4,], List2 = [3, 4, 5, 6]
	 * 
	 * Output: [3, 4]
	 */
	public List<Integer> commonElements(List<Integer> list1, List<Integer> list2) {

		return list1 //
				.stream() //
				.filter( //
						val1 -> list2.stream() //
								.anyMatch(val2 -> val1 == val2) //
				).toList();

	}

	/*
	 * PROBLEM -- 305
	 * 
	 * Split a list of integers into even and odd numbers using partitioning.
	 * 
	 * Example: Input: [1, 2, 3, 4, 5, 6]
	 * 
	 * Output: {true: [2, 4, 6], false: [1, 3, 5]}
	 */
	public Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> numbers) {

		// solution using groupingBy
//		Map<String, List<Integer>> collect = numbers.stream()
//				.collect(Collectors.groupingBy(val -> val % 2 == 0 ? "even" : "odd"));

		Map<Boolean, List<Integer>> result = numbers.stream().collect(Collectors.partitioningBy(val -> val % 2 == 0));

		return result;
	}

	/*
	 * PROBLEM -- 306
	 * 
	 * From a list of strings, find the N most frequently occurring elements.
	 * 
	 * Example:
	 * 
	 * Input: ["apple", "banana", "apple", "orange", "banana", "apple"]
	 * 
	 * , N = 2
	 * 
	 * Output: ["apple", "banana"]
	 */
	public List<String> topNFrequentElements(List<String> strings, int n) {
		// write code here using Java Streams API
		// key -> fruit
		// value -> frequency of the fruit
		Map<String, Long> interimMap = strings.stream().collect(Collectors.groupingBy( //
				Function.identity(), // key
				Collectors.counting() // value
		));
		// apple -> 2
		// orange -> 1
		// banana -> 2

		List<String> result = interimMap //
				.entrySet() //
				.stream() //
				.sorted(

						Comparator //
								.comparing(Map.Entry<String, Long>::getValue, Comparator.reverseOrder()) //
								.thenComparing(e -> strings.indexOf(e.getKey()), Comparator.reverseOrder())//
				) //
				.limit(n) //
				.map(Map.Entry::getKey).toList();

		return result;
	}

	/*
	 * PROBLEM -- 307
	 * 
	 * Given a list of lists, flatten it into a single list using flatMap
	 * 
	 * Example:
	 * 
	 * Input: [[1, 2], [3, 4], [5]]
	 * 
	 * Output: [1, 2, 3, 4, 5]
	 */
	public List<Integer> flattenListOfLists(List<List<Integer>> listOfLists) {

		return listOfLists//
				.stream()//
				.flatMap(smallerList -> smallerList.stream())//
				.toList();

	}

	/*
	 * PROBLEM -- 308
	 * 
	 * Transform a list of numbers into their cumulative sum. Example:
	 * 
	 * Input: [1, 2, 3, 4]
	 * 
	 * Output: [1, 3, 6, 10]
	 * 
	 * Explanation: (1, 1+2, 1+2+3, 1+2+3+4) (running sum)
	 */
	public List<Integer> cumulativeSum(List<Integer> numbers) {
		// write code here using Java Streams API

		List<Integer> result = IntStream //
				.rangeClosed(1, numbers.size()) //
				.map(i -> numbers //
						.stream() //
						.mapToInt(Integer::valueOf) //
						.limit(i) //
						.sum() //
				)//
				.boxed() //
				.toList(); //

		return result;
	}

	/*
	 * PROBLEM -- 309
	 * 
	 * From employee data, identify which department has the highest average salary
	 * 
	 * Example:
	 * 
	 * Input: List of Employees
	 * 
	 * [ Employee("Alice", "HR", 60000), Employee("Bob", "IT", 80000),
	 * Employee("Charlie", "HR", 70000),Employee("David", "IT", 90000) ]
	 * 
	 * Output: "IT"
	 */
	public String departmentWithHighestAverageSalary(List<Employee> employees) {
		// write code here using Java Streams API

		Map<String, Double> empSalAvg = employees //
				.stream() //
				.collect( //
						Collectors.groupingBy(Employee::department, // key
								Collectors.averagingDouble(Employee::salary) // value
						)

				);

		String highestAvgSalDeptName = empSalAvg //
				.entrySet() //
				.stream() //
				.sorted( //
						Map.Entry.comparingByValue(Comparator.reverseOrder()) //
				) //
				.findFirst() //
				.map(Map.Entry::getKey) //
				.orElse("");

		return highestAvgSalDeptName;
	}

	/*
	 * PROBLEM -- 310
	 * 
	 * Remove duplicate characters from a string while preserving order.
	 * 
	 * Example:
	 * 
	 * Input: "programming"
	 * 
	 * Output: "progamin"
	 */
	public String removeDuplicateCharacters(String input) {
		// write code here using Java Streams API

		String result = input //
				.chars() //
				.mapToObj(ascii -> String.valueOf((char) ascii)) //
				.distinct() //
				.collect(Collectors.joining());

		return result;
	}
}
