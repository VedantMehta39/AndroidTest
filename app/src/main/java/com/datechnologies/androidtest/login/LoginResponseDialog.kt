package com.datechnologies.androidtest.login

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.datechnologies.androidtest.MainActivity
import com.datechnologies.androidtest.R
import com.datechnologies.androidtest.api.login.LoginDialogModel

class LoginResponseDialog(private val response: LoginDialogModel) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.login_response_dialog, null)
        val codeTextView = view.findViewById<TextView>(R.id.response_code)
        val messageTextView = view.findViewById<TextView>(R.id.response_message)
        val timeTextView = view.findViewById<TextView>(R.id.response_time)
        codeTextView.text = response.code
        messageTextView.text = response.message
        timeTextView.text = response.responseTime.toString()
        val builder = AlertDialog.Builder(requireContext())
        val dialog = builder.setView(view).setPositiveButton(
            "Ok"
        ) { _, _ ->
            if(response.code == "Success"){
                requireContext().startActivity(Intent(requireContext(), MainActivity::class.java))
            }
            else{
                dismiss()
            }
        }
        return dialog.create()
    }
}