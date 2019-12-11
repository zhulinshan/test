package com.zls.filepickerdemo;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ess.filepicker.FilePicker;
import com.ess.filepicker.model.EssFile;
import com.ess.filepicker.util.Const;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick(View view) {
        switch (view.getId()){
            case R.id.btn_picker_imagOrVideo:
                FilePicker.from(this)
                        .chooseMedia()
                        .enabledCapture(true)
                        .setMaxCount(6)
                        .requestCode(220)
                        .start();
                break;
            case R.id.btn_picker_file:
                FilePicker.from(this)
                        .chooseForMimeType()
                        .setMaxCount(6)
                        .setMaxSize(2)
                        .setFileTypes("xls","doc","pdf","zip","rar")
                        .requestCode(221)
                        .start();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 220){
                ArrayList<EssFile> essFileList = data.getParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION);
                for (EssFile essFile: essFileList){
                    Log.d("tag","essfile====="+essFile.getAbsolutePath());
                    Log.d("tag","size======="+getFileSize(essFile.getAbsolutePath()));
                }
            }else if (requestCode == 221){
                ArrayList<EssFile> essFileList = data.getParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION);
                for (EssFile essFile: essFileList){
                    Log.d("tag","essfile====="+essFile.getAbsolutePath());
                }
            }
        }
    }


    private int getFileSize(String path){
        try{
            FileInputStream fis = new FileInputStream(new File(path));
            int available = fis.available();
            fis.close();
            return available;
        }catch (Exception e){

        }finally {
        }
        return 0;
    }
}
