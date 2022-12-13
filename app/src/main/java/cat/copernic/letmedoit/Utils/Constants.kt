package cat.copernic.letmedoit.Utils

import cat.copernic.letmedoit.data.model.Users

object Constants {
    const val DEALS_COLLECTION = "deals"
    const val USERS_COLLECTION = "users"
    const val CHATS_COLLECTION = "chats"
    const val SERVICES_COLLECTION = "services"
    const val SERVICES_COLLECTION_IMAGES = "images"
    const val CATEGORIES_COLLECTION ="categories"
    const val REPORTS_COLLECTION = "reports"
    const val REASONS_REPORTS_COLLECTION = "reasons_reports"

    var USER_LOGGED_IN_ID = ""
    lateinit var USER_LOGGED_IN : Users
    var TOKEN = ""
}