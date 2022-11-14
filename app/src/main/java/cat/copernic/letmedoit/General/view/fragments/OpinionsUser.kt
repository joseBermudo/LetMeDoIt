package cat.copernic.letmedoit.General.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.provider.OpinionsProvider
import cat.copernic.letmedoit.General.model.adapter.OpinionsAdapter
import cat.copernic.letmedoit.databinding.FragmentOpinionsUserBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OpinionsUser.newInstance] factory method to
 * create an instance of this fragment.
 */
class OpinionsUser : Fragment() {
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

    lateinit var opinionsRecyclerView : RecyclerView
    lateinit var adapter : OpinionsAdapter
    lateinit var binding : FragmentOpinionsUserBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOpinionsUserBinding.inflate(layoutInflater,container,false)
        inicializarRecyclerView()
        return binding.root
    }

    private fun inicializarRecyclerView() {

        opinionsRecyclerView = binding.RecyclerOpinions
        //LinearLayoutManager HORIZONTAL
        //serviceRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL,false)
        opinionsRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL,false)
        //Asignación del adaptador al recyclerview.

        opinionsRecyclerView.setHasFixedSize(true)
        adapter = OpinionsAdapter(OpinionsProvider.getOpinions())
        opinionsRecyclerView.adapter = adapter

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment opinions_user.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OpinionsUser().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}