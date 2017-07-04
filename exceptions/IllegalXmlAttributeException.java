package com.huami.midong.utils.calculators.exceptions;

/**
 * When users or developers use wrong/illegal attributes, this ex will be thrown.
 * <p>
 * Created by hm-su on 6/22/2017.
 */

public class IllegalXmlAttributeException extends IllegalArgumentException {

    public IllegalXmlAttributeException(String cause) {
        super(cause);
    }
}
