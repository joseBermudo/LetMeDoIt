package cat.copernic.letmedoit.domain.usecases.deals

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.domain.repositories.DealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class InsertDealUseCase @Inject constructor(
    private val dealRepository: DealRepository
){
    suspend operator fun invoke(deal: Deal) : Flow<DataState<Boolean>> = dealRepository.insertDeal(deal)
}

