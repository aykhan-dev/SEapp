package sample.seapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import sample.seapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
        configureUIComponents()
    }

    @SuppressLint("SetTextI18n")
    private fun configureUIComponents() {

        binding?.apply {

            buttonEncryptAndStore.setOnClickListener {

                val data = editText.text.toString()
                if (data.isBlank()) {
                    Toast.makeText(applicationContext, "Type something", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val encryption = SecureElement.encrypt(data)
                editText.text = SpannableStringBuilder("")

                result.text = "Click here: $encryption :to see the decrypted version"

                result.setOnClickListener {
                    val decryption = SecureElement.decrypt(encryption)
                    Toast.makeText(
                        applicationContext,
                        String(decryption ?: ByteArray(0), Charsets.UTF_8),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        }

    }

}