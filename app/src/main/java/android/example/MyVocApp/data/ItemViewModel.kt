package android.example.MyVocApp.data

import android.app.Application
import android.example.MyVocApp.Item
import android.example.MyVocApp.data.ItemDatabase.Companion.getDatabase
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel (application: Application):AndroidViewModel(application) {

    val readAllData: LiveData<Array<Item>>
    private val repository: ItemRepository

    init {
        val itemDao = getDatabase(application).itemDao()
        repository = ItemRepository(itemDao)
        readAllData = repository.readAllData
    }

    fun addItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(item)
        }
    }
    fun updateItem(item: Item){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(item)
        }

    }

    fun deleteItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(item)

        }

    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()

        }

    }
}