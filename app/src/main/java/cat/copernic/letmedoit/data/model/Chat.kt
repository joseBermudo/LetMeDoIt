package cat.copernic.letmedoit.data.model

import cat.copernic.letmedoit.Utils.datahepers.UsersMap
import com.google.firebase.firestore.Exclude

data class Chat (
    @get:Exclude val id :   String,
    val users : UsersMap,
    val messagesUserOne :   ArrayList<Message>,
    val messageUserTwo :    ArrayList<Message>,
    val lastMessage :       String

)