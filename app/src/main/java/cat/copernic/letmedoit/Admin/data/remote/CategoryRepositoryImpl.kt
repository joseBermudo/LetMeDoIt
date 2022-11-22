package cat.copernic.letmedoit.Admin.data.remote

import cat.copernic.letmedoit.Admin.repository.CategoryRepository
import cat.copernic.letmedoit.General.model.data.Category
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.di.FirebaseModule
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
            categoryCollection.document(category.id).set(category, SetOptions.merge())
                .addOnSuccessListener {
                    uploadStatus = true
                }.addOnFailureListener {
                    uploadStatus = false
                }.await()
            emit(DataState.Success(uploadStatus))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }

}