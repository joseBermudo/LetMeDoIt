package cat.copernic.letmedoit.Users.model.data

import com.google.firebase.firestore.Exclude

data class Deal(
    @get:Exclude val id :   String,
    val Users :             UsersMap,
    val Services :          ServicesMap,
    val description:        String,
    val accepted :          Boolean,
    val conclude:           Boolean
)