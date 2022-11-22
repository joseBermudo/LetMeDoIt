package cat.copernic.letmedoit.General.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import cat.copernic.letmedoit.General.model.adapter.UserTopMenuAdapter
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.databinding.FragmentPerfilUsuarioMenuSuperiorBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PerfilUsuarioMenuSuperior.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerfilUsuarioMenuSuperior : Fragment() {
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

    lateinit var adapter : FragmentStateAdapter
    lateinit var binding : FragmentPerfilUsuarioMenuSuperiorBinding
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPerfilUsuarioMenuSuperiorBinding.inflate(inflater,container,false)

        if(FirebaseAuth.getInstance().currentUser == null){
            binding.btnFavorites.visibility = View.INVISIBLE
            binding.btnReport.visibility = View.INVISIBLE
        }
        binding.btnFavorites.setOnClickListener {  }
        //Fragmentos del TabLayout
        val fragments : ArrayList<Fragment> = arrayListOf(
            PerfilUsuarioServicios(),
            OpinionsUser(),
            ProfileMoreInfo()
        )
        //Adapter del ViewPager
        adapter = UserTopMenuAdapter(this.childFragmentManager,fragments,lifecycle)
        //Lo asignamos
        binding.viewPager.adapter = adapter

        binding.btnBack.setOnClickListener { requireActivity().onBackPressed() }
        TabLayoutMediator(binding.tablayoutUserProfile, binding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                //Configure tabs..
                when (position) {
                    0 -> { tab.text = "%s \n Services"}
                    1 -> { tab.text = "%s \n Opinions"}
                    2 -> { tab.text = "+ \n info"}
                }
            }).attach()

        val args: PerfilUsuarioMenuSuperiorArgs by navArgs()
        //Evento que se llama al empezar el drawing de la vista. Obtenemos el height del movil, luego el del Menu superior los restamos y lo utilizamos como height del viewpager. luego actualizamos el layout de nuevo
        var viewPagerHeightSize = 0
        binding.viewPager.viewTreeObserver.addOnGlobalLayoutListener( object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.viewPager.viewTreeObserver
                    .removeOnGlobalLayoutListener(this)
                val displayMetrics = DisplayMetrics()
                requireActivity().windowManager.getDefaultDisplay().getMetrics(displayMetrics)
                val heightPhone = displayMetrics.heightPixels

                //val width = displayMetrics.widthPixels
                val heightTopMenu = binding.topMenuUser.measuredHeight
                viewPagerHeightSize = heightPhone-(heightTopMenu)
                binding.viewPager.layoutParams.height = viewPagerHeightSize - 50
                binding.viewPager.requestLayout()
            }

        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnReport.setOnClickListener{ Utils.goToUserReport(view, "1") }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PerfilUsuarioMenuSuperior.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PerfilUsuarioMenuSuperior().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}