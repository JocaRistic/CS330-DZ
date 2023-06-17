package rs.ac.metropolitan.repository

import rs.ac.metropolitan.common.UserItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import rs.ac.metropolitan.data.ApiService
import rs.ac.metropolitan.data.RetrofitHelper


class Repository {
    var usersFlow: Flow<List<UserItem>> = flowOf(listOf())

    suspend fun loadUsers(){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.getUsers()
        if (result != null){
            usersFlow = flowOf(result)

        }
    }

    suspend fun submitUser(userItem: UserItem){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.addUser(userItem)
    }

    suspend fun deleteUser(id: String){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.deleteUser(id)
    }
}