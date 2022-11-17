package cat.copernic.letmedoit.General.model.data

import com.google.firebase.firestore.Exclude

data class ReasonReport(
    @get:Exclude val id : Int,
    val name: String
)