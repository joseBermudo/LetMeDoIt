package cat.copernic.letmedoit.domain.repositories

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.Deal
import kotlinx.coroutines.flow.Flow

interface DealRepository {
    suspend fun insertDeal(deal: Deal): Flow<DataState<Boolean>>
    suspend fun denyDeal(id: String): Flow<DataState<Boolean>>
    suspend fun acceptDeal(id: String): Flow<DataState<Boolean>>
    suspend fun concludeDeal(id: String): Flow<DataState<Boolean>>
    suspend fun getDeal(id : String) : Flow<DataState<Deal>>
    suspend fun getDeals() : Flow<DataState<List<Deal>>>
    suspend fun suscribeForUpdates(idDeal : String) : Flow<DataState<Deal?>>
}