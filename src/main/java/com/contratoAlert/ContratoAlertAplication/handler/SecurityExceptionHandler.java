//package com.contratoAlert.ContratoAlertAplication.handler;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authorization.AuthorizationDeniedException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.Map;
//
//@RestControllerAdvice
//public class SecurityExceptionHandler {
//
//    @ExceptionHandler(AuthorizationDeniedException.class)
//    public ResponseEntity<?> handleAccessDenied() {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
//                Map.of(
//                        "error", "access_denied",
//                        "message", "Você não tem permissão para acessar este recurso"
//                )
//        );
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<?> handleAccessDeniedLegacy() {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
//                Map.of(
//                        "error", "access_denied",
//                        "message", "Acesso negado"
//                )
//        );
//    }
//}
//package com.contratoAlert.ContratoAlertAplication.handler;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authorization.AuthorizationDeniedException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.Map;
//
//@RestControllerAdvice
//public class SecurityExceptionHandler {
//
//    @ExceptionHandler(AuthorizationDeniedException.class)
//    public ResponseEntity<?> handleAccessDenied() {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
//                Map.of(
//                        "error", "access_denied",
//                        "message", "Você não tem permissão para acessar este recurso"
//                )
//        );
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<?> handleAccessDeniedLegacy() {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
//                Map.of(
//                        "error", "access_denied",
//                        "message", "Acesso negado"
//                )
//        );
//    }
//}
