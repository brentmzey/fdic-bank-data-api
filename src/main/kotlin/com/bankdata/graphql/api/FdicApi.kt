package com.bankdata.graphql.api

import com.bankdata.graphql.schema.Bank
import com.bankdata.graphql.schema.Fdic
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.client.reactive.ClientHttpResponse
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.BodyExtractor
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitEntity
import org.springframework.web.reactive.function.client.awaitExchange
import org.springframework.web.server.ResponseStatusException

@Service
open class FdicApi {

    enum class CallType {
        INSTITUTIONS, LOCATIONS
    }

    suspend fun call(callType: CallType, uriParams: MultiValueMap<String, String>): Fdic {
        return WebClient.create("https://banks.data.fdic.gov/api")
            .get().uri(){uriBuilder ->
                when(callType) {
                    CallType.INSTITUTIONS -> {
                        uriBuilder.path("institutions").queryParams(uriParams).build()
                    }
                    CallType.LOCATIONS -> {
                        uriBuilder.path("locations").queryParams(uriParams).build()
                    }
                }
            }
            .accept(MediaType.APPLICATION_JSON)
            .awaitExchange {
                if (it.statusCode() == HttpStatus.OK) {
                    it.awaitBody<Fdic>()
                } else {
                    throw ResponseStatusException(it.statusCode(), "FDIC API responded with HTTP status code: ${it.rawStatusCode()} and message: ${it.body<String>(it.awaitBody())}")
                }
            }
    }
}

