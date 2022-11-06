package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cogemutil.util.CogemUtil;


@Configuration
public class CoregemstoneConfig {
	@Bean
	public CogemUtil CogemUtil() {
		return new CogemUtil();
	}
}
