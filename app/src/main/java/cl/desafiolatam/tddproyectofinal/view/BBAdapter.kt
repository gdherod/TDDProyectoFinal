package cl.desafiolatam.tddproyectofinal.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.tddproyectofinal.R
import cl.desafiolatam.tddproyectofinal.model.db.CharacterEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.character_item.view.*

class BBAdapter(private var myDataset: MutableList<CharacterEntity>) :
    RecyclerView.Adapter<BBAdapter.BBHolder>() {

    val tag = "Adapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BBHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item, parent, false)

        return BBHolder(view)
    }

    val characterSelected = MutableLiveData<CharacterEntity>()

    override fun onBindViewHolder(holder: BBHolder, position: Int) {
        val character = myDataset[position]
        Glide.with(holder.itemView.context)
            .load(character.img)
            .into(holder.character_img)
        holder.character_name.text = character.name
        holder.itemView.setOnClickListener {
            //Log.d(tag, "${myDataset.get(position)}")
            characterSelected.value = myDataset.get(position)
        }
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    class BBHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var character_name = itemView.character_name
        var character_img = itemView.character_img
    }

    fun updateCharactersItems(it: List<CharacterEntity>) {
        myDataset.clear()
        myDataset.addAll(it)
        notifyDataSetChanged()
    }
}
