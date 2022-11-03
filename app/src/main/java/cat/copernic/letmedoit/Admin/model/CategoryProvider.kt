package cat.copernic.letmedoit.Admin.model

import cat.copernic.letmedoit.Admin.model.Category
import cat.copernic.letmedoit.Admin.model.Subcategory

class CategoryProvider {
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
                cat.copernic.letmedoit.Admin.model.Category(
                    "Historia",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
                        "Edat Antigua",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Mantenimiento",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
                        "Piscinas",
                        "100"
                    ),
                    "email_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Deportes",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
                        "Atletismo",
                        "100"
                    ),
                    "filter_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Musica",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
                        "Bachata",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Reforma",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
                        "Pintura",
                        "100"
                    ),
                    "filter_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Botanica",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
                        "Podar setos",
                        "100"
                    ),
                    "email_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Animales",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Geologia",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Literatura",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Jardineria",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Transporte",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Educación",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
                        "Pasear perros",
                        "100"
                    ),
                    "favorites_icon",
                    "3"
                ),
                cat.copernic.letmedoit.Admin.model.Category(
                    "Ocio",
                    cat.copernic.letmedoit.Admin.model.Subcategory(
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