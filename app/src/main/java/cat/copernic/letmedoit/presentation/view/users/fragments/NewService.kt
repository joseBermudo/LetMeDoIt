package cat.copernic.letmedoit.presentation.view.users.fragments

import android.Manifest
import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.CategoryMap
import cat.copernic.letmedoit.data.model.Image
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.data.provider.CategoryProvider
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.databinding.FragmentNewServiceBinding
import cat.copernic.letmedoit.presentation.adapter.general.ImagesAdapter
import cat.copernic.letmedoit.presentation.viewmodel.general.ServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
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

    private val serviceViewModel : ServiceViewModel by viewModels()

    lateinit var getContent : ActivityResultLauncher<String>
    lateinit var binding : FragmentNewServiceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewServiceBinding.inflate(inflater,container,false)
        binding.btnAddImage.setOnClickListener { addImage() }
        binding.btnRemoveImage.setOnClickListener { removeImage() }
        binding.selectAll.setOnClickListener { selectAll() }
        binding.btnSave.setOnClickListener{ saveService() }
        
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }
    private fun initObservers() {
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
        adapter.getItems().forEach {
            serviceViewModel.saveImage(requireActivity(),it.img_link.toUri(),idService,this,index)
            index++
        }
    }

    lateinit var idService : String

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

        idService = UUID.randomUUID().toString()
        saveImages()
        showProgress()

    }

    var numImgUploaded = 0
    var imagesUploaded = ArrayList<Image>()
    fun uploadImageSuccess(uri : String){
        Log.d("NewService", "uploadImageSuccess: Image Uploaded")

        imagesUploaded.add(Image(numImgUploaded.toString(),uri))
        numImgUploaded++
        if(numImgUploaded >= adapter.itemCount)
            uploadService()

    }
    private fun uploadService(){
        serviceViewModel.saveService(
            Service(
                id = idService,
                title = binding.editServiceTitle.text.toString(),
                description = binding.editDescription.text.toString(),
                category = CategoryMap(binding.spinnerCategory.selectedItem.toString(),binding.spinnerSubcategory.selectedItem.toString()),
                image = imagesUploaded,
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
            imagesList.add(Image(num.toString(),it.toString(),false))
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