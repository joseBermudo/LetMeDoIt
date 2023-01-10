package cat.copernic.letmedoit.presentation.viewmodel.general

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.data.model.Chat
import cat.copernic.letmedoit.data.model.Report
import cat.copernic.letmedoit.domain.usecases.admin.GetReportsUseCase
import cat.copernic.letmedoit.domain.usecases.reports.CreateReportUseCase
import cat.copernic.letmedoit.domain.usecases.reports.DeleteReportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val createReportUseCase: CreateReportUseCase,
    private val deleteReportUseCase: DeleteReportUseCase,
    private val getReportsUseCase: GetReportsUseCase
): ViewModel(){

    private val mGetReportState: MutableLiveData<DataState<List<Report>>> = MutableLiveData()
    val getReportState: LiveData<DataState<List<Report>>>
        get() = mGetReportState

    private val mCreateReportState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val createReportState: LiveData<DataState<Boolean>>
        get() = mCreateReportState
    private val mDeleteReportState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val deleteReportState: LiveData<DataState<Boolean>>
        get() = mDeleteReportState

    fun getReports(){
        viewModelScope.launch {
            getReportsUseCase().onEach { dataState ->
                mGetReportState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    fun createReport(report: Report){
        viewModelScope.launch(){
            createReportUseCase(report)
                .onEach { dataState ->
                    mCreateReportState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
    fun deleteReport(idReport: String){
        viewModelScope.launch(){
            deleteReportUseCase(idReport)
                .onEach { dataState ->
                    mDeleteReportState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
}