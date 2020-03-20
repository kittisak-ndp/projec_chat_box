package com.example.chat_box

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager
import java.util.jar.Attributes


class profile : Fragment() {
    var PhotoURL : String = ""
    var Name : String = ""

    fun newInstance(url: String,name : String): profile {
        val profile = profile()
        val bundle = Bundle()
        bundle.putString("PhotoURL", url)
        bundle.putString("Name", name)
        profile.setArguments(bundle)
        return profile
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val ivProfilePicture = view.findViewById(R.id.iv_profile) as ImageView
        val tvName = view.findViewById(R.id.tv_name) as TextView
        val login_button2 = view.findViewById(R.id.login_button2) as Button
        val chat_list  = view.findViewById(R.id.chat_list) as Button
        val history  = view.findViewById(R.id.history) as Button

        Glide.with(activity!!.baseContext)
            .load(PhotoURL)
            .into(ivProfilePicture)
        tvName.setText(Name)

        login_button2.setOnClickListener{
            LoginManager.getInstance().logOut()
            activity!!.supportFragmentManager.popBackStack()
        }

        this.change_page_chat_list()

        chat_list.setOnClickListener {
            this.change_page_chat_list()
        }
        history.setOnClickListener {
            this.chenge_page_history()
        }

        // Inflate the layout for this fragment
        return view

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            PhotoURL = bundle.getString("PhotoURL").toString()
            Name = bundle.getString("Name").toString()

        }

    }

    fun change_page_chat_list(){
        val Chat_page = Chat_page().newInstance(Name.toString())
        val fm = fragmentManager
        val transaction : FragmentTransaction = fm!!.beginTransaction()
        transaction.replace(R.id.Show_layout, Chat_page,"Chat_page")
        transaction.addToBackStack("Chat_page")
        transaction.commit()
    }

    fun chenge_page_history(){
        val History = History()
        val fm = fragmentManager
        val transaction : FragmentTransaction = fm!!.beginTransaction()

        transaction.replace(R.id.Show_layout, History,"History")
        transaction.addToBackStack("History")
        transaction.commit()
    }
}
