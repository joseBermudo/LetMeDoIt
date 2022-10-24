package cat.copernic.letmedoit.General.model

import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.General.model.Subcategory

class CategoryProvider {
    companion object{
        fun obtenerCategorias() : ArrayList<Category>{
            return arrayListOf(
                Category(
                    "Informática",
                    Subcategory("Web", "100"),
                    "email_icon",
                    "1"
                ),
                Category(
                    "Cocina",
                    Subcategory("Griega", "100"),
                    "favorites_icon",
                    "2"
                ),
                Category(
                    "Vehiculos",
                    Subcategory("Coches", "100"),
                    "filter_icon",
                    "3"
                ),
                Category(
                    "Informática",
                    Subcategory("Web", "100"),
                    "email_icon",
                    "1"
                ),
                Category(
                    "Cocina",
                    Subcategory("Griega", "100"),
                    "favorites_icon",
                    "2"
                ),
                Category(
                    "Vehiculos",
                    Subcategory("Coches", "100"),
                    "filter_icon",
                    "3"
                ),
            )
        }
    }
}