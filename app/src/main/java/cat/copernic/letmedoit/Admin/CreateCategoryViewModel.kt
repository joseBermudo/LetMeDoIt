package cat.copernic.letmedoit.Admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.Admin.useCases.NewCategoryUseCase
import cat.copernic.letmedoit.General.model.data.Category
import cat.copernic.letmedoit.Utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCategoryViewModel @Inject constructor(
    private val newCategoryUseCase: NewCategoryUseCase
) : ViewModel() {

    private val mNewCategoryState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val newCategoryState: LiveData<DataState<Boolean>> get() = mNewCategoryState


    fun newCategory(category: Category) {
        viewModelScope.launch {
            newCategoryUseCase(category).onEach { dataState ->
                mNewCategoryState.value = dataState
            }.launchIn(viewModelScope)
        }
    }
}