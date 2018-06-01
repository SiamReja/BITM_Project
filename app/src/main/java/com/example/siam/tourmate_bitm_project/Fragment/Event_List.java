package com.example.siam.tourmate_bitm_project.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siam.tourmate_bitm_project.ModelClass.Event;
import com.example.siam.tourmate_bitm_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Event_List extends Fragment {
    TextView add_event_TV;
    ListView event_list_View;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String TAG = "            Event list ";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_list_f, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        add_event_TV = view.findViewById(R.id.add_event_TV);
        event_list_View = view.findViewById(R.id.event_listView);


        add_event_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert();
            }
        });
    }

    private void Alert() {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        final LayoutInflater inflater = this.getLayoutInflater();


        final View dialogView;


        dialogView = inflater.inflate(R.layout.customdialogwithinput, null);
        dialogBuilder.setView(dialogView);
        final EditText event_Name_et = dialogView.findViewById(R.id.event_Name_ET_ID);
        final EditText starting_location_et = dialogView.findViewById(R.id.location_ET_ID);
        final EditText destination_et = dialogView.findViewById(R.id.destination_ET_ID);
        final EditText journey_date_et = dialogView.findViewById(R.id.journey_Date_ET_ID);
        final EditText budget_et = dialogView.findViewById(R.id.budget_ET_ID);


//        circleImageView = (CircleImageView) dialogView.findViewById(R.id.circle_image_view);

//set Action on Image view

//        circleImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*")
//                        .setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "select image"), profile_image_Code);
//                select_img_ET.setVisibility(View.GONE);
//            }
//        });
        dialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                submit_Data_To_Database(event_Name_et.getText().toString(), starting_location_et.getText().toString(),
                        destination_et.getText().toString(), journey_date_et.getText().toString(), budget_et.getText().toString());
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        }).setCancelable(false).create();

        dialogBuilder.show();
    }

    private void submit_Data_To_Database(String s, String s1, String s2, String s3, String s4) {
        Log.e(TAG, "submit_Data_To_Database: " + s);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Event event = new Event(s, s1, s2, s3, s4);
        databaseReference.child("Event List").setValue(event);


    }
}
