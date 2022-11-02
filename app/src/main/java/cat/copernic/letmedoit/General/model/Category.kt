package cat.copernic.letmedoit.General.model

data class Category(
    val nombre: String,
    val subcategories: cat.copernic.letmedoit.General.model.Subcategory,
    val image: String,
    val id : String
)
