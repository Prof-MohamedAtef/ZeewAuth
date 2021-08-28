package mohamed.atef.zeew.models.requestBody

data class SignUpModel(val action:String, val first_name:String, val last_name:String, val username:String,
                    val phone_number:String, val password:String, val referral_code:String)
