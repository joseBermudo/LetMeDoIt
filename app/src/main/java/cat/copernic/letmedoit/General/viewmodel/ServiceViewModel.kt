package cat.copernic.letmedoit.General.viewmodel

import android.app.Activity
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.General.model.data.Image
import cat.copernic.letmedoit.General.model.data.Service
import cat.copernic.letmedoit.General.usecases.GetAllServicesUseCase
import cat.copernic.letmedoit.General.usecases.GetServiceUseCase
import cat.copernic.letmedoit.Users.usecases.SaveImageUseCase
import cat.copernic.letmedoit.Users.usecases.SaveServiceUseCase
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Visitante.usecases.GetUserDataUseCase
import cat.copernic.letmedoit.Visitante.usecases.LogOutUseCase
import cat.copernic.letmedoit.Visitante.usecases.LoginUseCase
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
    private val saveImageUseCase: SaveImageUseCase
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

    fun saveImage(activity: Activity, fileUri : Uri, serviceId : String, fragment: Fragment,index : Int){
        viewModelScope.launch {
            saveImageUseCase(activity,fileUri, serviceId,fragment,index)
                .onEach { dataState ->
                    mSaveImageState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

}