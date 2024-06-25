package com.harsh.spinnerapplication

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.harsh.spinnerapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding ?= null
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.side_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toast -> Toast.makeText(
                this,
                "Toast shown from Action Bar item",
                Toast.LENGTH_LONG
            ).show()

            R.id.snackBar -> Toast.makeText(
                this,
                "Toast shown from Action Bar item",
                Toast.LENGTH_LONG
            ).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding?.btn2?.setOnClickListener {
            Dialog(this).apply {
                setContentView(R.layout.custom)
                show()
                val editText = this.findViewById<EditText>(R.id.edt1)
                val button = this.findViewById<Button>(R.id.add)
                button?.setOnClickListener {
                    if (editText?.text?.toString().isNullOrEmpty()){
                        editText?.error = "enter your city"
                    }
                    else {
                        this.dismiss()
                    }
                }
            }
        }
    }
}