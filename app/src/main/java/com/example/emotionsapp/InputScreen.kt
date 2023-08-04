package com.example.emotionsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import com.example.emotionsapp.data.DataEntity
import com.example.emotionsapp.data.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InputScreen : AppCompatActivity() {

    private lateinit var userDatabase: UserDatabase
    private lateinit var thetext: EditText
    private lateinit var radioGroup: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_screen)
        userDatabase = UserDatabase.getDatabase(this)
        thetext = findViewById(R.id.textInput)
        radioGroup = findViewById(R.id.emotion_options)

        // Back Button
        val backButton: Button = findViewById(R.id.back)
        backButton.setOnClickListener {
            finish()
        }

        // Save Button
        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            saveDataAndGoBack()
        }
    }

    // Save data to the database and finish the activity
    private fun saveDataAndGoBack() {
        val radioButtonSelection = getSelectedRadioButtonValue()
        val dataEntity = DataEntity(emotionType =radioButtonSelection, emotionDescription =thetext.text.toString())

        GlobalScope.launch {
            userDatabase.dataDao().insertData(dataEntity)
            setResult(RESULT_OK)
            finish()
        }
    }

    private fun getSelectedRadioButtonValue(): String {
        return when (radioGroup.checkedRadioButtonId) {
            R.id.happy -> "😀"
            R.id.neutral -> "😐"
            else -> "🙁"
        }
    }
}
