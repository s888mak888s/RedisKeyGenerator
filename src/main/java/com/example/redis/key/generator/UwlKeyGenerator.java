package com.example.redis.key.generator;

public class UwlKeyGenerator extends RedisKeyGenarator<UwlKeyGenerator> {
	private final String subDomain;
	private UwlKeyGenerator(Builder builer) {
		this.subDomain = builer.subDomain;
	}
	@Override
	public String generate() {
		return subDomain;
	}

	public static class Builder implements com.example.Builder<UwlKeyGenerator> {
		private final String subDomain = "uwl";
		@Override
		public UwlKeyGenerator build() {
			return new UwlKeyGenerator(this);
		}
		
	}
	@Override
	public com.example.Builder<UwlKeyGenerator> builder() {
		return new Builder();
	}
	
}
