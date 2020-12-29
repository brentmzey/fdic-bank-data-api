package com.bankdata.graphql.schema

import com.fasterxml.jackson.annotation.JsonProperty

data class Fdic(var meta: Map<String, out Any>, var data: List<BankData>, var totals: Map<String, Int>)
data class Bank(var data: Map<String, out Any>)
data class BankData(
    @JsonProperty("ACTIVE") var active: String?, @JsonProperty("ADDRESS") var address: String, @JsonProperty("CBSA") var cbsa: String?,
    @JsonProperty("CERT") var cert: String, @JsonProperty("NAME") var name: String, @JsonProperty("STALP") var stateAbbr: String, @JsonProperty("STNAME") var stateName: String,
    @JsonProperty("OFFICES") var numOffices: Int?, @JsonProperty("WEBADDR") var webAddr: String?, @JsonProperty("ZIP") var zip: String
)