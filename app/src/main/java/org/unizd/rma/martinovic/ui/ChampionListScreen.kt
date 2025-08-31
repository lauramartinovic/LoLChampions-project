package org.unizd.rma.martinovic.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.unizd.rma.martinovic.LolViewModel
import org.unizd.rma.martinovic.model.Champion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChampionListScreen(
    vm: LolViewModel,
    onOpenDetails: (String) -> Unit
) {
    var ui by remember { mutableStateOf(vm.state) }
    LaunchedEffect(Unit) { vm.observe { ui = it } }

    Scaffold(
        topBar = { TopAppBar(title = { Text("LoL Champions") }) }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            when {
                ui.loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
                ui.error != null -> {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Greška: ${ui.error}")
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { vm.reload() }) { Text("Pokušaj ponovo") }
                    }
                }
                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(ui.champions) { champ ->
                            ChampionCard(
                                champ = champ,
                                version = ui.version,
                                onClick = { champ.id?.let(onOpenDetails) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ChampionCard(champ: Champion, version: String, onClick: () -> Unit) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = if (champ.image?.full != null && version.isNotBlank())
                    "https://ddragon.leagueoflegends.com/cdn/$version/img/champion/${champ.image.full}"
                else null,
                contentDescription = champ.name ?: "",
                modifier = Modifier.size(56.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(champ.name ?: "", style = MaterialTheme.typography.titleMedium)
                Text(
                    champ.title ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
