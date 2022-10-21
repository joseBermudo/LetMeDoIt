package cat.copernic.letmedoit.General.model

data class Category(
    val nombre: String,
    val subcategories: Subcategory,
    val image: String,
    val id : String
)
