package com.huami.midong.utils.calculators.interfaces;

import android.support.annotation.NonNull;

/**
 * Mapping is an interface which providing a strategy about mapping a generic type of X to a generic type of E.
 * <p>
 * Created by hm-su on 6/20/2017.
 */
public interface Mapping<X, E> {
    /**
     * Map x to e.
     *
     * @param x an instance of x
     * @return an instance of e
     */
    E map(@NonNull X x);
}
