package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Deal(
    @get:Exclude val id :   String = UUID.randomUUID().toString(),
    val users : UsersMap = UsersMap("",""),
    val services : ServicesMap = ServicesMap("",""),
    val description:        String = "",
    val accepted :          Boolean = false,
    var conclude:           Int = 0
) :Parcelable