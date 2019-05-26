package com.jeb.demo.ftptest;

import com.fable.common.etl.kettle.bean.FtpConn;
import com.fable.common.util.EtlUtil;
import com.fable.common.util.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName：FtpTestController
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-05-25 12:27
 */
@RestController
@RequestMapping("/api/ftp")
@Slf4j
public class FtpTestController {

	@Autowired
	Environment environment;


	@RequestMapping("/changeDir")
	public boolean changeDir(@RequestParam String parentDir,@RequestParam String chirldDir){

		FtpConn ftpConn = getFtpConn(parentDir);
		try {
			log.info("=================================changeDir======================");
			final Map<String, String> ftpConnProp = EtlUtil.ftpConn2FtpConnProp(ftpConn);
			FTPClient ftpClient = FtpUtil.getFTPClient(ftpConnProp);
			boolean b = ftpClient.changeWorkingDirectory(chirldDir);
			log.info("change:"+b);
		} catch (Exception e) {
			log.error("",e);
			return false;

		}
		return true;
	}

	@RequestMapping("/createFtpDir")
	public boolean createFtpDir(@RequestParam String parentDir,@RequestParam String chirldDir){

		FtpConn ftpConn = getFtpConn(parentDir);
		try {
			log.info("=================================createFtpDir======================");
			FtpUtil.md(ftpConn,chirldDir);

		} catch (Exception e) {
			log.error("",e);
			return false;

		}
		return true;
	}

	private FtpConn getFtpConn(@RequestParam String parentDir) {
		FtpConn ftpConn = new FtpConn();
		ftpConn.setServerName(environment.getProperty("dssg.system.FtpClient.serverName"));
		ftpConn.setUserName(environment.getProperty("dssg.system.FtpClient.userName"));
		ftpConn.setPassword(environment.getProperty("dssg.system.FtpClient.password"));
		ftpConn.setPort(environment.getProperty("dssg.system.FtpClient.port"));
		ftpConn.setModel(Integer.valueOf(environment.getProperty("dssg.system.FtpClient.model")));
		ftpConn.setEncoding("utf8");
		//ftpConn.setFtpDirectory("/");
		ftpConn.setFtpDirectory(parentDir);
		return ftpConn;
	}


	@RequestMapping("/ftpDirInit")
	public boolean ftpDirInit(@RequestParam String parentDir,@RequestParam String chirldDir){

		FtpConn ftpConn = getFtpConn(parentDir);
		try {
			log.info("=================================ftpDirInit=======================");
			final Map<String, String> ftpConnProp = EtlUtil.ftpConn2FtpConnProp(ftpConn);
			EtlUtil.initDestFtp(ftpConnProp, new String[]{ chirldDir });

		} catch (Exception e) {
			log.error("",e);
			return false;
		}
		return true;
	}

}
