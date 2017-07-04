package com.huami.midong.utils.calculators;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.huami.midong.utils.calculators.interfaces.Mapping;
import com.huami.midong.utils.calculators.interfaces.MatchCallback;
import com.huami.midong.utils.calculators.interfaces.RuleFactory;
import com.huami.midong.utils.calculators.models.EntryEntity;
import com.huami.midong.utils.calculators.models.FailReason;
import com.huami.midong.utils.calculators.models.IntervalEntity;
import com.huami.midong.utils.calculators.models.IntervalsEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * It provides some utilities to read XML, convert XML and so on.
 * <p>
 * Created by hm-su on 6/20/2017.
 */

public final class IntervalsHelper {

    /**
     * Get the xml file input stream from assets.
     *
     * @param fileName the xml file name
     * @return a xml file input stream
     */
    @Nullable
    public static InputStream getXmlStream(@NonNull Context context, @NonNull String fileName) {
        if (fileName.isEmpty())
            return null;

        try {
            return context.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Convert a x to an e.
     *
     * @param x       an instance of x
     * @param mapping mapping interface
     * @param <X>     stands for xml
     * @param <E>     stands for entity
     * @return an e instance
     */
    public static <X, E> E convert(X x, Mapping<X, E> mapping) {
        return mapping.map(x);
    }

    /**
     * Validate whether a value is a negative integer.
     *
     * @param value validated value
     * @return is a negative value
     */
    public static boolean validateNegativeInteger(int value) {
        return value < 0;
    }

    /**
     * If start equals end, that is, there is no interval space such as 100 - 100.
     * This is a wrong configuration.
     *
     * @param start start of interval tag
     * @param end   end of interval tag
     * @return has no interval region? true wrong configuration otherwise false.
     */
    public static boolean noIntervalRegion(int start, int end) {
        return start == end;
    }

    /**
     * There is no entries in the entry list.
     *
     * @param entryList a list of {@link EntryEntity}
     * @return true no entries otherwise false.
     */
    public static boolean noEntries(List<EntryEntity> entryList) {
        return entryList == null || entryList.isEmpty();
    }

    public static <E> boolean checkXmlEntities(RuleFactory<E> factory, E e) {
        return factory.getValidator().validate(e);
    }

    public static <D> D getCData(int input, @NonNull String id, @NonNull IntervalsEntity intervalsEntity, @NonNull MatchCallback callback) {

        /* id checking */
        if (id.isEmpty()) {
            return null;
        }

        IntervalEntity intervalEntity = findIntervalById(id, intervalsEntity);
        if (intervalEntity == null) {
            callback.onFail(new FailReason(0, "There is no interval with given id."));
            return null;
        }


    }

    /**
     * Find out an interval from interval list.
     *
     * @param id              interval id
     * @param intervalsEntity from xml
     * @return a founded interval entity
     */
    @Nullable
    private static IntervalEntity findIntervalById(@NonNull String id, @NonNull IntervalsEntity intervalsEntity) {
        List<IntervalEntity> intervalEntityList = intervalsEntity.getIntervalList();
        List<String> idList = new ArrayList<>(intervalEntityList.size());
        for (int i = 0; i < intervalEntityList.size(); i++) {
            idList.add(i, intervalEntityList.get(i).getId());
        }

        if (idList.indexOf(id) == -1) {
            return null;
        } else {
            return intervalEntityList.get(idList.indexOf(id));
        }
    }
}
