package cat.copernic.letmedoit.presentation.viewmodel.visitante

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.usecases.login.RegisterUseCase
import cat.copernic.letmedoit.domain.usecases.login.SaveUserUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * viewModel que conecta la vista con el repositorio de usuarios
 * Registra y guarda usuarios en la bd
 * Proporciona objetos LiveData para mostrar el estado actual de las operaciones
 * @param registerUseCase useCase que invoca la funcion para registrar un usuario en la bd
 * @param saveUserUseCase useCase que invoca la funcio para guardar un usuario en la coleccion Users de labd
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    /**
     * Estado del registro
     * Puede contener 4 valores (DataState)
     */
    private val mSignUpState: MutableLiveData<DataState<Users>> = MutableLiveData()
    val registerState : LiveData<DataState<Users>>
        get() = mSignUpState

    /**
     * Estado del la insersion de un usuario en la coleccion Users de bd
     * Puede contener 4 valores (DataState)
     */
    private val mSaveUserState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val saveUserState : LiveData<DataState<Boolean>>
        get() = mSaveUserState

    /**
     * Funcion que registra un usuario
     * Al registrar el usuario mandamos una corrutina que ejecuta el use case del registro.
     * El use case ejecuta el flow y este es escuchado con el oneach.
     * En cada estado del flow guardamos en la variable signupstate el valor del estado.
     * @param user usurio que se registra
     * @param password contraseña que se registra
     */

    fun register(user : Users, password : String){
        viewModelScope.launch {
            registerUseCase(user, password)
                .onEach { dataState ->
                    mSignUpState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    /**
     * Funcio que guarda un usuario en la bd
     * @param user Usuario que guardamos
     */
    fun saveUser(user: Users){
        viewModelScope.launch {
            saveUserUseCase(user)
                .onEach { dataState ->
                    mSaveUserState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
}