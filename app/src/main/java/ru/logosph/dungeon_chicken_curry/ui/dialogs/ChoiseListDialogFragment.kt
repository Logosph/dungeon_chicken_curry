package ru.logosph.dungeon_chicken_curry.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.logosph.dungeon_chicken_curry.databinding.DialogChoiseListBinding

class ChoiseListDialogFragment : DialogFragment()
{

    lateinit var binding: DialogChoiseListBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireContext())
        binding = DialogChoiseListBinding.inflate(layoutInflater)
        builder.setView(binding.root)

        return builder.create()
    }
}