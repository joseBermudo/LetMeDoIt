package cat.copernic.letmedoit.domain.usecases.user

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.datahepers.ScheduleMap
import cat.copernic.letmedoit.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateScheduleUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(schedule : ScheduleMap) : Flow<DataState<Boolean>> = userRepository.updateSchedule(schedule)
}