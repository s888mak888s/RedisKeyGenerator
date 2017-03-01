package com.example.redis.key.generator;

public class SchoolVocabtestRankingKeyGenerator extends RedisKeyGenarator<SchoolVocabtestRankingKeyGenerator> {
	private final String prefix = "vocabtest-school-ranking";
	private final UwlKeyGenerator parent = new UwlKeyGenerator.Builder().build();
	private final int year;
	private final int schoolId;
	private final String grade;
	private final int classId;
	private final String bc;
	private final String mc;
	private final String sc;
	private final Span span;
	private final AggregateType aggregateType;

	public enum Span {
		NONE(""), MONTHLY("monthly"), DAILY("daily");
		private final String name;
		private Span(String name) {
			this.name = name;
		}
		public String getName() {
			return this.name;
		}
	}

	public enum AggregateType {
		NONE(""), AVERAGE("average"), MAX("max");
		private final String name;
		private AggregateType(String name) {
			this.name = name;
		}
		public String getName() {
			return this.name;
		}
	}

	private SchoolVocabtestRankingKeyGenerator(Builder builder) {
		this.year = builder.year;
		this.schoolId = builder.schoolId;
		this.grade = builder.grade;
		this.classId = builder.classId;
		this.bc = builder.bc;
		this.mc = builder.mc;
		this.sc = builder.sc;
		this.span = builder.span;
		this.aggregateType = builder.aggregateType;
	}

	@Override
	public String generate() {
		return String.join(
			"-", 
			new String[] { 
				parent.generate(), 
				prefix, 
				String.valueOf(year), 
				String.valueOf(schoolId), 
				grade, 
				String.valueOf(classId), 
				bc, mc, sc,
				span.getName(), 
				aggregateType.getName() 
			}
		);
	}

	public static class Builder implements com.example.Builder<SchoolVocabtestRankingKeyGenerator> {
		private int year = -1;
		private int schoolId = -1;
		private String grade = "";
		private int classId = 0;
		private String bc = "";
		private String mc = "";
		private String sc = "";
		private Span span = Span.NONE;
		private AggregateType aggregateType = AggregateType.NONE;

		@Override
		public SchoolVocabtestRankingKeyGenerator build() {
			if (year == -1) {
				throw new IllegalArgumentException("you must set year");
			}
			if (schoolId == -1) {
				throw new IllegalArgumentException("you must set schoolId");
			}
			if (span == Span.NONE) {
				throw new IllegalArgumentException("you must set span");
			}
			if (aggregateType == AggregateType.NONE) {
				throw new IllegalArgumentException("you must set aggregateType");
			}
			if (bc.isEmpty() && mc.isEmpty() && sc.isEmpty()) {
				throw new IllegalArgumentException("you must set bc OR mc OR sc");
			}
			
			return new SchoolVocabtestRankingKeyGenerator(this);
		}

		Builder year(int year) {
			this.year = year;
			return this;
		}

		Builder schoolId(int schoolId) {
			this.schoolId = schoolId;
			return this;
		}

		Builder grade(String grade) {
			this.grade = grade;
			return this;
		}

		Builder classId(int classId) {
			this.classId = classId;
			return this;
		}

		Builder bc(String bc) {
			this.bc = bc;
			return this;
		}

		Builder mc(String mc) {
			this.mc = mc;
			return this;
		}

		Builder sc(String sc) {
			this.sc = sc;
			return this;
		}

		Builder span(Span span) {
			this.span = span;
			return this;
		}

		Builder aggregateType(AggregateType aggregateType) {
			this.aggregateType = aggregateType;
			return this;
		}

	}

	@Override
	public com.example.Builder<SchoolVocabtestRankingKeyGenerator> builder() {
		return new Builder();
	}

}
