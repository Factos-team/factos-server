package com.factosback.factos.domain.ai.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiRequestDto {
	private String userInput;
	private String contextSummary;
}
