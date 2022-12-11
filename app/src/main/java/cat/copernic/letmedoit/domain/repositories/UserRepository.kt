package cat.copernic.letmedoit.domain.repositories

import android.net.Uri
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.LanguageConstants
import cat.copernic.letmedoit.data.model.*
import kotlinx.coroutines.flow.Flow


interface UserRepository {

    //get
    suspend fun getUser (idUser : String) : Flow<DataState<Users?>>
    suspend fun getServices (idUser : String) : Flow<DataState<ArrayList<UserServices>>>
    suspend fun getFavoriteProfiles() : Flow<DataState<ArrayList<UserFavoriteProfiles>>>
    suspend fun getFavoriteServices() : Flow<DataState<ArrayList<UserFavoriteServices>>>
    suspend fun getChats () : Flow<DataState<ArrayList<UserChats>>>
    suspend fun getHistoryDeals  () : Flow<DataState<ArrayList<HistoryDeal>>>
    suspend fun getOpinions (idUser: String) : Flow<DataState<ArrayList<Opinion>>>

    //delete
    suspend fun deleteService (idService : String) : Flow<DataState<Boolean>>
    suspend fun deleteFavoriteProfile(idProfile: String) : Flow<DataState<Boolean>>
    suspend fun deleteFavoriteService(idService: String) : Flow<DataState<Boolean>>
    suspend fun deleteAvatarFromStorage(imgLink: String) : Flow<DataState<Boolean>>
    suspend fun deleteCurriculumFromStorage(pdfLink : String) : Flow<DataState<Boolean>>
    suspend fun deleteDealFromHistory(idDeal: String, idUser: String) : Flow<DataState<Boolean>>

    //add
    suspend fun addService(idService : String) : Flow<DataState<Boolean>>
    suspend fun addFavoriteProfiles(idProfile : String) : Flow<DataState<Boolean>>
    suspend fun addFavoriteServices(idService: String) : Flow<DataState<Boolean>>
    suspend fun addChat(idChat: String) : Flow<DataState<Boolean>>
    suspend fun addHistoryDeal(idUserOne: String, idUserTwo : String, idDeal : String) : Flow<DataState<Boolean>>
    suspend fun addOpinion(opinion : Opinion,idUser : String) : Flow<DataState<Boolean>>
    suspend fun addAvatarToStorage (fileUri: Uri): Flow<DataState<String>>
    suspend fun addCurriculumToStorage(fileUri : Uri) : Flow<DataState<String>>

    //update
    suspend fun updateName(newName : String) : Flow<DataState<Boolean>>
    suspend fun updateSurname(newSurname : String) : Flow<DataState<Boolean>>
    suspend fun updatePassword(oldPassword : String, newPassword : String, email : String) : Flow<DataState<Boolean>>
    suspend fun updateLanguage(language : Int) : Flow<DataState<Boolean>>
    suspend fun updateDarkTheme(darkTheme : Boolean) : Flow<DataState<Boolean>>
    suspend fun updateAvatar(imgLink : String) : Flow<DataState<Boolean>>
    suspend fun updateCurriculum(pdfLink : String) : Flow<DataState<Boolean>>
    suspend fun updateAboutMe(aboutMe : String) : Flow<DataState<Boolean>>
    suspend fun updateSchedule(schedule: ScheduleMap) : Flow<DataState<Boolean>>
    suspend fun updateContactInfo(contactInfo : ContactInfoMap) : Flow<DataState<Boolean>>
    suspend fun updateLocation(newLocation : String) : Flow<DataState<Boolean>>
    suspend fun updateRating(updatedRating : Float,idUser: String) : Flow<DataState<Boolean>>
}