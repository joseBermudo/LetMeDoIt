package cat.copernic.letmedoit

import cat.copernic.letmedoit.Admin.model.Category
import cat.copernic.letmedoit.Admin.model.Subcategory

class LISTASDEPRUEBA {
    companion object{
        fun obtenerCategorias() : ArrayList<cat.copernic.letmedoit.Admin.model.Category>{
            return arrayListOf(
                cat.copernic.letmedoit.Admin.model.Category(
                    "Informática",
                    cat.copernic.letmedoit.Admin.model.Subcategory("Web", "100"),
                    "email_icon",
                    "1"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Cocina",
                    cat.copernic.letmedoit.Admin.model.Subcategory("Griega", "100"),
                    "favorites_icon",
                    "2"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Vehiculos",
                    cat.copernic.letmedoit.Admin.model.Subcategory("Coches", "100"),
                    "filter_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Informática",
                    cat.copernic.letmedoit.Admin.model.Subcategory("Web", "100"),
                    "email_icon",
                    "1"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Cocina",
                    cat.copernic.letmedoit.Admin.model.Subcategory("Griega", "100"),
                    "favorites_icon",
                    "2"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Vehiculos",
                    cat.copernic.letmedoit.Admin.model.Subcategory("Coches", "100"),
                    "filter_icon",
                    "3"
                ),
            )
        }
    }
}