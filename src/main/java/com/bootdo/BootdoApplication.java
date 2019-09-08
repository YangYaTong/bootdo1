package com.bootdo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.bootdo.*.dao")
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class BootdoApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootdoApplication.class, args);
		System.out.println("\n" +
				"                         #######  ##################  ##########################     \n" +
				"                         #######         ###          ###  ##############    ###     \n" +
				"                           ##            ###          ###        ##          ###       \n" +
				"                           ##         ## ########     ###   ##############   ###       \n" +
				"                           ##         ## ###          ###        ##    ##    ###     \n" +
				"                           ##         ## ###          ###   ##############   ###       \n" +
				"                           ##    #################### ##########################   \n" +
				"                                       征 国 集 团  项 目 后 台 管 理 系 统                                                  ");
	}
}
