package cat.copernic.letmedoit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cat.copernic.letmedoit.databinding.ActivityCustomspinnerBinding

class CustomSpinner : AppCompatActivity() {
    private lateinit var binding: ActivityCustomspinnerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomspinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //EJEMPLO DE USO DEL SPINNER --> HAY QUE IMPLEMENTARLO SI NO NO IRÁ
        val spinner = binding.spinner

        var list: ArrayList<String> = ArrayList<String>()
        list.add("A")
        list.add("B")
        list.add("C")

        Utils.AsignarPopUpSpinner(this,list,spinner)

        //
    }
}