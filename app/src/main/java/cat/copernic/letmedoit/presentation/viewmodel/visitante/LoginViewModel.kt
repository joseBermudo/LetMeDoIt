package cat.copernic.letmedoit.presentation.viewmodel.visitante

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.usecases.login.GetUserDataUseCase
import cat.copernic.letmedoit.domain.usecases.login.LogOutUseCase
import cat.copernic.letmedoit.domain.usecases.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * viewModel que conecta la vista con el repositorio de Users.
 * Se encarga del login
 * @param loginUseCase useCase que invoca la funcion para iniciar sesion
 * @param getUserDataUseCase useCase que invoca la funcion para leer un usuario
 * @param logOutUseCase useCase que cierra sesion
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val logOutUseCase: LogOutUseCase
): ViewModel() {
    /**
     * Estado del login
     * Puede contener 4 valores (DataState)
     */
    private val mLoginState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val loginState: LiveData<DataState<Boolean>>
        get() = mLoginState

    /**
     * Estado de la lectura de Users
     * Puede contener 4 valores (DataState)
     */
    private val mUserDataState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val userDataState: LiveData<DataState<Boolean>>
        get() = mUserDataState

    /**
     * Estado del cierre de sesion
     * Puede contener 4 valores (DataState)
     */
    private val mLogOutState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val logOutState: LiveData<DataState<Boolean>>
        get() = mLogOutState

    /**
     * Funcion que inicia sesion
     * Utiliza un corrutina para ejecutar el useCase
     * Obtenemos el estado del proceso desde el flow
     * @param email correo electronico del usuario
     * @param password contraseña del usuario
     */
    fun login(email: String, password: String){
        viewModelScope.launch() {
            loginUseCase(email, password)
                .onEach { dataState ->
                    mLoginState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
    /**
     * Funcion que lee un usuario
     * Utiliza un corrutina para ejecutar el useCase
     * Obtenemos el estado del proceso desde el flow
     */
    fun getUserData(){
        viewModelScope.launch {
            getUserDataUseCase()
                .onEach { dataState ->
                    mUserDataState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
    /**
     * Funcion que cierra sesion
     * Utiliza un corrutina para ejecutar el useCase
     * Obtenemos el estado del proceso desde el flow
     */
    fun logOut(){
        viewModelScope.launch {
            logOutUseCase()
                .onEach { dataState ->
                    mLogOutState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

}