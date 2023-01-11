package cat.copernic.letmedoit.presentation.viewmodel.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Chat
import cat.copernic.letmedoit.data.model.Message
import cat.copernic.letmedoit.domain.usecases.chats.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val createChatUseCase: CreateChatUseCase,
    private val obtenerChatUseCase: ObtenerChatUseCase,
    private val lastMessageUseCase: LastMessageUseCase,
    private val reciveMessageUseCase: ReciveMessageUseCase,
    private val sendMessageUseCase: SendMessageUseCase
): ViewModel(){
    private val mCreateChatState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val createChatState: LiveData<DataState<Boolean>>
        get() = mCreateChatState

    private val mObtenerChatState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val obtenerChatState: LiveData<DataState<Boolean>>
        get() = mObtenerChatState

    private val mLastMessageState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val lastMessageState: LiveData<DataState<Boolean>>
        get() = mLastMessageState

    private val mReciveMessageState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val reciveMessageState: LiveData<DataState<Boolean>>
        get() = mReciveMessageState

    private val mSendMessageState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val sendMessageState: LiveData<DataState<Boolean>>
        get() = mSendMessageState

    fun createChat(chat: Chat){
        viewModelScope.launch(){
            createChatUseCase(chat)
                .onEach { dataState ->
                    mCreateChatState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
    fun obtenerChat(chat: Chat){
        viewModelScope.launch(){
            obtenerChatUseCase(chat)
                .onEach { dataState ->
                    mObtenerChatState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
    fun lastMessage(lastMessage: String){
        viewModelScope.launch(){
            lastMessageUseCase(lastMessage)
                .onEach { dataState ->
                    mLastMessageState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
    fun sendMessage(messageUserOne:  ArrayList<Message>){
        viewModelScope.launch(){
            sendMessageUseCase(messageUserOne)
                .onEach { dataState ->
                    mSendMessageState.value = dataState
                }.launchIn(viewModelScope)
        }
    }
    fun reciveMessage(messageUserTwo:  ArrayList<Message>){
        viewModelScope.launch(){
            reciveMessageUseCase(messageUserTwo)
                .onEach { dataState ->
                    mReciveMessageState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

}