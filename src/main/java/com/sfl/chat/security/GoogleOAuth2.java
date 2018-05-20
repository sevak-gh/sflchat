package com.sfl.chat.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * google oauth2 helper class.
 *
 * @author Sevak Gharibian
 *
 */
public class GoogleOAuth2 {

    private static final Logger LOG = LoggerFactory.getLogger(GoogleOAuth2.class);

    private static final String GOOGLE_CLIENT_ID = "66744947469-2lf2immos8p8jtichrssnk1b1iufpb8u.apps.googleusercontent.com";
    private static final String GOOGLE_CLIENT_SECRET = "ZUBjHT0t0zyWYcCB5jpfPaeg";
    private static final String GOOGLE_REDIRECT_URL = "http://localhost:8080/oauth2callback";

    public static String getUserEmail(String code) {

        String urlParameters = "code="
                    + code
                    + "&client_id="
                    + GOOGLE_CLIENT_ID
                    + "&client_secret="
                    + GOOGLE_CLIENT_SECRET
                    + "&redirect_uri="
                    + GOOGLE_REDIRECT_URL
                    + "&grant_type=authorization_code";
           
            try { 
            //post parameters
            URL url = new URL("https://accounts.google.com/o/oauth2/token");
            URLConnection urlConn = url.openConnection();
            urlConn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(
                    urlConn.getOutputStream());
            writer.write(urlParameters);
            writer.flush();
            
            //get output in outputString 
            String line, outputString = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }

            LOG.debug("google token: {}", outputString);
            
            //get Access Token 
            JsonObject json = (JsonObject)new JsonParser().parse(outputString);
            String access_token = json.get("access_token").getAsString();
            LOG.debug("google token: {}", access_token);

            //get User Info 
            url = new URL(
                    "https://www.googleapis.com/oauth2/v1/userinfo?access_token="
                            + access_token);
            urlConn = url.openConnection();
            outputString = "";
            reader = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }
            LOG.debug("google user info: {}", outputString);
            
            // Convert JSON response into Pojo class
            GooglePojo data = new Gson().fromJson(outputString, GooglePojo.class);
            writer.close();
            reader.close();        
            return data.email;
    } catch (MalformedURLException e) {
        } catch (ProtocolException e) {
        } catch (IOException e) {
        }
        
        return null;
    }

public static class GooglePojo {
    String id;
    String email;
    boolean verified_email;
    String name;
    String given_name;
    String family_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified_email() {
        return verified_email;
    }

    public void setVerified_email(boolean verified_email) {
        this.verified_email = verified_email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    @Override
    public String toString() {
        return "GooglePojo [id=" + id + ", email=" + email
                + ", verified_email=" + verified_email + ", name=" + name
                + ", given_name=" + given_name + ", family_name=" + family_name
                + "]";
    }
}

}

