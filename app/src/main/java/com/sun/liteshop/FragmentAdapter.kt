package com.sun.liteshop

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragmentAdapter(supportFragmentManager: FragmentManager?, fragments: ArrayList<Fragment>, titles: ArrayList<String>)
    : FragmentPagerAdapter(supportFragmentManager) {
    private var titles=titles
    private var fragments=fragments

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->return titles[0]
            1->return titles[1]
            2->return titles[2]
            else -> return "轻商城"
        }
    }
}
