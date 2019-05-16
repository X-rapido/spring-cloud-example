package com.example;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrixDashboard	// 开启 dashboard，访问地址 http://localhost:8500/hystrix
@EnableTurbine			// 开启 turbine
public class DashboardTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardTurbineApplication.class, args);
	}

	/**
	 * 解决启动错误：https://www.cnblogs.com/houzheng/p/9906800.html
	 * https://www.jianshu.com/p/590bad4c8947
	 * http://blog.didispace.com/spring-cloud-tips-4/
	 */
//	@Bean
//	public ServletRegistrationBean getServlet(){
//		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//		registrationBean.setLoadOnStartup(1);  // 加载顺序
//		registrationBean.addUrlMappings("/hystrix.stream");// 路径
//		registrationBean.setName("HystrixMetricsStreamServlet");
//		return registrationBean;
//	}
}
