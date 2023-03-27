package com.example.aston_dev_2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ShareCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.aston_dev_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /** Обработчики кнопок */
        with(binding) {
            openWebsiteButton.setOnClickListener { openWebsite(it) }

            openLocationButton.setOnClickListener { openLocation(it) }

            shareTextButton.setOnClickListener { shareText(it) }

            makePhotoButton.setOnClickListener { makePhoto(it) }
        }

    }

    /** Функция для запуска неявного намерения
     *
     * Для api 30+ либо явно указываем через manifest приложения для использования
     * Либо обрабатываем через try/catch
     * */
    private fun startImplicitIntents(intent: Intent) {
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }
    }

    /** Открыть веб-сайт по его адресу*/
    private fun openWebsite(view: View) {
        val url: String = binding.websiteEdittext.text.toString()

        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        startImplicitIntents(intent)
    }

    /** Найти место на картах по названию*/
    private fun openLocation(view: View) {
        val loc: String = binding.locationEdittext.text.toString()

        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)

        startImplicitIntents(intent)
    }

    /** Поделиться текстовым сообщением с другими приложениями*/
    private fun shareText(view: View) {
        val txt: String = binding.shareEdittext.text.toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle(R.string.share_text_with)
            .setText(txt)
            .startChooser()
    }

    /** Запустить приложение камеры на телефоне для создания фото*/
    private fun makePhoto(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        startImplicitIntents(intent)
    }
}