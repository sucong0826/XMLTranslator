package com.huami.midong.utils.calculators.models.rule;

import android.support.annotation.NonNull;

import com.huami.midong.utils.calculators.IntervalsHelper;
import com.huami.midong.utils.calculators.exceptions.IllegalXmlAttributeException;
import com.huami.midong.utils.calculators.interfaces.RulesValidate;
import com.huami.midong.utils.calculators.models.EntryEntity;

/**
 * EntryValidateRule is designated for tag <entry></entry>.
 * This tag should be validated because it has some important attributes.
 * <p>
 * Created by hm-su on 6/22/2017.
 */

public class EntryValidateRule extends Rule implements RulesValidate<EntryEntity> {

    @Override
    public boolean validate(@NonNull EntryEntity entryEntity) {

        /* index checking */
        int index = entryEntity.getIndex();
        if (IntervalsHelper.validateNegativeInteger(index)) {
            throw new IllegalXmlAttributeException("Tag <entry> attribute 'index' is an integer can not be negative which is not supported temporarily.");
        }

        /*
            from checking
            to   checking
         */
        int from = entryEntity.getFrom();
        if (IntervalsHelper.validateNegativeInteger(from)) {
            throw new IllegalXmlAttributeException("Tag <entry> attribute 'from' is an integer can not be negative which is not supported temporarily.");
        }

        int to = entryEntity.getTo();
        if (IntervalsHelper.validateNegativeInteger(to)) {
            throw new IllegalXmlAttributeException("Tag <entry> attribute 'to' is an integer can not be negative which is not supported temporarily.");
        }

        /* equation checking */
        if (IntervalsHelper.noIntervalRegion(from, to)) {
            throw new IllegalXmlAttributeException("Tag attributes values of start and end cannot be equaled.");
        }

        return true;
    }
}
