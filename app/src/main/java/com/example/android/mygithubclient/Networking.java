package com.example.android.mygithubclient;

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static com.example.android.mygithubclient.MainActivity.LOG;

public class Networking {

     /** While working on this QueryUtils class, I used the code I got familiar with during the
     * networking part of Udacity android nanodegree scholarship I am currently taking part in.
     * */

    private static final int READ_TIMEOUT = 10000;
    private static final int CONNECT_TIMEOUT = 15000;
    private static final int SUCCESS_CODE = 200;

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG, "Malformed url: " + stringUrl, e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return null;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setConnectTimeout(CONNECT_TIMEOUT);
//            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == SUCCESS_CODE) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG, "ERROR response code " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG, "ERROR with marketRecords JSON", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Repository> extractFeatureFromJson(String repositoryJson) {
        if (TextUtils.isEmpty(repositoryJson)) {
            return null;
        }

        List<Repository> repositories = new ArrayList<>();

        try {
            JSONArray repositoryJsonArray = new JSONArray(repositoryJson);
            for (int i = 0; i < repositoryJsonArray.length(); i++) {

                JSONObject currentRepository = repositoryJsonArray.getJSONObject(i);

                String fullName = currentRepository.getString("full_name");
                String starsGiven = currentRepository.getString("stargazers_count").toString();
                String watchersCount = currentRepository.getString("watchers_count").toString();
                String forksCount = currentRepository.getString("forks_count").toString();
                boolean openSource = currentRepository.getBoolean("private");
                String programmingLanguage = currentRepository.getString("language");
                String issuesCount = currentRepository.getString("open_issues_count").toString();
                String branchesType = currentRepository.getString("default_branch");
                String createdDate = currentRepository.getString("created_at");
                String updatedDate = currentRepository.getString("updated_at");
                long sizeRepo = currentRepository.getLong("size");
                String name = currentRepository.getString("name");

                JSONObject owner = currentRepository.getJSONObject("owner");

                String avatar = owner.getString("avatar_url");
                String userLogin = owner.getString("login");
                Repository repository = new Repository(fullName, starsGiven, avatar, userLogin,
                        watchersCount, forksCount, openSource, programmingLanguage, issuesCount,
                        branchesType, createdDate, updatedDate, sizeRepo, name);
                repositories.add(repository);
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Error parsing JSON", e);
        }
        return repositories;
    }

    public static List<Repository> fetchMarketRecordData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG, "Error with HTTP request", e);
        }
        List<Repository> repositories = extractFeatureFromJson(jsonResponse);
        return repositories;
    }
}
