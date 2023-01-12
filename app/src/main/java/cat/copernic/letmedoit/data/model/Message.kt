package cat.copernic.letmedoit.data.model

import androidx.annotation.Keep

@Keep
data class Message (
    val id : String,
    val text : String,
    val created : String
)