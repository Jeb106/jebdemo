package com.jeb.demo.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/*
 * 1.数据的增删改查，需要使用的是Table
 * 
 * 2.Put: 代表对单行数据的put操作
 * 
 * 3. 在hbase中，操作的数据都是以byte[]形式存在，需要把常用的数据类型转为byte[]
 * 			hbase提供了Bytes工具类
 * 				Bytes.toBytes(x): 基本数据类型转byte[]
 * 				Bytes.toXxx(x): 从byte[]转为Xxx类型！
 * 
 * 4. Get: 代表对单行数据的Get操作！
 * 
 * 5. Result: scan或get的单行的所有的记录！
 * 
 * 6. Cell： 代表一个单元格，hbase提供了CellUtil.clonexxx(Cell)，来获取cell中的列族、列名和值属性！
 * 
 * 7. ResultScanner: 多行Result对象的集合
 */
public class DataUtil {
	
	//先获取到表的table对象
	public static Table getTable(Connection conn,String tableName,String nsname) throws IOException {
		
		//验证表名是否合法
		TableName tn = TableUtil.checkTableName(tableName, nsname);
		
		if (tn == null) {
			return null;
		}
		
		//根据TableName获取对应的Table
		return conn.getTable(tn);
		
	}
	
	//put 表名,rowkey,列名(列族名:列名),value
	public static void put(Connection conn,String tableName,String nsname,String rowkey,String cf,
			String cq,String value) throws IOException {
		
		//获取表对象
		Table table = getTable(conn, tableName, nsname);
		
		if (table==null) {
			return;
		}
		
		//创建一个Put对象
		Put put = new Put(Bytes.toBytes(rowkey));
		
		//向put中设置cell的细节信息
		put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cq), Bytes.toBytes(value));
		//.addColumn(family, qualifier, value)
		
		table.put(put);
		
		table.close();
		
		
	}
	
	// get 表名   rowkey
	
	public static void get(Connection conn,String tableName,String nsname,String rowkey) throws IOException {
		
		//获取表对象
		Table table = getTable(conn, tableName, nsname);
		
		if (table==null) {
			return ;
		}
		
		Get get = new Get(Bytes.toBytes(rowkey));
		
		//设置单行查询的详细信息
		//设置查哪个列
		//get.addColumn(family, qualifier)
		//设置查哪个列族
		//get.addFamily(family)
		//只查某个时间戳的数据
		//get.setTimeStamp(timestamp)
		//设置返回的versions
		//get.setMaxVersions(maxVersions)
		
		Result result = table.get(get);
		
		//System.out.println(result);
		
		parseResult(result);
		
		table.close();
		
		
	}
	
	//遍历result
	public static void parseResult(Result result) {
		
		if (result != null) {
			
			Cell[] cells = result.rawCells();
			
			for (Cell cell : cells) {
				
				System.out.println("行："+Bytes.toString(CellUtil.cloneRow(cell))+
						"  列族："+Bytes.toString(CellUtil.cloneFamily(cell))+"   列名："+
						Bytes.toString(CellUtil.cloneQualifier(cell))+
						"  值:"+Bytes.toString(CellUtil.cloneValue(cell)));
				
			}
			
		}
		
	}
	
	//scan '表名', {STARTROW=> X,STOPROW=>X, LIMIT=>1}
	public static void scan(Connection conn,String tableName,String nsname) throws IOException {
		
		//获取表对象
		Table table = getTable(conn, tableName, nsname);
				
		if (table==null) {
			return ;
		}
		
		//构建scan对象
		Scan scan = new Scan();
		
		//设置扫描的起始行
		//scan.setStartRow(startRow)
		//设置扫描的结束行
		//scan.setStopRow(stopRow)
		
		//结果集扫描器
		ResultScanner scanner = table.getScanner(scan);
		
		for (Result result : scanner) {
			
			parseResult(result);
			
		}
		
		//关闭表
		table.close();
		
	}
	
	//delete '表名','rowkey',[列族][列][ts]
	public static void delete(Connection conn,String tableName,String nsname,String rowkey) throws IOException {
		
		//获取表对象
		Table table = getTable(conn, tableName, nsname);
				
		if (table==null) {
			return ;
		}
		
		//构建delete对象
		Delete delete = new Delete(Bytes.toBytes(rowkey));
		
		//设置delete时的参数
		// 删除某个具体的列,为此列的最新的cell，添加一条type=DELETE的标记，只能删除最新的一条记录，如果有
		// 历史版本的记录，无法删除
		//delete.addColumn(Bytes.toBytes("cf1"), Bytes.toBytes("age"));
		
		//删除指定列的所有版本的数据，为当前列生成一个type=DeleteColumn的标记的记录
		//delete.addColumns(Bytes.toBytes("cf1"), Bytes.toBytes("age"));
		
		//删除整个列族
		delete.addFamily(Bytes.toBytes("cf2"));
		
		table.delete(delete);
		
		//关闭表
		table.close();
		
	}
	
	

}
