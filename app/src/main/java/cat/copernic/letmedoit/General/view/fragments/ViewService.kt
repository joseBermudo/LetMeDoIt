package cat.copernic.letmedoit.General.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import cat.copernic.letmedoit.General.model.Image
import cat.copernic.letmedoit.General.model.Service
import cat.copernic.letmedoit.General.model.ServiceProvider
import cat.copernic.letmedoit.General.model.adapter.SERVICE_ID
import cat.copernic.letmedoit.General.model.adapter.SliderImagesAdapter
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.Utils.Companion.goToDestination
import cat.copernic.letmedoit.databinding.FragmentViewServiceBinding

const val TAG_SLIDER_IMAGES = "sliderCardView"
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [viewService.newInstance] factory method to
 * create an instance of this fragment.
 */
class viewService : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var adapter : SliderImagesAdapter
    lateinit var binding : FragmentViewServiceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentViewServiceBinding.inflate(inflater,container,false)

        //Volver hacia atras
        binding.btnBack.setOnClickListener { requireActivity().onBackPressed() }

        initView(arguments?.getString(SERVICE_ID).toString())

        binding.btnGoToProfile.setOnClickListener { goToDestination(requireView(),R.id.viewServiceToUserProfile) }
        return binding.root
    }

    lateinit var service : Service
    private fun initView(id: String) {
        if (id == null)
            throw Exception("No hay ID")

        service = ServiceProvider.getServices().filter { it.id == id }[0]
        binding.tittleService.text = service.title
        binding.subTextCategory.text = service.category.id_category
        binding.descriptionService.text = service.description
        binding.txtCountFav.text = service.n_likes.toString()

        createSliderDots(service.image)

        adapter = SliderImagesAdapter(service.image)


        //Eventos del ViewPager de imagenes
        binding.imageServiceViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            var sumPosAndOffset : Float = 0.0f
            var swiped = true
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                var rePos = position

                if (swiped){
                    //position + offset > sumPositios --> Swipe Izquierda a Derecha y Viceversa
                    if (position + positionOffset > sumPosAndOffset)
                        rePos++

                    changeColor(rePos)

                    swiped = false

                }
                super.onPageScrolled(rePos, positionOffset, positionOffsetPixels)
                sumPosAndOffset = positionOffset + position
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                swiped = true
            }

        })

        binding.imageServiceViewPager.adapter = adapter
    }

    //Por cada foto creamos un punto gris debajo de la imagen utilizando cardviews
    private fun createSliderDots(images: ArrayList<Image>) {
        var contador = 0
        images.forEach { _ ->
            contador++
            val cardViewDotContainer = CardView(requireContext())
            val layoutparams = LinearLayout.LayoutParams(20,20)
            layoutparams.setMargins(10)
            cardViewDotContainer.layoutParams = layoutparams
            cardViewDotContainer.radius = 20F
            cardViewDotContainer.elevation = 2F
            cardViewDotContainer.contentDescription = TAG_SLIDER_IMAGES
            cardViewDotContainer.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.divider_color))
            binding.SliderDots.addView(cardViewDotContainer)
        }
    }


    //Cambiamos el color del CardView a azul o a gris
    private var lastColored : CardView? = null
    fun changeColor(position: Int) {

        val outputCardViews = ArrayList<View>()
        binding.root.findViewsWithText(outputCardViews, TAG_SLIDER_IMAGES,View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION)

        lastColored?.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.secundario_gris))
        (outputCardViews[position] as CardView).setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.principal_blanco))

        lastColored = outputCardViews[position] as CardView

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment viewService.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            viewService().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}