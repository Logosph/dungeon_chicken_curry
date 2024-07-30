package ru.logosph.dungeon_chicken_curry.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.logosph.dungeon_chicken_curry.R
import ru.logosph.dungeon_chicken_curry.databinding.FragmentCreateRoom1Binding
import ru.logosph.dungeon_chicken_curry.ui.dialogs.ChoiseListDialogFragment

class CreateRoom1Fragment : Fragment() {
    
    private lateinit var binding: FragmentCreateRoom1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        binding = FragmentCreateRoom1Binding.inflate(
            inflater,
            container,
            false
        )
        binding.titleClasses.setOnClickListener{
            val dialog = ChoiseListDialogFragment()
            dialog.show(parentFragmentManager, "gavna")
        }

        binding.nextStage.setOnClickListener {
            findNavController().navigate(R.id.action_createRoom1Fragment_to_createRoom2Fragment)
        }

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}