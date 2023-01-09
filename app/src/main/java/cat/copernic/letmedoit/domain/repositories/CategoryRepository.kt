package cat.copernic.letmedoit.domain.repositories

import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Subcategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun insertCategory(category: Category): Flow<DataState<Boolean>>
    suspend fun insertSubcategory(categoryId:String,subcategory: Subcategory): Flow<DataState<Boolean>>
    suspend fun getCategories(): Flow<DataState<List<Category>>>
    suspend fun deleteCategory(id: String): Flow<DataState<Boolean>>
    suspend fun updateNombre(idCategory: String, newNombre: String): Flow<DataState<Boolean>>
    suspend fun updateDescription(idCategory: String, newDescription: String): Flow<DataState<Boolean>>
}