package cat.copernic.letmedoit.domain.usecases.service

import android.net.Uri
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.ServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Clase para el caso de uso de editar una imagen del servicio
 * @constructor Inyecta una instancia del repositorio de servicios
 * @property serviceRepository el repositorio de servicios para realizar la operación.
 */
class EditServiceImageUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
){
    /**
     * Método para invocar el caso de editar una imagen del servicio
     * @param idService id del servicio
     * @param newFileURI la dirección de la nueva imagen
     * @param index el indice de la imagen a editar
     * @return un flujo de datos que indica el estado de la operación (success, error, loading) y el resultado de la misma
     * */
    suspend operator fun invoke(idService : String, newFileURI : Uri, index: Int) : Flow<DataState<String>> = serviceRepository.editServiceImage(idService,newFileURI,index)
}
