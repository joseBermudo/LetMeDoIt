package cat.copernic.letmedoit.domain.repositories

import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.Utils.DataState
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun insertCategory(category: Category): Flow<DataState<Boolean>>
}