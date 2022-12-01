package net.hafiznaufalr.movie.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class MovieBaseActivity<T : ViewBinding> : BaseActivity() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> T

    @Suppress("UNCHECKED_CAST")
    protected val binding: T
        get() = _binding as T

    abstract fun init()

    abstract fun initData()

    abstract fun initListener()

    abstract fun observer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        init()
        initData()
        initListener()
        observer()
    }
}