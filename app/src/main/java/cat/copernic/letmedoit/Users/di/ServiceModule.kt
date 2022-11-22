package cat.copernic.letmedoit.Users.di

import cat.copernic.letmedoit.Users.model.remote.ServiceRepositoryImpl
import cat.copernic.letmedoit.Users.model.repository.ServiceRepository
import cat.copernic.letmedoit.Utils.di.FirebaseModule
import cat.copernic.letmedoit.Visitante.model.remote.LoginRepositoryImpl
import cat.copernic.letmedoit.Visitante.model.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
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