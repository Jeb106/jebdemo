package com.jeb.demo.ftptest;

import com.fable.common.etl.kettle.DatabaseMetaBase;
import com.fable.common.etl.kettle.bean.DatabaseConn;
import com.fable.common.util.DBUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.util.EnvUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpTestApplicationTests {

	@Test
	public void contextLoads() {
		try {
			//初始化Kettle环境
			KettleEnvironment.init();
			EnvUtil.environmentInit();
		Database db = null;
		DatabaseConn dbConn = new DatabaseConn("192.168.90.43", "Oracle","orcl",
				"1521"+ "","fable2", "fable2");
		DatabaseMeta databaseMeta = new DatabaseMetaBase(dbConn.getDatabase(), dbConn.getType(), "0",
				dbConn.getServer(),
				dbConn.getDatabase(), dbConn.getPort(), dbConn.getUsername(), dbConn.getPassword());
		db = new Database(null, databaseMeta);

		    String dbScheme="fable1";
		    String tableName="T_TEST_PIC_JEB";

			DBUtil.connect(db);
			String destTable1 = dbScheme+"."+tableName;
			boolean destTable = db.checkTableExists(destTable1);
			System.out.println("===========================result:"+destTable);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
