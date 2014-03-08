

import com.become.bid.CustomUserDetailsService
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder

beans = {

	userDetailsService(CustomUserDetailsService)
	passwordEncoder(PlaintextPasswordEncoder)

}
