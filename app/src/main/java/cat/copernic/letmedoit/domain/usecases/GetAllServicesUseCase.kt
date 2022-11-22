package cat.copernic.letmedoit.domain.usecases

import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.domain.repositories.ServiceRepository
import cat.copernic.letmedoit.Utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllServicesUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
){
    suspend operator fun invoke() : Flow<DataState<List<Service>>> = serviceRepository.getAllServices()
}