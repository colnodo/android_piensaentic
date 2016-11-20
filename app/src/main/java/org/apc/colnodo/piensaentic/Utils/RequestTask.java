package org.apc.colnodo.piensaentic.Utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.provider.Settings;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public abstract class RequestTask {

    private static final String TAG = RequestTask.class.getSimpleName();
    private static final boolean DEBUG = true;
    private final int MAX_REQUEST_TIMEOUT_MS = 20*1000;

    private static final String KEY_APIKEY = "APIID";
    private static final String KEY_AUTHORIZATION = "AUTHORIZATION";
    private static final String KEY_DEVICEID = "UNIQUEDEVICEID";
    private static final String KEY_USERNAME = "Username";
    private static final String KEY_DEVICETYPE = "deviceType";
    private static final String KEY_PUSHTOKEN = "CloudPush-Id";
    private static final String KEY_FBTOKEN = "fbToken";
    private static final String KEY_MGTOKEN = "mgToken";

    private static AsyncHttpClient client = new AsyncHttpClient();

    private int id = 0;
    private Context ctx;
    private ProgressDialog loader;            // Dialog optional
    private Object request;
    private Object response;
    private String url;
    private OnRequestCompleted listener;
    private File file;
    private boolean isUploadFile = false;


    public interface OnRequestCompleted {
        void onRequestResponse(Object response, int taskId);


        void onRequestError(int errorCode, String errorMsg, int taskId);
    }

    public RequestTask(OnRequestCompleted listener) {
        this.listener = listener;
        this.ctx = (Context) listener;
    }

    public RequestTask(OnRequestCompleted listener, int taskId) {
        this.listener = listener;
        this.id = taskId;
        this.ctx = (Context) listener;
    }

    public RequestTask(OnRequestCompleted listener, ProgressDialog loader) {
        this.listener = listener;
        this.loader = loader;
        this.ctx = (Context) listener;
    }

    public RequestTask(OnRequestCompleted listener, ProgressDialog loader, int taskId) {
        this.listener = listener;
        this.loader = loader;
        this.id = taskId;
        this.ctx = (Context) listener;
    }

    public RequestTask(Context ctx, OnRequestCompleted listener, ProgressDialog loader, int taskId) {
        this.listener = listener;
        this.loader = loader;
        this.id = taskId;
        this.ctx = ctx;
    }

    public RequestTask(OnRequestCompleted listener, File file, ProgressDialog loader, int taskId) {
        this.listener = listener;
        this.file = file;
        this.loader = loader;
        this.id = taskId;
        this.isUploadFile = true;
        this.ctx = (Context) listener;
    }

    //***************************************************************************************
    // BASIC TOOLS
    //***************************************************************************************


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    //***************************************************************************************
    // MAIN FLOW
    //***************************************************************************************

    public void executeGet() {
        onPreExecute();
        volleyExecuteGet();
    }

    public void executeGet(String range, String childId) {
        onPreExecute();
        volleyExecuteGet(range, childId);
    }

    public void executeGet(String range, String childId, String friendid) {
        onPreExecute();
        volleyExecuteGet(range, childId, friendid);
    }

    public void executePost() {
        onPreExecute();
        if (isUploadFile)
            uploadFileExecute();
        else
            volleyExecutePost();
    }

    public void executePost(String childId) {
        onPreExecute();
        if (isUploadFile)
            uploadFileExecute();
        else
            volleyExecutePost(childId);
    }

    public void executePost(String childId, String restrictionId) {
        onPreExecute();
        if (isUploadFile)
            uploadFileExecute();
        else
            volleyExecutePost(childId, restrictionId);
    }

    public void executeGetImage() {
        onPreExecute();
        imageDownloadExecute();
    }

    public void executeDelete() {
        onPreExecute();
        volleyExecuteDelete();
    }

    private void volleyExecutePost() {

        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] POST_METHOD URL: " + url);

        JsonObjectRequest jsonRequet = new JsonObjectRequest(Method.POST, url, getRequest(),

                new Listener<JSONObject>() {

                    public void onResponse(JSONObject result) {

                        onPostExecute();

                        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] JSON_RECV:" + result.toString());
                        if (DEBUG)
                            Log.d(TAG, "[REQUEST_TASK] PARSE_AS:" + response.getClass().getSimpleName());

                        Object resp = new Gson().fromJson(result.toString(), response.getClass());
                        listener.onRequestResponse(resp, id);


                    }

                },

                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {
                        onPostExecute();
                        processError(error);
                    }

                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getConfigHeaders();
            }

        };

        jsonRequet.setTag(TAG);
        RequestQueue mQueue = VolleyInstance.getRequestQueue(this.ctx);
        mQueue.add(jsonRequet);

    }

    private void volleyExecutePost(final String childId) {

        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] POST_METHOD URL: " + url);

        JsonObjectRequest jsonRequet = new JsonObjectRequest(Method.POST, url, getRequest(),

                new Listener<JSONObject>() {

                    public void onResponse(JSONObject result) {

                        onPostExecute();

                        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] JSON_RECV:" + result.toString());
                        if (DEBUG)
                            Log.d(TAG, "[REQUEST_TASK] PARSE_AS:" + response.getClass().getSimpleName());

                        Object resp = new Gson().fromJson(result.toString(), response.getClass());
                        listener.onRequestResponse(resp, id);


                    }

                },

                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {
                        onPostExecute();
                        processError(error);
                    }

                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getConfigHeaders();
            }

        };
        jsonRequet.setTag(TAG);
        RequestQueue mQueue = VolleyInstance.getRequestQueue(this.ctx);
        mQueue.add(jsonRequet);
    }

    private void volleyExecutePost(final String childId, final String restrctionId) {

        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] POST_METHOD URL: " + url);

        JsonObjectRequest jsonRequet = new JsonObjectRequest(Method.POST, url, getRequest(),

                new Listener<JSONObject>() {

                    public void onResponse(JSONObject result) {

                        onPostExecute();

                        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] JSON_RECV:" + result.toString());
                        if (DEBUG)
                            Log.d(TAG, "[REQUEST_TASK] PARSE_AS:" + response.getClass().getSimpleName());

                        Object resp = new Gson().fromJson(result.toString(), response.getClass());
                        listener.onRequestResponse(resp, id);


                    }

                },

                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {
                        onPostExecute();
                        processError(error);
                    }

                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getConfigHeaders();
            }

        };

        jsonRequet.setTag(TAG);
        RequestQueue mQueue = VolleyInstance.getRequestQueue(this.ctx);
        mQueue.add(jsonRequet);

    }

    private void volleyExecuteGet() {

        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] GET_METHOD URL: " + url);

        JsonObjectRequest jsonRequet = new JsonObjectRequest(Method.GET, url, (String) null,

                new Listener<JSONObject>() {

                    public void onResponse(JSONObject result) {

                        onPostExecute();

                        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] JSON_RECV:" + result.toString());
                        if (DEBUG)
                            Log.d(TAG, "[REQUEST_TASK] PARSE_AS:" + response.getClass().getSimpleName());

                        Object resp = new Gson().fromJson(result.toString(), response.getClass());
                        listener.onRequestResponse(resp, id);


                    }

                },

                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {

                        onPostExecute();
                        processError(error);

                    }


                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getConfigHeaders();
            }

        };
        jsonRequet.setRetryPolicy(new DefaultRetryPolicy(
                MAX_REQUEST_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue mQueue = VolleyInstance.getRequestQueue(this.ctx);
        mQueue.add(jsonRequet);

    }

    private void volleyExecuteGet(final String range, final String ChildId, final String friendid) {

        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] GET_METHOD URL: " + url);

        JsonObjectRequest jsonRequet = new JsonObjectRequest(Method.GET, url, (String) null,

                new Listener<JSONObject>() {

                    public void onResponse(JSONObject result) {

                        onPostExecute();

                        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] JSON_RECV:" + result.toString());
                        if (DEBUG)
                            Log.d(TAG, "[REQUEST_TASK] PARSE_AS:" + response.getClass().getSimpleName());

                        Object resp = new Gson().fromJson(result.toString(), response.getClass());
                        listener.onRequestResponse(resp, id);


                    }

                },

                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {

                        onPostExecute();
                        processError(error);

                    }


                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getConfigHeaders();
            }

        };

        jsonRequet.setRetryPolicy(new DefaultRetryPolicy(
                MAX_REQUEST_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue mQueue = VolleyInstance.getRequestQueue(this.ctx);
        mQueue.add(jsonRequet);

    }


    private void volleyExecuteGet(final String range, final String ChildId) {

        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] GET_METHOD URL: " + url);

        JsonObjectRequest jsonRequet = new JsonObjectRequest(Method.GET, url, (String) null,

                new Listener<JSONObject>() {

                    public void onResponse(JSONObject result) {

                        onPostExecute();

                        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] JSON_RECV:" + result.toString());
                        if (DEBUG)
                            Log.d(TAG, "[REQUEST_TASK] PARSE_AS:" + response.getClass().getSimpleName());

                        Object resp = new Gson().fromJson(result.toString(), response.getClass());
                        listener.onRequestResponse(resp, id);
                    }
                },

                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {

                        onPostExecute();
                        processError(error);

                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getConfigHeaders();
            }

        };

        jsonRequet.setRetryPolicy(new DefaultRetryPolicy(
                MAX_REQUEST_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue mQueue = VolleyInstance.getRequestQueue(this.ctx);
        mQueue.add(jsonRequet);

    }

    private void volleyExecuteDelete() {

        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] DELETE_METHOD URL: " + url);
        JsonObjectRequest jsonRequet = new JsonObjectRequest(Method.DELETE, url, (String) null,

                new Listener<JSONObject>() {

                    public void onResponse(JSONObject result) {

                        onPostExecute();

                        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] JSON_RECV:" + result.toString());
                        if (DEBUG)
                            Log.d(TAG, "[REQUEST_TASK] PARSE_AS:" + response.getClass().getSimpleName());

                        Object resp = new Gson().fromJson(result.toString(), response.getClass());
                        listener.onRequestResponse(resp, id);

                    }

                },

                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {

                        onPostExecute();
                        processError(error);

                    }


                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getConfigHeaders();
            }

        };
        RequestQueue mQueue = VolleyInstance.getRequestQueue(this.ctx);
        mQueue.add(jsonRequet);

    }

    private Map<String, String> getConfigHeaders() {
        Map<String, String> params = new HashMap<>();
        //read token saved on sharedPreference previously and them put token on headers
        params.put(KEY_APIKEY, LocalConstants.API_KEY);
        return params;
    }

    private void processError(VolleyError error) {

        if (error != null) {

            try {
                String responseBody;
                if (error.networkResponse != null) {
                    responseBody = new String(error.networkResponse.data, "utf-8");
                    JSONObject jsonObject = new JSONObject(responseBody);
                    String errorIna = (String) jsonObject.getJSONArray(ErrorMessage.NON_FIELD_ERRORS).get(0);
                    Log.d(TAG, "ERROR LOGIN : "+ errorIna);
                    int errorCode = error.networkResponse.statusCode;
                    if (DEBUG) Log.d(TAG, "[REQUEST_TASK] VOLLEY ERROR FOR TASK ID: " + id  + ": " + responseBody);
                    if (DEBUG) Log.d(TAG, "[REQUEST_TASK] VOLLEY STATUS CODE TASK ID: "+ id  + ": " + errorCode);
                    if (errorCode == ErrorCodes.SERVICE_NOTFOUND_404) {
                        listener.onRequestError(errorCode, ErrorMessage.serviceNotFound, id);
                    }else if (errorCode == ErrorCodes.SERVICE_NOTFOUND_400) {
                        if (errorIna.equals(ErrorMessage.INNACTIVE_ACCOUNT)) ;{
                            listener.onRequestError(errorCode, errorIna, id);
                        }
                        if (!errorIna.equals(ErrorMessage.INNACTIVE_ACCOUNT)) {
                            listener.onRequestError(errorCode, ErrorMessage.serviceNotFound, id);
                        }
                    } else
                        listener.onRequestError(ErrorCodes.IO_EXCEPT, jsonObject.getString("email"), id);
                } else {
                    if (DEBUG)
                        Log.d(TAG, "[REQUEST_TASK] VOLLEY NETWORK RESPONSE IS NULL! FOR TASK ID: " + id  + ": " + error.getMessage());
                    listener.onRequestError(ErrorCodes.IO_EXCEPT, ErrorMessage.VOLLEY_ERROR_NO_MESSAGE, id);
                }


            } catch (JSONException e) {
                if (DEBUG) Log.d(TAG, "[REQUEST_TASK] VOLLEY ERROR JSONException FOR TASK ID: " + id );
                listener.onRequestError(ErrorCodes.WRONG_RESP, ErrorMessage.WRONG_RESP, id);
            } catch (UnsupportedEncodingException e) {
                if (DEBUG) Log.d(TAG, "[REQUEST_TASK] VOLLEY ERROR UnsupportedEncodingException FOR TASK ID: " + id);
            }
//    	    Crashlytics.log("[REQUEST_TASK] VOLLERY ERROR PARSE_AS:"+response.getClass().getSimpleName());
//        	Crashlytics.logException(error);

        } else {

            if (DEBUG) Log.d(TAG, "[REQUEST_TASK] VOLLEY ERROR is NULL FOR TASK ID: " + id);
            listener.onRequestError(ErrorCodes.VOLLEY_NULL, ErrorMessage.VOLLEY_ERROR_NULL, id);

        }

    }

    private void imageDownloadExecute() {

        if (DEBUG) Log.d(TAG, "[REQUEST_TASK] GET_IMAGE URL: " + url);

        ImageRequest imageRequest = new ImageRequest(url,
                new Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        onPostExecute();

                        listener.onRequestResponse(response, id);
                    }
                }, 0, 0, null,

                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {

                        onPostExecute();

                        if (error != null) {
                            if (DEBUG) Log.d(TAG, "[REQUEST_TASK] imageDownload VOLLEY ERROR!");
                            listener.onRequestError(ErrorCodes.VOLLEY_IMAGE_ERROR, error.getMessage(), id);

                        } else {
                            if (DEBUG) Log.d(TAG, "[REQUEST_TASK] imageDownload VOLLEY ERROR!");
                            listener.onRequestError(ErrorCodes.VOLLEY_NULL, ErrorMessage.VOLLEY_ERROR_NULL, id);
                        }

                    }
                });

        RequestQueue mQueue = VolleyInstance.getRequestQueue(this.ctx);
        mQueue.add(imageRequest);
    }


    private void uploadFileExecute() {

        RequestParams params = new RequestParams();

        try {
            params.put("photo", file);
        } catch (FileNotFoundException e) {
        }
    }


    private JSONObject getRequest() {

        try {
            String req = new Gson().toJson(request);
            if (DEBUG) Log.d(TAG, "[REQUEST_TASK] JSON_SEND: " + req);
            return new JSONObject(req);

        } catch (JSONException e) {

            if (DEBUG) Log.d(TAG, "[REQUEST_TASK] JSONException");
            listener.onRequestError(ErrorCodes.JSON_ERROR, e.getMessage(), id);
//			Crashlytics.logException(e);
            if (DEBUG) e.printStackTrace();
            return null;

        }

    }

    private void onPreExecute() {

        if (loader != null) {
            try {
                if (!loader.isShowing()) loader.show();
            } catch (Exception e) {
                if (DEBUG) {
                    Log.d(TAG, "[REQUEST_TASK] LOADER Exception:");
                    e.printStackTrace();
                }
            }
        }

    }

    private void onPostExecute() {

        if (loader != null) {
            try {
                if (loader.isShowing()) loader.dismiss();
                loader = null;
            } catch (Exception e) {
                if (DEBUG) Log.d(TAG, "[REQUEST_TASK] LOADER Exception:");
                if (DEBUG) e.printStackTrace();
            }
        }

    }

}
