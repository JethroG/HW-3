package com.yourcompany.bro.hi.ck

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {

    private val messageList = mutableListOf<MessageText>()
    private val adapter = Adapter(messageList)

    private val editText by lazy { findViewById<EditText>(R.id.editText) }
    private val radioGroup by lazy { findViewById<RadioGroup>(R.id.radioGroup) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val message = editText.text.toString()
            val user = radioButtonClick() ?: throw Exception("User not defined.")
            adapter.addMessageItem(user, message)
            editText.text.clear()
        }
    }
    private fun radioButtonClick(): String? {
        val checked = radioGroup.checkedRadioButtonId
        var user: String? = null
        when (checked) {
            R.id.radioButton -> user = "1"
            R.id.radioButton2 -> user = "2"
        }
        return user
    }
}