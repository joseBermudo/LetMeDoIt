package cat.copernic.letmedoit.data.remote

import android.provider.ContactsContract.Data
import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.di.FirebaseModule
import cat.copernic.letmedoit.domain.repositories.DealRepository
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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
                var dealStatus = deal.conclude

                when(dealStatus){
                    0 ->{
                        if (Constants.USER_LOGGED_IN_ID == deal.users.userOneId) dealStatus = 1
                        if (Constants.USER_LOGGED_IN_ID == deal.users.userTwoId) dealStatus = 2
                    }
                    1 ->{
                        if (Constants.USER_LOGGED_IN_ID == deal.users.userTwoId) dealStatus = 3
                    }
                    2 -> {
                        if (Constants.USER_LOGGED_IN_ID == deal.users.userOneId) dealStatus = 3
                    }
                }
                dealCollection.document(id).update("conclude", dealStatus)
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

            val dealColRef = dealCollection.document(id).get().await()
            dealColRef.toObject(Deal::class.java).let { deal ->
                if (deal != null) {
                    tempDeal = deal
                    tempDeal.id = dealColRef.id
                }
            }
            emit(DataState.Success(tempDeal))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun suscribeForUpdates(idDeal: String): Flow<DataState<Deal?>> = callbackFlow{
        send(DataState.Loading)
        try {
            var tempDeal : Deal? = null

            dealCollection.document(idDeal).addSnapshotListener{ deal, error ->
                if(error != null) close(error)
                if (deal != null) {
                    tempDeal = deal.toObject(Deal::class.java)
                    if(tempDeal == null) return@addSnapshotListener
                    tempDeal!!.id = idDeal
                    trySend(DataState.Success(tempDeal))
                    trySend(DataState.Finished)
                }
            }
        } catch (e: Exception) {
            send(DataState.Error(e))
            send(DataState.Finished)
        }
        finally {
            awaitClose()
        }
    }.flowOn(Dispatchers.IO)
}