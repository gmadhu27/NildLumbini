package ourproject.nildlumbini.Fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ourproject.nildlumbini.Item_Adap;
import ourproject.nildlumbini.MainActivity;
import ourproject.nildlumbini.MyList;
import ourproject.nildlumbini.RetrieveData;
import ourproject.nildlumbini.UserProfileActivity;

/**
 * Created by suman on 10/8/2017.
 */

public class GetDataForFragments {
    FragmentActivity activity;
    String option;
    static RecyclerView myRecyle;
    UserProfileActivity activitya;
    ArrayList<RetrieveData> arrayList;

    public GetDataForFragments(FragmentActivity activity,  String option, RecyclerView myRecyle){
        this.activity = activity;
        this.option = option;
        this.myRecyle = myRecyle;
    }



    public GetDataForFragments(UserProfileActivity applicationContext, String option, RecyclerView userProfileRecycler, String t) {
        this.activitya =  applicationContext;
        this.option = option;
        this.myRecyle = userProfileRecycler;
    }

    public void loadData(){

        arrayList= new ArrayList<>();
        ArrayList<RetrieveData> arrayList1=MyList.arrayList();
        for(RetrieveData r:arrayList1)
        {
            if(r.option.equals(option))
            {
                arrayList.add(new RetrieveData(r.name, option,r.title,r.article, r.imgUrl, r.date));
            }
        }
    }

    public void setData(){
        myRecyle.setAdapter(new Item_Adap(arrayList, activity));

    }

    public void loadDataA(){

        arrayList= new ArrayList<>();
        ArrayList<RetrieveData> arrayList1=MyList.arrayList();
        for(RetrieveData r:arrayList1)
        {
            if(r.name.equals(option))
            {
                arrayList.add(new RetrieveData(r.name, option,r.title,r.article, r.imgUrl, r.date));
            }
        }
    }
    public void setDataA(){
        myRecyle.setAdapter(new Item_Adap(arrayList, activitya, "userProfile"));
//        myRecyle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }
}
