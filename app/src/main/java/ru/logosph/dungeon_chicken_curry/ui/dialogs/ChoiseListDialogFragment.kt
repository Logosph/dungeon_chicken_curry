package ru.logosph.dungeon_chicken_curry.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.logosph.dungeon_chicken_curry.databinding.DialogChoiseListBinding
import ru.logosph.dungeon_chicken_curry.ui.fragments.create_room_1.CreateRoom1Adapter
import ru.logosph.dungeon_chicken_curry.ui.fragments.create_room_2.CreateRoom2Adapter

class ChoiseListDialogFragment : DialogFragment()
{

    lateinit var binding: DialogChoiseListBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireContext())
        binding = DialogChoiseListBinding.inflate(layoutInflater)
        builder.setView(binding.root)

        with(binding) {
            val adapter = CreateRoom1Adapter()
            listRecyclerView.adapter = adapter
            listRecyclerView.layoutManager = CarouselLayoutManager()
            val snapHelper = CarouselSnapHelper()
            snapHelper.attachToRecyclerView(listRecyclerView)
        }

        return builder.create()
    }
}