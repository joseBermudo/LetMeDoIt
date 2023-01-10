package cat.copernic.letmedoit.data.model

import cat.copernic.letmedoit.Utils.datahepers.UsersMap
import com.google.firebase.firestore.Exclude
import java.util.*

data class Report(
    val id: String = UUID.randomUUID().toString(),
    val users: UsersMap = UsersMap(),
    val description: String = "",
    val reason_id: Int = 0,
    val user_1: String = "",
    val user_2: String = "",
    val archived: Boolean = false,
    @get:Exclude var check: Boolean = false
)
