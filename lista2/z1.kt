data class UserInput(val name: String?, val email: String?, val age: String?)
data class UserProfile(
    var name: String = "",
    var email: String = "",
    var age: Int = 0,
    var isAdult: Boolean = false
)

fun buildProfile(input: UserInput?, logs: MutableList<String>): UserProfile? {
    return input?.let { inp ->

        val name = inp.name?.trim()?.takeIf { it.length >= 3 }
            ?: run { logs.add(if (inp.name == null) "Name is null" else "Name too short"); return null }

        val email = inp.email?.trim()?.lowercase()?.takeIf { it.contains("@") }
            ?: run { logs.add(if (inp.email == null) "Email is null" else "Invalid email"); return null }

        val age = inp.age
            ?.let { it.toIntOrNull() ?: run { logs.add("Age is not a number"); return null } }
            ?: run { logs.add("Age is null"); return null }

        UserProfile().apply {
            this.name = name
            this.email = email
            this.age = age
            this.isAdult = age >= 18
        }

    }?.also { profile ->
        logs.add("Profile created for ${profile.email}")
    } ?: run { logs.add("Input is null"); null }
}
