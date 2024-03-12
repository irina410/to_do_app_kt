package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listView = binding.list
        val userData = binding.editText
        val todoes: MutableList<String> = mutableListOf()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todoes)
        listView.adapter = adapter

        listView.setOnItemClickListener { adaperView, view, i, l ->
            val text = listView.getItemAtPosition(i).toString()
            // Добавляем диалоговое окно для выбора действия: редактировать или удалить
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Выберите действие")
            dialog.setItems(arrayOf("Редактировать", "Удалить")) { _, which ->
                when (which) {
                    0 -> {
                        // Редактирование элемента
                        userData.setText(text)
                        adapter.remove(text)
                    }
                    1 -> {
                        // Удаление элемента
                        adapter.remove(text)
                        Toast.makeText(this, "Удалили $text из списка дел", Toast.LENGTH_LONG).show()
                    }
                }
            }
            dialog.show()
        }

        binding.addButton.setOnClickListener{
            val text = userData.text.toString().trim()

            if (text != "")
                adapter.insert(text, 0)
        }


    }
}