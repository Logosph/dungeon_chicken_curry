package ru.logosph.dungeon_chicken_curry.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.logosph.dungeon_chicken_curry.databinding.FragmentCreateRoom1Binding

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
        
        return binding.root
    }
}