package cat.copernic.letmedoit.domain.usecases.chats

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Message
import cat.copernic.letmedoit.domain.repositories.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LastMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository
){
    suspend operator fun invoke(lastMessage: String): Flow<DataState<Boolean>> = chatRepository.lastMessage(lastMessage)
}