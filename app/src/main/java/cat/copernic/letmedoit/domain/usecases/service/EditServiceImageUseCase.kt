package cat.copernic.letmedoit.domain.usecases.service

import android.net.Uri
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.ServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditServiceImageUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
){
    suspend operator fun invoke(idService : String, newFileURI : Uri, index: Int) : Flow<DataState<String>> = serviceRepository.editServiceImage(idService,newFileURI,index)
}
