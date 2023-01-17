package cat.copernic.letmedoit.presentation.viewmodel.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.domain.usecases.deals.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.m
import javax.inject.Inject


/**
 * Una clase ViewModel que conecta la vista con el repositorio de Deals.
 * Proporciona objetos LiveData para mostrar el estado actual de las operaciones.
 * Todas las funciones de esta clase actuan sobre la base de datos.
 * Utiliza los UseCase para comunicarse con el repositorio
 *@param acceptDealUseCase objeto de AcceptDealUseCase
 *@param concludeDealUseCase objeto de ConcludeDealUseCase
 *@param denyDealUseCase objeto de DenyDealUseCase
 *@param insertDealUseCase objeto de InsertDealUseCase
 *@param getDealUseCase objeto de GetDealUseCase
 *@param getDealsUseCase objeto de GetDealsUseCase
 *@param suscribeForUpdatesUseCase objeto de SuscribeForUpdatesUseCase
 */
@HiltViewModel
class DealViewModel @Inject constructor(
    private val acceptDealUseCase: AcceptDealUseCase,
    private val concludeDealUseCase: ConcludeDealUseCase,
    private val denyDealUseCase: DenyDealUseCase,
    private val insertDealUseCase: InsertDealUseCase,
    private val getDealUseCase: GetDealUseCase,
    private val getDealsUseCase: GetDealsUseCase,
    private val suscribeForUpdatesUseCase: SuscribeForUpdatesUseCase
) : ViewModel() {

    /**
     * Estado de la aceptacion de un trato
     * Puede contener 4 valores (DataState)
     */
    private val mAcceptState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val acceptState: LiveData<DataState<Boolean>>
        get() = mAcceptState

    /**
     * Estado de la colusion de un trato
     * Puede contener 4 valores (DataState)
     */
    private val mConcludeState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val concludeState: LiveData<DataState<Boolean>>
        get() = mConcludeState

    /**
     * Estado del rechazo de un trato
     * Puede contener 4 valores (DataState)
     */
    private val mDenyState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val denyState: LiveData<DataState<Boolean>>
        get() = mDenyState

    /**
     * Estado de crear un trato en bd
     * Puede contener 4 valores (DataState)
     */
    private val mInsertState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val insertState: LiveData<DataState<Boolean>>
        get() = mInsertState

    /**
     * Estado de leer un trato de la bd
     * Puede contener 4 valores (DataState)
     */
    private val mGetDealState: MutableLiveData<DataState<Deal>> = MutableLiveData()
    val getDealState: LiveData<DataState<Deal>>
        get() = mGetDealState

    /**
     * Estado de lectura de todos los trato el usuario
     * Puede contener 4 valores (DataState)
     */
    private val mGetDealsState: MutableLiveData<DataState<List<Deal>>> = MutableLiveData()
    val getDealsState: LiveData<DataState<List<Deal>>>
        get() = mGetDealsState

    /**
     * Estado del trato
     * Puede contener 4 valores (DataState)
     */
    private val mSuscribeForUpdatesState: MutableLiveData<DataState<Deal?>> = MutableLiveData()
    val suscribeForUpdatesState: LiveData<DataState<Deal?>>
        get() = mSuscribeForUpdatesState

    /**
     * Funcion que actualiza el estado de aceptacion de un trato a aceptado
     * Utiliza acceptDealUseCase para invocar la funcion del repositorio
     * @param id id del trato
     */
    fun accept(id: String) {
        viewModelScope.launch() {
            acceptDealUseCase(id)
                .onEach { dataState ->
                    mAcceptState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    /**
     * Actualiza el estado de finalizacion del trato
     * Utiliza concludeDealUseCase para invocar la funcion del repositorio
     * @param id id del trato
     */
    fun conclude(id: String) {
        viewModelScope.launch() {
            concludeDealUseCase(id)
                .onEach { dataState ->
                    mConcludeState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    /**
     * Actualiza el estado de aceptacion del trato a rechazado
     * Utiliza denyDealUseCase para invocar la funcion del repositorio
     * @param id id del trato
     */
    fun deny(id: String) {
        viewModelScope.launch() {
            denyDealUseCase(id)
                .onEach { dataState ->
                    mDenyState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    /**
     * Sube un trato a la base de datos
     * Utiliza insertDealUseCase para invocar la funcion del repositorio
     * @param deal instancia de clase Deal
     */
    fun insert(deal: Deal) {
        viewModelScope.launch() {
            insertDealUseCase(deal)
                .onEach { dataState ->
                    mInsertState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    /**
     * Lee un trato de la base de datos
     * Utiliza getDealUseCase para invocar la funcion del repositorio
     * @param id id del trato
     */
    fun getDeal(id: String) {
        viewModelScope.launch() {
            getDealUseCase(id)
                .onEach { dataState ->
                    mGetDealState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    /**
     * Lee todos los tratos de un usuario de la base de datos
     * Utiliza getDealsUseCase para invocar la funcion del repositorio
     */
    fun getDeals() {
        viewModelScope.launch() {
            getDealsUseCase()
                .onEach { dataState ->
                    mGetDealsState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    /**
     * Actualiza el estado a tiempo real del trato
     * Utiliza suscribeForUpdatesUseCase para invocar la funcion del repositorio
     * @param id id del trato
     */
    fun suscribeForUpdates(id: String) {
        viewModelScope.launch() {
            suscribeForUpdatesUseCase(id)
                .onEach { dataState ->
                    mSuscribeForUpdatesState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
}