package com.example.test;

import com.google.gson.Gson;

import java.util.List;

/**
 * 文件名：GosnTest
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-05-19 11:24
 */
public class GosnTest {

	public static void main(String[] args) {
		Gson gson = new Gson();
		String jsonStr = "{\"data\":[{\"KTL_FLG\":\"N\",\"name\":\"fss\",\"id\":2}],\"fieldNames\":\"id,name\",\"lobLines\":\"\"}";
		GosnTest javaBean=gson.fromJson(jsonStr,GosnTest.class);
		System.out.println(javaBean.getFieldNames());
	}

	/**
	 * data : [{"KTL_FLG":"N","name":"fss","id":2}]
	 * fieldNames : id,name
	 * lobLines :
	 */

	private String fieldNames;
	private String lobLines;
	private List<DataBean> data;

	public String getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(String fieldNames) {
		this.fieldNames = fieldNames;
	}

	public String getLobLines() {
		return lobLines;
	}

	public void setLobLines(String lobLines) {
		this.lobLines = lobLines;
	}

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * KTL_FLG : N
		 * name : fss
		 * id : 2
		 */

		private String KTL_FLG;
		private String name;
		private int id;

		public String getKTL_FLG() {
			return KTL_FLG;
		}

		public void setKTL_FLG(String KTL_FLG) {
			this.KTL_FLG = KTL_FLG;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}
}
