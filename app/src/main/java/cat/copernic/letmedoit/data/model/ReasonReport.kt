package cat.copernic.letmedoit.data.model

import com.google.firebase.firestore.Exclude

data class ReasonReport(
    @get:Exclude val id : Int,
    val name: String
)