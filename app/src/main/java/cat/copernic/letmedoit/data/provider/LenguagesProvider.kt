package cat.copernic.letmedoit.data.provider

import cat.copernic.letmedoit.Utils.datahepers.Lenguages



class LenguagesProvider {
    companion object {
        fun obtenerLenguages(): ArrayList<Lenguages> {
            return arrayListOf(
                Lenguages(
                    "English",
                    "imagen 1",
                ),
                Lenguages(
                    "Español",
                    "imagen 2",
                ),
                Lenguages(
                    "Català",
                    "imagen 3",
                ),
            )
        }
    }
}