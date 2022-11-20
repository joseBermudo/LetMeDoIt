package cat.copernic.letmedoit.General.model.data

import com.google.firebase.firestore.Exclude

data class ReasonReports (
    @get:Exclude val id : String,
    val name : String
        )