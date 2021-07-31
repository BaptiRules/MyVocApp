package android.example.MyVocApp.data

import android.example.MyVocApp.Item
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao

interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun readAllData(): LiveData<Array<Item>>

    @Query("DELETE FROM item_table")
    suspend fun deleteAll()
}
