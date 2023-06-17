package rs.ac.metropolitan.data

import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rs.ac.metropolitan.common.UserItem

interface ApiService {
    @GET(Constants.USERS_URL)
    suspend fun getUsers(): List<UserItem>

    @POST(Constants.USERS_URL)
    suspend fun addUser(@Body userItem: UserItem)

    @DELETE(Constants.USERS_URL + "/{id}")
    suspend fun deleteUser(@Path ("id") id: String)
}