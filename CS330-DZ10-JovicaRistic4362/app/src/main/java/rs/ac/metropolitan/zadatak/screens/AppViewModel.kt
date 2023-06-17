package rs.ac.metropolitan.zadatak.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import rs.ac.metropolitan.zadatak.NavigationRoutes
import rs.ac.metropolitan.zadatak.Posiljke
import rs.ac.metropolitan.zadatak.PosiljkeModel
import java.net.URL

class AppViewModel : ViewModel() {

    lateinit var navController: NavHostController
    private lateinit var posiljkeList: MutableList<Posiljke>
    var granted =  mutableStateOf(false)
    var seeFrom = mutableStateOf(false)

    init {
        val jsonFile = loadResource("assets/posiljke.json").readText()
        val model = PosiljkeModel.fromJson(jsonFile)
        model?.let {
            posiljkeList = it.posiljke.toMutableStateList()
        }
    }

    private fun loadResource(path: String): URL {
        println("Loading resource: $path")
        return Thread.currentThread().contextClassLoader.getResource(path)
    }

    val posiljke: List<Posiljke>
        get() = posiljkeList.sortedBy { it.id }

    fun getPosiljka(id: String): Posiljke? {
        return posiljkeList.find { it.id == id }
    }

    fun switchSeeFrom(){
        seeFrom.value = !seeFrom.value
    }

    // Routing methods
    fun navigateToPosiljkaDetails(id: String) {
        navController.navigate(NavigationRoutes.PosiljkaDetailScreen.createRoute(id))
    }

    fun goBack() {
        navController.popBackStack()
    }
}