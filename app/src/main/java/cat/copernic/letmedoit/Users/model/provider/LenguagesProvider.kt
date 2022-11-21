package cat.copernic.letmedoit.Users.model.provider

import cat.copernic.letmedoit.Users.model.data.Lenguages


import cat.copernic.letmedoit.Users.model.data.Lenguages

class LenguagesProvider {
    companion object {
        fun obtenerLenguages(): ArrayList<Lenguages> {
            return arrayListOf(
                Lenguages(
                    "English",
                    "imagen 1",
                ),Lenguages(
                    "Español",
                    "imagen 2",
                ),Lenguages(
                    "Català",
                    "imagen 3",
                ),
            )
        }
    }
}