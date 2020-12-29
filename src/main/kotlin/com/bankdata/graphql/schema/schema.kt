package  com.bankdata.graphql.schema

import com.bankdata.graphql.api.FdicApi
import com.expediagroup.graphql.toSchema
import com.expediagroup.graphql.SchemaGeneratorConfig
import com.expediagroup.graphql.TopLevelObject
import com.expediagroup.graphql.annotations.GraphQLIgnore
import com.expediagroup.graphql.execution.GraphQLContext
import com.expediagroup.graphql.spring.execution.GraphQLContextFactory
import com.expediagroup.graphql.spring.operations.Query
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap


@Component
class BankQuery : Query {
    @GraphQLIgnore
    @Autowired
    private val fdicApi: FdicApi = FdicApi()

    suspend fun banksByName(name: String): List<BankData> {
        val uriParams = LinkedMultiValueMap<String, String>()
        uriParams["name"] = name
        print(uriParams)
        val bankList: List<BankData>
        coroutineScope {
            val fdicData: Deferred<Fdic?> = async(start = CoroutineStart.LAZY) {
                fdicApi.call(
                    FdicApi.CallType.INSTITUTIONS,
                    uriParams
                )
            }
            val banks: Fdic? = fdicData.await()
            bankList = banks!!.data
        }
        return bankList
    }

    suspend fun listOfBranches(name: String, cert: String, cbsa: String?): List<BankData> {
        val uriParams = LinkedMultiValueMap<String, String>()
        uriParams["name"] = name
        uriParams["cert"] = cert
        uriParams["cbsa"] = cbsa
        print(uriParams)
        val bankLocations: List<BankData>
        coroutineScope {
            val fdicData: Deferred<Fdic?> = async(start = CoroutineStart.LAZY) {
                fdicApi.call(
                    FdicApi.CallType.LOCATIONS,
                    uriParams
                )
            }
            val banks: Fdic? = fdicData.await()
            bankLocations = banks!!.data
        }
        return bankLocations
    }
}

val bankQuery = BankQuery()
val config = SchemaGeneratorConfig(supportedPackages = listOf("com.bankdata.graphql.schema"))
val topLevelQueries = listOf<TopLevelObject>(TopLevelObject(bankQuery))

val schema = toSchema(config, topLevelQueries)