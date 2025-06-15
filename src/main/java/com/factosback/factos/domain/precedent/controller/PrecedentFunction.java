// package com.factosback.factos.domain.precedent.controller; // 패키지 경로는 본인 것에 맞게 확인
//
// import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
// import com.factosback.factos.domain.precedent.dto.GetPrecedentDto;
// import com.factosback.factos.domain.precedent.service.PrecedentService;
// import com.factosback.factos.global.error.exception.RestApiException; // RestApiException 임포트
// import com.factosback.factos.global.response.ApiResponse;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import java.util.function.Function;
//
// @Slf4j
// @Configuration
// @RequiredArgsConstructor
// public class PrecedentFunction {
//
//     private final PrecedentService precedentService;
//
//     @Bean
//     public Function<APIGatewayProxyRequestEvent, ApiResponse<?>> getPrecedents() {
//         return request -> {
//             try {
//                 log.info("API Gateway 요청 수신");
//
//                 String caseNumberStr = request.getPathParameters().get("caseNumber");
//                 if (caseNumberStr == null || caseNumberStr.isBlank()) {
//                     return ApiResponse.createFail("caseNumber가 경로에 존재하지 않습니다.");
//                 }
//
//                 Integer caseNumber = Integer.parseInt(caseNumberStr);
//                 log.info("요청된 사건번호: {}", caseNumber);
//
//                 GetPrecedentDto.Response responseDto = precedentService.getPrecedent(caseNumber);
//
//                 return ApiResponse.createSuccess(responseDto);
//
//             } catch (RestApiException e) {
//                 log.warn("정의된 API 예외 발생: {}", e.getErrorCode().getMessage());
//                 return ApiResponse.createFail(e.getErrorCode());
//
//             } catch (NumberFormatException e) {
//                 log.warn("잘못된 숫자 형식의 caseNumber: {}", request.getPathParameters().get("caseNumber"));
//                 return ApiResponse.createFail("사건번호는 유효한 숫자 형식이어야 합니다.");
//
//             } catch (Exception e) {
//                 log.error("예측하지 못한 서버 오류 발생", e);
//                 return ApiResponse.createFail("서버 내부 오류가 발생했습니다.");
//             }
//         };
//     }
// }