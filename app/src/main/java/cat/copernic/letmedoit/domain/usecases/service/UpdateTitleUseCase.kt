package cat.copernic.letmedoit.domain.usecases.service

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.ServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTitleUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
){
    suspend operator fun invoke(idService : String, newTitle : String) : Flow<DataState<Boolean>> = serviceRepository.updateTitle(idService,newTitle)
}