package rs.ac.metropolitan.zadatak.navigation

sealed class NavigationRoutes(val route: String){
    object Home : NavigationRoutes(route = "home")
    object NewUser : NavigationRoutes(route = "new")
    object UserDetailScreen : NavigationRoutes(route = "detail/{elementId}"){
        fun createRoute(elementId : String) = "detail/$elementId"
    }
}
