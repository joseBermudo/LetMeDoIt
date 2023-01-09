package cat.copernic.letmedoit.domain.usecases.admin

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCategoryUseCase @Inject constructor(
    val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(id: String): Flow<DataState<Boolean>> =
        categoryRepository.deleteCategory(id)
}