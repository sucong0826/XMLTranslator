package com.huami.midong.utils.calculators.interfaces;

import com.huami.midong.utils.calculators.models.FailReason;

/**
 * MatchCallback is an interface which is focused on xml-entity matching.
 * <p>
 * Created by hm-su on 6/23/2017.
 */

public interface MatchCallback {

    /**
     * when matches successfully
     */
    void onSuccess();

    /**
     * when matches failed
     *
     * @param failReason the failReason instance of why failed.
     */
    void onFail(FailReason failReason);
}
