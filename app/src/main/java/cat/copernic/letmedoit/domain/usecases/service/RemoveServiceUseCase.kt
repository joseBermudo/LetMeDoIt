package cat.copernic.letmedoit.domain.usecases.service

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.ServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveServiceUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
){
    suspend operator fun invoke(idService : String) : Flow<DataState<Boolean>> = serviceRepository.removeService(idService)
}