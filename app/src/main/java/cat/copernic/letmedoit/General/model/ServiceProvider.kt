package cat.copernic.letmedoit.General.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class ServiceProvider {
    companion object{
        fun getServices() : ArrayList<Service>{
            return arrayListOf(
                Service(
                    "1",
                    "Página Web",
                    "Creación de página web",
                    CategoryMap("Informática","Web"),
                    arrayListOf(
                        Image("1","https://picsum.photos/200/200"),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "2",
                    "Tacos",
                    "Creación de página web",
                    CategoryMap("Cocina","Méxicana"),
                    arrayListOf(
                        Image("1","https://picsum.photos/200/200"),
                    ),
                    200,
                    java.util.Calendar.getInstance().time,
                ),
                Service(
                    "3",
                    "Página Web",
                    "Creación de página web",
                    CategoryMap("Informática","Web"),
                    arrayListOf(
                        Image("1","https://picsum.photos/200/200"),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "4",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap("Belleza","Pelo"),
                    arrayListOf(
                        Image("1","https://picsum.photos/200/200"),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "5",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap("Informática","Web"),
                    arrayListOf(
                        Image("1","https://picsum.photos/200/200"),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "6",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap("Informática","Web"),
                    arrayListOf(
                        Image("1","https://picsum.photos/200/200"),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "7",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap("Informática","Web"),
                    arrayListOf(
                        Image("1","https://picsum.photos/200/200"),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "8",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap("Informática","Web"),
                    arrayListOf(
                        Image("1","https://picsum.photos/200/200"),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "9",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap("Informática","Web"),
                    arrayListOf(
                        Image("1","https://picsum.photos/200/200"),
                    ),
                    200,
                    java.util.Calendar.getInstance().time
                )
            )
        }
    }
}