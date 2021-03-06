package ourproject.nildlumbini;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ourproject.nildlumbini.Fragment.GetDataForFragments;

public class UserProfileActivity extends AppCompatActivity {

    Button userProfileAddButton;
    RecyclerView userProfileRecycler;
    FirebaseAuth mauth;
    Toolbar toolbar;

    //AlertDialogManage alt;

    static  String option = "";

    ImageView deletePost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbarFi);
        setSupportActionBar(toolbar);
        getSupportActionBar();
        mauth= FirebaseAuth.getInstance();
        option = mauth.getCurrentUser().getEmail().toString();
        //userProfileAddButton = (Button) findViewById(R.id.user_profile_add_button);
        userProfileRecycler = (RecyclerView) findViewById(R.id.user_profile_recycler);
        deletePost = (ImageView)findViewById(R.id.dlt);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UserProfileActivity.this, LinearLayoutManager.VERTICAL, false);
        userProfileRecycler.setLayoutManager(layoutManager);
        userProfileRecycler.setItemAnimator(new DefaultItemAnimator());

        GetDataForFragments g =  new GetDataForFragments(UserProfileActivity.this, option, userProfileRecycler, "e");
        g.loadDataA();
        g.setDataA();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfileActivity.this, DiaLog_Add.class));
            }
        });

    }

    public static void onPressStart(){

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                GetDataForFragments g =  new GetDataForFragments(UserProfileActivity.this, option, userProfileRecycler, "e");
                g.loadDataA();
                g.setDataA();
            }
        },5000);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pro,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.user_logout) {
            AlertDialogManage();
            //   alt.AlertManage();

        }
        else if(id == R.id.user_additm){
            startActivity(new Intent(UserProfileActivity.this, DiaLog_Add.class));

        }
        return true;
    }



   private void AlertDialogManage() {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(UserProfileActivity.this);
        alertbox.setTitle("Are you sure want to logout??");
        alertbox.setCancelable(false);
        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mauth.signOut();
                startActivity(new Intent(UserProfileActivity.this,MainActivity.class));
                finish();
            }
        });
        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertbox.show();
    }

}