package cat.copernic.letmedoit.General.model.data

import com.google.firebase.firestore.Exclude

data class Image (
        @get:Exclude val  id : String,
        val img_link : String,
        @get:Exclude var checked : Boolean? = false
)