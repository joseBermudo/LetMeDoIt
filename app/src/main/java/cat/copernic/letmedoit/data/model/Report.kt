package cat.copernic.letmedoit.data.model

import cat.copernic.letmedoit.Utils.datahepers.UsersMap
import com.google.firebase.firestore.Exclude

data class Report(
    @get:Exclude val id : String,
    val users: UsersMap,
    val description: String,
    val reason_id: Int,
    val archived: Boolean
)
