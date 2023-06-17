package rs.ac.metropolitan.zadatak.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import rs.ac.metropolitan.zadatak.AppViewModel
import rs.ac.metropolitan.zadatak.view.HomeScreen
import rs.ac.metropolitan.zadatak.view.NewUserScreen
import rs.ac.metropolitan.zadatak.view.UserDetailScreen

@Composable
fun NavSetup(navController: NavHostController) {
    val vm: AppViewModel = viewModel()
    var paddingValues = PaddingValues()
    vm.navController = navController

    NavHost(navController = navController, startDestination = NavigationRoutes.Home.route) {
        composable(route = NavigationRoutes.Home.route){
            HomeScreen(vm, paddingValues = paddingValues)
        }
        composable(route = NavigationRoutes.UserDetailScreen.route) { navBackStackEntry ->
            var elementId = navBackStackEntry.arguments?.getString("elementId")
            if (elementId != null){
                UserDetailScreen(vm, elementId, paddingValues)
            } else {
                Toast.makeText(navController.context, "Error, elementId is required!", Toast.LENGTH_SHORT).show()
            }
        }
        composable(route = NavigationRoutes.NewUser.route){
            NewUserScreen(vm, paddingValues)
        }
    }
}