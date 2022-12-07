package cat.copernic.letmedoit.domain.usecases.service

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.CategoryMap
import cat.copernic.letmedoit.domain.repositories.ServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateCategoryUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
){
    suspend operator fun invoke(idService : String, newCategoryMap : CategoryMap) : Flow<DataState<Boolean>> = serviceRepository.updateCategory(idService,newCategoryMap)
}