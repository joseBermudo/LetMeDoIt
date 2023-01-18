package cat.copernic.letmedoit.presentation.viewmodel.users

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.datahepers.*
import cat.copernic.letmedoit.data.model.*
import cat.copernic.letmedoit.domain.usecases.user.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Una clase ViewModel que conecta la vista con el repositorio Users.
 * Proporciona objetos LiveData para mostrar el estado actual de las operaciones.
 * Todas las funciones de esta clase actuan sobre la base de datos.
 * Utiliza los UseCase para comunicarse con el repositorio
 * @param addChatUseCase objeto AddChatUseCase
 * @param addFavoriteProfileUseCase  objeto de AddFavoriteProfileUseCase
 * @param addFavoriteServiceUseCase  objeto de AddFavoriteServiceUseCase
 * @param addHistoryDealUseCase  objeto de AddHistoryDealUseCase
 * @param addOpinionUseCase  objeto de AddOpinionUseCase
 * @param addServiceUseCase  objeto de AddServiceUseCase
 * @param addDeviceTokenUseCase  objeto de AddDeviceTokenUseCase
 * @param addAvatarToStorageUseCase  objeto de AddAvatarToStorageUseCase
 * @param addCurriculumToStorageUseCase  objeto de AddCurriculumToStorageUseCase
 * @param deleteCurriculumFromStorageUseCase  objeto de DeleteCurriculumFromStorageUseCase
 * @param deleteAvatarFromStorageUseCase  objeto de DeleteAvatarFromStorageUseCase
 * @param deleteFavoriteProfileUseCase  objeto de DeleteFavoriteProfileUseCase
 * @param deleteFavoriteServiceUseCase  objeto de DeleteFavoriteServiceUseCase
 * @param deleteServiceUseCase  objeto de DeleteServiceUseCase
 * @param deleteDealFromHistoryUseCase  objeto de DeleteDealFromHistoryUseCase
 * @param getChatsUseCase  objeto de GetChatsUseCase
 * @param getAllUserUseCase  objeto de GetAllUsersUseCase
 * @param getFavoriteProfilesUseCase  objeto de GetFavoriteProfilesUseCase
 * @param getFavoriteServicesUseCase  objeto de GetFavoriteServicesUseCase
 * @param getHistoryDealsUseCase  objeto de GetHistoryDealsUseCase
 * @param getOpinionsUseCase  objeto de GetOpinionsUseCase
 * @param getServicesUseCase  objeto de GetServicesUseCase
 * @param getUserUseCase  objeto de GetUserUseCase
 * @param updateAboutMeUseCase  objeto de UpdateAboutMeUseCase
 * @param updateAvatarUseCase  objeto de UpdateAvatarUseCase
 * @param updateContactInfoUseCase  objeto de UpdateContactInfoUseCase
 * @param updateCurriculumUseCase  objeto de UpdateCurriculumUseCase
 * @param updateDarkThemeUseCase  objeto de UpdateDarkThemeUseCase
 * @param updateLanguageUseCase  objeto de UpdateLanguageUseCase
 * @param updateBanUseCase  objeto de UpdateBanUseCase
 * @param updateLocationUseCase  objeto de UpdateLocationUseCase
 * @param updateNameUseCase  objeto de UpdateNameUseCase
 * @param updateSurnameUseCase  objeto de UpdateSurnameUseCase
 * @param updatePasswordUseCase  objeto de UpdatePasswordUseCase
 * @param updateRatingUseCase  objeto de UpdateRatingUseCase
 * @param updateScheduleUseCase  objeto de UpdateScheduleUseCase
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    private val addChatUseCase: AddChatUseCase,
    private val addFavoriteProfileUseCase: AddFavoriteProfileUseCase,
    private val addFavoriteServiceUseCase: AddFavoriteServiceUseCase,
    private val addHistoryDealUseCase: AddHistoryDealUseCase,
    private val addOpinionUseCase: AddOpinionUseCase,
    private val addServiceUseCase: AddServiceUseCase,
    private val addDeviceTokenUseCase: AddDeviceTokenUseCase,
    private val addAvatarToStorageUseCase: AddAvatarToStorageUseCase,
    private val addCurriculumToStorageUseCase: AddCurriculumToStorageUseCase,
    private val deleteCurriculumFromStorageUseCase: DeleteCurriculumFromStorageUseCase,
    private val deleteAvatarFromStorageUseCase: DeleteAvatarFromStorageUseCase,
    private val deleteFavoriteProfileUseCase: DeleteFavoriteProfileUseCase,
    private val deleteFavoriteServiceUseCase: DeleteFavoriteServiceUseCase,
    private val deleteServiceUseCase: DeleteServiceUseCase,
    private val deleteDealFromHistoryUseCase: DeleteDealFromHistoryUseCase,
    private val getChatsUseCase: GetChatsUseCase,
    private val getAllUserUseCase: GetAllUsersUseCase,
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
    private val updateBanUseCase: UpdateBanUseCase,
    private val updateLocationUseCase: UpdateLocationUseCase,
    private val updateNameUseCase: UpdateNameUseCase,
    private val updateSurnameUseCase: UpdateSurnameUseCase,
    private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val updateRatingUseCase: UpdateRatingUseCase,
    private val updateScheduleUseCase: UpdateScheduleUseCase
) : ViewModel() {

    /**
     * Estado de la operacion añadir un chat
     * Puede contener 4 valores (DataState)
     */
    private val mAddChatState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addChatState: LiveData<DataState<Boolean>>
        get() = mAddChatState

    /**
     *Funcion que añade un chat
     * Para futuras versiones
     * @param idChat id del chat
     */
    fun addChat(idChat: String) {
        viewModelScope.launch {
            addChatUseCase(idChat).onEach { dataState ->
                    mAddChatState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la operacion añadir un perfil a favoritos
     * Puede contener 4 valores (DataState)
     */
    private val mAddFavoriteProfileState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addFavoriteProfileState: LiveData<DataState<Boolean>> get() = mAddFavoriteProfileState

    /**
     * Funcion que añade un perfil a la lista de favoritos
     * Utiliza addFavoriteProfileUseCase para invocar la funcion correspondiente del repositorio
     * @param idProfile id del perfil
     */
    fun addFavoriteProfile(idProfile: String) {
        viewModelScope.launch {
            addFavoriteProfileUseCase(idProfile).onEach { dataState ->
                mAddFavoriteProfileState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la operacion de añadir un servicio a favoritos
     * Puede contener 4 valores (DataState)
     */
    private val mAddFavoriteServiceState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addFavoriteServiceState: LiveData<DataState<Boolean>> get() = mAddFavoriteServiceState

    /**
     * Funcion que añade un servicio a favoritos
     * Utiliza el addFavoriteServiceUseCase para invocar la funcion correspondiente del repositorio
     * @param idService id del servicio
     */
    fun addFavoriteService(idService: String) {
        viewModelScope.launch {
            addFavoriteServiceUseCase(idService).onEach { dataState ->
                mAddFavoriteServiceState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la operación de añadir un trato al historial de tratos
     * Puede contener 4 valores (DataState)
     */
    private val mAddHistoryDealState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addHistoryDealState: LiveData<DataState<Boolean>> get() = mAddHistoryDealState

    /**
     * Funcion que añade un trato al historial de tratos
     * Utiliza el addHistoryDealUseCasa para invocar la funcion correspondiente del repositorio
     * @param idUserOne id del prime usuario
     * @param idUserTwo id del segundo usuario
     * @param idDeal id del trato
     */
    fun addHistoryDeal(idUserOne: String, idUserTwo: String, idDeal: String) {
        viewModelScope.launch {
            addHistoryDealUseCase(idUserOne, idUserTwo, idDeal).onEach { dataState ->
                mAddHistoryDealState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la operacion de crear una opinion
     * Puede contener 4 valores (DataState)
     */
    private val mAddOpinionState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addOpinionState: LiveData<DataState<Boolean>> get() = mAddOpinionState

    /**
     * Funcion que añade una opinion
     * Utiliza el addOpinionUseCase para invocar la funcion correpondiente del repositorio
     * @param opinion instance de la clase Opinion
     * @param idUser id del usuario que recibe la opinion
     */
    fun addOpinion(opinion: Opinion, idUser: String) {
        viewModelScope.launch {
            addOpinionUseCase(opinion, idUser).onEach { dataState ->
                mAddOpinionState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de subir un servicio
     * Puede contener 4 valores (DataState)
     */
    private val mAddServiceState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addServiceState: LiveData<DataState<Boolean>> get() = mAddServiceState

    /**
     * Funcion que sube un servicio
     * Utiliza el addServiceUseCase para invocar la funcion correspondiente del repositorio
     * @param idService id del servicio
     */
    fun addService(idService: String) {
        viewModelScope.launch {
            addServiceUseCase(idService).onEach { dataState ->
                mAddServiceState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la operacion de poner un token
     * Puede contener 4 valores (DataState)
     */
    private val mAddDeviceTokenState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val addDeviceTokenState: LiveData<DataState<Boolean>> get() = mAddDeviceTokenState

    /**
     * Inserta un token en el dispositivo
     * Utiliza addDeviceTokenUseCase para invocar la funcion correspondiente del repositorio
     * @param token
     */
    fun addDeviceToken(token: String) {
        viewModelScope.launch {
            addDeviceTokenUseCase(token).onEach { dataState ->
                mAddDeviceTokenState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de subir una imagene de perfil
     * Puede contener 4 valores (DataState)
     */
    private val mAddAvatarToStorageState: MutableLiveData<DataState<String>> = MutableLiveData()
    val addAvatarToStorageState: LiveData<DataState<String>> get() = mAddAvatarToStorageState

    /**
     * Sube una imagen de perfil
     * Utiliza el addAvatarToStorageUseCase para invocar la funcion correpondiente del repositorio
     * @param uri Uri de la imagen
     */
    fun addAvatarToStorage(uri: Uri) {
        viewModelScope.launch {
            addAvatarToStorageUseCase(uri).onEach { dataState ->
                mAddAvatarToStorageState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de subir un curriculum (pdf)
     * Puede contener 4 valores (DataState)
     */
    private val mAddCurriculumToStorageState: MutableLiveData<DataState<String>> = MutableLiveData()
    val addCurriculumToStorageState: LiveData<DataState<String>> get() = mAddCurriculumToStorageState

    /**
     * Sube un curriculum(pdf)
     * Utiliza addCurriculumToStorageUseCase para invocar a la funcion correpondiente del repositorio
     * @param uri Uri del PDF
     */
    fun addCurriculumToStorage(uri: Uri) {
        viewModelScope.launch {
            addCurriculumToStorageUseCase(uri).onEach { dataState ->
                mAddCurriculumToStorageState.value = dataState
            }.launchIn(viewModelScope)
        }
    }


    /**
     * Estado de elimnar la imagen de perfil de la bd
     * Puede contener 4 valores (DataState)
     */
    private val mDeleteAvatarFromStorageState: MutableLiveData<DataState<Boolean>> =
        MutableLiveData()
    val deleteAvatarFromStorageState: LiveData<DataState<Boolean>> get() = mDeleteAvatarFromStorageState

    /**
     * Elimina la imagen de perfil
     * Utiliza deleteAvatarFromStorageUseCase para invocar la funcion correspondiente del repositorio
     * @param imgLink link de la imagen
     */
    fun deleteAvatarFromStorage(imgLink: String) {
        viewModelScope.launch {
            deleteAvatarFromStorageUseCase(imgLink).onEach { dataState ->
                mDeleteAvatarFromStorageState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado del elimnar el curriculum(pdf) de la bd
     * Puede contener 4 valores (DataState)
     */
    private val mDeleteCurriculumFromStorageState: MutableLiveData<DataState<Boolean>> =
        MutableLiveData()
    val deleteCurriculumFromStorageState: LiveData<DataState<Boolean>> get() = mDeleteCurriculumFromStorageState

    /**
     * Elimina el curriculum(pdf) de la base de datos
     * Utiliza deleteCurriculumFromStorageUseCase para invocar la funcion correspondiente del repositorio
     * @param imgLink link del PDF
     */
    fun deleteCurriculumFromStorage(imgLink: String) {
        viewModelScope.launch {
            deleteCurriculumFromStorageUseCase(imgLink).onEach { dataState ->
                mDeleteCurriculumFromStorageState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de eliminar un perfil de la lista de favoritos
     * Puede contener 4 valores (DataState)
     */
    private val mDeleteFavoriteProfileState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val deleteFavoriteProfileState: LiveData<DataState<Boolean>> get() = mDeleteFavoriteProfileState

    /**
     * Elimina un perfil de la lista de favoritos
     * Utiliza deleteFavoriteProfileUseCase para invocar la funcion del repositorio
     * @param idProfile id del perfil
     */
    fun deleteFavoriteProfile(idProfile: String) {
        viewModelScope.launch {
            deleteFavoriteProfileUseCase(idProfile).onEach { dataState ->
                mDeleteFavoriteProfileState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de eliminar un servicio de la lista de favoritos
     * Puede contener 4 valores (DataState)
     */
    private val mDeleteFavoriteServiceState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val deleteFavoriteServiceState: LiveData<DataState<Boolean>> get() = mDeleteFavoriteServiceState

    /**
     * Eliminar un servicio de la lista de favoritos
     * Utiliza deleteFavoriteServiceUseCase para invocar la funcion del repositorio.
     * @param idService id del servicio
     */
    fun deleteFavoriteService(idService: String) {
        viewModelScope.launch {
            deleteFavoriteServiceUseCase(idService).onEach { dataState ->
                mDeleteFavoriteServiceState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de eliminar un servicio
     * Puede contener 4 valores (DataState)
     */
    private val mDeleteServiceState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val deleteServiceState: LiveData<DataState<Boolean>> get() = mDeleteServiceState

    /**
     * Elimina un servicio
     * Utiliza deleteServiceUseCase para invocar la funcion del repositorio
     * @param idService id del servicio
     */
    fun deleteService(idService: String) {
        viewModelScope.launch {
            deleteServiceUseCase(idService).onEach { dataState ->
                mDeleteServiceState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de eliminar un trato del historial de tratos
     * Puede contener 4 valores (DataState)
     */
    private val mDeleteDealFromHistoryState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val deleteDealFromHistoryState: LiveData<DataState<Boolean>> get() = mDeleteDealFromHistoryState

    /**
     * Elimina un trato del historial de tratos
     * Utiliza deleteDealFromHistoryUseCase para invocar la funcion del repositorio
     * @param idDeal id del trato
     * @param idUser id del usuario 1
     * @param idUserTwo id del usuario 2
     */
    fun deleteDealFromHistory(idDeal: String, idUser: String, idUserTwo: String) {
        viewModelScope.launch {
            deleteDealFromHistoryUseCase(idDeal, idUser, idUserTwo).onEach { dataState ->
                mDeleteDealFromHistoryState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la lectura de chats
     * Puede contener 4 valores (DataState)
     */
    private val mGetChatsState: MutableLiveData<DataState<ArrayList<UserChats>>> = MutableLiveData()
    val getChatsState: LiveData<DataState<ArrayList<UserChats>>> get() = mGetChatsState

    /**
     * Obtiene un chat de la bd
     *  Para futuras versiones
     *  Utiliza getChatsUseCase para invocar la funcion del repositorio
     */
    fun getChats() {
        viewModelScope.launch {
            getChatsUseCase().onEach { dataState ->
                mGetChatsState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de los perfiles favoritos
     * Puede contener 4 valores (DataState)
     */
    private val mGetFavoriteProfilesState: MutableLiveData<DataState<ArrayList<UserFavoriteProfiles>>> =
        MutableLiveData()
    val getFavoriteProfilesState: LiveData<DataState<ArrayList<UserFavoriteProfiles>>> get() = mGetFavoriteProfilesState

    /**
     * Lee los perfiles favoritos de un usario de la base de datos
     * Utiliza getFavoriteProfilesUseCase para invocar la funcion del repositorio
     */
    fun getFavoriteProfiles() {
        viewModelScope.launch {
            getFavoriteProfilesUseCase().onEach { dataState ->
                mGetFavoriteProfilesState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la lectura de servicios favoritos
     * Puede contener 4 valores (DataState)
     */
    private val mGetFavoriteServicesState: MutableLiveData<DataState<ArrayList<UserFavoriteServices>>> =
        MutableLiveData()
    val getFavoriteServicesState: LiveData<DataState<ArrayList<UserFavoriteServices>>> get() = mGetFavoriteServicesState

    /**
     * Lee los servicios favortios de un usuario de la base de datos
     * Utiliza getFavoriteServicesUseCase para invocar la funcion del repositorio
     */
    fun getFavoriteServices() {
        viewModelScope.launch {
            getFavoriteServicesUseCase().onEach { dataState ->
                mGetFavoriteServicesState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la lectura de tratos del usuario
     * Puede contener 4 valores (DataState)
     */
    private val mGetHistoryDealsState: MutableLiveData<DataState<ArrayList<HistoryDeal>>> =
        MutableLiveData()
    val getHistoryDealsState: LiveData<DataState<ArrayList<HistoryDeal>>> get() = mGetHistoryDealsState

    /**
     * Lee el historial de tratos del usuario
     * Utiliza getHistoryDealsUseCase para invocar la funcion del repositorio
     */
    fun getHistoryDeals() {
        viewModelScope.launch {
            getHistoryDealsUseCase().onEach { dataState ->
                mGetHistoryDealsState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la lectura de opiniones de un usuario
     * Puede contener 4 valores (DataState)
     */
    private val mGetOpinionsState: MutableLiveData<DataState<ArrayList<Opinion>>> =
        MutableLiveData()
    val getOpinionsState: LiveData<DataState<ArrayList<Opinion>>> get() = mGetOpinionsState

    /**
     * Lee las opiniones de un usuario de la base de datos
     * Utiliza getOpinionsUseCase para invocar la funcion del repositorio
     * @param idUser id del usuario
     */
    fun getOpinions(idUser: String) {
        viewModelScope.launch {
            getOpinionsUseCase(idUser).onEach { dataState ->
                mGetOpinionsState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la lectura de servicios de un usuario
     * Puede contener 4 valores (DataState)
     */
    private val mGetServicesState: MutableLiveData<DataState<ArrayList<UserServices>>> =
        MutableLiveData()
    val getServicesState: LiveData<DataState<ArrayList<UserServices>>> get() = mGetServicesState

    /**
     * Obtiene los servicios de un usuario de la base de datos
     * Utiliza getServicesUseCase para invocar la funcion del repositorio
     * @param idUser id del usuario
     */
    fun getServices(idUser: String) {
        viewModelScope.launch {
            getServicesUseCase(idUser).onEach { dataState ->
                mGetServicesState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la lectura de un usuario
     * Puede contener 4 valores (DataState)
     */
    private val mGetUserState: MutableLiveData<DataState<Users?>> = MutableLiveData()
    val getUserState: LiveData<DataState<Users?>> get() = mGetUserState

    /**
     * Lee un usuario de la base de datos
     * Utiliza getUserUseCase para invocar la funcion del repositorio
     * @param idUser id del usuario
     */
    fun getUser(idUser: String) {
        viewModelScope.launch {
            getUserUseCase(idUser).onEach { dataState ->
                mGetUserState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la lectura de todos los usuarios
     * Puede contener 4 valores (DataState)
     */
    private val mGetAllUserState: MutableLiveData<DataState<List<Users>>> = MutableLiveData()
    val getAllUserState: LiveData<DataState<List<Users>>> get() = mGetAllUserState

    /**
     * Lee todos los usuarios de la base de datos
     * Utiliza getAllUserUseCase para invocar la funcion del repositorio
     */
    fun getAllUser() {
        viewModelScope.launch {
            getAllUserUseCase().onEach { dataState ->
                mGetAllUserState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizacion de la descripcion de un usuario
     * Puede contener 4 valores (DataState)
     */
    private val mUpdateAboutMeState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateAboutMeState: LiveData<DataState<Boolean>> get() = mUpdateAboutMeState

    /**
     * Actualiza la descripcion de un usuario en la base de datos
     * Utiliza updateAboutMeUseCase para invocar la funcion del repositorio
     * @param aboutMe nueva descripcion
     */
    fun updateAboutMe(aboutMe: String) {
        viewModelScope.launch {
            updateAboutMeUseCase(aboutMe).onEach { dataState ->
                mUpdateAboutMeState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizcion de la imagen de perfil
     * Puede contener 4 valores (DataState)
     */
    private val mUpdateAvatarState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateAvatarState: LiveData<DataState<Boolean>> get() = mUpdateAvatarState

    /**
     * Actualiza la imagen de perfil en la base de datos
     * Utiliza updateAvatarUseCase para invocar la funcion del repositorio
     * @param imgLink link de la nueva imagen
     */
    fun updateAvatar(imgLink: String) {
        viewModelScope.launch {
            updateAvatarUseCase(imgLink).onEach { dataState ->
                mUpdateAvatarState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizacion de la informacion de contacto
     * Puede contener 4 valores (DataState)
     */
    private val mUpdateContactInfoState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateContactInfoState: LiveData<DataState<Boolean>> get() = mUpdateContactInfoState

    /**
     * Actualiza la informacion de contacto en la base de datos
     * Utiliza updateContactInfoUseCase para invocar la funcion del repositorio
     * @param contactInfo ContactInfoMap que contiene numero de telefono y correo electronico
     */
    fun updateContactInfo(contactInfo: ContactInfoMap) {
        viewModelScope.launch {
            updateContactInfoUseCase(contactInfo).onEach { dataState ->
                mUpdateContactInfoState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizacion del curriculum(pdf)
     * Puede contener 4 valores (DataState)
     */
    private val mUpdateCurriculumState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateCurriculumState: LiveData<DataState<Boolean>> get() = mUpdateCurriculumState

    /**
     * Actualiza el curriculum(pdf) en la base de datos
     * Utiliza updateCurriculumUseCase para invocar la funcion del repositorio
     * @param pdfLink Link del nuevo PDF
     */
    fun updateCurriculum(pdfLink: String) {
        viewModelScope.launch {
            updateCurriculumUseCase(pdfLink).onEach { dataState ->
                mUpdateCurriculumState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizacion de prohibicion del usuario
     * Puede contener 4 valores (DataState)
     */
    private val mUpdateBanState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateBanState: LiveData<DataState<Boolean>> get() = mUpdateBanState

    /**
     * Actualiza el estado de prohibicion del usuario en la base de datos
     * Utiliza updateBanUseCase para invocar la funcion del repositorio
     * @param userId id del usuario
     * @param ban nuevo estado true=banned /false = no banned
     */
    fun updateBan(userId: String, ban: Boolean) {
        viewModelScope.launch {
            updateBanUseCase(userId, ban).onEach { dataState ->
                mUpdateBanState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizacion del tema
     * Puede contener 4 valores (DataState)
     */
    private val mUpdateDarkThemeState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateDarkThemeState: LiveData<DataState<Boolean>> get() = mUpdateDarkThemeState

    /**
     * Actualiza el tema
     * Utiliza updateDarkThemeUseCase para invocar la funcion del repositorio
     * @param darkTheme
     */
    fun updateDarkTheme(darkTheme: Boolean) {
        viewModelScope.launch {
            updateDarkThemeUseCase(darkTheme).onEach { dataState ->
                mUpdateDarkThemeState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizacion del lenguage del usuario
     * Puede contener 4 valores (DataState)
     */
    private val mUpdateLanguageState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateLanguageState: LiveData<DataState<Boolean>> get() = mUpdateLanguageState

    /**
     * Actualiza el lenguage del usuario
     * Utiliza updateLanguageUseCase para invocar la funcion del repositorio
     * @param language
     */
    fun updateLanguage(language: Int) {
        viewModelScope.launch {
            updateLanguageUseCase(language).onEach { dataState ->
                mUpdateLanguageState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizacion de la ubicacion del usuario
     * Puede contener 4 valores (DataState)
     */
    private val mUpdateLocationState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateLocationState: LiveData<DataState<Boolean>> get() = mUpdateLocationState

    /**
     * Actualiza la ubicacion del usuario en la base de datos
     * Utiliza updateLocationUseCase para invocar la funcion del repositorio
     * @param newLocation nueva ubicacion
     */
    fun updateLocation(newLocation: String) {
        viewModelScope.launch {
            updateLocationUseCase(newLocation).onEach { dataState ->
                mUpdateLocationState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizacion del nombre personal del usuario
     * Puede contener 4 valores (DataState)
     */
    private val mUpdateNameState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateNameState: LiveData<DataState<Boolean>> get() = mUpdateNameState

    /**
     * Actualiza el nombre personal del usuario en la base de datos
     * Utiliza updateNameUseCase para invocar la funcion del repositorio
     * @param newName nuevo nombre del usuario
     */
    fun updateName(newName: String) {
        viewModelScope.launch {
            updateNameUseCase(newName).onEach { dataState ->
                mUpdateNameState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizacion del apellido del usuario
     * Puede contener 4 valores (DataState)
     */
    private val mUpdateSurnameState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateSurnameState: LiveData<DataState<Boolean>> get() = mUpdateSurnameState

    /**
     * Actualiza el apellido del usuario en la base de datos
     * Utiliza updateSurnameUseCase para invocar la funcion del repositorio
     * @param newSurname nuevo apellido
     */
    fun updateSurname(newSurname: String) {
        viewModelScope.launch {
            updateSurnameUseCase(newSurname).onEach { dataState ->
                mUpdateSurnameState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizacion de la contraseña del usuario
     * Puede contener 4 valores (DataState)
     */
    private val mUpdatePasswordState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updatePasswordState: LiveData<DataState<Boolean>> get() = mUpdatePasswordState

    /**
     * Actualiza la contraseña del usuario de la base de datos
     * Utiliza updatePasswordUseCase para invocar la funcion del repositorio
     * @param oldPassword contraseña anterior
     * @param newPassword nueva contraseña
     * @param email correo electronico
     */
    fun updatePassword(oldPassword: String, newPassword: String, email: String) {
        viewModelScope.launch {
            updatePasswordUseCase(oldPassword, newPassword, email).onEach { dataState ->
                mUpdatePasswordState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizacion de valoracion
     * Puede contener 4 valores (DataState)
     */
    private val mUpdateRatingState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateRatingState: LiveData<DataState<Boolean>> get() = mUpdateRatingState

    /**
     * Actualiza la valoracion del usuario de la base datos
     * Utiliza updateRatingUseCase para invocar la funcion del repositorio
     * @param updatedRating nuevo rating
     * @param idUser id del usuario
     */
    fun updateRating(updatedRating: Float, idUser: String) {
        viewModelScope.launch {
            updateRatingUseCase(updatedRating, idUser).onEach { dataState ->
                mUpdateRatingState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    /**
     * Estado de la actualizacion de horario
     * Puede contener 4 valores (DataState)
     */
    private val mUpdateScheduleState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val updateScheduleState: LiveData<DataState<Boolean>> get() = mUpdateScheduleState

    /**
     * Actualiza el horario del usuario en la base de datos
     * Utiliza updateScheduleUseCase para invocar la funcion del repositorio
     * @param schedule instancia de SheduleMap que contiene la hora de inicio y final
     */
    fun updateSchedule(schedule: ScheduleMap) {
        viewModelScope.launch {
            updateScheduleUseCase(schedule).onEach { dataState ->
                mUpdateScheduleState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

}