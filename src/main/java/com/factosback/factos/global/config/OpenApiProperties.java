package com.factosback.factos.global.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openapi")
public record OpenApiProperties (
	String oc,
	String baseUrl,
	String target,
	String type
) {}
