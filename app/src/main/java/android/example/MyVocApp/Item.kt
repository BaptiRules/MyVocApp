package android.example.MyVocApp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "item_table")
data class Item(
        @PrimaryKey (autoGenerate = true) val id: Int,
        var german: String = "",
        var english: String = "",
        var isShowinGerman: Boolean = true

)
