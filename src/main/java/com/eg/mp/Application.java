package com.eg.mp;

import com.eg.mp.sys.SystemConfig;
import com.eg.mp.util.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yujiezhang
 * @since 2017-08-31 19:34
 */
@SpringBootApplication
@EnableConfigurationProperties({SystemConfig.class})
@MapperScan(basePackages = "com.eg.mp.mapper", markerInterface = MyMapper.class)
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}