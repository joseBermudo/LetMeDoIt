package cat.copernic.letmedoit.domain.usecases.user

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.datahepers.ContactInfoMap
import cat.copernic.letmedoit.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateContactInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(contactInfo : ContactInfoMap) : Flow<DataState<Boolean>> = userRepository.updateContactInfo(contactInfo)
}