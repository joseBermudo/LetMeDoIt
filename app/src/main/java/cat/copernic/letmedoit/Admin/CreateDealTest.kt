package cat.copernic.letmedoit.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import cat.copernic.letmedoit.Utils
import cat.copernic.letmedoit.databinding.ActivityCreateDealTestBinding
import cat.copernic.letmedoit.databinding.ActivityCustomspinnerBinding

private lateinit var binding: ActivityCreateDealTestBinding

class CreateDealTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateDealTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Utils.AsignarPopUpSpinner(this,Utils.createList(),binding.spinner)
        Utils.AsignarPopUpSpinner(this, Utils.createList(),binding.hisSpinner)
    }



}

