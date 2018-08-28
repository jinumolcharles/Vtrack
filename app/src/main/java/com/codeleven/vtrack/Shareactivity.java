package com.codeleven.vtrack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CodelevenPC on 24-Jul-18.
 */

public class Shareactivity {


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    private List<mainmodel> imageslist;
    mainmodel allTripFeed;

    //SharePreference FileName
    public static final String PREF_NAME = "EMPDETAILS";

    public static final int private_Mode = 0;

    //EmployeeID (make variable public to access from outside)
    public static String EMPLOYEE_ID = "employeeID";
    public static String IMAGEID = "imageid";
    public static String PASSWORD = "Pswd";
    public static String IMAGE_PATH = "image_path";
    public static String SLUGNAME = "slugname";
    public static String DEPARTMENT_ID = "department_id";
    public static String JOB_TITLE_ID = "job_title_id";
    public static String REPORT_TO = "reportTo";
    public static String YEAR_ID = "yearValue";
    public static String DEVICEID = "deviceId";
    public static String REPORTSTO_OR_NOT = "reportsornot";

    //EmpDetails Available
    public static final String IS_EMP_DETAILS_AVAIL = "IsAvail";


    public Shareactivity(Context ctx) {
        this._context = ctx;
        pref = _context.getSharedPreferences(PREF_NAME, private_Mode);
        editor = pref.edit();
        new getAllTrip().execute();
    }

    @SuppressLint("HardwareIds")
    public void createEmployeeSession(mainmodel model) {

        editor.putBoolean(IS_EMP_DETAILS_AVAIL, true);
        editor.putString(IMAGEID, model.getImage());
//        editor.putString(PASSWORD, employee.password);
        editor.putString(SLUGNAME, model.getSlugname());
//        editor.putString(EMPLOYEE_ID, employee.employeeID);
//        editor.putString(COMPANY_ID, employee.companyID);
//        editor.putString(DEPARTMENT_ID, employee.departmentId);
//        editor.putString(JOB_TITLE_ID, employee.jobTitleId);
//        editor.putString(REPORT_TO, employee.reportTo);
//        editor.putString(REPORTSTO_OR_NOT, employee.repotornot);
//        editor.putString(DEVICEID, Settings.Secure.getString(_context.getContentResolver(),
//                Settings.Secure.ANDROID_ID));


        editor.apply();
    }

    public HashMap<String, String> getEmployeeDetails() {
//
        HashMap<String, String> user = new HashMap<>();
        user.put(IMAGEID, pref.getString(IMAGEID, null));
        user.put(SLUGNAME, pref.getString(SLUGNAME, null));
//        user.put(DEPARTMENT_ID, pref.getString(DEPARTMENT_ID, null));
//        user.put(JOB_TITLE_ID, pref.getString(JOB_TITLE_ID, null));
//        user.put(REPORT_TO, pref.getString(REPORT_TO, null));
//        user.put(USER_NAME, pref.getString(USER_NAME, null));
//        user.put(DEVICEID, pref.getString(DEVICEID, null));
//        user.put(REPORTSTO_OR_NOT, pref.getString(REPORTSTO_OR_NOT, null));
//        user.put(PASSWORD, pref.getString(PASSWORD, null));
        return user;
    }

    public void saveFlags(String key, Object value) {

        pref = _context.getSharedPreferences("dRsharePref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value.toString());
        editor.apply();
    }

    public void saveDate(String key, String value)
    {
        pref = _context.getSharedPreferences("currentDate",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,value);
        editor.apply();
    }



    public String getDate(String key) {
        pref = _context.getSharedPreferences("currentDate", Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }
    public String getFlag(String key) {
        pref = _context.getSharedPreferences("dRsharePref", Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }

//    public void checkEmpDetails() {
//        if (!this.isEmpDetailAvail()) {
//            System.out.println("Emp Details is not Available");
//            // user is not logged in redirect him to LoginActivity Activity
//            Intent intent = new Intent(_context, LoginActivity.class);
//            // Closing all the Activities
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            // Add new Flag to start new Activity
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            // Starting LoginActivity Activity
//            _context.startActivity(intent);
//        }
//    }

    public boolean isEmpDetailAvail() {
        return pref.getBoolean(IS_EMP_DETAILS_AVAIL, false);
    }


    private class getAllTrip extends AsyncTask<Void, Void, Void> {


        protected void onProgressUpdate(Integer... progress) {
// receive progress updates from doInBackground
        }

        @Override
        protected Void doInBackground(Void... params) {
            Htteprequest sh = new Htteprequest();
            // Making a request to url and getting response
            String url = "https://ownshopz.com/wp-json/wp/v2/deal";
            String jsonStr = sh.makeServiceCall(url);

            imageslist = new ArrayList<mainmodel>();


            if (jsonStr != null) {

                try {
//                    JSONObject resObj = new JSONObject(jsonStr);
//                    JSONArray jsono = new JSONObject(jsonStr);
//                    JSONArray mainObject = jsono.getJSONArray("_links");
//                    JSONArray jsonArrayData = mainObject.getJSONArray("wp:attachment");
                    JSONArray tripArray = new JSONArray(jsonStr);
                    for (int t = 0; t < tripArray.length(); t++) {
                        JSONObject trpObj = tripArray.getJSONObject(t);

                        allTripFeed = new mainmodel();
                        allTripFeed.setId(trpObj.getString("image"));
                        allTripFeed.setSlugname(trpObj.getString("slug"));
//                        allTripFeed.setId(trpObj.getString("id"));
                        imageslist.add(allTripFeed);
                    }
                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    });

                }

            } else {
//                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
            }

            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog

            /**
             * Updating parsed JSON data into ListView
             * */

//            Intent intent = new Intent();
//            intent.putExtra("LeaveRequest", (Serializable) imageslist);
//                   setAdapter1();
        }
    }

}

