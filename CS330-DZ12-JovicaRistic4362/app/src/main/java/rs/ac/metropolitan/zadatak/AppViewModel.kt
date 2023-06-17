package rs.ac.metropolitan.zadatak

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import rs.ac.metropolitan.common.UserItem
import rs.ac.metropolitan.repository.Repository
import rs.ac.metropolitan.zadatak.navigation.NavigationRoutes

class AppViewModel : ViewModel() {
    lateinit var navController: NavHostController
    val repository = Repository()
    var granted = mutableStateOf(false)

    private val _users = MutableLiveData<List<UserItem>>()
    val users: LiveData<List<UserItem>>
        get() = _users

    //Komunikacija sa repozitorijumom
    fun loadUsers() {
        GlobalScope.launch {
            repository.loadUsers()
            MainScope().launch {
                repository.usersFlow.collect() {
                    _users.value = it
                }
            }
        }
    }

    fun getUser(id: String): UserItem? {
        return _users.value?.find { it.id == id }
    }

    fun deleteUser(id: String) {
        GlobalScope.launch {
            repository.deleteUser(id)
        }
        goBack()
    }

    fun submitUser(userItem: UserItem) {
        GlobalScope.launch {
            repository.submitUser(userItem)
        }
    }

    fun navigateToUserDetail(id: String){
        navController.navigate(NavigationRoutes.UserDetailScreen.createRoute(id))
    }

    fun navigateToNewUser() {
        navController.navigate(NavigationRoutes.NewUser.route)
    }

    fun goBack() {
        navController.popBackStack()
    }
}