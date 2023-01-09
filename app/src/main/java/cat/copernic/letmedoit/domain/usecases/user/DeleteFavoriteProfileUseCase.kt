package cat.copernic.letmedoit.domain.usecases.user

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteFavoriteProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(idProfile : String) : Flow<DataState<Boolean>> = userRepository.deleteFavoriteProfile(idProfile)
}