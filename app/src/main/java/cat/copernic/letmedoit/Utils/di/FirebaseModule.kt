package cat.copernic.letmedoit.Utils.di

import cat.copernic.letmedoit.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

//Module -> Encargado de construir dependencias
//Lo instalamos como un singleton, de esta forma es la misma clase para toda la app
@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    //Componente Singleton que provee el FireBaseAuth
    @Provides
    @Singleton
    fun firebaseAuthProvider() : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    //Componente Singleton que provee la instacia de Firebase Firestiore
    @Provides
    @Singleton
    fun firestoreProvider() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @UsersCollection
    @Provides
    @Singleton
    fun usersProvider(firestore: FirebaseFirestore) : CollectionReference{
        return firestore.collection(Constants.USERS_COLLECTION)
    }

    //Annotation Class --> Utilizada para identificar un binding especifico para un tipo cuandoe ste tipo tiene multiples bindings definidos
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UsersCollection
}