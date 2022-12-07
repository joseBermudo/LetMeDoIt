package cat.copernic.letmedoit.presentation.view.users.fragments

import android.Manifest
import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.CategoryMap
import cat.copernic.letmedoit.data.model.Image
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.data.provider.CategoryProvider
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.databinding.FragmentNewServiceBinding
import cat.copernic.letmedoit.presentation.adapter.general.ImagesAdapter
import cat.copernic.letmedoit.presentation.viewmodel.general.ServiceViewModel
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewService.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class NewService : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        getContent = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList -> managePhotosUri(uriList) }
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permissions -> checkPermissions(permissions) }

    }
    var colum = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        READ_EXTERNAL_STORAGE
    )
    private val args: NewServiceArgs by navArgs()
    private val serviceViewModel : ServiceViewModel by viewModels()
    private val userViewModel : UserViewModel by viewModels()
    private lateinit var service : Service
    lateinit var getContent : ActivityResultLauncher<String>
    lateinit var binding : FragmentNewServiceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentNewServiceBinding.inflate(inflater,container,false)
        initListeners()
        val categoryNames = CategoryProvider.obtenerCategorias().map { it.nombre } as ArrayList<String>
        Utils.AsignarPopUpSpinner(requireContext(),categoryNames,binding.spinnerCategory)

        binding.editDescription.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                binding.scrollableLayout.requestDisallowInterceptTouchEvent(true)
                return false
            }

        })
        binding.spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val category = CategoryProvider.obtenerCategorias()[position]
                val subCategoryNames = category.subcategories?.map { it.nombre } as ArrayList<String>
                Utils.AsignarPopUpSpinner(requireContext(), subCategoryNames,binding.spinnerSubcategory)
            }

        }

        initRecyclerView()

        if(args.serviceID != "null")
            serviceViewModel.getService(args.serviceID)
        return binding.root
    }

    private fun initListeners() {
        binding.btnAddImage.setOnClickListener { addImage() }
        binding.btnRemoveImage.setOnClickListener { removeImage() }
        binding.selectAll.setOnClickListener { selectAll() }
        binding.btnSave.setOnClickListener{ saveService() }
    }


    private fun initViewWithData(service: Service) {
        binding.txtTitleEditService.text = resources.getText(R.string.txt_edit_service)
        binding.editServiceTitle.setText(service.title)
        var categoryPos = 0
        var subCategoryPos = 0
        for(i in 0 until binding.spinnerCategory.adapter.count){
            if(binding.spinnerCategory.getItemAtPosition(i).equals(service.category.id_category)){
                categoryPos = i
                break
            }
        }
        for(i in 0 until binding.spinnerSubcategory.adapter.count){
            if(binding.spinnerCategory.getItemAtPosition(i).equals(service.category.id_category)){
                subCategoryPos = i
                break
            }
        }
        binding.spinnerCategory.setSelection(categoryPos)
        binding.spinnerSubcategory.setSelection(subCategoryPos)
        binding.editDescription.setText(service.description)
        imagesList.addAll(service.image)
        adapter.notifyDataSetChanged()

        val menu_inferior = requireActivity().findViewById<BottomNavigationView>(R.id.menu_inferior)
        menu_inferior.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }
    var totalImagesEdited = 0
    private fun initObservers() {
        serviceViewModel.getServiceState.observe(viewLifecycleOwner,Observer { dataState ->
            when(dataState){
                is DataState.Success<Service> -> {
                    service = dataState.data
                    initViewWithData(service)
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {}
                else -> Unit
            }
        })
        serviceViewModel.updateServiceImageState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<String> -> {
                    editImageSuccess(dataState.data)
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> { }
                else -> Unit
            }
        } )
        serviceViewModel.removeImageUseCaseState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    removeImageSuccess()
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> { }
                else -> Unit
            }
        } )
        serviceViewModel.saveImageState.observe(viewLifecycleOwner,Observer { dataState ->
            when(dataState){
                is DataState.Success<String> -> {
                    uploadImageSuccess(dataState.data)
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {}
                else -> Unit
            }
        })
        serviceViewModel.saveServiceState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Service> -> {
                    userViewModel.addService(dataState.data.id)
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> { }
                else -> Unit
            }
        } )

        userViewModel.addServiceState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    hideProgress()
                    //Utils.showOkDialog("Service Uploaded",requireContext())
                    resetComponents()
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> { }
                else -> Unit
            }
        } )

    }




    private fun resetComponents() {
        binding.editDescription.setText("")
        binding.editServiceTitle.setText("")
        numImgUploaded = 0
        imagesUploaded.removeAll(imagesUploaded)
        adapter.removeAll()
        adapter.notifyDataSetChanged()
    }

    private fun hideProgress() {
        binding.btnSave.isEnabled = true
        binding.btnSave.text = resources.getString(R.string.save)
        binding.uploadServiceLoading.isVisible = false
    }

    private fun showProgress() {
        binding.btnSave.isEnabled = false
        binding.btnSave.text = ""
        binding.uploadServiceLoading.isVisible = true
    }

    private fun saveImages() {
        var index = 0
        if (args.serviceID != "null"){
            adapter.getItems().forEachIndexed { i, image ->
                if(image.img_link.contains("content://")){
                    serviceViewModel.editServiceImage(service.id,image.img_link.toUri(),i)
                    totalImagesEdited++
                }
            }
            if(adapter.getItems().size < service.image.size){
                var startIndex = service.image.size-1
                for (i in startIndex until adapter.getItems().size+1){
                    serviceViewModel.removeImage(service.id,i,service.image[i].img_link)
                    totalImagesEdited--
                }
            }
            return
        }
        adapter.getItems().forEach {
            serviceViewModel.saveImage(requireActivity(),it.img_link.toUri(),idServiceRandom,this,index)
            index++
        }
    }

    lateinit var idServiceRandom : String

    private fun isDataSet() : Boolean{
        if(binding.editDescription.text.isNullOrEmpty()){
            Utils.showOkDialog("Description Must Be Indicated",requireContext())
            return false
        }
        if(binding.editServiceTitle.text.isNullOrEmpty()){
            Utils.showOkDialog("Title Must Be Indicated",requireContext())
            return false
        }
        if(binding.listImages.size <= 0){
            Utils.showOkDialog("You Must Add Images",requireContext())
            return false
        }

        return true

    }

    private fun saveService() {
        if (!isDataSet())
            return

        idServiceRandom = UUID.randomUUID().toString()
        saveImages()
        showProgress()

    }

    var numImgUploaded = 0
    var imagesUploaded = ArrayList<Image>()
    fun uploadImageSuccess(uri : String){
        imagesUploaded.add(Image(numImgUploaded.toString(),uri,numImgUploaded))
        numImgUploaded++
        if(numImgUploaded >= adapter.itemCount)
            uploadService()

    }
    var numImagesRemoved = 0
    private fun removeImageSuccess() {
        numImagesRemoved++
        if(numImagesRemoved >= totalImagesEdited)
            editService()
    }
    var numImgEdited = 0
    var imagesEdited = ArrayList<Image>()
    fun editImageSuccess(uri : String){

        imagesEdited.add(Image(numImgEdited.toString(),uri))
        numImgUploaded++
        if(numImgUploaded >= totalImagesEdited)
            editService()

    }

    private fun editService() {
        serviceViewModel.updateTitle(service.id,binding.editServiceTitle.text.toString())
        serviceViewModel.updateDescription(service.id,binding.editDescription.text.toString())
        serviceViewModel.updateEditedTime(service.id,LocalDate.now().format(DateTimeFormatter.ofPattern(("dd-MM-yyyy"))))
        serviceViewModel.updateCategory(service.id, CategoryMap(binding.spinnerCategory.selectedItem.toString(),binding.spinnerSubcategory.selectedItem.toString()))
        hideProgress()

    }

    private fun uploadService(){
        serviceViewModel.saveService(
            Service(
                id = idServiceRandom,
                title = binding.editServiceTitle.text.toString(),
                description = binding.editDescription.text.toString(),
                category = CategoryMap(binding.spinnerCategory.selectedItem.toString(),binding.spinnerSubcategory.selectedItem.toString()),
                image = imagesUploaded,
                userid = Constants.USER_LOGGED_IN_ID,
                edited_time = LocalDate.now().format(DateTimeFormatter.ofPattern(("dd-MM-yyyy")))

            )
        )
    }
    private var isAllSelected = false

    private fun selectAll() {
        if (!isAllSelected) adapter.selectAll()
        else adapter.unselectAll()

        isAllSelected = !isAllSelected
    }

    private fun removeImage() {
        adapter.removeItems()
        binding.selectAll.isChecked = false

    }

    private fun managePhotosUri(uriList: List<Uri>?) {
        var num = 0
        uriList?.forEach {
            imagesList.add(Image(num.toString(),it.toString(),num,false))
            num++
        }
        adapter.notifyDataSetChanged()
    }

    var storagePermission = false
    var cameraPermission = false
    private fun checkPermissions(permissions: Map<String, @JvmSuppressWildcards Boolean>) {
        permissions.forEach{
            actionMap -> when(actionMap.key){
                CAMERA -> { cameraPermission = actionMap.value }
                READ_EXTERNAL_STORAGE ->  { storagePermission = actionMap.value }
            }
        }
    }

    private fun addImage() {
        if((ActivityCompat.checkSelfPermission( requireContext(),colum[0])!= PackageManager.PERMISSION_GRANTED) &&
            (ActivityCompat.checkSelfPermission( requireContext(),colum[1])!= PackageManager.PERMISSION_GRANTED)){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requireActivity().requestPermissions(colum,123)
            }
        }
        else{
            cameraPermission = true
            storagePermission = true
        }
        if(cameraPermission && storagePermission){
            openGallery()
        }

    }

    private fun openGallery() {
        getContent.launch("image/*")
    }


    lateinit var imagesRecyclerView : RecyclerView
    lateinit var adapter : ImagesAdapter
    var imagesList = ArrayList<Image>()
    private fun initRecyclerView() {
        imagesRecyclerView = binding.listImages
        //LinearLayoutManager HORIZONTAL
        //serviceRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL,false)
        imagesRecyclerView.layoutManager = GridLayoutManager(binding.root.context, 2)
        //Asignación del adaptador al recyclerview.

        adapter = ImagesAdapter(imagesList)
        imagesRecyclerView.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewService.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewService().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}