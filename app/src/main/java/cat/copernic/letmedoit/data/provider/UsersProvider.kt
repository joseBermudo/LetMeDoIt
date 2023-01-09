package cat.copernic.letmedoit.data.provider

import cat.copernic.letmedoit.data.model.Users

class UsersProvider {

    companion object{
        fun obtenerUsers() : ArrayList<Users> {
            return arrayListOf(
                Users(
                    "01",
                    "Pau",
                    banned = true,
                ),
                Users(
                    "02",
                    "Ismael",
                    banned = true,
                ),
                Users(
                    "03",
                    "Lausin",
                    banned = false,
                ),
                Users(
                    "04",
                    "Mire",
                    banned = true,
                ),
                Users(
                    "05",
                    "Nico",
                    banned = true,
                ),
                Users(
                    "06",
                    "Maxi",
                    banned = false,
                ),
                Users(
                    "07",
                    "Mauri",
                    banned = true,
                ),
                Users(
                    "08",
                    "Jose",
                    banned = false,
                ),
                Users(
                    "09",
                    "Alex",
                    banned = false,
                ),
                Users(
                    "Paco",
                    "09",
                    banned = true,
                )
            )
        }
    }
}