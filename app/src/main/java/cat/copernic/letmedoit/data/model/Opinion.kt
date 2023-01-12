package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Keep
@Parcelize
data class Opinion (

    @get:Exclude val id : String = UUID.randomUUID().toString(),
    val userId :          String = "",
    val serviceId :       String = "",
    val rating  :       Float = 0f,
    val description:    String = "",
    val deal_id :       String = ""

) : Parcelable