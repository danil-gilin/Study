package com.example.room.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.room.bd.NewWord
import com.example.room.bd.Word
import com.example.room.bd.WordDao
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {
     val allWord=wordDao.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    private val _state= MutableStateFlow<State>(State.Success)
    val state=_state.asStateFlow()

    fun addWord(word:String) {
        viewModelScope.launch {
            _state.value=State.Loading
            if(checkInput(word).first) {
                wordDao.update(word)
                wordDao.insert(Word(word))
                _state.value=State.Success
            }else{
                _state.value=State.Error(checkInput(word).second)
            }
        }

    }

    fun clearAll() {
        viewModelScope.launch {
            wordDao.delete()
        }
    }

    fun checkInput(word: String):Pair<Boolean,String>{

        return if (word.all{ it.isLetter() || it=='-' } && word.isNotEmpty()){
            Pair(true,"")
        }else{
            if(word.isEmpty()){
                Pair(false,"Ввод не должен быть пустой")
            }else{
                Pair(false,"Допустимы только буквы и дефисы")
            }
        }
    }

}