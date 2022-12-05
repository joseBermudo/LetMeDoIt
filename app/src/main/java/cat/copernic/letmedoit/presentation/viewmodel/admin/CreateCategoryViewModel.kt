package cat.copernic.letmedoit.presentation.viewmodel.admin

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.copernic.letmedoit.domain.usecases.admin.InsertCategoryUseCase
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.usecases.DeleteCategoryUseCase
import cat.copernic.letmedoit.domain.usecases.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCategoryViewModel @Inject constructor(
    val newCategoryUseCase: InsertCategoryUseCase,
    val getCategoriesUseCase: GetCategoriesUseCase,
    val deleteCategoryUseCase: DeleteCategoryUseCase
) : ViewModel() {

    private val mNewCategoryState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val newCategoryState: LiveData<DataState<Boolean>> get() = mNewCategoryState

    private val mDeleteCategoryState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val deleteCategoryState: LiveData<DataState<Boolean>> get() = mDeleteCategoryState

    private val mGetCategoriesState: MutableLiveData<DataState<List<Category>>> = MutableLiveData()
    val getCategoriesState: LiveData<DataState<List<Category>>> get() = mGetCategoriesState

    fun insertCategory(category: Category) {
        viewModelScope.launch {
            newCategoryUseCase(category).onEach { dataState ->
                mNewCategoryState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    fun deleteCategory(id: String) {
        viewModelScope.launch {
            deleteCategoryUseCase(id).onEach { dataState ->
                mDeleteCategoryState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().onEach { dataState ->
                mGetCategoriesState.value = dataState
            }.launchIn(viewModelScope)
        }
    }
}