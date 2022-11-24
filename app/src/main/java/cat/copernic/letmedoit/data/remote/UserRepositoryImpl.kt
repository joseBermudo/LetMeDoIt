package cat.copernic.letmedoit.data.remote

import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.LanguageConstants
import cat.copernic.letmedoit.Utils.UserConstants
import cat.copernic.letmedoit.data.model.ContactInfoMap
import cat.copernic.letmedoit.data.model.HistoryDeal
import cat.copernic.letmedoit.data.model.Opinions
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.di.FirebaseModule
import cat.copernic.letmedoit.domain.repositories.UserRepository
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    @FirebaseModule.UsersCollection val usersCollection: CollectionReference
) : UserRepository {

    //GET
    private suspend fun generalArrayListObtainer(idUser: String, userCollection : String) : Flow<DataState<ArrayList<String>>> = flow {
        var generalArrayList = ArrayList<String>()
        emit(DataState.Loading)
        try {
            idUser.let {
                usersCollection.document(it).collection(UserConstants.SERVICES)
                    .get()
                    .addOnSuccessListener { data ->
                        generalArrayList = ArrayList(data.toObjects(String::class.java))
                    }
                    .addOnFailureListener{ error ->
                        throw Exception(error)
                    }
                    .await()
            }
            emit(DataState.Success(generalArrayList))
            emit(DataState.Finished)
        }catch (e : Exception){
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getUser(idUser: String): Flow<DataState<Users?>> = flow {
        var user : Users? = null
        emit(DataState.Loading)
        try {
            idUser.let {
                usersCollection.document(it)
                    .get()
                    .addOnSuccessListener { document ->
                        user = document.toObject(Users::class.java)!!
                    }
                    .addOnFailureListener {
                        user = null
                    }
                    .await()
            }
            emit(DataState.Success(user))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getServices(idUser: String): Flow<DataState<ArrayList<String>>>{
        return generalArrayListObtainer(idUser,UserConstants.SERVICES)
    }

    override suspend fun getFavoriteProfiles(): Flow<DataState<ArrayList<String>>>{
        return generalArrayListObtainer(Constants.USER_LOGGED_IN_ID,UserConstants.FAVORITE_PROFILES)
    }

    override suspend fun getFavoriteServices(): Flow<DataState<ArrayList<String>>> {
        return generalArrayListObtainer(Constants.USER_LOGGED_IN_ID,UserConstants.FAVORITE_SERVICES)
    }

    override suspend fun getChats(): Flow<DataState<ArrayList<String>>> {
        return generalArrayListObtainer(Constants.USER_LOGGED_IN_ID,UserConstants.CHATS)
    }


    override suspend fun getHistoryDeals(): Flow<DataState<ArrayList<HistoryDeal>>> = flow {
        var historyDeals = ArrayList<HistoryDeal>()
        emit(DataState.Loading)
        try {
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).collection(UserConstants.HISTORY_DEALS)
                    .get()
                    .addOnSuccessListener { data ->
                        historyDeals = ArrayList(data.toObjects(HistoryDeal::class.java))
                    }
                    .addOnFailureListener{ error ->
                        throw Exception(error)
                    }
                    .await()
            }
            emit(DataState.Success(historyDeals))
            emit(DataState.Finished)
        }catch (e : Exception){
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getOpinions(idUser: String): Flow<DataState<ArrayList<Opinions>>> = flow{
        var opinions = ArrayList<Opinions>()
        emit(DataState.Loading)
        try {
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).collection(UserConstants.OPINIONS)
                    .get()
                    .addOnSuccessListener { data ->
                        opinions = ArrayList(data.toObjects(Opinions::class.java))
                    }
                    .addOnFailureListener{ error ->
                        throw Exception(error)
                    }
                    .await()
            }
            emit(DataState.Success(opinions))
            emit(DataState.Finished)
        }catch (e : Exception){
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    //DELETE
    override suspend fun deleteService(idService: String): Flow<DataState<Boolean>> = flow{
        var isSuccesful = false
        emit(DataState.Loading)
        try {
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).collection(UserConstants.SERVICES).document(idService)
                    .delete()
                    .addOnSuccessListener { data ->
                        isSuccesful = true
                    }
                    .addOnFailureListener{ error ->
                        isSuccesful = false
                        throw Exception(error)
                    }
                    .await()
            }
            emit(DataState.Success(isSuccesful))
            emit(DataState.Finished)
        }catch (e : Exception){
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteFavoriteProfile(idProfile: String): Flow<DataState<Boolean>> = flow {
        var isSuccesful = false
        emit(DataState.Loading)
        try {
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).collection(UserConstants.FAVORITE_PROFILES).document(idProfile)
                    .delete()
                    .addOnSuccessListener { data ->
                        isSuccesful = true
                    }
                    .addOnFailureListener{ error ->
                        isSuccesful = false
                        throw Exception(error)
                    }
                    .await()
            }
            emit(DataState.Success(isSuccesful))
            emit(DataState.Finished)
        }catch (e : Exception){
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteFavoriteService(idService: String): Flow<DataState<Boolean>> = flow {
        var isSuccesful = false
        emit(DataState.Loading)
        try {
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).collection(UserConstants.FAVORITE_SERVICES).document(idService)
                    .delete()
                    .addOnSuccessListener { data ->
                        isSuccesful = true
                    }
                    .addOnFailureListener{ error ->
                        isSuccesful = false
                        throw Exception(error)
                    }
                    .await()
            }
            emit(DataState.Success(isSuccesful))
            emit(DataState.Finished)
        }catch (e : Exception){
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteAvatarFromStorage(imgLink: String): Flow<DataState<Boolean>> = flow{
        var isSuccessful = false
        emit(DataState.Loading)
        val sRef: StorageReference =
            FirebaseModule.storageProvider().getReferenceFromUrl(imgLink)

        try {
            sRef.delete()
                .addOnSuccessListener {
                    isSuccessful = true
                }
                .addOnFailureListener{
                    throw Exception(it)
                }
                .await()

            emit(DataState.Success(isSuccessful))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)


    //ADD
    override suspend fun addService(idService: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).collection(UserConstants.SERVICES).document(idService).set(idService, SetOptions.merge())
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

    override suspend fun addFavoriteProfiles(idProfile: String): Flow<DataState<Boolean>> = flow{
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).collection(UserConstants.FAVORITE_PROFILES).document(idProfile).set(idProfile, SetOptions.merge())
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

    override suspend fun addFavoriteServices(idService: String): Flow<DataState<Boolean>> = flow{
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).collection(UserConstants.FAVORITE_SERVICES).document(idService).set(idService, SetOptions.merge())
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

    override suspend fun addChat(idChat: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).collection(UserConstants.CHATS).document(idChat).set(idChat, SetOptions.merge())
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

    override suspend fun addHistoryDeal(idUser: String, idDeal: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).collection(UserConstants.HISTORY_DEALS).document(idUser).collection(UserConstants.DEALS).document(idDeal).set(idDeal, SetOptions.merge())
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

    override suspend fun addOpinion(opinion: Opinions): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).collection(UserConstants.OPINIONS).document(opinion.id).set(opinion, SetOptions.merge())
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

    override suspend fun updateName(newName: String): Flow<DataState<Boolean>>  = flow {
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).update(UserConstants.NAME,newName)
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

    override suspend fun updateSurname(newSurname: String): Flow<DataState<Boolean>> = flow{
        emit(DataState.Loading)
        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).update(UserConstants.SURNAME,newSurname)
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

    override suspend fun updatePassword(
        oldPassword: String,
        newPassword: String,
        email : String
    ): Flow<DataState<Boolean>> = flow<DataState<Boolean>> {
        emit(DataState.Loading)
        try {
            var changeSuccesful: Boolean = false

            val credential = EmailAuthProvider
                .getCredential(email,oldPassword)

            val user =  FirebaseModule.firebaseAuthProvider().currentUser!!

            user.reauthenticate(credential)
                .addOnSuccessListener {
                    user.updatePassword(newPassword)
                        .addOnSuccessListener {
                            changeSuccesful = true
                        }
                        .addOnFailureListener{
                            throw Exception(it)
                        }
                }
                .addOnFailureListener{
                    throw Exception(it)
                }
                .await()
            emit(DataState.Success(changeSuccesful))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateLanguage(language: Int): Flow<DataState<Boolean>> = flow<DataState<Boolean>> {
        emit(DataState.Loading)

        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).update(UserConstants.LANGUAGE,language)
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

    override suspend fun updateDarkTheme(darkTheme: Boolean): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)

        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).update(UserConstants.DARK_THEME,darkTheme)
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

    override suspend fun updateAvatar(imgLink: String): Flow<DataState<Boolean>> = flow{
        emit(DataState.Loading)

        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).update(UserConstants.AVATAR,imgLink)
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

    override suspend fun updateCurriculum(pdfLink: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)

        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).update(UserConstants.CURRICULUM,pdfLink)
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

    override suspend fun updateAboutMe(aboutMe: String): Flow<DataState<Boolean>> = flow{
        emit(DataState.Loading)

        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).update(UserConstants.ABOUT_ME,aboutMe)
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

    override suspend fun updateContactInfo(contactInfo: ContactInfoMap): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)

        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).update(UserConstants.CONTACT_INFO,contactInfo)
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

    override suspend fun updateLocation(newLocation: String): Flow<DataState<Boolean>> = flow{
        emit(DataState.Loading)

        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).update(UserConstants.LOCATION,newLocation)
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

    override suspend fun updateRating(updatedRating: Float): Flow<DataState<Boolean>> = flow{
        emit(DataState.Loading)

        try {
            var uploadSuccesful: Boolean = false
            Constants.USER_LOGGED_IN_ID.let {
                usersCollection.document(it).update(UserConstants.RATING,updatedRating)
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