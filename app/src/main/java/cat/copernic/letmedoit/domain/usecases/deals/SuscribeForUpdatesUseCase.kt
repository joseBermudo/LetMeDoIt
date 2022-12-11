package cat.copernic.letmedoit.domain.usecases.deals

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.domain.repositories.DealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SuscribeForUpdatesUseCase @Inject constructor(
    private val dealRepository: DealRepository
){
    suspend operator fun invoke(idDeal: String) : Flow<DataState<Deal?>> = dealRepository.suscribeForUpdates(idDeal)
}