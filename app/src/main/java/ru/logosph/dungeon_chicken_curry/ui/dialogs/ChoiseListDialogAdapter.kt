package ru.logosph.dungeon_chicken_curry.ui.dialogs

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.logosph.dungeon_chicken_curry.R
import ru.logosph.dungeon_chicken_curry.databinding.ItemForListBinding
import ru.logosph.dungeon_chicken_curry.domain.local_data.RaceAndClassModel


class ChoiseListDialogAdapter(
    private val data: MutableList<RaceAndClassModel> = mutableListOf(),
) : RecyclerView.Adapter<ChoiseListDialogAdapter.ViewHolder>() {


    private var selectedItems = mutableSetOf<Int>()
    lateinit var onItemClickListener: (Int) -> Unit

    class ViewHolder(val binding: ItemForListBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateData(data: List<RaceAndClassModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemForListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding) {
            listImageView.setImageBitmap(data[position].image)
            listNameView.text = data[position].name
            holder.itemView.setOnClickListener {
                onItemClickListener(position)

                if (selectedItems.contains(position)) {
                    selectedItems.remove(position)
                    root.backgroundTintList = ColorStateList(
                        arrayOf(IntArray(1) {0}),
                        intArrayOf(holder.itemView.resources.getColor(R.color.md_theme_surface))
                    )
                } else {
                    selectedItems.add(position)
                    root.backgroundTintList = ColorStateList(
                        arrayOf(IntArray(1) {0}),
                        intArrayOf(holder.itemView.resources.getColor(R.color.md_theme_surfaceContainerHigh))
                    )
                }
            }
        }
    }
}


