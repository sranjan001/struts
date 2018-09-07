package org.superbiz.struts;

import com.opensymphony.module.sitemesh.filter.PageFilter;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    FilterRegistrationBean strut2Filter () {
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setFilter(new FilterDispatcher());

        Map<String, String> params = new HashMap<>();
        params.put("actionPackages", "com.lq");

        frb.setInitParameters(params);
        frb.setUrlPatterns(Arrays.asList("/*"));
        frb.setOrder(1);
        return frb;
    }


    @Bean
    FilterRegistrationBean strutsCleanUpFilter () {
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setFilter(new ActionContextCleanUp());
        frb.setUrlPatterns(Arrays.asList("/*"));

        frb.setOrder(2);
        return frb;
    }

    @Bean
    FilterRegistrationBean sitemeshFilter () {
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setFilter(new PageFilter());
        frb.setUrlPatterns(Arrays.asList("/*"));

        frb.setOrder(3);
        return frb;
    }

}
