package cat.copernic.letmedoit.Admin.model

data class Category(
    val nombre: String,
    val subcategories: cat.copernic.letmedoit.Admin.model.Subcategory,
    val image: String,
    val id : String
)
