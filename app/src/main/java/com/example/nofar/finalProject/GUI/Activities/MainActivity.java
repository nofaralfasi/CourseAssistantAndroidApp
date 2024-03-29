package com.example.nofar.finalProject.GUI.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nofar.finalProject.GUI.Dialogs.AddStudyGroupDialog;
import com.example.nofar.finalProject.GUI.Fragments.UserListFragment;
import com.example.nofar.finalProject.LOGIC.Interfaces.ShowDialogStudyGroupListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.nofar.finalProject.GUI.Dialogs.AddDialog;
import com.example.nofar.finalProject.GUI.Dialogs.AddExamDialog;
import com.example.nofar.finalProject.GUI.Dialogs.AddHWDialog;
import com.example.nofar.finalProject.GUI.Fragments.CalendarFragment;
import com.example.nofar.finalProject.GUI.Fragments.ClassesFragment;
import com.example.nofar.finalProject.GUI.Fragments.ExamsFragment;
import com.example.nofar.finalProject.GUI.Fragments.HomeWorkFragment;
import com.example.nofar.finalProject.GUI.Fragments.StudyGroupsFragment;
import com.example.nofar.finalProject.LOGIC.Core.User;
import com.example.nofar.finalProject.LOGIC.Interfaces.OnHWDialogListener;
import com.example.nofar.finalProject.LOGIC.Interfaces.RefreshDataSetListener;
import com.example.nofar.finalProject.LOGIC.Interfaces.ShowDialogExamListener;
import com.example.nofar.finalProject.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RefreshDataSetListener, ShowDialogExamListener, OnHWDialogListener, ShowDialogStudyGroupListener
{
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private int CurrentFragment;
    private String[] toolbarTitles;
    private ActionBarDrawerToggle toggle;
    private HomeWorkFragment homeWorkFragment;
    private ClassesFragment classesFragment;
    private CalendarFragment calendarFragment;
    private ExamsFragment examsFragment;
    private StudyGroupsFragment studyGroupsFragment;
    private UserListFragment userListFragment;
    private ImageView signOut;
    private Fragment fragment;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        homeWorkFragment = new HomeWorkFragment();
        homeWorkFragment.setOnHWDialogListener(this);
        classesFragment = new ClassesFragment();
        calendarFragment = new CalendarFragment();
        studyGroupsFragment = new StudyGroupsFragment();
        studyGroupsFragment.setDialogStudyGroupListener(this);
        examsFragment = new ExamsFragment();
        examsFragment.setDialogExamListener(this);
        userListFragment=new UserListFragment();
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR}, 0);
        }
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navView);
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.userEmail)).setText(User.Student.getEmail());
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.userFullName)).setText(User.Student.getUserName());
        signOut = navigationView.getHeaderView(0).findViewById(R.id.logOutButton);
        navigationView.setNavigationItemSelectedListener(this);
        toolbarTitles = getResources().getStringArray(R.array.nav_titles);
        CurrentFragment = 0;
        SetCurrentFragment();
        signOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mAuth.signOut();
                myRef.child(User.Student.getUserID()).setValue(User.Student);
                for (int i = 0; i < User.Student.getCourses().size(); i++)
                {
                    User.Student.RemoveClassElements(i, getApplicationContext());
                }
                User.Student = null;
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

    private void SetCurrentFragment()
    {
        SetItemFocus();
        SetToolBarTitle();
        FragmentTransaction fragmentTransaction;
        switch (CurrentFragment)
        {
            case 0:
                fragment = homeWorkFragment;
                break;
            case 1:
                fragment = classesFragment;
                break;
            case 2:
                fragment = examsFragment;
                break;
            case 3:
                fragment = calendarFragment;
                break;
            case 4:
                fragment = studyGroupsFragment;
                break;
            case 5:
                fragment = userListFragment;
                break;
            default:
                fragment = homeWorkFragment;
        }

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commitAllowingStateLoss();

    }

    private void SetToolBarTitle()
    {
        ((TextView) findViewById(R.id.toolbarTitleText)).setText(this.toolbarTitles[CurrentFragment]);
    }

    private void SetItemFocus()
    {
        if (navigationView.getMenu().getItem(CurrentFragment).isChecked() == false)
        {
            navigationView.getMenu().getItem(CurrentFragment).setChecked(true);
        }
    }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();
        if (id == R.id.addToolBarButton)
        {
            showDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int fragmentNumber = 0;
        switch (item.getItemId())
        {
            case R.id.navHomeWork:
                fragmentNumber = 0;
                break;
            case R.id.navClasses:
                fragmentNumber = 1;
                break;
            case R.id.navExams:
                fragmentNumber = 2;
                break;
            case R.id.navCalander:
                fragmentNumber = 3;
                break;
            case R.id.navStudyGroups:
                fragmentNumber = 4;
                break;
            case R.id.navChatRoom:
                fragmentNumber = 5;
                break;
        }

        if (fragmentNumber != CurrentFragment)
        {
            CurrentFragment = fragmentNumber;
            SetCurrentFragment();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showDialog()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddDialog newFragment = new AddDialog();
        newFragment.setRefreshDataSetListener(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
    }

    @Override
    public void ShowDialogExam(int pos)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddExamDialog examDialog = new AddExamDialog();
        Bundle bundle = new Bundle();
        bundle.putString("opt", String.valueOf(pos));
        examDialog.setArguments(bundle);
        examDialog.setRefreshDataSetListener(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, examDialog).addToBackStack(null).commit();
    }

    @Override
    public void ShowDialogStudyGroup(int pos)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddStudyGroupDialog studyGroupDialog = new AddStudyGroupDialog();
        Bundle bundle = new Bundle();
        bundle.putString("opt", String.valueOf(pos));
        studyGroupDialog.setArguments(bundle);
        studyGroupDialog.setRefreshDataSetListener(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, studyGroupDialog).addToBackStack(null).commit();
    }


    @Override
    public void RefreshDataSet()
    {
        ((RefreshDataSetListener) fragment).RefreshDataSet();
    }

    @Override
    public void OnHWDialog(String index)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddHWDialog addHWDialog = new AddHWDialog();
        Bundle bundle = new Bundle();
        bundle.putString("opt", String.valueOf(index));
        addHWDialog.setArguments(bundle);
        addHWDialog.setRefreshDataSetListener(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, addHWDialog).addToBackStack(null).commit();
    }
}
