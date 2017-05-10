package com.ixxus.hastalavista.store

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}

@Configuration
@EnableAutoConfiguration
@ComponentScan
class StoreApplication

object StoreApplication  {

    def main(args: Array[String]): Unit = {
        SpringApplication.run(classOf[StoreApplication])
    }

}
