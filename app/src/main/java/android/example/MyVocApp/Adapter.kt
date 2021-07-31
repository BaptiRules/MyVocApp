package android.example.MyVocApp

import android.content.Context
import android.example.MyVocApp.data.ItemViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class Adapter(val c: Context, var mViewModel: ItemViewModel): RecyclerView.Adapter<Adapter.ViewHolder>() {

    var itemListDB = emptyArray<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return ViewHolder(inflater)
    }

    override fun getItemCount() = itemListDB.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        var textViewDe: TextView = v.text_view_de
        var textViewEn: TextView = v.text_view_en
        private var menuImage: Button

        init {
            v.setOnClickListener(this)

            textViewDe = v.findViewById(R.id.text_view_de)
            textViewEn = v.findViewById(R.id.text_view_en)
            menuImage = v.findViewById(R.id.menu_button)
            menuImage.setOnClickListener { popupMenu(it)}
        }

        private fun popupMenu(v:View) {
            val drop = PopupMenu(c, v)

            drop.inflate(R.menu.drop_menu)
            drop.setOnMenuItemClickListener {
                when(it.itemId){

                    R.id.delete_menu-> {

                        val currentItem = itemListDB[position]
                        mViewModel.deleteItem(currentItem)

                        notifyDataSetChanged()

                        true
                    }
                    else -> true
                }
            }
            drop.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(drop)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)

        }

        override fun onClick(p0: View?) {

            if (textViewDe.visibility == View.VISIBLE) {
                textViewDe.visibility = View.GONE
                textViewEn.visibility = View.VISIBLE
            } else {
                textViewDe.visibility = View.VISIBLE
                textViewEn.visibility = View.GONE
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemListDB[position]

        holder.textViewDe.text = currentItem.german
        holder.textViewEn.text = currentItem.english

        holder.textViewDe.isVisible = !currentItem.isShowinGerman
        holder.textViewEn.isVisible = currentItem.isShowinGerman

    }
    fun setData(item: Array<Item>){
        itemListDB = item
        notifyDataSetChanged()
    }


}


/*                    R.id.edit_menu->{
                        val v2 = LayoutInflater.from(c).inflate(R.layout.add_item_layout,null)  //edit layout aufrufen //Layout inflator wird immer gebrauch wenn irgend ein layout jetzt gebraucht wird
                        val de = v2.findViewById<EditText>(R.id.editText)        //eingaben einfügen
                        val en = v2.findViewById<EditText>(R.id.editText2)
                        AlertDialog.Builder(c)                                   //dialog fenster starten
                                .setView(v2)                                         //dialog und eingabe-view aufrufen von add_item_layout
                                .setPositiveButton("Ok"){
                                    dialog,_->

                                    val deutsch = de.text.toString()           //in andere funktion
                                    val english = en.text.toString()

                                    val updateItem = Item(2, deutsch, english)

                                    mViewModel.updateItem(updateItem)

                                    notifyDataSetChanged()
                                    Toast.makeText(c,"wurde geändert",Toast.LENGTH_SHORT).show()
                                    dialog.dismiss()

                                }
                                .setNegativeButton("Abbrechen"){
                                    dialog,_->
                                    dialog.dismiss()
                                }
                                .create()
                                .show()
                        true
                    }*/


/*
*
*         if(holder.textViewDe.isVisible == !currentItem.isShowinGerman) {
            holder.textViewDe.isVisible = !currentItem.isShowinGerman
            holder.textViewEn.isVisible = currentItem.isShowinGerman

        }else {
            holder.textViewEn.isVisible = currentItem.isShowinGerman
            holder.textViewDe.isVisible = !currentItem.isShowinGerman

        }*/