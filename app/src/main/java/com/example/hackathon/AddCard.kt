package com.example.hackathon

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hackathon.room.models.CardModel
import com.example.hackathon.room.viewmodel.CardViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.mlkit.vision.common.InputImage
import java.io.ByteArrayOutputStream

class AddCard : Fragment() {

    private val CAMERA_PERMISSION_CODE = 123
    private val STORAGE_PERMISSION_CODE = 113
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    lateinit var inputImage: InputImage

    private lateinit var mCardViewModel: CardViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mCardViewModel = ViewModelProvider(this).get(CardViewModel::class.java)


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_card, container, false)
        val btnBack: MaterialButton = view.findViewById(R.id.backBtnAdd)
        btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_addCard_to_collection2)
        }

        val addNameCardEt: TextInputEditText = view.findViewById(R.id.addNameCardEt)
        val descEt: TextInputEditText = view.findViewById(R.id.addDescCardEt)
        val btnSave: ExtendedFloatingActionButton = view.findViewById(R.id.btnSave)
        val cardImage: ImageView = view.findViewById(R.id.cardImage)

        btnSave.setOnClickListener {
            insertDataToDatabase(
                addNameCardEt.text.toString(),
                toByteArray(cardImage),
                descEt.text.toString()

            )
        }

        cardImage.setOnClickListener {

            val options = arrayOf("Camera", "Gallery")
            val builder = AlertDialog.Builder(requireContext())

            builder.setItems(options, DialogInterface.OnClickListener { dialog, which ->
                if (which == 0) {
                    val camera_permission =
                        ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.CAMERA
                        )
                    if (camera_permission == PackageManager.PERMISSION_GRANTED) {
                        val camerIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        cameraLauncher.launch(camerIntent)
                    } else {
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(Manifest.permission.CAMERA),
                            targetRequestCode
                        )
                    }
                } else {
                    val storageIntent = Intent()
                    storageIntent.setType("image/*")
                    storageIntent.setAction(Intent.ACTION_GET_CONTENT)
                    galleryLauncher.launch(storageIntent)

                }
            })


            builder.show()

        }
        cameraLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            object : ActivityResultCallback<ActivityResult> {
                override fun onActivityResult(result: ActivityResult?) {
                    val data = result?.data
                    try {
                        val photo = data?.extras?.get("data") as Bitmap
                        cardImage.setImageBitmap(photo)
                        inputImage = InputImage.fromBitmap(photo, 0)
//                        processImage()

                    } catch (e: Exception) {
                        Log.d(Log.INFO.toString(), "onActivityResult: ${e.message}")
                    }
                }
            }
        )

        galleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            object : ActivityResultCallback<ActivityResult> {
                override fun onActivityResult(result: ActivityResult?) {
                    val data = result?.data
                    try {
                        inputImage = InputImage.fromFilePath(requireActivity(), data?.data!!)
                        cardImage.setImageURI(data?.data)
//                        processImage()
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Bad save", Toast.LENGTH_LONG).show()
                    }
                }
            }
        )


        return view
    }

    private fun insertDataToDatabase(cardName: String,image: ByteArray ,desc: String) {
        if (inputCheck(cardName, desc)) {
            val card = CardModel(card_name = cardName, card_image = image, description = desc)
            mCardViewModel.addCard(card)
            view?.let { Snackbar.make(it, "Сохранено", Snackbar.LENGTH_LONG).show() }
            findNavController().navigate(R.id.action_addCard_to_collection2)
        } else {
            view?.let {
                Snackbar.make(it, "Заполните форму до конца :)", Snackbar.LENGTH_LONG).show()
            }

        }
    }

    private fun toByteArray(img: ImageView): ByteArray {
        val bitmap = (img.drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        val image = stream.toByteArray()
        return image
    }

    private fun inputCheck(cardName: String, desc: String): Boolean {
        return !TextUtils.isEmpty(cardName) && !TextUtils.isEmpty(desc)
    }


}