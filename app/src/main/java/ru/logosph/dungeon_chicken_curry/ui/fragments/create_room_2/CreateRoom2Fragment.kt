package ru.logosph.dungeon_chicken_curry.ui.fragments.create_room_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
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

        with(binding) {
            val adapter = CreateRoom2Adapter()
            carouselRecyclerView.adapter = adapter
            carouselRecyclerView.layoutManager = CarouselLayoutManager()
            val snapHelper = CarouselSnapHelper()
            snapHelper.attachToRecyclerView(carouselRecyclerView)
        }



        return binding.root
    }
}