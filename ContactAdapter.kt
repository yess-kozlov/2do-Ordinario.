ckage com.example.listview

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class ContactAdapter(
    private val context: Context,
    private var contactos: ArrayList<ContactoModel>
) : BaseAdapter(), Filterable {

    private var filteredContacts: ArrayList<ContactoModel> = contactos

    override fun getCount(): Int = filteredContacts.size

    override fun getItem(position: Int): ContactoModel = filteredContacts[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_contact, parent, false)
        val contacto = getItem(position)

        val imageViewPerfil = view.findViewById<ImageView>(R.id.imageViewPerfil)
        val textViewNombre = view.findViewById<TextView>(R.id.textViewNombre)
        val textViewTelefono = view.findViewById<TextView>(R.id.textViewTelefono)
        val textViewEmail = view.findViewById<TextView>(R.id.textViewEmail)

        imageViewPerfil.setImageResource(contacto.imagenId)
        textViewNombre.text = contacto.nombre
        textViewTelefono.text = contacto.telefono
        textViewEmail.text = contacto.email

        // duplicacion
        val duplicates = filteredContacts.filter { it.telefono == contacto.telefono }
        if (duplicates.size > 1) {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.duplicateContactColor))
        } else {
            view.setBackgroundColor(Color.TRANSPARENT)
        }

        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint?.toString()?.lowercase()

                val results = FilterResults()
                results.values = if (queryString.isNullOrEmpty()) {
                    contactos
                } else {
                    contactos.filter {
                        it.nombre.lowercase().contains(queryString) ||
                                it.telefono.contains(queryString)
                    }
                }
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredContacts = results?.values as ArrayList<ContactoModel>
                notifyDataSetChanged()
            }
        }
    }
}
