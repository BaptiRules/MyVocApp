
package android.example.MyVocApp

import android.content.Intent
import android.example.MyVocApp.data.ItemViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

//VARIABLEN DEFINIEREN
    private lateinit var addButton: FloatingActionButton
    private lateinit var recy: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var mViewModel: ItemViewModel
    private lateinit var itemList: Array<Item>

//ONCREATE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//VIEWMODEL
        mViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        mViewModel.readAllData.observe(this, Observer { item ->
            adapter.setData(item)
        })

//FINDVIEWBYID
        addButton = findViewById(R.id.addingBtn)
        recy = findViewById(R.id.recycler_view)

//RECYCLERVIEW
        adapter = Adapter(this, mViewModel)

        recy.layoutManager = LinearLayoutManager(this)
        recy.adapter = adapter
        itemList = adapter.itemListDB


//FUNKTIONS-AUFRUFE
        addButton.setOnClickListener { addInfo() }
    }

//FUNKTIONEN
    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item_layout, null)
        val eng = v.findViewById<EditText>(R.id.editText)
        val deu = v.findViewById<EditText>(R.id.editText2)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("OK"){ dialog, _->

            val eng2 = eng.text.toString()
            val deu2 = deu.text.toString()

            val item = Item(0, eng2, deu2)

            mViewModel.addItem(item)

            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }

        addDialog.setNegativeButton("Abbrechen"){ dialog, _->
            dialog.dismiss()

        }
        addDialog.create()
        addDialog.show()
    }

    private fun resetLanguage() {
        for (item in itemList) {
            item.isShowinGerman = true
        }
        adapter.notifyDataSetChanged()
    }


//MENÜ KLASSE
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.open_menu -> {

                val intent = Intent(this, InfoActivity::class.java)
                startActivity(intent)
            }
            R.id.open_menu2 -> {

                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.reset_all -> {

                resetLanguage()
                Toast.makeText(this, "Zurückgestellt auf Deutsch", Toast.LENGTH_SHORT).show()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}


