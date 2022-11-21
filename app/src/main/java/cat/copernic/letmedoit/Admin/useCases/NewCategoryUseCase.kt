package cat.copernic.letmedoit.Admin.useCases

import cat.copernic.letmedoit.Admin.repository.CategoryRepository
import cat.copernic.letmedoit.General.model.data.Category
import cat.copernic.letmedoit.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(category: Category): Flow<DataState<Boolean>> = flow {
        categoryRepository.insertCategory(category)
    }
}