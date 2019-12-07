package com.jeb.https.demo.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * 版权：Copyright © Fable  Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建时间：2018-11-03 21:13
 *
 * @author Administrator
 */
@Configuration
public class SslConfig {




//	@Bean
//		public ServletWebServerFactory servletContainer(){
//			TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//			tomcat.addAdditionalTomcatConnectors(createStandardConnector());
//			return tomcat;
//		}
//
//
//	private Connector createStandardConnector() {
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setPort(9000);
//		return connector;
//	}

	//@Bean
//	public TomcatServletWebServerFactory servletContainer() {
//
//		// springboot2 新变化
//		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//
//			@Override
//			protected void postProcessContext(Context context) {
//
//				SecurityConstraint securityConstraint = new SecurityConstraint();
//				securityConstraint.setUserConstraint("CONFIDENTIAL");
//				SecurityCollection collection = new SecurityCollection();
//				collection.addPattern("/*");
//				securityConstraint.addCollection(collection);
//				context.addConstraint(securityConstraint);
//			}
//		};
//		tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//		return tomcat;
//	}
//
//	private Connector initiateHttpConnector() {
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setScheme("http");
//		connector.setPort(8081);
//		connector.setSecure(false);
//		connector.setRedirectPort(8443);
//		return connector;
//	}
}
