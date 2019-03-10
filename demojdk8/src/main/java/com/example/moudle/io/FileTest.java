package com.example.moudle.io;

import java.io.*;

/**
 * @author jinBiaoHu
 * @date 2019-01-16 21:52
 */
public class FileTest {
	public static void main(String[] args) {
		File files = new File("E:\\桌面备份");
		System.out.println(files.isFile());
		//listFile(files);
		ioTest();

	}

	private static void ioTest(){
		try {
			FileInputStream in  = new FileInputStream("E:\\桌面备份\\VPN.txt");
			InputStreamReader reader = new InputStreamReader(in,"utf-8");
			System.out.println(reader);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void listFile(File files) {
		for(File file : files.listFiles()){
			if (file.isFile()) {
				System.out.println(file.getAbsolutePath());
			}
			else {
				listFile(file);
			}
		}
	}

	public static void copyFile(String src, String dist) throws IOException
	{
		FileInputStream in = new FileInputStream(src);
		FileOutputStream out = new FileOutputStream(dist);
		byte[] buffer = new byte[20 * 1024];
		// read() 最多读取 buffer.length 个字节
		// 返回的是实际读取的个数
		// 返回 -1 的时候表示读到 eof，即文件尾
		while (in.read(buffer, 0, buffer.length) != -1) {
			out.write(buffer);
		}
		in.close();
		out.close();
	}

}
