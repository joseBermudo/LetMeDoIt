package cat.copernic.letmedoit.domain.usecases.deals

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.DealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DenyDealUseCase @Inject constructor(
    private val dealRepository: DealRepository
){
    suspend operator fun invoke(id: String): Flow<DataState<Boolean>> = dealRepository.denyDeal(id)
}