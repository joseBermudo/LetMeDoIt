package cat.copernic.letmedoit.Users.model.data

import cat.copernic.letmedoit.General.model.data.Users
import com.google.firebase.firestore.Exclude
import org.checkerframework.checker.units.qual.A

data class Chat (
    @get:Exclude val id :   String,
    val users :             UsersMap,
    val messagesUserOne :   ArrayList<Message>,
    val messageUserTwo :    ArrayList<Message>,
    val lastMessage :       String

)