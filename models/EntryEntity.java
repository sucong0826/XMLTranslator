package com.huami.midong.utils.calculators.models;

import android.support.annotation.NonNull;

/**
 * EntryEntity is the last layer for the whole xml document.
 * It is a segment of {@link IntervalEntity}.
 * It has three attributes:
 * 1) index: the index of entry item. It must be sequential. It acts as the role like unique id with value of int type.
 * 2) from:  the start value of entry. It is an int type which must be smaller than end(NO EQUALS).
 * 3) to:    the end value of entry. It is an int type which must be larger than end(NO EQUALS).
 * The three attrs are required.
 * <p>
 * The part of text of entry are diverse.
 * It includes integer/double/float/string. If we want to define some entities from external, it is ok.
 * <p>
 * The generic type T should be checked within types of Integer, Double, Float, String.
 * Created by hm-su on 6/20/2017.
 */

public class EntryEntity<D> implements Comparable<EntryEntity> {

    /**
     * the index of entry item.
     */
    private int index;

    /**
     * the start value of entry.
     */
    private int from;

    /**
     * the end value of entry.
     */
    private int to;

    /**
     * It includes integer/double/float/string.
     * Such as DTD's CDATA/PCDATA
     */
    private D textPartT;

    /* please note that: fromInclusive and toInclusive cannot be true at the same time. */
    /**
     * the from value is inclusive or not.
     * true:  inclusive
     * false: exclusive
     */
    private boolean fromInclusive;

    /**
     * the to value is inclusive or not.
     * true:  inclusive
     * false: exclusive
     */
    private boolean toInclusive;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public boolean isFromInclusive() {
        return fromInclusive;
    }

    public void setFromInclusive(boolean fromInclusive) {
        this.fromInclusive = fromInclusive;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public D getTextPartT() {
        return textPartT;
    }

    public void setTextPartT(D textPartT) {
        this.textPartT = textPartT;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public boolean isToInclusive() {
        return toInclusive;
    }

    public void setToInclusive(boolean toInclusive) {
        this.toInclusive = toInclusive;
    }

    @Override
    public String toString() {
        return "EntryEntity{" +
                "from=" + from +
                ", index=" + index +
                ", to=" + to +
                ", textPartT=" + textPartT +
                ", fromInclusive=" + fromInclusive +
                ", toInclusive=" + toInclusive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntryEntity<?> that = (EntryEntity<?>) o;

        return index == that.index;
    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public int compareTo(@NonNull EntryEntity another) {
        return this.index - another.index;
    }
}
