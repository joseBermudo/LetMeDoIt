package cat.copernic.letmedoit.presentation.viewmodel.general

import android.app.Activity
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.datahepers.CategoryMap
import cat.copernic.letmedoit.domain.usecases.service.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val getAllServicesUseCase: GetAllServicesUseCase,
    private val getServiceUseCase: GetServiceUseCase,
    private val saveServiceUseCase: SaveServiceUseCase,
    private val saveImageUseCase: SaveImageUseCase,
    private val updateTitleUseCase: UpdateTitleUseCase,
    private val updateEditedTimeUseCase: UpdateEditedTimeUseCase,
    private val updateNLikesUseCase: UpdateNLikesUseCase,
    private val updateDescriptionUseCase: UpdateDescriptionUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase,
    private val removeImageUseCase: RemoveImageUseCase,
    private val editServiceImageUseCase: EditServiceImageUseCase,
    private val removeServiceUseCase: RemoveServiceUseCase
): ViewModel() {

    private val mGetServiceState: MutableLiveData<DataState<Service>> = MutableLiveData()

    val getServiceState: LiveData<DataState<Service>>
        get() = mGetServiceState

    private val mGetServicesState: MutableLiveData<DataState<List<Service>>> = MutableLiveData()

    val getServicesState: LiveData<DataState<List<Service>>>
        get() = mGetServicesState

    private val mSaveServiceState: MutableLiveData<DataState<Service>> = MutableLiveData()

    val saveServiceState: LiveData<DataState<Service>>
        get() = mSaveServiceState

    private val mSaveImageState: MutableLiveData<DataState<String>> = MutableLiveData()

    val saveImageState: LiveData<DataState<String>>
        get() = mSaveImageState

    fun getAllServices(){
        viewModelScope.launch {
            getAllServicesUseCase()
                .onEach { dataState ->
                    mGetServicesState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
    fun getService(uid : String){
        viewModelScope.launch {
            getServiceUseCase(uid)
                .onEach { dataState ->
                    mGetServiceState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
    fun saveService(service: Service){
        viewModelScope.launch {
            saveServiceUseCase(service)
                .onEach { dataState ->
                    mSaveServiceState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    fun saveImage(fileUri : Uri, serviceId : String,index : Int){
        viewModelScope.launch {
            saveImageUseCase(fileUri, serviceId,index)
                .onEach { dataState ->
                    mSaveImageState.value = dataState
                }.launchIn(viewModelScope)
        }
    }


    private val mUpdateTitleState: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    val updateTitleState: LiveData<DataState<Boolean>>
        get() = mUpdateTitleState

    fun updateTitle(idService : String, newTitle : String){
        viewModelScope.launch {
            updateTitleUseCase(idService, newTitle)
                .onEach { dataState ->
                    mUpdateTitleState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    private val mUpdateDescriptionState: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    val updateDescriptionState: LiveData<DataState<Boolean>>
        get() = mUpdateDescriptionState

    fun updateDescription(idService : String, newDescription : String){
        viewModelScope.launch {
            updateDescriptionUseCase(idService, newDescription)
                .onEach { dataState ->
                    mUpdateDescriptionState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
    private val mUpdateServiceImageState: MutableLiveData<DataState<String>> = MutableLiveData()

    val updateServiceImageState: LiveData<DataState<String>>
        get() = mUpdateServiceImageState

    fun editServiceImage(idService : String,newFileURI : Uri, index: Int){
        viewModelScope.launch {
            editServiceImageUseCase(idService,newFileURI,index)
                .onEach { dataState ->
                    mUpdateServiceImageState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    private val mUpdateCategoryState: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    val updateCategoryState: LiveData<DataState<Boolean>>
        get() = mUpdateCategoryState

    fun updateCategory(idService : String, newCategoryMap : CategoryMap){
        viewModelScope.launch {
            updateCategoryUseCase(idService, newCategoryMap)
                .onEach { dataState ->
                    mUpdateCategoryState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    private val mUpdateNLikesState: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    val updateNLikesState: LiveData<DataState<Boolean>>
        get() = mUpdateNLikesState

    fun updateNLikes(idService : String, newNum : Int){
        viewModelScope.launch {
            updateNLikesUseCase(idService, newNum)
                .onEach { dataState ->
                    mUpdateNLikesState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    private val mUpdateEditedTimeState: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    val updateEditedTimeState: LiveData<DataState<Boolean>>
        get() = mUpdateEditedTimeState

    fun updateEditedTime(idService : String, newEditedTime : String){
        viewModelScope.launch {
            updateEditedTimeUseCase(idService, newEditedTime)
                .onEach { dataState ->
                    mUpdateEditedTimeState.value = dataState
                }.launchIn(viewModelScope)
        }
    }



    private val mRemoveImageUseCaseState: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    val removeImageUseCaseState: LiveData<DataState<Boolean>>
        get() = mRemoveImageUseCaseState

    fun removeImage(idService : String,index : Int,imgLink : String){
        viewModelScope.launch {
            removeImageUseCase(idService,index,imgLink)
                .onEach { dataState ->
                    mRemoveImageUseCaseState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    private val mRemoveServiceState: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    val removeServiceState: LiveData<DataState<Boolean>>
        get() = mRemoveServiceState

    fun removeService(idService : String){
        viewModelScope.launch {
            removeServiceUseCase(idService)
                .onEach { dataState ->
                    mRemoveServiceState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
}