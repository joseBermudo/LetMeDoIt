package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import cat.copernic.letmedoit.Utils.datahepers.ServicesMap
import cat.copernic.letmedoit.Utils.datahepers.UsersMap
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Deal(
    @get:Exclude var id :   String = UUID.randomUUID().toString(),
    val users : UsersMap = UsersMap("",""),
    val services : ServicesMap = ServicesMap("",""),
    val description:        String = "",
    var accepted :          Boolean = false,
    var conclude:           Int = 0
) :Parcelable