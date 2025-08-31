package org.unizd.rma.martinovic.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageInfo(
    val full: String?
) : Parcelable

@Parcelize
data class Champion(
    val id: String?,
    val key: String?,
    val name: String?,
    val title: String?,
    val blurb: String?,
    val partype: String?,
    val tags: List<String>?,
    val image: ImageInfo?,
    val info: InfoStats?,
    val stats: BaseStats?
) : Parcelable

@Parcelize
data class InfoStats(
    val attack: Int? = null,
    val defense: Int? = null,
    val magic: Int? = null,
    val difficulty: Int? = null
) : Parcelable

@Parcelize
data class BaseStats(
    val hp: Double? = null,
    val hpperlevel: Double? = null,
    val mp: Double? = null,
    val mpperlevel: Double? = null,
    val movespeed: Double? = null,
    val armor: Double? = null,
    val armorperlevel: Double? = null,
    val spellblock: Double? = null,
    val spellblockperlevel: Double? = null,
    val attackrange: Double? = null,
    val hpregen: Double? = null,
    val hpregenperlevel: Double? = null,
    val mpregen: Double? = null,
    val mpregenperlevel: Double? = null,
    val crit: Double? = null,
    val critperlevel: Double? = null,
    val attackdamage: Double? = null,
    val attackdamageperlevel: Double? = null,
    val attackspeedperlevel: Double? = null,
    val attackspeed: Double? = null
) : Parcelable
