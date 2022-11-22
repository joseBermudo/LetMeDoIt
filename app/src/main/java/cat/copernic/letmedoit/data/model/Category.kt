package cat.copernic.letmedoit.data.model

import com.google.firebase.firestore.Exclude


data class Category(

    val nombre: String,
    val description: String,
    var subcategories: ArrayList<Subcategory>,
    val image: String,
    @get:Exclude val id : String
)
