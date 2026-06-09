package com.instalinux

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setBackgroundColor(android.graphics.Color.BLACK)

        val log = TextView(this)
        log.setTextColor(android.graphics.Color.GREEN)
        log.text = "root@linux:~$ waiting command...\n"

        val input = EditText(this)
        input.hint = "paste instagram url"
        input.setTextColor(android.graphics.Color.WHITE)

        val btn = Button(this)
        btn.text = "RUN"
        btn.setBackgroundColor(android.graphics.Color.DKGRAY)
        btn.setTextColor(android.graphics.Color.GREEN)

        layout.addView(log)
        layout.addView(input)
        layout.addView(btn)

        setContentView(layout)

        btn.setOnClickListener {
            val url = input.text.toString()

            log.append("\nroot@linux:~$ downloading...\n")

            thread {
                try {
                    val stream = URL(url).openStream()
                    stream.close()

                    runOnUiThread {
                        log.append("root@linux:~$ done ✔\n")
                        Toast.makeText(this, "Downloaded", Toast.LENGTH_SHORT).show()
                    }

                } catch (e: Exception) {
                    runOnUiThread {
                        log.append("root@linux:~$ error ❌\n")
                    }
                }
            }
        }
    }
}
