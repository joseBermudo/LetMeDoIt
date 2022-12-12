package cat.copernic.letmedoit.data.remote

import cat.copernic.letmedoit.domain.repositories.CategoryRepository
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.di.FirebaseModule
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    @FirebaseModule.CategoryCollection val categoryCollection: CollectionReference
) : CategoryRepository {

    override suspend fun insertCategory(category: Category): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadStatus: Boolean = false
            category.id.let {
                categoryCollection.document(it).set(category, SetOptions.merge())
                    .addOnSuccessListener { uploadStatus = true }
                    .addOnFailureListener { uploadStatus = false }
                    .await()
            }
            emit(DataState.Success(uploadStatus))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(IO)

    override suspend fun getCategories(): Flow<DataState<List<Category>>> = flow {
        emit(DataState.Loading)
        try {
            val categories = categoryCollection.get().await().toObjects(Category::class.java)
            emit(DataState.Success(categories))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(IO)

    override suspend fun deleteCategory(id: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var deleteStatus: Boolean = false
            categoryCollection.document(id).delete()
                .addOnSuccessListener { deleteStatus = true }
                .addOnFailureListener { deleteStatus = false }
                .await()

            emit(DataState.Success(deleteStatus))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(IO)

    override suspend fun getCategories(): Flow<DataState<List<Category>>> = flow {
        emit(DataState.Loading)
        try {
            val categories = categoryCollection.get().await().toObjects(Category::class.java)
            emit(DataState.Success(categories))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteCategory(id: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var deleteStatus: Boolean = false
            categoryCollection.document(id).delete()
                .addOnSuccessListener { deleteStatus = true }
                .addOnFailureListener { deleteStatus = false }
                .await()

            emit(DataState.Success(deleteStatus))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

}