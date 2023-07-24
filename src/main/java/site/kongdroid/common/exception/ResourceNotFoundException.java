package site.kongdroid.common.exception;


import site.kongdroid.common.constant.CommonCode;

public class ResourceNotFoundException extends DefaultNestedRuntimeExcepiton {
    public ResourceNotFoundException(String reason) {
        super(CommonCode.RESOURCE_NOT_FOUND_CODE, reason);
    }
}