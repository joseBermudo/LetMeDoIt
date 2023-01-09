package cat.copernic.letmedoit.di

import cat.copernic.letmedoit.data.remote.CategoryRepositoryImpl
import cat.copernic.letmedoit.domain.repositories.CategoryRepository
import com.google.firebase.firestore.CollectionReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {

    @Provides
    @Singleton
    fun provideCategoryRepository(
        @FirebaseModule.CategoryCollection categoryCollection: CollectionReference
    ): CategoryRepository {
        return CategoryRepositoryImpl(
            categoryCollection
        )
    }
}