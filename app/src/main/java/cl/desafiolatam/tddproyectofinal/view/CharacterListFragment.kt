package cl.desafiolatam.tddproyectofinal.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cl.desafiolatam.tddproyectofinal.R
import cl.desafiolatam.tddproyectofinal.model.db.CharacterEntity
import cl.desafiolatam.tddproyectofinal.viewmodel.BBViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.android.synthetic.main.fragment_character_list.view.*


class CharacterListFragment : Fragment() {

    private val bbViewModel: BBViewModel by activityViewModels()
    private var characterList = ArrayList<CharacterEntity>()
    private lateinit var bbAdapter: BBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_list, container, false)
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
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

        bbAdapter.characterSelected.observe(viewLifecycleOwner, {
            Log.d("List Fragment", "Personaje seleccionado $it")
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.mainContainer,
                    CharacterDetailFragment.newInstance(it.char_id.toString(), ""), "A detalles"
                )
                .addToBackStack("Paso atrás")
                .commit()
        })
    }
}
