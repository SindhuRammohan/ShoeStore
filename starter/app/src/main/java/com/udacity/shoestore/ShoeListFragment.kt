package com.udacity.shoestore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.databinding.ShoelistFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var binding: ShoelistFragmentBinding
    private lateinit var viewModel: ShoeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackPressedConfiguration()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoelist_fragment, container, false)

        initViewModel()
        initObservers()

        (activity as AppCompatActivity).supportActionBar?.show()
//        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.actionShoeListingsToShoeDetails)
        return super.onOptionsItemSelected(item)
    }

    private fun setBackPressedConfiguration() {
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val i = Intent()
                i.action = Intent.ACTION_MAIN
                i.addCategory(Intent.CATEGORY_HOME)
                startActivity(i)
            }
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        binding.listShareViewModel = viewModel
    }

    private fun initObservers() {
        viewModel.eventAddShoeListPress.observe(viewLifecycleOwner, {
            if (it) {
                goToShoeList()
                viewModel.goToShoeDetailStartComplete()
            }
        })

        viewModel.shoesList.observe(viewLifecycleOwner, { listShoes ->
            initShoeList(listShoes)
        })
    }

    private fun goToShoeList() {
        findNavController().navigate(R.id.actionShoeListingsToShoeDetails)
    }

    private fun initShoeList(listShoes: MutableList<Shoe>) {
        val parentLayout = binding.shoelist

        var index = 0
        while (index < listShoes.size) {
            val shoeBinding: ShoeListItemBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.shoe_list_item,
                parentLayout,
                false
            )

            val shoe = listShoes[index]
            shoeBinding.shoeName.text = shoe.name
            shoeBinding.companyName.text = shoe.company
            shoeBinding.shoeSize.text = shoe.size.toString()
            shoeBinding.description.text = shoe.description

            parentLayout.addView(shoeBinding.root)

            index++
        }
    }
}