package app.manganyan.presentation.screens.publish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.ActionBar
import app.manganyan.R
import java.net.URI

class PublishChapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish_chapter)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val chapterTitleEditText = findViewById<EditText>(R.id.publish_chapter_chapter_title_edit_text)
        val chapterNumberEditText = findViewById<EditText>(R.id.publish_chapter_chapter_number_edit_text)

        val chapterPublishButton = findViewById<Button>(R.id.publish_chapter_button)

        val chapterListView = findViewById<ListView>(R.id.publish_chapter_list_view)
        val imageUris = mutableListOf<String>()


        chapterPublishButton.setOnClickListener{

        }
    }
}