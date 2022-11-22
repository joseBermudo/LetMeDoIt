package cat.copernic.letmedoit.domain.usecases

import cat.copernic.letmedoit.domain.repositories.CategoryRepository
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.Utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertCategoryUseCase @Inject constructor(
     val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(category: Category): Flow<DataState<Boolean>> = categoryRepository.insertCategory(category)
}