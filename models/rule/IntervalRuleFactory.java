package com.huami.midong.utils.calculators.models.rule;

import com.huami.midong.utils.calculators.interfaces.RuleFactory;
import com.huami.midong.utils.calculators.interfaces.RulesValidate;
import com.huami.midong.utils.calculators.models.IntervalEntity;

/**
 * IntervalRuleFactory is a child type of {@linkplain RuleFactory}.
 * <p>
 * Created by hm-su on 6/22/2017.
 */

public class IntervalRuleFactory implements RuleFactory {

    @Override
    public RulesValidate<IntervalEntity> getValidator() {
        return new IntervalValidateRule();
    }
}
