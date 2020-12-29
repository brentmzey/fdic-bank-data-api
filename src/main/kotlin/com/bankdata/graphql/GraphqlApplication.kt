package com.bankdata.graphql

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping

@SpringBootApplication
class GraphqlApplication{
//	@Bean
//	fun dataFetcherFactoryProvider(
//		springDataFetcherFactory: SpringDataFetcherFactory,
//		objectMapper: ObjectMapper,
//		applicationContext: ApplicationContext
//	) = CustomDataFetcherFactoryProvider(springDataFetcherFactory, objectMapper, applicationContext)
}
fun main(args: Array<String>) {
	runApplication<GraphqlApplication>(*args)
	val requestMappingHandlerMapping: RequestMappingHandlerMapping
}
