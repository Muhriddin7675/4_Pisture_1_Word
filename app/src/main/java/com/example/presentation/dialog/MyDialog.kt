package com.example.presentation.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.airbnb.lottie.LottieAnimationView
import com.example.findwordkotlin.R


class MyDialog : DialogFragment() {
    var listener: SelectListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.win_dialog, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animation = view.findViewById<LottieAnimationView>(R.id.coinLottieAnimation)
        animation.playAnimation()

        val delayMilliseconds: Long = 4500 // 5 sekund

        val handler = Handler()
        val runnable = Runnable {
            animation.pauseAnimation()
        }
        handler.postDelayed(runnable, delayMilliseconds)

        view.findViewById<View>(R.id.nextButton).setOnClickListener { v: View? ->
            listener!!.next()
            dismiss()
        }
        view.findViewById<View>(R.id.menuButton).setOnClickListener { v: View? ->
            listener!!.menu()
            dismiss()
        }
    }

    fun setSelectListener(listener: SelectListener?) {
        this.listener = listener
    }

    override fun onStart() {
        super.onStart()
        val width = resources.displayMetrics.widthPixels * 0.9    // 90% of screen width
        val height = ViewGroup.LayoutParams.WRAP_CONTENT         // Automatic height
        dialog?.window?.setLayout(width.toInt(), height)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
    }
}