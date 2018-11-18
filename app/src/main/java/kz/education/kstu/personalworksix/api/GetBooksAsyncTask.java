package kz.education.kstu.personalworksix.api;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import kz.education.kstu.personalworksix.model.Book;
import kz.education.kstu.personalworksix.model.EntityHandler;
import kz.education.kstu.personalworksix.model.Response;

public class GetBooksAsyncTask extends AsyncTask<String, Void, List<Book>> {
    private static final String TAG = "GetBooksAsyncTask";
    private BooksApiCallback callback;
    private static final String URL_ = "https://www.googleapis.com/books/v1/volumes?maxResults=10&q=";

    public GetBooksAsyncTask(BooksApiCallback booksApiCallback) {
        this.callback = booksApiCallback;
    }

    @Override
    protected List<Book> doInBackground(String... params) {
        List<Book> books = null;
        URL url = null;
        try {
            url = new URL(URL_ + params[0]);
        } catch (MalformedURLException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            String response = readStream(inputStream);
            Response booksResponse = new EntityHandler<Response>().handleDataResponse(response, Response.class);
            books = booksResponse.getItems();
        } catch (IOException | JSONException e) {
            Log.e(TAG, e.getLocalizedMessage());
        } finally {
            urlConnection.disconnect();
        }
        return books;
    }

    @Override
    protected void onPostExecute(List<Book> books) {
        callback.onSuccess(books);
    }

    private String readStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}
