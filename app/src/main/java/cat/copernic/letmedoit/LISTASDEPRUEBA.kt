package cat.copernic.letmedoit

import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.General.model.Subcategory

class LISTASDEPRUEBA {
    companion object{
        fun obtenerCategorias() : ArrayList<cat.copernic.letmedoit.General.model.Category>{
            return arrayListOf(
                cat.copernic.letmedoit.General.model.Category(
                    "Informática",
                    cat.copernic.letmedoit.General.model.Subcategory("Web", "100"),
                    "email_icon",
                    "1"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Cocina",
                    cat.copernic.letmedoit.General.model.Subcategory("Griega", "100"),
                    "favorites_icon",
                    "2"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Vehiculos",
                    cat.copernic.letmedoit.General.model.Subcategory("Coches", "100"),
                    "filter_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Informática",
                    cat.copernic.letmedoit.General.model.Subcategory("Web", "100"),
                    "email_icon",
                    "1"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Cocina",
                    cat.copernic.letmedoit.General.model.Subcategory("Griega", "100"),
                    "favorites_icon",
                    "2"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Vehiculos",
                    cat.copernic.letmedoit.General.model.Subcategory("Coches", "100"),
                    "filter_icon",
                    "3"
                ),
            )
        }
    }
}