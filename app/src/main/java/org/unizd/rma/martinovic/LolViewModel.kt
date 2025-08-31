package org.unizd.rma.martinovic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.unizd.rma.martinovic.model.Champion
import org.unizd.rma.martinovic.repository.LolRepository

data class UiState(
    val loading: Boolean = false,
    val error: String? = null,
    val version: String = "",
    val champions: List<Champion> = emptyList()
)

class LolViewModel : ViewModel() {
    private val repo = LolRepository()

    var state: UiState = UiState(loading = true)
        private set

    private val listeners = mutableListOf<(UiState) -> Unit>()
    fun observe(listener: (UiState) -> Unit) {
        listeners += listener
        listener(state)
    }
    private fun notifyChange() = listeners.forEach { it(state) }

    init {
        reload()
    }

    fun reload() {
        state = state.copy(loading = true, error = null)
        notifyChange()

        viewModelScope.launch {
            try {
                val ver = repo.fetchLatestVersion()
                val champs = repo.fetchChampions(ver)
                state = UiState(
                    loading = false,
                    version = ver,
                    champions = champs
                )
                notifyChange()
            } catch (e: Exception) {
                state = state.copy(loading = false, error = e.message ?: "Greška")
                notifyChange()
            }
        }
    }

    fun findChampion(id: String): Champion? =
        state.champions.firstOrNull { it.id == id }
}
