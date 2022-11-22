package cat.copernic.letmedoit.di

import cat.copernic.letmedoit.data.remote.ServiceRepositoryImpl
import cat.copernic.letmedoit.domain.repositories.ServiceRepository
import com.google.firebase.firestore.CollectionReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {


    @Provides
    @Singleton
    fun serviceRepositoryProvider(
        @FirebaseModule.ServiceCollection serviceCollection: CollectionReference
    ): ServiceRepository {
        return ServiceRepositoryImpl(serviceCollection)
    }
}