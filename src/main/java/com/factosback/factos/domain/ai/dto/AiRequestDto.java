package com.factosback.factos.domain.ai.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiRequestDto {
	private String case_summary;
	private String member_evidence;
	private String opponent_claim;
}
