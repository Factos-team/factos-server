package com.factosback.factos.domain.term.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class OpenApiResponseParserTest {

	private final OpenApiResponseParser parser = new OpenApiResponseParser();

	@Test
	void extractGeneralTerms() {

		// given
		String mockJson = """
            {
              "법령용어": {
                "연계용어": [
                  { "일상용어명": "민원" },
                  { "일상용어명": "신청" },
                  { "일상용어명": "요청" }
                ]
              }
            }
        """;

		// when
		List<String> result = parser.extractGeneralTerms(mockJson);

		// then
		assertEquals(List.of("민원", "신청", "요청"), result);
	}
}