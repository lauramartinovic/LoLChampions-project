package org.unizd.rma.martinovic.network

import org.unizd.rma.martinovic.model.ChampionListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LolApi {
    @GET("api/versions.json")
    suspend fun getVersions(): List<String>

    @GET("cdn/{ver}/data/en_US/champion.json")
    suspend fun getChampions(@Path("ver") version: String): ChampionListResponse
}
