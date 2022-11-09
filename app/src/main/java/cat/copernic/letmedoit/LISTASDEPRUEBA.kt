package cat.copernic.letmedoit

import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.General.model.Subcategory

class LISTASDEPRUEBA {
    companion object {
        fun obtenerCategorias(): ArrayList<Category> {
            return arrayListOf(
                Category(
                    "Informática",
                    arrayListOf(Subcategory("Web", "100")),
                    "email_icon",
                    "1"
                ),
                Category(
                    "Cocina",
                    arrayListOf(Subcategory("Web", "100")),
                    "favorites_icon",
                    "2"
                ),
                Category(
                    "Vehiculos",
                    arrayListOf(Subcategory("Web", "100")),
                    "filter_icon",
                    "3"
                ),
                Category(
                    "Informática",
                    arrayListOf(Subcategory("Web", "100")),
                    "email_icon",
                    "1"
                ),
                Category(
                    "Cocina",
                    arrayListOf(Subcategory("Web", "100")),
                    "favorites_icon",
                    "2"
                ),
                Category(
                    "Vehiculos",
                    arrayListOf(Subcategory("Web", "100")),
                    "filter_icon",
                    "3"
                ),
            )
        }
    }
}