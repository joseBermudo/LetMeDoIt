package cat.copernic.letmedoit.data.remote

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.di.FirebaseModule
import cat.copernic.letmedoit.domain.repositories.DealRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class DealRepositoryImpl @Inject constructor(
    @FirebaseModule.DealCollection val dealCollection: CollectionReference
) : DealRepository {

    override suspend fun insertDeal(deal: Deal): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {

            var uploadStatus: Boolean = false
            deal.id.let {
                dealCollection.document(it).set(deal, SetOptions.merge())
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

    }.flowOn(Dispatchers.IO)

    override suspend fun denyDeal(id: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadStatus: Boolean = false
            id.let {
                dealCollection.document(it).delete()
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

    }.flowOn(Dispatchers.IO)

    override suspend fun acceptDeal(idDeal: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadStatus: Boolean = false
            idDeal.let {
                dealCollection.document(it).update("accepted", true)
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

    }.flowOn(Dispatchers.IO)

    override suspend fun concludeDeal(id: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadStatus: Boolean = false
            dealCollection.document(id).get().await().toObject(Deal::class.java)?.let { deal ->
                dealCollection.document(id).update("conclude", deal.conclude++)
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
    }.flowOn(Dispatchers.IO)

    override suspend fun getDeal(id: String): Flow<DataState<Deal>>  = flow{
        emit(DataState.Loading)
        try {
            var tempDeal = Deal()

            dealCollection.document(id).get().await().toObject(Deal::class.java).let { deal ->
                if (deal != null) {
                    tempDeal = deal
                }
            }
            emit(DataState.Success(tempDeal))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)
}