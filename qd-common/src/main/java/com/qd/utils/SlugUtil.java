package com.qd.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import java.text.Normalizer;
import java.util.Locale;

public final class SlugUtil {
    private static final int MAX_LEN = 80;

    /** 把标题转为 slug（支持中文转拼音） */
    public static String slugifyWithPinyin(String title) {
        if (title == null) return "post";

        // 1) 中文转拼音
        String withPinyin = toPinyin(title);

        // 2) 基础 slug 化
        return slugify(withPinyin);
    }

    /** 基础 slugify：英文/数字处理，拼音也走这里 */
    private static String slugify(String input) {
        String s = input.toLowerCase(Locale.ROOT).trim();

        // 去掉音标
        s = Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // 非字母数字 -> "-"
        s = s.replaceAll("[^a-z0-9]+", "-");

        // 合并多个 "-"
        s = s.replaceAll("-{2,}", "-");

        // 去头尾 "-"
        s = s.replaceAll("(^-+|-+$)", "");

        // 截断
        if (s.length() > MAX_LEN) {
            s = s.substring(0, MAX_LEN);
            s = s.replaceAll("(^-+|-+$)", "");
        }

        return s.isEmpty() ? "post" : s;
    }

    /** 中文转拼音（不带声调），保留非中文字符 */
    private static String toPinyin(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.toString(c).matches("[\\u4e00-\\u9fa5]")) {
                String[] pys = PinyinHelper.toHanyuPinyinStringArray(c);
                if (pys != null && pys.length > 0) {
                    sb.append(pys[0].replaceAll("\\d", "")).append(" ");
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
