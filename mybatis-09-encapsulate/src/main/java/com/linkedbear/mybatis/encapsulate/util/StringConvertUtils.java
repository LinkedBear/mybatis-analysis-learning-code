package com.linkedbear.mybatis.encapsulate.util;

public abstract class StringConvertUtils {
    
    public static String underscoreToCamelCase(String source) {
        if (!source.contains("_")) {
            return source;
        }
        char[] chars = source.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean nextUpper = false;
        for (char c : chars) {
            if (c == '_') {
                nextUpper = true;
            } else if (nextUpper) {
                sb.append(Character.toUpperCase(c));
                nextUpper = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    public static String camelCaseToUnderscore(String source) {
        char[] chars = source.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (Character.isUpperCase(c)) {
                sb.append('_').append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
}
