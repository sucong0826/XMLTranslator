package com.huami.midong.utils.calculators.mapper;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Xml;

import com.huami.midong.utils.calculators.interfaces.Mapping;
import com.huami.midong.utils.calculators.models.EntryEntity;
import com.huami.midong.utils.calculators.models.IntervalEntity;
import com.huami.midong.utils.calculators.models.IntervalsEntity;
import com.huami.midong.utils.calculators.models.RootType;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.huami.midong.utils.calculators.Configs.ATTR_END;
import static com.huami.midong.utils.calculators.Configs.ATTR_FROM;
import static com.huami.midong.utils.calculators.Configs.ATTR_ID;
import static com.huami.midong.utils.calculators.Configs.ATTR_INDEX;
import static com.huami.midong.utils.calculators.Configs.ATTR_IS_EVEN;
import static com.huami.midong.utils.calculators.Configs.ATTR_ROOT_TYPE;
import static com.huami.midong.utils.calculators.Configs.ATTR_START;
import static com.huami.midong.utils.calculators.Configs.ATTR_TO;
import static com.huami.midong.utils.calculators.Configs.DEF_NS;
import static com.huami.midong.utils.calculators.Configs.TAG_ENTRY;
import static com.huami.midong.utils.calculators.Configs.TAG_INTERVAL;
import static com.huami.midong.utils.calculators.Configs.TAG_INTERVALS;

/**
 * XMLMapper is a kind of mapping strategy.
 * It will handle an input stream mapped to a {@link IntervalsEntity}.
 * <p>
 * Created by hm-su on 6/20/2017.
 */

public class XMLMapper implements Mapping<InputStream, IntervalsEntity> {

    // encoding type
    private static final String ENCODING_TYPE = "utf-8";

    @SuppressWarnings({"ConstantConditions", "StatementWithEmptyBody"})
    @Override
    public IntervalsEntity map(@NonNull InputStream inputStream) {
        // create an instance of IntervalsEntity
        IntervalsEntity intervalsEntity = null;

        try {
            /* declare entities and lists */
            IntervalEntity intervalEntity = null;
            EntryEntity<String> entryEntity;
            List<IntervalEntity> intervalList = null;
            List<EntryEntity> entryList = null;

            /* create xml pull parser and set it up */
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(inputStream, ENCODING_TYPE);

            // get event type
            int event = xmlPullParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {

                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        intervalsEntity = IntervalsEntity.initiateAsRoot();
                        break;

                    case XmlPullParser.START_TAG:
                        if (TAG_INTERVALS.equals(xmlPullParser.getName())) {
                            intervalList = new ArrayList<>();
                        } else if (TAG_INTERVAL.equals(xmlPullParser.getName())) {
                            /* parse interval entity */
                            entryList = new ArrayList<>();
                            intervalEntity = new IntervalEntity();
                            intervalEntity.setId(xmlPullParser.getAttributeValue(DEF_NS, ATTR_ID));
                            intervalEntity.setStart(Integer.parseInt(xmlPullParser.getAttributeValue(DEF_NS, ATTR_START)));
                            intervalEntity.setEnd(Integer.parseInt(xmlPullParser.getAttributeValue(DEF_NS, ATTR_END)));
                            intervalEntity.setEven(Boolean.parseBoolean(xmlPullParser.getAttributeValue(DEF_NS, ATTR_IS_EVEN)));
                            intervalEntity.setRootType(RootType.filter(xmlPullParser.getAttributeValue(DEF_NS, ATTR_ROOT_TYPE)));
                        } else if (TAG_ENTRY.equals(xmlPullParser.getName())) {
                            /* parse entry entity */
                            entryEntity = new EntryEntity<>();
                            entryEntity.setIndex(Integer.parseInt(xmlPullParser.getAttributeValue(DEF_NS, ATTR_INDEX)));
                            entryEntity.setFrom(Integer.parseInt(xmlPullParser.getAttributeValue(DEF_NS, ATTR_FROM)));
                            entryEntity.setTo(Integer.parseInt(xmlPullParser.getAttributeValue(DEF_NS, ATTR_TO)));
                            entryEntity.setTextPartT(xmlPullParser.nextText());
                            entryList.add(entryEntity);
                        } else {
                            Log.w("XMLMapper", "Sorry the tag is not supported now.");
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (TAG_INTERVALS.equals(xmlPullParser.getName())) {
                            intervalsEntity.setIntervalList(intervalList);
                        } else if (TAG_INTERVAL.equals(xmlPullParser.getName())) {
                            intervalEntity.setEntryList(entryList);
                            intervalList.add(intervalEntity);
                        } else if (TAG_ENTRY.equals(xmlPullParser.getName())) {
                            /* do nothing */
                        } else {
                            Log.w("XMLMapper", "Sorry the tag is not supported now.");
                        }
                        break;

                    default:
                        /* do nothing */
                        break;
                }

                // it's important to invoke, otherwise, event is always 0;
                event = xmlPullParser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return intervalsEntity;
    }
}
