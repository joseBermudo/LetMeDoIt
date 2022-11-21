package cat.copernic.letmedoit.Users.view

class LenguagesProvider {
    companion object {
        fun obtenerLenguages(): ArrayList<cat.copernic.letmedoit.Users.model.data.Lenguages> {
            return arrayListOf(
                    cat.copernic.letmedoit.Users.model.data.Lenguages(
                    "English",
                    "imagen 1",
                ),cat.copernic.letmedoit.Users.model.data.Lenguages(
                    "Español",
                    "imagen 2",
                ),cat.copernic.letmedoit.Users.model.data.Lenguages(
                    "Català",
                    "imagen 3",
                ),
            )
        }
    }
}