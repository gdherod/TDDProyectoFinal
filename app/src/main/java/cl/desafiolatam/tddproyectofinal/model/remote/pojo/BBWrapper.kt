package cl.desafiolatam.tddproyectofinal.model.remote.pojo

class BBWrapper : ArrayList<BBWrapper.Character>() {
    data class Character(
        val charId: Int,
        val name: String,
        val birthday: String,
        val occupation: List<String>,
        val img: String,
        val status: String,
        val nickname: String,
        val appearance: List<Int>,
        val portrayed: String,
        val category: List<String>,
        val betterCallSaulAppearance: List<Int?>
    )
}