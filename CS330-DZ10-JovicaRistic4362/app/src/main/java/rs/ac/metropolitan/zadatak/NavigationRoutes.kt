package rs.ac.metropolitan.zadatak

sealed class NavigationRoutes(val route: String){
    object Home: NavigationRoutes(route = "home")
    object PosiljkaDetailScreen: NavigationRoutes(route = "detail/{elementId}"){
        fun createRoute(elementId: String) = "detail/$elementId"
    }
}
