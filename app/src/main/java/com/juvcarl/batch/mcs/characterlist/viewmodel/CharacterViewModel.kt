package com.juvcarl.batch.mcs.characterlist.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import com.juvcarl.batch.mcs.characterlist.repository.Repository
import com.juvcarl.batch.mcs.characterlist.view.CharacterListActivity
import javax.inject.Inject

class CharacterViewModel : ViewModel() {

    private lateinit var repository : Repository
    @Inject fun init(repository: Repository){
        this.repository = repository
    }

    val searchInput: MutableLiveData<String> = MutableLiveData()
    val searchResult = Transformations.switchMap(searchInput){
        if(it.isNotEmpty()){
            repository.getCharacters(it)
        }else{
            MutableLiveData()
        }
    }




    fun search(term: String){
        searchInput.value = (term)
    }

    companion object{
        fun create(activity: CharacterListActivity): CharacterViewModel{
            var characterViewModel = ViewModelProviders.of(activity).get(CharacterViewModel::class.java)
            return characterViewModel
        }
    }

}