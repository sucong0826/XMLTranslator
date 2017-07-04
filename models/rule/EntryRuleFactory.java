package com.huami.midong.utils.calculators.models.rule;

import com.huami.midong.utils.calculators.interfaces.RuleFactory;
import com.huami.midong.utils.calculators.interfaces.RulesValidate;

/**
 * This factory generates EntryRule.
 * <p>
 * Created by hm-su on 6/22/2017.
 */

public class EntryRuleFactory implements RuleFactory {
    @Override
    public RulesValidate getValidator() {
        return new EntryValidateRule();
    }
}
