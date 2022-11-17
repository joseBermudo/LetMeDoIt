package cat.copernic.letmedoit

object Constants {
    const val DEALS_COLLECTION = "deals"
    const val USERS_COLLECTION = "users"
    const val CHATS_COLLECTION = "chats"
    const val SERVICES_COLLECTION = "services"
    const val CATEGORIES_COLLECTION ="categories"
    const val REPORTS_COLLECTION = "reports"
    const val REASONS_REPORTS_COLLECTION = "reasons_reports"

    var USER_LOGGED_IN_ID = ""

    fun valueOf(constant : Constants) : String{
        return constant.toString()
    }
}