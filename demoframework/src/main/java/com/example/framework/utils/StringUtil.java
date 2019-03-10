package com.example.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * udp工具类<br>
 *
 * @author wolf
 */
@Slf4j
public class StringUtil {
	/** 16进制数字字符集 */
	private static String hexString = "0123456789ABCDEF";
	/** 字符串编码 */
	private static String charset = "GBK";
	public static String browser(String userAgent,String name){
		String finalFileName = null;
		try {
			if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器
				finalFileName = URLEncoder.encode(name,"UTF8");
			}else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
				finalFileName = new String(name.getBytes(), "ISO8859-1");
			}else{
				finalFileName = URLEncoder.encode(name,"UTF8");//其他浏览器
			}} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		return finalFileName;
	}
	/**
	 * 按batch拆分字符串
	 *
	 * @param oriStr 原始字符串
	 * @param batch  分段的长度
	 * @return
	 */
	public static List<String> batchStr(String oriStr, Integer batch) {
		List<String> result = new ArrayList<String>();
		if (oriStr == null)
			return result;
		for (int i = 0; i < oriStr.length(); i++) {
			String temp = "";
			if (i + batch > oriStr.length()) {
				temp = oriStr.substring(i, oriStr.length());
			}
			else {
				temp = oriStr.substring(i, i + batch);
			}
			result.add(temp);
			i += batch - 1;
		}
		return result;
	}

	/**
	 * 补齐不足长度
	 *
	 * @param length 长度
	 * @param str    字符串
	 * @return
	 */
	public static String lpad(int length, String str) {
		String f = "%0" + length + "d";

		return String.format(f, Long.valueOf(str));
	}

	/**
	 * 补齐不足长度
	 *
	 * @param length 长度
	 * @param number 数字
	 * @return
	 */
	public static String lpad(int length, int number) {
		String f = "%0" + length + "d";
		return String.format(f, number);
	}

	/**
	 * 补齐不足长度
	 *
	 * @param length 长度
	 * @param number 数字
	 * @return
	 */
	public static String lpad(int length, long number) {
		String f = "%0" + length + "d";
		return String.format(f, number);
	}

	/**
	 * 分解byte，为byte数组
	 *
	 * @param outstrbyte 原始byte
	 * @param length     每个数组长度
	 * @return byte[]
	 */
	public static List<byte[]> batchByte(byte[] outstrbyte, int length) {
		int len = (int) Math.ceil(outstrbyte.length / Double.valueOf(length));
		if (len == 1) {
			length = outstrbyte.length;
		}
		List<byte[]> res = new ArrayList<byte[]>();
		for (int i = 0; i < len; i++) {
			if (i + 1 == len) {
				int last = outstrbyte.length - i * length;
				byte[] temp = new byte[last];
				System.arraycopy(outstrbyte, i * length, temp, 0, last);
				res.add(temp);
			}
			else {
				byte[] temp = new byte[length];
				System.arraycopy(outstrbyte, i * length, temp, 0, length);
				res.add(temp);
			}
		}
		return res;
	}

	/**
	 * 合并2个byte
	 *
	 * @param byte_1
	 * @param byte_2
	 * @return
	 */
	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	/**
	 * 截取byte[]
	 *
	 * @param src
	 * @param begin 起始位
	 * @param len   长度
	 * @return
	 */
	public static byte[] subBytes(byte[] src, int begin, int len) {
		byte[] bs = new byte[len];

		for (int i = begin; i < begin + len; i++)
			bs[i - begin] = src[i];

		return bs;
	}

	/**
	 * int 转 byte[]
	 *
	 * @param i
	 * @return
	 */
	public static byte[] int2Bytes(int i) {
		byte[] b = new byte[4];
		b[0] = (byte) ((0xff000000 & i) >> 24);
		b[1] = (byte) ((0xff0000 & i) >> 16);
		b[2] = (byte) ((0xff00 & i) >> 8);
		b[3] = (byte) (0xff & i);
		return b;
	}

	/**
	 * byte[] 转 int
	 *
	 * @param bytes
	 */
	public static int bytes2IntBig(byte[] bytes) {
		int num = 0;
		for (int ix = 0; ix < 4; ++ix) {
			num <<= 8;
			num |= (bytes[3 - ix] & 0xff);
		}
		return num;
	}

	public static int bytes2Int(byte[] bytes) {
		int num = 0;
		for (int ix = 0; ix < 4; ++ix) {
			num <<= 8;
			num |= (bytes[ix] & 0xff);
		}
		return num;
	}

	/**
	 * long 转 byte[]
	 *
	 * @param l
	 * @return
	 */
	public static byte[] long2bytes(long l) {
		byte[] b = new byte[8];
		for (int i = 0; i < 8; i++) {
			b[i] = (byte) (l >>> (56 - (i * 8)));
		}
		return b;
	}

	/**
	 * byte[] 转 long
	 *
	 * @param bytes
	 * @return
	 */
	public static long bytes2longBig(byte[] bytes) {
		long temp = 0;
		long res = 0;
		for (int i = 0; i < 8; i++) {
			res <<= 8;
			temp = bytes[7 - i] & 0xff;
			res |= temp;
		}
		return res;
	}

	public static long bytes2long(byte[] bytes) {
		long temp = 0;
		long res = 0;
		for (int i = 0; i < 8; i++) {
			res <<= 8;
			temp = bytes[i] & 0xff;
			res |= temp;
		}
		return res;
	}

	public static String parseLongStr2Ip(String s) {
		long i = Long.parseLong(s);
		return ((i >> 24) & 0xFF) +
				"." + ((i >> 16) & 0xFF) +
				"." + ((i >> 8) & 0xFF) +
				"." + (i & 0xFF);

	}

	public static String ip2String(String ipAddress) {

		long result = 0;

		String[] ipAddressInArray = ipAddress.split("\\.");

		for (int i = 3; i >= 0; i--) {

			long ip = Long.parseLong(ipAddressInArray[3 - i]);

			result |= ip << (i * 8);

		}
		return String.valueOf(result);
	}

	public static String matcherVersionNumber(String str) {
		if(StringUtils.isEmpty(str))
			return null;

		String	regEx = "\\d+(\\.\\d+)+";

		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		String ret = null;
		if(m.find()) {
			ret =m.group();
		}
		return ret;
	}

	/**
	 *
	 * @param str
	 * @param regEx
	 * @return
	 */
	public static String matcherStr(String str, String regEx) {
		try {
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);

			return m.replaceAll("");
		}
		catch (Exception e) {
			return null;
		}
	}
	/**
	 *
	 * 功能描述: 将16进制数字解码成字符串,适用于所有字符（包括中文）<br>
	 *
	 * @param bytes 16进制数字
	 * @return
	 */
	public static String decode(String bytes) {
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream(bytes.length() / 2);
			// 将每2位16进制整数组装成一个字节
			for (int i = 0; i < bytes.length(); i += 2) {
				baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
			}
			return baos.toString(charset);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (baos != null)
				try {
					baos.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
		}
		return null;
	}
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static String reverse(final String str) {
		if (str == null) {
			return null;
		}
		return new StringBuilder(str).reverse().toString();
	}
}
