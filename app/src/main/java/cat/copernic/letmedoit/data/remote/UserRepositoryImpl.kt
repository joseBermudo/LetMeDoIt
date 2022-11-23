package cat.copernic.letmedoit.data.remote

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.LanguageConstants
import cat.copernic.letmedoit.data.model.ContactInfoMap
import cat.copernic.letmedoit.data.model.HistoryDeal
import cat.copernic.letmedoit.data.model.Opinions
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.di.FirebaseModule
import cat.copernic.letmedoit.domain.repositories.UserRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @FirebaseModule.UsersCollection val usersCollection: CollectionReference
) : UserRepository {

    override suspend fun getUser(idUser: String): Flow<DataState<Users>> {
        TODO("Not yet implemented")
    }

    override suspend fun getServices(): Flow<DataState<ArrayList<String>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteProfiles(): Flow<DataState<ArrayList<String>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteServices(): Flow<DataState<ArrayList<String>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getChats(): Flow<DataState<ArrayList<String>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHistoryDeals(): Flow<DataState<ArrayList<HistoryDeal>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOpinions(): Flow<DataState<ArrayList<Opinions>>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteService(idService: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteProfile(idProfile: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteService(idService: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun addService(idService: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteProfiles(idProfile: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteServices(idService: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun addChat(idChat: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun addHistoryDeal(idUser: String, idDeal: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun addOpinion(opinion: Opinions): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateName(newName: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateSurname(newSurname: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateEmail(newEmail: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePassword(
        oldPassword: String,
        newPassword: String
    ): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateLanguage(language: LanguageConstants): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateDarkTheme(darkTheme: Boolean): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateAvatar(imgLink: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCurriculum(pdfLink: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateAboutMe(aboutMe: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateContactInfo(contactInfo: ContactInfoMap): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateLocation(newLocation: String): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateRating(updatedRating: Float): Flow<DataState<Boolean>> {
        TODO("Not yet implemented")
    }


}