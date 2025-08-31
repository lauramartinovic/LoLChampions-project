package org.unizd.rma.martinovic.repository

import org.unizd.rma.martinovic.model.Champion
import org.unizd.rma.martinovic.network.RetrofitClient

class LolRepository {
    private val api = RetrofitClient.api

    suspend fun fetchLatestVersion(): String {
        val versions = api.getVersions()
        return versions.firstOrNull() ?: "latest"
    }

    suspend fun fetchChampions(version: String): List<Champion> {
        val resp = api.getChampions(version)
        return resp.data?.values?.sortedBy { it.name ?: "" }.orEmpty()
    }
}
