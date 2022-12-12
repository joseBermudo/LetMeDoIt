package cat.copernic.letmedoit.data.remote

import cat.copernic.letmedoit.Utils.Constants.USER_LOGGED_IN_ID
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.di.FirebaseModule
import cat.copernic.letmedoit.domain.repositories.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

//Implementación de la interfaz de Login. Utiliza la inyección de dependencias utilizando @Inject a fin de pedir las dependencias.
class LoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    @FirebaseModule.UsersCollection private val usersCollection: CollectionReference
) : LoginRepository {

    //Corrutinas --> flow que devuelven estados
    override suspend fun login(email: String, password: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var isSuccesful: Boolean = false
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { isSuccesful = true }
                .addOnFailureListener { isSuccesful = false }
                .await()
            emit(DataState.Success(isSuccesful))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun signUp(user: Users, password: String): Flow<DataState<Users>> = flow {
        //Cargando
        emit(DataState.Loading)
        try {
            lateinit var exception: Exception
            lateinit var registeredUser: Users

            //Creamos el usuario con email y contraseña
            auth.createUserWithEmailAndPassword(user.email!!, password)
                .addOnCompleteListener { task ->

                    //Si el usuario se crea creamos el objecto del usuario como registeredUser
                    if (task.isSuccessful) {

                        val firebaseUser: FirebaseUser = task.result!!.user!!

                        registeredUser = Users(
                            id = firebaseUser.uid,
                            name = user.name,
                            surname = user.surname,
                            email = user.email,
                            language = user.language,
                            darkTheme = user.darkTheme,
                            avatar = user.avatar,
                            curriculum = user.curriculum,
                            schedule = user.schedule,
                            aboutMe = user.aboutMe,
                            contactInfo = user.contactInfo,
                            location = user.location,
                            servicesId = user.servicesId,
                            favorites = user.favorites,
                            chatsId = user.chatsId,
                            historyDeals = user.historyDeals,
                            opinions = user.opinions,
                            rating = user.rating,
                            banned = user.banned,
                            admin = user.admin,
                            username = user.username

                        )

                    } else {
                        exception = task.exception!!
                    }
                }.await()
            if (registeredUser.id != "") {
                emit(DataState.Success(registeredUser))
                emit(DataState.Finished)
            } else {
                emit(DataState.Error(exception))
                emit(DataState.Finished)
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun logOut(): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            auth.signOut()
            emit(DataState.Success(true))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getUserData(): Flow<DataState<Boolean>> = flow {
        var isSuccesful = false
        val currentUser = auth.currentUser
        emit(DataState.Loading)
        try {
            currentUser?.uid?.let {
                usersCollection.document(it)
                    .get()
                    .addOnSuccessListener { document ->
                        val user = document.toObject(Users::class.java)!!
                        isSuccesful = true
                        USER_LOGGED_IN_ID = user.id
                    }
                    .addOnFailureListener {
                        isSuccesful = false
                    }
                    .await()
            }
            emit(DataState.Success(isSuccesful))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun saveUser(user: Users): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            //Guardamos el usuario, pero si ya existe un documento con estos datos hace un merge sobreescrbiendo los datos.
            user.id?.let {
                usersCollection.document(it).set(user, SetOptions.merge())
                    .addOnSuccessListener { uploadSuccesful = true }
                    .addOnFailureListener { uploadSuccesful = false }
                    .await()
            }
            emit(DataState.Success(uploadSuccesful))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)
}