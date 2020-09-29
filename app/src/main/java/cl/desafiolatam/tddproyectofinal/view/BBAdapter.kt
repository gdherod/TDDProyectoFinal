package cl.desafiolatam.tddproyectofinal.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.tddproyectofinal.R
import cl.desafiolatam.tddproyectofinal.model.db.CharacterEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.character_item.view.*

class BBAdapter(private var myDataset: MutableList<CharacterEntity>) :
    RecyclerView.Adapter<BBAdapter.BBHolder>() {

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
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.characterImg)
        holder.characterName.text = character.name
        holder.characterNickname.text = character.nickname
        holder.btnDetails.setOnClickListener {
            characterSelected.value = myDataset[position]
        }
        holder.btnAddFav.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    class BBHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var characterName = itemView.character_name!!
        var characterNickname = itemView.character_nickname!!
        var characterImg = itemView.character_img!!
        var btnAddFav = itemView.action_btn_addfavorites!!
        var btnDetails = itemView.action_btn_details!!
    }

    fun updateCharactersItems(it: List<CharacterEntity>) {
        myDataset.clear()
        myDataset.addAll(it)
        notifyDataSetChanged()
    }
}
