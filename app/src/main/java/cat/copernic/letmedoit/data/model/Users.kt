package cat.copernic.letmedoit.data.model

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    @get:Exclude var id: String? = "",
    val name: String? = null,
    val surname: String? = null,
    val email: String? = null,
    val language: Int? = null,
    val darkTheme: Boolean? = null,
    val avatar: String? = null,
    val curriculum: String? = null,
    val schedule: ScheduleMap? = null,
    val aboutMe: String? = null,
    val contactInfo: ContactInfoMap? = null,
    val location: String? = null,
    @get:Exclude var servicesId: ArrayList<UserServices>? = null,
    @get:Exclude val favorites: FavoritesMap? = null,
    @get:Exclude val chatsId: ArrayList<UserChats>? = null,
    @get:Exclude val historyDeals: ArrayList<HistoryDeal>? = null,
    @get:Exclude val opinions: ArrayList<Opinions>? = null,
    val rating: Float = 0f,
    val banned: Boolean? = null,
    val admin: Boolean? = null,
    val username: String? = null
    ) : Parcelable