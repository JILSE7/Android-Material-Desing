package org.bedu.a03material

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
    }
}
