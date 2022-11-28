package cat.copernic.letmedoit.presentation.viewmodel.general.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.domain.usecases.deals.AcceptDealUseCase
import cat.copernic.letmedoit.domain.usecases.deals.ConcludeDealUseCase
import cat.copernic.letmedoit.domain.usecases.deals.DenyDealUseCase
import cat.copernic.letmedoit.domain.usecases.deals.InsertDealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealViewModel @Inject constructor(
    private val acceptDealUseCase: AcceptDealUseCase,
    private val concludeDealUseCase: ConcludeDealUseCase,
    private val denyDealUseCase: DenyDealUseCase,
    private val insertDealUseCase: InsertDealUseCase
): ViewModel(){

    private val mAcceptState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val acceptState: LiveData<DataState<Boolean>>
        get() = mAcceptState

    private val mConcludeState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val concludeState: LiveData<DataState<Boolean>>
        get() = mConcludeState

    private val mDenyState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val denyState: LiveData<DataState<Boolean>>
        get() = mDenyState

    private val mInsertState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val insertState: LiveData<DataState<Boolean>>
        get() = mInsertState

    fun accept(id: String){
        viewModelScope.launch() {
            acceptDealUseCase(id)
                .onEach { dataState ->
                    mAcceptState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    fun conclude(id: String){
        viewModelScope.launch() {
            concludeDealUseCase(id)
                .onEach { dataState ->
                    mConcludeState.value = dataState
                }
        }
    }

    fun deny(id: String){
        viewModelScope.launch() {
            denyDealUseCase(id)
                .onEach { dataState ->
                    mDenyState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    fun insert(deal: Deal){
        viewModelScope.launch() {
            insertDealUseCase(deal)
                .onEach { dataState ->
                    mInsertState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
}