package ru.logosph.dungeon_chicken_curry.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.logosph.dungeon_chicken_curry.R
import ru.logosph.dungeon_chicken_curry.databinding.DialogChoiseListBinding

class ChoiseListDialogFragment : DialogFragment() {

    lateinit var binding: DialogChoiseListBinding
    lateinit var viewModel: ChooseListViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireContext())
        binding = DialogChoiseListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ChooseListViewModel::class.java]
        builder.setView(binding.root)

        // TODO: Change carousel LM to Grid LM to Yulia
        with(binding) {
            val adapter = ChoiseListDialogAdapter()
            listRecyclerView.adapter = adapter
            listRecyclerView.layoutManager = GridLayoutManager(context, 2)


            toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->

                when (checkedId) {
                    R.id.list_button -> {
                        chipsFlexViewGroup.visibility = android.view.View.GONE
                        listRecyclerView.visibility = android.view.View.VISIBLE
                    }

                    R.id.chips_button -> {
                        listRecyclerView.visibility = android.view.View.GONE
                        chipsFlexViewGroup.visibility = android.view.View.VISIBLE
                    }
                }
            }

        }

        return builder.create()
    }


}