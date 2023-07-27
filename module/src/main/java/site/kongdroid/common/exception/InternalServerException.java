package site.kongdroid.common.exception;

import site.kongdroid.common.constant.CommonCode;

public class InternalServerException extends DefaultNestedRuntimeExcepiton {
    public InternalServerException(String reason) {
        super(CommonCode.INTERNAL_SERVER_ERROR_CODE, reason);
    }

}