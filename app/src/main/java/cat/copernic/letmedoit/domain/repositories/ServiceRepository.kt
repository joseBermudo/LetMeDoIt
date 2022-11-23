package cat.copernic.letmedoit.domain.repositories

import android.app.Activity
import android.net.Uri
import androidx.fragment.app.Fragment
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.data.model.CategoryMap
import kotlinx.coroutines.flow.Flow


interface ServiceRepository {

    //add
    suspend fun saveService(service : Service) : Flow<DataState<Service>>
    suspend fun saveServiceImage(activity : Activity, fileURI : Uri, serviceId : String,fragment: Fragment,index : Int): Flow<DataState<String>>

    //get
    suspend fun getService(uid : String) : Flow<DataState<Service>>
    suspend fun getAllServices() : Flow<DataState<List<Service>>>

    //update
    suspend fun updateTitle(idService : String, newTitle : String): Flow<DataState<Boolean>>
    suspend fun updateDescription(idService : String, newDescription : String): Flow<DataState<Boolean>>
    suspend fun updateCategory(idService : String, newCategoryMap: CategoryMap): Flow<DataState<Boolean>>
    suspend fun updateNLikes(idService : String, newNum : Int): Flow<DataState<Boolean>>
    suspend fun updateEditedTime(idService : String, newEditedTime : String): Flow<DataState<Boolean>>
    suspend fun editServiceImage(idService : String, idImg : String, newFileURI : Uri, ): Flow<DataState<Boolean>>

}