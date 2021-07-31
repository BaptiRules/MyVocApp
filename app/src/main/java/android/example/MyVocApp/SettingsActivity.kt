package android.example.MyVocApp

import android.app.AlertDialog
import android.content.Intent
import android.example.MyVocApp.data.ItemViewModel
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class SettingsActivity : AppCompatActivity() {


    private lateinit var mViewModel: ItemViewModel

    private lateinit var deleteAll: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        mViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        deleteAll = findViewById(R.id.delete_all)

        deleteAll.setOnClickListener { warningDialog()}
    }


//FUNKTIONEN
private fun warningDialog(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Soll wirklich alles gelöscht werden?")
        builder.setPositiveButton("JA"){ dialogInterface, _ ->
            deleteAllElements()
            dialogInterface.dismiss()
        }

        builder.setNegativeButton("NEIN"){ dialogInterface, _ ->
            dialogInterface.dismiss()
        }

        val alertDialog : AlertDialog = builder.create()

        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    private fun deleteAllElements() {

        mViewModel.deleteAll()

        Toast.makeText(this, "Daten bereinigt", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}