# XMLTranslator
XML helps Java define interval.

```xml
<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<intervals xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:noNamespaceSchemaLocation="health_score_intervals.xsd">

    <interval id="fatigue_calculation" start="0" end="100" root-type="inclusive_exclusive" is-even="true">
        <entry index="1" from="0" to="20">Excellent</entry>
        <entry index="2" from="20" to="40">Good</entry>
        <entry index="3" from="60" to="80">General</entry>
        <entry index="4" from="80" to="100">Bad</entry>
    </interval>

</intervals>
```
Think about your code of Java defining an interval like this:
``` java
  public static int getHeartLevel(final int value) {
        if (value >= 0 && value <= 64) return BAD;
        else if (value >= 65 && value <= 79) return GENERAL;
        else if (value >= 80 && value <= 89) return GOOD;
        else if (value >= 90 && value <= 100) return EXCELLENT;
        return NO_LEVEL;
    }
```

If you can use an XML which is able to own its XML Schema to verify, it is better than place this phase of Java code.
In the meanwhile, XMLTranslator provides some APIs for developers to access and get CDATA/PCDATA part for you to be used. It will be updated later.
