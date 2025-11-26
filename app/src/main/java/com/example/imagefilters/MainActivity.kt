package com.example.imagefilters

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mukesh.imageproccessing.OnProcessingCompletionListener
import com.mukesh.imageproccessing.PhotoFilter
import com.mukesh.imageproccessing.filters.*

class MainActivity : AppCompatActivity(),
    OnProcessingCompletionListener, OnFilterClickListener {

    private lateinit var result: Bitmap
    private var photoFilter: PhotoFilter? = null

    private lateinit var effectView: GLSurfaceView
    private lateinit var effectsRecyclerView: RecyclerView
    private lateinit var chooseImageButton: Button

    // Master image — NEVER sent directly to PhotoFilter
    private var originalBitmap: Bitmap? = null

    private fun scaleToEffectView(bitmap: Bitmap): Bitmap {
        val width = effectView.width.takeIf { it > 0 } ?: bitmap.width
        val height = effectView.height.takeIf { it > 0 } ?: bitmap.height
        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }


    // Choosing image from gallery (modern API)
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val data: Intent? = activityResult.data
                val imageUri: Uri? = data?.data

                try {
                    val inputStream = contentResolver.openInputStream(imageUri!!)
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream?.close()

                    if (bitmap != null) {

                        originalBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)

                        // Scale to GLSurfaceView size
                        val scaled = scaleToEffectView(originalBitmap!!)

                        val working = scaled.copy(Bitmap.Config.ARGB_8888, true)
                        photoFilter?.applyEffect(working, None())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    override fun onProcessingComplete(bitmap: Bitmap) {
        result = bitmap
    }

    // APPLYING FILTER SAFELY
    override fun onFilterClicked(filters: Filters) {

        if (originalBitmap == null || originalBitmap!!.isRecycled) {
            originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.background)
                .copy(Bitmap.Config.ARGB_8888, true)
        }

        // Scale image to match GLSurfaceView
        val scaled = scaleToEffectView(originalBitmap!!)

        // Working safe copy
        val workingCopy = scaled.copy(Bitmap.Config.ARGB_8888, true)

        photoFilter?.applyEffect(workingCopy, filters.filter)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        effectView = findViewById(R.id.effectView)
        effectsRecyclerView = findViewById(R.id.effectsRecyclerView)
        chooseImageButton = findViewById(R.id.chooseImageButton)

        // Load default background safely
        val defaultBmp = BitmapFactory.decodeResource(resources, R.drawable.background)
        originalBitmap = defaultBmp.copy(Bitmap.Config.ARGB_8888, true)

        // Setup filter engine
        photoFilter = PhotoFilter(effectView, this)

        val scaled = scaleToEffectView(originalBitmap!!)
        val working = scaled.copy(Bitmap.Config.ARGB_8888, true)
        photoFilter?.applyEffect(working, None())

        // Setup RecyclerView
        effectsRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        effectsRecyclerView.setHasFixedSize(true)
        effectsRecyclerView.adapter = Adapter(getItems(), this)

        // Select image button
        chooseImageButton.setOnClickListener {
            openImagePicker()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }

    private fun getItems(): MutableList<Filters> {
        return mutableListOf(
            Filters("None", None()),
            Filters("AutoFix", AutoFix()),
            Filters("Highlight", Highlight()),
            Filters("Brightness", Brightness()),
            Filters("Contrast", Contrast()),
            Filters("Cross Process", CrossProcess()),
            Filters("Documentary", Documentary()),
            Filters("Duo Tone", DuoTone()),
            Filters("Fill Light", FillLight()),
            Filters("Fisheye", FishEye()),
            Filters("Flip Horizontally", FlipHorizontally()),
            Filters("Flip Vertically", FlipVertically()),
            Filters("Grain", Grain()),
            Filters("Grayscale", Grayscale()),
            Filters("Lomoish", Lomoish()),
            Filters("Negative", Negative()),
            Filters("Posterize", Posterize()),
            Filters("Rotate", Rotate()),
            Filters("Saturate", Saturate()),
            Filters("Sepia", Sepia()),
            Filters("Sharpen", Sharpen()),
            Filters("Temperature", Temperature()),
            Filters("Tint", Tint()),
            Filters("Vignette", Vignette())
        )
    }
}
