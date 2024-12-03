package com.example.ecommerce.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
        private int status;
        private ResponseEntity<?> statusCode;
        private String message;
        public Object data;
        private List<ErrorDto> errors;

        public Response(int status, ResponseEntity<?> statusCode, String message, List<ErrorDto> errors) {
            this.status = status;
            this.statusCode = statusCode;
            this.message = message;
            this.errors = errors;
        }

        public Response(int status, String message, Object data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public Response(int successStatus, String schemaCreatedSuccessfully) {
        }
}
