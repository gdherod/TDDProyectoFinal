package cl.desafiolatam.tddproyectofinal.model.remote.pojo

data class Character(
        val char_id: Int,
        val name: String,
        val birthday: String,
        val occupation: List<String>,
        val img: String,
        val status: String,
        val nickname: String,
        //val appearance: List<Int>,
        val portrayed: String,
        val category: String
        //val betterCallSaulAppearance: List<Int?>
)
