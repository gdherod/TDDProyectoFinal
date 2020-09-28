package cl.desafiolatam.tddproyectofinal.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cl.desafiolatam.tddproyectofinal.R
import cl.desafiolatam.tddproyectofinal.model.db.CharacterEntity
import cl.desafiolatam.tddproyectofinal.viewmodel.BBViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*


class CharacterListFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private val bbViewModel: BBViewModel by activityViewModels()
    private var param1: String? = null
    private var param2: String? = null

    val logtag = "CharacterListFragment"
    private var characterList = ArrayList<CharacterEntity>()
    private lateinit var bbAdapter: BBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CharacterListFragment.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
        /*fun newInstance(param1: String, param2: String) =
            CharacterListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bbAdapter = BBAdapter(characterList)
        character_list.setHasFixedSize(true)
        character_list.layoutManager = LinearLayoutManager(context)
        character_list.adapter = bbAdapter

        bbViewModel.characterList.observe(viewLifecycleOwner, {
            bbAdapter.updateCharactersItems(it)
        })

        bbAdapter.characterSelected.observe(viewLifecycleOwner, Observer {
            Log.d(logtag, "Personaje seleccionado $it")
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.mainContainer,
                    CharacterDetailFragment.newInstance(it.char_id.toString(), ""), "A detalles"
                )
                .addToBackStack("A la lista")
                .commit()
        })
    }
}
