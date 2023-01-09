package cat.copernic.letmedoit.domain.usecases.chats

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Chat
import cat.copernic.letmedoit.domain.repositories.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateChatUseCase @Inject constructor(
    private val chatRepository: ChatRepository
){
    suspend operator fun invoke(chat: Chat): Flow<DataState<Boolean>> = chatRepository.createChat(chat)
}