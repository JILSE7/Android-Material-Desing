package org.bedu.a03material

import android.graphics.Color

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.webkit.URLUtil
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.snackbar.Snackbar
import org.bedu.a03material.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fab.setOnClickListener {
            if (binding.bottomAppBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            } else {
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            }
        }

        binding.bottomAppBar.setNavigationOnClickListener{
            Snackbar.make(binding.root, R.string.message_sucess,Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fab)
                .show()
        }

        binding.btnSkip.setOnClickListener {
            binding.card1.visibility = View.GONE
        }

        binding.btnBuy.setOnClickListener {
            Snackbar.make(it, R.string.card_buy, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fab)
                .setAction(R.string.card_buy) {
                    Toast.makeText(this, R.string.history, Toast.LENGTH_LONG).show()
                }
                .show()
        }
        loadImg()

        binding.cbEnablePass.setOnClickListener {
            binding.edPass.isEnabled = !binding.edPass.isEnabled
        }

        binding.edUrl.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val edText = binding.edUrl.text.toString()
            if (!hasFocus && edText.isNotEmpty()) {
                loadImg(binding.edUrl.text.toString())
                return@OnFocusChangeListener
            }
            if (!hasFocus && edText.isEmpty() || !hasFocus && !URLUtil.isValidUrl(edText)) {
                binding.edUrl.error = "Sin url"
            }
        }


        binding.toggleBtn.addOnButtonCheckedListener { _, checkedId, _ ->
            binding.root.setBackgroundColor(
                when(checkedId){
                    R.id.btn_red -> Color.RED
                    R.id.btn_blue -> Color.BLUE
                    else -> Color.YELLOW

                }
            )

        }

        binding.swFab.setOnCheckedChangeListener { V, isChecked ->
            if (isChecked) {
                binding.fab.show()
            }else {
                binding.fab.hide()
            }
        }

        binding.slider.addOnChangeListener { slider, value, fromUser ->

            binding.textView2.text = "Vol $value"
        }
    }

    private fun loadImg(imgUrl: String = "https://cdn2.excelsior.com.mx/media/styles/image800x600/public/pictures/2021/06/09/2592367.jpg") {
        Glide.with(this)
            .load(imgUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.imgCover)
    }

}
