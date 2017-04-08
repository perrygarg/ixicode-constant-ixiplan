package com.ixicode.constant.ixiplan.common.network.volley;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class MultiPartRequest<T> extends Request<T>
{
    private final String DEFAULT_FILE_NAME = "default.jpeg";

    private Class<T> responseType = null;
    private final Response.Listener<T> mListener;
    private final Response.ErrorListener mErrorListener;
    private Gson mGson = null;

    private final String twoHyphens = "--";
    private final String lineEnd = "\r\n";
    private final String boundary = "Boundary+62DF24DD4261A1A8";
    private final String mimeType = "multipart/form-data;boundary=" + boundary;

    private Map<String, String> textPart = null;
    private Map<String, String> filePart = null;
    private Map<String, String> mHeaders = null;
    private String fileName = null;

    public MultiPartRequest(String url, Map<String, String> mHeaders, Class<T> responseType, Response.Listener<T> listener,
                            Response.ErrorListener errorListener, Map<String, String> textPart, Map<String, String> filePart)
    {
        super(Method.POST, url, errorListener);
        this.mListener = listener;
        this.mErrorListener = errorListener;
        this.mHeaders = mHeaders;
        this.responseType = responseType;
        this.textPart = textPart;
        this.filePart = filePart;

        mGson = new GsonBuilder().setExclusionStrategies().create();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError
    {
        return (mHeaders != null) ? mHeaders : super.getHeaders();
    }

    @Override
    public String getBodyContentType()
    {
        return mimeType;
    }

    @Override
    public byte[] getBody() throws AuthFailureError
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        try
        {
            addTextPart(dos);
            addFilePart(dos);

            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
        }
        catch (Exception ex)
        {
            Log.e("Mulitpart Request", "Error occurred while building multipart request");
            ex.printStackTrace();
        }

        return bos.toByteArray();
    }

    private void addTextPart(DataOutputStream dos)
    {
        try
        {
            if (textPart != null)
            {
                for (Map.Entry<String, String> entry : textPart.entrySet())
                {
                    buildTextPart(dos, entry.getKey(), entry.getValue());
                }
            }
        }
        catch(Exception e)
        {
            Log.e("Mulitpart Request", "Error occurred while building multipart request");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param dos
     */
    private void addFilePart(DataOutputStream dos)
    {
        try
        {
            if (filePart != null)
            {
                for (Map.Entry<String, String> entry : filePart.entrySet())
                {
                    buildPart(dos, entry.getKey(), entry.getValue());
                }
            }
        }
        catch(Exception e)
        {
            Log.e("Mulitpart Request", "Error occurred while building multipart request");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param dataOutputStream
     * @param parameterName
     * @param parameterValue
     * @throws IOException
     */
    private void buildTextPart(DataOutputStream dataOutputStream, String parameterName, String parameterValue) throws IOException
    {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + parameterName + "\"" + lineEnd);
        dataOutputStream.writeBytes("Content-Type: text/plain; charset=UTF-8" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);
        dataOutputStream.writeBytes(parameterValue + lineEnd);
    }

    /**
     *
     * @param dataOutputStream
     * @param key
     * @param filePath
     * @throws IOException
     */
    private void buildPart(DataOutputStream dataOutputStream, String key, String filePath) throws IOException
    {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        byte[] fileData = getByteArray(filePath);

        fileName = getFileNameFromPath(filePath);
        fileName = TextUtils.isEmpty(fileName) ? DEFAULT_FILE_NAME : fileName;

        String str = "Content-Disposition: form-data; name=\"" + key + "\"; filename=\""
                + fileName + "\"" + lineEnd;
        dataOutputStream.writeBytes(str);
        dataOutputStream.writeBytes(lineEnd);

        //dataOutputStream.writeBytes("Content-Type: "+ getMimeType(filePath) + lineEnd);

        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(fileData);
        int bytesAvailable = fileInputStream.available();

        int maxBufferSize = 1024 * 1024;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
        byte[] buffer = new byte[bufferSize];

        // read file and write it into form...
        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

        while (bytesRead > 0)
        {
            dataOutputStream.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }

        dataOutputStream.writeBytes(lineEnd);
    }

    /**
     *
     * @param path
     * @return
     */
    private byte[] getByteArray(String path)
    {
        /*Bitmap bitmap = BitmapFactory.decodeFile(path);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);

        return stream.toByteArray();*/

        byte[] bytes = null;
        File file = new File(path);

        try
        {
            FileInputStream stream = new FileInputStream(file);

            bytes = new byte[stream.available()];
            stream.read(bytes);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return bytes;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse)
    {
        try
        {
            String json = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            return Response.success(mGson.fromJson(json, responseType), HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
        catch (UnsupportedEncodingException e)
        {
            return Response.error(new ParseError(e));
        }
        catch (JsonSyntaxException e)
        {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response)
    {
        mListener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error)
    {
        mErrorListener.onErrorResponse(error);
    }



    // url = file path or whatever suitable URL you want.
    private String getMimeType(String url)
    {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null)
        {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    /**
     *
     * @param path
     * @return
     */
    private String getFileNameFromPath(String path)
    {
        File file = new File(path);
        return file.getName();
    }
}