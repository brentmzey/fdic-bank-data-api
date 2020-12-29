package com.bankdata.graphql.schema
//
//import com.bankdata.graphql.schema.Fdic
//import com.bankdata.graphql.api.FdicApi
//import com.bankdata.graphql.schema.BankData
//import com.expediagroup.graphql.annotations.GraphQLIgnore
//import com.expediagroup.graphql.spring.execution.SpringDataFetcher
//import com.expediagroup.graphql.spring.operations.Query
//import kotlinx.coroutines.CoroutineStart
//import kotlinx.coroutines.Deferred
//import kotlinx.coroutines.async
//import kotlinx.coroutines.coroutineScope
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Component
//import org.springframework.util.LinkedMultiValueMap
//import org.springframework.web.server.ServerErrorException
//
//
//@Component
//class BanksQuery : Query {
//    @GraphQLIgnore @Autowired private val fdicApi: FdicApi = FdicApi()
//    suspend fun banksByName(name: String): List<BankData> {
//        val uriParams = LinkedMultiValueMap<String, String>()
//        uriParams["name"] = name
//        val bankList: List<BankData>
//        coroutineScope {
//            val fdicData: Deferred<Fdic?> = async(start = CoroutineStart.LAZY) {
//                fdicApi.call(
//                    FdicApi.CallType.INSTITUTIONS,
//                    uriParams
//                )
//            }
//            val banks: Fdic? = fdicData.await()
//            bankList = banks!!.data
////            var matches: Int? = banks?.totals?.get("count")
////            if (banks != null) {
////                return@coroutineScope banks.data
////            } else {
////                throw ServerErrorException(
////                    "Could not process bank data",
////                    KotlinNullPointerException("banks data for this request from FDIC API was null")
////                )
////            }
//        }
//    return bankList
//    }
//
//    suspend fun listOfBranches(name: String, cert: String, cbsa: String?): List<BankData> {
//        val uriParams = LinkedMultiValueMap<String, String>()
//        uriParams["name"] = name
//        uriParams["cert"] = cert
//        uriParams["cbsa"] = cbsa
//        val bankLocations: List<BankData>
//        coroutineScope {
//            val fdicData: Deferred<Fdic?> = async(start = CoroutineStart.LAZY) {
//                fdicApi.call(
//                    FdicApi.CallType.LOCATIONS,
//                    uriParams
//                )
//            }
//            val banks: Fdic? = fdicData.await()
//            bankLocations = banks!!.data
//        }
//        return bankLocations
//    }
//}
//
////@Component
////class ListOfBranches : Query {
////    suspend fun listOfBranches(name: String, cert: String, cbsa: String?): List<BankData> {
////        val fdicApi: FdicApi = FdicApi()
////        val uriParams = LinkedMultiValueMap<String, String>()
////        uriParams["name"] = name
////        uriParams["cert"] = cert
////        uriParams["cbsa"] = cbsa
////        return coroutineScope {
////            val fdicData: Deferred<Fdic?> = async(start = CoroutineStart.LAZY) {
////                fdicApi.call(
////                    FdicApi.CallType.LOCATIONS,
////                    uriParams
////                )
////            }
////            var banks: Fdic? = fdicData.await()
////            return@coroutineScope banks!!.data
////        }
////    }
////}