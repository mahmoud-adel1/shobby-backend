package com.shobby.common.exception;

import java.time.Instant;

public record ApiError(int status, String error, String path, Instant timestamp) {

}
