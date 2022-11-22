package cat.copernic.letmedoit.data.model

import com.google.firebase.firestore.Exclude

data class ReasonReports (
    @get:Exclude val id : String,
    val name : String
        )