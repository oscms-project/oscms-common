package com.osc.oscms.common.exception.MaterialException;

import com.osc.oscms.common.exception.BaseException;

/**
 * 资料版本未找到异常
 */
public class MaterialVersionNotFoundException extends BaseException {

    public MaterialVersionNotFoundException(String message) {
        super("MATERIAL_VERSION_NOT_FOUND", message);
    }

    public MaterialVersionNotFoundException(String message, Throwable cause) {
        super("MATERIAL_VERSION_NOT_FOUND", message, cause);
    }
}

