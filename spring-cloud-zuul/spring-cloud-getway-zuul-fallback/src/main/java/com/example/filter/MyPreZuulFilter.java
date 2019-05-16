package com.example.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

/**
 * Zuul 过滤器：http://cloud.spring.io/spring-cloud-static/Greenwich.SR1/single/spring-cloud.html#zuul-developer-guide-sample-pre-filter
 *
 * 过滤器中可以判定登录验证、设置 header 等待操作
 */
@Component
public class MyPreZuulFilter extends ZuulFilter {

    /**
     * 类型包含：pre post route error
     * pre：代理之前
     * route：代理执行时
     * errpr：出现错误时
     * post：route 或 error 执行完成限制后执行
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filter 是链式调用，order 值越小，越先执行
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否使用这个过滤器
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("前置过滤器 MyPreZuulFilter 执行了");
        return null;
    }
}
