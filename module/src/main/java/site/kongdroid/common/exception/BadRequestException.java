package site.kongdroid.common.exception;

import site.kongdroid.common.constant.CommonCode;

public class BadRequestException extends DefaultNestedRuntimeExcepiton {
    public BadRequestException(String reason) {
        super(CommonCode.BAD_REQUEST_CODE, reason);
    }
}