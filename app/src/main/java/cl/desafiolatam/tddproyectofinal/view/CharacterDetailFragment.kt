package cl.desafiolatam.tddproyectofinal.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import cl.desafiolatam.tddproyectofinal.R
import cl.desafiolatam.tddproyectofinal.viewmodel.BBViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.fragment_character_detail.*
import kotlinx.android.synthetic.main.fragment_character_detail.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CharacterDetailFragment : Fragment() {

    private val bbViewModel: BBViewModel by activityViewModels()
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Parametro 1", param1!!)
        bbViewModel.getCharacterDetail(param1!!).observe(viewLifecycleOwner, {
            Log.d("Observando Parámetro 1", it.toString())
        })
        val view = inflater.inflate(R.layout.fragment_character_detail, container, false)
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bbViewModel.getCharacterDetail(param1!!).observe(viewLifecycleOwner, {
            character_detail_name.text = it.name
            character_detail_birthday.text = it.birthday
            character_detail_occupation.text = it.occupation.toString()
            character_detail_status.text = it.status
            character_detail_nickname.text = it.nickname
            character_detail_portrayed.text = it.portrayed
            character_detail_appearances.text = it.category
            Glide.with(view.context)
                .load(it.img)
                .override(1080)
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(character_detail_img)
        })
    }
}