package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Opinion (

    @get:Exclude val id : String = UUID.randomUUID().toString(),
    val userId :          String = "",
    val serviceId :       String = "",
    val rating  :       Float = 0f,
    val description:    String = "",
    val deal_id :       String = ""

) : Parcelable