package com.lemon.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.util.HtmlUtils;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by simpletour_Jenkin on 2016/8/3.
 */
public class StringUtils {
    public static final int CR = 13; // <US-ASCII CR, carriage return (13)>
    public static final int LF = 10; // <US-ASCII LF, linefeed (10)>
    public static final int SP = 32; // <US-ASCII SP, space (32)>
    public static final int HT = 9; // <US-ASCII HT, horizontal-tab (9)>
    public static final String EMPTY = "";
    public static final String EMPTYSTRING = "\"\"";
    public static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";
    public static final String HEX_STRING = "0123456789abcdef";
    public static final char[] HEX_DIGITS = HEX_STRING.toCharArray();
    public static final char[] ALPHANUM = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final Pattern EMAIL = Pattern.compile("^[_a-zA-Z\\d\\-\\.]+@[_a-zA-Z\\d\\-]+(\\.[_a-zA-Z\\d\\-]+)+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern JSON = Pattern.compile("^\\s*[\\{\\[].*?[\\}\\]]\\s*$");
    public static final Pattern JSON_OBJECT = Pattern.compile("^\\s*\\{.*?\\}\\s*$");
    public static final Pattern JSON_ARRAY = Pattern.compile("^\\s*\\[.*?\\]\\s*$");

    /**
     * 格式化Url
     *
     * @param requestURI
     * @param queryString
     * @return
     */
    public static String formatURI(String requestURI, String queryString) {
        return String.format("%s%s", requestURI, Optional.ofNullable(queryString).isPresent() ? "?".concat(queryString) : "").trim();
    }


    /**
     * 转换成DaoImpl字符串
     *
     * @param str
     * @return
     */
    public static String toDaoImpl(String str) {
        return toFirstLowerCase(str) + "Dao" + "Impl";
    }


    /**
     * 首字符大写
     *
     * @param str
     * @return
     */
    public static String toFirstUpCase(String str) {
        return toSomeCase(str, String::toUpperCase);
    }

    /**
     * 首字符小写
     *
     * @param str
     * @return
     */
    public static String toFirstLowerCase(String str) {
        return toSomeCase(str, String::toLowerCase);
    }

    private static String toSomeCase(String str, Function<String, String> fun) {
        return fun.apply(str.substring(0, 1)) + str.substring(1, str.length());
    }

    /**
     * @param s
     * @return
     */
    public static boolean notEmpty(final String s) {
        return s != null && !s.trim().equals("");
    }

    public static boolean isEmpty(final String s) {
        return !notEmpty(s);
    }

    /**
     * @param bytes
     * @return
     */
    public static String toHexString(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    /**
     * @param l
     * @return
     */
    public static String toHexString(int[] l) {
        StringBuffer stringBuffer = new StringBuffer();
//        String m = "";
        for (int j = 0; j < l.length * 4; j++) {
            stringBuffer.append(HEX_DIGITS[(l[j >> 2] >> ((3 - j % 4) * 8 + 4)) & 15])
                    .append(HEX_DIGITS[(l[j >> 2] >> ((3 - j % 4) * 8)) & 15]);
//            m += HEX_DIGITS[(l[j >> 2] >> ((3 - j % 4) * 8 + 4)) & 15] + HEX_DIGITS[(l[j >> 2] >> ((3 - j % 4) * 8)) & 15];
        }
        return stringBuffer.toString();
    }

    /**
     * @param hexstr
     * @return
     */
    public static byte[] fromHexString(String hexstr) {
        hexstr = StringUtils.notEmpty(hexstr) ? hexstr.toLowerCase() : StringUtils.EMPTY;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(hexstr.length() / 2);
        for (int i = 0; i < hexstr.length(); i = i + 2) {
            baos.write((HEX_STRING.indexOf(hexstr.charAt(i)) << 4 | HEX_STRING.indexOf(hexstr.charAt(i + 1))));
        }
        return baos.toByteArray();
    }

    /**
     * @param ch
     * @return
     */
    public static boolean isWhitespace(final char ch) {
        return ch == SP || ch == HT || ch == CR || ch == LF;
    }

    /**
     * @param s
     * @return
     */
    public static boolean isDigit(final String s) {
        return StringUtils.notEmpty(s) && s.trim().matches("\\d+");
    }

    /**
     * @param str
     * @return
     */
    public static String replaceHtmlSpecials(String str) {
        str = str.replaceAll("&gt;", ">");
        str = str.replaceAll("&lt;", "<");
        str = str.replaceAll("&quot;", "\"");
        str = str.replaceAll("&ldquo;", "”");
        str = str.replaceAll("&rdquo;", "“");
        str = str.replaceAll("&nbsp;", " ");
        str = str.replaceAll("&amp;", "&");
        str = str.replaceAll("&mdash;", "-");
        return str;
    }

    /**
     * @param str
     * @return
     */
    public static String replaceHtmlSpecialsToString(String str) {
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("-", "&mdash;");
        return str;
    }


    /**
     * 替换文本中的空白
     *
     * @param str         被替换的字符串
     * @param replacement 替换文本
     * @return
     */
    public static String replaceWhiteSpace(String str, String replacement) {
        return str.replaceAll("\\p{javaWhitespace}", replacement);
    }

    /**
     * 用空字符串替换文本中的空白，是方法{@link StringUtils#replaceWhiteSpace}的重载版本
     *
     * @param str
     * @return
     * @see StringUtils#replaceWhiteSpace(String, String)
     */
    public static String replaceWhiteSpace(String str) {
        return str.replaceAll("\\p{javaWhitespace}", "");
    }

    /**
     * 检测输入数据是否是email
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return StringUtils.notEmpty(email) && EMAIL.matcher(email).matches();
    }

    /**
     * @param value
     * @return
     */
    public static boolean isJSONObject(String value) {
        return StringUtils.notEmpty(value) && JSON_OBJECT.matcher(value).matches();
    }

    /**
     * 获取两个字符串的最长公共子序列，不要求连续，按顺序出现即可
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String LCS(String str1, String str2) {
        int l1 = str1.length(), l2 = str2.length();
        int[][] arr = new int[l1 + 1][l2 + 1];

        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j))
                    arr[i][j] = arr[i + 1][j + 1] + 1;
                else
                    arr[i][j] = Math.max(arr[i + 1][j], arr[i][j + 1]);
            }
        }

        int i = 0, j = 0;
        StringBuffer sb = new StringBuffer();
        while (i < l1 && j < l2) {
            if (str1.charAt(i) == str2.charAt(j)) {
                sb.append(str1.charAt(i));
                i++;
                j++;
            } else if (arr[i + 1][j] >= arr[i][j + 1])
                i++;
            else
                j++;
        }
        return sb.toString();
    }

    /**
     * 隐藏手机号的中间四位
     * @param mobile
     * @return
     */
    public static String mobileHideSomeNumbers(String mobile){
        if (mobile != null && mobile.length() == 11){
            StringBuffer head = new StringBuffer(mobile.substring(0,3));
            String tail = mobile.substring(7,11);
            return head.append("****").append(tail).toString();
        }
        return "";
    }

    public static Set<String> lcs(String str1, String str2, Set<String> sequences) {
        if (str1.isEmpty() || str2.isEmpty()) return sequences;
        if (str1.charAt(0) == str2.charAt(0)) {
            Set<String> suffixes = lcs(str1.substring(1), str2.substring(1), new TreeSet<>());
            sequences.addAll(suffixes.stream().map(suffix -> str1.charAt(0) + suffix).collect(Collectors.toList()));
        } else {
            sequences.addAll(lcs(str1.substring(1), str2, new TreeSet<>()));
            sequences.addAll(lcs(str1, str2.substring(1), new TreeSet<>()));
        }
        return sequences;
    }

    public static List<String> extractImgAnnotation(String text) {
        Document document = Jsoup.parse(HtmlUtils.htmlUnescape(text));
        return document.select("img").stream().map(element -> element.attr("src"))
                .filter(src -> !(src == null || src.isEmpty() || src.matches("^[#/].*"))).map(src -> src.split("!")[0]).collect(Collectors.toList());
    }

}
