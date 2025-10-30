package org.projects.assignments.l4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.projects.assignments.models.Employee;

public class L4AssignmentTest {

	L4Assignment l4Assignment = new L4Assignment();

	/**
	 * Tests for L4Assignment class
	 */
	// Tests for findSalaryPercentile
//	@Test
//	public void testFindSalaryPercentile() {
//		List<Employee> employees = List.of(new Employee("Alice", "HR", 60000.0), new Employee("Bob", "IT", 80000.0),
//				new Employee("Charlie", "HR", 70000.0), new Employee("David", "IT", 90000.0),
//				new Employee("Eve", "Finance", 50000.0));
//		double percentile = 75.0;
//		double result = l4Assignment.findSalaryPercentile(employees, percentile);
//		// Expected 75th percentile salary is 80000.0
//		assertEquals(result, 80000.0);
//	}

//	@Test
//	public void testFindSalaryPercentileEmptyList() {
//		List<Employee> employees = List.of();
//		double percentile = 75.0;
//		double result = l4Assignment.findSalaryPercentile(employees, percentile);
//		// Expected -1.0 for empty list
//		assertEquals(result, -1.0);
//	}

	// Tests for concatenateNamesWithSeparator
	@Test
	public void testConcatenateNamesWithSeparator() {
		List<Employee> employees = List.of(new Employee("Alice", "HR", 60000.0), new Employee("Bob", "IT", 80000.0),
				new Employee("Charlie", "HR", 70000.0), new Employee("David", "IT", 90000.0),
				new Employee("Eve", "Finance", 50000.0));
		String department = "HR";
		String result = l4Assignment.concatenateNamesWithSeparator(employees, department);
		// Expected concatenated names for HR department
		assertEquals(result, "Alice & Charlie");
	}

	@Test
	public void testConcatenateNamesWithSeparatorNoDepartment() {
		List<Employee> employees = List.of(new Employee("Alice", "HR", 60000.0), new Employee("Bob", "IT", 80000.0));
		String department = "Finance";
		String result = l4Assignment.concatenateNamesWithSeparator(employees, department);
		// Expected null for non-existing department
		assertEquals(result, null);
	}

	// Tests for slidingWindowAverageSalary
	@Test
	public void testSlidingWindowAverageSalary() {
		List<Employee> employees = List.of(new Employee("Alice", "HR", 60000.0), new Employee("Bob", "IT", 80000.0),
				new Employee("Charlie", "HR", 70000.0), new Employee("David", "IT", 90000.0),
				new Employee("Eve", "Finance", 80000.0));
		int k = 3;
		List<Double> result = l4Assignment.slidingWindowAverageSalary(employees, k);
		// Expected average salaries in sliding windows of size 3
		List<Double> expected = List.of(70000.0, 80000.0, 80000.0);
		assertEquals(expected, result);
	}

	@Test
	public void testSlidingWindowAverageSalaryKGreaterThanList() {
		List<Employee> employees = List.of(new Employee("Alice", "HR", 60000.0), new Employee("Bob", "IT", 80000.0));
		int k = 3;
		List<Double> result = l4Assignment.slidingWindowAverageSalary(employees, k);
		// Expected empty list when k is greater than the list size
		List<Double> expected = List.of();
		assertEquals(expected, result);
	}

	// Tests for multiLevelGroupingWithAggregation
	@Test
	public void testMultiLevelGroupingWithAggregation() {
		List<Employee> employees = List.of(new Employee("Alice", "HR", 60000.0), new Employee("Bob", "IT", 80000.0),
				new Employee("Charlie", "HR", 70000.0), new Employee("David", "IT", 90000.0),
				new Employee("Eve", "Finance", 80000.0), new Employee("Frank", "IT", 80000.0));
		var result = l4Assignment.multiLevelGroupingWithAggregation(employees);
		// Expected multi-level grouping with counts
		var expected = Map.of("HR", Map.of("<70000", 1L, "70000-80000", 1L, ">80000", 0L), "IT",
				Map.of("<70000", 0L, "70000-80000", 2L, ">80000", 1L), "Finance",
				Map.of("<70000", 0L, "70000-80000", 1L, ">80000", 0L));
		assertEquals(expected, result);
	}

	@Test
	public void testMultiLevelGroupingWithAggregationEmptyList() {
		List<Employee> employees = List.of();
		var result = l4Assignment.multiLevelGroupingWithAggregation(employees);
		// Expected empty map for empty employee list
		var expected = Map.of();
		assertEquals(expected, result);
	}

	// Tests for 5. findAllSubsetsOfDepartments
//	@Test
//	public void testFindAllSubsetsOfDepartments() {
//		List<String> departments = List.of("IT", "HR", "Finance");
//		List<List<String>> result = l4Assignment.findAllSubsetsOfDepartments(departments);
//		// Expected all subsets of the given departments
//		List<List<String>> expected = List.of(List.of(), List.of("IT"), List.of("HR"), List.of("Finance"),
//				List.of("IT", "HR"), List.of("IT", "Finance"), List.of("HR", "Finance"),
//				List.of("IT", "HR", "Finance"));
//		assertEquals(expected, result);
//	}

//	@Test
//	public void testFindAllSubsetsOfDepartmentsEmptyList() {
//		List<String> departments = List.of();
//		List<List<String>> result = l4Assignment.findAllSubsetsOfDepartments(departments);
//		// Expected list with only the empty subset
//		List<List<String>> expected = List.of(List.of());
//		assertEquals(expected, result);
//	}

	// Tests for 6. customPartitioningWithPredicateChain
	@Test
	public void testCustomPartitioningWithPredicateChain() {
		List<Employee> employees = List.of(new Employee("Alice", "HR", 60000.0), new Employee("Bob", "IT", 80000.0),
				new Employee("Charlie", "HR", 70000.0), new Employee("David", "IT", 90000.0),
				new Employee("Eve", "Finance", 50000.0));
		Map<String, List<Employee>> result = l4Assignment.customPartitioningWithPredicateChain(employees);
		// Expected partitioning based on predicates
		Map<String, List<Employee>> expected = Map.of("HIGH",
				List.of(new Employee("David", "IT", 90000.0), new Employee("Bob", "IT", 80000.0)), "MID",
				List.of(new Employee("Charlie", "HR", 70000.0)), "LOW",
				List.of(new Employee("Alice", "HR", 60000.0), new Employee("Eve", "Finance", 50000.0)));
		assertEquals(expected, result);
	}

	@Test
	public void testCustomPartitioningWithPredicateChainEmptyList() {
		List<Employee> employees = List.of();
		Map<String, List<Employee>> result = l4Assignment.customPartitioningWithPredicateChain(employees);
		// Expected empty map for empty employee list
		Map<String, List<Employee>> expected = Map.of();
		assertEquals(expected, result);
	}

	// Tests for 7. calculateSalaryStandardDeviationByDepartment
	@Test
	public void testCalculateSalaryStandardDeviationByDepartment() {
		List<Employee> employees = List.of(new Employee("Alice", "IT", 75000.0), new Employee("Bob", "IT", 85000.0),
				new Employee("Charlie", "IT", 95000.0), new Employee("David", "HR", 60000.0),
				new Employee("Eve", "HR", 70000.0));
		Map<String, Double> result = l4Assignment.calculateSalaryStandardDeviationByDepartment(employees);
		// Expected standard deviation values for each department
		Map<String, Double> expected = Map.of("IT", 8164.96580927726, "HR", 7071.0678118654755);
		assertEquals(expected, result);
	}

	@Test
	public void testCalculateSalaryStandardDeviationByDepartmentEmptyList() {
		List<Employee> employees = List.of();
		Map<String, Double> result = l4Assignment.calculateSalaryStandardDeviationByDepartment(employees);
		// Expected empty map for empty employee list
		Map<String, Double> expected = Map.of();
		assertEquals(expected, result);
	}

	// Tests for 8. findLongestConsecutiveSalarySequence
	@Test
	public void testFindLongestConsecutiveSalarySequence() {
		List<Double> salaries = List.of(60000.0, 61000.0, 62000.0, 70000.0, 71000.0, 80000.0);
		List<Double> result = l4Assignment.findLongestConsecutiveSalarySequence(salaries);
		// Expected longest consecutive salary sequence
		List<Double> expected = List.of(60000.0, 61000.0, 62000.0);
		assertEquals(expected, result);
	}

	@Test
	public void testFindLongestConsecutiveSalarySequenceNoConsecutive() {
		List<Double> salaries = List.of(60000.0, 62000.0, 64000.0);
		List<Double> result = l4Assignment.findLongestConsecutiveSalarySequence(salaries);
		// Expected single element sequences when no consecutive salaries
		List<Double> expected = List.of(60000.0);
		assertEquals(expected, result);
	}

	// Tests for 9. customReducingWithTeeingCollector
	@Test
	public void testCustomReducingWithTeeingCollector() {
		List<Employee> employees = List.of(new Employee("Alice", "IT", 75000.0), new Employee("Bob", "IT", 85000.0),
				new Employee("Charlie", "IT", 95000.0));
		Map<String, Double> result = l4Assignment.customReducingWithTeeingCollector(employees);
		// Expected average and max salary
		Map<String, Double> expected = Map.of("average", 85000.0, "max", 95000.0);
		assertEquals(expected, result);
	}

	@Test
	public void testCustomReducingWithTeeingCollectorEmptyList() {
		List<Employee> employees = List.of();
		Map<String, Double> result = l4Assignment.customReducingWithTeeingCollector(employees);
		// Expected average and max as 0.0 for empty employee list
		Map<String, Double> expected = Map.of("average", 0.0, "max", 0.0);
		assertEquals(expected, result);
	}

	// Tests for 10. findCartesianProductOfTwoDepartmentLists
	@Test
	public void testFindCartesianProductOfTwoDepartmentLists() {
		List<String> list1 = List.of("IT", "HR");
		List<String> list2 = List.of("Finance", "Marketing");
		List<List<String>> result = l4Assignment.findCartesianProductOfTwoDepartmentLists(list1, list2);
		// Expected Cartesian product of the two department lists
		List<List<String>> expected = List.of(List.of("IT", "Finance"), List.of("IT", "Marketing"),
				List.of("HR", "Finance"), List.of("HR", "Marketing"));
		assertEquals(expected, result);
	}

	@Test
	public void testFindCartesianProductOfTwoDepartmentListsEmptyList() {
		List<String> list1 = List.of();
		List<String> list2 = List.of("Finance", "Marketing");
		List<List<String>> result = l4Assignment.findCartesianProductOfTwoDepartmentLists(list1, list2);
		// Expected empty list when one of the lists is empty
		List<List<String>> expected = List.of();
		assertEquals(expected, result);
	}

}
