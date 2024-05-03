//package com.example.otp_number
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.location.Location
//import android.net.Uri
//import android.os.Bundle
//import android.telephony.SmsManager
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.Toast
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.databinding.DataBindingUtil
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.Observer
//import androidx.navigation.findNavController
//import androidx.room.Room
//import com.example.otp_number.FirebaseAuth.LoginViewModel
//import com.firebase.ui.auth.AuthUI
//import com.firebase.ui.auth.IdpResponse
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationServices
//import com.google.firebase.auth.FirebaseAuth
//import com.example.otp_number.database.Guardian
//import com.example.otp_number.database.GuardianDatabase
//import com.example.otp_number.databinding.FragmentDashBoardBinding
//import kotlinx.coroutines.*
//
//class DashBoardFragment : Fragment() {
//
//    private lateinit var binding: FragmentDashBoardBinding
//
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    private lateinit var lastLocation: Location
//    private var Latitude: String = ""
//    private var Longitude: String = ""
//    private lateinit var button: Button
//    private val CALL_PERMISSION_REQUEST_CODE = 101
//
//    private var viewModelJob = Job()
//    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
//
//    companion object {
//        const val TAG = "DashBoardFragment"
//        const val SIGN_IN_RESULT_CODE = 1001
//        private const val PERMISSION_SEND_SMS = 2
//    }
//
//    private val viewModel by viewModels<LoginViewModel>()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
//
//        // Inflate the layout for this fragment
//        binding = DataBindingUtil.inflate(
//            inflater,
//            R.layout.fragment_dash_board, container, false
//        )
//
//        getLocation()
//
//        binding.guardianButton.setOnClickListener { view: View ->
//            view.findNavController()
//                .navigate(R.id.action_dashBoardFragment_to_guardianInfo2)
//        }
//
//        binding.locButton.setOnClickListener {
////            view.findNavController().navigate(R.id.action_dashBoardFragment_to_mapsActivity)
////            view.findNavController().navigate(R.id.action_dashBoardFragment2_to_mapsActivity2)
//            val intent = Intent(context, MapsActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.emerButton.setOnClickListener {
////            getLocation()
////            if (Longitude.isNullOrBlank() || Longitude.isNullOrEmpty()) {
////                Toast.makeText(
////                    requireActivity(),
////                    "Click on Location button and try again",
////                    Toast.LENGTH_LONG
////                ).show()
////            } else {
//                if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.SEND_SMS)
//                    != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.SEND_SMS), PERMISSION_SEND_SMS)
//                } else {
//                    uiScope.launch {
//                        withContext(Dispatchers.IO) {
//                            emergencyFun()
//                        }
//                    }
//                }
////            }
//        }
//
////        button = findViewById(R.id.button)
////        edittext = findViewById(R.id.editText)
//
//        // Attach set on click listener to the button for initiating intent
////        binding.sosbtn.setOnClickListener(View.OnClickListener {
//            // getting phone number from edit text and changing it to String
////            val phone_number = edittext.text.toString()
////
////            // Getting instance of Intent with action as ACTION_CALL
////            val phone_intent = Intent(Intent.ACTION_CALL)
////
////            // Set data of Intent through Uri by parsing phone number
////            phone_intent.data = Uri.parse("tel:$phone_number")
////
////            // start Intent
////            startActivity(phone_intent)
////        })
//            binding.sosbtn.setOnClickListener(View.OnClickListener {
//                if (ContextCompat.checkSelfPermission(
//                        requireActivity(),
//                        android.Manifest.permission.CALL_PHONE
//                    ) != PackageManager.PERMISSION_GRANTED
//                ) {
//                    // Request the permission
//                    ActivityCompat.requestPermissions(
//                        requireActivity(),
//                        arrayOf(android.Manifest.permission.CALL_PHONE),
//                        CALL_PERMISSION_REQUEST_CODE
//                    )
//                } else {
//                    // Permission is already granted, proceed with making the call
//                    makePhoneCall()
//                }
//            })
//
//
//            return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        observeAuthenticationState()
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == SIGN_IN_RESULT_CODE) {
//            val response = IdpResponse.fromResultIntent(data)
//            if (resultCode == Activity.RESULT_OK) {
//                //User successfully signed in
//            } else {
//                // Sign in failed. If response is null, the user canceled the
//                // sign-in flow using the back button. Otherwise, check
//                // the error code and handle the error.
//                Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
//            }
//        }
//    }
//
////    override fun onRequestPermissionsResult(
////        requestCode: Int,
////        permissions: Array<out String>,
////        grantResults: IntArray
////    ) {
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
////        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
////            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                // Permission granted, make the call
////                makePhoneCall()
////            } else {
////                // Permission denied, show a message or handle it accordingly
////                // For simplicity, I'm just showing a toast message here
////                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
////            }
////        }
////    }
//
//    private fun makePhoneCall() {
//        // getting phone number from edit text and changing it to String
////        val phone_number = edittext.text.toString()
//        val phone_number = 100
//
//        // Getting instance of Intent with action as ACTION_CALL
//        val phone_intent = Intent(Intent.ACTION_CALL)
//
//        // Set data of Intent through Uri by parsing phone number
//        phone_intent.data = Uri.parse("tel:$phone_number")
//
//        // start Intent
//        startActivity(phone_intent)
//    }
//
//    @SuppressLint("SetTextI18n")
//    private fun observeAuthenticationState() {
//        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
//            when (authenticationState) {
//                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
//                    binding.textView.text =
//                        ("Welcome, " + FirebaseAuth.getInstance().currentUser?.displayName)
//                }
//                else -> {
//                    launchSignInFlow()
//                }
//            }
//        })
//    }
//
//    private fun launchSignInFlow() {
//        // Give users the option to sign in / register with their email
//        // If users choose to register with their email,
//        // they will need to create a password as well
//        val providers = arrayListOf(
//            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
//            //
//        )
//
//        // Create and launch sign-in intent.
//        // We listen to the response of this activity with the
//        // SIGN_IN_RESULT_CODE code
//        startActivityForResult(
//            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
//                providers
//            ).setTheme(R.style.LoginTheme_NoActionBar)
//                .setLogo(R.drawable.women)
//                .build(), SIGN_IN_RESULT_CODE
//        )
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        if(requestCode == PERMISSION_SEND_SMS){
//            uiScope.launch {
//                withContext(Dispatchers.IO) {
//                    emergencyFun()
//                }
//            }
//        }
//    }
//
//    private fun getLocation() {
//        if (ActivityCompat.checkSelfPermission(
//                requireActivity(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                requireActivity(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Handle permission request if not granted
//            return
//        }
//        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//            if (location != null) {
//                lastLocation = location
//                Latitude = location.latitude.toString()
//                Longitude = location.longitude.toString()
//            }
//        }
//    }
//
//
//
//    private fun emergencyFun() {
//        val db =
//            Room.databaseBuilder(requireActivity(), GuardianDatabase::class.java, "GuardianDB").build()
//        val emailList: List<Guardian> = db.guardianDatabaseDao().getEmail()
//
//        var maillist: String = ""
//        val subject: String = "From FamSafe App"
//        val text: String = resources.getString(R.string.problem)
//        val text1 =
////            text.plus("https://www.google.com/maps/search/?api=1&query=$Latitude,$Longitude")
//            text.plus("https://www.google.com/maps/search/?api=1&query=28.752526,77.499031")
//
//        emailList.forEach() {
//            maillist = emailList.joinToString(separator = ",") { it -> it.guardianEmail }
//        }
//        emailList.forEach() {
//            val smsManager = SmsManager.getDefault() as SmsManager
//            smsManager.sendTextMessage(it.guardianPhoneNo, null, text1, null, null)
//        }
//
//        val shareIntent = Intent(Intent.ACTION_SEND)
//
//        shareIntent.setType("message/rfc822")
//            .putExtra(Intent.EXTRA_EMAIL, arrayOf(maillist))
//            .putExtra(Intent.EXTRA_SUBJECT, subject)
//            .putExtra(Intent.EXTRA_TEXT, text1)
//        startActivity(Intent.createChooser(shareIntent, "Send mail using.."))
//    }
//
//}


package com.example.otp_number

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.otp_number.FirebaseAuth.LoginViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.example.otp_number.database.Guardian
import com.example.otp_number.database.GuardianDatabase
import com.example.otp_number.databinding.FragmentDashBoardBinding
import kotlinx.coroutines.*

class DashBoardFragment : Fragment() {

    private lateinit var binding: FragmentDashBoardBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private var Latitude: String = ""
    private var Longitude: String = ""
    private val CALL_PERMISSION_REQUEST_CODE = 101
    private val PERMISSION_SEND_SMS = 102

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    companion object {
        const val TAG = "DashBoardFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dash_board, container, false
        )

        getLocation()

        binding.guardianButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_dashBoardFragment_to_guardianInfo2)
        }

        binding.locButton.setOnClickListener {view: View ->
//            val intent = Intent(context, MapsActivity::class.java)
//            startActivity(intent)
            view.findNavController().navigate((R.id.action_dashBoardFragment_to_fragmentMap))
        }

        binding.emerButton.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.SEND_SMS), PERMISSION_SEND_SMS)
            } else {
                uiScope.launch {
                    withContext(Dispatchers.IO) {
                        emergencyFun()
                    }
                }
            }
        }

        binding.sosbtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Request the permission
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CALL_PHONE),
                    CALL_PERMISSION_REQUEST_CODE
                )
            } else {
                makePhoneCall()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthenticationState()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                //User successfully signed in
            } else {
                // Sign in failed. If response is null, the user canceled the
                // sign-in flow using the back button. Otherwise, check
                // the error code and handle the error.
                Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }

    private fun makePhoneCall() {
        val phone_number = 100 // Assuming a fixed phone number

        val phone_intent = Intent(Intent.ACTION_CALL)
        phone_intent.data = Uri.parse("tel:$phone_number")
        startActivity(phone_intent)
    }

    @SuppressLint("SetTextI18n")
    private fun observeAuthenticationState() {
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    binding.textView.text =
                        ("Welcome, " + FirebaseAuth.getInstance().currentUser?.displayName)
                }
                else -> {
                    launchSignInFlow()
                }
            }
        })
    }

    private fun launchSignInFlow() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                providers
            ).setTheme(R.style.LoginTheme_NoActionBar)
                .setLogo(R.drawable.women)
                .build(), SIGN_IN_RESULT_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == PERMISSION_SEND_SMS){
            uiScope.launch {
                withContext(Dispatchers.IO) {
                    emergencyFun()
                }
            }
        }
    }

//    private fun getLocation() {
//        if (ActivityCompat.checkSelfPermission(
//                requireActivity(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                requireActivity(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//            if (location != null) {
//                lastLocation = location
//            }
//        }
//    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Handle permission request if not granted
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                lastLocation = location
                Latitude = location.latitude.toString()
                Longitude = location.longitude.toString()
            }
        }
    }

    private fun emergencyFun() {
        val db = Room.databaseBuilder(requireActivity(), GuardianDatabase::class.java, "GuardianDB").build()
        val guardianList: List<Guardian> = db.guardianDatabaseDao().getEmail()

        val text: String = resources.getString(R.string.problem)
//        val textWithLocation = text.plus("https://www.google.com/maps/search/?api=1&query=28.752526,77.499031")
        val textWithLocation = text.plus("https://www.google.com/maps/search/?api=1&query=$Latitude,$Longitude")
        val new = text.plus("Last Location was = $lastLocation")

        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "message/rfc822"
        val smsManager = SmsManager.getDefault()

        for (guardian in guardianList) {
            // Sending email
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(guardian.guardianEmail))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Emergency from FamSafe App")
//            emailIntent.putExtra(Intent.EXTRA_SUBJECT, new)
            emailIntent.putExtra(Intent.EXTRA_TEXT, textWithLocation)
            startActivity(Intent.createChooser(emailIntent, "Send mail using.."))

            // Sending SMS
            val sentPI = PendingIntent.getBroadcast(requireActivity(), 0, Intent("SMS_SENT"),
                PendingIntent.FLAG_IMMUTABLE)
            val deliveredPI = PendingIntent.getBroadcast(requireActivity(), 0, Intent("SMS_DELIVERED"),
                PendingIntent.FLAG_IMMUTABLE)
            smsManager.sendTextMessage(guardian.guardianPhoneNo, null, textWithLocation, sentPI, deliveredPI)
        }
    }
}

