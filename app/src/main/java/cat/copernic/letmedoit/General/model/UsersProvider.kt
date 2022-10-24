package cat.copernic.letmedoit.General.model

class UsersProvider {

    companion object{
        fun obtenerUsers() : ArrayList<Users> {
            return arrayListOf(
                Users(
                    "Pau",
                ),
                Users(
                    "Ismael",
                )
            )
        }
    }
}