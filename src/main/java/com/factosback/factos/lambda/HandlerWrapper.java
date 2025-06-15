// package com.factosback.factos.lambda;
//
// import com.amazonaws.services.lambda.runtime.Context;
// import org.springframework.cloud.function.adapter.aws.FunctionInvoker;
//
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.OutputStream;
//
//
// public class HandlerWrapper extends FunctionInvoker {
//
//     /**
//      * @param input   입력 스트림
//      * @param output  출력 스트림
//      * @param context 실행 컨텍스트
//      * @throws IOException 입출력 예외
//      */
//
//     public void handleStreamRequest(InputStream input, OutputStream output, Context context) throws IOException {
//         // 부모 클래스의 핵심 로직을 그대로 호출합니다.
//         this.handleRequest(input, output, context);
//     }
// }