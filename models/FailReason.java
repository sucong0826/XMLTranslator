package com.huami.midong.utils.calculators.models;

/**
 * This object is for failure reason when developers pass an argument of id but nothing hit.
 * <p>
 * Created by hm-su on 6/23/2017.
 */

public class FailReason {
    /**
     * type of failure
     */
    private int type;

    /**
     * failure content
     */
    private String reason;

    public FailReason(int type, String reason) {
        this.type = type;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "FailReason{" +
                "reason='" + reason + '\'' +
                ", type=" + type +
                '}';
    }
}
