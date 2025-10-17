package com.example.driodcafe

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Inisialisasi Spinner
        val spinner: Spinner = findViewById(R.id.city_spinner)

        // Data kota yang ditampilkan di Spinner
        val cities = arrayOf("Pilih Kota", "Kota Bandung", "Kota Cimahi", "Kabupaten Bandung")

        // Adapter untuk Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Event ketika user memilih kota
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                if (position > 0) { // Menghindari posisi "Pilih Kota"
                    val selectedCity = parent.getItemAtPosition(position).toString()
                    Toast.makeText(applicationContext, "Kota dipilih: $selectedCity", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Tidak melakukan apa-apa
            }
        }
    }

    // Event handler untuk RadioButton
    fun onRadioButtonClicked(view: View) {
        val checked = (view as RadioButton).isChecked
        when (view.id) {
            R.id.sameday ->
                if (checked) displayToast(getString(R.string.same_day_messenger_service))
            R.id.nextday ->
                if (checked) displayToast(getString(R.string.next_day_ground_delivery))
            R.id.pickup ->
                if (checked) displayToast(getString(R.string.pick_up))
        }
    }

    private fun displayToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
