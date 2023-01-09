package cat.copernic.letmedoit.data.remote

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Chat
import cat.copernic.letmedoit.data.model.Message
import cat.copernic.letmedoit.di.FirebaseModule
import cat.copernic.letmedoit.domain.repositories.ChatRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    @FirebaseModule.ChatCollection val chatCollection: CollectionReference
) : ChatRepository {

    override suspend fun createChat(chat: Chat): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadStatus: Boolean = false
            chat.id.let {
                chatCollection.document(it).get()
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

    override suspend fun obtenerChat(chat: Chat): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadStatus: Boolean = false
            chat.id.let {
                chatCollection.document(it).set(chat, SetOptions.merge())
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

    override suspend fun sendMessage(messageUserOne: ArrayList<Message>): Flow<DataState<Boolean>> =
        flow {
            emit(DataState.Loading)
            try {
                var uploadStatus: Boolean = false
                messageUserOne.let {
                    chatCollection.document(it.toString()).update("messageUserOne", true)
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

    override suspend fun reciveMessage(messageUserTwo: ArrayList<Message>): Flow<DataState<Boolean>>  = flow{
        emit(DataState.Loading)
        try {
            var uploadStatus: Boolean = false
            messageUserTwo.let {
                chatCollection.document(it.toString()).update("messageUserTwo", true)
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

    override suspend fun lastMessage(lastMessage: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try{
            var uploadStatus: Boolean = false
            lastMessage.let {
                chatCollection.document(it).update("lastMessage", true)
                    .addOnSuccessListener { uploadStatus = true }
                    .addOnFailureListener { uploadStatus = false }
                    .await()
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }.flowOn(Dispatchers.IO)

}