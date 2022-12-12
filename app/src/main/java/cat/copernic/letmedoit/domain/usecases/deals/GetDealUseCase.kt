package cat.copernic.letmedoit.domain.usecases.deals

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.domain.repositories.DealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDealUseCase @Inject constructor(
    private val dealRepository: DealRepository
){
    suspend operator fun invoke(id: String) : Flow<DataState<Deal>> = dealRepository.getDeal(id)
}