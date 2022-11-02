package cat.copernic.letmedoit.General.model

import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.General.model.Subcategory

class CategoryProvider {
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
                cat.copernic.letmedoit.General.model.Category(
                    "Historia",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Edat Antigua",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Mantenimiento",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Piscinas",
                        "100"
                    ),
                    "email_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Deportes",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Atletismo",
                        "100"
                    ),
                    "filter_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Musica",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Bachata",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Reforma",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Pintura",
                        "100"
                    ),
                    "filter_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Botanica",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Podar setos",
                        "100"
                    ),
                    "email_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Animales",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Geologia",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Literatura",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Jardineria",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Transporte",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Educación",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.General.model.Category(
                    "Ocio",
                    cat.copernic.letmedoit.General.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
            )
        }
    }
}