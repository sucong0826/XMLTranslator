package com.huami.midong.utils.calculators.interfaces;

/**
 * RuleFactory is for generating a set of class that implements {@link RulesValidate}.
 * <p>
 * Created by hm-su on 6/22/2017.
 */

public interface RuleFactory<E> {
    /**
     * Get an invalidator.
     *
     * @return classes which implements {@link RulesValidate}.
     */
    RulesValidate<E> getValidator();
}
