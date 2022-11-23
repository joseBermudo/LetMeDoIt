package cat.copernic.letmedoit.presentation.viewmodel.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.domain.usecases.InsertCategoryUseCase
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.Utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCategoryViewModel @Inject constructor(
     val newCategoryUseCase: InsertCategoryUseCase
) : ViewModel() {

    private val mNewCategoryState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val newCategoryState: LiveData<DataState<Boolean>> get() = mNewCategoryState


    fun insertCategory(category: Category) {
        viewModelScope.launch {
            newCategoryUseCase(category).onEach { dataState ->
                mNewCategoryState.value = dataState
            }.launchIn(viewModelScope)
        }
    }
}