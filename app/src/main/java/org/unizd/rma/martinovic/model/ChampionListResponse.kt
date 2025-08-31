package org.unizd.rma.martinovic.model

data class ChampionListResponse(
    val type: String?,
    val format: String?,
    val version: String?,
    val data: Map<String, Champion>?
)
