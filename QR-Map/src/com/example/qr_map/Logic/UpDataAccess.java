package com.example.qr_map.Logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;

import java.net.HttpURLConnection; 
import java.net.URL; 
import java.net.MalformedURLException; 
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.io.*; 

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class UpDataAccess 
{
	Context context; 
	int version;
	String[] no = new String[]{"no"};
	public UpDataAccess(Context g,String vers){
		//context =g;
		version=Integer.parseInt(vers);
	}
	
	public String[] Getlab() throws InterruptedException, ExecutionException{
		if(getversion()!=version){
		String[] lab;
		UpdateTask j= new UpdateTask(context);
		j.execute("http://alubas.com.ua/project/Laboratory.txt");
		lab=j.get();
		return lab;}else{return no;}
	}
	public String[] GetEquipment() throws InterruptedException, ExecutionException{
		if(getversion()!=version){
		String[] equipment;
		UpdateTask j= new UpdateTask(context);
		j.execute("http://alubas.com.ua/project/LabEquipment.txt");
		equipment=j.get();
		return equipment;}else{return no;}
	}
    public String[] GetSponsor() throws InterruptedException, ExecutionException{
    	if(getversion()!=version){
    	String[] sponsor;
		UpdateTask j= new UpdateTask(context);
    	j.execute("http://alubas.com.ua/project/Sponsor.txt");
		sponsor=j.get();
		return sponsor;}else{return no;}
	}
    private int getversion() throws InterruptedException, ExecutionException{
    	String[] vers;
    	UpdateTask j= new UpdateTask(context);
    	j.execute("http://alubas.com.ua/project/BD.txt");
		vers=j.get();
		int i=Integer.parseInt(vers[0]);
		return i;
    }
}
class UpdateTask extends AsyncTask<String, String, String[]> {
    Context context;
    public UpdateTask(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected String[] doInBackground(String... url){
    	try {
            URL urlr = new URL(url[0]);

            HttpURLConnection con = (HttpURLConnection) urlr.openConnection();
            
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            //StringBuilder sb = new StringBuilder();
            //String line;
            String lab;
            
           
           //line=br.readLine();
           //line=br.readLine();
           
           StringBuilder sb = new StringBuilder();
           String[] mass;
           while ((lab=br.readLine())!=null){
        	   sb.append(lab+";");
        	   
        	   //mass[i]=lab;   
        	   //i++;
           }
           mass=sb.toString().split(";");
           br.close();
           //mass[0][0]=line;
           //onProgressUpdate(line);
            return mass;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
