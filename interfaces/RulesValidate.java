package com.huami.midong.utils.calculators.interfaces;

/**
 * RuleValidate is an interface to express some classes have an ability of validating.
 * <p>
 * Created by hm-su on 6/22/2017.
 */

public interface RulesValidate<E> {

    /**
     * Validate whether the rule passes.
     *
     * @param e some entity needs to be validated
     * @return true validate passes otherwise false
     */
    boolean validate(E e);
}
