package cat.copernic.letmedoit.data.model

import androidx.annotation.Keep
import cat.copernic.letmedoit.Utils.datahepers.UsersMap
import com.google.firebase.firestore.Exclude

@Keep
data class Chat (
    @get:Exclude val id :   String,
    val users : UsersMap,
    val messagesUserOne :   ArrayList<Message>,
    val messageUserTwo :    ArrayList<Message>,
    val lastMessage :       String

)