package com.gforeroc.reignapp.view.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import com.gforeroc.reignapp.R
import com.gforeroc.reignapp.utils.ARGUMENT_DESCRIPTION
import com.gforeroc.reignapp.utils.ARGUMENT_DISMISS
import com.gforeroc.reignapp.utils.ARGUMENT_TITLE
import com.gforeroc.reignapp.view.callback.ErrorMessageListener
import kotlinx.android.synthetic.main.dialog_error_message.*

class ErrorDialog : AppCompatDialogFragment() {

    private var mListener: ErrorMessageListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.full_screen_dialog_style)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_error_message, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(ARGUMENT_TITLE)
                ?.let {
                    tvTitle?.setText(it)
                }
        arguments?.getInt(ARGUMENT_DESCRIPTION)
                ?.let {
                    tvDescription?.setText(it)
                }
        arguments?.getBoolean(ARGUMENT_DISMISS)
                ?.let {
                    if (it) {
                        ivClose?.visibility = View.VISIBLE
                        ivClose?.setOnClickListener {
                            mListener?.onDismissClicked()
                            dismiss()
                        }
                    } else {
                        ivClose?.visibility = View.GONE
                    }
                }
        btnTryAgain?.setOnClickListener {
            mListener?.tryAgainAction()
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ErrorMessageListener) {
            mListener = context
        }
    }

    companion object {
        fun newInstance(
            titleResId: Int = R.string.connection_error_title,
            descriptionResId: Int = R.string.connection_error_message,
            showDismiss: Boolean = true
        ): ErrorDialog {
            return ErrorDialog().apply {
                arguments = Bundle().apply {
                    putInt(ARGUMENT_TITLE, titleResId)
                    putInt(ARGUMENT_DESCRIPTION, descriptionResId)
                    putBoolean(ARGUMENT_DISMISS, showDismiss)
                }
            }
        }
    }
}
