package com.bonaparte.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangmingquan on 2018/9/6.
 */
public class PatternUtil {
    public PatternUtil() {
    }

    public static String matcherGroupOne(Pattern pattern, CharSequence input, int group) {
        Matcher matcher = pattern.matcher(input);
        return matcher.find()?matcher.group(group):null;
    }

    public static String matcherGroupOne(String regex, CharSequence input, int group) {
        Pattern pattern = Pattern.compile(regex);
        return matcherGroupOne(pattern, input, group);
    }

    public static List<String> matcherGroupList(Pattern pattern, CharSequence input, int group) {
        Matcher matcher = pattern.matcher(input);
        ArrayList result = new ArrayList();

        while(matcher.find()) {
            result.add(matcher.group(group));
        }

        return result;
    }

    public static List<String> matcherGroupList(String regex, CharSequence input, int group) {
        Pattern pattern = Pattern.compile(regex);
        return matcherGroupList(pattern, input, group);
    }

    public static boolean matcher(Pattern pattern, CharSequence input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
