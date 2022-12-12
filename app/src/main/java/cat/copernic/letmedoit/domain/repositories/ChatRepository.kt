package cat.copernic.letmedoit.domain.repositories

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Chat
import cat.copernic.letmedoit.data.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun createChat(chat: Chat): Flow<DataState<Boolean>>
    suspend fun obtenerChat(chat: Chat): Flow<DataState<Boolean>>
    suspend fun sendMessage(messageUserOne:  ArrayList<Message>): Flow<DataState<Boolean>>
    suspend fun reciveMessage(messageUserTwo:  ArrayList<Message>): Flow<DataState<Boolean>>
    suspend fun lastMessage(lastMessage: String): Flow<DataState<Boolean>>
}