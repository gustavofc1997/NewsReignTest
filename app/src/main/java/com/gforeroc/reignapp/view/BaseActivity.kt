package com.gforeroc.reignapp.view

import android.app.AlertDialog
import android.view.View
import com.gforeroc.reignapp.view.callback.ErrorMessageListener
import com.gforeroc.reignapp.view.callback.IProgressDialogContract
import com.gforeroc.reignapp.view.dialog.ErrorDialog
import com.gforeroc.reignapp.view.dialog.SimpleProgressDialog
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity : DaggerAppCompatActivity(), BaseView, ErrorMessageListener,
    IProgressDialogContract {

    private var progressDialog: AlertDialog? = null
    override fun showErrorDialog(error: String) {
        supportFragmentManager.beginTransaction()
            .add(ErrorDialog.newInstance(), ErrorDialog::class.java.name)
            .commitAllowingStateLoss()
    }

    override fun showErrorDialog(errorStringId: Int) {
        try {
            showErrorDialog(getString(errorStringId))
        } catch (e: Exception) {
            print(e)
        }
    }

    override fun showError(error: String) {
        window?.decorView?.findViewById<View>(android.R.id.content)
            ?.let {
                Snackbar.make(it, error, Snackbar.LENGTH_LONG)
                    .show()
            }
    }

    override fun showError(errorStringId: Int) {
        showError(getString(errorStringId))
    }

    override fun tryAgainAction() {}

    override fun onDismissClicked() {}


    override fun showProgressDialog(
        messageStringResource: Int
    ) {
        if (progressDialog == null) {
            progressDialog = SimpleProgressDialog
                .Builder(
                    this,
                    getString(messageStringResource)
                )
                .create()
        }

        progressDialog?.show()
    }

    override fun hideProgressDialog() {
        progressDialog?.hide()
    }

    override fun onPause() {
        progressDialog?.let {
            if (it.isShowing) {
                it.cancel()
            }
        }
        super.onPause()
    }
}
