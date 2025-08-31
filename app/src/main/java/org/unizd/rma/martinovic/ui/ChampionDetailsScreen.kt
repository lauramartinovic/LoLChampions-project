package org.unizd.rma.martinovic.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.unizd.rma.martinovic.LolViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChampionDetailsScreen(
    vm: LolViewModel,
    champId: String,
    onBack: () -> Unit
) {
    val champ = remember(vm.state.champions, champId) { vm.findChampion(champId) }
    val version = vm.state.version

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(champ?.name ?: "Detalji") },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("Natrag") }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = champ?.id?.let { "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${it}_0.jpg" },
                contentDescription = champ?.name ?: "",
                modifier = Modifier.fillMaxWidth()
            )

            Text("${champ?.name ?: ""} — ${champ?.title ?: ""}", style = MaterialTheme.typography.titleLarge)

            if (!champ?.blurb.isNullOrBlank()) {
                Text(champ!!.blurb!!)
            }

            Text(
                "Tags: ${champ?.tags?.joinToString() ?: "N/A"}",
                fontWeight = FontWeight.SemiBold
            )

            Divider()

            champ?.info?.let { info ->
                Text("INFO  attack=${info.attack}  defense=${info.defense}  magic=${info.magic}  difficulty=${info.difficulty}")
            }

            // Base stats
            champ?.stats?.let { s ->
                Text(
                    """
                    STATS
                    HP=${s.hp} (+${s.hpperlevel}/lvl) | MP=${s.mp} (+${s.mpperlevel}/lvl)
                    Armor=${s.armor} (+${s.armorperlevel}/lvl) | MR=${s.spellblock} (+${s.spellblockperlevel}/lvl)
                    AD=${s.attackdamage} (+${s.attackdamageperlevel}/lvl) | AS=${s.attackspeed} (+${s.attackspeedperlevel}/lvl)
                    Range=${s.attackrange} | MS=${s.movespeed}
                    """.trimIndent()
                )
            }

            AsyncImage(
                model = champ?.image?.full?.let { full ->
                    if (version.isNotBlank())
                        "https://ddragon.leagueoflegends.com/cdn/$version/img/champion/$full"
                    else null
                },
                contentDescription = "Icon",
                modifier = Modifier.size(72.dp)
            )

            Spacer(Modifier.height(24.dp))
            Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text("Natrag")
            }
        }
    }
}
