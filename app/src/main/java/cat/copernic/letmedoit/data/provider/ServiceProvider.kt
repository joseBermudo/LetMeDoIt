package cat.copernic.letmedoit.data.provider

import cat.copernic.letmedoit.Utils.datahepers.CategoryMap
import cat.copernic.letmedoit.data.model.Image
import cat.copernic.letmedoit.data.model.Service
import kotlin.collections.ArrayList

class ServiceProvider {
    companion object{
        fun getServices() : ArrayList<Service>{
            return arrayListOf(
                Service(
                    "1",
                    "Página Web",
                    "Creación de página web",
                    CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                        Image(
                            "2",
                            "https://picsum.photos/200/200"
                        ),
                        Image(
                            "3",
                            "https://picsum.photos/200/200"
                        ),
                        Image(
                            "4",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    "11/07/2021 20:00:00"
                ),
                Service(
                    "2",
                    "Tacos",
                    "Creación de página web",
                    CategoryMap(
                        "Cocina",
                        "Méxicana"
                    ),
                    arrayListOf(
                        Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    "11/07/2021 21:00:00"
                ),
                Service(
                    "3",
                    "Página Web",
                    "Creación de página web",
                    CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    "11/07/2021 22:00:00"
                ),
                Service(
                    "4",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap(
                        "Belleza",
                        "Pelo"
                    ),
                    arrayListOf(
                        Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    "12/07/2021 20:00:00"
                ),
                Service(
                    "5",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    "13/07/2021 20:00:00"
                ),
                Service(
                    "6",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    "11/07/2021 22:00:00"
                ),
                Service(
                    "7",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    "11/07/2021 21:00:00"
                ),
                Service(
                    "8",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    "11/07/2021 10:00:00"
                ),
                Service(
                    "9",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap(
                        "Informática",
                        "Web"
                    ),
                    arrayListOf(
                        Image(
                            "1",
                            "https://picsum.photos/200/200"
                        ),
                    ),
                    200,
                    "11/07/2021 21:00:00"
                )
            )
        }
    }
}