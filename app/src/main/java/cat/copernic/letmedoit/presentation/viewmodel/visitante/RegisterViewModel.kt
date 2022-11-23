package cat.copernic.letmedoit.presentation.viewmodel.visitante

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.usecases.RegisterUseCase
import cat.copernic.letmedoit.domain.usecases.SaveUserUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    //Estado del Registro
    private val mSignUpState: MutableLiveData<DataState<Users>> = MutableLiveData()
    val registerState : LiveData<DataState<Users>>
        get() = mSignUpState

    //Estado de guardar el usuario
    private val mSaveUserState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val saveUserState : LiveData<DataState<Boolean>>
        get() = mSaveUserState

    //Al registrar el usuario mandamos una corrutina que ejecuta el use case del registro.
    //El use case ejecuta el flow y este es escuchado con el oneach
    //en cada estado del flow guardamos en la variable signupstate el valor del estado.
    fun register(user : Users, password : String){
        viewModelScope.launch {
            registerUseCase(user, password)
                .onEach { dataState ->
                    mSignUpState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    fun saveUser(user: Users){
        viewModelScope.launch {
            saveUserUseCase(user)
                .onEach { dataState ->
                    mSaveUserState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
}