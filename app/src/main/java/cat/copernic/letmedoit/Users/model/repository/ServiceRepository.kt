package cat.copernic.letmedoit.Users.model.repository

import android.app.Activity
import android.net.Uri
import androidx.fragment.app.Fragment
import cat.copernic.letmedoit.General.model.data.Service
import cat.copernic.letmedoit.Utils.DataState
import kotlinx.coroutines.flow.Flow


interface ServiceRepository {
    suspend fun saveService(service : Service) : Flow<DataState<Service>>
    suspend fun getService(uid : String) : Flow<DataState<Service>>
    suspend fun getAllServices() : Flow<DataState<List<Service>>>
    suspend fun saveServiceImage(activity : Activity, fileURI : Uri, serviceId : String,fragment: Fragment,index : Int): Flow<DataState<String>>
}