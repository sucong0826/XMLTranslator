package com.huami.midong.utils.calculators.exceptions;

/**
 * XML's CDATA part checking will use this exception.
 * For example, if developers set CDATA as a value of string type, but with a type of integer,
 * at this moment, this exception will be thrown, it indicates that CDATA is a wrong type as there set as standard.
 * <p>
 * Created by hm-su on 6/22/2017.
 */

public class IllegalXmlCDATAException extends RuntimeException {

    public IllegalXmlCDATAException(String cause) {
        super(cause);
    }
}
