package cat.copernic.letmedoit.data.model

import cat.copernic.letmedoit.Utils.datahepers.UsersMap
import com.google.firebase.firestore.Exclude
import java.util.*

data class Report(
    val id : String = UUID.randomUUID().toString(),
    val users: UsersMap = UsersMap(),
    val description: String = "",
    val reason_id: Int = 0,
    @get:Exclude var user_1: String = "",
    @get:Exclude var user_2: String = "",
    val archived: Boolean = false
)
