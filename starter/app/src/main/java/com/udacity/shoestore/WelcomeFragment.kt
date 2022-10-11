package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val binding: WelcomeFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.welcome_fragment, container, false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding.next.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeToInstructions())
        }
        return binding.root
    }
}

