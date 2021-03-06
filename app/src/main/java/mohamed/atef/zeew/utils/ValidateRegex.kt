package mohamed.atef.zeew.utils

import android.text.TextUtils
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText

class ValidateRegex {
    companion object{
        fun isValidEmailAddress(email: String?):Boolean{
            return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun areAllFieldsFilled(firstName:String?, lastName: String?, email: String?, phoneNumber: String?,
        password: String?, confirmPassword: String?, referralCode:String?):Boolean{
            return  !TextUtils.isEmpty(firstName)&&!TextUtils.isEmpty(lastName)&&!TextUtils.isEmpty(email)&&
                    !TextUtils.isEmpty(phoneNumber)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(confirmPassword)&&
                    !TextUtils.isEmpty(referralCode)
        }

        fun isValidLength(password: String?):Boolean{
            password?.let { return password.length >=6 } ?:return false
        }

        fun isPhoneLengthValid(phoneNumber:String?):Boolean{
            phoneNumber?.let { return phoneNumber.length==11 }?:return false
        }

        fun isEqualLength(password: String?, confirmPassword: String?):Boolean{
            password?.let { return password?.length==confirmPassword?.length } ?:return false
        }

        fun isValidPassword(password: String?) : Boolean {
            password?.let {
//                val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
                val passwordPattern = "^(?=.*[0-9])"
                val passwordMatcher = Regex(passwordPattern)

                return passwordMatcher.find(password) != null
            } ?: return false
        }
        fun isNotEmpty(email: String?, password: String?):Boolean{
            return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)
        }
    }
}