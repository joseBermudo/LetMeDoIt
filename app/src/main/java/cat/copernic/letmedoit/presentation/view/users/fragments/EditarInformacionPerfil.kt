package cat.copernic.letmedoit.presentation.view.users.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.Utils.datahepers.ContactInfoMap
import cat.copernic.letmedoit.Utils.datahepers.ScheduleMap
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.FragmentEditarInformacionPerfilBinding
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class EditarInformacionPerfil : Fragment() {


    lateinit var user : Users
    val args : EditarInformacionPerfilArgs by navArgs()
    lateinit var getContent : ActivityResultLauncher<String>
    lateinit var openDocument : ActivityResultLauncher<Array<String>>
    lateinit var requestLocationPermission : ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Image
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri -> managePhotoUri(uri)}
        //Document
        openDocument = registerForActivityResult(ActivityResultContracts.OpenDocument()) {uri -> manageDocumentUri(uri) }
        //Maps
        requestLocationPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){requestPermissionsResult(it)}

    }

    val userViewModel : UserViewModel by viewModels()
    lateinit var binding : FragmentEditarInformacionPerfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEditarInformacionPerfilBinding.inflate(inflater,container,false)

        initView()
        initListeners()
        initObservers()

        return binding.root
    }

    private fun initView() {
        user =  args.user

        if(user.avatar != "") Picasso.get().load(user.avatar).into(binding.profileImage)
        binding.nameSurname.text = "${user.name} ${user.surname} \n @${user.username} \n"
        binding.aboutMeText.text = user.aboutMe
        binding.scheduleText.text = "${user.schedule?.initHour} - ${user.schedule?.endHour}"
        binding.locationText.text = user.location
    }
    private fun initListeners() {
        binding.backArrow.setOnClickListener{requireActivity().onBackPressed()}
        binding.btnEditProfileImage.setOnClickListener  { editProfileImage()    }
        binding.btnEditNameSurname.setOnClickListener   { editNameSurname()     }
        binding.btnEditAboutMe.setOnClickListener       { editAboutMe()         }
        binding.btnEditCurriculum.setOnClickListener    { editCurriculum()      }
        binding.btnEditSchedule.setOnClickListener      { editSchedule()        }
        binding.btnEditContactInfo.setOnClickListener   { editContactInfo()     }
        binding.btnEditLocation.setOnClickListener      { editLocation()        }
        binding.btnEditPassword.setOnClickListener      { editPassword()        }

        binding.btnPdf.setOnClickListener{ user.curriculum?.let { it1 -> openPDF(it1) } }
        binding.btnEmail.setOnClickListener { user.contactInfo?.let { it1 -> sendEmail(it1.email) } }
        binding.btnMobile.setOnClickListener { user.contactInfo?.let { it1 -> callUser(it1.phone) } }
        binding.btnLocationIcon.setOnClickListener { user.location?.let { it1 -> openMaps(it1) } }
    }



    private fun initObservers(){
        userViewModel.deleteAvatarFromStorageState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {

                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}",requireContext(),dataState.exception.message.toString(),requireActivity())
                    hideImageProgressBar()
                }
                is DataState.Loading -> { showImageProgressBar() }
                else -> Unit
            }
        })

        userViewModel.addAvatarToStorageState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<String> -> {
                    userViewModel.updateAvatar(dataState.data)
                    user.avatar = dataState.data
                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}",requireContext(),dataState.exception.message.toString(),requireActivity())
                    hideImageProgressBar()
                }
                is DataState.Loading -> { showImageProgressBar() }
                else -> Unit
            }
        })

        userViewModel.updateAvatarState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    if(dataState.data)
                        if(user.avatar != "") Picasso.get().load(user.avatar).into(binding.profileImage)
                    hideImageProgressBar()
                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}",requireContext(),dataState.exception.message.toString(),requireActivity())
                    hideImageProgressBar()
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        })
        userViewModel.updateAboutMeState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    dialog.dismiss()
                    binding.aboutMeText.text = user.aboutMe
                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}",requireContext(),dataState.exception.message.toString(),requireActivity())

                    dialogBinding.findViewById<ProgressBar>(R.id.progress).isVisible = false
                    val btn = dialogBinding.findViewById<Button>(R.id.btn_done)
                    btn.isEnabled = true
                    btn.text = resources.getText(R.string.done)
                }
                is DataState.Loading -> {
                    dialogBinding.findViewById<ProgressBar>(R.id.progress).isVisible = true
                    val btn = dialogBinding.findViewById<Button>(R.id.btn_done)
                    btn.isEnabled = false
                    btn.text = ""
                }
                else -> Unit
            }
        })
        userViewModel.deleteCurriculumFromStorageState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    user.curriculum = ""
                    binding.btnEditCurriculum.isEnabled = true
                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}",requireContext(),dataState.exception.message.toString(),requireActivity())
                    binding.btnEditCurriculum.isEnabled = true
                    binding.btnPdf.isVisible = true
                    binding.curriculumLoading.isVisible = false
                }
                is DataState.Loading -> {binding.btnEditCurriculum.isEnabled = false}
                else -> Unit
            }
        })
        userViewModel.addCurriculumToStorageState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<String> -> {
                    userViewModel.updateCurriculum(dataState.data)
                    user.curriculum = dataState.data
                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}",requireContext(),dataState.exception.message.toString(),requireActivity())
                    binding.btnEditCurriculum.isEnabled = true
                    binding.btnPdf.isVisible = true
                    binding.curriculumLoading.isVisible = false
                }
                is DataState.Loading -> {binding.btnEditCurriculum.isEnabled = false}
                else -> Unit
            }
        })
        userViewModel.updateCurriculumState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    binding.btnPdf.isVisible = true
                    binding.curriculumLoading.isVisible = false
                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}",requireContext(),dataState.exception.message.toString(),requireActivity())
                    binding.btnEditCurriculum.isEnabled = true
                    binding.btnPdf.isVisible = true
                    binding.curriculumLoading.isVisible = false
                }
                is DataState.Loading -> {
                    binding.btnPdf.isVisible = false
                    binding.curriculumLoading.isVisible = true
                }
                else -> Unit
            }
        })
        userViewModel.updateScheduleState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    dialog.dismiss()
                    binding.scheduleText.text = "${user.schedule?.initHour} - ${user.schedule?.endHour}"
                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}",requireContext(),dataState.exception.message.toString(),requireActivity())
                    dialogBinding.findViewById<ProgressBar>(R.id.progress).isVisible = false

                    val btn = dialogBinding.findViewById<Button>(R.id.btn_done)
                    btn.isEnabled = true
                    btn.text = resources.getText(R.string.done)
                }
                is DataState.Loading -> {
                    dialogBinding.findViewById<ProgressBar>(R.id.progress).isVisible = true
                    val btn = dialogBinding.findViewById<Button>(R.id.btn_done)
                    btn.isEnabled = false
                    btn.text = ""
                }
                else -> Unit
            }
        })
        userViewModel.updateContactInfoState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    dialog.dismiss()
                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}",requireContext(),dataState.exception.message.toString(),requireActivity())
                    dialogBinding.findViewById<ProgressBar>(R.id.progress).isVisible = false

                    val btn = dialogBinding.findViewById<Button>(R.id.btn_done)
                    btn.isEnabled = true
                    btn.text = resources.getText(R.string.done)
                }
                is DataState.Loading -> {
                    dialogBinding.findViewById<ProgressBar>(R.id.progress).isVisible = true
                    val btn = dialogBinding.findViewById<Button>(R.id.btn_done)
                    btn.isEnabled = false
                    btn.text = ""
                }
                else -> Unit
            }
        })
        userViewModel.updateLocationState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    dialog.dismiss()
                    binding.locationText.text = user.location
                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}",requireContext(),dataState.exception.message.toString(),requireActivity())
                    dialogBinding.findViewById<ProgressBar>(R.id.progress).isVisible = false

                    val btn = dialogBinding.findViewById<Button>(R.id.btn_done)
                    btn.isEnabled = true
                    btn.text = resources.getText(R.string.done)
                }
                is DataState.Loading -> {
                    dialogBinding.findViewById<ProgressBar>(R.id.progress).isVisible = true
                    val btn = dialogBinding.findViewById<Button>(R.id.btn_done)
                    btn.isEnabled = false
                    btn.text = ""
                }
                else -> Unit
            }
        })
        userViewModel.updateSurnameState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    dialog.dismiss()
                    binding.nameSurname.text = "${user.name} ${user.surname} \n @${user.username}"
                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}",requireContext(),dataState.exception.message.toString(),requireActivity())
                    dialogBinding.findViewById<ProgressBar>(R.id.progress).isVisible = false

                    val btn = dialogBinding.findViewById<Button>(R.id.btn_done)
                    btn.isEnabled = true
                    btn.text = resources.getText(R.string.done)
                }
                is DataState.Loading -> {
                    dialogBinding.findViewById<ProgressBar>(R.id.progress).isVisible = true
                    val btn = dialogBinding.findViewById<Button>(R.id.btn_done)
                    btn.isEnabled = false
                    btn.text = ""
                }
                else -> Unit
            }
        })
        userViewModel.updatePasswordState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    dialog.dismiss()
                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}",requireContext(),dataState.exception.message.toString(),requireActivity())
                    dialogBinding.findViewById<ProgressBar>(R.id.progress).isVisible = false

                    val btn = dialogBinding.findViewById<Button>(R.id.btn_done)
                    btn.isEnabled = true
                    btn.text = resources.getText(R.string.done)
                }
                is DataState.Loading -> {
                    dialogBinding.findViewById<ProgressBar>(R.id.progress).isVisible = true
                    val btn = dialogBinding.findViewById<Button>(R.id.btn_done)
                    btn.isEnabled = false
                    btn.text = ""
                }
                else -> Unit
            }
        })
    }

    private fun hideImageProgressBar() {
        binding.btnEditProfileImage.isEnabled = true
        binding.profileImage.alpha = 1.0f
        binding.loginLoading.isVisible = false
    }

    private fun showImageProgressBar() {
        binding.btnEditProfileImage.isEnabled = false
        binding.profileImage.alpha = 0.0f
        binding.loginLoading.isVisible = true
    }

    lateinit var dialogBinding: View
    lateinit var dialog : Dialog
    lateinit var googleMap : GoogleMap
    lateinit var mapView : MapView
    var markers = ArrayList<Marker>()
    lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    private fun editLocation() {
        dialogBinding = layoutInflater.inflate(R.layout.dialog_location,null)
        dialog = Dialog(binding.root.context)
        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)


        dialogBinding.findViewById<Button>(R.id.btn_cancel).setOnClickListener { dialog.dismiss() }

        val locationText = dialogBinding.findViewById<TextView>(R.id.textLocation)
        dialogBinding.findViewById<Button>(R.id.btn_search).setOnClickListener { searchLocation(locationText.text.toString()) }
        dialogBinding.findViewById<Button>(R.id.btn_done).setOnClickListener {
            val textLocation = locationText.text.toString()
            userViewModel.updateLocation(textLocation)
            user.location = textLocation
        }

        locationText.text = user.location

        mapView = dialogBinding.findViewById<MapView>(R.id.mapView)

        MapsInitializer.initialize(requireActivity())
        mapView.onCreate(dialog.onSaveInstanceState())
        mapView.onResume()

        val geoCoder = Geocoder(requireContext())

        mapView.getMapAsync {
            googleMap = it
            if(checkPermission())
                it.isMyLocationEnabled = true
            else
                askPermission()

            it.setOnMyLocationButtonClickListener{

                val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    val address = geoCoder.getFromLocation(location.latitude,location.longitude,1)[0]
                    locationText.text = "${address.getAddressLine(0)}"
                }
                false
            }

            it.setOnMapClickListener { location ->
                val address = geoCoder.getFromLocation(location.latitude,location.longitude,1)[0]

                markers.forEach { it.remove() }

                val marker = googleMap.addMarker(MarkerOptions().position(location))

                if (marker != null) {
                    markers.add(marker)
                }

                locationText.text = "${address.getAddressLine(0)}"
            }
        }
        dialog.show()
    }

    private fun searchLocation(text: String) {
        if(text.isEmpty()) return

        markers.forEach { it.remove() }
        val geoCoder = Geocoder(requireContext())
        val address = geoCoder.getFromLocationName(text,1)[0]

        val latLng = LatLng(address.latitude,address.longitude)
        val marker = googleMap.addMarker(MarkerOptions().position(latLng).title(text))
        if (marker != null) {
            markers.add(marker)
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18f));

    }

    private fun askPermission() {
        dialog.dismiss()
        requestLocationPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    @SuppressLint("MissingPermission")
    private fun requestPermissionsResult(accepted: Boolean) {
        if(accepted){
            googleMap.isMyLocationEnabled = true
            dialog.show()
        }
    }

    private fun checkPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED )
    }

    private fun editContactInfo() {
        dialogBinding = layoutInflater.inflate(R.layout.dialog_contact_info,null)

        dialogBinding.findViewById<TextInputEditText>(R.id.textEmail).setText(user.contactInfo?.email
            ?: "")
        dialogBinding.findViewById<TextInputEditText>(R.id.textPhoneNumber).setText(user.contactInfo?.phone
            ?: "")

        dialog = Dialog(binding.root.context)
        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)
        dialog.show()

        dialogBinding.findViewById<Button>(R.id.btn_done).setOnClickListener{
            val textEmail = dialogBinding.findViewById<TextInputEditText>(R.id.textEmail).text.toString()
            val textPhone = dialogBinding.findViewById<TextInputEditText>(R.id.textPhoneNumber).text.toString()

            if(textPhone.length < 9 || textPhone.length>12){
                Utils.showOkDialog("${resources.getString(R.string.error)}", requireContext(),resources.getString(R.string.invalidPhone),requireActivity())
                return@setOnClickListener
            }
            val contactInfo = ContactInfoMap(textEmail,textPhone)
            userViewModel.updateContactInfo(contactInfo)
            user.contactInfo = contactInfo

        }
        dialogBinding.findViewById<Button>(R.id.btn_cancel).setOnClickListener { dialog.dismiss() }
    }

    private fun editSchedule() {
        dialogBinding = layoutInflater.inflate(R.layout.dialog_schedule,null)

        dialogBinding.findViewById<TextInputEditText>(R.id.textInitHour).setText(user.schedule?.initHour
            ?: "")
        dialogBinding.findViewById<TextInputEditText>(R.id.textEndHour).setText(user.schedule?.endHour
            ?: "")

        dialog = Dialog(binding.root.context)
        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)
        dialog.show()

        dialogBinding.findViewById<Button>(R.id.btn_done).setOnClickListener{
            val textInitHour = dialogBinding.findViewById<TextInputEditText>(R.id.textInitHour).text.toString()
            val textEndHour = dialogBinding.findViewById<TextInputEditText>(R.id.textEndHour).text.toString()

            val schedule = ScheduleMap(textInitHour,textEndHour)
            userViewModel.updateSchedule(schedule)
            user.schedule = schedule

        }
        dialogBinding.findViewById<Button>(R.id.btn_cancel).setOnClickListener { dialog.dismiss() }

    }

    private fun editPassword() {
        dialogBinding = layoutInflater.inflate(R.layout.dialog_edit_password,null)

        dialog = Dialog(binding.root.context)
        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)
        dialog.show()

        dialogBinding.findViewById<Button>(R.id.btn_done).setOnClickListener{
            val textEmail = dialogBinding.findViewById<TextInputEditText>(R.id.textEmail).text.toString()
            val textOldPassword = dialogBinding.findViewById<TextInputEditText>(R.id.editOldPassword).text.toString()
            val textNewPassword = dialogBinding.findViewById<TextInputEditText>(R.id.editNewPassword).text.toString()

            userViewModel.updatePassword(textOldPassword,textNewPassword,textEmail)

        }
        dialogBinding.findViewById<Button>(R.id.btn_cancel).setOnClickListener { dialog.dismiss() }
    }

    private fun manageDocumentUri(uri: Uri?) {
        if (uri == null)
            return

        if(user.curriculum != "")
            userViewModel.deleteCurriculumFromStorage(user.curriculum)

        userViewModel.addCurriculumToStorage(uri)
    }

    private fun editCurriculum() {
        openDocument.launch(arrayOf("application/pdf"))
    }

    private fun editAboutMe() {
        dialogBinding = layoutInflater.inflate(R.layout.dialog_about_me,null)
        dialogBinding.findViewById<TextInputEditText>(R.id.textAboutMe).setText(user.aboutMe)

        dialog = Dialog(binding.root.context)
        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)
        dialog.show()

        dialogBinding.findViewById<Button>(R.id.btn_done).setOnClickListener{
            val aboutMeText = dialogBinding.findViewById<TextInputEditText>(R.id.textAboutMe).text.toString()
            userViewModel.updateAboutMe(aboutMeText)
            user.aboutMe = aboutMeText
        }
        dialogBinding.findViewById<Button>(R.id.btn_cancel).setOnClickListener { dialog.dismiss() }
    }

    private fun editNameSurname() {
        dialogBinding = layoutInflater.inflate(R.layout.dialog_name_surname,null)

        dialogBinding.findViewById<TextInputEditText>(R.id.textName).setText(user.name
            ?: "")
        dialogBinding.findViewById<TextInputEditText>(R.id.textSurname).setText(user.surname
            ?: "")

        dialog = Dialog(binding.root.context)
        dialog.setContentView(dialogBinding)
        dialog.setCancelable(true)
        dialog.show()

        dialogBinding.findViewById<Button>(R.id.btn_done).setOnClickListener{
            val textName = dialogBinding.findViewById<TextInputEditText>(R.id.textName).text.toString()
            val textSurname = dialogBinding.findViewById<TextInputEditText>(R.id.textSurname).text.toString()


            userViewModel.updateName(textName)
            userViewModel.updateSurname(textSurname)

            user.name = textName
            user.surname = textSurname

        }
        dialogBinding.findViewById<Button>(R.id.btn_cancel).setOnClickListener { dialog.dismiss() }
    }

    private fun managePhotoUri(uri: Uri?) {
        if (uri == null)
            return

        if(user.avatar != "")
            userViewModel.deleteAvatarFromStorage(user.avatar!!)

        userViewModel.addAvatarToStorage(uri)
    }

    private fun editProfileImage() {
        getContent.launch("image/*")
    }


    private fun openPDF( url : String){

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)


        startActivity(browserIntent)
    }
    private fun sendEmail(address : String){

        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:${address}") // only email apps should handle this

        startActivity(Intent.createChooser(intent,
            "Send Email Using: "))
    }
    private fun callUser(telf : String){
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:${telf}")
        startActivity(
            Intent.createChooser(dialIntent,
            "Call Using: "))
    }
    private fun openMaps(location : String){
        val gmmIntentUri =  Uri.parse("geo:0,0?q=${location}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(
            Intent.createChooser(mapIntent,
            "Show Map Using: "))
    }

    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        if(!checkPermission()){
            if(::dialog.isInitialized){
                dialog.dismiss()
                googleMap.isMyLocationEnabled = false
                dialog.show()
                askPermission()
            }

        }
    }
}