package mohamed.atef.zeew.models.responses

data class Result(val success: Int = 0,
                  val customerId: Int = 0,
                  val message: String = "",
                  val token: String = "")