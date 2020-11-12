package com.gforeroc.reignapp.view.callback

import com.gforeroc.reignapp.R


interface IProgressDialogContract {

    fun showProgressDialog(messageStringResource: Int = R.string.uploading_info)

    fun hideProgressDialog()
}
