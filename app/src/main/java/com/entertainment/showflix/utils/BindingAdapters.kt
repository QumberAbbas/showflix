package com.entertainment.showflix.utils

import android.R
import android.databinding.BindingAdapter
import android.text.TextWatcher
import android.view.View
import android.widget.*


@BindingAdapter("error")
fun setError(autoCompleteTextView: AutoCompleteTextView, strOrResId: Any?) {
    if (strOrResId is Int) {
        autoCompleteTextView.error = autoCompleteTextView.context.getString(strOrResId)
    } else {
        autoCompleteTextView.error = strOrResId?.let { it as String } ?: null
    }

}

@BindingAdapter("onFocus")
fun bindFocusChange(autoCompleteTextView: AutoCompleteTextView, onFocusChangeListener: View.OnFocusChangeListener?) {
    if (autoCompleteTextView.onFocusChangeListener == null) {
        autoCompleteTextView.onFocusChangeListener = onFocusChangeListener
    }
}

@BindingAdapter("entries")
fun setEntries(spinner: Spinner, entries: List<String>?) {
    val adapter = ArrayAdapter<String>(spinner.context, R.layout.simple_list_item_1, entries);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner.adapter = adapter
}

@BindingAdapter("onItemSelected")
fun setItemSelectedListener(spinner: Spinner, itemSelectedListener : AdapterView.OnItemSelectedListener?) {
    spinner.setOnItemSelectedListener(itemSelectedListener)
}

@BindingAdapter("onTextChanged")
fun setTextChangedListener(textView: TextView, textWatcher: TextWatcher) {
    textView.addTextChangedListener(textWatcher)
}
