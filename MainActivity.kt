package com.example.listview

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewContactos = findViewById<ListView>(R.id.listViewContactos)
        val searchView = findViewById<EditText>(R.id.searchView)

        // Inicializar la lista de contactos
        val contactos = listOf(
            ContactoModel("Monkey.D. Luffy", "555-1234", "luffy@gmail.com", R.drawable.img),
            ContactoModel("Zoro Roronoa", "555-5678", "zoro@gmail.com", R.drawable.img),
            ContactoModel("Nami", "555-8765", "nami@gmail.com", R.drawable.img),
            ContactoModel("Ussop", "555-4321", "ussop@gmail.com", R.drawable.img),
            ContactoModel("Tonny Chopper", "555-1357", "tony@gmail.com", R.drawable.img),
            ContactoModel("Chopper", "555-1357", "tony@gmail.com", R.drawable.img),
            ContactoModel("Nico Robin", "555-8642", "robin@gmail.com", R.drawable.img),
        )

        // Inicializar el adaptador y asignarlo al ListView
        adapter = ContactAdapter(this, ArrayList(contactos))
        listViewContactos.adapter = adapter

        // Configurar el EditText para el filtrado
        searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                adapter.filter.filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
