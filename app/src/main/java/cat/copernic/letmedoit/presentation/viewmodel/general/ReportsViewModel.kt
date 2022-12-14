package cat.copernic.letmedoit.presentation.viewmodel.general

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Chat
import cat.copernic.letmedoit.data.model.Report
import cat.copernic.letmedoit.domain.usecases.reports.CreateReportUseCase
import cat.copernic.letmedoit.domain.usecases.reports.DeleteReportUseCase
import cat.copernic.letmedoit.presentation.view.users.fragments.chat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val createReportUseCase: CreateReportUseCase,
    private val deleteReportUseCase: DeleteReportUseCase,
): ViewModel(){
    private val mCreateReportState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val createReportState: LiveData<DataState<Boolean>>
        get() = mCreateReportState
    private val mDeleteReportState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val deleteReportState: LiveData<DataState<Boolean>>
        get() = mDeleteReportState

    fun createReport(report: Report){
        viewModelScope.launch(){
            createReportUseCase(report)
                .onEach { dataState ->
                    mCreateReportState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
    fun deleteReport(report: Report){
        viewModelScope.launch(){
            deleteReportUseCase(report)
                .onEach { dataState ->
                    mDeleteReportState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
}