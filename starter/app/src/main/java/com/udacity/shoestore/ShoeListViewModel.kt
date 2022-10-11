package com.udacity.shoestore

import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    var shoe = Shoe(
        "",
        "",
        "",
        ""
    )





    //Shoes List -----------
    private var _shoesList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    private val _eventAddShoeListPress = MutableLiveData(false)
    val eventAddShoeListPress: LiveData<Boolean>
        get() = _eventAddShoeListPress
    // ---------

    //Shoe detail ----------
    //save
    private val _eventSaveShoeDetailPress = MutableLiveData(false)
    val eventSaveShoeDetailPress: LiveData<Boolean>
        get() = _eventSaveShoeDetailPress

    //cancel
    private val _eventCancelShoeDetailPress = MutableLiveData(false)
    val eventCancelShoeDetailPress: LiveData<Boolean>
        get() = _eventCancelShoeDetailPress

    //missing name shoe
    private val _eventSaveFailByNameShoeDetail = MutableLiveData(false)
    val eventSaveFailByNameShoeDetail: LiveData<Boolean>
        get() = _eventSaveFailByNameShoeDetail

    //missing size shoe
    private val _eventSaveFailBySizeShoeDetail = MutableLiveData(false)
    val eventSaveFailBySizeShoeDetail: LiveData<Boolean>
        get() = _eventSaveFailBySizeShoeDetail

    //missing name company
    private val _eventSaveFailByNameCompanyShoeDetail = MutableLiveData(false)
    val eventSaveFailByNameCompanyShoeDetail: LiveData<Boolean>
        get() = _eventSaveFailByNameCompanyShoeDetail

    //Shoes List -------
    fun goToShoeDetailStart() {
        _eventAddShoeListPress.value = true
    }

    fun goToShoeDetailStartComplete() {
        _eventAddShoeListPress.value = false
    }
    // ---------

    //Shoe detail
    //Save
    fun saveShoeDetailStart() {
        when {
            shoe.company.trim().isEmpty() -> {
                _eventSaveFailByNameCompanyShoeDetail.value = true
            }
            shoe.name.trim().isEmpty() -> {
                _eventSaveFailByNameShoeDetail.value = true
            }
            shoe.size .trim().isEmpty() -> {
                _eventSaveFailBySizeShoeDetail.value = true
            }
            else -> {
                saveNewShoe()
                _eventSaveShoeDetailPress.value = true
            }
        }
    }

    fun saveShoeDetailComplete() {
        _eventSaveShoeDetailPress.value = false
    }

    fun saveFailByNameShoeDetailComplete() {
        _eventSaveFailByNameShoeDetail.value = false
    }

    fun saveFailByNameCompanyShoeDetailComplete() {
        _eventSaveFailByNameCompanyShoeDetail.value = false
    }

    fun saveFailBySizeShoeDetailComplete() {
        _eventSaveFailBySizeShoeDetail.value = false
    }

    //Cancel
    fun cancelShoeDetailStart() {
        _eventCancelShoeDetailPress.value = true
    }

    fun cancelShoeDetailComplete() {
        _eventCancelShoeDetailPress.value = false
    }

    private fun saveNewShoe() {
        val list = _shoesList.value
        list?.let {
            it.add(shoe)
            _shoesList.setValue(list)
        }
    }

    fun clearShoeTemplate() {
        shoe = Shoe(
            "",
            "",
            "",
            ""
        )
    }
}