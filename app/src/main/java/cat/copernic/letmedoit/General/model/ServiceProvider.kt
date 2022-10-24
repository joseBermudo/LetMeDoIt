package cat.copernic.letmedoit.General.model

import kotlin.collections.ArrayList

class ServiceProvider {
    companion object{
        fun getServices() : ArrayList<Service>{
            return arrayListOf(
                Service(
                    "1",
                    "Página Web",
                    "Creación de página web",
                    CategoryMap("1","10"),
                    Image("1","https://picsum.photos/200/200"),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "2",
                    "Tacos",
                    "Creación de página web",
                    CategoryMap("1","10"),
                    Image("1","https://picsum.photos/200/200"),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "3",
                    "Página Web",
                    "Creación de página web",
                    CategoryMap("1","10"),
                    Image("1","https://picsum.photos/200/200"),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "1",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap("1","10"),
                    Image("1","https://picsum.photos/200/200"),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "1",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap("1","10"),
                    Image("1","https://picsum.photos/200/200"),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "1",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap("1","10"),
                    Image("1","https://picsum.photos/200/200"),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "1",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap("1","10"),
                    Image("1","https://picsum.photos/200/200"),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "1",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap("1","10"),
                    Image("1","https://picsum.photos/200/200"),
                    200,
                    java.util.Calendar.getInstance().time
                ),
                Service(
                    "1",
                    "Peluquería",
                    "Creación de página web",
                    CategoryMap("1","10"),
                    Image("1","https://picsum.photos/200/200"),
                    200,
                    java.util.Calendar.getInstance().time
                )
            )
        }
    }
}