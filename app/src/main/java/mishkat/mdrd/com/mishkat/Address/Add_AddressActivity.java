package mishkat.mdrd.com.mishkat.Address;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mishkat.mdrd.com.mishkat.Address.Model.SaveAddressModel;
import mishkat.mdrd.com.mishkat.Data.SharePrefarence;
import mishkat.mdrd.com.mishkat.R;
import mishkat.mdrd.com.mishkat.SearchVanders.Models.AreasModel;
import mishkat.mdrd.com.mishkat.UtilsTools.UtilsTool;
import mishkat.mdrd.com.mishkat.api.API;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Deepak Jaiswal on 15-0-2019.
 */

public class Add_AddressActivity extends AppCompatActivity {

    Spinner sp_Address_Types;
    List<String> TypeArray = new ArrayList<>();
    public List<AreasModel.ResultEntity> AreasListData = new ArrayList<>();
    private LinearLayout linner2;
    private ImageView imageView2;
    private LinearLayout linearLayout2;
    private TextInputEditText AddressName;
    private TextInputEditText AddressArea;
    private Spinner spAddressTypes;
    private TextInputEditText AddressBlock;
    private TextInputEditText AddressStreet;
    private TextInputEditText AddressAvenue;
    private TextInputEditText AddressBuilding;
    private TextInputEditText AddressFloor;
    private TextInputEditText AddressApartmentsNo;
    private EditText AddressAdditonal;
    private Button BtAddnewAddress;
    int Userid;
    String mAddressName, mAddressArea, mspAddressTypes, mAddressBlock, mAddressStreet, mAddressAvenue, mAddressBuilding, mAddressFloor, mAddressApartmentsNo, mAddressAdditonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__address);
        sp_Address_Types = findViewById(R.id.sp_Address_Types);
        BtAddnewAddress = findViewById(R.id.Bt_AddnewAddress);
        Userid = SharePrefarence.getmInstance(getApplication()).getUserId();
        GetAreas();

        BtAddnewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAddressName = AddressName.getText().toString().trim();
                mAddressArea = AddressArea.getText().toString().trim();
                mspAddressTypes = spAddressTypes.getSelectedItem().toString().trim();
                mAddressBlock = AddressBlock.getText().toString().trim();
                mAddressStreet = AddressStreet.getText().toString().trim();
                mAddressAvenue = AddressAvenue.getText().toString().trim();
                mAddressBuilding = AddressBuilding.getText().toString().trim();
                mAddressFloor = AddressFloor.getText().toString().trim();
                mAddressApartmentsNo = AddressApartmentsNo.getText().toString().trim();
                mAddressAdditonal = AddressAdditonal.getText().toString().trim();
               /* String mPassword = signUpPasswordEt.getText().toString().trim();
                String mRePassword = signUpConfirmPasswordEt.getText().toString().trim();*/
                if (UtilsTool.isInternetOn(Add_AddressActivity.this)) {
                    if (!UtilsTool.isEmpty(AddressName) && !UtilsTool.isEmpty(AddressArea) && !UtilsTool.isEmpty(AddressBlock) && !UtilsTool.isEmpty(AddressStreet) && !UtilsTool.isEmpty(AddressAvenue) && !UtilsTool.isEmpty(AddressFloor) && !UtilsTool.isEmpty(AddressBuilding) && !UtilsTool.isEmpty(AddressFloor)) {

                        SaveAddress(Userid + "", mAddressName + "", mAddressArea + "", mAddressBlock + "",
                                mAddressStreet + "", mAddressAvenue + "", mAddressFloor  + "",  mAddressBuilding+ "",
                                mAddressAdditonal + "", "1", "1");

                    } else {
                        UtilsTool.ChackNull(AddressName);
                        UtilsTool.ChackNull(AddressArea);
                        UtilsTool.ChackNull(AddressBlock);
                        UtilsTool.ChackNull(AddressStreet);
                        UtilsTool.ChackNull(AddressAvenue);
                        UtilsTool.ChackNull(AddressBuilding);
                        UtilsTool.ChackNull(AddressFloor);
                        UtilsTool.ChackNull(AddressApartmentsNo);
                    }
                } else {
                    UtilsTool.customMessage(Add_AddressActivity.this, "Please check your Internet connection!");
                }

            }
        });


        initView();
    }


    private void GetAreas() {
        API.getAPIService().GetAreas().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AreasModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(AreasModel response) {
                        if (response.getStatus()) {
                            if (response.getResult().size() > 0) {
                                AreasListData = response.getResult();
                                for (int i = 0; i < AreasListData.size(); i++) {
                                    TypeArray.add(AreasListData.get(i).getName());
                                }
                                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                                        Add_AddressActivity.this, R.layout.spinner_item, TypeArray);
                                adapter2.setDropDownViewResource(R.layout.spinner_itemc);
                                sp_Address_Types.setAdapter(adapter2);

                            }
                        }
                    }
                });
    }

    private void initView() {
        linner2 = (LinearLayout) findViewById(R.id.linner2);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        AddressName = (TextInputEditText) findViewById(R.id.Address_Name);
        AddressArea = (TextInputEditText) findViewById(R.id.Address_Area);
        spAddressTypes = (Spinner) findViewById(R.id.sp_Address_Types);
        AddressBlock = (TextInputEditText) findViewById(R.id.Address_Block);
        AddressStreet = (TextInputEditText) findViewById(R.id.Address_Street);
        AddressAvenue = (TextInputEditText) findViewById(R.id.Address_Avenue);
        AddressBuilding = (TextInputEditText) findViewById(R.id.Address_Building);
        AddressFloor = (TextInputEditText) findViewById(R.id.Address_Floor);
        AddressApartmentsNo = (TextInputEditText) findViewById(R.id.Address_Apartments_No);
        AddressAdditonal = (EditText) findViewById(R.id.Address_Additonal);
        BtAddnewAddress = (Button) findViewById(R.id.Bt_AddnewAddress);
    }

    private void SaveAddress(String customer_id, String title, String area, String block, String street, String house, String flat, String apartment, String notes, String city_id, String area_id) {
        UtilsTool.showProgressDialog(Add_AddressActivity.this);
        API.getAPIService().AddAddress(SharePrefarence.ENGLISH, customer_id, title, area, block, street, house, flat, apartment, notes, city_id, area_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SaveAddressModel>() {
                    @Override
                    public void onCompleted() {
                        UtilsTool.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        UtilsTool.hideProgressDialog();
                        UtilsTool.custoAlert(Add_AddressActivity.this,e.getMessage());
                    }

                    @Override
                    public void onNext(SaveAddressModel response) {
                        if (response.getStatus()) {
                            UtilsTool.customMessage(Add_AddressActivity.this, response.getMessage() + "");
                            finish();
                        }
                    }
                });
    }
}
