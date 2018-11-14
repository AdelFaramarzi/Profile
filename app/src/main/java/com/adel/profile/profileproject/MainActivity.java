package com.adel.profile.profileproject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private View fullNameContainer;
    private EditText firstNameEt;
    private EditText lastNameEt;
    private Button editProfileBtn;
    private TextView fullNameTextView;
    private CheckBox androidCheckBox;
    private CheckBox cssCheckBox;
    private CheckBox deepLearningCheckBox;
    private RadioGroup cityRadioGroup;
    private Button saveInformationBtn;
    private boolean isInEditMode;
    private SharePerManager sharePerManager;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharePerManager = new SharePerManager(this);
        firstNameEt = findViewById(R.id.et_profile_firstName);
        lastNameEt = findViewById(R.id.et_profile_lastName);
        fullNameTextView = findViewById(R.id.tv_profile_fullName);
        fullNameContainer = findViewById(R.id.linearLayout_profile_fullNameContainer);

        if(sharePerManager.getFirstName()!=null &&
                sharePerManager.getLastName() != null){
            fullNameTextView.setText((sharePerManager.getFirstName()+""+
            sharePerManager.getLastName()));
        }

        editProfileBtn = findViewById(R.id.btn_profile_fullName);
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInEditMode) {
                    editProfileBtn.setText(getString(R.string.profile_editProfile));
                    fullNameContainer.setVisibility(View.GONE);
                    fullNameTextView.setText(firstNameEt.getText() +
                            " "
                            + lastNameEt.getText());

                    sharePerManager.saveFullName(
                            firstNameEt.getText().toString(),
                            lastNameEt.getText().toString());


                } else {
                    editProfileBtn.setText(getString(R.string.profile_done));
                    fullNameContainer.setVisibility(View.VISIBLE);
                }

                isInEditMode = !isInEditMode;
            }
        });

        androidCheckBox = findViewById(R.id.checkbox_profile_android);
        androidCheckBox.setOnCheckedChangeListener(this);
        cssCheckBox = findViewById(R.id.checkbox_profile_css);
        cssCheckBox.setOnCheckedChangeListener(this);
        deepLearningCheckBox = findViewById(R.id.checkbox_profile_deep);
        deepLearningCheckBox.setOnCheckedChangeListener(this);

        cityRadioGroup = findViewById(R.id.radioGroup_profile);
        cityRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                Toast.makeText(MainActivity.this, getString(R.string.profile_isCheckedMessaged,
                        ((RadioButton) findViewById(checkId)).getText().toString()), Toast.LENGTH_SHORT).show();
            }
        });
        saveInformationBtn = findViewById(R.id.btn_saveInformation);
        saveInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), getString(R.string.profile_clickSaveInformation),
                        Toast.LENGTH_SHORT).show();
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
    }




    private static final String TAG = "MainActivity";

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            Toast.makeText(MainActivity.this, getString(R.string.profile_isCheckedMessaged, compoundButton.getText()),
                    Toast.LENGTH_LONG).show();
        }
    }


}

