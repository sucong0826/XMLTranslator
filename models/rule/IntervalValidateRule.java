package com.huami.midong.utils.calculators.models.rule;

import android.support.annotation.NonNull;

import com.huami.midong.utils.calculators.IntervalsHelper;
import com.huami.midong.utils.calculators.exceptions.IllegalXmlAttributeException;
import com.huami.midong.utils.calculators.exceptions.IllegalXmlCDATAException;
import com.huami.midong.utils.calculators.interfaces.RulesValidate;
import com.huami.midong.utils.calculators.models.EntryEntity;
import com.huami.midong.utils.calculators.models.IntervalEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * IntervalValidateRule is designated for tag <interval></interval>.
 * This tag should be validated because it has some important attributes.
 * Created by hm-su on 6/22/2017.
 */

public class IntervalValidateRule extends Rule implements RulesValidate<IntervalEntity> {

    @Override
    public boolean validate(@NonNull IntervalEntity validation) {
        /*
            attribute 'start' and 'end' negative checking
         */
        int start = validation.getStart();
        if (IntervalsHelper.validateNegativeInteger(start)) {
            throw new IllegalXmlAttributeException("Tag <interval> attribute 'start' is an integer can not be negative which is not supported temporarily.");
        }

        int end = validation.getEnd();
        if (IntervalsHelper.validateNegativeInteger(end)) {
            throw new IllegalXmlAttributeException("Tag <interval> attribute 'start' is an integer can not be negative which is not supported temporarily.");
        }

        /* equation checking */
        if (IntervalsHelper.noIntervalRegion(start, end)) {
            throw new IllegalXmlAttributeException("Tag attributes values of start and end cannot be equaled.");
        }

        /* entry list checking */
        if (IntervalsHelper.noEntries(validation.getEntryList())) {
            throw new IllegalXmlCDATAException("Tag <interval> must have <entry>s as its children.");
        }

        List<EntryEntity> entities = validation.getEntryList();
        for (int i = 0; i < entities.size(); i++) {
            if (i < entities.size()) {
                int indexCurrent = entities.get(i).getIndex();
                int indexNext = entities.get(i + 1).getIndex();

                /* index current and next can't be same. */
                if (indexCurrent == indexNext) {
                    throw new IllegalXmlAttributeException("Index must be sequential and there is no two indexes same with each other.");
                }
            }
        }

        // noinspection unchecked
        Collections.sort(entities);

        if (entities.size() > 1) {
            EntryEntity first = entities.get(0);
            EntryEntity last = entities.get(entities.size() - 1);

            if (first.getFrom() != start || last.getTo() != end) {
                throw new IllegalXmlAttributeException("Value from must be equal to start, value to must be equal to end.");
            }
        }

        /* sequential check */
        boolean isSequential = isSequential(flat(entities));
        if (!isSequential) {
            throw new IllegalXmlCDATAException("Entries must be sequential.");
        }

        /* even check */
        boolean evenCheck = isEven(flat(entities), validation.isEven());
        if (!evenCheck) {
            throw new IllegalXmlAttributeException("You specify 'is-even' true/false, but entry list are not satisfied.");
        }

        // here return true just indicates that <interval> tag passes validation, its children are not checked yet.
        return true;
    }

    /**
     * Flat a entry list which has values of from and to.
     *
     * @param entries entry list
     * @return a flatted integer list
     */
    private List<Integer> flat(@NonNull List<EntryEntity> entries) {
        /*
            empty checking
         */
        if (entries.isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> flattedIntegersList = new ArrayList<>();

        /* flatting */
        for (EntryEntity item : entries) {
            flattedIntegersList.add(item.getFrom());
            flattedIntegersList.add(item.getTo());
        }

        return flattedIntegersList;
    }

    /**
     * Check a list of integers whether they are sequential.
     *
     * @param integers target integer list
     * @return true sequential false otherwise
     */
    private boolean isSequential(@NonNull List<Integer> integers) {

        /* integer list must be a even-size list. */
        if (integers.size() % 2 != 0)
            throw new RuntimeException("Elements must be even.");

        /* empty checking */
        if (integers.isEmpty())
            return false;

        final int start = 1 /* start position from 1 not 0 */;
        final int endPos = integers.size() - 1;

        for (int i = start; i < endPos; i++) {
            boolean isEqual = integers.get(i).intValue() != integers.get(i + 1).intValue();
            boolean isInterleaving = Math.abs(integers.get(i) - integers.get(i + 1)) == 1;
            if (!isEqual || !isInterleaving) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check whether entries are satisfied are even.
     *
     * @param integers       interval's from(s) and to(s) are hold into an list
     * @param isEvenRequired attribute value of is-even
     * @return whether entries are satisfied with values are even.
     */
    private boolean isEven(List<Integer> integers, boolean isEvenRequired) {

        /* integer list must be a even-size list. */
        if (integers.size() % 2 != 0)
            throw new RuntimeException("Elements must be even.");

        int groups = integers.size() / 2 /* each group has two elements */;
        final int step = 2;
        List<Integer> minusResultList = new ArrayList<>(groups);

        for (int counter = 0; counter < integers.size(); counter += step) {
            int minusResult = Math.abs(integers.get(counter) - integers.get(counter + 1));
            minusResultList.add(minusResult);
        }

        int delta = 0;
        for (int i = 0; i < minusResultList.size(); i++) {
            delta = minusResultList.get(i) - minusResultList.get(i + 1);
        }

        if (isEvenRequired) {
            return delta == 0;
        } else {
            return delta != 0;
        }
    }
}
