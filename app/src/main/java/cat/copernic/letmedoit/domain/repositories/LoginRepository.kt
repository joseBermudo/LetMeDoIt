package cat.copernic.letmedoit.domain.repositories

import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.Utils.DataState
import kotlinx.coroutines.flow.Flow


//Interfaz de Login
interface LoginRepository {
    //Funciones de tipo suspend (funciones que pueden ser pausadas y resumidas dentro de las corrutinas).
    suspend fun login(email : String, password : String) : Flow<DataState<Boolean>>

    suspend fun signUp(user : Users, password: String) : Flow<DataState<Users>>

    suspend fun logOut() : Flow<DataState<Boolean>>

    suspend fun getUserData() : Flow<DataState<Boolean>>

    suspend fun saveUser(user : Users) : Flow<DataState<Boolean>>
}