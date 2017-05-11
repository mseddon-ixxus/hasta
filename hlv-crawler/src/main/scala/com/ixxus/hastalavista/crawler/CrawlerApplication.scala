package com.ixxus.hastalavista.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}

@Configuration
@EnableAutoConfiguration
@ComponentScan
class ApiApplication

object ApiApplication  {

    def main(args: Array[String]): Unit = {
        SpringApplication.run(classOf[ApiApplication])
    }

}
