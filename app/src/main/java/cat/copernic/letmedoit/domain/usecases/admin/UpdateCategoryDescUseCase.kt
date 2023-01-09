package cat.copernic.letmedoit.domain.usecases.admin

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateCategoryDescUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(idCategory: String, newDesc: String): Flow<DataState<Boolean>> =
        categoryRepository.updateDescription(idCategory, newDesc)
}