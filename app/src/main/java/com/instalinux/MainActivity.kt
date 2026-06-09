package com.instalinux

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setBackgroundColor(android.graphics.Color.BLACK)

        val log = TextView(this)
        log.setTextColor(android.graphics.Color.GREEN)
        log.text = "root@linux:~# system ready\n"

        val input = EditText(this)
        input.hint = "enter command / url"
        input.setTextColor(android.graphics.Color.WHITE)

        val btn = Button(this)
        btn.text = "RUN"

        layout.addView(log)
        layout.addView(input)
        layout.addView(btn)

        setContentView(layout)

        btn.setOnClickListener {
            val cmd = input.text.toString()

            log.append("\n> executing...\n")

            thread {
                try {
                    Thread.sleep(1000)

                    runOnUiThread {
                        log.append("> done ✔\n")
                        Toast.makeText(this, "Executed", Toast.LENGTH_SHORT).show()
                    }

                } catch (e: Exception) {
                    runOnUiThread {
                        log.append("> error ❌\n")
                    }
                }
            }
        }
    }
}
