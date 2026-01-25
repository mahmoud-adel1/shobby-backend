package com.shobby.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.Instant;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(int statusCode, String errorCode, String path, Instant timestamp, Object details) {

}
