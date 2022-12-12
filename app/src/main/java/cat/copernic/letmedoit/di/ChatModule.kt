package cat.copernic.letmedoit.di

import cat.copernic.letmedoit.data.remote.ChatRepositoryImpl
import cat.copernic.letmedoit.domain.repositories.ChatRepository
import com.google.firebase.firestore.CollectionReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Module -> Encargado de construir dependencias
//Lo instalamos como un singleton, de esta forma es la misma clase para toda la app
@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    //Provider que proporciona un Singleton el cual permite obtener una implementación del deal repository.
    @Provides
    @Singleton
    fun chatRepositoryProvider(
        @FirebaseModule.ChatCollection chatCollection: CollectionReference
    ): ChatRepository{
        return ChatRepositoryImpl(
            chatCollection
        )
    }
}