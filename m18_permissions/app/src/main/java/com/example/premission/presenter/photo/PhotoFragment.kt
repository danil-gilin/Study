package com.example.premission.presenter.photo

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Build

import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.premission.databinding.FragmentPhotoBinding
import com.example.premission.entity.photo.NewPhoto
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject

private const val FILENAME_FORMAT="yyyy-MM-dd-HH-mm-ss"
@AndroidEntryPoint
class PhotoFragment : Fragment() {

    @Inject
    lateinit var photoFactory:PhotoViewModelFactory

    private val viewModel: PhotoViewModel by viewModels{ photoFactory }

    lateinit var binding:FragmentPhotoBinding

    private lateinit var executer:Executor
    private lateinit var imageCapture: ImageCapture

    private val name=SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())

    private val launcer=registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){map->
       if(map.values.all { it }){
           startCamera()
       }else {
           Toast.makeText(requireContext(), "permission is not Granted ", Toast.LENGTH_SHORT).show()
       }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentPhotoBinding.inflate(inflater)

        executer=ContextCompat.getMainExecutor(requireContext())

        checkPermission()
        binding.btnPhoto.setOnClickListener {
           val photo= takePhoto()
            Log.d("Photo",photo.toString())
           if(photo!=null){
           }
        }




        return binding.root
    }

  private fun takePhoto(): NewPhoto? {
        var photo: NewPhoto?=null
        val imageCapture=imageCapture?:return photo

       val contentValues= ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME,name)
            put(MediaStore.MediaColumns.MIME_TYPE,"image/jpeg")
        }

      val outputOption=  ImageCapture.OutputFileOptions.Builder(
            activity?.contentResolver!!,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ).build()

        imageCapture.takePicture(
            outputOption,
            executer,
            object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Toast.makeText(requireContext(),"Photo saved",Toast.LENGTH_SHORT).show()
                    photo= NewPhoto(null,outputFileResults.savedUri.toString(),name)
                    viewModel.addPhoto(photo!!)
                }
                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(requireContext(),"Photo failed",Toast.LENGTH_SHORT).show()
                }

            }
        )
        return photo
    }

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
      cameraProviderFuture.addListener({
     val cameraProvider=cameraProviderFuture.get()

          val preview=Preview.Builder().build()
          preview.setSurfaceProvider(binding.previewView.surfaceProvider)
          imageCapture=ImageCapture.Builder().build()

          cameraProvider.unbindAll()
          cameraProvider.bindToLifecycle(
              this,
              CameraSelector.DEFAULT_BACK_CAMERA,
              preview,
              imageCapture
          )
     },executer)
    }


    private fun checkPermission(){
       val allGranted= REQUEST_PERMISSON.all {
            permission->
            ContextCompat.checkSelfPermission(requireContext(),permission)==PackageManager.PERMISSION_GRANTED
        }
        if(allGranted){
            startCamera()
            Toast.makeText(requireContext(),"permission is true",Toast.LENGTH_SHORT).show()
        }else{
            launcer.launch(REQUEST_PERMISSON)
        }
    }

    companion object {
        fun newInstance() = PhotoFragment()
        private val REQUEST_PERMISSON:Array<String> = buildList {
            add(Manifest.permission.CAMERA)
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()
    }
}