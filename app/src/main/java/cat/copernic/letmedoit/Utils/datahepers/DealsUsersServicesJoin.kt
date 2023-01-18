package cat.copernic.letmedoit.Utils.datahepers

import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.data.model.Users

/**
 * Data Class de utilidad para manejar el listado de tratos
 * @param deal id del trato
 * @param userOne id del usario 1
 * @param userTwo id del usuario 2
 * @param ServiceTwo id del servicio del usuario 2
 */
data class DealsUsersServicesJoin(
    val deal : Deal,
    val userOne : Users,
    val userTwo : Users,
    val ServiceTwo : Service
)