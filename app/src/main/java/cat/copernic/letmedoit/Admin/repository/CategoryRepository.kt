package cat.copernic.letmedoit.Admin.repository

import cat.copernic.letmedoit.General.model.data.Category
import cat.copernic.letmedoit.Utils.DataState
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun insertCategory(category: Category): Flow<DataState<Boolean>>
}