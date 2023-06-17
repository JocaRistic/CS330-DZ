package rs.ac.metropolitan.zadatak

sealed class FoodListRoute(val route: String){
    object Home : FoodListRoute(route = "home")
    object FoodDetailScreen: FoodListRoute(route = "detail/{elementId}"){
        fun createRoute(elementId: Long) = "detail/$elementId"
    }
}
