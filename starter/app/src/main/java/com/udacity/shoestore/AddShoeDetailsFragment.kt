package com.udacity.shoestore

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.AddShoedetailsFragmentBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.add_shoedetails_fragment.view.*

class AddShoeDetailsFragment : Fragment() {

    private lateinit var binding: AddShoedetailsFragmentBinding
    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_shoedetails_fragment, container, false)

        initViewModel()
        initObservers()

        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearShoeTemplate()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        binding.detailShoeDetailViewModel = viewModel
        binding.shoe = viewModel.shoe
    }

    private fun initObservers() {
        viewModel.eventSaveShoeDetailPress.observe(viewLifecycleOwner, {
            if (it) {
                saveShoeDetail()
                viewModel.saveShoeDetailComplete()
            }
        })

        viewModel.eventCancelShoeDetailPress.observe(viewLifecycleOwner, {
            if (it) {
                cancelShoeDetail()
                viewModel.cancelShoeDetailComplete()
            }
        })




        viewModel.eventSaveFailByNameShoeDetail.observe(viewLifecycleOwner, {
            if (it) {
                val message = "The name of the shoe is required"
                showAlert(message)
                viewModel.saveFailByNameShoeDetailComplete()
            }
        })

        viewModel.eventSaveFailBySizeShoeDetail.observe(viewLifecycleOwner, {
            if (it) {
                val message = "The shoe's size is required"
                showAlert(message)
                viewModel.saveFailBySizeShoeDetailComplete()
            }
        })

        viewModel.eventSaveFailByNameCompanyShoeDetail
            .observe(viewLifecycleOwner, {
                if (it) {
                    val message = "The name of the company is required"
                    showAlert(message)
                    viewModel.saveFailByNameCompanyShoeDetailComplete()
                }
            })
    }

    private fun saveShoeDetail() {
        findNavController().popBackStack()
    }

    private fun cancelShoeDetail() {
        findNavController().popBackStack()
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(context)

        with(builder)
        {
            setTitle("Error")
            setMessage(message)
            setPositiveButton("OK", null)
            show()
        }
    }
}