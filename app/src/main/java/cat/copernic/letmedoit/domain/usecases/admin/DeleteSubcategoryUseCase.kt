package cat.copernic.letmedoit.domain.usecases.admin

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteSubcategoryUseCase @Inject constructor(
    val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(
        categoryId: String,
        subcategoryId: String
    ): Flow<DataState<Boolean>> =
        categoryRepository.deleteSubcategory(categoryId, subcategoryId)
}