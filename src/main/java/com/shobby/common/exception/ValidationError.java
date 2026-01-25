package com.shobby.common.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record ValidationError(String field, String errorCode) {
}
