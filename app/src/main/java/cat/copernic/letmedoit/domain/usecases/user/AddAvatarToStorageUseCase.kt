package cat.copernic.letmedoit.domain.usecases.user

import android.net.Uri
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddAvatarToStorageUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(uri: Uri) : Flow<DataState<String>> = userRepository.addAvatarToStorage(uri)
}