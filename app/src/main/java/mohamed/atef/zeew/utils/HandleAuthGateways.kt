package mohamed.atef.zeew.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.facebook.*
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInResult


class HandleAuthGateways {
    companion object{

        /*
      Facebook Sign in attributes
       */

        private var facebookEmail: String?=null
        private var facebookFirstName: String?=null
        val LOG_TAG: String?="Log"
        /*
        Google Sign in attributes
         */
        private var GAccessToken: GoogleSignInAccount? =null
        private var Google_personName: String? = null
        private var GoogleUserID: String? = null
        private var Google_AccessToken: String? = null
        private var Google_email: String? = null
        private var Google_personPhotoUrl: String?=null

        fun handleSignInResult(applicationContext: Context, result: GoogleSignInResult) {
            Log.d(LOG_TAG, "handleSignInResult:" + result.isSuccess)
            Log.d(LOG_TAG, "handleSignInResultCode:" + result.status.statusCode)
            Log.d(LOG_TAG, "handleSignInResultCode:" + result.status.statusMessage)

            if (result.isSuccess) {
                // Signed in successfully, show authenticated UI.
                GAccessToken = result.signInAccount
                if (GAccessToken != null) {
                    val account=result.signInAccount
                    Google_personName = GAccessToken!!.displayName
                    if (GAccessToken!!.photoUrl != null) {
                        Google_personPhotoUrl = GAccessToken!!.photoUrl.toString()
                    }
                    Google_email = GAccessToken!!.email
                    Google_AccessToken = GAccessToken!!.idToken
                    GoogleUserID = GAccessToken!!.id
                    Log.v(LOG_TAG, Google_personName+" has logged in")
                    Toast.makeText(applicationContext,Google_personName+"signed in as:"+result.signInAccount?.email, Toast.LENGTH_LONG).show()
                }
            } else {
                    Log.e("GoogleResult", "Failed Google Access")
                    Toast.makeText(applicationContext,result!!.status.statusMessage, Toast.LENGTH_LONG).show()
            }
        }

        @SuppressLint("LongLogTag")
        fun getUserProfile(token: AccessToken?, userId: String?, context: Context?) {
            val parameters = Bundle()
            parameters.putString(
                "fields",
                "id, first_name, middle_name, last_name, name, picture, email"
            )
            if (BuildConfig.DEBUG) {
                FacebookSdk.setIsDebugEnabled(true)
                FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
            }
            GraphRequest(token,
                "/$userId/",
                parameters,
                HttpMethod.GET,
                GraphRequest.Callback { response ->
                    val jsonObject = response!!.jsonObject

                    // Facebook Access Token
                    // You can see Access Token only in Debug mode.
                    // You can't see it in Logcat using Log.d, Facebook did that to avoid leaking user's access token.


                    // Facebook Id
                    if (jsonObject.has("id")) {
                        val facebookId = jsonObject.getString("id")
                        Log.i("Facebook Id: ", facebookId.toString())
                    } else {
                        Log.i("Facebook Id: ", "Not exists")
                    }


                    // Facebook First Name
                    if (jsonObject.has("first_name")) {
                        facebookFirstName = jsonObject.getString("first_name")
                        Log.i("Facebook First Name: ", facebookFirstName.toString())
                    } else {
                        Log.i("Facebook First Name: ", "Not exists")
                    }


                    // Facebook Middle Name
                    if (jsonObject.has("middle_name")) {
                        val facebookMiddleName = jsonObject.getString("middle_name")
                        Log.i("Facebook Middle Name: ", facebookMiddleName)
                    } else {
                        Log.i("Facebook Middle Name: ", "Not exists")
                    }


                    // Facebook Last Name
                    if (jsonObject.has("last_name")) {
                        val facebookLastName = jsonObject.getString("last_name")
                        Log.i("Facebook Last Name: ", facebookLastName)
                    } else {
                        Log.i("Facebook Last Name: ", "Not exists")
                    }


                    // Facebook Name
                    if (jsonObject.has("name")) {
                        val facebookName = jsonObject.getString("name")
                        Log.i("Facebook Name: ", facebookName)
                    } else {
                        Log.i("Facebook Name: ", "Not exists")
                    }


                    // Facebook Profile Pic URL
                    if (jsonObject.has("picture")) {
                        val facebookPictureObject = jsonObject.getJSONObject("picture")
                        if (facebookPictureObject.has("data")) {
                            val facebookDataObject = facebookPictureObject.getJSONObject("data")
                            if (facebookDataObject.has("url")) {
                                val facebookProfilePicURL = facebookDataObject.getString("url")
                                Log.i("Facebook Profile Pic URL: ", facebookProfilePicURL)
                            }
                        }
                    } else {
                        Log.i("Facebook Profile Pic URL: ", "Not exists")
                    }

                    // Facebook Email
                    if (jsonObject.has("email")) {
                        facebookEmail = jsonObject.getString("email")
                        Log.i("Facebook Email: ", facebookEmail.toString())
                    } else {
                        Log.i("Facebook Email: ", "Not exists")
                    }
                    Toast.makeText(context,facebookFirstName+" logged in as:"+ facebookEmail, Toast.LENGTH_LONG).show()

                }).executeAsync()
        }
    }
}