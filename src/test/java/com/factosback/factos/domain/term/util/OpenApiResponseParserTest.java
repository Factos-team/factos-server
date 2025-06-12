package com.factosback.factos.domain.term.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class OpenApiResponseParserTest {

	private final OpenApiResponseParser parser = new OpenApiResponseParser();

	@Test
	void extractGeneralTerms() {

		String mockJson = """
    {
      "lstrmRltService": {
        "검색결과개수": "1",
        "키워드": "청원",
        "target": "lstrmRlt",
        "법령용어": {
          "id": "1",
          "연계용어": [
            {
              "용어간관계링크": "...",
              "id": "1",
              "일상용어명": "민원",
              "용어관계": "연관어",
              "용어관계코드": "140305",
              "일상용어조회링크": "..."
            },
            {
              "용어간관계링크": "...",
              "id": "2",
              "일상용어명": "신청",
              "용어관계": "연관어",
              "용어관계코드": "140305",
              "일상용어조회링크": "..."
            },
            {
              "용어간관계링크": "...",
              "id": "3",
              "일상용어명": "요청",
              "용어관계": "연관어",
              "용어관계코드": "140305",
              "일상용어조회링크": "..."
            }
          ],
          "법령용어명": "청원",
          "비고": ""
        }
      }
    }
    """;

		// when
		List<String> result = parser.extractGeneralTerms(mockJson);

		// then
		assertEquals(List.of("민원", "신청", "요청"), result);
	}
}