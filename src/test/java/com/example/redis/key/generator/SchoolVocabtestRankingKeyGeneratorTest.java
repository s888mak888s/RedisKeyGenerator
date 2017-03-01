package com.example.redis.key.generator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.example.redis.key.generator.SchoolVocabtestRankingKeyGenerator.AggregateType;
import com.example.redis.key.generator.SchoolVocabtestRankingKeyGenerator.Span;

public class SchoolVocabtestRankingKeyGeneratorTest {
	private int year = 2017;
	private int schoolId = 1;
	private SchoolVocabtestRankingKeyGenerator.Span span = SchoolVocabtestRankingKeyGenerator.Span.DAILY;
	private SchoolVocabtestRankingKeyGenerator.AggregateType aggregateType = SchoolVocabtestRankingKeyGenerator.AggregateType.AVERAGE;
	private String grade = "staff";
	private int classId = 3;
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public final void testNoneYear() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("you must set year");
		new SchoolVocabtestRankingKeyGenerator.Builder().build();
	}

	@Test
	public final void testNoneSchool() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("you must set schoolId");
		new SchoolVocabtestRankingKeyGenerator.Builder().year(year).build();
	}

	@Test
	public final void testNoneSpan() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("you must set span");
		new SchoolVocabtestRankingKeyGenerator.Builder().year(year).schoolId(schoolId).build();
	}

	@Test
	public final void testNoneAgg() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("you must set aggregateType");
		new SchoolVocabtestRankingKeyGenerator.Builder().year(year).schoolId(schoolId).span(span).build();
	}
	@Test
	public final void testNoneCategories() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("you must set bc OR mc OR sc");
		new SchoolVocabtestRankingKeyGenerator.Builder().year(year).schoolId(schoolId).span(span).aggregateType(aggregateType).build();
	}

	@Test
	public final void testGenerate1() {
		assertThat(
				new SchoolVocabtestRankingKeyGenerator.Builder().year(year).schoolId(schoolId).span(span)
						.aggregateType(aggregateType).bc("school").build().generate(),
				is("uwl-vocabtest-school-ranking-2017-1--0-school---daily-average"));
	}

	@Test
	public final void testGenerate2() {

		assertThat(
				new SchoolVocabtestRankingKeyGenerator.Builder().year(year).schoolId(schoolId).span(Span.MONTHLY)
						.aggregateType(AggregateType.MAX).grade(grade).classId(classId)
						.bc("school")
						.mc("test60")
						.sc("sc")
						.build().generate(),
				is("uwl-vocabtest-school-ranking-2017-1-staff-3-school-test60-sc-monthly-max"));
	}

}
