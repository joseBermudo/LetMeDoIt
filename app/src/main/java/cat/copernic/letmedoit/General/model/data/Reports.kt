package cat.copernic.letmedoit.General.model.data

import cat.copernic.letmedoit.Users.model.data.UsersMap
import com.google.firebase.firestore.Exclude
import java.util.*

data class Reports (
    @get:Exclude val id : String = UUID.randomUUID().toString(),
    val users : UsersMap,
    val description : String,
    val reason_id : String,
    val Archived : Boolean
        )