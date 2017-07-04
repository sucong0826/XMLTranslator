package com.huami.midong.utils.calculators.models;

import java.util.List;

/**
 * An IntervalEntity wants to express a segment of intervals.
 * Such as 0 - 100 is-even, that is, an interval segment maybe indicates 0 - 20.
 * <p>
 * An interval entity has several attributes：
 * `interval` has five attributes which are id, is-even, root-type, start,end.
 * ` id:           id is a unique identifier to indicate the interval is the only one existing in this xml config.
 * ` is-even:      indicating that whether the distribution is even or not. true means even, false otherwise.
 * ` root-type:    root-type is an enumeration type. It means the situations of values of boundaries.
 * It has three enums:
 * ` inclusive_exclusive
 * ` exclusive_inclusive
 * ` exclusive_exclusive
 * ` start:        start is a value of integer type which means that the start value of interval. Such as 0 as general one.
 * ` end:          end is a value of integer type which means that the end value of interval. Such as 100 as general one.
 * Created by hm-su on 6/20/2017.
 */

public class IntervalEntity<D> {

    private final static int ID_NONE = 0;
    private final static int DEF_START = 0;
    private final static int DEF_END = 100;

    /**
     * id is the unique identifier to identify an interval item.
     * As it is unique, so there cannot be duplicated ids.
     * If any, XML Schema has obligation to check that.
     */
    private String id = String.valueOf(ID_NONE);

    /**
     * is-even is easy to understand.
     * for instance:
     * 0 - 100, if true, the interval should be divided to several even segments depending on your divisions.
     * 0 - 20, 20 - 40, 40 - 80.
     * if false, that is, those segments are not even. you can give them values as you want.
     * Please note that, is-even is used to indicate and check.
     * If you set it with true but give them random segments, some checkers may tell you've made a wrong setting.
     */
    private boolean isEven = true;

    /**
     * rootType is an instance of this enumeration {@linkplain RootType}.
     * It is used to setup the involvement of start and end value.
     */
    private RootType rootType = RootType.INCLUSIVE_EXCLUSIVE;

    /**
     * The start value for the whole interval.
     * <p>
     * <strong>
     * And now it can be merely set with an positive integer(0 included). Negative integer is not supported temporarily.
     * </strong>
     */
    private int start = DEF_START;

    /**
     * The end value for the whole interval.
     * <p>
     * <strong>
     * And now it can be merely set with an positive integer(0 included). Negative integer is not supported temporarily.
     * In the meanwhile, please note that, {@linkplain IntervalEntity#start} should be small than {IntervalEntity#end}.
     * If not, some checkers will throw some exception.
     * </strong>
     */
    private int end = DEF_END;

    private List<EntryEntity<D>> entryList;

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public boolean isEven() {
        return isEven;
    }

    public void setEven(boolean even) {
        isEven = even;
    }

    public RootType getRootType() {
        return rootType;
    }

    public void setRootType(RootType rootType) {
        this.rootType = rootType;
    }

    public List<EntryEntity<D>> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<EntryEntity<D>> entryList) {
        this.entryList = entryList;
    }

    @Override
    public String toString() {
        return "IntervalEntity{" +
                "end=" + end +
                ", id='" + id + '\'' +
                ", isEven=" + isEven +
                ", rootType=" + rootType +
                ", start=" + start +
                ", entryList=" + entryList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntervalEntity that = (IntervalEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
