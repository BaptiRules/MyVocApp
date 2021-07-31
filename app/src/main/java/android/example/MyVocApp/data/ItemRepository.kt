package android.example.MyVocApp.data

import android.example.MyVocApp.Item
import androidx.lifecycle.LiveData

class ItemRepository(private val itemDao: ItemDao) {

    val readAllData: LiveData<Array<Item>> = itemDao.readAllData()

    suspend fun addItem(item: Item){

        itemDao.addItem(item)
    }

    suspend fun updateItem(item: Item){
        itemDao.updateItem(item)
    }


    suspend fun deleteItem(item: Item){
        itemDao.deleteItem(item)
    }

    suspend fun deleteAll(){
        itemDao.deleteAll()

    }


}
