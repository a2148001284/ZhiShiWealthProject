package com.zhouy.login.config;

import com.zhouy.login.handler.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(userInterceptor)
               .addPathPatterns("/update");
//       .excludePathPatterns("/register").excludePathPatterns("/login").excludePathPatterns("/")
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 实例化CORS配置对象
        CorsConfiguration config = new CorsConfiguration();
        // 允许Cookie跨域
        config.setAllowCredentials(true);
        // 设置你要允许的网站域名
        // 允许所有网站发起跨域请求
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        // 允许跨域请求的请求类型，*表示允许所有类型
        config.addAllowedMethod("*");
        // 注册跨域配置，/**表示允许自己所有的API路径接受跨域请求
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // 这个顺序很重要哦，为避免麻烦请设置在最前
        bean.setOrder(0);
        return bean;
    }



}
