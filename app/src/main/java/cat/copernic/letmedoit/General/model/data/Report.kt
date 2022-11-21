package cat.copernic.letmedoit.General.model.data

import cat.copernic.letmedoit.Users.model.data.UsersMap
import com.google.firebase.firestore.Exclude

data class Report(
    @get:Exclude val id : String,
    val users: UsersMap,
    val description: String,
    val reason_id: Int,
    val archived: Boolean
)
