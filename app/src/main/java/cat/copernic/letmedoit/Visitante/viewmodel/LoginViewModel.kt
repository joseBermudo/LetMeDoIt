package cat.copernic.letmedoit.Visitante.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Visitante.usecases.GetUserDataUseCase
import cat.copernic.letmedoit.Visitante.usecases.LogOutUseCase
import cat.copernic.letmedoit.Visitante.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val logOutUseCase: LogOutUseCase
): ViewModel() {

    private val mLoginState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val loginState: LiveData<DataState<Boolean>>
        get() = mLoginState

    private val mUserDataState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val userDataState: LiveData<DataState<Boolean>>
        get() = mUserDataState

    private val mLogOutState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val logOutState: LiveData<DataState<Boolean>>
        get() = mLogOutState

    fun login(email: String, password: String){
        viewModelScope.launch() {
            loginUseCase(email, password)
                .onEach { dataState ->
                    mLoginState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    fun getUserData(){
        viewModelScope.launch {
            getUserDataUseCase()
                .onEach { dataState ->
                    mUserDataState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    fun logOut(){
        viewModelScope.launch {
            logOutUseCase()
                .onEach { dataState ->
                    mLogOutState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

}