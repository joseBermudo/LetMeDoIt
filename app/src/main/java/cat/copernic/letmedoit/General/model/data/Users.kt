package cat.copernic.letmedoit.General.model.data

import com.google.firebase.firestore.Exclude

class Users(
    @get:Exclude val id: String,
    val name: String?,
    val surname : String?,
    val email: String?,
    val language: Int?,
    val darkTheme: Boolean?,
    val avatar: String?,
    val curriculum: String?,
    val schedule: ScheduleMap?,
    val aboutMe: String?,
    val contactInfo: ContactInfoMap?,
    val location: String?,
    val servicesId: ArrayList<String>?,
    val favorites: FavoritesMap?,
    val chatsId: ArrayList<String>?,
    val historyDeals: ArrayList<HistoryDeal>?,
    val opinions: ArrayList<Opinions>?,
    val rating: Float?,
    val banned: Boolean?,
    val admin: Boolean?,
    val username : String?
    )