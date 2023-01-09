package cat.copernic.letmedoit.data.remote

import android.util.Log
import cat.copernic.letmedoit.Utils.CategoryConstants
import cat.copernic.letmedoit.domain.repositories.CategoryRepository
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Subcategory
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


    override suspend fun updateNombre(
        idCategory: String,
        newNombre: String
    ): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var updateStatus: Boolean = false
            idCategory.let {
                categoryCollection.document(it).update(CategoryConstants.NOMBRE, newNombre)
                    .addOnFailureListener { updateStatus = false }
                    .addOnSuccessListener { updateStatus = true }
                    .await()
            }
            emit(DataState.Success(updateStatus))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getSubCat(category: Category) {
        val ref =
            categoryCollection.document(category.id).collection(CategoryConstants.SUBCAT).get()
                .await()
        val subcategories = ref.toObjects(Subcategory::class.java)
        category.subcategories.removeAll(category.subcategories.toSet())
        category.subcategories.addAll(subcategories)
    }

    override suspend fun updateDescription(
        idCategory: String,
        newDescription: String
    ): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var updateStatus: Boolean = false
            idCategory.let {
                categoryCollection.document(it).update(CategoryConstants.DESC, newDescription)
                    .addOnFailureListener { updateStatus = false }
                    .addOnSuccessListener { updateStatus = true }
                    .await()
            }
            emit(DataState.Success(updateStatus))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun insertCategory(category: Category): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadStatus: Boolean = false
            category.id.let {
                categoryCollection.document(it).set(category, SetOptions.merge())
                    .addOnSuccessListener { uploadStatus = true }
                    .addOnFailureListener { uploadStatus = false }
                    .await()
                categoryCollection.document(it).collection(CategoryConstants.SUBCAT)
            }

            emit(DataState.Success(uploadStatus))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(IO)

    override suspend fun insertSubcategory(
        categoryId: String,
        subcategory: Subcategory
    ): Flow<DataState<Boolean>> = flow<DataState<Boolean>> {
        var dataStatus: Boolean = false
        emit(DataState.Loading)
        try {

            categoryCollection.document(categoryId).collection(CategoryConstants.SUBCAT)
                .document(subcategory.id).set(subcategory, SetOptions.merge())
                .addOnSuccessListener { dataStatus = true }
                .addOnFailureListener { dataStatus = false }
            emit(DataState.Success(dataStatus))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getCategories(): Flow<DataState<List<Category>>> = flow {
        emit(DataState.Loading)
        try {
            val categories = categoryCollection.get().await().toObjects(Category::class.java)
            categories.forEach {
                getSubCat(it)
            }
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

    override suspend fun deleteSubcategory(
        categoryId: String,
        subcategoryId: String
    ): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            Log.d("sub", subcategoryId)
            Log.d("sub", categoryId)
            var deleteStatus: Boolean = false
            categoryCollection.document(categoryId).collection(CategoryConstants.SUBCAT)
                .document(subcategoryId).delete()
                .addOnSuccessListener { deleteStatus = true }
                .addOnFailureListener { deleteStatus = false }
                .await()

            emit(DataState.Success(deleteStatus))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }

}