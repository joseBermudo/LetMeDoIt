package cat.copernic.letmedoit.Users.model.remote

import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment
import cat.copernic.letmedoit.General.model.data.Service
import cat.copernic.letmedoit.Users.model.repository.ServiceRepository
import cat.copernic.letmedoit.Users.view.fragments.NewService
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import javax.inject.Inject
import cat.copernic.letmedoit.Utils.di.FirebaseModule
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ServiceRepositoryImpl @Inject constructor(
    @FirebaseModule.ServiceCollection val serviceCollection: CollectionReference
) : ServiceRepository{

    override suspend fun saveService(service: Service): Flow<DataState<Service>> = flow{
        emit(DataState.Loading)
        try {
            var uploadSuccesful : Boolean = false
            //Guardamos el usuario, pero si ya existe un documento con estos datos hace un merge sobreescrbiendo los datos.
            service.id.let {
                serviceCollection.document(it).set(service, SetOptions.merge())
                    .addOnSuccessListener { uploadSuccesful = true }
                    .addOnFailureListener { uploadSuccesful = false }
                    .await()
            }
            if (!uploadSuccesful)
                throw Exception("Error Uploading Service")

            emit(DataState.Success(service))
            emit(DataState.Finished)
        }catch (e : Exception){
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }


    override suspend fun getService(uid : String): Flow<DataState<Service>> = flow{
        emit(DataState.Loading)
        try {
            val service = serviceCollection.document(uid).get().await().toObject(Service::class.java) ?: throw Exception("ID DOES NOT EXIST")
            emit(DataState.Success(service))
            emit(DataState.Finished)

        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

    override suspend fun getAllServices(): Flow<DataState<List<Service>>> = flow {
        emit(DataState.Loading)
        try {
            val services = serviceCollection.get().await().toObjects(Service::class.java)
            emit(DataState.Success(services))
            emit(DataState.Finished)

        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }


    override fun saveServiceImage(
        activity: Activity,
        fileURI: Uri,
        serviceId: String,
        fragment: Fragment,
        index : Int
    ) {
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child("serviceImages/${serviceId}/${index}")

        // Adding the file to reference
        sRef.putFile(fileURI)
            .addOnSuccessListener { taskSnapshot ->
                // The image upload is success
                // Get the downloadable url from the task snapshot
                taskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->
                        when(fragment) {
                            is NewService -> fragment.uploadImageSuccess(uri.toString())
                        }
                    }
            }
            .addOnFailureListener { exception ->
                Utils.showOkDialog(exception.toString(),fragment.requireContext())
            }
    }

}