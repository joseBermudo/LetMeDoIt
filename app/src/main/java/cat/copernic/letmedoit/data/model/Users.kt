package cat.copernic.letmedoit.data.model

import com.google.firebase.firestore.Exclude

class Users(
    @get:Exclude val id: String? = "",
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
    @get:Exclude val servicesId: ArrayList<String>? = null,
    @get:Exclude val favorites: FavoritesMap? = null,
    @get:Exclude val chatsId: ArrayList<String>? = null,
    @get:Exclude val historyDeals: ArrayList<HistoryDeal>? = null,
    @get:Exclude val opinions: ArrayList<Opinions>? = null,
    val rating: Float? = null,
    val banned: Boolean? = null,
    val admin: Boolean? = null,
    val username: String? = null
    )