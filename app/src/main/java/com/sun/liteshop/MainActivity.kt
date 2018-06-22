package com.sun.liteshop

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.sun.liteshop.fragments.HomeFragment
import org.jetbrains.anko.toast
import android.widget.Toast
import com.miguelcatalan.materialsearchview.MaterialSearchView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.widget.FrameLayout
import com.sun.liteshop.fragments.GroupBuyFragment
import com.sun.liteshop.fragments.HotBuyFragment
import com.sun.liteshop.login.LoginEvent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.headerlayout.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.imageBitmap
import org.litepal.LitePal

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var loginItem:MenuItem
    private lateinit var logoutItem:MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){
        toolbar.hideOverflowMenu()
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        initViewPager()
        LitePal.getDatabase()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home ->{
                tabs.setScrollPosition(0,0f,true)
            }
            R.id.shopping_car->{
                toast("you click shoppingcar")
            }
            R.id.person_center->{
                toast("you click person center")
            }
            R.id.login->{
                startActivity(Intent(this,LoginActivity::class.java))
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    @Subscribe(threadMode= ThreadMode.MAIN)
    fun onLoginEvent(loginEvent: LoginEvent){
        icon_image.imageBitmap=BitmapFactory.decodeResource(resources,R.drawable.nav_icon)
        tv_nickname.text=loginEvent.nickname
        loginItem.setVisible(false)
        logoutItem.setVisible(true)
    }

    /*private fun addFragment(){
        var homeFragment=HomeFragment.newInstance()
        var manager=supportFragmentManager
        var transaction=manager.beginTransaction()
        transaction.add(R.id.fg_home,homeFragment)
        transaction.commit()
    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        loginItem=menu!!.findItem(R.id.login)
        logoutItem=menu!!.findItem(R.id.logout)
        return false
    }

    fun initViewPager(){
        var titles= ArrayList<String>()
        titles.add("首页")
        titles.add("热卖")
        titles.add("团购")

        for(title in titles.iterator()){
            tabs.addTab(tabs.newTab().setText(title))
        }
        var fragments=ArrayList<Fragment>()
        fragments.add(HomeFragment.newInstance())
        fragments.add(HotBuyFragment.newInstance())
        fragments.add(GroupBuyFragment.newInstance())

        var mFragmentAdapter=
                FragmentAdapter(supportFragmentManager,fragments,titles)
        view_pager.adapter=mFragmentAdapter
        tabs.tabGravity=Gravity.CENTER
        tabs.setupWithViewPager(view_pager,true)
    }

}
