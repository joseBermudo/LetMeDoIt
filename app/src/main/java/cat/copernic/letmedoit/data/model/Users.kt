package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import cat.copernic.letmedoit.Utils.datahepers.*
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class Users(
    @get:Exclude  var id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var surname: String = "",
    val email: String = "",
    val language: Int = 0,
    val darkTheme: Boolean = false,
    var avatar: String = "",
    val curriculum: String = "",
    var schedule: ScheduleMap = ScheduleMap("",""),
    var aboutMe: String = "",
    var contactInfo: ContactInfoMap = ContactInfoMap("",""),
    var location: String = "",
    @get:Exclude var servicesId: ArrayList<UserServices>? = null,
    @get:Exclude val favorites: FavoritesMap? = null,
    @get:Exclude val chatsId: ArrayList<UserChats>? = null,
    @get:Exclude val historyDeals: ArrayList<HistoryDeal>? = null,
    @get:Exclude var opinions: ArrayList<Opinion> = ArrayList(),
    @get:Exclude var check: Boolean = false,
    val rating: Float = 0f,
    var banned: Boolean = false,
    val admin: Boolean = false,
    val username: String = "",
    val deviceToken : String = ""
    ) : Parcelable