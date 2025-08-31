package org.unizd.rma.martinovic.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.unizd.rma.martinovic.LolViewModel

@Composable
fun AppNav(vm: LolViewModel) {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "list") {

        composable("list") {
            ChampionListScreen(
                vm = vm,
                onOpenDetails = { id -> nav.navigate("details/$id") }
            )
        }

        composable(
            route = "details/{champId}",
            arguments = listOf(navArgument("champId") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments?.getString("champId").orEmpty()
            ChampionDetailsScreen(
                vm = vm,
                champId = id,
                onBack = { nav.navigateUp() }
            )
        }
    }
}
