package org.projects.assignments.l4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.projects.assignments.models.Employee;

public class L4Assignment {
	// We are skipping this question because it is mostly mathematical. This has
	// very little to do with Java Streams.
	/**
	 * 1. Find Salary Percentile Example: Given a list of employees, find the 75th
	 * percentile salary. Example: Employees: [ Employee("Alice", "HR", 60000),
	 * Employee("Bob", "IT", 80000), Employee("Charlie", "HR", 70000),
	 * Employee("David", "IT", 90000), Employee("Eve", "Finance", 80000) ] Output:
	 * 80000 Explanation: The 75th percentile salary is 80000. In simple terms, 75%
	 * of employees earn less than or equal to 80000.
	 */
//    public double findSalaryPercentile(List<Employee> salaries, double percentile) {
	// write code here using Java Streams API
//        return -1;
//    }

	/**
	 * 2. Concatenate Names with Separator
	 * 
	 * Input: Employees grouped by department, concatenate names with " & "
	 * 
	 * Example: Employees: [ Employee("Alice", "HR", 60000), Employee("Bob", "IT",
	 * 80000), Employee("Charlie", "HR", 70000), Employee("David", "IT", 90000),
	 * Employee("Eve", "Finance", 80000) ]
	 * 
	 * Output: { "HR": "Alice & Charlie", "IT": "Bob & David", "Finance": "Eve" }
	 * 
	 * Explanation: Concatenate names of employees in each department with " & " as
	 * a separator. In simple terms, it joins the names of employees in each
	 * department with " & ".
	 */
	public String concatenateNamesWithSeparator(List<Employee> employees, String department) {
		// write code here using Java Streams API

		Map<String, String> collect = employees //
				.stream() //
//				.filter(emp -> emp.department().equals(department)) //
				.collect( //
						Collectors.groupingBy(Employee::department, // key
								Collectors.collectingAndThen(Collectors.toList(),
										list -> list.stream().map(Employee::name).collect(Collectors.joining(" & "))) // value
						) //
				);

		System.out.println(collect);

		return collect.get(department);
	}

	/**
	 * 3. Sliding Window Average Salary
	 * 
	 * Input: List of employees with sorted salaries, calculate average salary in a
	 * sliding window of size k
	 * 
	 * Example: Employees: [ Employee("Alice", "HR", 60000), Employee("Bob", "IT",
	 * 80000), Employee("Charlie", "HR", 70000), Employee("David", "IT", 90000),
	 * Employee("Eve", "Finance", 80000) ] k = 3
	 * 
	 * Output: [70000.0, 80000.0, 80000.0]
	 * 
	 * 
	 * Explanation: The average salaries in each sliding window of size 3 are
	 * [70000.0, 80000.0, 80000.0]. In simple terms, it calculates the average
	 * salary for every group of 3 consecutive employees.
	 */
	public List<Double> slidingWindowAverageSalary(List<Employee> employees, int k) {
		// write code here using Java Streams API

		int end = employees.size() - k + 1;

		List<Double> result = IntStream //
				.rangeClosed(1, end) //
				.mapToDouble(i -> { //
					return employees.stream() //
							.skip(i - 1) //
							.limit(k) //
							.mapToDouble(Employee::salary) //
							.average() //
							.orElse(0.0);
				}) //
				.boxed() //
				.toList();
		;

		return result;
	}

	/**
	 * 4. Multi-Level Grouping with Aggregation
	 * 
	 * Input: List of employees, group by department and then by salary range (e.g.,
	 * <70000, 70000-80000, >80000), count employees in each group
	 * 
	 * Example: Employees: [ Employee("Alice", "HR", 60000), Employee("Bob", "IT",
	 * 80000), Employee("Charlie", "HR", 70000), Employee("David", "IT", 90000),
	 * Employee("Eve", "Finance", 80000), Employee("Frank", "IT", 80000) ]
	 * 
	 * Output: { "HR": { "<70000": 1, "70000-80000": 1, ">80000": 0 }, "IT": {
	 * "<70000": 0, "70000-80000": 2, ">80000": 1 }, "Finance": {"<70000": 0,
	 * "70000-80000": 1, ">80000": 0 } }
	 * 
	 * 
	 * Explanation: The outer map groups employees by their department, and the
	 * inner map counts employees in each salary range within each department. In
	 * simple terms, it organizes employees by department and counts how many fall
	 * into each salary category.
	 */
	public Map<String, Map<String, Long>> multiLevelGroupingWithAggregation(List<Employee> employees) {
		// write code here using Java Streams API

		Map<String, Map<String, Long>> result = employees //
				.stream() //
				.collect( //
						Collectors.groupingBy(Employee::department, // key
								Collectors.groupingBy(emp -> { // value
									if (emp.salary() < 70000)
										return "<70000";
									else if (emp.salary() >= 70000 && emp.salary() <= 80000)
										return "70000-80000";
									return ">80000";

								}, Collectors.counting())));

		final Set<String> SALARY_BINS = Set.of("<70000", "70000-80000", ">80000");

		result.values().forEach(map -> {
			SALARY_BINS.forEach(bin -> map.putIfAbsent(bin, 0L));
		});

		return result;
	}

	/**
	 * 5. Find All Subsets of Departments Input: Departments: ["IT", "HR", Finance"]
	 * 
	 * Output: [[], [IT], [HR], [IT, HR], [Finance], [IT, Finance], [HR, Finance],
	 * [IT, HR, Finance]]
	 * 
	 * Explanation: Generate all possible subsets of the given list of departments.
	 * In simple terms, it finds every combination of the departments, including the
	 * empty set and the full set.
	 */
//	public List<List<String>> findAllSubsetsOfDepartments(List<String> departments) {
//		// write code here using Java Streams API
//		return null;
//	}

	/**
	 * 6. Custom Input: Employees partitioned by:
	 * 
	 * - High earners (>=80k)
	 * 
	 * - Mid earners (60001k-79999k)
	 * 
	 * - Low earners (<=60k)
	 *
	 * Output: { HIGH=[Employee("Bob", "HR", 85000.0), Employee("Diana", "IT",
	 * 90000.0)], MID=[Employee("Alice", "IT", 75000.0), Employee("Eve", "HR",
	 * 70000.0)], LOW=[Employee("Charlie", "Finance", 60000.0)] } Explanation:
	 * 
	 * Partition employees into three categories based on their salaries using a
	 * chain of predicates. In simple terms, it sorts employees into high, mid, and
	 * low earners based on their salary ranges. Also, sort the output in descending
	 * order of salaries.
	 */
	public Map<String, List<Employee>> customPartitioningWithPredicateChain(List<Employee> employees) {
		// write code here using Java Streams API

		Map<String, List<Employee>> result = employees //
				.stream() //
				.sorted(Comparator.comparing(Employee::salary).reversed())//
				.collect( //
						Collectors.groupingBy(emp -> {
							if (emp.salary() <= 60000)
								return "LOW";
							else if (emp.salary() > 60000 && emp.salary() < 80000)
								return "MID";
							else
								return "HIGH";
						}));

		return result;
	}

	/**
	 * 7. Calculate Salary Standard Deviation by Department
	 * 
	 * Input: Employees:
	 * 
	 * - Employee("Alice", "IT", 75000.0)
	 * 
	 * - Employee("Bob", "IT", 85000.0)
	 * 
	 * - Employee("Charlie", "IT", 95000.0)
	 * 
	 * Output: {IT=8164.97}
	 * 
	 * Explanation:
	 * 
	 * Calculate the standard deviation of salaries for each department. In simple
	 * terms, it measures how much the salaries in each department vary from the
	 * average salary.
	 */
	public Map<String, Double> calculateSalaryStandardDeviationByDepartment(List<Employee> employees) {
		// write code here using Java Streams API

		Map<String, Double> avgSalaryMap = employees //
				.stream() //
				.collect(Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::salary)));

		Map<String, Long> employeeDptCount = employees //
				.stream() //
				.collect(Collectors.groupingBy(Employee::department, Collectors.counting()));

		Map<String, Double> result = //
				avgSalaryMap //
						.entrySet() //
						.stream() //
						.collect(Collectors.toMap(Map.Entry::getKey, entry -> { //
							String dept = entry.getKey(); //
							Double mean = entry.getValue(); //
							Long employeeCount = employeeDptCount.get(dept); //

							double variance = employees //
									.stream() //
									.filter(emp -> emp.department().equals(dept)) //
									.mapToDouble(v -> Math.pow(v.salary() - mean, 2)) //
									.sum() / (employeeCount <= 2 ? employeeCount - 1 : employeeCount); //
							return Math.sqrt(variance); //
						}));

		return result;
	}

	/**
	 * 8. Find Longest Consecutive Salary Sequence where 2 items are accepted as
	 * consecutive if their difference is 1000.
	 * 
	 * Input: Salaries: [60000, 61000, 62000, 70000, 71000, 80000]
	 * 
	 * Output: [60000, 61000, 62000]
	 */
	public List<Double> findLongestConsecutiveSalarySequence(List<Double> salaries) {
		// write code here using Java Streams API

		List<List<Double>> interimResult = new ArrayList<>();

		salaries //
				.stream() //
				.forEach(aSalary -> compute(aSalary, interimResult));

		List<Double> finalResult = interimResult //
				.stream() //
				.sorted(Comparator.comparing(List<Double>::size).reversed()) //
				.findFirst() //
				.orElse(List.of());

		return finalResult;
	}

	private static void compute(Double currentValue, List<List<Double>> result) {
		if (result.size() == 0) {
			List<Double> firstValue = new ArrayList<>();
			firstValue.add(currentValue);
			result.add(firstValue);
			return;
		}
		List<Double> lastNestedList = result.get(result.size() - 1);
		Double lastValue = lastNestedList.get(lastNestedList.size() - 1);
		Double diff = currentValue - lastValue;
		if (diff == 1000) {
			lastNestedList.add(currentValue);
		} else {
			List<Double> firstValue = new ArrayList<>();
			firstValue.add(currentValue);
			result.add(firstValue);
		}
	}

	/**
	 * 9. Custom Reducing with Teeing Collector
	 * 
	 * Input: Employees: Calculate both average and max salary simultaneously using
	 * teeing collector Output: {average=75000.0, max=90000.0}
	 * 
	 * Explanation: Use a teeing collector to compute both the average and maximum
	 * salary in a single pass through the data. In simple terms, it finds both the
	 * average and highest salary at the same time.
	 */
	public Map<String, Double> customReducingWithTeeingCollector(List<Employee> employees) {
		// write code here using Java Streams API

		Map<String, Double> finalResult = employees //
				.stream() //
				.collect( //
						Collectors.teeing( //
								Collectors.averagingDouble(Employee::salary), //
								Collectors.maxBy(Comparator.comparing(Employee::salary)), //
								(avg, maxEmployee) -> {

									Employee maxEmployeeVal = maxEmployee.orElse(new Employee(null, null, 0.0));

									Map<String, Double> result = new HashMap<>();
									result.put("average", avg);
									result.put("max", maxEmployeeVal.salary());
									return result;
								}) //
				);

		return finalResult;
	}

	/**
	 * 10. Find Cartesian Product of Two Department Lists
	 * 
	 * Input:
	 * 
	 * List1: ["IT", "HR"],
	 * 
	 * List2: ["Finance", "Marketing"]
	 * 
	 * Output: [
	 * 
	 * ("IT", "Finance"), ("IT", "Marketing"),
	 * 
	 * ("HR", "Finance"), ("HR", "Marketing")
	 * 
	 * ]
	 * 
	 * Explanation: Generate the Cartesian product of two lists of departments. In
	 * simple terms, it creates all possible pairs of departments from the two
	 * lists.
	 */
	public List<List<String>> findCartesianProductOfTwoDepartmentLists(List<String> list1, List<String> list2) {

//		List<List<String>> bruteForceSolution = new ArrayList<>();
//		list1 //
//				.stream() //
//				.forEach(str1 -> //
//				list2 //
//						.stream() //
//						.forEach(str2 -> //
//						{
//							List<String> interimList = List.of(str1, str2);
//							bruteForceSolution.add(interimList);
//						}) //
//				);

		List<List<String>> list = list1 //
				.stream() //
				.flatMap( //
						str1 -> list2 //
								.stream() //
								.map(str2 -> List.of(str1, str2)) //
				).toList();

		return list;
	}
}
