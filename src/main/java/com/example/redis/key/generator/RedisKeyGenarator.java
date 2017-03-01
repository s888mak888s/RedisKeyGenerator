package com.example.redis.key.generator;

import com.example.Builder;

public abstract class RedisKeyGenarator<T> {
	public abstract String generate();
	public abstract Builder<T> builder();
}
