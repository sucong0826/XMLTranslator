package com.huami.midong.utils.calculators.models;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for XML tag &lt;intervals&gt; The tag is the root of XML file.
 * It includes no special attributes but has a list of interval as its children.
 * The list of interval can be empty,that is, there is no interval in it.
 * <p>
 * Created by hm-su on 6/20/2017.
 */

public class IntervalsEntity {

    /**
     * This list contains a set of interval entity.
     */
    private List<IntervalEntity> intervalList;

    private IntervalsEntity() {
        /* do-nothing */
    }

    public static IntervalsEntity initiateAsRoot() {
        return new IntervalsEntity();
    }

    public List<IntervalEntity> getIntervalList() {
        return intervalList;
    }

    public void setIntervalList(@Nullable List<IntervalEntity> intervalList) {
        this.intervalList = intervalList;
    }

    @Override
    public String toString() {
        return "IntervalsEntity{" +
                "intervalList=" + intervalList +
                '}';
    }
}
