package cat.copernic.letmedoit.domain.usecases.admin

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.data.model.Subcategory
import cat.copernic.letmedoit.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertSubcategoryUseCase @Inject constructor(
    val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(categoryId: String,subcategory: Subcategory): Flow<DataState<Boolean>> = categoryRepository.insertSubcategory(categoryId,subcategory)
}
