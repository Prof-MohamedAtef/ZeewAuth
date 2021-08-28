package mohamed.atef.zeew.utils

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import org.json.JSONException
import org.json.JSONObject
import java.net.MalformedURLException
import java.net.URL


class HandleAuthGateways {
    companion object{
        val LOG_TAG: String?="Log"
        private var FB_UserName: String?=null
        private var FB_Profile_URL: String?=null
        private var FB_Email: String?=null
        private var FB_LastName: String?=null
        private var FB_FirstName: String?=null
        private var FB_ProfilePic: String?=null
        private var FB_id: String?=null

        fun handleSignInResult(applicationContext: Context, result: GoogleSignInResult) {
            Log.d(LOG_TAG, "handleSignInResult:" + result.isSuccess)
            Log.d(LOG_TAG, "handleSignInResultCode:" + result.status.statusCode)
            Log.d(LOG_TAG, "handleSignInResultCode:" + result.status.statusMessage)
            var GAccessToken: GoogleSignInAccount? = null
            var Google_personName: String? = null
            var GoogleUserID: String? = null
            var Google_AccessToken: String? = null
            var Google_email: String? = null
            var Google_personPhotoUrl: String?=null

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
                    Toast.makeText(applicationContext,Google_personName+" has signed in", Toast.LENGTH_LONG).show()
                }
            } else {
            }
        }

        fun getFacebookData(`object`: JSONObject): Bundle? {
            return try {
                val bundle = Bundle()
                if (`object`.has("id")) {
                    FB_id = `object`.getString("id")
                }
                try {
                    val profile_pic =
                        URL("https://graph.facebook.com/$FB_id/picture?width=200&height=150")
                    Log.i("profile_pic", profile_pic.toString() + "")
                    FB_ProfilePic = profile_pic.toString()
                    bundle.putString("profile_pic", profile_pic.toString())
                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                    return null
                }
                bundle.putString("idFacebook", FB_id)
                if (`object`.has("first_name")) FB_FirstName =
                    `object`.getString("first_name").toString()
                bundle.putString("first_name", `object`.getString("first_name"))
                if (`object`.has("last_name")) FB_LastName = `object`.getString("last_name").toString()
                bundle.putString("last_name", `object`.getString("last_name"))
                Log.i("accessToken", FB_FirstName.toString()+" "+FB_LastName+", signed in")
                if (`object`.has("email")) FB_Email = `object`.getString("email").toString()
                bundle.putString("email", `object`.getString("email"))
                if (`object`.has("gender")) bundle.putString("gender", `object`.getString("gender"))
                if (`object`.has("birthday")) bundle.putString(
                    "birthday",
                    `object`.getString("birthday")
                )
                if (`object`.has("location")) bundle.putString(
                    "location",
                    `object`.getJSONObject("location").getString("name")
                )
                if (`object`.has("link")) {
                    bundle.putString("link", `object`.getString("link"))
                    FB_Profile_URL = `object`.getString("link")
                }
                FB_UserName = FB_FirstName + " " + FB_LastName
                bundle

            } catch (e: JSONException) {
                Log.d(LOG_TAG, "Error parsing JSON")
                null
            }
        }

//        fun startGoogleIntent(mGoogleApiClient: GoogleApiClient) {
//            val opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient)
//            if (opr.isDone) {
//                val result = opr.get()
//                handleSignInResult(applicationContext, result)
//            } else {
//                opr.setResultCallback(
//                    object : ResultCallback<GoogleSignInResult> {
//                        override fun onResult(p0: GoogleSignInResult) {
//                            handleSignInResult(applicationContext, p0!!)
//                        }
//                    })
//            }
//        }
    }
}