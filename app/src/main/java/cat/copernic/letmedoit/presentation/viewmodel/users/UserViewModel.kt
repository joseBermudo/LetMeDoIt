package cat.copernic.letmedoit.presentation.viewmodel.users

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.*
import cat.copernic.letmedoit.domain.usecases.user.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val addChatUseCase: AddChatUseCase,
    private val addFavoriteProfileUseCase: AddFavoriteProfileUseCase,
    private val addFavoriteServiceUseCase: AddFavoriteServiceUseCase,
    private val addHistoryDealUseCase: AddHistoryDealUseCase,
    private val addOpinionUseCase: AddOpinionUseCase,
    private val addServiceUseCase: AddServiceUseCase,
    private val addAvatarToStorageUseCase: AddAvatarToStorageUseCase,
    private val addCurriculumToStorageUseCase: AddCurriculumToStorageUseCase,
    private val deleteCurriculumFromStorageUseCase: DeleteCurriculumFromStorageUseCase,
    private val deleteAvatarFromStorageUseCase: DeleteAvatarFromStorageUseCase,
    private val deleteFavoriteProfileUseCase: DeleteFavoriteProfileUseCase,
    private val deleteFavoriteServiceUseCase: DeleteFavoriteServiceUseCase,
    private val deleteServiceUseCase: DeleteServiceUseCase,
    private val deleteDealFromHistoryUseCase: DeleteDealFromHistoryUseCase,
    private val getChatsUseCase: GetChatsUseCase,
    private val getFavoriteProfilesUseCase: GetFavoriteProfilesUseCase,
    private val getFavoriteServicesUseCase: GetFavoriteServicesUseCase,
    private val getHistoryDealsUseCase: GetHistoryDealsUseCase,
    private val getOpinionsUseCase: GetOpinionsUseCase,
    private val getServicesUseCase: GetServicesUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateAboutMeUseCase: UpdateAboutMeUseCase,
    private val updateAvatarUseCase: UpdateAvatarUseCase,
    private val updateContactInfoUseCase: UpdateContactInfoUseCase,
    private val updateCurriculumUseCase: UpdateCurriculumUseCase,
    private val updateDarkThemeUseCase: UpdateDarkThemeUseCase,
    private val updateLanguageUseCase: UpdateLanguageUseCase,
    private val updateLocationUseCase: UpdateLocationUseCase,
    private val updateNameUseCase: UpdateNameUseCase,
    private val updateSurnameUseCase: UpdateSurnameUseCase,
    private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val updateRatingUseCase: UpdateRatingUseCase,
    private val updateScheduleUseCase: UpdateScheduleUseCase
) : ViewModel() {

    private val mAddChatState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addChatState: LiveData<DataState<Boolean>>
        get() = mAddChatState

    fun addChat(idChat: String) {
        viewModelScope.launch {
            addChatUseCase(idChat)
                .onEach { dataState ->
                    mAddChatState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    private val mAddFavoriteProfileState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addFavoriteProfileState: LiveData<DataState<Boolean>> get() = mAddFavoriteProfileState

    fun addFavoriteProfile(idProfile : String) {
        viewModelScope.launch {
            addFavoriteProfileUseCase(idProfile).onEach { dataState ->
                mAddFavoriteProfileState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mAddFavoriteServiceState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addFavoriteServiceState: LiveData<DataState<Boolean>> get() = mAddFavoriteServiceState
    fun addFavoriteService(idService : String) {
        viewModelScope.launch {
            addFavoriteServiceUseCase(idService).onEach { dataState ->
                mAddFavoriteServiceState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mAddHistoryDealState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addHistoryDealState: LiveData<DataState<Boolean>> get() = mAddHistoryDealState
    fun addHistoryDeal(idUserOne : String,idUserTwo : String, idDeal : String) {
        viewModelScope.launch {
            addHistoryDealUseCase(idUserOne, idUserTwo, idDeal).onEach { dataState ->
                mAddHistoryDealState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mAddOpinionState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addOpinionState: LiveData<DataState<Boolean>> get() = mAddOpinionState
    fun addOpinion(opinion : Opinion,idUser : String) {
        viewModelScope.launch {
            addOpinionUseCase(opinion,idUser).onEach { dataState ->
                mAddOpinionState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mAddServiceState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addServiceState: LiveData<DataState<Boolean>> get() = mAddServiceState
    fun addService(idService: String) {
        viewModelScope.launch {
            addServiceUseCase(idService).onEach { dataState ->
                mAddServiceState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mAddAvatarToStorageState: MutableLiveData<DataState<String>> = MutableLiveData()
    val addAvatarToStorageState: LiveData<DataState<String>> get() = mAddAvatarToStorageState

    fun addAvatarToStorage(uri: Uri) {
        viewModelScope.launch {
            addAvatarToStorageUseCase(uri).onEach { dataState ->
                mAddAvatarToStorageState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mAddCurriculumToStorageState: MutableLiveData<DataState<String>> = MutableLiveData()
    val addCurriculumToStorageState: LiveData<DataState<String>> get() = mAddCurriculumToStorageState

    fun addCurriculumToStorage(uri: Uri) {
        viewModelScope.launch {
            addCurriculumToStorageUseCase(uri).onEach { dataState ->
                mAddCurriculumToStorageState.value = dataState
            }.launchIn(viewModelScope)
        }
    }


    private val mDeleteAvatarFromStorageState: MutableLiveData<DataState<Boolean>> =
        MutableLiveData()
    val deleteAvatarFromStorageState: LiveData<DataState<Boolean>> get() = mDeleteAvatarFromStorageState
    fun deleteAvatarFromStorage(imgLink : String) {
        viewModelScope.launch {
            deleteAvatarFromStorageUseCase(imgLink).onEach { dataState ->
                mDeleteAvatarFromStorageState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mDeleteCurriculumFromStorageState: MutableLiveData<DataState<Boolean>> =
        MutableLiveData()
    val deleteCurriculumFromStorageState: LiveData<DataState<Boolean>> get() = mDeleteCurriculumFromStorageState

    fun deleteCurriculumFromStorage(imgLink : String) {
        viewModelScope.launch {
            deleteCurriculumFromStorageUseCase(imgLink).onEach { dataState ->
                mDeleteCurriculumFromStorageState.value = dataState
            }.launchIn(viewModelScope)
        }
    }



    private val mDeleteFavoriteProfileState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val deleteFavoriteProfileState: LiveData<DataState<Boolean>> get() = mDeleteFavoriteProfileState
    fun deleteFavoriteProfile(idProfile : String) {
        viewModelScope.launch {
            deleteFavoriteProfileUseCase(idProfile).onEach { dataState ->
                mDeleteFavoriteProfileState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mDeleteFavoriteServiceState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val deleteFavoriteServiceState: LiveData<DataState<Boolean>> get() = mDeleteFavoriteServiceState
    fun deleteFavoriteService(idService : String) {
        viewModelScope.launch {
            deleteFavoriteServiceUseCase(idService).onEach { dataState ->
                mDeleteFavoriteServiceState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mDeleteServiceState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val deleteServiceState: LiveData<DataState<Boolean>> get() = mDeleteServiceState
    fun deleteService(idService: String) {
        viewModelScope.launch {
            deleteServiceUseCase(idService).onEach { dataState ->
                mDeleteServiceState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mDeleteDealFromHistoryState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val deleteDealFromHistoryState: LiveData<DataState<Boolean>> get() = mDeleteDealFromHistoryState

    fun deleteDealFromHistory(idDeal: String,idUser : String) {
        viewModelScope.launch {
            deleteDealFromHistoryUseCase(idDeal, idUser).onEach { dataState ->
                mDeleteDealFromHistoryState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mGetChatsState: MutableLiveData<DataState<ArrayList<UserChats>>> = MutableLiveData()
    val getChatsState: LiveData<DataState<ArrayList<UserChats>>> get() = mGetChatsState
    fun getChats() {
        viewModelScope.launch {
            getChatsUseCase().onEach { dataState ->
                mGetChatsState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mGetFavoriteProfilesState: MutableLiveData<DataState<ArrayList<UserFavoriteProfiles>>> = MutableLiveData()
    val getFavoriteProfilesState: LiveData<DataState<ArrayList<UserFavoriteProfiles>>> get() = mGetFavoriteProfilesState
    fun getFavoriteProfiles() {
        viewModelScope.launch {
            getFavoriteProfilesUseCase().onEach { dataState ->
                mGetFavoriteProfilesState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mGetFavoriteServicesState: MutableLiveData<DataState<ArrayList<UserFavoriteServices>>> = MutableLiveData()
    val getFavoriteServicesState: LiveData<DataState<ArrayList<UserFavoriteServices>>> get() = mGetFavoriteServicesState
    fun getFavoriteServices() {
        viewModelScope.launch {
            getFavoriteServicesUseCase().onEach { dataState ->
                mGetFavoriteServicesState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mGetHistoryDealsState: MutableLiveData<DataState<ArrayList<HistoryDeal>>> = MutableLiveData()
    val getHistoryDealsState: LiveData<DataState<ArrayList<HistoryDeal>>> get() = mGetHistoryDealsState
    fun getHistoryDeals() {
        viewModelScope.launch {
            getHistoryDealsUseCase().onEach { dataState ->
                mGetHistoryDealsState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mGetOpinionsState: MutableLiveData<DataState<ArrayList<Opinion>>> = MutableLiveData()
    val getOpinionsState: LiveData<DataState<ArrayList<Opinion>>> get() = mGetOpinionsState
    fun getOpinions(idUser: String) {
        viewModelScope.launch {
            getOpinionsUseCase(idUser).onEach { dataState ->
                mGetOpinionsState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mGetServicesState: MutableLiveData<DataState<ArrayList<UserServices>>> = MutableLiveData()
    val getServicesState: LiveData<DataState<ArrayList<UserServices>>> get() = mGetServicesState
    fun getServices(idUser: String) {
        viewModelScope.launch {
            getServicesUseCase(idUser).onEach { dataState ->
                mGetServicesState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mGetUserState: MutableLiveData<DataState<Users?>> = MutableLiveData()
    val getUserState: LiveData<DataState<Users?>> get() = mGetUserState
    fun getUser(idUser: String) {
        viewModelScope.launch {
            getUserUseCase(idUser).onEach { dataState ->
                mGetUserState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mUpdateAboutMeState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateAboutMeState: LiveData<DataState<Boolean>> get() = mUpdateAboutMeState
    fun updateAboutMe(aboutMe : String) {
        viewModelScope.launch {
            updateAboutMeUseCase(aboutMe).onEach { dataState ->
                mUpdateAboutMeState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mUpdateAvatarState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateAvatarState: LiveData<DataState<Boolean>> get() = mUpdateAvatarState
    fun updateAvatar(imgLink: String) {
        viewModelScope.launch {
            updateAvatarUseCase(imgLink).onEach { dataState ->
                mUpdateAvatarState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mUpdateContactInfoState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateContactInfoState: LiveData<DataState<Boolean>> get() = mUpdateContactInfoState
    fun updateContactInfo(contactInfo : ContactInfoMap) {
        viewModelScope.launch {
            updateContactInfoUseCase(contactInfo).onEach { dataState ->
                mUpdateContactInfoState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mUpdateCurriculumState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateCurriculumState: LiveData<DataState<Boolean>> get() = mUpdateCurriculumState

    fun updateCurriculum(pdfLink : String) {
        viewModelScope.launch {
            updateCurriculumUseCase(pdfLink).onEach { dataState ->
                mUpdateCurriculumState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mUpdateDarkThemeState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateDarkThemeState: LiveData<DataState<Boolean>> get() = mUpdateDarkThemeState
    fun updateDarkTheme(darkTheme : Boolean) {
        viewModelScope.launch {
            updateDarkThemeUseCase(darkTheme).onEach { dataState ->
                mUpdateDarkThemeState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mUpdateLanguageState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateLanguageState: LiveData<DataState<Boolean>> get() = mUpdateLanguageState
    fun updateLanguage(language: Int) {
        viewModelScope.launch {
            updateLanguageUseCase(language).onEach { dataState ->
                mUpdateLanguageState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mUpdateLocationState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateLocationState: LiveData<DataState<Boolean>> get() = mUpdateLocationState
    fun updateLocation(newLocation : String) {
        viewModelScope.launch {
            updateLocationUseCase(newLocation).onEach { dataState ->
                mUpdateLocationState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mUpdateNameState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateNameState: LiveData<DataState<Boolean>> get() = mUpdateNameState
    fun updateName(newName : String) {
        viewModelScope.launch {
            updateNameUseCase(newName).onEach { dataState ->
                mUpdateNameState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mUpdateSurnameState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateSurnameState: LiveData<DataState<Boolean>> get() = mUpdateSurnameState
    fun updateSurname(newSurname : String) {
        viewModelScope.launch {
            updateSurnameUseCase(newSurname).onEach { dataState ->
                mUpdateSurnameState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mUpdatePasswordState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updatePasswordState: LiveData<DataState<Boolean>> get() = mUpdatePasswordState
    fun updatePassword(oldPassword : String, newPassword : String, email : String) {
        viewModelScope.launch {
            updatePasswordUseCase(oldPassword, newPassword, email).onEach { dataState ->
                mUpdatePasswordState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mUpdateRatingState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateRatingState: LiveData<DataState<Boolean>> get() = mUpdateRatingState
    fun updateRating(updatedRating : Float,idUser: String) {
        viewModelScope.launch {
            updateRatingUseCase(updatedRating,idUser).onEach { dataState ->
                mUpdateRatingState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    private val mUpdateScheduleState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateScheduleState: LiveData<DataState<Boolean>> get() = mUpdateScheduleState
    fun updateSchedule(schedule : ScheduleMap) {
        viewModelScope.launch {
            updateScheduleUseCase(schedule).onEach { dataState ->
                mUpdateScheduleState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

}