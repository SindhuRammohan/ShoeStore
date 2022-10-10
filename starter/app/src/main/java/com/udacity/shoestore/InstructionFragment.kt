package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.InstructionFragmentBinding

class InstructionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: InstructionFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.instruction_fragment, container, false
        )

        binding.viewShoelist.setOnClickListener {
            findNavController().navigate(InstructionFragmentDirections.actionInstructiontoshoeList())
        }
        return binding.root
    }
}