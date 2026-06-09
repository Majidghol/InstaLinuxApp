package com.instalinux

import android.os.Bundle
import android.widget.*
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.BLACK)
            setPadding(30,30,30,30)
        }

        val log = TextView(this).apply {
            setTextColor(Color.GREEN)
            text = "root@linux:~$ system ready\n"
        }

        val input = EditText(this).apply {
            hint = "enter url"
            setTextColor(Color.WHITE)
        }

        val btn = Button(this).apply {
            text = "RUN"
        }

        val progress = ProgressBar(this).apply {
            visibility = ProgressBar.GONE
        }

        layout.addView(log)
        layout.addView(input)
        layout.addView(btn)
        layout.addView(progress)

        setContentView(layout)

        btn.setOnClickListener {
            val url = input.text.toString()

            log.append("\nconnecting...\n")
            progress.visibility = ProgressBar.VISIBLE

            thread {
                try {
                    URL(url).openStream().close()

                    runOnUiThread {
                        progress.visibility = ProgressBar.GONE
                        log.append("DONE ✔\n")
                    }

                } catch (e: Exception) {
                    runOnUiThread {
                        progress.visibility = ProgressBar.GONE
                        log.append("ERROR ❌\n")
                    }
                }
            }
        }
    }
}
