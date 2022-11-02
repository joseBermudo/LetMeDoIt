package cat.copernic.letmedoit.General.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class ServiceProvider {
    companion object{
        fun getServices() : ArrayList<cat.copernic.letmedoit.General.model.Service>{
            return arrayListOf(
                cat.copernic.letmedoit.General.model.Service(
                    "1",
                    "Página Web",
                    "Creación de página web",
                    cat.copernic.letmedoit.General.model.CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        cat.copernic.letmedoit.General.model.Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                        cat.copernic.letmedoit.General.model.Image(
                            "2",
                            "https://picsum.photos/200/200"
                        ),
                        cat.copernic.letmedoit.General.model.Image(
                            "3",
                            "https://picsum.photos/200/200"
                        ),
                        cat.copernic.letmedoit.General.model.Image(
                            "4",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                cat.copernic.letmedoit.General.model.Service(
                    "2",
                    "Tacos",
                    "Creación de página web",
                    cat.copernic.letmedoit.General.model.CategoryMap(
                        "Cocina",
                        "Méxicana"
                    ),
                    arrayListOf(
                        cat.copernic.letmedoit.General.model.Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    java.util.Calendar.getInstance().time,
                ),
                cat.copernic.letmedoit.General.model.Service(
                    "3",
                    "Página Web",
                    "Creación de página web",
                    cat.copernic.letmedoit.General.model.CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        cat.copernic.letmedoit.General.model.Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                cat.copernic.letmedoit.General.model.Service(
                    "4",
                    "Peluquería",
                    "Creación de página web",
                    cat.copernic.letmedoit.General.model.CategoryMap(
                        "Belleza",
                        "Pelo"
                    ),
                    arrayListOf(
                        cat.copernic.letmedoit.General.model.Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                cat.copernic.letmedoit.General.model.Service(
                    "5",
                    "Peluquería",
                    "Creación de página web",
                    cat.copernic.letmedoit.General.model.CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        cat.copernic.letmedoit.General.model.Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                cat.copernic.letmedoit.General.model.Service(
                    "6",
                    "Peluquería",
                    "Creación de página web",
                    cat.copernic.letmedoit.General.model.CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        cat.copernic.letmedoit.General.model.Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                cat.copernic.letmedoit.General.model.Service(
                    "7",
                    "Peluquería",
                    "Creación de página web",
                    cat.copernic.letmedoit.General.model.CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        cat.copernic.letmedoit.General.model.Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                cat.copernic.letmedoit.General.model.Service(
                    "8",
                    "Peluquería",
                    "Creación de página web",
                    cat.copernic.letmedoit.General.model.CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        cat.copernic.letmedoit.General.model.Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                cat.copernic.letmedoit.General.model.Service(
                    "9",
                    "Peluquería",
                    "Creación de página web",
                    cat.copernic.letmedoit.General.model.CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        cat.copernic.letmedoit.General.model.Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                )
            )
        }
    }
}