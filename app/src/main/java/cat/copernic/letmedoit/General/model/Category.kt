package cat.copernic.letmedoit.General.model

data class Category(
    var nombre: String,
    var subcategories: ArrayList<Subcategory>,
    val image: String,
    val id : String
)
