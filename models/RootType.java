package com.huami.midong.utils.calculators.models;

import static com.huami.midong.utils.calculators.Configs.CONST_EX_EX;
import static com.huami.midong.utils.calculators.Configs.CONST_EX_IN;
import static com.huami.midong.utils.calculators.Configs.CONST_IN_EX;

/**
 * RootType is an enumeration class.
 * It is easy to understand the aim and goal of its designation.
 * It is used to setup the involvement of start and end value.
 * For more details to see the doc of each enumeration.
 * <p>
 * Created by hm-su on 6/20/2017.
 */

public enum RootType {
    /**
     * the start value is inclusive, the end value is exclusive.
     * for instance:
     * 0 <= x < 20;
     */
    INCLUSIVE_EXCLUSIVE,

    /**
     * the start value is exclusive, the end value is inclusive.
     * for instance:
     * 0 < x <= 20;
     */
    EXCLUSIVE_INCLUSIVE,

    /**
     * the start value is exclusive, the end value is exclusive.
     * 0 < x < 20;
     */
    EXCLUSIVE_EXCLUSIVE,

    /**
     * unknown root type.
     */
    UNKNOWN;

    /**
     * Return an enumeration instance root type according to string value of root type.
     *
     * @param strRootType the string value of root type.
     * @return the instance of enumeration root type.
     */
    public static RootType filter(String strRootType) {
        switch (strRootType) {
            case CONST_IN_EX:
                return INCLUSIVE_EXCLUSIVE;

            case CONST_EX_IN:
                return EXCLUSIVE_INCLUSIVE;

            case CONST_EX_EX:
                return EXCLUSIVE_EXCLUSIVE;

            default:
                return UNKNOWN;
        }
    }
}
