package ru.logosph.dungeon_chicken_curry.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.logosph.dungeon_chicken_curry.databinding.FragmentCreateRoom2Binding

class CreateRoom2Fragment : Fragment() {

    private lateinit var binding: FragmentCreateRoom2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateRoom2Binding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }
}