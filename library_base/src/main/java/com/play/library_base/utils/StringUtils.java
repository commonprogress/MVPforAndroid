/*
 * Copyright (c) 2018.
 * Author：Zhao
 * Email：joeyzhao1005@gmail.com
 */

package com.play.library_base.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jhonjson on 2019/8/09
 */
public class StringUtils {

    /**
     * 防止空字符串
     *
     * @param value
     * @return
     */
    public static String setNotNull(String value) {
        if (TextUtils.isEmpty(value)) {
            value = "";
        }
        return value;
    }

    /**
     * 获取加密的手机号码
     *
     * @param phone
     * @return
     */
    public static String getSecFomatPhone(String phone) {
        phone = setNotNull(phone);
        if (phone.length() < 11) {
            return phone;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(phone.substring(0, 3));
        stringBuilder.append("****");
        stringBuilder.append(phone.substring(7));
        return stringBuilder.toString();
    }

    /**
     * 判定是否为null，或是否为""
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }


    /**
     * 判定是否为empty(null或"")或为字符串"null"
     *
     * @param str
     * @return
     */
    public static boolean isEmptyOrNullStr(String str) {
        return (TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str.trim()) || "".equals(str.trim()));
    }

    public static String newGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getTimeStamp() {
        return System.currentTimeMillis() / 1000 + "";
    }

    public static String getNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(System.currentTimeMillis());
    }

    public static String getNow2() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        return formatter.format(System.currentTimeMillis());
    }

    public static String convertTimestampToHourAndMins(long timeStamp) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(timeStamp);
    }

    public static String convertTimestampToYYYYMMDDHHmm(long timeStamp) {
        if (timeStamp < 10000000000l) {
            timeStamp = timeStamp * 1000;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(timeStamp);
    }

    public static String convertTimestampToYYYYMMDDHHmmss(long timeStamp) {
        if (timeStamp < 10000000000l) {
            timeStamp = timeStamp * 1000;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(timeStamp);
    }

    public static String convertTimestampToMMDDHHmm(long timeStamp) {
        if (timeStamp < 10000000000l) {
            timeStamp = timeStamp * 1000;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
        return formatter.format(timeStamp);
    }

    public static String convertNum2f(double num) {
        return String.format("%.2f", num);
//        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
//        df.format(num);
    }

    public static String convertNum(double num) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
        return df.format(num);
    }


    public static String convertTimestampToMMDD(long timeStamp) {
        if (timeStamp < 10000000000l) {
            timeStamp = timeStamp * 1000;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        return formatter.format(timeStamp);
    }

//    public static long convertStringToTimesstamp(String date) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            return sdf.parse(date).getTime();
//        } catch (Exception e) {
//            return System.currentTimeMillis();
//        }
//    }

    public static int[] getStringYearMonthDay(String date) {
        if (null == date || "".equalsIgnoreCase(date) || !date.contains("-")) {
            return null;
        }
        String[] dateStr = date.split("-");
        if (null == dateStr || dateStr.length != 3) {
            return null;
        }
        int[] retDate = new int[3];
        for (int i = 0; i < 3; i++) {
            retDate[i] = Integer.parseInt(dateStr[i]);
        }
        return retDate;
    }


    /**
     * 判断是否为合法的日期时间字符串
     *
     * @param str_input
     * @param rDateFormat
     * @return boolean;符合为true,不符合为false
     */
    public static boolean isDate(String str_input, String rDateFormat) {
        if (!isEmpty(str_input)) {
            SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat);
            formatter.setLenient(false);
            try {
                formatter.format(formatter.parse(str_input));
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static int convertDpToPixel(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    public static String getStringHead(String name) {
        if ("".equals(name)) {
            return "";
        } else if (Character.isDigit(name.charAt(0))) {
            return "#";
        } else {
            name = HanziToPinyin.getInstance().get(name.substring(0, 1))
                    .get(0).target.substring(0, 1).toUpperCase();
            char header = name.toLowerCase().charAt(0);
            if (header < 'a' || header > 'z') {
                return "#";
            }
        }
        return name;
    }

    /**
     * 判断URL是否合法
     *
     * @param url
     * @return
     */
    public static boolean isUrlLegal(String url) {
        if (null == url) {
            return false;
        }
        Pattern p = Pattern.compile("[a-zA-z]+://[^\\s]*");
        Matcher m = p.matcher(url);
        return (m.matches());
    }

    /**
     * 判断URL是否合法
     *
     * @param url
     * @return
     */
    public static boolean urlLegal(String url) {
        if (null == url) {
            return false;
        }
        String regex = "((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)";
        return Pattern.matches(regex, url);
    }

    /**
     * 判断手机号码是否合法 (以"1"开始，后边跟10位数字)
     *
     * @param mobiles 手机号码
     * @return true表示合法，false表示不合法
     */
    public static boolean isMobileNumLegal(String mobiles) {
        if (null == mobiles) {
            return false;
        }
        Pattern p = Pattern.compile("^(1)\\d{10}$");
        Matcher m = p.matcher(mobiles);
        return (m.matches());
    }

    /**
     * 判断手机号码是否合法
     * 当国家是中国时(+86),校验是否是以1开头的11位数字,其他地区只要求输入1个数字就可以
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNumLegalNew(String code, String mobiles) {
        if (isEmpty(code) || isEmpty(mobiles)) {
            return false;
        }

        Pattern p;
        if ("+86".equalsIgnoreCase(code)) {
            p = Pattern.compile("^(1)\\d{10}$");
        } else {
            p = Pattern.compile("^\\d+$");
        }
        Matcher m = p.matcher(mobiles);
        return (m.matches());
    }

    /**
     * 判断电子邮件是否合法
     *
     * @param email
     * @return true表示合法，false表示不合法
     */
    public static boolean isEmailLegal(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    /**
     * 判断密码输入是否合法 6-12位
     *
     * @param pwd
     * @return
     */
    public static boolean isPasswordLegal(String pwd) {
//        boolean flag = false;
//        try {
//            String check = "[a-zA-Z0-9_]{6,12}$";
//            Pattern regex = Pattern.compile(check);
//            Matcher matcher = regex.matcher(pwd);
//            flag = matcher.matches();
//        } catch (Exception e) {
//            flag = false;
//        }
//        return flag;
        //现在要求在服务器端进行密码校验,本地只判断密码的长度是否符合规定.此判断方法只是为获取验证码及登录,注册等按钮是否可以点击提供条件
        boolean flag = false;
        if (!StringUtils.isEmpty(pwd)) {
            flag = pwd.length() >= 6 && pwd.length() <= 12;
        }
        return flag;

    }

    public static int getAgeFromTimeStamps(long timestamp) {
        long time_len = System.currentTimeMillis() - timestamp;

        //365*24*60*60*1000
        long year_time = 31536000000l;
        return (int) (time_len / year_time);
    }

    public static SpannableStringBuilder convertLYGHighLight(String str) {
        if (isEmpty(str)) {
            return new SpannableStringBuilder("");
        }
        String hlStr = new String(str);
        int sIndex = hlStr.indexOf("<lyg-hl>");
        if (sIndex == -1) {
            return new SpannableStringBuilder(hlStr);
        }
        ArrayList<Integer> startIndexs = new ArrayList<Integer>();
        ArrayList<Integer> endIndexs = new ArrayList<Integer>();
        while (sIndex != -1) {
            int index = sIndex;
            startIndexs.add(index);
            sIndex = hlStr.indexOf("<lyg-hl>", sIndex + 1);
        }
        sIndex = hlStr.indexOf("</lyg-hl>");
        if (sIndex == -1) {
            return new SpannableStringBuilder(hlStr);
        }
        while (sIndex != -1) {
            int index = sIndex;
            endIndexs.add(index);
            sIndex = hlStr.indexOf("</lyg-hl>", sIndex + 1);
        }
        if (startIndexs.size() != endIndexs.size()) {
            return new SpannableStringBuilder(hlStr);
        }
        SpannableStringBuilder result = new SpannableStringBuilder();
        //加 head
        if (startIndexs.get(0) != 0) {
            result.append(hlStr.substring(0, startIndexs.get(0)));
        }
        try {
            int size = startIndexs.size();
            for (int i = 0; i < size; i++) {
                SpannableString spanStr = new SpannableString(hlStr.substring(startIndexs.get(i) + 8, endIndexs.get(i)));

                spanStr.setSpan(new ForegroundColorSpan(Color.parseColor("#209E89")), 0, spanStr.length(),
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                result.append(spanStr);

                if (i < startIndexs.size() - 1) { //mid
                    result.append(hlStr.substring(endIndexs.get(i) + 9, startIndexs.get(i + 1)));
                } else { //last
                    result.append(hlStr.substring(endIndexs.get(i) + 9));
                }
            }
            return result;
        } catch (Exception ex) {
            return new SpannableStringBuilder(hlStr);
        }
    }

    public static String toDBC(String input) {
        if (null != input) {
            char[] c = input.toCharArray();
            int size = c.length;
            for (int i = 0; i < size; i++) {
                if (c[i] == 12288) {
                    c[i] = (char) 32;
                    continue;
                }
                if (c[i] > 65280 && c[i] < 65375)
                    c[i] = (char) (c[i] - 65248);
            }
            return new String(c);
        } else {
            return "";
        }
    }

    /**
     * 字符在字符串中出现的次数
     *
     * @param string
     * @param a
     * @return
     */
    public static int occurTimes(String string, String a) {
        int pos = -2;
        int n = 0;

        while (pos != -1) {
            if (pos == -2) {
                pos = -1;
            }
            pos = string.indexOf(a, pos + 1);
            if (pos != -1) {
                n++;
            }
        }
        return n;
    }

    public static int stringToInt(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
        if (pattern.matcher(str).matches()) {
            return Integer.valueOf(str);
        }
        return 0;
    }

    public static String dataToString(Object obj) {

        return (obj == null) ? "" : obj.toString();
    }

    /**
     * 验证身份证号码
     *
     * @param idCard
     * @return
     * @author luck
     */
    public static boolean validateIdCard(String idCard) {
        if (TextUtils.isEmpty(idCard))
            return false;
        String pattern = "^[0-9]{17}[0-9|xX]{1}$";
        return idCard.matches(pattern);
    }

    /**
     * 保留小数点后两位
     */
    public static String priceFromat(double price) {

        if (price == 0) {
            return String.format("%.2f", price);
        } else {
            double con = 100.0;
            return String.format("%.2f", price / con);
        }

    }

    /**
     * 保留两位小数正则
     *
     * @param number
     * @return
     */
    public static boolean isOnlyPointNumber(String number) {
        Pattern pattern = Pattern.compile("^\\d+\\.?\\d{0,2}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * 判断字符串是否是浮点数
     *
     * @param value
     * @return
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 正则匹配<>号内容
     *
     * @param content
     * @return
     */
    public static String tempReason(String content) {
        if (!TextUtils.isEmpty(content)) {
            Pattern pattern = Pattern.compile("(?<=\\<)(.+?)(?=\\>)");
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                return matcher.group(0);
            }
        }
        return "";
    }

    /**
     * 部分字体变小处理
     */
    public static void fontSmall(TextView tv, String content, float proportion, int start, int end) {
        try {
            Spannable span = new SpannableString(content);
            span.setSpan(new RelativeSizeSpan(proportion), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(span);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将字符串中的连续的多个换行缩减成一个换行
     *
     * @param str 要处理的内容
     * @return 返回的结果
     */
    public static String replaceLineBlanks(String str) {
        try {
            String result;
            if (str != null) {
                Pattern p = Pattern.compile("(\r?\n(\\s*\r?\n)+)");
                Matcher m = p.matcher(str);
                result = m.replaceAll("\r\n");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
        return str;
    }


    /**
     * 格式化字符串，每三位用逗号隔开
     *
     * @param str
     * @return
     */
    public static String addComma(String str) {
        //先将字符串颠倒顺序
        str = new StringBuilder(str).reverse().toString();
        if (str.equals("0")) {
            return str;
        }
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            if (i * 3 + 3 > str.length()) {
                str2 += str.substring(i * 3);
                break;
            }
            str2 += str.substring(i * 3, i * 3 + 3) + ",";
        }
        if (str2.endsWith(",")) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        //最后再将顺序反转过来
        String temp = new StringBuilder(str2).reverse().toString();
        //将最后的,去掉
        return temp.substring(0, temp.lastIndexOf(",")) + temp.substring(temp.lastIndexOf(",") + 1);
    }
}
